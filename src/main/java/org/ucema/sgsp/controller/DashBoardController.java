package org.ucema.sgsp.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

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
import org.ucema.sgsp.api.dto.CurrencyDTO;
import org.ucema.sgsp.api.dto.DashBoardUserDTO;
import org.ucema.sgsp.api.dto.QuoteDTO;
import org.ucema.sgsp.persistence.model.QuoteStatusType;
import org.ucema.sgsp.security.model.CustomUserDetails;
import org.ucema.sgsp.service.CountryService;
import org.ucema.sgsp.service.CurrencyService;
import org.ucema.sgsp.service.DashBoardUserService;
import org.ucema.sgsp.service.OrderService;
import org.ucema.sgsp.service.QuoteService;
import org.ucema.sgsp.service.UserService;
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

	@RequestMapping(value = "/dashboard", method = RequestMethod.GET)
	public String dashboard(WebRequest request, Model model) {
		return dashboard(request, model, "profile");
	}

	@RequestMapping(value = "/dashboard/{tabToShow}", method = RequestMethod.GET)
	public String dashboard(WebRequest request, Model model,
			@PathVariable String tabToShow) {

		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();

		String username = auth.getName(); // get logged in username

		CustomUserDetails userDetails = (CustomUserDetails) auth.getPrincipal();

		DashBoardUserDTO user = dashBoardUserService.getDashBoardUser(username);

		model.addAttribute("user", user);

		model.addAttribute("orders", orderService.list(username));

		model.addAttribute("workAreaQuestions", workAreaQuestionService.list());

		model.addAttribute("workAreaItems", workAreaItemService.list());

		model.addAttribute("tabToShow", tabToShow); // requests, profile,
													// ratings

		model.addAttribute("pendingQuotes",
				quoteService.list(username, QuoteStatusType.PENDING));

		//model.addAttribute("placeQuotes", new PlaceQuoteDTO());
		model.addAttribute("quote", new QuoteDTO());

		model.addAttribute("currency",
				currencyService.findByCountryCode(userDetails.getCountryCode()));

		return VIEW_NAME_DASHBOARD_PAGE;
	}

	@RequestMapping(value = "/dashboard/profile", method = RequestMethod.POST)
	public String profile(
			@Valid @ModelAttribute("user") DashBoardUserDTO dashBoardUser,
			BindingResult result, WebRequest request, Model model) {

		LOGGER.debug("Changing user data with information: {}", dashBoardUser);

		model.addAttribute("tabToShow", "profile"); // requests, profile,
													// ratings

		if (result.hasErrors()) {
			LOGGER.debug("Validation errors found. Rendering form view.");
			return VIEW_NAME_DASHBOARD_PAGE;
		}

		LOGGER.debug("No validation errors found. Continuing changing user data process.");

		userService.update(dashBoardUser);

		return "redirect:/dashboard/profile";
	}

	// @RequestMapping(value = "/dashboard/budgets", method =
	// RequestMethod.POST)
	// public String budgets(
	// @Valid @ModelAttribute("placeQuotes") PlaceQuoteDTO quotes,
	// BindingResult result, WebRequest request, Model model) {
	//
	// model.addAttribute("tabToShow", "budgets"); // requests, profile,
	// // ratings
	//
	// if (result.hasErrors()) {
	// LOGGER.debug("Validation errors found. Rendering form view.");
	// return VIEW_NAME_DASHBOARD_PAGE;
	// }
	//
	// Authentication auth = SecurityContextHolder.getContext()
	// .getAuthentication();
	//
	// CustomUserDetails userDetails = (CustomUserDetails) auth.getPrincipal();
	//
	// CurrencyDTO currency =
	// currencyService.findByCountryCode(userDetails.getCountryCode());
	//
	// quotes.getQuotes().forEach(q -> {
	// if (q.getAmount() != null){
	// q.getAmount().setCurrency(currency);
	//
	// };
	// if (q.getVisitAmount() != null){
	// q.getVisitAmount().setCurrency(currency);
	// };
	// });
	//
	// quoteService.update(quotes);
	//
	// return "redirect:/dashboard/budgets";
	// }

	@RequestMapping(value = "/dashboard/budgets", method = RequestMethod.POST)
	public String budgets(@Valid @ModelAttribute("quote") QuoteDTO quote,
			BindingResult result, WebRequest request, Model model) {

		model.addAttribute("tabToShow", "budgets"); // requests, profile,
													// ratings

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

		};
		if (quote.getVisitAmount() != null) {
			quote.getVisitAmount().setCurrency(currency);
		};

		quoteService.update(quote);

		return "redirect:/dashboard/budgets";
	}
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
	    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	    dateFormat.setLenient(false);

	    // true passed to CustomDateEditor constructor means convert empty String to null
	    binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}	
}
