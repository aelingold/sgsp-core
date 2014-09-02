package org.ucema.sgsp.api.transformation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.ucema.sgsp.api.dto.CityDTO;
import org.ucema.sgsp.persistence.model.City;

@Component
public class CityTransformation {

	public List<CityDTO> transformToApi(List<City> cities) {
		List<CityDTO> result = new ArrayList<CityDTO>();

		for (City city : cities) {
			result.add(transformToApi(city));
		}

		return result;
	}

	public CityDTO transformToApi(City city) {
		CityDTO result = new CityDTO();

		result.setCode(city.getCode());
		result.setDescription(city.getDescription());
		
		result.setStateCode(city.getState().getCode());

		return result;
	}
}
