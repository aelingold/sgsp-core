package org.ucema.sgsp.api.dto;

import java.io.Serializable;

public class WorkAreaItemDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private String name;
	private String description;
	private Long workAreaId;

	public WorkAreaItemDTO(Long id, String name, String description,
			Long workAreaId) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.workAreaId = workAreaId;
	}

	public WorkAreaItemDTO() {
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

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("WorkAreaItemDTO [id=");
		builder.append(id);
		builder.append(", name=");
		builder.append(name);
		builder.append(", description=");
		builder.append(description);
		builder.append(", workAreaId=");
		builder.append(workAreaId);
		builder.append("]");
		return builder.toString();
	}
}
