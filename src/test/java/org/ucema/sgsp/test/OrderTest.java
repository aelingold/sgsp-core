package org.ucema.sgsp.test;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.ucema.sgsp.BaseTest;
import org.ucema.sgsp.api.dto.OrderDTO;
import org.ucema.sgsp.api.dto.PlaceOrderDTO;
import org.ucema.sgsp.service.OrderService;

public class OrderTest extends BaseTest {

	@Autowired
	private OrderService orderService;

	@Test
	public void insertAndVerifyOrder() {

		String workDescription = "workDescription";
		String workDateType = "URGENT";
		String stateCode = null;
		String cityCode = null;
		String username = null;
		String workAreaCode = null;

		PlaceOrderDTO order = PlaceOrderDTO.newInstance()
				.withStateCode(stateCode).withCityCode(cityCode)
				.withUsername(username).withWorkAreaCode(workAreaCode)
				.withWorkDateType(workDateType)
				.withWorkDescription(workDescription);

		OrderDTO response = orderService.saveOrUpdate(order);
		Assert.assertNotNull(response);

		OrderDTO orderRetrieved = orderService.get(response.getId());

		Assert.assertEquals(workDescription,
				orderRetrieved.getWorkDescription());
	}
}
