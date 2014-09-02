package org.ucema.sgsp.api.transformation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.ucema.sgsp.api.dto.StateDTO;
import org.ucema.sgsp.persistence.model.State;

@Component
public class StateTransformation {

	@Autowired
	private CityTransformation cityTransformation;
	
	public List<StateDTO> transformToApi(List<State> states, Boolean withCities) {
		List<StateDTO> result = new ArrayList<StateDTO>();

		for (State state : states) {
			result.add(transformToApi(state,withCities));
		}

		return result;
	}

	public StateDTO transformToApi(State state, Boolean withCities) {
		StateDTO result = new StateDTO();

		result.setCode(state.getCode());
		result.setDescription(state.getDescription());
		
		if (state.getCities() != null && withCities){
			result.setCities(cityTransformation.transformToApi(state.getCities()));	
		}
		
		result.setCountryCode(state.getCountry().getCode());
		result.setId(state.getId());

		return result;
	}
}
