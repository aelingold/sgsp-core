package org.ucema.sgsp.api.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class OrderDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private Long userId;
	private Long workAreaId;
	private String workDescription;
	private Date workDate;
	private String stateCode;
	private String cityCode;
	private String squareMeters;
	private Integer airConditionerPower;
	private Boolean pendingNotify = true;
	private Boolean pendingQuotes = true;
	private String workDateType;
	private List<Long> orderItemIds;

	public OrderDTO() {
		super();
	}

	public static OrderDTO newInstance() {
		return new OrderDTO();
	}

	public OrderDTO build() {
		OrderDTO result = new OrderDTO();

		result.setId(id);
		result.setPendingNotify(pendingNotify);
		result.setPendingQuotes(pendingQuotes);
		result.setSquareMeters(squareMeters);
		result.setCityCode(cityCode);
		result.setStateCode(stateCode);
		result.setUserId(userId);
		result.setWorkAreaId(workAreaId);
		result.setWorkDate(workDate);
		result.setWorkDateType(workDateType);
		result.setWorkDescription(workDescription);
		result.setOrderItemIds(orderItemIds);
		result.setAirConditionerPower(airConditionerPower);		

		return result;
	}
	
	public OrderDTO withAirConditionerPower(Integer airConditionerPower) {
		this.airConditionerPower = airConditionerPower;
		return this;
	}	
	
	public OrderDTO withSquareMeters(String squareMeters) {
		this.squareMeters = squareMeters;
		return this;
	}
	
	public OrderDTO withStateCode(String stateCode) {
		this.stateCode = stateCode;
		return this;
	}	
	
	public OrderDTO withCityCode(String cityCode) {
		this.cityCode = cityCode;
		return this;
	}	
	
	public OrderDTO withOrderItemIds(List<Long> orderItemIds) {
		this.orderItemIds = orderItemIds;
		return this;
	}	

	public OrderDTO withId(Long id) {
		this.id = id;
		return this;
	}
	
	public OrderDTO withUserId(Long userId) {
		this.userId = userId;
		return this;
	}
	
	public OrderDTO withWorkAreaId(Long workAreaId) {
		this.workAreaId = workAreaId;
		return this;
	}
	
	public OrderDTO withWorkDate(Date workDate) {
		this.workDate = workDate;
		return this;
	}	
	
	public OrderDTO withWorkDescription(String workDescription) {
		this.workDescription = workDescription;
		return this;
	}	
	
	public OrderDTO withWorkDateType(String workDateType) {
		this.workDateType = workDateType;
		return this;
	}	
	
	public OrderDTO withPendingNotify(Boolean pendingNotify) {
		this.pendingNotify = pendingNotify;
		return this;
	}
	
	public OrderDTO withPendingQuotes(Boolean pendingQuotes) {
		this.pendingQuotes = pendingQuotes;
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getWorkDescription() {
		return workDescription;
	}

	public void setWorkDescription(String workDescription) {
		this.workDescription = workDescription;
	}

	public Date getWorkDate() {
		return workDate;
	}

	public void setWorkDate(Date workDate) {
		this.workDate = workDate;
	}

	public Boolean getPendingNotify() {
		return pendingNotify;
	}

	public void setPendingNotify(Boolean pendingNotify) {
		this.pendingNotify = pendingNotify;
	}

	public Boolean getPendingQuotes() {
		return pendingQuotes;
	}

	public void setPendingQuotes(Boolean pendingQuotes) {
		this.pendingQuotes = pendingQuotes;
	}

	public String getWorkDateType() {
		return workDateType;
	}

	public void setWorkDateType(String workDateType) {
		this.workDateType = workDateType;
	}

	public List<Long> getOrderItemIds() {
		return orderItemIds;
	}

	public void setOrderItemIds(List<Long> orderItemIds) {
		this.orderItemIds = orderItemIds;
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
