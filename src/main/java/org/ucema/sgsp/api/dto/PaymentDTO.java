package org.ucema.sgsp.api.dto;

import java.io.Serializable;

public class PaymentDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private Long quoteId;
	private String type;

	public PaymentDTO(Long id, Long quoteId, String type) {
		super();
		this.id = id;
		this.quoteId = quoteId;
		this.type = type;
	}

	public PaymentDTO() {
		super();
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

	public Long getQuoteId() {
		return quoteId;
	}

	public void setQuoteId(Long quoteId) {
		this.quoteId = quoteId;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PaymentDTO [id=");
		builder.append(id);
		builder.append(", quoteId=");
		builder.append(quoteId);
		builder.append(", type=");
		builder.append(type);
		builder.append("]");
		return builder.toString();
	}
}
