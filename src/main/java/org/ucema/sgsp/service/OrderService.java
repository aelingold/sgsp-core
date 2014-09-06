package org.ucema.sgsp.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.ucema.sgsp.api.dto.OrderDTO;
import org.ucema.sgsp.api.dto.PlaceOrderDTO;
import org.ucema.sgsp.api.transformation.OrderTransformation;
import org.ucema.sgsp.persistence.OrderDAO;
import org.ucema.sgsp.persistence.model.Order;

@Service
public class OrderService {

	@Autowired
	private OrderTransformation orderTransformation;

	@Autowired
	private OrderDAO orderDAO;

	@Transactional
	public List<OrderDTO> list() {
		return orderTransformation.transformToApi(orderDAO.findAll());
	}

	@Transactional
	public List<OrderDTO> list(Long userId) {

		List<Order> orders = orderDAO.findAll();
		List<Order> filteredOrders = orders.stream()
				.filter(o -> o.getUser().getId().equals(userId))
				.collect(Collectors.toList());

		return orderTransformation.transformToApi(filteredOrders);
	}

	@Transactional
	public OrderDTO saveOrUpdate(PlaceOrderDTO order) {
		Order response = orderDAO.save(orderTransformation
				.transformToModel(order));
		return orderTransformation.transformToApi(response);
	}

	@Transactional
	public void delete(Long id) {
		if (id == null) {
			throw new RuntimeException("id can not be null");
		}
		orderDAO.delete(id);
	}

	@Transactional
	public OrderDTO get(Long id) {
		Order order = orderDAO.getOne(id);
		if (order == null) {
			throw new RuntimeException("order not found");
		}
		return orderTransformation.transformToApi(order);
	}
}
