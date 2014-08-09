package org.ucema.sgsp.persistence.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "work_areas")
public class WorkArea {

	@Id
	@GeneratedValue
	private Long id;
	private String name;
	private String description;
	@OneToMany(mappedBy="workArea")
	private List<WorkAreaItem> workAreaItems; 
    @Column(name="created_at")
    @Temporal(TemporalType.TIMESTAMP)
	private Date createdAt;
    @Column(name="updated_at")
    @Temporal(TemporalType.TIMESTAMP)
	private Date updatedAt;
    @Column(name="is_enabled")
	private Boolean isEnabled;

	public WorkArea() {
		super();
	}

	public WorkArea(Long id) {
		super();
		this.id = id;
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

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public Boolean getIsEnabled() {
		return isEnabled;
	}

	public void setIsEnabled(Boolean isEnabled) {
		this.isEnabled = isEnabled;
	}

	public List<WorkAreaItem> getWorkAreaItems() {
		return workAreaItems;
	}

	public void setWorkAreaItems(List<WorkAreaItem> workAreaItems) {
		this.workAreaItems = workAreaItems;
	}
}
