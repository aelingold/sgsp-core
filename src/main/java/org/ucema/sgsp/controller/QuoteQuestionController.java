package org.ucema.sgsp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.ucema.sgsp.api.dto.QuoteQuestionDTO;
import org.ucema.sgsp.service.QuoteQuestionService;

@Controller
public class QuoteQuestionController {

	@Autowired
	private QuoteQuestionService quoteQuestionService;

	@RequestMapping(value = "/quote-questions/{id}", method = RequestMethod.GET)
	public @ResponseBody QuoteQuestionDTO get(@PathVariable Long id) {
		return quoteQuestionService.get(id);
	}
	
	@RequestMapping(value = "/quote-questions", method = RequestMethod.GET)
	public @ResponseBody List<QuoteQuestionDTO> list() {
		return quoteQuestionService.list();
	}

	@RequestMapping(value = "/quote-questions", method = RequestMethod.POST)
	public @ResponseBody void saveOrUpdate(@RequestBody QuoteQuestionDTO quoteQuestion) {
		quoteQuestionService.saveOrUpdate(quoteQuestion);
	}

	@RequestMapping(value = "/quote-questions/{id}", method = RequestMethod.DELETE)
	public @ResponseBody void delete(@PathVariable Long id) {
		quoteQuestionService.delete(id);
	}
}
