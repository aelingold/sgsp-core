package org.ucema.sgsp.api.dto;

import java.io.Serializable;

public class QuoteQuestionDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private Long quoteId;
	private String description;
	private QuoteQuestionReplyDTO quoteQuestionReply;
	private Long quoteQuestionReplyId;

	public QuoteQuestionDTO() {
		super();
	}

	public static QuoteQuestionDTO newInstance() {
		return new QuoteQuestionDTO();
	}

	public QuoteQuestionDTO build() {
		QuoteQuestionDTO result = new QuoteQuestionDTO();

		result.setId(id);
		result.setQuoteId(quoteId);
		result.setDescription(description);

		return result;
	}

	public QuoteQuestionDTO withDescription(String description) {
		this.description = description;
		return this;
	}

	public QuoteQuestionDTO withQuoteId(Long quoteId) {
		this.quoteId = quoteId;
		return this;
	}

	public QuoteQuestionDTO withId(Long id) {
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

	public Long getQuoteId() {
		return quoteId;
	}

	public void setQuoteId(Long quoteId) {
		this.quoteId = quoteId;
	}

	public QuoteQuestionReplyDTO getQuoteQuestionReply() {
		return quoteQuestionReply;
	}

	public void setQuoteQuestionReply(QuoteQuestionReplyDTO quoteQuestionReply) {
		this.quoteQuestionReply = quoteQuestionReply;
	}

	public Long getQuoteQuestionReplyId() {
		return quoteQuestionReplyId;
	}

	public void setQuoteQuestionReplyId(Long quoteQuestionReplyId) {
		this.quoteQuestionReplyId = quoteQuestionReplyId;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("QuoteQuestionDTO [id=");
		builder.append(id);
		builder.append(", quoteId=");
		builder.append(quoteId);
		builder.append(", description=");
		builder.append(description);
		builder.append(", quoteQuestionReply=");
		builder.append(quoteQuestionReply);
		builder.append(", quoteQuestionReplyId=");
		builder.append(quoteQuestionReplyId);
		builder.append("]");
		return builder.toString();
	}
}
