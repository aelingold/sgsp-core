package org.ucema.sgsp.api.dto;

import java.io.Serializable;
import java.util.List;

public class PlaceOrderDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private String username;
	private String workAreaCode;
	private String workDescription;
	private String workDateType;
	private String location;
	private List<String> workAreaItemCodes;

	public PlaceOrderDTO() {
		super();
	}

	public static PlaceOrderDTO newInstance() {
		return new PlaceOrderDTO();
	}

	public PlaceOrderDTO build() {
		PlaceOrderDTO result = new PlaceOrderDTO();

		result.setLocation(location);
		result.setWorkAreaCode(workAreaCode);
		result.setWorkDateType(workDateType);
		result.setWorkDescription(workDescription);
		result.setWorkAreaItemCodes(workAreaItemCodes);
		result.setUsername(username);

		return result;
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
	
	public PlaceOrderDTO withLocation(String location) {
		this.location = location;
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

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
}
