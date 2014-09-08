package org.ucema.sgsp.test;

import java.math.BigDecimal;
import java.util.Date;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.ucema.sgsp.BaseTest;
import org.ucema.sgsp.api.dto.AmountDTO;
import org.ucema.sgsp.api.dto.QuoteDTO;
import org.ucema.sgsp.service.QuoteService;

public class QuoteTest extends BaseTest{

	@Autowired
	private QuoteService quoteService;

	@Test
	public void insertAndVerifyQuote() {

		Long id = null;
		String description = "Description";
		Date validDateUntil = new Date();
		BigDecimal amount = BigDecimal.ZERO;
		String currencyCode = "ARS";
		AmountDTO amountDTO = AmountDTO.newInstance().withAmount(amount).withCurrencyCode(currencyCode);
		QuoteDTO quote = QuoteDTO.newInstance().withId(id).withDescription(description).withValidDateUntil(validDateUntil).withAmount(amountDTO)
				.build();		
		
		QuoteDTO response = quoteService.saveOrUpdate(quote);

		QuoteDTO retrieved = quoteService.get(response.getId());
		Assert.assertNotNull(retrieved);

		Assert.assertEquals(description, retrieved.getDescription());
	}
}
