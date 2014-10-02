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
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "quote_question_replies")
public class QuoteQuestionReply {

	@Id
	@GeneratedValue
	private Long id;
	@ManyToOne
	@JoinColumn(name = "quote_question_id", foreignKey = @ForeignKey(name = "fk_quote_questions_quote_questions_replies"))
	private QuoteQuestion quoteQuestion;
	private String description;
	@Column(name = "created_at")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdAt;
    @Enumerated(EnumType.STRING)
    @Column(name = "status_type")
	private QuoteQuestionReplyStatusType statusType;
	@Column(name = "updated_at")
	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedAt;	    

	public QuoteQuestionReply(Long id) {
		super();
		this.id = id;
	}

	public QuoteQuestionReply() {
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

	public QuoteQuestion getQuoteQuestion() {
		return quoteQuestion;
	}

	public void setQuoteQuestion(QuoteQuestion quoteQuestion) {
		this.quoteQuestion = quoteQuestion;
	}

	public QuoteQuestionReplyStatusType getStatusType() {
		return statusType;
	}

	public void setStatusType(QuoteQuestionReplyStatusType statusType) {
		this.statusType = statusType;
	}
}
