package org.ucema.sgsp.api.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class QuoteDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private String username;
	private String firstName;
	private String lastName;
	private Long orderId;
	private OrderDTO order;
	private String description;
	private AmountDTO amount;
	private Date validDateUntil;
	private List<QuoteQuestionDTO> quoteQuestions;
	private List<Long> quoteQuestionIds;
	private String statusType;
	private Boolean requireVisit;
	private AmountDTO visitAmount;
	private Date createdAt;

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
		result.setUsername(username);
		result.setDescription(description);
		result.setAmount(amount);
		result.setValidDateUntil(validDateUntil);
		result.setQuoteQuestions(quoteQuestions);
		result.setQuoteQuestionIds(quoteQuestionIds);
		result.setStatusType(statusType);
		result.setOrder(order);
		result.setRequireVisit(requireVisit);
		result.setVisitAmount(visitAmount);

		return result;
	}

	public QuoteDTO withVisit(Boolean requireVisit) {
		this.requireVisit = requireVisit;
		return this;
	}	
	
	public QuoteDTO withStatusType(String statusType) {
		this.statusType = statusType;
		return this;
	}	

	public QuoteDTO withVisitAmount(AmountDTO amount) {
		this.visitAmount = amount;
		return this;
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
	
	public QuoteDTO withUsername(String username) {
		this.username = username;
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

	public String getStatusType() {
		return statusType;
	}

	public void setStatusType(String statusType) {
		this.statusType = statusType;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public OrderDTO getOrder() {
		return order;
	}

	public void setOrder(OrderDTO order) {
		this.order = order;
	}

	public Boolean getRequireVisit() {
		return requireVisit;
	}

	public void setRequireVisit(Boolean requireVisit) {
		this.requireVisit = requireVisit;
	}

	public AmountDTO getVisitAmount() {
		return visitAmount;
	}

	public void setVisitAmount(AmountDTO visitAmount) {
		this.visitAmount = visitAmount;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
}
