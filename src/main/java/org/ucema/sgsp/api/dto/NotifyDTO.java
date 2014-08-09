package org.ucema.sgsp.api.dto;

import java.io.Serializable;

public class NotifyDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private Long orderId;
	private String type;

	public NotifyDTO() {
		super();
	}

	public NotifyDTO(Long id, Long orderId, String type) {
		super();
		this.id = id;
		this.orderId = orderId;
		this.type = type;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("NotifyDTO [id=");
		builder.append(id);
		builder.append(", orderId=");
		builder.append(orderId);
		builder.append(", type=");
		builder.append(type);
		builder.append("]");
		return builder.toString();
	}
}
