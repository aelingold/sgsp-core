package org.ucema.sgsp.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.ucema.sgsp.api.dto.WorkAreaQuestionDTO;
import org.ucema.sgsp.api.transformation.WorkAreaQuestionTransformation;
import org.ucema.sgsp.persistence.WorkAreaQuestionDAO;
import org.ucema.sgsp.persistence.model.WorkAreaQuestion;

@Service
public class WorkAreaQuestionService {

	@Autowired
	private WorkAreaQuestionTransformation workAreaQuestionTransformation;
	
	@Autowired
	private WorkAreaQuestionDAO workAreaQuestionDAO;

	@Transactional
	public List<WorkAreaQuestionDTO> list() {
		return workAreaQuestionTransformation
				.transformToApi(workAreaQuestionDAO.findAll());
	}

	@Transactional
	public WorkAreaQuestionDTO saveOrUpdate(WorkAreaQuestionDTO workAreaQuestion) {
		WorkAreaQuestion response = workAreaQuestionDAO.save(workAreaQuestionTransformation
				.transformToModel(workAreaQuestion));
		workAreaQuestion.setId(response.getId());
		return workAreaQuestion;
	}

	@Transactional
	public void delete(WorkAreaQuestionDTO workAreaQuestion) {
		workAreaQuestionDAO.delete(workAreaQuestionTransformation
				.transformToModel(workAreaQuestion));
	}

	@Transactional
	public void delete(Long id) {
		WorkAreaQuestion workAreaQuestion= workAreaQuestionDAO.getOne(id);
		if (workAreaQuestion == null) {
			throw new RuntimeException("workAreaQuestion not found");
		}
		workAreaQuestionDAO.delete(workAreaQuestion);
	}

	@Transactional
	public WorkAreaQuestionDTO get(Long id) {
		WorkAreaQuestion workAreaQuestion = workAreaQuestionDAO.getOne(id);
		if (workAreaQuestion == null) {
			throw new RuntimeException("workAreaQuestion not found");
		}
		return workAreaQuestionTransformation.transformToApi(workAreaQuestion);
	}
}
