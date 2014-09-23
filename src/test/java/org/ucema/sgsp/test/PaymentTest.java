package org.ucema.sgsp.test;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.ucema.sgsp.BaseTest;
import org.ucema.sgsp.api.dto.PaymentDTO;
import org.ucema.sgsp.service.PaymentService;

public class PaymentTest extends BaseTest {

	@Autowired
	private PaymentService paymentService;

	@Test
	public void insertAndVerifyPayment() {

		Long id = null;
		String paymentType = "DEPOSIT";
		Long quoteId = null;

		PaymentDTO paymentDTO = PaymentDTO.newInstance().withId(id)
				.withPaymentType(paymentType).withQuoteId(quoteId).build();

		PaymentDTO response = paymentService.saveOrUpdate(paymentDTO);

		PaymentDTO paymentRetrieved = paymentService.get(response.getId());
		Assert.assertNotNull(paymentRetrieved);

		Assert.assertEquals(paymentType, paymentRetrieved.getPaymentType());
	}
}
