package org.ucema.sgsp.api.transformation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.ucema.sgsp.api.dto.QuoteDTO;
import org.ucema.sgsp.persistence.model.Order;
import org.ucema.sgsp.persistence.model.Quote;
import org.ucema.sgsp.security.model.User;

@Component
public class QuoteTransformation {

	@Autowired
	private OrderTransformation orderTransformation;
	@Autowired
	private UserTransformation userTransformation;

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
		result.setAmount(quote.getAmount());
		result.setDescription(quote.getDescription());
		if (quote.getOrder() != null) {
			result.setOrderId(quote.getOrder().getId());
		}
		if (quote.getUser() != null) {
			result.setUserId(quote.getUser().getId());
		}

		return result;
	}

	public Quote transformToModel(QuoteDTO quote) {
		Quote result = new Quote();

		result.setId(quote.getId());
		result.setAmount(quote.getAmount());
		result.setDescription(quote.getDescription());
		if (quote.getOrderId() != null) {
			result.setOrder(new Order(quote.getOrderId()));
		}
		if (quote.getUserId() != null) {
			result.setUser(new User(quote.getUserId()));
		}

		return result;
	}
}
