package org.ucema.sgsp.api.dto;

import java.io.Serializable;
import java.util.List;

public class StateDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private String code;
	private String description;
	private List<CityDTO> cities;

	public static StateDTO newInstance() {
		return new StateDTO();
	}

	public StateDTO build() {
		StateDTO result = new StateDTO();

		result.setId(id);
		result.setDescription(description);
		result.setCode(code);
		result.setCities(cities);

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

	public List<CityDTO> getCities() {
		return cities;
	}

	public void setCities(List<CityDTO> cities) {
		this.cities = cities;
	}
}
