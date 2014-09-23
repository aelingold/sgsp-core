package org.ucema.sgsp.api.transformation;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.ucema.sgsp.api.dto.AmountDTO;
import org.ucema.sgsp.persistence.model.Amount;
import org.ucema.sgsp.persistence.model.Currency;
import org.ucema.sgsp.service.CurrencyService;

@Component
public class AmountTransformation {

	@Autowired
	private CurrencyService currencyService;
	@Autowired
	private CurrencyTransformation currencyTransformation;

	public Amount buildAmount(AmountDTO amount) {

		Amount result = new Amount();

		result.setAmount(amount.getAmount());
		result.setCurrency(new Currency(currencyService.findByCode(
				amount.getCurrency().getCode()).getId()));

		return result;
	}

	public AmountDTO buildAmount(Amount amount) {

		AmountDTO result = new AmountDTO();

		result.setAmount(amount.getAmount());
		result.setCurrency(currencyTransformation.transformToApi(amount
				.getCurrency()));

		return result;
	}

	public Amount buildAmount(BigDecimal amount, String currencyCode) {

		return new Amount(amount, currencyService.findByCode(currencyCode)
				.getId());
	}
}
