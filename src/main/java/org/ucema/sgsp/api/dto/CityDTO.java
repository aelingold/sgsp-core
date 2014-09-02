package org.ucema.sgsp.api.dto;

import java.io.Serializable;

public class CityDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private String code;
	private String description;
	private String stateCode;

	public static CityDTO newInstance() {
		return new CityDTO();
	}

	public CityDTO build() {
		CityDTO result = new CityDTO();

		result.setDescription(description);
		result.setCode(code);
		result.setStateCode(stateCode);

		return result;
	}	
	
	public CityDTO withStateCode(String stateCode) {
		this.stateCode = stateCode;
		return this;
	}	
	
	public CityDTO withCode(String code) {
		this.code = code;
		return this;
	}	
	
	public CityDTO withDescription(String description) {
		this.description = description;
		return this;
	}	

	public CityDTO() {
		super();
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getStateCode() {
		return stateCode;
	}

	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}
}
