package org.ucema.sgsp.api.transformation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.ucema.sgsp.api.dto.WorkAreaItemDTO;
import org.ucema.sgsp.persistence.model.WorkAreaItem;

@Component
public class WorkAreaItemTransformation {

	@Autowired
	private WorkAreaTransformation workAreaTransformation;

	public List<WorkAreaItemDTO> transformToApi(List<WorkAreaItem> workAreaItems) {
		List<WorkAreaItemDTO> result = new ArrayList<WorkAreaItemDTO>();

		for (WorkAreaItem workAreaItem : workAreaItems) {
			result.add(transformToApi(workAreaItem));
		}

		return result;
	}

	public List<WorkAreaItem> transformToModel(
			List<WorkAreaItemDTO> workAreaItems) {
		List<WorkAreaItem> result = new ArrayList<WorkAreaItem>();

		for (WorkAreaItemDTO workAreaItem : workAreaItems) {
			result.add(transformToModel(workAreaItem));
		}

		return result;
	}

	public WorkAreaItemDTO transformToApi(WorkAreaItem workAreaItem) {
		WorkAreaItemDTO result = new WorkAreaItemDTO();

		result.setId(workAreaItem.getId());
		result.setDescription(workAreaItem.getDescription());
		result.setName(workAreaItem.getName());
		if (workAreaItem.getWorkArea() != null) {
			result.setWorkArea(workAreaTransformation
					.transformToApi(workAreaItem.getWorkArea()));
		}

		return result;
	}

	public WorkAreaItem transformToModel(WorkAreaItemDTO workAreaItem) {
		WorkAreaItem result = new WorkAreaItem();

		result.setId(workAreaItem.getId());
		result.setDescription(workAreaItem.getDescription());
		result.setName(workAreaItem.getName());
		if (workAreaItem.getWorkArea() != null) {
			result.setWorkArea(workAreaTransformation
					.transformToModel(workAreaItem.getWorkArea()));
		}

		return result;
	}
}
