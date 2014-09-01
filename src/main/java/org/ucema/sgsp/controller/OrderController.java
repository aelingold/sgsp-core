package org.ucema.sgsp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.ucema.sgsp.api.dto.OrderDTO;
import org.ucema.sgsp.api.dto.PlaceOrderDTO;
import org.ucema.sgsp.service.OrderService;

@Controller
public class OrderController {

	@Autowired
	private OrderService orderService;

	@RequestMapping(value = "/orders/{id}", method = RequestMethod.GET)
	public @ResponseBody OrderDTO get(@PathVariable Long id) {
		return orderService.get(id);
	}
	
	@RequestMapping(value = "/orders", method = RequestMethod.GET)
	public @ResponseBody List<OrderDTO> list() {
		return orderService.list();
	}

//	@RequestMapping(value = "/orders", method = RequestMethod.POST)
//	public @ResponseBody void saveOrUpdate(@RequestBody OrderDTO order) {
//		orderService.saveOrUpdate(order);
//	}
	
	@RequestMapping(value = "/orders", method = RequestMethod.POST)
	public @ResponseBody void saveOrUpdate(@RequestBody PlaceOrderDTO order) {
		orderService.saveOrUpdate(order);
	}	

	@RequestMapping(value = "/orders/{id}", method = RequestMethod.DELETE)
	public @ResponseBody void delete(@PathVariable Long id) {
		orderService.delete(id);
	}
}
