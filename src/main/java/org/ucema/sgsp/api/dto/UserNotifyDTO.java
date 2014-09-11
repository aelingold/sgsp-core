package org.ucema.sgsp.api.dto;

import java.io.Serializable;

public class UserNotifyDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private Long orderId;
	private String type;
	private String username;

	public UserNotifyDTO() {
		super();
	}

	public static UserNotifyDTO newInstance() {
		return new UserNotifyDTO();
	}

	public UserNotifyDTO build() {
		UserNotifyDTO result = new UserNotifyDTO();

		result.setId(id);
		result.setOrderId(orderId);
		result.setType(type);
		result.setUsername(username);

		return result;
	}
	
	public UserNotifyDTO withUsername(String username) {
		this.username = username;
		return this;
	}	
	
	public UserNotifyDTO withOrderId(Long orderId) {
		this.orderId = orderId;
		return this;
	}
	
	public UserNotifyDTO withType(String type) {
		this.type = type;
		return this;
	}	
	
	public UserNotifyDTO withId(Long id) {
		this.id = id;
		return this;
	}	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
}
