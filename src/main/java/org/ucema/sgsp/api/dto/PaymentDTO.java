package org.ucema.sgsp.api.dto;

import java.io.Serializable;
import java.util.Date;

public class PaymentDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private Long quoteId;
	private String paymentType;
	private Date createdAt;
	private Date updatedAt;
	private String statusType;
	private AmountDTO amount;	

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
		result.setPaymentType(paymentType);
		result.setId(id);
		result.setStatusType(statusType);
		result.setAmount(amount);
		
		return result;
	}
	
	public PaymentDTO withAmount(AmountDTO amount) {
		this.amount	 = amount;
		return this;
	}	

	public PaymentDTO withStatusType(String statusType) {
		this.statusType = statusType;
		return this;
	}	
	
	public PaymentDTO withPaymentType(String paymentType) {
		this.paymentType = paymentType;
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

	public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
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

	public String getStatusType() {
		return statusType;
	}

	public void setStatusType(String statusType) {
		this.statusType = statusType;
	}

	public AmountDTO getAmount() {
		return amount;
	}

	public void setAmount(AmountDTO amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "PaymentDTO [id=" + id + ", quoteId=" + quoteId
				+ ", paymentType=" + paymentType + ", createdAt=" + createdAt
				+ ", updatedAt=" + updatedAt + ", statusType=" + statusType
				+ ", amount=" + amount + "]";
	}
}
