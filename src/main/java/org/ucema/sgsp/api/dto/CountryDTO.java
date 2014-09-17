package org.ucema.sgsp.api.dto;

import java.io.Serializable;
import java.util.List;

public class CountryDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private String code;
	private String description;
	private List<StateDTO> states;

	public static CountryDTO newInstance() {
		return new CountryDTO();
	}

	public CountryDTO build() {
		CountryDTO result = new CountryDTO();

		result.setDescription(description);
		result.setCode(code);
		result.setStates(states);
		result.setId(id);
		
		return result;
	}
	
	public CountryDTO withId(Long id) {
		this.id = id;
		return this;
	}	
	
	public CountryDTO withStates(List<StateDTO> states) {
		this.states = states;
		return this;
	}		
	
	public CountryDTO withCode(String code) {
		this.code = code;
		return this;
	}	
	
	public CountryDTO withDescription(String description) {
		this.description = description;
		return this;
	}	

	public CountryDTO() {
		super();
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public List<StateDTO> getStates() {
		return states;
	}

	public void setStates(List<StateDTO> states) {
		this.states = states;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CountryDTO [id=");
		builder.append(id);
		builder.append(", code=");
		builder.append(code);
		builder.append(", description=");
		builder.append(description);
		builder.append(", states=");
		builder.append(states);
		builder.append("]");
		return builder.toString();
	}
}
