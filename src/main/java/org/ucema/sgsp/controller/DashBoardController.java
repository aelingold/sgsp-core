package org.ucema.sgsp.controller;

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
import org.ucema.sgsp.api.dto.QuoteQuestionReplyDTO;
import org.ucema.sgsp.api.dto.UserWorkRateDTO;
import org.ucema.sgsp.security.model.CustomUserDetails;
import org.ucema.sgsp.security.model.Role;
import org.ucema.sgsp.service.DashBoardDataService;
import org.ucema.sgsp.service.DashBoardRatingService;
import org.ucema.sgsp.service.DashBoardUserService;
import org.ucema.sgsp.service.QuoteQuestionReplyService;
import org.ucema.sgsp.service.QuoteQuestionService;
import org.ucema.sgsp.service.QuoteService;
import org.ucema.sgsp.service.UserService;
import org.ucema.sgsp.service.UserWorkRateService;
import org.ucema.sgsp.service.UserWorkZoneService;

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
	private QuoteQuestionReplyService quoteQuestionReplyService;

	@RequestMapping(value = "/dashboard", method = RequestMethod.GET)
	public String dashboard(WebRequest request, Model model) {

		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();

		CustomUserDetails userDetails = (CustomUserDetails) auth.getPrincipal();

		String dashboard = "";
		if (userDetails.getRole() != null
				&& userDetails.getRole().equals(Role.ROLE_ADMIN)) {
			dashboard = dashboard(request, model, "admin");
		} else {
			dashboard = dashboard(request, model, "profile");
		}

		return dashboard;
	}

	@RequestMapping(value = "/dashboard/{tabToShow}", method = RequestMethod.GET)
	public String dashboard(WebRequest request, Model model,
			@PathVariable String tabToShow) {

		putDataModelInfo(tabToShow, model);

		return VIEW_NAME_DASHBOARD_PAGE;
	}

	private void putDataModelInfo(String tabToShow, Model model) {
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();

		String username = auth.getName();

		CustomUserDetails userDetails = (CustomUserDetails) auth.getPrincipal();

		Map<String, Object> dataMap = dashBoardDataService.data(username,
				tabToShow, userDetails);
		dataMap.forEach((k, v) -> {
			model.addAttribute(k, v);
		});
	}

	@RequestMapping(value = "/dashboard/profile", method = RequestMethod.POST)
	public String profile(
			@Valid @ModelAttribute("user") DashBoardUserDTO dashBoardUser,
			BindingResult result, WebRequest request, Model model) {

		LOGGER.debug("Changing user data with information: {}", dashBoardUser);

		if (result.hasErrors()) {
			LOGGER.debug("Validation errors found. Rendering form view.");
			return "redirect:/dashboard/profile";
		}

		LOGGER.debug("No validation errors found. Continuing changing user data process.");

		userService.update(dashBoardUser);

		model.addAttribute("successMessage",
				"La informacion de su perfil se ha actualizado correctamente.");		
		
		putDataModelInfo("profile", model);

		return VIEW_NAME_DASHBOARD_PAGE;
	}

	@RequestMapping(value = "/dashboard/budgets/replied", method = RequestMethod.POST)
	public String budgetsReplied(
			@Valid @ModelAttribute("quote") QuoteDTO quote,
			BindingResult result, WebRequest request, Model model) {

		if (result.hasErrors()) {
			LOGGER.debug("Validation errors found. Rendering form view.");
			return "redirect:/dashboard/budgets";
		}

		quoteService.update(quote);
		
		model.addAttribute("successMessage",
				"El presupuesto ha sido respondido satisfactoriamente.");		
		
		putDataModelInfo("budgets", model);	

		return VIEW_NAME_DASHBOARD_PAGE;
	}

	@RequestMapping(value = "/dashboard/ratings", method = RequestMethod.POST)
	public String ratings(
			@Valid @ModelAttribute("userWorkRate") UserWorkRateDTO userWorkRate,
			BindingResult result, WebRequest request, Model model) {

		if (result.hasErrors()) {
			LOGGER.debug("Validation errors found. Rendering form view.");
			return "redirect:/dashboard/ratings";
		}

		dashBoardRatingService.save(userWorkRate);
		
		model.addAttribute("successMessage",
				"Su calificacion ha sido realizada correctamente.");	
		
		putDataModelInfo("ratings", model);		

		return VIEW_NAME_DASHBOARD_PAGE;
	}

	@RequestMapping(value = "/dashboard/questions", method = RequestMethod.POST)
	public String questions(
			@Valid @ModelAttribute("quoteQuestion") QuoteQuestionDTO quoteQuestion,
			BindingResult result, WebRequest request, Model model) {

		if (result.hasErrors()) {
			LOGGER.debug("Validation errors found. Rendering form view.");
			return "redirect:/dashboard/requests";
		}

		quoteQuestionService.save(quoteQuestion);
		
		model.addAttribute("successMessage",
				"Su pregunta ha sido enviada correctamente.");	
		
		putDataModelInfo("requests", model);		

		return VIEW_NAME_DASHBOARD_PAGE;
	}

	@RequestMapping(value = "/dashboard/question-replies", method = RequestMethod.POST)
	public String questionReplies(
			@Valid @ModelAttribute("quoteQuestionReply") QuoteQuestionReplyDTO quoteQuestionReply,
			BindingResult result, WebRequest request, Model model) {

		if (result.hasErrors()) {
			LOGGER.debug("Validation errors found. Rendering form view.");
			return "redirect:/dashboard/requests";
		}

		quoteQuestionReplyService.quoteQuestionReply(quoteQuestionReply);
		
		model.addAttribute("successMessage",
				"Su respuesta ha sido enviada correctamente.");	
		
		putDataModelInfo("questions", model);		

		return VIEW_NAME_DASHBOARD_PAGE;
	}

	@RequestMapping(value = "/dashboard/requests/accepted/{quoteId}", method = RequestMethod.POST)
	public String budgetsAccepted(@PathVariable Long quoteId, Model model) {

		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();

		String username = auth.getName();		
		
		quoteService.accept(quoteId,username);
		
		model.addAttribute("successMessage",
				"El presupuesto ha sido aceptado correctamente.");	
		
		putDataModelInfo("requests", model);		

		return VIEW_NAME_DASHBOARD_PAGE;
	}

	@RequestMapping(value = "/dashboard/config", method = RequestMethod.POST)
	public String config(
			@Valid @ModelAttribute("config") DashBoardConfigDTO config,
			BindingResult result, WebRequest request, Model model) {

		if (result.hasErrors()) {
			LOGGER.debug("Validation errors found. Rendering form view.");
			return "redirect:/dashboard/config";
		}

		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();

		String username = auth.getName();

		userWorkZoneService.deleteByUser_Email(username);
		userWorkZoneService.save(config, username);
		
		model.addAttribute("successMessage",
				"La configuracion ha sido actualizada correctamente.");	
		
		putDataModelInfo("config", model);		

		return VIEW_NAME_DASHBOARD_PAGE;
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
