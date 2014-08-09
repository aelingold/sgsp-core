package org.ucema.sgsp.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.ucema.sgsp.persistence.model.Payment;

public interface PaymentDAO extends JpaRepository<Payment, Long> {

}
