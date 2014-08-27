package org.ucema.sgsp.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
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
	public List<WorkAreaDTO> list() {
		return workAreaTransformation.transformToApi(workAreaDAO.findAll());
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
		return workAreaTransformation.transformToApi(workArea);
	}
	
	@Transactional
	public WorkAreaDTO findByCode(String code){
		WorkArea workArea = workAreaDAO.findByCode(code);
		if (workArea == null) {
			throw new RuntimeException("workArea not found");
		}		
		return workAreaTransformation.transformToApi(workArea);		
	}
}
