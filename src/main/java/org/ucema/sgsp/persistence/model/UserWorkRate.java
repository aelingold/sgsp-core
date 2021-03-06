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
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.ucema.sgsp.security.model.User;

@Entity
@Table(name = "user_work_rates")
public class UserWorkRate {

	@Id
	@GeneratedValue
	private Long id;
	@ManyToOne
	@JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "fk_user_work_rates_user"))
	private User user;
	private String comment;
    @Enumerated(EnumType.STRING)
    @Column(name = "rating_type")	
	private UserWorkRateRatingType ratingType;
	@Column(name = "created_at")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdAt;
	@OneToOne
	@JoinColumn(name = "quote_id", foreignKey = @ForeignKey(name = "fk_user_work_rates_quote"))
	private Quote quote;
	@Column(name = "updated_at")
	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedAt;
    @Enumerated(EnumType.STRING)
    @Column(name = "status_type")
	private UserWorkRateStatusType statusType;
    @Column(name = "work_completed")
    private Boolean workCompleted;
    private Boolean summarized;    

	public UserWorkRate() {
		super();
	}

	public UserWorkRate(Long id) {
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

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Quote getQuote() {
		return quote;
	}

	public void setQuote(Quote quote) {
		this.quote = quote;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public UserWorkRateStatusType getStatusType() {
		return statusType;
	}

	public void setStatusType(UserWorkRateStatusType statusType) {
		this.statusType = statusType;
	}

	public Boolean getWorkCompleted() {
		return workCompleted;
	}

	public void setWorkCompleted(Boolean workCompleted) {
		this.workCompleted = workCompleted;
	}

	public UserWorkRateRatingType getRatingType() {
		return ratingType;
	}

	public void setRatingType(UserWorkRateRatingType ratingType) {
		this.ratingType = ratingType;
	}

	public Boolean getSummarized() {
		return summarized;
	}

	public void setSummarized(Boolean summarized) {
		this.summarized = summarized;
	}
}
