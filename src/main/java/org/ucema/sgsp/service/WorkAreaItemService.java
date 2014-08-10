package org.ucema.sgsp.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.ucema.sgsp.api.dto.WorkAreaItemDTO;
import org.ucema.sgsp.api.transformation.WorkAreaItemTransformation;
import org.ucema.sgsp.persistence.WorkAreaItemDAO;
import org.ucema.sgsp.persistence.model.WorkAreaItem;

@Service
public class WorkAreaItemService {

	@Autowired
	private WorkAreaItemTransformation workAreaItemTransformation;
	
	@Autowired
	private WorkAreaItemDAO workAreaItemDAO;

	@Transactional
	public List<WorkAreaItemDTO> list() {
		return workAreaItemTransformation
				.transformToApi(workAreaItemDAO.findAll());
	}

	@Transactional
	public WorkAreaItemDTO saveOrUpdate(WorkAreaItemDTO workAreaItem) {
		WorkAreaItem response = workAreaItemDAO.save(workAreaItemTransformation
				.transformToModel(workAreaItem));
		workAreaItem.setId(response.getId());
		return workAreaItem;
	}

	@Transactional
	public void delete(WorkAreaItemDTO workAreaItem) {
		workAreaItemDAO.delete(workAreaItemTransformation
				.transformToModel(workAreaItem));
	}

	@Transactional
	public void delete(Long id) {
		WorkAreaItem workAreaItem = workAreaItemDAO.getOne(id);
		if (workAreaItem == null) {
			throw new RuntimeException("workAreaItem not found");
		}
		workAreaItemDAO.delete(workAreaItem);
	}

	@Transactional
	public WorkAreaItemDTO get(Long id) {
		WorkAreaItem workAreaItem = workAreaItemDAO.getOne(id);
		if (workAreaItem == null) {
			throw new RuntimeException("workAreaItem not found");
		}
		return workAreaItemTransformation.transformToApi(workAreaItem);
	}
}
