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
		String type = "DEPOSIT";
		Long quoteId = null;
		
		PaymentDTO response = paymentService.saveOrUpdate(new PaymentDTO(id, quoteId, type));
		
		PaymentDTO paymentRetrieved = paymentService.get(response.getId());
		Assert.assertNotNull(paymentRetrieved);
		
		Assert.assertEquals(type, paymentRetrieved.getType());		
	}
}
