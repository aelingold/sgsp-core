package org.ucema.sgsp.api.transformation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.ucema.sgsp.api.dto.OrderDTO;
import org.ucema.sgsp.persistence.model.Order;
import org.ucema.sgsp.persistence.model.WorkArea;
import org.ucema.sgsp.persistence.model.WorkDateType;
import org.ucema.sgsp.security.model.User;

@Component
public class OrderTransformation {

	@Autowired
	private UserTransformation userTransformation;
	@Autowired
	private WorkAreaTransformation workAreaTransformation;

	public List<OrderDTO> transformToApi(List<Order> orders) {
		List<OrderDTO> result = new ArrayList<OrderDTO>();

		for (Order order : orders) {
			result.add(transformToApi(order));
		}

		return result;
	}

	public List<Order> transformToModel(List<OrderDTO> orders) {
		List<Order> result = new ArrayList<Order>();

		for (OrderDTO order : orders) {
			result.add(transformToModel(order));
		}

		return result;
	}

	public OrderDTO transformToApi(Order order) {
		OrderDTO result = new OrderDTO();

		result.setId(order.getId());
		result.setPendingNotify(order.getPendingNotify());
		result.setPendingQuotes(order.getPendingQuotes());
		result.setPlace(order.getPlace());
		result.setTitle(order.getTitle());
		if (order.getUser() != null) {
			result.setUserId(order.getUser().getId());
		}
		if (order.getWorkArea() != null) {
			result.setWorkAreaId(order.getWorkArea().getId());
		}
		result.setWorkDate(order.getWorkDate());
		result.setWorkDescription(order.getWorkDescription());
		result.setWorkProblem(order.getWorkProblem());
		result.setWorkDateType(order.getWorkDateType().name());

		return result;
	}

	public Order transformToModel(OrderDTO order) {
		Order result = new Order();

		result.setId(order.getId());
		result.setPendingNotify(order.getPendingNotify());
		result.setPendingQuotes(order.getPendingQuotes());
		result.setPlace(order.getPlace());
		result.setTitle(order.getTitle());
		if (order.getUserId() != null) {
			result.setUser(new User(order.getUserId()));
		}
		if (order.getWorkAreaId() != null) {
			result.setWorkArea(new WorkArea(order.getWorkAreaId()));
		}
		result.setWorkDate(order.getWorkDate());
		result.setWorkDescription(order.getWorkDescription());
		result.setWorkProblem(order.getWorkProblem());
		result.setWorkDateType(WorkDateType.valueOf(order.getWorkDateType()));

		return result;
	}
}
