package org.ucema.sgsp.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.ucema.sgsp.persistence.model.QuoteQuestionReply;

public interface QuoteQuestionReplyDAO extends JpaRepository<QuoteQuestionReply, Long> {

}
