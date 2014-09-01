package org.ucema.sgsp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.ucema.sgsp.api.dto.OrderItemDTO;
import org.ucema.sgsp.service.OrderItemService;

@Controller
public class OrderItemController {

	@Autowired
	private OrderItemService orderItemService;

	@RequestMapping(value = "/orderItems/{id}", method = RequestMethod.GET)
	public @ResponseBody OrderItemDTO get(@PathVariable Long id) {
		return orderItemService.get(id);
	}
	
	@RequestMapping(value = "/orderItems", method = RequestMethod.GET)
	public @ResponseBody List<OrderItemDTO> list() {
		return orderItemService.list();
	}

	@RequestMapping(value = "/orderItems", method = RequestMethod.POST)
	public @ResponseBody void saveOrUpdate(@RequestBody OrderItemDTO orderItem) {
		orderItemService.saveOrUpdate(orderItem);
	}

	@RequestMapping(value = "/orderItems/{id}", method = RequestMethod.DELETE)
	public @ResponseBody void delete(@PathVariable Long id) {
		orderItemService.delete(id);
	}
}
