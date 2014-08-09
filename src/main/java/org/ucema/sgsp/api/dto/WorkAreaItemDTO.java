package org.ucema.sgsp.api.dto;

import java.io.Serializable;

public class WorkAreaItemDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private String name;
	private String description;
	private WorkAreaDTO workArea;

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

	public WorkAreaDTO getWorkArea() {
		return workArea;
	}

	public void setWorkArea(WorkAreaDTO workArea) {
		this.workArea = workArea;
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
		builder.append(", workArea=");
		builder.append(workArea);
		builder.append("]");
		return builder.toString();
	}
}
