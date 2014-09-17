package org.ucema.sgsp.api.dto;

import java.io.Serializable;

public class UserWorkRateDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private String username;
	private String comment;
	private String rate;
	private Long quoteId;
	private String quoteUsername;
	private String statusType;
	private Boolean recommended;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getRate() {
		return rate;
	}

	public void setRate(String rate) {
		this.rate = rate;
	}

	public Long getQuoteId() {
		return quoteId;
	}

	public void setQuoteId(Long quoteId) {
		this.quoteId = quoteId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getStatusType() {
		return statusType;
	}

	public void setStatusType(String statusType) {
		this.statusType = statusType;
	}

	public Boolean getRecommended() {
		return recommended;
	}

	public void setRecommended(Boolean recommended) {
		this.recommended = recommended;
	}

	public String getQuoteUsername() {
		return quoteUsername;
	}

	public void setQuoteUsername(String quoteUsername) {
		this.quoteUsername = quoteUsername;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("UserWorkRateDTO [id=");
		builder.append(id);
		builder.append(", username=");
		builder.append(username);
		builder.append(", comment=");
		builder.append(comment);
		builder.append(", rate=");
		builder.append(rate);
		builder.append(", quoteId=");
		builder.append(quoteId);
		builder.append(", quoteUsername=");
		builder.append(quoteUsername);
		builder.append(", statusType=");
		builder.append(statusType);
		builder.append(", recommended=");
		builder.append(recommended);
		builder.append("]");
		return builder.toString();
	}
}
