package org.ucema.sgsp.api.transformation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.ucema.sgsp.api.dto.WorkAreaDTO;
import org.ucema.sgsp.persistence.model.WorkArea;
import org.ucema.sgsp.persistence.model.WorkAreaItem;
import org.ucema.sgsp.service.WorkAreaItemService;

@Component
public class WorkAreaTransformation {

	@Autowired
	private WorkAreaItemTransformation workAreaItemTransformation;
	@Autowired
	private WorkAreaItemService workAreaItemService;

	public List<WorkAreaDTO> transformToApi(List<WorkArea> workAreas) {
		List<WorkAreaDTO> result = new ArrayList<WorkAreaDTO>();

		for (WorkArea workArea : workAreas) {
			result.add(transformToApi(workArea));
		}

		return result;
	}

	public List<WorkArea> transformToModel(List<WorkAreaDTO> workAreas) {
		List<WorkArea> result = new ArrayList<WorkArea>();

		for (WorkAreaDTO workArea : workAreas) {
			result.add(transformToModel(workArea));
		}

		return result;
	}

	public WorkAreaDTO transformToApi(WorkArea workArea) {
		WorkAreaDTO result = new WorkAreaDTO();

		result.setId(workArea.getId());
		result.setDescription(workArea.getDescription());
		result.setCode(workArea.getCode());
		
		if (workArea.getWorkAreaItems() != null) {
			result.setWorkAreaItemCodes(getWorkAreaItemCodes(workArea
					.getWorkAreaItems()));
		}
		result.setIsEnabled(workArea.getIsEnabled());

		return result;
	}

	private List<String> getWorkAreaItemCodes(List<WorkAreaItem> workAreaItems) {
		List<String> response = new ArrayList<String>();

		for (WorkAreaItem workAreaItem : workAreaItems) {
			response.add(workAreaItem.getCode());
		}

		return response;
	}

	public WorkArea transformToModel(WorkAreaDTO workArea) {
		WorkArea result = new WorkArea();

		result.setId(workArea.getId());
		result.setDescription(workArea.getDescription());
		result.setCode(workArea.getCode());
		if (workArea.getWorkAreaItemCodes() != null) {
			result.setWorkAreaItems(getWorkAreaItems(workArea
					.getWorkAreaItemCodes()));
		}
		result.setIsEnabled(workArea.getIsEnabled());

		return result;
	}

	private List<WorkAreaItem> getWorkAreaItems(List<String> workAreaItemCodes) {
		List<WorkAreaItem> response = new ArrayList<WorkAreaItem>();

		for (String workAreaItemCode : workAreaItemCodes) {
			response.add(new WorkAreaItem(workAreaItemService.findByCode(
					workAreaItemCode).getId()));
		}

		return response;
	}
}
