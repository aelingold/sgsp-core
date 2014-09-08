package org.ucema.sgsp.api.dto;

import java.io.Serializable;

public class UserWorkRateDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private UserDTO user;
	private String comment;
	private String rate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public UserDTO getUser() {
		return user;
	}

	public void setUser(UserDTO user) {
		this.user = user;
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

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("UserWorkRateDTO [id=");
		builder.append(id);
		builder.append(", user=");
		builder.append(user);
		builder.append(", comment=");
		builder.append(comment);
		builder.append(", rate=");
		builder.append(rate);
		builder.append("]");
		return builder.toString();
	}
}
