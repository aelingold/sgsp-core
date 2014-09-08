package org.ucema.sgsp.api.transformation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.ucema.sgsp.api.dto.CurrencyDTO;
import org.ucema.sgsp.persistence.model.Currency;

@Component
public class CurrencyTransformation {

	public List<CurrencyDTO> transformToApi(List<Currency> currencies) {
		List<CurrencyDTO> result = new ArrayList<CurrencyDTO>();

		for (Currency currency : currencies) {
			result.add(transformToApi(currency));
		}

		return result;
	}

	public CurrencyDTO transformToApi(Currency currency) {
		CurrencyDTO result = new CurrencyDTO();

		result.setCode(currency.getCode());
		result.setName(currency.getName());
		
		result.setSymbol(currency.getSymbol());
		result.setId(currency.getId());

		return result;
	}
}
