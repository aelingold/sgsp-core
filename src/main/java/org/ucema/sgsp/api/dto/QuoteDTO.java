package org.ucema.sgsp.api.dto;

import java.io.Serializable;
import java.math.BigDecimal;

public class QuoteDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private Long userId;
	private Long orderId;
	private String description;
	private BigDecimal amount;

	public QuoteDTO() {
		super();
	}

	public QuoteDTO(Long id, Long userId, Long orderId, String description,
			BigDecimal amount) {
		super();
		this.id = id;
		this.userId = userId;
		this.orderId = orderId;
		this.description = description;
		this.amount = amount;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
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

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("QuoteDTO [id=");
		builder.append(id);
		builder.append(", userId=");
		builder.append(userId);
		builder.append(", orderId=");
		builder.append(orderId);
		builder.append(", description=");
		builder.append(description);
		builder.append(", amount=");
		builder.append(amount);
		builder.append("]");
		return builder.toString();
	}
}
