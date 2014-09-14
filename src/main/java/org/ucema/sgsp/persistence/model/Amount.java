package org.ucema.sgsp.persistence.model;

import java.math.BigDecimal;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class Amount {

	private BigDecimal amount;
	@ManyToOne
	@JoinColumn(name = "currency_id")	
	private Currency currency;
	
	public Amount() {
		super();
	}

	public Amount(BigDecimal amount, Long currencyId) {
		super();
		this.amount = amount;
		this.currency = new Currency(currencyId);
	}	
	
	public Amount(BigDecimal amount, Currency currency) {
		super();
		this.amount = amount;
		this.currency = currency;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public Currency getCurrency() {
		return currency;
	}

	public void setCurrency(Currency currency) {
		this.currency = currency;
	}
}
