package org.ucema.sgsp.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.ucema.sgsp.persistence.model.Quote;
import org.ucema.sgsp.persistence.model.QuoteStatusType;

public interface QuoteDAO extends JpaRepository<Quote, Long> {

	List<Quote> findByStatusTypeAndUser_Email(QuoteStatusType statusType,
			String username);

	List<Quote> findByUser_Email(String username);
	
	List<Quote> findByOrder_Id(Long orderId);
	
	List<Quote> findByOrder_Id(List<Long> orderIds);
	
	List<Quote> findByStatusType(QuoteStatusType statusType);
}
