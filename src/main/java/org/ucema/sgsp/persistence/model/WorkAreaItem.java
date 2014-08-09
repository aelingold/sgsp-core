package org.ucema.sgsp.persistence.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "work_area_items")
public class WorkAreaItem {

	@Id
	@GeneratedValue
	private Long id;
	private String name;
	private String description;
	@ManyToOne
	@JoinColumn(name = "work_area_id", foreignKey = @ForeignKey(name = "fk_work_area_items_work_area"))
	private WorkArea workArea;	
    @Column(name="created_at")
    @Temporal(TemporalType.TIMESTAMP)
	private Date createdAt;
    @Column(name="updated_at")
    @Temporal(TemporalType.TIMESTAMP)
	private Date updatedAt;
    @Column(name="is_enabled")
	private Boolean isEnabled;

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

	public WorkArea getWorkArea() {
		return workArea;
	}

	public void setWorkArea(WorkArea workArea) {
		this.workArea = workArea;
	}
}
