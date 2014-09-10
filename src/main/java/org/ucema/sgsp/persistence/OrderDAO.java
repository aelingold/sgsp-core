package org.ucema.sgsp.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.ucema.sgsp.persistence.model.Order;

public interface OrderDAO extends JpaRepository<Order, Long> {
}
