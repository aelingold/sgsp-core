package org.ucema.sgsp.api.dto;

import java.io.Serializable;

public class WorkAreaQuestionDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private String groupType;
	private String description;
	private String workAreaCode;

	public WorkAreaQuestionDTO() {
		super();
	}
	
	public static WorkAreaQuestionDTO newInstance() {
		return new WorkAreaQuestionDTO();
	}

	public WorkAreaQuestionDTO build() {
		WorkAreaQuestionDTO result = new WorkAreaQuestionDTO();

		result.setId(id);
		result.setGroupType(groupType);
		result.setDescription(description);
		result.setWorkAreaCode(workAreaCode);

		return result;
	}
	
	public WorkAreaQuestionDTO withWorkAreaCode(String workAreaCode) {
		this.workAreaCode = workAreaCode;
		return this;
	}		
	
	public WorkAreaQuestionDTO withDescription(String description) {
		this.description = description;
		return this;
	}	
	
	public WorkAreaQuestionDTO withGroupType(String groupType) {
		this.groupType = groupType;
		return this;
	}	

	public WorkAreaQuestionDTO withId(Long id) {
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

	public String getWorkAreaCode() {
		return workAreaCode;
	}

	public void setWorkAreaCode(String workAreaCode) {
		this.workAreaCode = workAreaCode;
	}
}
