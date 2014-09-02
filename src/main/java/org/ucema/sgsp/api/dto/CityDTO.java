package org.ucema.sgsp.api.dto;

import java.io.Serializable;

public class CityDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private String code;
	private String description;

	public static CityDTO newInstance() {
		return new CityDTO();
	}

	public CityDTO build() {
		CityDTO result = new CityDTO();

		result.setId(id);
		result.setDescription(description);
		result.setCode(code);

		return result;
	}
	
	public CityDTO withId(Long id) {
		this.id = id;
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
}
