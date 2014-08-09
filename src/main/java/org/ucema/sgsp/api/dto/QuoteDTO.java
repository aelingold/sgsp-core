package org.ucema.sgsp.api.dto;

import java.io.Serializable;
import java.math.BigDecimal;

public class QuoteDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private UserDTO user;
	private OrderDTO order;
	private String description;
	private BigDecimal amount;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public UserDTO getUser() {
		return user;
	}

	public void setUser(UserDTO user) {
		this.user = user;
	}

	public OrderDTO getOrder() {
		return order;
	}

	public void setOrder(OrderDTO order) {
		this.order = order;
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
		builder.append(", user=");
		builder.append(user);
		builder.append(", order=");
		builder.append(order);
		builder.append(", description=");
		builder.append(description);
		builder.append(", amount=");
		builder.append(amount);
		builder.append("]");
		return builder.toString();
	}
}
