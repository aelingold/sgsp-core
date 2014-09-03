package org.ucema.sgsp.test;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.ucema.sgsp.BaseTest;
import org.ucema.sgsp.api.dto.WorkAreaItemDTO;
import org.ucema.sgsp.service.WorkAreaItemService;

public class WorkAreaItemTest extends BaseTest {

	@Autowired
	private WorkAreaItemService workAreaItemService;

	@Test
	public void insertAndVerify() {

		Long id = null;
		String description = "Description";
		String code = "XXX";
		String groupType = "WORK";
		String workAreaCode = null;

		WorkAreaItemDTO workAreaItemDTO = WorkAreaItemDTO.newInstance()
				.withDescription(description).withGroupType(groupType)
				.withId(id).withWorkAreaCode(workAreaCode).withCode(code).build();

		WorkAreaItemDTO response = workAreaItemService
				.saveOrUpdate(workAreaItemDTO);

		WorkAreaItemDTO retrieved = workAreaItemService.get(response.getId());
		Assert.assertNotNull(retrieved);

		Assert.assertEquals(description, retrieved.getDescription());
	}
}
