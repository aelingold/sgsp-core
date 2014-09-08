package org.ucema.sgsp.api.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class QuoteDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private Long userId;
	private Long orderId;
	private String description;
	private AmountDTO amount;
	private Date validDateUntil;
	private List<QuoteQuestionDTO> quoteQuestions;
	private List<Long> quoteQuestionIds;

	public QuoteDTO() {
		super();
	}

	public static QuoteDTO newInstance() {
		return new QuoteDTO();
	}

	public QuoteDTO build() {
		QuoteDTO result = new QuoteDTO();

		result.setId(id);
		result.setOrderId(orderId);
		result.setUserId(userId);
		result.setDescription(description);
		result.setAmount(amount);
		result.setValidDateUntil(validDateUntil);
		result.setQuoteQuestions(quoteQuestions);
		result.setQuoteQuestionIds(quoteQuestionIds);

		return result;
	}
	
	public QuoteDTO withAmount(AmountDTO amount) {
		this.amount	 = amount;
		return this;
	}	
	
	public QuoteDTO withValidDateUntil(Date validDateUntil) {
		this.validDateUntil = validDateUntil;
		return this;
	}	
	
	public QuoteDTO withDescription(String description) {
		this.description = description;
		return this;
	}	
	
	public QuoteDTO withUserId(Long userId) {
		this.userId = userId;
		return this;
	}	
	
	public QuoteDTO withId(Long id) {
		this.id = id;
		return this;
	}
	
	public QuoteDTO withOrderId(Long orderId) {
		this.orderId = orderId;
		return this;
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

	public AmountDTO getAmount() {
		return amount;
	}

	public void setAmount(AmountDTO amount) {
		this.amount = amount;
	}

	public Date getValidDateUntil() {
		return validDateUntil;
	}

	public void setValidDateUntil(Date validDateUntil) {
		this.validDateUntil = validDateUntil;
	}

	public List<QuoteQuestionDTO> getQuoteQuestions() {
		return quoteQuestions;
	}

	public void setQuoteQuestions(List<QuoteQuestionDTO> quoteQuestions) {
		this.quoteQuestions = quoteQuestions;
	}

	public List<Long> getQuoteQuestionIds() {
		return quoteQuestionIds;
	}

	public void setQuoteQuestionIds(List<Long> quoteQuestionIds) {
		this.quoteQuestionIds = quoteQuestionIds;
	}
}
