package org.ucema.sgsp.api.transformation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.ucema.sgsp.api.dto.OrderItemDTO;
import org.ucema.sgsp.persistence.model.Order;
import org.ucema.sgsp.persistence.model.OrderItem;
import org.ucema.sgsp.persistence.model.WorkAreaItem;

@Component
public class OrderItemTransformation {

	public List<OrderItemDTO> transformToApi(List<OrderItem> orderItems) {
		List<OrderItemDTO> result = new ArrayList<OrderItemDTO>();

		for (OrderItem orderItem : orderItems) {
			result.add(transformToApi(orderItem));
		}

		return result;
	}

	public List<OrderItem> transformToModel(List<OrderItemDTO> orderItems) {
		List<OrderItem> result = new ArrayList<OrderItem>();

		for (OrderItemDTO orderItem : orderItems) {
			result.add(transformToModel(orderItem));
		}

		return result;
	}

	public OrderItemDTO transformToApi(OrderItem orderItem) {
		OrderItemDTO result = new OrderItemDTO();

		result.setId(orderItem.getId());
		result.setOrderId(orderItem.getOrder().getId());
		result.setValue(orderItem.getValue());
		result.setWorkAreaItemId(orderItem.getWorkAreaItem().getId());

		return result;
	}	

	public OrderItem transformToModel(OrderItemDTO orderItem) {
		OrderItem result = new OrderItem();

		result.setId(orderItem.getId());
		result.setOrder(new Order(orderItem.getOrderId()));
		result.setValue(orderItem.getValue());
		result.setWorkAreaItem(new WorkAreaItem(orderItem.getWorkAreaItemId()));

		return result;
	}	
}
