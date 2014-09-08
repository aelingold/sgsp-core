package org.ucema.sgsp.api.dto;

import java.math.BigDecimal;

public class AmountDTO {

	private BigDecimal amount;
	private CurrencyDTO currency;

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public CurrencyDTO getCurrency() {
		return currency;
	}

	public void setCurrency(CurrencyDTO currency) {
		this.currency = currency;
	}
	
	public static AmountDTO newInstance() {
		return new AmountDTO();
	}

	public AmountDTO build() {
		AmountDTO result = new AmountDTO();

		result.setAmount(amount);
		result.setCurrency(currency);

		return result;
	}
	
	public AmountDTO withCurrencyCode(String currencyCode) {
		this.currency = CurrencyDTO.newInstance().withCode(currencyCode).build();
		return this;
	}	
	
	public AmountDTO withCurrencyId(Long currencyId) {
		this.currency = CurrencyDTO.newInstance().withId(currencyId).build();
		return this;
	}	
	
	public AmountDTO withAmount(BigDecimal amount) {
		this.amount	 = amount;
		return this;
	}	
}
