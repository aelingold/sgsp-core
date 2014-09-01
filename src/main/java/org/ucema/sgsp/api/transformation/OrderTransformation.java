package org.ucema.sgsp.api.transformation;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.ucema.sgsp.api.dto.OrderDTO;
import org.ucema.sgsp.api.dto.PlaceOrderDTO;
import org.ucema.sgsp.persistence.model.Order;
import org.ucema.sgsp.persistence.model.OrderItem;
import org.ucema.sgsp.persistence.model.WorkArea;
import org.ucema.sgsp.persistence.model.WorkAreaItem;
import org.ucema.sgsp.persistence.model.WorkDateType;
import org.ucema.sgsp.security.model.User;
import org.ucema.sgsp.service.UserService;
import org.ucema.sgsp.service.WorkAreaItemService;
import org.ucema.sgsp.service.WorkAreaService;

@Component
public class OrderTransformation {

	@Autowired
	private UserTransformation userTransformation;
	@Autowired
	private WorkAreaTransformation workAreaTransformation;
	@Autowired
	private WorkAreaItemService workAreaItemService;
	@Autowired
	private WorkAreaService workAreaService;
	@Autowired
	private UserService userService;

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
		result.setLocation(order.getLocation());
		if (order.getUser() != null) {
			result.setUserId(order.getUser().getId());
		}
		if (order.getWorkArea() != null) {
			result.setWorkAreaId(order.getWorkArea().getId());
		}
		result.setWorkDate(order.getWorkDate());
		result.setWorkDescription(order.getWorkDescription());
		result.setWorkDateType(order.getWorkDateType().name());

		if (order.getOrderItems() != null) {
			result.setOrderItemIds(getOrderItemIds(order.getOrderItems()));
		}

		return result;
	}

	private List<Long> getOrderItemIds(List<OrderItem> orderItems) {
		List<Long> response = new ArrayList<Long>();

		for (OrderItem orderItem : orderItems) {
			response.add(orderItem.getId());
		}

		return response;
	}

	public Order transformToModel(OrderDTO order) {
		Order result = new Order();

		result.setId(order.getId());
		result.setPendingNotify(order.getPendingNotify());
		result.setPendingQuotes(order.getPendingQuotes());
		result.setLocation(order.getLocation());
		if (order.getUserId() != null) {
			result.setUser(new User(order.getUserId()));
		}
		if (order.getWorkAreaId() != null) {
			result.setWorkArea(new WorkArea(order.getWorkAreaId()));
		}
		result.setWorkDate(order.getWorkDate());
		result.setWorkDescription(order.getWorkDescription());

		if (order.getWorkDateType() != null) {
			result.setWorkDateType(WorkDateType.valueOf(order.getWorkDateType()));
		}

		if (order.getOrderItemIds() != null) {
			result.setOrderItems(getWorkAreaItems(order.getOrderItemIds()));
		}
		result.setCreatedAt(new Date());

		return result;
	}

	private List<OrderItem> getWorkAreaItems(List<Long> orderItemIds) {
		List<OrderItem> response = new ArrayList<OrderItem>();

		for (Long orderItemId : orderItemIds) {
			response.add(new OrderItem(orderItemId));
		}

		return response;
	}

	public Order transformToModel(PlaceOrderDTO order) {
		Order result = new Order();

		result.setPendingNotify(true);
		result.setPendingQuotes(true);
		result.setLocation(order.getLocation());

		if (order.getUsername() != null) {
			result.setUser(new User(userService
					.findByEmail(order.getUsername()).getId()));
		}

		if (order.getWorkAreaCode() != null) {
			result.setWorkArea(new WorkArea(workAreaService.findByCode(
					order.getWorkAreaCode()).getId()));
		}

		result.setWorkDescription(order.getWorkDescription());

		if (order.getWorkDateType() != null) {
			result.setWorkDateType(WorkDateType.valueOf(order.getWorkDateType()));
		}

		if (order.getWorkAreaItemCodes() != null) {
			result.setOrderItems(buildOrderItems(result,
					order.getWorkAreaItemCodes()));
		}
		result.setCreatedAt(new Date());

		return result;
	}

	private List<OrderItem> buildOrderItems(Order order,
			List<String> workAreaItemCodes) {

		List<OrderItem> result = new ArrayList<>();

		for (String workAreaItemCode : workAreaItemCodes) {
			if (workAreaItemCode != null) {
				result.add(buildOrderItem(order, workAreaItemCode));
			}
		}

		return result;
	}

	private OrderItem buildOrderItem(Order order, String workAreaItemCode) {

		OrderItem result = new OrderItem();

		result.setOrder(order);
		result.setWorkAreaItem(new WorkAreaItem(workAreaItemService.findByCode(
				workAreaItemCode).getId()));

		return result;
	}
}
