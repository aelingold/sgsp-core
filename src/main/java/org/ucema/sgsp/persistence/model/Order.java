package org.ucema.sgsp.persistence.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.ucema.sgsp.security.model.User;

@Entity
@Table(name = "orders")
public class Order {

	@Id
	@GeneratedValue
	private Long id;
	@ManyToOne
	@JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "fk_order_user"))
	private User user;
	private String title;
	@ManyToOne
	@JoinColumn(name = "work_area_id", foreignKey = @ForeignKey(name = "fk_order_work_area"))
	private WorkArea workArea;
	@Column(name = "work_description")
	private String workDescription;
	@Column(name = "work_problem")
	private String workProblem;
	@Column(name = "work_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date workDate;
    @Enumerated(EnumType.STRING)
    @Column(name = "work_date_type", length = 20, nullable = false)	
	private WorkDateType workDateType;
	private String place;
	@Column(name = "pending_notify")
	private Boolean pendingNotify;
	@Column(name = "pending_quotes")
	private Boolean pendingQuotes;
	@Column(name = "created_at")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdAt;
	@Column(name = "updated_at")
	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedAt;
	@Column(name = "is_enabled")
	private Boolean isEnabled;

	public Order(Long id) {
		super();
		this.id = id;
	}

	public Order() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public WorkArea getWorkArea() {
		return workArea;
	}

	public void setWorkArea(WorkArea workArea) {
		this.workArea = workArea;
	}

	public String getWorkDescription() {
		return workDescription;
	}

	public void setWorkDescription(String workDescription) {
		this.workDescription = workDescription;
	}

	public String getWorkProblem() {
		return workProblem;
	}

	public void setWorkProblem(String workProblem) {
		this.workProblem = workProblem;
	}

	public Date getWorkDate() {
		return workDate;
	}

	public void setWorkDate(Date workDate) {
		this.workDate = workDate;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public Boolean getPendingNotify() {
		return pendingNotify;
	}

	public void setPendingNotify(Boolean pendingNotify) {
		this.pendingNotify = pendingNotify;
	}

	public Boolean getPendingQuotes() {
		return pendingQuotes;
	}

	public void setPendingQuotes(Boolean pendingQuotes) {
		this.pendingQuotes = pendingQuotes;
	}

	public WorkDateType getWorkDateType() {
		return workDateType;
	}

	public void setWorkDateType(WorkDateType workDateType) {
		this.workDateType = workDateType;
	}
}
