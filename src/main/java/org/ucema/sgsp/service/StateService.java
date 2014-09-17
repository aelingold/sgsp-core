package org.ucema.sgsp.service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.ucema.sgsp.api.dto.CityDTO;
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

	public Map<String, Map<String, String>> getConfigMap(
			List<StateDTO> states, List<CityDTO> cities) {
		
		Map<String, Map<String, String>> configMap = new LinkedHashMap<String, Map<String, String>>();
		states.forEach(s -> {
			Map<String, String> citiesMap = new LinkedHashMap<String, String>();

			List<CityDTO> citiesFiltered = cities.stream()
					.filter(c -> c.getStateCode().equals(s.getCode()))
					.collect(Collectors.toList());

			citiesFiltered.forEach(cf -> {
				citiesMap.put(cf.getCode(), cf.getDescription());
			});

			configMap.put(s.getCode(), citiesMap);
		});

		return configMap;
	}

	@Transactional
	public List<StateDTO> list(Sort sort) {

		List<State> states = stateDAO.findAll(sort);

		List<State> statesFiltered = new ArrayList<State>();
		for (State state : states) {
			if (state.getIsEnabled()) {
				statesFiltered.add(state);
			}
		}

		return stateTransformation.transformToApi(statesFiltered, false);
	}

	@Transactional
	public List<StateDTO> list() {
		return list(new Sort(Sort.Direction.ASC, "description"));
	}

	@Transactional
	public StateDTO get(Long id) {
		State state = stateDAO.getOne(id);
		if (state == null) {
			throw new RuntimeException("state not found");
		}

		if (state.getIsEnabled()) {
			return stateTransformation.transformToApi(state, false);
		} else {
			return null;
		}
	}

	@Transactional
	public StateDTO findByCode(String code) {
		State state = stateDAO.findByCode(code);
		if (state == null) {
			throw new RuntimeException("state not found");
		}

		if (state.getIsEnabled()) {
			return stateTransformation.transformToApi(state, false);
		} else {
			return null;
		}
	}
}
