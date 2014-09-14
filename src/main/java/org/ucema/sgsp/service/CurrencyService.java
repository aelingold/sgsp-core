package org.ucema.sgsp.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.ucema.sgsp.api.dto.CurrencyDTO;
import org.ucema.sgsp.api.transformation.CurrencyTransformation;
import org.ucema.sgsp.persistence.CurrencyDAO;
import org.ucema.sgsp.persistence.model.Currency;

@Service
public class CurrencyService {

	@Autowired
	private CurrencyTransformation currencyTransformation;

	@Autowired
	private CurrencyDAO currencyDAO;

	@Transactional
	public List<CurrencyDTO> list() {

		List<Currency> currencies = currencyDAO.findAll();

		List<Currency> currenciesFiltered = new ArrayList<Currency>();
		for (Currency currency : currencies) {
			if (currency.getIsEnabled()) {
				currenciesFiltered.add(currency);
			}
		}

		return currencyTransformation.transformToApi(currenciesFiltered);
	}

	@Transactional
	public CurrencyDTO get(Long id) {
		Currency currency = currencyDAO.getOne(id);
		if (currency == null) {
			throw new RuntimeException("currency not found");
		}

		if (currency.getIsEnabled()) {
			return currencyTransformation.transformToApi(currency);
		} else {
			return null;
		}
	}

	@Transactional
	public CurrencyDTO findByCountryCode(String code) {
		Currency currency = currencyDAO.findByCountry_Code(code);
		if (currency == null) {
			throw new RuntimeException("currency not found");
		}

		if (currency.getIsEnabled()) {
			return currencyTransformation.transformToApi(currency);
		} else {
			return null;
		}
	}	
	
	@Transactional
	public CurrencyDTO findByCode(String code) {
		Currency currency = currencyDAO.findByCode(code);
		if (currency == null) {
			throw new RuntimeException("currency not found");
		}

		if (currency.getIsEnabled()) {
			return currencyTransformation.transformToApi(currency);
		} else {
			return null;
		}
	}
}
