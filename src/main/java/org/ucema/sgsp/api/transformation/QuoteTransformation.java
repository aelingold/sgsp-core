package org.ucema.sgsp.api.transformation;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.ucema.sgsp.api.dto.AmountDTO;
import org.ucema.sgsp.api.dto.CurrencyDTO;
import org.ucema.sgsp.api.dto.QuoteDTO;
import org.ucema.sgsp.persistence.model.Amount;
import org.ucema.sgsp.persistence.model.Currency;
import org.ucema.sgsp.persistence.model.Order;
import org.ucema.sgsp.persistence.model.Quote;
import org.ucema.sgsp.security.model.User;
import org.ucema.sgsp.service.CurrencyService;

@Component
public class QuoteTransformation {

	@Autowired
	private OrderTransformation orderTransformation;
	@Autowired
	private UserTransformation userTransformation;
	@Autowired
	private CurrencyService currencyService;
	@Autowired
	private QuoteQuestionTransformation quoteQuestionTransformation;

	public List<QuoteDTO> transformToApi(List<Quote> quotes) {
		List<QuoteDTO> result = new ArrayList<QuoteDTO>();

		for (Quote quote : quotes) {
			result.add(transformToApi(quote));
		}

		return result;
	}

	public List<Quote> transformToModel(List<QuoteDTO> quotes) {
		List<Quote> result = new ArrayList<Quote>();

		for (QuoteDTO quote : quotes) {
			result.add(transformToModel(quote));
		}

		return result;
	}

	public QuoteDTO transformToApi(Quote quote) {
		QuoteDTO result = new QuoteDTO();

		result.setId(quote.getId());
		result.setAmount(buildAmount(quote.getAmount()));
		result.setDescription(quote.getDescription());
		if (quote.getOrder() != null) {
			result.setOrderId(quote.getOrder().getId());
		}
		if (quote.getUser() != null) {
			result.setUserId(quote.getUser().getId());
		}

		if (quote.getQuoteQuestions() != null) {
			result.setQuoteQuestions(quoteQuestionTransformation
					.transformToApi(quote.getQuoteQuestions()));
			result.setQuoteQuestionIds(quote.getQuoteQuestions().stream()
					.map(qq -> qq.getId()).collect(Collectors.toList()));
		}

		return result;
	}

	private AmountDTO buildAmount(Amount amount) {

		AmountDTO result = new AmountDTO();

		result.setAmount(amount.getAmount());
		result.setCurrency(CurrencyDTO.newInstance()
				.withCode(amount.getCurrency().getCode()).build());

		return result;
	}

	public Quote transformToModel(QuoteDTO quote) {
		Quote result = new Quote();

		result.setId(quote.getId());
		result.setAmount(buildAmount(quote.getAmount()));
		result.setDescription(quote.getDescription());
		if (quote.getOrderId() != null) {
			result.setOrder(new Order(quote.getOrderId()));
		}
		if (quote.getUserId() != null) {
			result.setUser(new User(quote.getUserId()));
		}
		if (quote.getQuoteQuestions() != null) {
			result.setQuoteQuestions(quoteQuestionTransformation
					.transformToModel(quote.getQuoteQuestions()));
		}

		return result;
	}

	private Amount buildAmount(AmountDTO amount) {

		Amount result = new Amount();

		result.setAmount(amount.getAmount());
		result.setCurrency(new Currency(currencyService.findByCode(
				amount.getCurrency().getCode()).getId()));

		return result;
	}
}
