package org.ucema.sgsp.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;
import org.ucema.sgsp.api.dto.DashBoardConfigDTO;
import org.ucema.sgsp.api.dto.DashBoardUserDTO;
import org.ucema.sgsp.api.dto.QuoteDTO;
import org.ucema.sgsp.api.dto.QuoteQuestionDTO;
import org.ucema.sgsp.api.dto.UserWorkRateDTO;
import org.ucema.sgsp.persistence.model.UserWorkRateStatusType;
import org.ucema.sgsp.security.model.CustomUserDetails;
import org.ucema.sgsp.security.model.Role;
import org.ucema.sgsp.service.DashBoardDataService;
import org.ucema.sgsp.service.DashBoardRatingService;
import org.ucema.sgsp.service.DashBoardUserService;
import org.ucema.sgsp.service.QuoteQuestionService;
import org.ucema.sgsp.service.QuoteService;
import org.ucema.sgsp.service.UserService;
import org.ucema.sgsp.service.UserWorkRateService;
import org.ucema.sgsp.service.UserWorkZoneService;

import freemarker.template.Configuration;
import freemarker.template.TemplateException;

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
	private QuoteService quoteService;
	@Autowired
	private UserWorkZoneService userWorkZoneService;
	@Autowired
	private UserWorkRateService userWorkRateService;
	@Autowired
	private DashBoardRatingService dashBoardRatingService;
	@Autowired
	private DashBoardDataService dashBoardDataService;
	@Autowired
	private QuoteQuestionService quoteQuestionService;
	@Autowired
	private Configuration configuration;

	@RequestMapping(value = "/dashboard", method = RequestMethod.GET)
	public String dashboard(WebRequest request, Model model) {
		
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();

		CustomUserDetails userDetails = (CustomUserDetails) auth.getPrincipal();		
		
		String dashboard = "";
		if (userDetails.getRole() != null && userDetails.getRole().equals(Role.ROLE_ADMIN)){
			dashboard = dashboard(request, model, "admin");
		}else{
			dashboard = dashboard(request, model, "profile");	
		}
		
		return dashboard;
	}

	@RequestMapping(value = "/dashboard/{tabToShow}", method = RequestMethod.GET)
	public String dashboard(WebRequest request, Model model,
			@PathVariable String tabToShow) {

		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();

		String username = auth.getName();

		CustomUserDetails userDetails = (CustomUserDetails) auth.getPrincipal();

		Map<String, Object> dataMap = dashBoardDataService.data(username,
				tabToShow, userDetails);
		dataMap.forEach((k, v) -> {
			model.addAttribute(k, v);
		});
		
		try {
			String processTemplateIntoString = FreeMarkerTemplateUtils.processTemplateIntoString(configuration.getTemplate("dashboard.ftl"), model);
		} catch (IOException | TemplateException e) {
			LOGGER.error(e.getMessage(),e);
		}

		return VIEW_NAME_DASHBOARD_PAGE;
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

		dashBoardRatingService.save(userWorkRate);

		return "redirect:/dashboard/ratings";
	}
	
	@RequestMapping(value = "/dashboard/questions", method = RequestMethod.POST)
	public String questions(
			@Valid @ModelAttribute("quoteQuestion") QuoteQuestionDTO quoteQuestion,
			BindingResult result, WebRequest request, Model model) {

		model.addAttribute("tabToShow", "requests");

		if (result.hasErrors()) {
			LOGGER.debug("Validation errors found. Rendering form view.");
			return "redirect:/dashboard/requests";
		}

		quoteQuestionService.save(quoteQuestion);

		return "redirect:/dashboard/requests";
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
		userWorkRate.setSummarized(false);
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

		userWorkZoneService.deleteByUser_Email(username);
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
