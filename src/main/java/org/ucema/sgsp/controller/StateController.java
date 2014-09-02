package org.ucema.sgsp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.ucema.sgsp.api.dto.StateDTO;
import org.ucema.sgsp.service.StateService;

@Controller
public class StateController {

	@Autowired
	private StateService stateService;

	@RequestMapping(value = "/states/{id}", method = RequestMethod.GET)
	public @ResponseBody StateDTO get(@PathVariable Long id) {
		return stateService.get(id);
	}
	
	@RequestMapping(value = "/states/code/{code}", method = RequestMethod.GET)
	public @ResponseBody StateDTO get(@PathVariable String code) {
		return stateService.findByCode(code);
	}	

	@RequestMapping(value = "/states", method = RequestMethod.GET)
	public @ResponseBody List<StateDTO> list() {
		return stateService.list();
	}
}
