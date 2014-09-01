package org.ucema.sgsp.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.ucema.sgsp.persistence.model.OrderItem;

public interface OrderItemDAO extends JpaRepository<OrderItem, Long> {

}
