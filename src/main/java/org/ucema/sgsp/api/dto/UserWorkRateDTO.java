package org.ucema.sgsp.api.dto;

import java.io.Serializable;

public class UserWorkRateDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private String username;
	private String userFirstName;
	private String userLastName;
	private String comment;
	private String ratingType;
	private Long quoteId;
	private String quoteUsername;
	private String quoteUserFirstName;
	private String quoteUserLastName;
	private String quoteUserWorkAreaDescription;
	private String statusType;
	private Boolean workCompleted;

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

	public String getQuoteUsername() {
		return quoteUsername;
	}

	public void setQuoteUsername(String quoteUsername) {
		this.quoteUsername = quoteUsername;
	}

	public String getRatingType() {
		return ratingType;
	}

	public void setRatingType(String ratingType) {
		this.ratingType = ratingType;
	}

	public Boolean getWorkCompleted() {
		return workCompleted;
	}

	public void setWorkCompleted(Boolean workCompleted) {
		this.workCompleted = workCompleted;
	}

	public String getQuoteUserWorkAreaDescription() {
		return quoteUserWorkAreaDescription;
	}

	public void setQuoteUserWorkAreaDescription(String quoteUserWorkAreaDescription) {
		this.quoteUserWorkAreaDescription = quoteUserWorkAreaDescription;
	}

	public String getQuoteUserFirstName() {
		return quoteUserFirstName;
	}

	public void setQuoteUserFirstName(String quoteUserFirstName) {
		this.quoteUserFirstName = quoteUserFirstName;
	}

	public String getQuoteUserLastName() {
		return quoteUserLastName;
	}

	public void setQuoteUserLastName(String quoteUserLastName) {
		this.quoteUserLastName = quoteUserLastName;
	}

	public String getUserFirstName() {
		return userFirstName;
	}

	public void setUserFirstName(String userFirstName) {
		this.userFirstName = userFirstName;
	}

	public String getUserLastName() {
		return userLastName;
	}

	public void setUserLastName(String userLastName) {
		this.userLastName = userLastName;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("UserWorkRateDTO [id=");
		builder.append(id);
		builder.append(", username=");
		builder.append(username);
		builder.append(", userFirstName=");
		builder.append(userFirstName);
		builder.append(", userLastName=");
		builder.append(userLastName);
		builder.append(", comment=");
		builder.append(comment);
		builder.append(", ratingType=");
		builder.append(ratingType);
		builder.append(", quoteId=");
		builder.append(quoteId);
		builder.append(", quoteUsername=");
		builder.append(quoteUsername);
		builder.append(", quoteUserFirstName=");
		builder.append(quoteUserFirstName);
		builder.append(", quoteUserLastName=");
		builder.append(quoteUserLastName);
		builder.append(", quoteUserWorkAreaDescription=");
		builder.append(quoteUserWorkAreaDescription);
		builder.append(", statusType=");
		builder.append(statusType);
		builder.append(", workCompleted=");
		builder.append(workCompleted);
		builder.append("]");
		return builder.toString();
	}
}
