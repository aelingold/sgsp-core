package org.ucema.sgsp.api.transformation;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.ucema.sgsp.api.dto.QuoteDTO;
import org.ucema.sgsp.persistence.model.Order;
import org.ucema.sgsp.persistence.model.Quote;
import org.ucema.sgsp.persistence.model.QuoteStatusType;
import org.ucema.sgsp.security.model.User;
import org.ucema.sgsp.service.UserService;

@Component
public class QuoteTransformation {

	@Autowired
	private OrderTransformation orderTransformation;
	@Autowired
	private UserTransformation userTransformation;
	@Autowired
	private QuoteQuestionTransformation quoteQuestionTransformation;
	@Autowired
	private UserService userService;
	@Autowired
	private AmountTransformation amountTransformation;

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

		if (quote.getOrder() != null) {
			result.setOrder(orderTransformation.transformToApi(quote.getOrder()));
		}
		result.setCreatedAt(quote.getCreatedAt());
		result.setUpdatedAt(quote.getUpdatedAt());

		if (quote.getAmount() != null) {
			if (quote.getRequireVisit() != null && quote.getRequireVisit()) {
				result.setVisitAmount(amountTransformation.buildAmount(quote
						.getAmount()));
			} else {
				result.setAmount(amountTransformation.buildAmount(quote
						.getAmount()));
			}
		}

		result.setDescription(quote.getDescription());
		if (quote.getOrder() != null) {
			result.setOrderId(quote.getOrder().getId());
		}

		if (quote.getUser() != null) {
			result.setUsername(quote.getUser().getEmail());
			result.setFirstName(quote.getUser().getFirstName());
			result.setLastName(quote.getUser().getLastName());
		}

		if (quote.getQuoteQuestions() != null) {
			result.setQuoteQuestions(quoteQuestionTransformation
					.transformToApi(quote.getQuoteQuestions()));
			result.setQuoteQuestionIds(quote.getQuoteQuestions().stream()
					.map(qq -> qq.getId()).collect(Collectors.toList()));
		}
		result.setValidDateUntil(quote.getValidDateUntil());

		return result;
	}

	public Quote transformToModel(QuoteDTO quote) {
		Quote result = new Quote();

		result.setId(quote.getId());
		result.setRequireVisit(quote.getRequireVisit());
		result.setStatusType(QuoteStatusType.valueOf(quote.getStatusType()));
		result.setValidDateUntil(quote.getValidDateUntil());
		result.setCreatedAt(quote.getCreatedAt());
		result.setUpdatedAt(quote.getUpdatedAt());

		if (quote.getAmount() != null && quote.getAmount().getAmount() != null) {
			result.setAmount(amountTransformation.buildAmount(quote.getAmount()));
		}

		if (quote.getVisitAmount() != null
				&& quote.getVisitAmount().getAmount() != null) {
			result.setAmount(amountTransformation.buildAmount(quote
					.getVisitAmount()));
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

	public Quote updateFields(Quote quote, QuoteDTO quoteDTO) {

		quote.setValidDateUntil(quoteDTO.getValidDateUntil());
		quote.setDescription(quoteDTO.getDescription());
		quote.setStatusType(QuoteStatusType.valueOf(quoteDTO.getStatusType()));
		quote.setRequireVisit(quoteDTO.getRequireVisit());
		if (quoteDTO.getRequireVisit() != null && quoteDTO.getRequireVisit()) {
			quote.setAmount(amountTransformation.buildAmount(quoteDTO
					.getVisitAmount().getAmount(), quoteDTO.getVisitAmount()
					.getCurrency().getCode()));
		} else {
			quote.setAmount(amountTransformation.buildAmount(quoteDTO
					.getAmount().getAmount(), quoteDTO.getAmount()
					.getCurrency().getCode()));
		}
		return quote;
	}
}
