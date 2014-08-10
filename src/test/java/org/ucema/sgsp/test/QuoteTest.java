package org.ucema.sgsp.test;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.ucema.sgsp.BaseTest;
import org.ucema.sgsp.api.dto.QuoteDTO;
import org.ucema.sgsp.service.QuoteService;

public class QuoteTest extends BaseTest{

	@Autowired
	private QuoteService quoteService;

	@Test
	public void insertAndVerifyQuote() {

		Long id = null;
		String description = "Description";
		BigDecimal amount = BigDecimal.ZERO;
		Long userId = null;
		Long orderId = null;

		QuoteDTO response = quoteService.saveOrUpdate(new QuoteDTO(id, userId,
				orderId, description, amount));

		QuoteDTO retrieved = quoteService.get(response.getId());
		Assert.assertNotNull(retrieved);

		Assert.assertEquals(description, retrieved.getDescription());
	}
}
