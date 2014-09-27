package org.ucema.sgsp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.ucema.sgsp.api.dto.UserWorkRateSummarizeDTO;
import org.ucema.sgsp.service.UserWorkRateSummarizeService;

@Controller
public class UserWorkRateSummarizeController {

	@Autowired
	private UserWorkRateSummarizeService userWorkRateSummarizeService;

	@RequestMapping(value = "/user-work-rate-summarizes/{id}", method = RequestMethod.GET)
	public @ResponseBody UserWorkRateSummarizeDTO get(@PathVariable Long id) {
		return userWorkRateSummarizeService.get(id);
	}

	@RequestMapping(value = "/user-work-rate-summarizes", method = RequestMethod.GET)
	public @ResponseBody List<UserWorkRateSummarizeDTO> list() {
		return userWorkRateSummarizeService.list();
	}

	@RequestMapping(value = "/user-work-rate-summarizes", method = RequestMethod.POST)
	public @ResponseBody void saveOrUpdate(
			@RequestBody UserWorkRateSummarizeDTO userWorkRateSummarize) {
		userWorkRateSummarizeService.saveOrUpdate(userWorkRateSummarize);
	}

	@RequestMapping(value = "/user-work-rate-summarizes/{id}", method = RequestMethod.DELETE)
	public @ResponseBody void delete(@PathVariable Long id) {
		userWorkRateSummarizeService.delete(id);
	}
}
