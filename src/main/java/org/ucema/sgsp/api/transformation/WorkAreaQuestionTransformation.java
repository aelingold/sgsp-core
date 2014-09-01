package org.ucema.sgsp.api.transformation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.ucema.sgsp.api.dto.WorkAreaQuestionDTO;
import org.ucema.sgsp.persistence.model.GroupType;
import org.ucema.sgsp.persistence.model.WorkArea;
import org.ucema.sgsp.persistence.model.WorkAreaQuestion;
import org.ucema.sgsp.service.WorkAreaService;

@Component
public class WorkAreaQuestionTransformation {

	@Autowired
	private WorkAreaService workAreaService;

	public List<WorkAreaQuestionDTO> transformToApi(List<WorkAreaQuestion> workAreaQuestions) {
		List<WorkAreaQuestionDTO> result = new ArrayList<WorkAreaQuestionDTO>();

		for (WorkAreaQuestion workAreaQuestion : workAreaQuestions) {
			result.add(transformToApi(workAreaQuestion));
		}

		return result;
	}

	public List<WorkAreaQuestion> transformToModel(
			List<WorkAreaQuestionDTO> workAreaQuestions) {
		List<WorkAreaQuestion> result = new ArrayList<WorkAreaQuestion>();

		for (WorkAreaQuestionDTO workAreaQuestion : workAreaQuestions) {
			result.add(transformToModel(workAreaQuestion));
		}

		return result;
	}

	public WorkAreaQuestionDTO transformToApi(WorkAreaQuestion workAreaQuestion) {
		WorkAreaQuestionDTO result = new WorkAreaQuestionDTO();

		result.setId(workAreaQuestion.getId());
		result.setDescription(workAreaQuestion.getDescription());
		result.setGroupType(workAreaQuestion.getGroupType().name());
		if (workAreaQuestion.getWorkArea() != null) {
			result.setWorkAreaCode(workAreaQuestion.getWorkArea().getCode());
		}

		return result;
	}

	public WorkAreaQuestion transformToModel(WorkAreaQuestionDTO workAreaQuestion) {
		WorkAreaQuestion result = new WorkAreaQuestion();

		result.setId(workAreaQuestion.getId());
		result.setDescription(workAreaQuestion.getDescription());
		result.setGroupType(GroupType.valueOf(workAreaQuestion.getGroupType()));
		if (workAreaQuestion.getWorkAreaCode() != null) {
			result.setWorkArea(new WorkArea(workAreaService.findByCode(
					workAreaQuestion.getWorkAreaCode()).getId()));
		}

		return result;
	}
}
