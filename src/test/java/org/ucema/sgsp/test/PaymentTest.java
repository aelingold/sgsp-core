package org.ucema.sgsp.test;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.ucema.sgsp.BaseTest;
import org.ucema.sgsp.api.dto.PaymentDTO;
import org.ucema.sgsp.persistence.model.PaymentStatusType;
import org.ucema.sgsp.persistence.model.PaymentType;
import org.ucema.sgsp.service.PaymentService;

public class PaymentTest extends BaseTest {

	@Autowired
	private PaymentService paymentService;

	@Test
	public void insertAndVerifyPayment() {

		Long id = null;
		String paymentType = PaymentType.CASH.name();
		Long quoteId = null;
		String statusType = PaymentStatusType.DONE.name();
		
		PaymentDTO paymentDTO = PaymentDTO.newInstance().withId(id)
				.withPaymentType(paymentType).withQuoteId(quoteId).withStatusType(statusType).build();

		PaymentDTO response = paymentService.saveOrUpdate(paymentDTO);

		PaymentDTO paymentRetrieved = paymentService.get(response.getId());
		Assert.assertNotNull(paymentRetrieved);

		Assert.assertEquals(paymentType, paymentRetrieved.getPaymentType());
	}
}
