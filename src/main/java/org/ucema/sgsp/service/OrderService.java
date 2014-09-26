package org.ucema.sgsp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.ucema.sgsp.api.dto.OrderDTO;
import org.ucema.sgsp.api.dto.PlaceOrderDTO;
import org.ucema.sgsp.api.transformation.OrderTransformation;
import org.ucema.sgsp.persistence.OrderDAO;
import org.ucema.sgsp.persistence.model.Order;
import org.ucema.sgsp.persistence.model.OrderStatusType;

@Service
public class OrderService {

	@Autowired
	private OrderTransformation orderTransformation;

	@Autowired
	private OrderDAO orderDAO;

	@Transactional
	public List<OrderDTO> list(Boolean pendingNotify) {
		return orderTransformation.transformToApi(orderDAO
				.findByPendingNotify(pendingNotify));
	}

	@Transactional
	public List<OrderDTO> list(Sort sort) {
		return orderTransformation.transformToApi(orderDAO.findAll(sort));
	}

	@Transactional
	public List<OrderDTO> list() {
		return list(new Sort(Sort.Direction.DESC, "createdAt"));
	}

	@Transactional
	public List<OrderDTO> list(Long userId) {

		List<Order> orders = orderDAO.findAll(new Sort(Sort.Direction.DESC,
				"createdAt"));
		List<Order> filteredOrders = orders.stream()
				.filter(o -> o.getUser().getId().equals(userId))
				.collect(Collectors.toList());

		return orderTransformation.transformToApi(filteredOrders);
	}

	@Transactional
	public List<OrderDTO> list(String username) {

		List<Order> orders = orderDAO.findAll(new Sort(Sort.Direction.DESC,
				"createdAt"));
		List<Order> filteredOrders = orders.stream()
				.filter(o -> o.getUser().getEmail().equals(username))
				.collect(Collectors.toList());

		return orderTransformation.transformToApi(filteredOrders);
	}

	@Transactional
	public List<OrderDTO> update(List<OrderDTO> ordersDTO) {

		List<OrderDTO> response = new ArrayList<OrderDTO>();

		ordersDTO.forEach(o -> {
			response.add(update(o));
		});

		return response;
	}

	@Transactional
	public OrderDTO updateStatus(Long orderId, OrderStatusType statusType) {

		Order order = orderDAO.getOne(orderId);
		if (order == null) {
			throw new RuntimeException("order not found");
		}

		order.setStatusType(statusType);

		Order response = orderDAO.save(order);
		return orderTransformation.transformToApi(response);
	}

	@Transactional
	public OrderDTO update(OrderDTO orderDTO) {

		Order order = orderDAO.getOne(orderDTO.getId());
		if (order == null) {
			throw new RuntimeException("order not found");
		}

		Order response = orderDAO.save(orderTransformation.updateFields(order,
				orderDTO));
		return orderTransformation.transformToApi(response);
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
