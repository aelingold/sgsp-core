package org.ucema.sgsp.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.ucema.sgsp.persistence.model.Currency;

public interface CurrencyDAO extends JpaRepository<Currency, Long> {

	Currency findByCode(String code);
	Currency findByCountry_Code(String countryCode);
}
