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
import org.ucema.sgsp.persistence.model.QuoteStatusType;
import org.ucema.sgsp.security.model.User;
import org.ucema.sgsp.service.CurrencyService;
import org.ucema.sgsp.service.UserService;

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
	@Autowired
	private UserService userService;

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
		result.setRequireVisit(quote.getRequireVisit());
		result.setStatusType(quote.getStatusType().name());
		result.setOrder(orderTransformation.transformToApi(quote.getOrder()));

		if (quote.getAmount() != null) {
			result.setAmount(buildAmount(quote.getAmount()));
		}

		result.setDescription(quote.getDescription());
		if (quote.getOrder() != null) {
			result.setOrderId(quote.getOrder().getId());
		}

		if (quote.getUser() != null) {
			result.setUsername(quote.getUser().getEmail());
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
		result.setRequireVisit(quote.getRequireVisit());
		result.setStatusType(QuoteStatusType.valueOf(quote.getStatusType()));

		if (quote.getAmount() != null) {
			if (quote.getRequireVisit() != null && quote.getRequireVisit()) {
				result.setAmount(buildAmount(quote.getVisitAmount()));
			} else {
				result.setAmount(buildAmount(quote.getAmount()));
			}
		}

		result.setDescription(quote.getDescription());
		if (quote.getOrderId() != null) {
			result.setOrder(new Order(quote.getOrderId()));
		}

		if (quote.getQuoteQuestions() != null) {
			result.setQuoteQuestions(quoteQuestionTransformation
					.transformToModel(quote.getQuoteQuestions()));
		}

		if (quote.getUsername() != null) {
			result.setUser(new User(userService
					.findByEmail(quote.getUsername()).getId()));
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

	public Quote updateFields(Quote quote, QuoteDTO quoteDTO) {

		quote.setValidDateUntil(quoteDTO.getValidDateUntil());
		quote.setDescription(quoteDTO.getDescription());
		quote.setStatusType(QuoteStatusType.DONE);
		quote.setRequireVisit(quoteDTO.getRequireVisit());
		if (quoteDTO.getRequireVisit() != null && quoteDTO.getRequireVisit()) {
			quote.setAmount(new Amount(quoteDTO.getVisitAmount().getAmount(),
					currencyService.findByCode(
							quoteDTO.getVisitAmount().getCurrency().getCode())
							.getId()));
		} else {
			quote.setAmount(new Amount(quoteDTO.getAmount().getAmount(),
					currencyService.findByCode(
							quoteDTO.getAmount().getCurrency().getCode())
							.getId()));
		}
		return quote;
	}
}
