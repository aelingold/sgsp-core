package org.ucema.sgsp.persistence.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Cascade;
import org.ucema.sgsp.security.model.User;

@Entity
@Table(name = "quotes")
public class Quote {

	@Id
	@GeneratedValue
	private Long id;
	@ManyToOne
	@JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "fk_quote_user"))
	private User user;
	@ManyToOne
	@JoinColumn(name = "order_id", foreignKey = @ForeignKey(name = "fk_quote_order"))
	private Order order;	
	private String description;
	@Embedded
	private Amount amount;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "valid_date_until")
	private Date validDateUntil;
	@OneToMany(mappedBy = "quote",orphanRemoval=true)
	@Cascade(org.hibernate.annotations.CascadeType.ALL)
	private List<QuoteQuestion> quoteQuestions;	
	@Column(name = "created_at")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdAt;

	public Quote(Long id) {
		super();
		this.id = id;
	}

	public Quote() {
		super();
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

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Amount getAmount() {
		return amount;
	}

	public void setAmount(Amount amount) {
		this.amount = amount;
	}

	public Date getValidDateUntil() {
		return validDateUntil;
	}

	public void setValidDateUntil(Date validDateUntil) {
		this.validDateUntil = validDateUntil;
	}

	public List<QuoteQuestion> getQuoteQuestions() {
		return quoteQuestions;
	}

	public void setQuoteQuestions(List<QuoteQuestion> quoteQuestions) {
		this.quoteQuestions = quoteQuestions;
	}
}
