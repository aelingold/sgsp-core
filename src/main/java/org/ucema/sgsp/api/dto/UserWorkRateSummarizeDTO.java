package org.ucema.sgsp.api.dto;

import java.util.Date;

public class UserWorkRateSummarizeDTO {

	private Long id;
	private Long userId;
	private String username;
	private Long positiveQuantity;
	private Long negativeQuantity;
	private Long neutralQuantity;
	private Date createdAt;
	private Date updatedAt;

	public UserWorkRateSummarizeDTO() {
		super();
	}

	public static UserWorkRateSummarizeDTO newInstance() {
		return new UserWorkRateSummarizeDTO();
	}

	public UserWorkRateSummarizeDTO build() {
		UserWorkRateSummarizeDTO result = new UserWorkRateSummarizeDTO();

		result.setId(id);
		result.setUserId(userId);
		result.setNegativeQuantity(negativeQuantity);
		result.setPositiveQuantity(positiveQuantity);
		result.setNeutralQuantity(neutralQuantity);
		result.setCreatedAt(createdAt);
		result.setUpdatedAt(updatedAt);
		result.setUsername(username);

		return result;
	}

	public UserWorkRateSummarizeDTO withPositiveQuantity(Long positiveQuantity) {
		this.positiveQuantity = positiveQuantity;
		return this;
	}

	public UserWorkRateSummarizeDTO withNeutralQuantity(Long neutralQuantity) {
		this.neutralQuantity = neutralQuantity;
		return this;
	}

	public UserWorkRateSummarizeDTO withNegativeQuantity(Long negativeQuantity) {
		this.negativeQuantity = negativeQuantity;
		return this;
	}

	public UserWorkRateSummarizeDTO withUserId(Long userId) {
		this.userId = userId;
		return this;
	}

	public UserWorkRateSummarizeDTO withId(Long id) {
		this.id = id;
		return this;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getPositiveQuantity() {
		return positiveQuantity;
	}

	public void setPositiveQuantity(Long positiveQuantity) {
		this.positiveQuantity = positiveQuantity;
	}

	public Long getNeutralQuantity() {
		return neutralQuantity;
	}

	public void setNeutralQuantity(Long neutralQuantity) {
		this.neutralQuantity = neutralQuantity;
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

	public Long getNegativeQuantity() {
		return negativeQuantity;
	}

	public void setNegativeQuantity(Long negativeQuantity) {
		this.negativeQuantity = negativeQuantity;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("UserWorkRateSummarizeDTO [id=");
		builder.append(id);
		builder.append(", userId=");
		builder.append(userId);
		builder.append(", username=");
		builder.append(username);
		builder.append(", positiveQuantity=");
		builder.append(positiveQuantity);
		builder.append(", negativeQuantity=");
		builder.append(negativeQuantity);
		builder.append(", neutralQuantity=");
		builder.append(neutralQuantity);
		builder.append(", createdAt=");
		builder.append(createdAt);
		builder.append(", updatedAt=");
		builder.append(updatedAt);
		builder.append("]");
		return builder.toString();
	}
}
