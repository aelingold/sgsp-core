package org.ucema.sgsp.persistence.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import org.ucema.sgsp.security.model.User;

@Entity
@Table(name = "user_work_rate_summarizes", uniqueConstraints = @UniqueConstraint(name = "uq_user_work_rate_summarizes_id", columnNames = { "user_id" }))
public class UserWorkRateSummarize {

	@Id
	@GeneratedValue
	private Long id;
	@ManyToOne
	@JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "fk_user_work_rate_user"))
	private User user;
	private Long positiveQuantity;
	private Long negativeQuantity;
	private Long neutralQuantity;
	@Column(name = "created_at")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdAt;
	@Column(name = "updated_at")
	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedAt;

	public UserWorkRateSummarize() {
		super();
	}

	public UserWorkRateSummarize(Long id) {
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
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

	public Long getPositiveQuantity() {
		return positiveQuantity;
	}

	public void setPositiveQuantity(Long positiveQuantity) {
		this.positiveQuantity = positiveQuantity;
	}

	public Long getNeutralQuantity() {
		return neutralQuantity;
	}

	public void setNeutralQuantity(Long neutralQuantity) {
		this.neutralQuantity = neutralQuantity;
	}

	public Long getNegativeQuantity() {
		return negativeQuantity;
	}

	public void setNegativeQuantity(Long negativeQuantity) {
		this.negativeQuantity = negativeQuantity;
	}
}
