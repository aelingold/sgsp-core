package org.ucema.sgsp.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.ucema.sgsp.api.dto.OrderDTO;
import org.ucema.sgsp.api.dto.PlaceOrderDTO;
import org.ucema.sgsp.security.model.CustomUserDetails;
import org.ucema.sgsp.service.DashBoardDataService;
import org.ucema.sgsp.service.OrderService;

@Controller
public class OrderController {

	@Autowired
	private OrderService orderService;
	@Autowired
	private DashBoardDataService dashBoardDataService;

	@RequestMapping(value = "/orders/{id}", method = RequestMethod.GET)
	public @ResponseBody OrderDTO get(@PathVariable Long id) {
		return orderService.get(id);
	}

	@RequestMapping(value = "/orders", method = RequestMethod.GET)
	public @ResponseBody List<OrderDTO> list() {
		return orderService.list(new Sort(Sort.Direction.DESC, "createdAt"));
	}

	@RequestMapping(value = "/orders/user/{userId}", method = RequestMethod.GET)
	public @ResponseBody List<OrderDTO> list(@PathVariable Long userId) {
		return orderService.list(userId);
	}

	@RequestMapping(value = "/place-order", method = RequestMethod.POST)
	public String saveOrUpdate(@ModelAttribute("order") PlaceOrderDTO order,
			BindingResult result, WebRequest request, Model model) {

		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		String username = auth.getName(); // get logged in username
		order.setUsername(username);

		orderService.saveOrUpdate(order);

		model.addAttribute("successMessage",
				"Su presupuesto se ha enviado correctamente.");

		putDataModelInfo("requests", model);

		return "dashboard";
	}

	private void putDataModelInfo(String tabToShow, Model model) {
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();

		String username = auth.getName();

		CustomUserDetails userDetails = (CustomUserDetails) auth.getPrincipal();

		Map<String, Object> dataMap = dashBoardDataService.data(username,
				tabToShow, userDetails);
		dataMap.forEach((k, v) -> {
			model.addAttribute(k, v);
		});
	}

	@RequestMapping(value = "/orders/{id}", method = RequestMethod.DELETE)
	public @ResponseBody void delete(@PathVariable Long id) {
		orderService.delete(id);
	}
}
