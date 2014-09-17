package org.ucema.sgsp.api.dto;

import java.io.Serializable;
import java.util.List;

public class WorkAreaDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private String code;
	private String description;
	private List<String> workAreaItemCodes;
	private Boolean isEnabled;

	public static WorkAreaDTO newInstance() {
		return new WorkAreaDTO();
	}

	public WorkAreaDTO build() {
		WorkAreaDTO result = new WorkAreaDTO();

		result.setId(id);
		result.setDescription(description);
		result.setCode(code);
		result.setWorkAreaItemCodes(workAreaItemCodes);
		result.setIsEnabled(isEnabled);

		return result;
	}
	
	public WorkAreaDTO withIsEnabled(Boolean isEnabled) {
		this.isEnabled = isEnabled;
		return this;
	}	
	
	public WorkAreaDTO withId(Long id) {
		this.id = id;
		return this;
	}
	
	public WorkAreaDTO withWorkAreaItemCodes(List<String> workAreaItemCodes) {
		this.workAreaItemCodes = workAreaItemCodes;
		return this;
	}		
	
	public WorkAreaDTO withCode(String code) {
		this.code = code;
		return this;
	}	
	
	public WorkAreaDTO withDescription(String description) {
		this.description = description;
		return this;
	}	

	public WorkAreaDTO() {
		super();
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

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public List<String> getWorkAreaItemCodes() {
		return workAreaItemCodes;
	}

	public void setWorkAreaItemCodes(List<String> workAreaItemCodes) {
		this.workAreaItemCodes = workAreaItemCodes;
	}

	public Boolean getIsEnabled() {
		return isEnabled;
	}

	public void setIsEnabled(Boolean isEnabled) {
		this.isEnabled = isEnabled;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("WorkAreaDTO [id=");
		builder.append(id);
		builder.append(", code=");
		builder.append(code);
		builder.append(", description=");
		builder.append(description);
		builder.append(", workAreaItemCodes=");
		builder.append(workAreaItemCodes);
		builder.append(", isEnabled=");
		builder.append(isEnabled);
		builder.append("]");
		return builder.toString();
	}
}
