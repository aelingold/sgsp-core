package org.ucema.sgsp.api.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class OrderDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private String username;
	private String workAreaCode;
	private String workAreaDescription;
	private String workDescription;
	private Date workDate;
	private String stateCode;
	private String stateDescription;
	private String cityCode;
	private String cityDescription;
	private String squareMeters;
	private Integer airConditionerPower;
	private Boolean pendingNotify = true;
	private String workDateType;
	private List<Long> orderItemIds;
	private List<String> workAreaItemCodes;
	private List<Long> quoteIds;

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
		result.setSquareMeters(squareMeters);
		result.setCityCode(cityCode);
		result.setStateCode(stateCode);
		result.setUsername(username);
		result.setWorkAreaCode(workAreaCode);
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
	
	public OrderDTO withUsername(String username) {
		this.username = username;
		return this;
	}
	
	public OrderDTO withWorkAreaCode(String workAreaCode) {
		this.workAreaCode = workAreaCode;
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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getWorkAreaCode() {
		return workAreaCode;
	}

	public void setWorkAreaCode(String workAreaCode) {
		this.workAreaCode = workAreaCode;
	}

	public String getWorkAreaDescription() {
		return workAreaDescription;
	}

	public void setWorkAreaDescription(String workAreaDescription) {
		this.workAreaDescription = workAreaDescription;
	}

	public String getStateDescription() {
		return stateDescription;
	}

	public void setStateDescription(String stateDescription) {
		this.stateDescription = stateDescription;
	}

	public String getCityDescription() {
		return cityDescription;
	}

	public void setCityDescription(String cityDescription) {
		this.cityDescription = cityDescription;
	}

	public List<String> getWorkAreaItemCodes() {
		return workAreaItemCodes;
	}

	public void setWorkAreaItemCodes(List<String> workAreaItemCodes) {
		this.workAreaItemCodes = workAreaItemCodes;
	}

	public List<Long> getQuoteIds() {
		return quoteIds;
	}

	public void setQuoteIds(List<Long> quoteIds) {
		this.quoteIds = quoteIds;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("OrderDTO [id=");
		builder.append(id);
		builder.append(", username=");
		builder.append(username);
		builder.append(", workAreaCode=");
		builder.append(workAreaCode);
		builder.append(", workAreaDescription=");
		builder.append(workAreaDescription);
		builder.append(", workDescription=");
		builder.append(workDescription);
		builder.append(", workDate=");
		builder.append(workDate);
		builder.append(", stateCode=");
		builder.append(stateCode);
		builder.append(", stateDescription=");
		builder.append(stateDescription);
		builder.append(", cityCode=");
		builder.append(cityCode);
		builder.append(", cityDescription=");
		builder.append(cityDescription);
		builder.append(", squareMeters=");
		builder.append(squareMeters);
		builder.append(", airConditionerPower=");
		builder.append(airConditionerPower);
		builder.append(", pendingNotify=");
		builder.append(pendingNotify);
		builder.append(", workDateType=");
		builder.append(workDateType);
		builder.append(", orderItemIds=");
		builder.append(orderItemIds);
		builder.append(", workAreaItemCodes=");
		builder.append(workAreaItemCodes);
		builder.append(", quoteIds=");
		builder.append(quoteIds);
		builder.append("]");
		return builder.toString();
	}
}
