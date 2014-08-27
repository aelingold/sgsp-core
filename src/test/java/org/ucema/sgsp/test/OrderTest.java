package org.ucema.sgsp.test;

import java.util.Date;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.ucema.sgsp.BaseTest;
import org.ucema.sgsp.api.dto.OrderDTO;
import org.ucema.sgsp.service.OrderService;

public class OrderTest extends BaseTest {

	@Autowired
	private OrderService orderService;

	@Test
	public void insertAndVerifyOrder() {

		Long id = null;
		Long userId = null;
		String title = "Title";
		Long workAreaId = null;
		String workDescription = "workDescription";
		String workProblem = "workProblem";
		String workDateType = "URGENT";
		Date workDate = new Date();
		String place = "place";
		Boolean pendingNotify = true;
		Boolean pendingQuotes = true;

		OrderDTO order = OrderDTO.newInstance().withId(id)
				.withPendingNotify(pendingNotify)
				.withPendingQuotes(pendingQuotes).withPlace(place)
				.withTitle(title).withUserId(userId).withWorkAreaId(workAreaId)
				.withWorkDate(workDate).withWorkDateType(workDateType)
				.withWorkDescription(workDescription)
				.withWorkProblem(workProblem);

		OrderDTO response = orderService.saveOrUpdate(order);
		Assert.assertNotNull(response);

		OrderDTO orderRetrieved = orderService.get(response.getId());

		Assert.assertEquals(title, orderRetrieved.getTitle());
	}
}
