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
		String name = "Name";
		Long workAreaId = null;

		WorkAreaItemDTO response = workAreaItemService
				.saveOrUpdate(new WorkAreaItemDTO(id, name, description,
						workAreaId));

		WorkAreaItemDTO retrieved = workAreaItemService.get(response.getId());
		Assert.assertNotNull(retrieved);

		Assert.assertEquals(description, retrieved.getDescription());
	}
}
