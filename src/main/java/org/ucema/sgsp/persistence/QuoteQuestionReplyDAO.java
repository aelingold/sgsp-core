package org.ucema.sgsp.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.ucema.sgsp.persistence.model.QuoteQuestionReply;
import org.ucema.sgsp.persistence.model.QuoteQuestionReplyStatusType;

public interface QuoteQuestionReplyDAO extends
		JpaRepository<QuoteQuestionReply, Long> {

	List<QuoteQuestionReply> findByStatusTypeAndQuoteQuestion_Quote_User_Email(
			QuoteQuestionReplyStatusType statusType, String username);

	List<QuoteQuestionReply> findByQuoteQuestion_Quote_User_Email(
			String username);
}
