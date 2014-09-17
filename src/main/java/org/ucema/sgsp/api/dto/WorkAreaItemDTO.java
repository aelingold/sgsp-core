package org.ucema.sgsp.api.dto;

import java.io.Serializable;

public class WorkAreaItemDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private String code;
	private String groupType;
	private String description;
	private String workAreaCode;

	public WorkAreaItemDTO() {
		super();
	}
	
	public static WorkAreaItemDTO newInstance() {
		return new WorkAreaItemDTO();
	}

	public WorkAreaItemDTO build() {
		WorkAreaItemDTO result = new WorkAreaItemDTO();

		result.setId(id);
		result.setGroupType(groupType);
		result.setDescription(description);
		result.setCode(code);
		result.setWorkAreaCode(workAreaCode);

		return result;
	}
	
	public WorkAreaItemDTO withWorkAreaCode(String workAreaCode) {
		this.workAreaCode = workAreaCode;
		return this;
	}	
	
	public WorkAreaItemDTO withCode(String code) {
		this.code = code;
		return this;
	}	
	
	public WorkAreaItemDTO withDescription(String description) {
		this.description = description;
		return this;
	}	
	
	public WorkAreaItemDTO withGroupType(String groupType) {
		this.groupType = groupType;
		return this;
	}	

	public WorkAreaItemDTO withId(Long id) {
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

	public String getGroupType() {
		return groupType;
	}

	public void setGroupType(String groupType) {
		this.groupType = groupType;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getWorkAreaCode() {
		return workAreaCode;
	}

	public void setWorkAreaCode(String workAreaCode) {
		this.workAreaCode = workAreaCode;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("WorkAreaItemDTO [id=");
		builder.append(id);
		builder.append(", code=");
		builder.append(code);
		builder.append(", groupType=");
		builder.append(groupType);
		builder.append(", description=");
		builder.append(description);
		builder.append(", workAreaCode=");
		builder.append(workAreaCode);
		builder.append("]");
		return builder.toString();
	}
}
