package org.ucema.sgsp.api.dto;

import java.io.Serializable;

public class ContactMessageDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private String name;
	private String email;
	private String message;

	public static ContactMessageDTO newInstance() {
		return new ContactMessageDTO();
	}

	public ContactMessageDTO build() {
		ContactMessageDTO result = new ContactMessageDTO();

		result.setId(id);
		result.setEmail(email);
		result.setMessage(message);
		result.setName(name);

		return result;
	}	
	
	public ContactMessageDTO withMessage(String message) {
		this.message = message;
		return this;
	}
	
	public ContactMessageDTO withId(Long id) {
		this.id = id;
		return this;
	}	
	
	public ContactMessageDTO withName(String name) {
		this.name = name;
		return this;
	}	
	
	public ContactMessageDTO withEmail(String email) {
		this.email = email;
		return this;
	}	

	public ContactMessageDTO() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
