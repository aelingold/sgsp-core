package org.ucema.sgsp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.ucema.sgsp.api.dto.UserDTO;
import org.ucema.sgsp.service.UserService;

@Controller
public class UserController {

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
	public @ResponseBody UserDTO get(@PathVariable Long id) {
		return userService.get(id);
	}
	
	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public @ResponseBody List<UserDTO> list() {
		return userService.list();
	}

	@RequestMapping(value = "/users", method = RequestMethod.POST)
	public @ResponseBody void saveOrUpdate(@RequestBody UserDTO user) {
		userService.saveOrUpdate(user);
	}

	@RequestMapping(value = "/users/{id}", method = RequestMethod.DELETE)
	public @ResponseBody void delete(@PathVariable Long id) {
		userService.delete(id);
	}
}
