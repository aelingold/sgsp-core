package org.ucema.sgsp.api.dto;

import java.io.Serializable;
import java.util.List;

public class WorkAreaDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private String code;
	private String description;
	private List<Long> workAreaItemIds;
	private List<String> workAreaItemCodes;

	public static WorkAreaDTO newInstance() {
		return new WorkAreaDTO();
	}

	public WorkAreaDTO build() {
		WorkAreaDTO result = new WorkAreaDTO();

		result.setId(id);
		result.setDescription(description);
		result.setWorkAreaItemIds(workAreaItemIds);
		result.setCode(code);
		result.setWorkAreaItemCodes(workAreaItemCodes);

		return result;
	}
	
	public WorkAreaDTO withId(Long id) {
		this.id = id;
		return this;
	}
	
	public WorkAreaDTO withWorkAreaItemCodes(List<String> workAreaItemCodes) {
		this.workAreaItemCodes = workAreaItemCodes;
		return this;
	}	
	
	public WorkAreaDTO withWorkAreaItemIds(List<Long> workAreaItemIds) {
		this.workAreaItemIds = workAreaItemIds;
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

	public List<Long> getWorkAreaItemIds() {
		return workAreaItemIds;
	}

	public void setWorkAreaItemIds(List<Long> workAreaItemIds) {
		this.workAreaItemIds = workAreaItemIds;
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
}
