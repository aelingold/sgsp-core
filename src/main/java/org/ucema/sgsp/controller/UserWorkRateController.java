package org.ucema.sgsp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.ucema.sgsp.api.dto.UserWorkRateDTO;
import org.ucema.sgsp.service.UserWorkRateService;

@Controller
public class UserWorkRateController {

	@Autowired
	private UserWorkRateService userWorkRateService;

	@RequestMapping(value = "/user-work-rates/{id}", method = RequestMethod.GET)
	public @ResponseBody UserWorkRateDTO get(@PathVariable Long id) {
		return userWorkRateService.get(id);
	}
	
	@RequestMapping(value = "/user-work-rates", method = RequestMethod.GET)
	public @ResponseBody List<UserWorkRateDTO> list() {
		return userWorkRateService.list();
	}

	@RequestMapping(value = "/user-work-rates", method = RequestMethod.POST)
	public @ResponseBody void saveOrUpdate(@RequestBody UserWorkRateDTO userWorkRate) {
		userWorkRateService.saveOrUpdate(userWorkRate);
	}

	@RequestMapping(value = "/user-work-rates/{id}", method = RequestMethod.DELETE)
	public @ResponseBody void delete(@PathVariable Long id) {
		userWorkRateService.delete(id);
	}
}
