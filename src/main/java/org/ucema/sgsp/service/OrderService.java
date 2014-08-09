package org.ucema.sgsp.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.ucema.sgsp.api.dto.OrderDTO;
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
	public OrderDTO saveOrUpdate(OrderDTO order) {
		Order response = orderDAO.save(orderTransformation.transformToModel(order));
		order.setId(response.getId());
		return order;
	}

	@Transactional
	public void delete(OrderDTO order) {
		orderDAO.delete(orderTransformation.transformToModel(order));
	}
	
	@Transactional
	public void delete(Long id) {
		Order order = orderDAO.getOne(id);
		if (order == null){
			throw new RuntimeException("order not found");
		}		
		orderDAO.delete(order);
	}	
	
	@Transactional
	public OrderDTO get(Long id){
		Order order = orderDAO.getOne(id);
		if (order == null){
			throw new RuntimeException("order not found");
		}		
		return orderTransformation.transformToApi(order);
	}	
}
