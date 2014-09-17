package org.ucema.sgsp.api.dto;

import java.io.Serializable;

public class OrderItemDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private Long orderId;
	private Long workAreaItemId;
	private String value;

	public OrderItemDTO() {
		super();
	}

	public static OrderItemDTO newInstance() {
		return new OrderItemDTO();
	}

	public OrderItemDTO build() {
		OrderItemDTO result = new OrderItemDTO();

		result.setId(id);
		result.setOrderId(orderId);
		result.setValue(value);
		result.setWorkAreaItemId(workAreaItemId);

		return result;
	}

	public OrderItemDTO withId(Long id) {
		this.id = id;
		return this;
	}
	
	public OrderItemDTO withOrderId(Long orderId) {
		this.orderId = orderId;
		return this;
	}
	
	public OrderItemDTO withWorkAreaItemId(Long workAreaItemId) {
		this.workAreaItemId = workAreaItemId;
		return this;
	}
	
	public OrderItemDTO withValue(String value) {
		this.value = value;
		return this;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public Long getWorkAreaItemId() {
		return workAreaItemId;
	}

	public void setWorkAreaItemId(Long workAreaItemId) {
		this.workAreaItemId = workAreaItemId;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("OrderItemDTO [id=");
		builder.append(id);
		builder.append(", orderId=");
		builder.append(orderId);
		builder.append(", workAreaItemId=");
		builder.append(workAreaItemId);
		builder.append(", value=");
		builder.append(value);
		builder.append("]");
		return builder.toString();
	}
}
