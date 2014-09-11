package org.ucema.sgsp.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;
import org.ucema.sgsp.api.dto.DashBoardUserDTO;
import org.ucema.sgsp.service.DashBoardUserService;
import org.ucema.sgsp.service.OrderService;
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

	@RequestMapping(value = "/dashboard", method = RequestMethod.GET)
	public String dashboard(WebRequest request, Model model) {
		return dashboard(request, model, "profile");
	}
	
	@RequestMapping(value = "/dashboard/{tabToShow}", method = RequestMethod.GET)
	public String dashboard(WebRequest request, Model model, @PathVariable String tabToShow) {
		
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
				
		String username = auth.getName(); // get logged in username

		DashBoardUserDTO user = dashBoardUserService.getDashBoardUser(username);

		model.addAttribute("user", user);
		
		model.addAttribute("orders", orderService.list(username));
		
		model.addAttribute("workAreaQuestions", workAreaQuestionService.list());
		
		model.addAttribute("workAreaItems", workAreaItemService.list());
		
		model.addAttribute("tabToShow", tabToShow); //requests, profile, ratings

		return VIEW_NAME_DASHBOARD_PAGE;
	}

	@RequestMapping(value = "/dashboard/profile", method = RequestMethod.POST)
	public String changeUserDate(
			@Valid @ModelAttribute("user") DashBoardUserDTO dashBoardUser,
			BindingResult result, WebRequest request, Model model) {
		
		LOGGER.debug("Changing user data with information: {}",
				dashBoardUser);
		
		model.addAttribute("tabToShow", "profile"); //requests, profile, ratings
		
		if (result.hasErrors()) {
			LOGGER.debug("Validation errors found. Rendering form view.");
			return VIEW_NAME_DASHBOARD_PAGE;
		}
		
		LOGGER.debug("No validation errors found. Continuing changing user data process.");
		
		userService.update(dashBoardUser);		
		
		return "redirect:/dashboard/profile";
	}
}
