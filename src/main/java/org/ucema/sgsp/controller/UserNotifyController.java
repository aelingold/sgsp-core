package org.ucema.sgsp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.ucema.sgsp.api.dto.UserNotifyDTO;
import org.ucema.sgsp.service.UserNotifyService;

@Controller
public class UserNotifyController {

	@Autowired
	private UserNotifyService userNotifyService;

	@RequestMapping(value = "/user-notifies/{id}", method = RequestMethod.GET)
	public @ResponseBody UserNotifyDTO get(@PathVariable Long id) {
		return userNotifyService.get(id);
	}
	
	@RequestMapping(value = "/user-notifies", method = RequestMethod.GET)
	public @ResponseBody List<UserNotifyDTO> list() {
		return userNotifyService.list();
	}

	@RequestMapping(value = "/user-notifies", method = RequestMethod.POST)
	public @ResponseBody void saveOrUpdate(@RequestBody UserNotifyDTO userNotify) {
		userNotifyService.saveOrUpdate(userNotify);
	}

	@RequestMapping(value = "/user-notifies/{id}", method = RequestMethod.DELETE)
	public @ResponseBody void delete(@PathVariable Long id) {
		userNotifyService.delete(id);
	}
}
