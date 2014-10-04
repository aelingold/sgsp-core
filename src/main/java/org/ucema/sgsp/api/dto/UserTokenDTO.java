package org.ucema.sgsp.api.dto;

import java.io.Serializable;
import java.util.Date;

public class UserTokenDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private String username;
	private String token;
	private Date createdAt;
	private Date updatedAt;
	private Boolean valid;

	public static UserTokenDTO newInstance() {
		return new UserTokenDTO();
	}

	public UserTokenDTO build() {
		UserTokenDTO result = new UserTokenDTO();

		result.setId(id);
		result.setToken(token);
		result.setUsername(username);

		return result;
	}
	
	public UserTokenDTO withId(Long id) {
		this.id = id;
		return this;
	}
	
	public UserTokenDTO withToken(String token) {
		this.token = token;
		return this;
	}
	
	public UserTokenDTO withUsername(String username) {
		this.username = username;
		return this;
	}	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
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

	public Boolean getValid() {
		return valid;
	}

	public void setValid(Boolean valid) {
		this.valid = valid;
	}
}
