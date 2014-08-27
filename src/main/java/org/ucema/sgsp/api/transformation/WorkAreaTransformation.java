package org.ucema.sgsp.api.transformation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.ucema.sgsp.api.dto.WorkAreaDTO;
import org.ucema.sgsp.persistence.model.WorkArea;
import org.ucema.sgsp.persistence.model.WorkAreaItem;

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
		result.setCode(workArea.getCode());
		if (workArea.getWorkAreaItems() != null) {
			result.setWorkAreaItemIds(getWorkAreaItemIds(workArea.getWorkAreaItems()));
			result.setWorkAreaItemCodes(getWorkAreaItemCodes(workArea.getWorkAreaItems()));
		}

		return result;
	}
	
	private List<String> getWorkAreaItemCodes(List<WorkAreaItem> workAreaItems) {
		List<String> response = new ArrayList<String>();
		
		for (WorkAreaItem workAreaItem : workAreaItems) {
			response.add(workAreaItem.getCode());
		}
		
		return response;
	}

	private List<Long> getWorkAreaItemIds(List<WorkAreaItem> workAreaItems){
		List<Long> response = new ArrayList<Long>();
		
		for (WorkAreaItem workAreaItem : workAreaItems) {
			response.add(workAreaItem.getId());
		}
		
		return response;
	}

	public WorkArea transformToModel(WorkAreaDTO workArea) {
		WorkArea result = new WorkArea();

		result.setId(workArea.getId());
		result.setDescription(workArea.getDescription());
		result.setCode(workArea.getCode());
		if (workArea.getWorkAreaItemIds() != null) {
			result.setWorkAreaItems(getWorkAreaItems(workArea.getWorkAreaItemIds()));
		}

		return result;
	}
	
	private List<WorkAreaItem> getWorkAreaItems(List<Long> workAreaItemIds){
		List<WorkAreaItem> response = new ArrayList<WorkAreaItem>();
		
		for (Long workAreaItemId : workAreaItemIds) {
			response.add(new WorkAreaItem(workAreaItemId));
		}
		
		return response;
	}	
}
