package org.ucema.sgsp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.ucema.sgsp.service.UserService;

@Controller
public class AdminController {

	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/admin/users/disable/{userId}", method = RequestMethod.PUT)
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public @ResponseBody void disableUser(@PathVariable Long userId) {
		userService.disable(userId);
	}
	
	@RequestMapping(value = "/admin/users/enable/{userId}", method = RequestMethod.PUT)
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public @ResponseBody void enableUser(@PathVariable Long userId) {
		userService.enable(userId);
	}
	
	@RequestMapping(value = "/admin/users/disable/username/{username}", method = RequestMethod.PUT)
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public @ResponseBody void disableUser(@PathVariable String username) {
		userService.disable(username);
	}	
	
	@RequestMapping(value = "/admin/users/enable/username/{username}", method = RequestMethod.PUT)
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public @ResponseBody void enableUser(@PathVariable String username) {
		userService.enable(username);
	}	
}
