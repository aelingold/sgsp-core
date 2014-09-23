package org.ucema.sgsp.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.ucema.sgsp.api.dto.RatePlanDTO;
import org.ucema.sgsp.api.transformation.RatePlanTransformation;
import org.ucema.sgsp.persistence.RatePlanDAO;
import org.ucema.sgsp.persistence.model.RatePlan;

@Service
public class RatePlanService {

	@Autowired
	private RatePlanTransformation ratePlanTransformation;

	@Autowired
	private RatePlanDAO ratePlanDAO;

	@Transactional
	public List<RatePlanDTO> list() {
		return ratePlanTransformation.transformToApi(ratePlanDAO.findAll());
	}	
	
	@Transactional
	public RatePlanDTO get(Long id) {
		RatePlan ratePlan = ratePlanDAO.getOne(id);
		if (ratePlan == null) {
			throw new RuntimeException("ratePlan not found");
		}

		if (ratePlan.getIsEnabled()) {
			return ratePlanTransformation.transformToApi(ratePlan);
		} else {
			return null;
		}
	}

	@Transactional
	public RatePlanDTO findByCode(String code) {
		RatePlan ratePlan = ratePlanDAO.findByCode(code);
		if (ratePlan == null) {
			throw new RuntimeException("ratePlan not found");
		}

		if (ratePlan.getIsEnabled()) {
			return ratePlanTransformation.transformToApi(ratePlan);
		} else {
			return null;
		}
	}
}
