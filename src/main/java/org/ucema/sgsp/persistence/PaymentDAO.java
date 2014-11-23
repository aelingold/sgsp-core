package org.ucema.sgsp.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.ucema.sgsp.persistence.model.Payment;
import org.ucema.sgsp.persistence.model.PaymentStatusType;

public interface PaymentDAO extends JpaRepository<Payment, Long> {

	List<Payment> findByQuote_User_Email(String username);
	
	List<Payment> findByUser_Email(String username);
	
	List<Payment> findByUser_EmailOrderByCreatedAtDesc(String username);
	
	List<Payment> findByStatusType(PaymentStatusType paymentStatusType);
}
