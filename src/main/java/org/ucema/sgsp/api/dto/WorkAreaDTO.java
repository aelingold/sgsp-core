package org.ucema.sgsp.api.dto;

import java.io.Serializable;
import java.util.List;

public class WorkAreaDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private String name;
	private String description;
	private List<WorkAreaItemDTO> workAreaItems;

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

	public List<WorkAreaItemDTO> getWorkAreaItems() {
		return workAreaItems;
	}

	public void setWorkAreaItems(List<WorkAreaItemDTO> workAreaItems) {
		this.workAreaItems = workAreaItems;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("WorkAreaDTO [id=");
		builder.append(id);
		builder.append(", name=");
		builder.append(name);
		builder.append(", description=");
		builder.append(description);
		builder.append(", workAreaItems=");
		builder.append(workAreaItems);
		builder.append("]");
		return builder.toString();
	}
}
