package org.ucema.sgsp.api.dto;

import java.io.Serializable;
import java.util.List;

public class StateDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private String code;
	private String description;
	private List<CityDTO> cities;
	private String countryCode;

	public static StateDTO newInstance() {
		return new StateDTO();
	}

	public StateDTO build() {
		StateDTO result = new StateDTO();

		result.setDescription(description);
		result.setCode(code);
		result.setCities(cities);
		result.setCountryCode(countryCode);
		result.setId(id);

		return result;
	}
	
	public StateDTO withId(Long id) {
		this.id = id;
		return this;
	}	
	
	public StateDTO withCities(List<CityDTO> cities) {
		this.cities = cities;
		return this;
	}
	
	public StateDTO withCountryCode(String countryCode) {
		this.countryCode = countryCode;
		return this;
	}	
	
	public StateDTO withCode(String code) {
		this.code = code;
		return this;
	}	
	
	public StateDTO withDescription(String description) {
		this.description = description;
		return this;
	}	

	public StateDTO() {
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

	public List<CityDTO> getCities() {
		return cities;
	}

	public void setCities(List<CityDTO> cities) {
		this.cities = cities;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
