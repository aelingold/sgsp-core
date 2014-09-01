package org.ucema.sgsp.api.dto;

import java.io.Serializable;
import java.util.List;

public class PlaceOrderDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long userId;
	private Long workAreaId;
	private String workAreaCode;
	private String workDescription;
	private String workDateType;
	private String place;
	private List<String> workAreaItemCodes;

	public PlaceOrderDTO() {
		super();
	}

	public static PlaceOrderDTO newInstance() {
		return new PlaceOrderDTO();
	}

	public PlaceOrderDTO build() {
		PlaceOrderDTO result = new PlaceOrderDTO();

		result.setPlace(place);
		result.setUserId(userId);
		result.setWorkAreaId(workAreaId);
		result.setWorkAreaCode(workAreaCode);
		result.setWorkDateType(workDateType);
		result.setWorkDescription(workDescription);
		result.setWorkAreaItemCodes(workAreaItemCodes);

		return result;
	}	
	
	public PlaceOrderDTO withWorkAreaItemCodes(List<String> workAreaItemCodes) {
		this.workAreaItemCodes = workAreaItemCodes;
		return this;
	}	
	
	public PlaceOrderDTO withWorkAreaCode(String workAreaCode) {
		this.workAreaCode = workAreaCode;
		return this;
	}	
	
	public PlaceOrderDTO withUserId(Long userId) {
		this.userId = userId;
		return this;
	}
	
	public PlaceOrderDTO withWorkAreaId(Long workAreaId) {
		this.workAreaId = workAreaId;
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
	
	public PlaceOrderDTO withPlace(String place) {
		this.place = place;
		return this;
	}	
	
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getWorkAreaId() {
		return workAreaId;
	}

	public void setWorkAreaId(Long workAreaId) {
		this.workAreaId = workAreaId;
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

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
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
}
