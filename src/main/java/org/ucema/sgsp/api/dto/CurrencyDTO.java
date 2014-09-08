package org.ucema.sgsp.api.dto;

public class CurrencyDTO {

	private Long id;
	private String code;
	private String symbol;
	private String name;

	public static CurrencyDTO newInstance() {
		return new CurrencyDTO();
	}

	public CurrencyDTO build() {
		CurrencyDTO result = new CurrencyDTO();

		result.setName(name);
		result.setCode(code);
		result.setSymbol(symbol);
		result.setId(id);

		return result;
	}		
	
	public CurrencyDTO withSymbol(String symbol) {
		this.symbol = symbol;
		return this;
	}
	
	public CurrencyDTO withId(Long id) {
		this.id = id;
		return this;
	}	
	
	public CurrencyDTO withCode(String code) {
		this.code = code;
		return this;
	}	
	
	public CurrencyDTO withName(String name) {
		this.name = name;
		return this;
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
}
