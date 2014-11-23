package org.ucema.sgsp.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.ucema.sgsp.api.dto.WorkAreaDTO;
import org.ucema.sgsp.api.transformation.WorkAreaTransformation;
import org.ucema.sgsp.persistence.WorkAreaDAO;
import org.ucema.sgsp.persistence.model.WorkArea;

@Service
public class WorkAreaService {

	@Autowired
	private WorkAreaTransformation workAreaTransformation;
	
	@Autowired
	private WorkAreaDAO workAreaDAO;

	@Transactional
	@Cacheable("workAreas")
	public List<WorkAreaDTO> list() {
		
		List<WorkArea> workAreas = workAreaDAO.findAll();

		List<WorkArea> workAreasFiltered = new ArrayList<WorkArea>();
		for (WorkArea workArea : workAreas) {
			if (workArea.getIsEnabled()) {
				workAreasFiltered.add(workArea);
			}
		}

		return workAreaTransformation.transformToApi2(workAreasFiltered);		
	}

	@Transactional
	public WorkAreaDTO saveOrUpdate(WorkAreaDTO workArea) {
		WorkArea response = workAreaDAO.save(workAreaTransformation.transformToModel(workArea));
		workArea.setId(response.getId());
		return workArea;
	}

	@Transactional
	public void delete(WorkAreaDTO workArea) {
		workAreaDAO.delete(workAreaTransformation.transformToModel(workArea));
	}
	
	@Transactional
	public void delete(Long id) {
		WorkArea workArea = workAreaDAO.getOne(id);
		if (workArea == null) {
			throw new RuntimeException("workArea not found");
		}		
		workAreaDAO.delete(workArea);
	}		
	
	@Transactional
	public WorkAreaDTO get(Long id){
		WorkArea workArea = workAreaDAO.getOne(id);
		if (workArea == null) {
			throw new RuntimeException("workArea not found");
		}		

		if (workArea.getIsEnabled()) {
			return workAreaTransformation.transformToApi(workArea);
		} else {
			return null;
		}
	}
	
	@Transactional
	public WorkAreaDTO findByCode(String code){
		WorkArea workArea = workAreaDAO.findByCode(code);
		if (workArea == null) {
			throw new RuntimeException("workArea not found");
		}
		
		if (workArea.getIsEnabled()) {
			return workAreaTransformation.transformToApi(workArea);
		} else {
			return null;
		}
	}
}
