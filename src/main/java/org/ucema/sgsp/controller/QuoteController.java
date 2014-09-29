package org.ucema.sgsp.controller;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.ucema.sgsp.api.dto.QuoteDTO;
import org.ucema.sgsp.api.dto.ReportWorkAreaDTO;
import org.ucema.sgsp.persistence.model.QuoteStatusType;
import org.ucema.sgsp.service.QuoteService;

import com.google.common.collect.Sets;

@Controller
public class QuoteController {

	@Autowired
	private QuoteService quoteService;

	@RequestMapping(value = "/quotes/{id}", method = RequestMethod.GET)
	public @ResponseBody QuoteDTO get(@PathVariable Long id) {
		return quoteService.get(id);
	}

	@RequestMapping(value = "/quotes/{userId}/{statusType}", method = RequestMethod.GET)
	public @ResponseBody List<QuoteDTO> list(@PathVariable Long userId,
			@PathVariable String statusType) {
		return quoteService.list(userId, QuoteStatusType.valueOf(statusType));
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
	
	@RequestMapping(value = "/quotes/services/{quoteStatusType}", method = RequestMethod.GET)
	public @ResponseBody Collection<ReportWorkAreaDTO> servicesStatusType(@PathVariable QuoteStatusType quoteStatusType) {
		return quoteService.quotesServices(Sets.newHashSet(quoteStatusType));
	}
}
