package org.ucema.sgsp.persistence.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "currencies")
public class Currency {

	@Id
	@GeneratedValue
	private Long id;
	private String code;
	private String symbol;
	private String name;
	@Column(name = "is_enabled")
	private Boolean isEnabled;
	@OneToOne
	@JoinColumn(name = "country_id", foreignKey = @ForeignKey(name = "fk_country_currency"))
	private Country country;
	
	public Currency(Long id) {
		super();
		this.id = id;
	}

	public Currency() {
		super();
	}	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Boolean getIsEnabled() {
		return isEnabled;
	}

	public void setIsEnabled(Boolean isEnabled) {
		this.isEnabled = isEnabled;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}
}
