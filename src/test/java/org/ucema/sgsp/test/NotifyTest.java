package org.ucema.sgsp.test;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.ucema.sgsp.BaseTest;
import org.ucema.sgsp.api.dto.NotifyDTO;
import org.ucema.sgsp.service.NotifyService;

public class NotifyTest extends BaseTest {

	@Autowired
	private NotifyService notifyService;
	
	@Test
	public void insertAndVerifyNotify() {
		
		Long id = null;
		Long orderId = null;
		String type = "EMAIL";
		
		NotifyDTO response = notifyService.saveOrUpdate(new NotifyDTO(id, orderId, type));
		
		NotifyDTO notifyRetrieved = notifyService.get(response.getId());
		Assert.assertNotNull(notifyRetrieved);
		
		Assert.assertEquals(type, notifyRetrieved.getType());
	}
}
