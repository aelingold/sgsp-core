package org.ucema.sgsp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.ucema.sgsp.api.dto.CountryDTO;
import org.ucema.sgsp.service.CountryService;

@Controller
public class CountryController {

	@Autowired
	private CountryService countryService;

	@RequestMapping(value = "/countries/{id}", method = RequestMethod.GET)
	public @ResponseBody CountryDTO get(@PathVariable Long id) {
		return countryService.get(id);
	}

	@RequestMapping(value = "/countries", method = RequestMethod.GET)
	public @ResponseBody List<CountryDTO> list() {
		return countryService.list();
	}

	@RequestMapping(value = "/countries/import", method = RequestMethod.GET)
	public @ResponseBody void saveOrUpdateAll() {
		countryService.importGeo();
	}
}
