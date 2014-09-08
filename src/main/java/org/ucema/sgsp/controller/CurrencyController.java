package org.ucema.sgsp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.ucema.sgsp.api.dto.CurrencyDTO;
import org.ucema.sgsp.service.CurrencyService;

@Controller
public class CurrencyController {

	@Autowired
	private CurrencyService currencyService;

	@RequestMapping(value = "/currencies/{id}", method = RequestMethod.GET)
	public @ResponseBody CurrencyDTO get(@PathVariable Long id) {
		return currencyService.get(id);
	}
	
	@RequestMapping(value = "/currencies/code/{code}", method = RequestMethod.GET)
	public @ResponseBody CurrencyDTO get(@PathVariable String code) {
		return currencyService.findByCode(code);
	}	

	@RequestMapping(value = "/currencies", method = RequestMethod.GET)
	public @ResponseBody List<CurrencyDTO> list() {
		return currencyService.list();
	}
}
