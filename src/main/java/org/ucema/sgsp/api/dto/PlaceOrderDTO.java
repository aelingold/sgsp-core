package org.ucema.sgsp.api.dto;

import java.io.Serializable;
import java.util.List;

public class PlaceOrderDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private String username;
	private String workAreaCode;
	private String workDescription;
	private String workDateType;
	private String stateCode;
	private String cityCode;
	private String squareMeters;
	private Integer airConditionerPower;
	private List<String> workAreaItemCodes;

	public PlaceOrderDTO() {
		super();
	}

	public static PlaceOrderDTO newInstance() {
		return new PlaceOrderDTO();
	}

	public PlaceOrderDTO build() {
		PlaceOrderDTO result = new PlaceOrderDTO();

		result.setWorkAreaCode(workAreaCode);
		result.setWorkDateType(workDateType);
		result.setWorkDescription(workDescription);
		result.setWorkAreaItemCodes(workAreaItemCodes);
		result.setUsername(username);
		result.setSquareMeters(squareMeters);
		result.setCityCode(cityCode);
		result.setStateCode(stateCode);
		result.setAirConditionerPower(airConditionerPower);

		return result;
	}
	
	public PlaceOrderDTO withSquareMeters(String squareMeters) {
		this.squareMeters = squareMeters;
		return this;
	}

	public PlaceOrderDTO withAirConditionerPower(Integer airConditionerPower) {
		this.airConditionerPower = airConditionerPower;
		return this;
	}	
	
	public PlaceOrderDTO withStateCode(String stateCode) {
		this.stateCode = stateCode;
		return this;
	}	
	
	public PlaceOrderDTO withCityCode(String cityCode) {
		this.cityCode = cityCode;
		return this;
	}	
	
	public PlaceOrderDTO withUsername(String username) {
		this.username = username;
		return this;
	}	
	
	public PlaceOrderDTO withWorkAreaItemCodes(List<String> workAreaItemCodes) {
		this.workAreaItemCodes = workAreaItemCodes;
		return this;
	}	
	
	public PlaceOrderDTO withWorkAreaCode(String workAreaCode) {
		this.workAreaCode = workAreaCode;
		return this;
	}	
	
	public PlaceOrderDTO withWorkDescription(String workDescription) {
		this.workDescription = workDescription;
		return this;
	}	
	
	public PlaceOrderDTO withWorkDateType(String workDateType) {
		this.workDateType = workDateType;
		return this;
	}		

	public String getWorkDescription() {
		return workDescription;
	}

	public void setWorkDescription(String workDescription) {
		this.workDescription = workDescription;
	}

	public String getWorkDateType() {
		return workDateType;
	}

	public void setWorkDateType(String workDateType) {
		this.workDateType = workDateType;
	}

	public List<String> getWorkAreaItemCodes() {
		return workAreaItemCodes;
	}

	public void setWorkAreaItemCodes(List<String> workAreaItemCodes) {
		this.workAreaItemCodes = workAreaItemCodes;
	}

	public String getWorkAreaCode() {
		return workAreaCode;
	}

	public void setWorkAreaCode(String workAreaCode) {
		this.workAreaCode = workAreaCode;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getStateCode() {
		return stateCode;
	}

	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	public String getSquareMeters() {
		return squareMeters;
	}

	public void setSquareMeters(String squareMeters) {
		this.squareMeters = squareMeters;
	}

	public Integer getAirConditionerPower() {
		return airConditionerPower;
	}

	public void setAirConditionerPower(Integer airConditionerPower) {
		this.airConditionerPower = airConditionerPower;
	}
}
