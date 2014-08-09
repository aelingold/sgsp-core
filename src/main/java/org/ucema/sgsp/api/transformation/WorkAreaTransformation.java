package org.ucema.sgsp.api.transformation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.ucema.sgsp.api.dto.WorkAreaDTO;
import org.ucema.sgsp.persistence.model.WorkArea;

@Component
public class WorkAreaTransformation {

	@Autowired
	private WorkAreaItemTransformation workAreaItemTransformation;

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
		result.setName(workArea.getName());
		if (workArea.getWorkAreaItems() != null) {
			result.setWorkAreaItems(workAreaItemTransformation
					.transformToApi(workArea.getWorkAreaItems()));
		}

		return result;
	}

	public WorkArea transformToModel(WorkAreaDTO workArea) {
		WorkArea result = new WorkArea();

		result.setId(workArea.getId());
		result.setDescription(workArea.getDescription());
		result.setName(workArea.getName());
		if (workArea.getWorkAreaItems() != null) {
			result.setWorkAreaItems(workAreaItemTransformation
					.transformToModel(workArea.getWorkAreaItems()));
		}

		return result;
	}
}
