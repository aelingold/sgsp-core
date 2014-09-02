package org.ucema.sgsp.api.transformation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.ucema.sgsp.api.dto.CityDTO;
import org.ucema.sgsp.api.dto.CountryDTO;
import org.ucema.sgsp.api.dto.StateDTO;
import org.ucema.sgsp.persistence.model.City;
import org.ucema.sgsp.persistence.model.Country;
import org.ucema.sgsp.persistence.model.State;

@Component
public class CountryTransformation {

	public List<CountryDTO> transformToApi(List<Country> countries) {
		List<CountryDTO> result = new ArrayList<CountryDTO>();

		for (Country country : countries) {
			result.add(transformToApi(country));
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

	public CountryDTO transformToApi(Country country) {
		CountryDTO result = new CountryDTO();

		result.setId(country.getId());
		result.setCode(country.getCode());
		result.setDescription(country.getDescription());
		
		if (country.getStates() != null){
			result.setStates(buildStates(country.getStates()));
		}

		return result;
	}

	private List<StateDTO> buildStates(List<State> states) {

		List<StateDTO> result = new ArrayList<StateDTO>();
		
		for (State state : states) {
			result.add(buildState(state));
		}
		
		return result;
	}

	private StateDTO buildState(State state) {

		StateDTO result = new StateDTO();
		
		if (state.getCities() != null){
			result.setCities(buildCities(state.getCities()));	
		}
		
		result.setCode(state.getCode());
		result.setDescription(state.getDescription());
		
		return result;
	}

	private List<CityDTO> buildCities(List<City> cities) {

		List<CityDTO> result = new ArrayList<>();
		
		for (City city : cities) {
			result.add(buildCity(city));
		}
		
		return result;
	}

	private CityDTO buildCity(City city) {

		CityDTO result = new CityDTO();
		
		result.setCode(city.getCode());
		result.setDescription(city.getDescription());
		
		return result;
	}

	public Country transformToModel(CountryDTO country) {
		Country result = new Country();

		result.setId(country.getId());
		result.setCode(country.getCode());
		result.setDescription(country.getDescription());

		return result;
	}
}
