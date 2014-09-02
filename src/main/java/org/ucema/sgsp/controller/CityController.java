package org.ucema.sgsp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.ucema.sgsp.api.dto.CityDTO;
import org.ucema.sgsp.service.CityService;

@Controller
public class CityController {

	@Autowired
	private CityService cityService;

	@RequestMapping(value = "/cities/{id}", method = RequestMethod.GET)
	public @ResponseBody CityDTO get(@PathVariable Long id) {
		return cityService.get(id);
	}
	
	@RequestMapping(value = "/cities/code/{code}", method = RequestMethod.GET)
	public @ResponseBody CityDTO get(@PathVariable String code) {
		return cityService.findByCode(code);
	}	

	@RequestMapping(value = "/cities", method = RequestMethod.GET)
	public @ResponseBody List<CityDTO> list() {
		return cityService.list();
	}
}
