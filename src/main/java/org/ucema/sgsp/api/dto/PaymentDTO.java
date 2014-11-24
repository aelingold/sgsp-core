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
	private Long userId;
	private String username;
	private Boolean userEnabled;
	private Date paymentDateAllowedBefore;
	private Date paymentEffectiveDate;
	private String ratePlanCode;
	private String ratePlanDescription;

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
		result.setPaymentDateAllowedBefore(paymentDateAllowedBefore);
		result.setPaymentEffectiveDate(paymentEffectiveDate);
		
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

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Date getPaymentDateAllowedBefore() {
		return paymentDateAllowedBefore;
	}

	public void setPaymentDateAllowedBefore(Date paymentDateAllowedBefore) {
		this.paymentDateAllowedBefore = paymentDateAllowedBefore;
	}

	public Boolean getUserEnabled() {
		return userEnabled;
	}

	public void setUserEnabled(Boolean userEnabled) {
		this.userEnabled = userEnabled;
	}

	public Date getPaymentEffectiveDate() {
		return paymentEffectiveDate;
	}

	public void setPaymentEffectiveDate(Date paymentEffectiveDate) {
		this.paymentEffectiveDate = paymentEffectiveDate;
	}

	public String getRatePlanCode() {
		return ratePlanCode;
	}

	public void setRatePlanCode(String ratePlanCode) {
		this.ratePlanCode = ratePlanCode;
	}

	public String getRatePlanDescription() {
		return ratePlanDescription;
	}

	public void setRatePlanDescription(String ratePlanDescription) {
		this.ratePlanDescription = ratePlanDescription;
	}

	@Override
	public String toString() {
		return "PaymentDTO [id=" + id + ", quoteId=" + quoteId
				+ ", paymentType=" + paymentType + ", createdAt=" + createdAt
				+ ", updatedAt=" + updatedAt + ", statusType=" + statusType
				+ ", amount=" + amount + ", userId=" + userId + ", username="
				+ username + ", userEnabled=" + userEnabled
				+ ", paymentDateAllowedBefore=" + paymentDateAllowedBefore
				+ ", paymentEffectiveDate=" + paymentEffectiveDate
				+ ", ratePlanCode=" + ratePlanCode + ", ratePlanDescription="
				+ ratePlanDescription + "]";
	}
}
