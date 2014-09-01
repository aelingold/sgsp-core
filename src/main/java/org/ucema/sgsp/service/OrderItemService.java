package org.ucema.sgsp.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.ucema.sgsp.api.dto.OrderItemDTO;
import org.ucema.sgsp.api.transformation.OrderItemTransformation;
import org.ucema.sgsp.persistence.OrderItemDAO;
import org.ucema.sgsp.persistence.model.OrderItem;

@Service
public class OrderItemService {

	@Autowired
	private OrderItemTransformation orderItemTransformation;
	
	@Autowired
	private OrderItemDAO orderItemDAO;

	@Transactional
	public List<OrderItemDTO> list() {
		return orderItemTransformation.transformToApi(orderItemDAO.findAll());
	}

	@Transactional
	public OrderItemDTO saveOrUpdate(OrderItemDTO orderItem) {
		OrderItem response = orderItemDAO.save(orderItemTransformation.transformToModel(orderItem));
		orderItem.setId(response.getId());
		return orderItem;
	}

	@Transactional
	public void delete(OrderItemDTO orderItem) {
		orderItemDAO.delete(orderItemTransformation.transformToModel(orderItem));
	}
	
	@Transactional
	public void delete(Long id) {
		OrderItem orderItem = orderItemDAO.getOne(id);
		if (orderItem == null){
			throw new RuntimeException("orderItem not found");
		}		
		orderItemDAO.delete(orderItem);
	}	
	
	@Transactional
	public OrderItemDTO get(Long id){
		OrderItem orderItem = orderItemDAO.getOne(id);
		if (orderItem == null){
			throw new RuntimeException("orderItem not found");
		}		
		return orderItemTransformation.transformToApi(orderItem);
	}	
}
