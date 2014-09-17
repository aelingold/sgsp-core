package org.ucema.sgsp.api.dto;

import java.io.Serializable;

public class QuoteQuestionReplyDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private Long quoteQuestionId;
	private String description;

	public QuoteQuestionReplyDTO() {
		super();
	}

	public static QuoteQuestionReplyDTO newInstance() {
		return new QuoteQuestionReplyDTO();
	}

	public QuoteQuestionReplyDTO build() {
		QuoteQuestionReplyDTO result = new QuoteQuestionReplyDTO();

		result.setId(id);
		result.setQuoteQuestionId(quoteQuestionId);
		result.setDescription(description);

		return result;
	}

	public QuoteQuestionReplyDTO withDescription(String description) {
		this.description = description;
		return this;
	}

	public QuoteQuestionReplyDTO withQuoteQuestionId(Long quoteQuestionId) {
		this.quoteQuestionId = quoteQuestionId;
		return this;
	}

	public QuoteQuestionReplyDTO withId(Long id) {
		this.id = id;
		return this;
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

	public Long getQuoteQuestionId() {
		return quoteQuestionId;
	}

	public void setQuoteQuestionId(Long quoteQuestionId) {
		this.quoteQuestionId = quoteQuestionId;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("QuoteQuestionReplyDTO [id=");
		builder.append(id);
		builder.append(", quoteQuestionId=");
		builder.append(quoteQuestionId);
		builder.append(", description=");
		builder.append(description);
		builder.append("]");
		return builder.toString();
	}
}
