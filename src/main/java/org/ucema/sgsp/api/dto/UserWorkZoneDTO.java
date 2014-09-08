package org.ucema.sgsp.api.dto;

import java.io.Serializable;
import java.util.List;

public class UserWorkZoneDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private String username;
	private String stateCode;
	private Boolean allCities;
	private List<String> cityCodes;

	public UserWorkZoneDTO() {
		super();
	}

	public static UserWorkZoneDTO newInstance() {
		return new UserWorkZoneDTO();
	}

	public UserWorkZoneDTO build() {
		UserWorkZoneDTO result = new UserWorkZoneDTO();

		result.setAllCities(allCities);
		result.setCityCodes(cityCodes);
		result.setStateCode(stateCode);
		result.setId(id);
		result.setUsername(username);

		return result;
	}

	public UserWorkZoneDTO withUsername(String username) {
		this.username = username;
		return this;
	}

	public UserWorkZoneDTO withStateCode(String stateCode) {
		this.stateCode = stateCode;
		return this;
	}

	public UserWorkZoneDTO withId(Long id) {
		this.id = id;
		return this;
	}

	public UserWorkZoneDTO withAllCities(Boolean allCities) {
		this.allCities = allCities;
		return this;
	}

	public UserWorkZoneDTO withCityCodes(List<String> cityCodes) {
		this.cityCodes = cityCodes;
		return this;
	}

	public String getStateCode() {
		return stateCode;
	}

	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Boolean getAllCities() {
		return allCities;
	}

	public void setAllCities(Boolean allCities) {
		this.allCities = allCities;
	}

	public List<String> getCityCodes() {
		return cityCodes;
	}

	public void setCityCodes(List<String> cityCodes) {
		this.cityCodes = cityCodes;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
}
