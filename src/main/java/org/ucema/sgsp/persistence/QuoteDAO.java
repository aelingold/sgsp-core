package org.ucema.sgsp.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.ucema.sgsp.persistence.model.Quote;

public interface QuoteDAO extends JpaRepository<Quote, Long> {

}
