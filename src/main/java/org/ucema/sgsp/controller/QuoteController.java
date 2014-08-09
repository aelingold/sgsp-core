package org.ucema.sgsp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.ucema.sgsp.api.dto.QuoteDTO;
import org.ucema.sgsp.service.QuoteService;

@Controller
public class QuoteController {

	@Autowired
	private QuoteService quoteService;

	@RequestMapping(value = "/quotes/{id}", method = RequestMethod.GET)
	public @ResponseBody QuoteDTO get(@PathVariable Long id) {
		return quoteService.get(id);
	}
	
	@RequestMapping(value = "/quotes", method = RequestMethod.GET)
	public @ResponseBody List<QuoteDTO> list() {
		return quoteService.list();
	}

	@RequestMapping(value = "/quotes", method = RequestMethod.POST)
	public @ResponseBody void saveOrUpdate(@RequestBody QuoteDTO quote) {
		quoteService.saveOrUpdate(quote);
	}

	@RequestMapping(value = "/quotes/{id}", method = RequestMethod.DELETE)
	public @ResponseBody void delete(@PathVariable Long id) {
		quoteService.delete(id);
	}
}
