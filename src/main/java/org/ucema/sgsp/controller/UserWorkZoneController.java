package org.ucema.sgsp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.ucema.sgsp.api.dto.UserWorkZoneDTO;
import org.ucema.sgsp.service.UserWorkZoneService;

@Controller
public class UserWorkZoneController {

	@Autowired
	private UserWorkZoneService userWorkZoneService;

	@RequestMapping(value = "/user-work-zones/{id}", method = RequestMethod.GET)
	public @ResponseBody UserWorkZoneDTO get(@PathVariable Long id) {
		return userWorkZoneService.get(id);
	}

	@RequestMapping(value = "/user-work-zones", method = RequestMethod.GET)
	public @ResponseBody List<UserWorkZoneDTO> list() {
		return userWorkZoneService.list();
	}

	@RequestMapping(value = "/user-work-zones", method = RequestMethod.POST)
	public @ResponseBody void saveOrUpdate(
			@RequestBody UserWorkZoneDTO userWorkZone) {
		userWorkZoneService.saveOrUpdate(userWorkZone);
	}

	@RequestMapping(value = "/user-work-zones/{id}", method = RequestMethod.DELETE)
	public @ResponseBody void delete(@PathVariable Long id) {
		userWorkZoneService.delete(id);
	}
}
