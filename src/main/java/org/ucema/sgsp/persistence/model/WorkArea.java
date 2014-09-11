package org.ucema.sgsp.persistence.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "work_areas", uniqueConstraints = @UniqueConstraint(name = "uq_work_area_code", columnNames = { "code" }))
public class WorkArea {

	@Id
	@GeneratedValue
	private Long id;
	private String code;
	private String description;
	@OneToMany(mappedBy = "workArea")
	private List<WorkAreaItem> workAreaItems;
	@OneToMany(mappedBy = "workArea")
	private List<WorkAreaQuestion> workAreaQuestions;	
	@Column(name = "created_at")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdAt;
	@Column(name = "updated_at")
	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedAt;
	@Column(name = "is_enabled")
	private Boolean isEnabled;

	public WorkArea() {
		super();
	}

	public WorkArea(Long id) {
		super();
		this.id = id;
	}
	
    @PrePersist
    public void prePersist() {
    	Date now = new Date();
        this.createdAt = now;
        this.updatedAt = now;
    }

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = new Date();
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

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public List<WorkAreaQuestion> getWorkAreaQuestions() {
		return workAreaQuestions;
	}

	public void setWorkAreaQuestions(List<WorkAreaQuestion> workAreaQuestions) {
		this.workAreaQuestions = workAreaQuestions;
	}
}
