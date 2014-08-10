package org.ucema.sgsp.api.dto;

import java.io.Serializable;
import java.util.List;

public class WorkAreaDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private String name;
	private String description;
	private List<Long> workAreaItemIds;

	public WorkAreaDTO(Long id, String name, String description,
			List<Long> workAreaItemIds) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.workAreaItemIds = workAreaItemIds;
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

	public List<Long> getWorkAreaItemIds() {
		return workAreaItemIds;
	}

	public void setWorkAreaItemIds(List<Long> workAreaItemIds) {
		this.workAreaItemIds = workAreaItemIds;
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
		builder.append(", workAreaItemIds=");
		builder.append(workAreaItemIds);
		builder.append("]");
		return builder.toString();
	}
}
