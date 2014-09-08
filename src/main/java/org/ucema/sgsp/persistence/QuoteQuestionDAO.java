package org.ucema.sgsp.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.ucema.sgsp.persistence.model.QuoteQuestion;

public interface QuoteQuestionDAO extends JpaRepository<QuoteQuestion, Long> {

}
