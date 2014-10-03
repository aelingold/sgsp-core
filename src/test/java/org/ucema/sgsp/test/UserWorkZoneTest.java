package org.ucema.sgsp.test;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.ucema.sgsp.BaseTest;
import org.ucema.sgsp.api.dto.UserWorkZoneDTO;
import org.ucema.sgsp.service.UserWorkZoneService;

public class UserWorkZoneTest extends BaseTest {

	@Autowired
	private UserWorkZoneService userWorkZoneService;

	@Test
	public void insertAndVerifyUser() {

		String cityCode = "JSIXZ";

		UserWorkZoneDTO request = UserWorkZoneDTO.newInstance()
				.withCityCode(cityCode).build();

		UserWorkZoneDTO response = userWorkZoneService.saveOrUpdate(request);

		UserWorkZoneDTO responseRetrieved = userWorkZoneService.get(response
				.getId());
		Assert.assertNotNull(responseRetrieved);
	}
}
