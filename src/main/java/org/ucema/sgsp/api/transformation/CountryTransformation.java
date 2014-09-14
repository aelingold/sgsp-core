package org.ucema.sgsp.api.transformation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.ucema.sgsp.api.dto.CountryDTO;
import org.ucema.sgsp.persistence.model.Country;

@Component
public class CountryTransformation {

	@Autowired
	private StateTransformation stateTransformation;
	
	public List<CountryDTO> transformToApi(List<Country> countries, Boolean withStates) {
		List<CountryDTO> result = new ArrayList<CountryDTO>();

		for (Country country : countries) {
			result.add(transformToApi(country,withStates));
		}

		return result;
	}

	public List<Country> transformToModel(List<CountryDTO> countries) {
		List<Country> result = new ArrayList<Country>();

		for (CountryDTO country : countries) {
			result.add(transformToModel(country));
		}

		return result;
	}
	
	public Country transformToModel(CountryDTO country) {
		Country result = new Country();

		result.setId(country.getId());
		result.setCode(country.getCode());
		result.setDescription(country.getDescription());

		return result;
	}	

	public CountryDTO transformToApi(Country country, Boolean withStates) {
		CountryDTO result = new CountryDTO();

		result.setId(country.getId());
		result.setCode(country.getCode());
		result.setDescription(country.getDescription());
		
		if (country.getStates() != null && withStates){
			result.setStates(stateTransformation.transformToApi(country.getStates(),withStates));
		}

		return result;
	}
}
