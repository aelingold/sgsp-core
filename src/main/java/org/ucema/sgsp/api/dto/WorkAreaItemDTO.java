package org.ucema.sgsp.api.dto;

import java.io.Serializable;

public class WorkAreaItemDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private String groupType;
	private String description;
	private Long workAreaId;

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
		result.setWorkAreaId(workAreaId);

		return result;
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
	
	public WorkAreaItemDTO withWorkAreaId(Long workAreaId) {
		this.workAreaId = workAreaId;
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

	public Long getWorkAreaId() {
		return workAreaId;
	}

	public void setWorkAreaId(Long workAreaId) {
		this.workAreaId = workAreaId;
	}

	public String getGroupType() {
		return groupType;
	}

	public void setGroupType(String groupType) {
		this.groupType = groupType;
	}
}
