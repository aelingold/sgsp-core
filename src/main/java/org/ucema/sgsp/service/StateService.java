package org.ucema.sgsp.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.ucema.sgsp.api.dto.StateDTO;
import org.ucema.sgsp.api.transformation.StateTransformation;
import org.ucema.sgsp.persistence.StateDAO;
import org.ucema.sgsp.persistence.model.State;

@Service
public class StateService {

	@Autowired
	private StateTransformation stateTransformation;
	
	@Autowired
	private StateDAO stateDAO;

	@Transactional
	public List<StateDTO> list() {
		
		List<State> states = stateDAO.findAll();

		List<State> statesFiltered = new ArrayList<State>();
		for (State state : states) {
			if (state.getIsEnabled()) {
				statesFiltered.add(state);
			}
		}
	
		return stateTransformation.transformToApi(statesFiltered,false);
	}	
	
	@Transactional
	public StateDTO get(Long id){
		State state = stateDAO.getOne(id);
		if (state == null){
			throw new RuntimeException("state not found");
		}
		
		if (state.getIsEnabled()) {
			return stateTransformation.transformToApi(state,false);
		} else {
			return null;
		}
	}
	
	@Transactional
	public StateDTO findByCode(String code){
		State state = stateDAO.findByCode(code);
		if (state == null) {
			throw new RuntimeException("state not found");
		}		

		if (state.getIsEnabled()) {
			return stateTransformation.transformToApi(state,false);
		} else {
			return null;
		}		
	}
}
