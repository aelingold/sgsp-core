package org.ucema.sgsp.persistence.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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

import org.ucema.sgsp.security.model.User;

@Entity
@Table(name = "payments")
public class Payment {

	@Id
	@GeneratedValue
	private Long id;
	@ManyToOne
	@JoinColumn(name = "quote_id", foreignKey = @ForeignKey(name = "fk_payment_quote"))
	private Quote quote;
    @Enumerated(EnumType.STRING)
    @Column(name = "payment_type")	
	private PaymentType paymentType;	
    @Column(name="created_at")
    @Temporal(TemporalType.TIMESTAMP)
	private Date createdAt;
	@Column(name = "updated_at")
	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedAt;	    
    @Enumerated(EnumType.STRING)
    @Column(name = "status_type")
	private PaymentStatusType statusType;
	@Embedded
	private Amount amount;
	@ManyToOne
	@JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "fk_payment_user"))
	private User user;
	@Column(name = "payment_date_allowed_before")
	@Temporal(TemporalType.TIMESTAMP)
	private Date paymentDateAllowedBefore;	    	

	public Payment(Long id) {
		super();
		this.id = id;
	}

	public Payment() {
		super();
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

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
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

	public PaymentStatusType getStatusType() {
		return statusType;
	}

	public void setStatusType(PaymentStatusType statusType) {
		this.statusType = statusType;
	}

	public PaymentType getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(PaymentType paymentType) {
		this.paymentType = paymentType;
	}

	public Amount getAmount() {
		return amount;
	}

	public void setAmount(Amount amount) {
		this.amount = amount;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Date getPaymentDateAllowedBefore() {
		return paymentDateAllowedBefore;
	}

	public void setPaymentDateAllowedBefore(Date paymentDateAllowedBefore) {
		this.paymentDateAllowedBefore = paymentDateAllowedBefore;
	}

	@Override
	public String toString() {
		return "Payment [id=" + id + ", quote=" + quote + ", paymentType="
				+ paymentType + ", createdAt=" + createdAt + ", updatedAt="
				+ updatedAt + ", statusType=" + statusType + ", amount="
				+ amount + ", user=" + user + ", paymentDateAllowedBefore="
				+ paymentDateAllowedBefore + "]";
	}
}
