package org.ucema.sgsp.api.transformation;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.ucema.sgsp.api.dto.OrderDTO;
import org.ucema.sgsp.api.dto.PlaceOrderDTO;
import org.ucema.sgsp.persistence.model.City;
import org.ucema.sgsp.persistence.model.Order;
import org.ucema.sgsp.persistence.model.OrderItem;
import org.ucema.sgsp.persistence.model.OrderStatusType;
import org.ucema.sgsp.persistence.model.QuoteStatusType;
import org.ucema.sgsp.persistence.model.State;
import org.ucema.sgsp.persistence.model.WorkArea;
import org.ucema.sgsp.persistence.model.WorkAreaItem;
import org.ucema.sgsp.persistence.model.WorkDateType;
import org.ucema.sgsp.security.model.User;
import org.ucema.sgsp.service.CityService;
import org.ucema.sgsp.service.StateService;
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
	@Autowired
	private StateService stateService;
	@Autowired
	private CityService cityService;
	@Autowired
	private QuoteTransformation quoteTransformation;

	public List<OrderDTO> transformToApi(List<Order> orders) {
		List<OrderDTO> result = new ArrayList<OrderDTO>();

		if (orders != null) {
			for (Order order : orders) {
				result.add(transformToApi(order));
			}
		}

		return result;
	}

	public OrderDTO transformToApi(Order order) {
		OrderDTO result = new OrderDTO();

		result.setId(order.getId());
		result.setPendingNotify(order.getPendingNotify());

		if (order.getUser() != null) {
			result.setUsername(order.getUser().getEmail());
			result.setFirstName(order.getUser().getFirstName());
			result.setLastName(order.getUser().getLastName());
		}
		if (order.getWorkArea() != null) {
			result.setWorkAreaCode(order.getWorkArea().getCode());
			result.setWorkAreaDescription(order.getWorkArea().getDescription());
		}
		result.setWorkDate(order.getWorkDate());
		result.setWorkDescription(order.getWorkDescription());
		result.setWorkDateType(order.getWorkDateType().name());

		if (order.getOrderItems() != null) {
			result.setOrderItemIds(getOrderItemIds(order.getOrderItems()));
			result.setWorkAreaItemCodes(order.getOrderItems().stream()
					.map(o -> o.getWorkAreaItem().getCode())
					.collect(Collectors.toList()));
		}

		if (order.getSquareMeters() != null) {
			result.setSquareMeters(order.getSquareMeters().toString());
		}

		result.setAirConditionerPower(order.getAirConditionerPower());

		if (order.getState() != null) {
			result.setStateCode(order.getState().getCode());
			result.setStateDescription(order.getState().getDescription());
		}

		if (order.getCity() != null) {
			result.setCityCode(order.getCity().getCode());
			result.setCityDescription(order.getCity().getDescription());
		}

		if (order.getQuotes() != null) {
			result.setQuoteIds(order.getQuotes().stream().map(q -> q.getId())
					.collect(Collectors.toList()));
			result.setQuoteAccepted(order
					.getQuotes()
					.stream()
					.filter(q -> q.getStatusType().equals(
							QuoteStatusType.ACCEPTED)).count() > 0);
			result.setQuoteReplied(order
					.getQuotes()
					.stream()
					.filter(q -> q.getStatusType().equals(
							QuoteStatusType.ACCEPTED)
							|| q.getStatusType().equals(
									QuoteStatusType.REPLIED)
							|| q.getStatusType().equals(
									QuoteStatusType.DONE)).count() > 0);
		}

		result.setStatusType(order.getStatusType().name());

		return result;
	}

	private List<Long> getOrderItemIds(List<OrderItem> orderItems) {
		List<Long> response = new ArrayList<Long>();

		for (OrderItem orderItem : orderItems) {
			response.add(orderItem.getId());
		}

		return response;
	}

	public Order transformToModel(PlaceOrderDTO order) {
		Order result = new Order();

		result.setPendingNotify(true);

		if (order.getUsername() != null) {
			result.setUser(new User(userService
					.findByEmail(order.getUsername()).getId()));
		}

		if (order.getWorkAreaCode() != null) {
			result.setWorkArea(new WorkArea(workAreaService.findByCode(
					order.getWorkAreaCode()).getId()));
		}

		result.setStatusType(OrderStatusType.IN_PROGRESS);
		result.setWorkDescription(order.getWorkDescription());

		if (order.getWorkDateType() != null) {
			result.setWorkDateType(WorkDateType.valueOf(order.getWorkDateType()));
		}

		if (order.getWorkAreaItemCodes() != null) {
			result.setOrderItems(buildOrderItems(result,
					order.getWorkAreaItemCodes()));
		}

		result.setAirConditionerPower(order.getAirConditionerPower());

		if (order.getSquareMeters() != null && !order.getSquareMeters().isEmpty()) {
			result.setSquareMeters(new BigDecimal(order.getSquareMeters()));
		}

		if (order.getStateCode() != null && !order.getStateCode().isEmpty()) {
			result.setState(new State(stateService.findByCode(
					order.getStateCode()).getId()));
		}

		if (order.getCityCode() != null && !order.getCityCode().isEmpty()) {
			result.setCity(new City(cityService.findByCode(order.getCityCode())
					.getId()));
		}

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

	public Order updateFields(Order order, OrderDTO orderDTO) {

		order.setPendingNotify(orderDTO.getPendingNotify());

		return order;
	}
}
