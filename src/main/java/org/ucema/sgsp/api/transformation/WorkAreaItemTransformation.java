package org.ucema.sgsp.api.transformation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.ucema.sgsp.api.dto.WorkAreaItemDTO;
import org.ucema.sgsp.persistence.model.GroupType;
import org.ucema.sgsp.persistence.model.WorkArea;
import org.ucema.sgsp.persistence.model.WorkAreaItem;
import org.ucema.sgsp.service.WorkAreaService;

@Component
public class WorkAreaItemTransformation {

	@Autowired
	private WorkAreaTransformation workAreaTransformation;
	@Autowired
	private WorkAreaService workAreaService;

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
		result.setGroupType(workAreaItem.getGroupType().name());
		if (workAreaItem.getWorkArea() != null) {
			result.setWorkAreaCode(workAreaItem.getWorkArea().getCode());
		}
		result.setCode(workAreaItem.getCode());

		return result;
	}

	public WorkAreaItem transformToModel(WorkAreaItemDTO workAreaItem) {
		WorkAreaItem result = new WorkAreaItem();

		result.setId(workAreaItem.getId());
		result.setDescription(workAreaItem.getDescription());
		result.setGroupType(GroupType.valueOf(workAreaItem.getGroupType()));
		if (workAreaItem.getWorkAreaCode() != null) {
			result.setWorkArea(new WorkArea(workAreaService.findByCode(
					workAreaItem.getWorkAreaCode()).getId()));
		}
		result.setCode(workAreaItem.getCode());

		return result;
	}
}
