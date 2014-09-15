package org.ucema.sgsp.api.dto;

import java.io.Serializable;

public class UserWorkZoneDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private String username;
	private String cityCode;

	public UserWorkZoneDTO() {
		super();
	}

	public static UserWorkZoneDTO newInstance() {
		return new UserWorkZoneDTO();
	}

	public UserWorkZoneDTO build() {
		UserWorkZoneDTO result = new UserWorkZoneDTO();

		result.setCityCode(cityCode);
		result.setId(id);
		result.setUsername(username);

		return result;
	}

	public UserWorkZoneDTO withUsername(String username) {
		this.username = username;
		return this;
	}

	public UserWorkZoneDTO withCityCode(String cityCode) {
		this.cityCode = cityCode;
		return this;
	}

	public UserWorkZoneDTO withId(Long id) {
		this.id = id;
		return this;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}
}
