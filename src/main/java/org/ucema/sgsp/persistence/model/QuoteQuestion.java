package org.ucema.sgsp.persistence.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Cascade;

@Entity
@Table(name = "quote_questions")
public class QuoteQuestion {

	@Id
	@GeneratedValue
	private Long id;
	@ManyToOne
	@JoinColumn(name = "quote_id", foreignKey = @ForeignKey(name = "fk_quote_questions_quote"))
	private Quote quote;
	private String description;
	@Column(name = "created_at")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdAt;
	@OneToMany(mappedBy = "quoteQuestion",orphanRemoval=true)
	@Cascade(org.hibernate.annotations.CascadeType.ALL)
	private List<QuoteQuestionReply> quoteQuestionReplies;		

	public QuoteQuestion(Long id) {
		super();
		this.id = id;
	}

	public QuoteQuestion() {
		super();
	}
	
    @PrePersist
    public void prePersist() {
    	Date now = new Date();
        this.createdAt = now;
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Quote getQuote() {
		return quote;
	}

	public void setQuote(Quote quote) {
		this.quote = quote;
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

	public List<QuoteQuestionReply> getQuoteQuestionReplies() {
		return quoteQuestionReplies;
	}

	public void setQuoteQuestionReplies(
			List<QuoteQuestionReply> quoteQuestionReplies) {
		this.quoteQuestionReplies = quoteQuestionReplies;
	}
}
