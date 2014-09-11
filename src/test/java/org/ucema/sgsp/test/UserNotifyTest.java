package org.ucema.sgsp.test;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.ucema.sgsp.BaseTest;
import org.ucema.sgsp.api.dto.UserNotifyDTO;
import org.ucema.sgsp.service.UserNotifyService;

public class UserNotifyTest extends BaseTest {

	@Autowired
	private UserNotifyService userNotifyService;

	@Test
	public void insertAndVerifyUserNotify() {

		String type = "EMAIL";

		UserNotifyDTO userNotify = UserNotifyDTO.newInstance().withType(type)
				.build();

		UserNotifyDTO response = userNotifyService.saveOrUpdate(userNotify);

		UserNotifyDTO notifyRetrieved = userNotifyService.get(response.getId());
		Assert.assertNotNull(notifyRetrieved);

		Assert.assertEquals(type, notifyRetrieved.getType());
	}
}
