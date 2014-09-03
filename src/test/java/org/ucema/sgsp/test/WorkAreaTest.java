package org.ucema.sgsp.test;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.ucema.sgsp.BaseTest;
import org.ucema.sgsp.api.dto.WorkAreaDTO;
import org.ucema.sgsp.service.WorkAreaService;

public class WorkAreaTest extends BaseTest {

	@Autowired
	private WorkAreaService workAreaService;

	@Test
	public void insertAndVerify() {

		Long id = null;
		String description = "Description";
		String code = "XXX";
		List<String> workAreaItemCodes = null;
		Boolean isEnabled = true;
		
		WorkAreaDTO workAreaDTO = WorkAreaDTO.newInstance().withCode(code)
				.withDescription(description).withId(id).withIsEnabled(isEnabled)
				.withWorkAreaItemCodes(workAreaItemCodes).build();

		WorkAreaDTO response = workAreaService.saveOrUpdate(workAreaDTO);

		WorkAreaDTO retrieved = workAreaService.get(response.getId());
		Assert.assertNotNull(retrieved);

		Assert.assertEquals(description, retrieved.getDescription());
	}
}
