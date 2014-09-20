package org.ucema.sgsp.api.dto;

import java.io.Serializable;
import java.util.Date;

public class PaymentDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private Long quoteId;
	private String type;
	private Date createdAt;
	private Date updatedAt;	

	public PaymentDTO() {
		super();
	}
	
	public static PaymentDTO newInstance() {
		return new PaymentDTO();
	}
	
	public PaymentDTO build() {
		PaymentDTO result = new PaymentDTO();
		
		result.setCreatedAt(createdAt);
		result.setUpdatedAt(updatedAt);
		result.setQuoteId(quoteId);
		result.setType(type);
		result.setId(id);
		
		return result;
	}
	
	public PaymentDTO withType(String type) {
		this.type = type;
		return this;
	}	
	
	public PaymentDTO withId(Long id) {
		this.id = id;
		return this;
	}
	
	public PaymentDTO withQuoteId(Long quoteId) {
		this.quoteId = quoteId;
		return this;
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

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
}
