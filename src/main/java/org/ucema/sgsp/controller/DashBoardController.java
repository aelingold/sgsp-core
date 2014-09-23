package org.ucema.sgsp.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
import org.ucema.sgsp.api.dto.AmountDTO;
import org.ucema.sgsp.api.dto.CityDTO;
import org.ucema.sgsp.api.dto.DashBoardConfigDTO;
import org.ucema.sgsp.api.dto.DashBoardUserDTO;
import org.ucema.sgsp.api.dto.OrderDTO;
import org.ucema.sgsp.api.dto.PaymentDTO;
import org.ucema.sgsp.api.dto.QuoteDTO;
import org.ucema.sgsp.api.dto.RatePlanDTO;
import org.ucema.sgsp.api.dto.StateDTO;
import org.ucema.sgsp.api.dto.UserWorkRateDTO;
import org.ucema.sgsp.api.transformation.AmountTransformation;
import org.ucema.sgsp.persistence.model.PaymentStatusType;
import org.ucema.sgsp.persistence.model.QuoteStatusType;
import org.ucema.sgsp.persistence.model.UserWorkRateStatusType;
import org.ucema.sgsp.security.model.CustomUserDetails;
import org.ucema.sgsp.service.CityService;
import org.ucema.sgsp.service.CountryService;
import org.ucema.sgsp.service.CurrencyService;
import org.ucema.sgsp.service.DashBoardUserService;
import org.ucema.sgsp.service.OrderService;
import org.ucema.sgsp.service.PaymentService;
import org.ucema.sgsp.service.QuoteService;
import org.ucema.sgsp.service.RatePlanService;
import org.ucema.sgsp.service.StateService;
import org.ucema.sgsp.service.UserService;
import org.ucema.sgsp.service.UserWorkRateService;
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
	@Autowired
	private UserWorkRateService userWorkRateService;
	@Autowired
	private PaymentService paymentService;
	@Autowired
	private RatePlanService ratePlanService;
	@Autowired
	private AmountTransformation amountTransformation;

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

		List<OrderDTO> orders = orderService.list(username);
		model.addAttribute("orders", orders);

		List<Long> quoteIds = new ArrayList<>();
		orders.forEach(o -> {
			quoteIds.addAll(o.getQuoteIds());
		});

		List<QuoteDTO> allQuotes = quoteService.list(quoteIds);

		model.addAttribute("repliedQuotes", repliedQuotes(allQuotes));

		model.addAttribute("userWorkRates",
				userWorkRateService.userWorkRatesMap(allQuotes));

		model.addAttribute("userWorkRate", new UserWorkRateDTO());

		model.addAttribute("pendingUserWorkRates", userWorkRateService
				.findByUser_EmailAndStatusType(username,
						UserWorkRateStatusType.PENDING));

		model.addAttribute("doneUserWorkRates", userWorkRateService
				.findByQuote_User_EmailAndStatusType(username,
						UserWorkRateStatusType.DONE));

		model.addAttribute("workAreaQuestions", workAreaQuestionService.list());

		model.addAttribute("workAreaItems", workAreaItemService.list());

		model.addAttribute("tabToShow", tabToShow);

		model.addAttribute("pendingQuotes",
				quoteService.list(username, QuoteStatusType.PENDING));

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
		model.addAttribute("configMap",
				stateService.getConfigMap(states, cities));

		return VIEW_NAME_DASHBOARD_PAGE;
	}

	public List<QuoteDTO> repliedQuotes(List<QuoteDTO> allQuotes) {
		return allQuotes
				.stream()
				.filter(q -> q.getStatusType().equals(
						QuoteStatusType.REPLIED.name())
						|| q.getStatusType().equals(
								QuoteStatusType.ACCEPTED.name()))
				.collect(Collectors.toList());
	}

	@RequestMapping(value = "/dashboard/profile", method = RequestMethod.POST)
	public String profile(
			@Valid @ModelAttribute("user") DashBoardUserDTO dashBoardUser,
			BindingResult result, WebRequest request, Model model) {

		LOGGER.debug("Changing user data with information: {}", dashBoardUser);

		model.addAttribute("tabToShow", "profile");

		if (result.hasErrors()) {
			LOGGER.debug("Validation errors found. Rendering form view.");
			return "redirect:/dashboard/profile";
		}

		LOGGER.debug("No validation errors found. Continuing changing user data process.");

		userService.update(dashBoardUser);

		return "redirect:/dashboard/profile";
	}

	@RequestMapping(value = "/dashboard/budgets/replied", method = RequestMethod.POST)
	public String budgetsReplied(
			@Valid @ModelAttribute("quote") QuoteDTO quote,
			BindingResult result, WebRequest request, Model model) {

		model.addAttribute("tabToShow", "budgets");

		if (result.hasErrors()) {
			LOGGER.debug("Validation errors found. Rendering form view.");
			return "redirect:/dashboard/budgets";
		}

		quoteService.update(quote);

		return "redirect:/dashboard/budgets";
	}

	@RequestMapping(value = "/dashboard/ratings", method = RequestMethod.POST)
	public String ratings(
			@Valid @ModelAttribute("userWorkRate") UserWorkRateDTO userWorkRate,
			BindingResult result, WebRequest request, Model model) {

		model.addAttribute("tabToShow", "ratings");

		if (result.hasErrors()) {
			LOGGER.debug("Validation errors found. Rendering form view.");
			return "redirect:/dashboard/ratings";
		}

		userWorkRateService.update(userWorkRate);

		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();

		CustomUserDetails customUserDetails = (CustomUserDetails) auth
				.getPrincipal();

		String ratePlanCode = customUserDetails.getRatePlanCode();

		QuoteDTO quoteDTO = quoteService.get(userWorkRate.getQuoteId());

		RatePlanDTO ratePlan = ratePlanService.findByCode(ratePlanCode);

		if (ratePlanCode.equals(RatePlanDTO.PLAN2)) {

			PaymentDTO payment = new PaymentDTO();
			payment.setStatusType(PaymentStatusType.DONE.name());
			payment.setQuoteId(userWorkRate.getQuoteId());
			payment.setUsername(quoteDTO.getUsername());
			payment.setAmount(ratePlan.getAmount());
			paymentService.saveOrUpdate(payment);

		} else if (ratePlanCode.equals(RatePlanDTO.PLAN3)) {

			PaymentDTO payment = new PaymentDTO();
			payment.setStatusType(PaymentStatusType.DONE.name());
			payment.setQuoteId(userWorkRate.getQuoteId());
			payment.setUsername(quoteDTO.getUsername());

			AmountDTO amount = AmountDTO
					.newInstance()
					.withAmount(
							quoteDTO.getAmount().getAmount()
									.multiply(ratePlan.getPercentageQuantity()))
					.withCurrencyCode(
							quoteDTO.getAmount().getCurrency().getCode());

			payment.setAmount(amount);
			paymentService.saveOrUpdate(payment);
		}

		return "redirect:/dashboard/ratings";
	}

	@RequestMapping(value = "/dashboard/requests/accepted/{quoteId}", method = RequestMethod.POST)
	public String budgetsAccepted(@PathVariable Long quoteId, Model model) {

		model.addAttribute("tabToShow", "requests");

		quoteService.save(quoteId);

		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();

		String username = auth.getName();

		UserWorkRateDTO userWorkRate = new UserWorkRateDTO();
		userWorkRate.setQuoteId(quoteId);
		userWorkRate.setUsername(username);
		userWorkRate.setStatusType(UserWorkRateStatusType.PENDING.name());
		userWorkRateService.saveOrUpdate(userWorkRate);

		return "redirect:/dashboard/requests";
	}

	@RequestMapping(value = "/dashboard/config", method = RequestMethod.POST)
	public String config(
			@Valid @ModelAttribute("config") DashBoardConfigDTO config,
			BindingResult result, WebRequest request, Model model) {

		model.addAttribute("tabToShow", "config");

		if (result.hasErrors()) {
			LOGGER.debug("Validation errors found. Rendering form view.");
			return "redirect:/dashboard/config";
		}

		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();

		String username = auth.getName();

		userWorkZoneService.save(config, username);

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
