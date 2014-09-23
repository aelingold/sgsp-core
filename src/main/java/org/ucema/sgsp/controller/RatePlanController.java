package org.ucema.sgsp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.ucema.sgsp.api.dto.RatePlanDTO;
import org.ucema.sgsp.service.RatePlanService;

@Controller
public class RatePlanController {

	@Autowired
	private RatePlanService ratePlanService;

	@RequestMapping(value = "/rate-plans/{id}", method = RequestMethod.GET)
	public @ResponseBody RatePlanDTO get(@PathVariable Long id) {
		return ratePlanService.get(id);
	}
	
	@RequestMapping(value = "/rate-plans", method = RequestMethod.GET)
	public @ResponseBody List<RatePlanDTO> list() {
		return ratePlanService.list();
	}
}
