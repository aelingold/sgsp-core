package org.ucema.sgsp.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;
import org.ucema.sgsp.api.dto.CityDTO;
import org.ucema.sgsp.api.dto.CurrencyDTO;
import org.ucema.sgsp.api.dto.DashBoardConfigDTO;
import org.ucema.sgsp.api.dto.DashBoardUserDTO;
import org.ucema.sgsp.api.dto.QuoteDTO;
import org.ucema.sgsp.api.dto.StateDTO;
import org.ucema.sgsp.api.dto.UserWorkZoneDTO;
import org.ucema.sgsp.persistence.model.QuoteStatusType;
import org.ucema.sgsp.security.model.CustomUserDetails;
import org.ucema.sgsp.service.CityService;
import org.ucema.sgsp.service.CountryService;
import org.ucema.sgsp.service.CurrencyService;
import org.ucema.sgsp.service.DashBoardUserService;
import org.ucema.sgsp.service.OrderService;
import org.ucema.sgsp.service.QuoteService;
import org.ucema.sgsp.service.StateService;
import org.ucema.sgsp.service.UserService;
import org.ucema.sgsp.service.UserWorkZoneService;
import org.ucema.sgsp.service.WorkAreaItemService;
import org.ucema.sgsp.service.WorkAreaQuestionService;

@Controller
public class DashBoardController {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(DashBoardController.class);
	protected static final String VIEW_NAME_DASHBOARD_PAGE = "dashboard";
	@Autowired
	private DashBoardUserService dashBoardUserService;
	@Autowired
	private UserService userService;
	@Autowired
	private OrderService orderService;
	@Autowired
	private WorkAreaQuestionService workAreaQuestionService;
	@Autowired
	private WorkAreaItemService workAreaItemService;
	@Autowired
	private QuoteService quoteService;
	@Autowired
	private CountryService countryService;
	@Autowired
	private CurrencyService currencyService;
	@Autowired
	private StateService stateService;
	@Autowired
	private CityService cityService;
	@Autowired
	private UserWorkZoneService userWorkZoneService;

	@RequestMapping(value = "/dashboard", method = RequestMethod.GET)
	public String dashboard(WebRequest request, Model model) {
		return dashboard(request, model, "profile");
	}

	@RequestMapping(value = "/dashboard/{tabToShow}", method = RequestMethod.GET)
	public String dashboard(WebRequest request, Model model,
			@PathVariable String tabToShow) {

		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();

		String username = auth.getName();

		CustomUserDetails userDetails = (CustomUserDetails) auth.getPrincipal();

		DashBoardUserDTO user = dashBoardUserService.getDashBoardUser(username);

		model.addAttribute("user", user);

		model.addAttribute("orders", orderService.list(username));

		model.addAttribute("workAreaQuestions", workAreaQuestionService.list());

		model.addAttribute("workAreaItems", workAreaItemService.list());

		model.addAttribute("tabToShow", tabToShow);

		model.addAttribute("pendingQuotes",
				quoteService.list(username, QuoteStatusType.PENDING));

		// model.addAttribute("placeQuotes", new PlaceQuoteDTO());
		model.addAttribute("quote", new QuoteDTO());

		model.addAttribute("currency",
				currencyService.findByCountryCode(userDetails.getCountryCode()));

		List<CityDTO> cities = cityService.list();
		model.addAttribute("cities", cities);

		List<StateDTO> states = stateService.list();
		model.addAttribute("states", states);

		DashBoardConfigDTO dashBoardConfig = new DashBoardConfigDTO();
		userWorkZoneService.list(username).forEach(uwz -> {
			dashBoardConfig.getCityCodes().add(uwz.getCityCode());
		});
		
		model.addAttribute("config", dashBoardConfig);	
		model.addAttribute("configMap", getConfigMap(states, cities));

		return VIEW_NAME_DASHBOARD_PAGE;
	}

	private Map<String, Map<String, String>> getConfigMap(
			List<StateDTO> states, List<CityDTO> cities) {
		Map<String, Map<String, String>> configMap = new HashMap<String, Map<String, String>>();
		states.forEach(s -> {
			Map<String, String> citiesMap = new HashMap<String, String>();
			
			List<CityDTO> citiesFiltered = cities.stream()
					.filter(c -> c.getStateCode().equals(s.getCode()))
					.collect(Collectors.toList());
			
			citiesFiltered.forEach(cf -> {
				citiesMap.put(cf.getCode(), cf.getDescription());
			});
			
			configMap.put(s.getCode(), citiesMap);
		});

		return configMap;
	}

	@RequestMapping(value = "/dashboard/profile", method = RequestMethod.POST)
	public String profile(
			@Valid @ModelAttribute("user") DashBoardUserDTO dashBoardUser,
			BindingResult result, WebRequest request, Model model) {

		LOGGER.debug("Changing user data with information: {}", dashBoardUser);

		model.addAttribute("tabToShow", "profile");

		if (result.hasErrors()) {
			LOGGER.debug("Validation errors found. Rendering form view.");
			return VIEW_NAME_DASHBOARD_PAGE;
		}

		LOGGER.debug("No validation errors found. Continuing changing user data process.");

		userService.update(dashBoardUser);

		return "redirect:/dashboard/profile";
	}

	@RequestMapping(value = "/dashboard/budgets", method = RequestMethod.POST)
	public String budgets(@Valid @ModelAttribute("quote") QuoteDTO quote,
			BindingResult result, WebRequest request, Model model) {

		model.addAttribute("tabToShow", "budgets");

		if (result.hasErrors()) {
			LOGGER.debug("Validation errors found. Rendering form view.");
			return VIEW_NAME_DASHBOARD_PAGE;
		}

		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();

		CustomUserDetails userDetails = (CustomUserDetails) auth.getPrincipal();

		CurrencyDTO currency = currencyService.findByCountryCode(userDetails
				.getCountryCode());

		if (quote.getAmount() != null) {
			quote.getAmount().setCurrency(currency);

		}
		;
		if (quote.getVisitAmount() != null) {
			quote.getVisitAmount().setCurrency(currency);
		}
		;

		quoteService.update(quote);

		return "redirect:/dashboard/budgets";
	}

	@RequestMapping(value = "/dashboard/config", method = RequestMethod.POST)
	public String config(
			@Valid @ModelAttribute("config") DashBoardConfigDTO config,
			BindingResult result, WebRequest request, Model model) {

		model.addAttribute("tabToShow", "config");

		if (result.hasErrors()) {
			LOGGER.debug("Validation errors found. Rendering form view.");
			return VIEW_NAME_DASHBOARD_PAGE;
		}

		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();

		String username = auth.getName();

		userWorkZoneService.deleteAll();

		List<UserWorkZoneDTO> userWorkZonesNew = new ArrayList<UserWorkZoneDTO>();

		config.getCityCodes().forEach(cc -> {
			UserWorkZoneDTO uwz = new UserWorkZoneDTO();
			uwz.setCityCode(cc);
			uwz.setUsername(username);
			userWorkZonesNew.add(uwz);
		});
		userWorkZoneService.saveOrUpdate(userWorkZonesNew);

		return "redirect:/dashboard/config";
	}

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		dateFormat.setLenient(false);

		// true passed to CustomDateEditor constructor means convert empty
		// String to null
		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				dateFormat, true));
	}
}
