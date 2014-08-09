package org.ucema.sgsp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.ucema.sgsp.api.dto.PaymentDTO;
import org.ucema.sgsp.service.PaymentService;

@Controller
public class PaymentController {

	@Autowired
	private PaymentService paymentService;

	@RequestMapping(value = "/payments/{id}", method = RequestMethod.GET)
	public @ResponseBody PaymentDTO get(@PathVariable Long id) {
		return paymentService.get(id);
	}
	
	@RequestMapping(value = "/payments", method = RequestMethod.GET)
	public @ResponseBody List<PaymentDTO> list() {
		return paymentService.list();
	}

	@RequestMapping(value = "/payments", method = RequestMethod.POST)
	public @ResponseBody void saveOrUpdate(@RequestBody PaymentDTO payment) {
		paymentService.saveOrUpdate(payment);
	}

	@RequestMapping(value = "/payments/{id}", method = RequestMethod.DELETE)
	public @ResponseBody void delete(@PathVariable Long id) {
		paymentService.delete(id);
	}
}
