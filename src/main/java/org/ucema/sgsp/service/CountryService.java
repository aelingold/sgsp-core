package org.ucema.sgsp.service;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.ucema.sgsp.api.dto.CountryDTO;
import org.ucema.sgsp.api.transformation.CountryTransformation;
import org.ucema.sgsp.persistence.CountryDAO;
import org.ucema.sgsp.persistence.model.City;
import org.ucema.sgsp.persistence.model.Country;
import org.ucema.sgsp.persistence.model.State;

@Service
public class CountryService {

	@Autowired
	private CountryTransformation countryTransformation;

	@Autowired
	private CountryDAO countryDAO;
	
	@Transactional
	public List<CountryDTO> list() {
		return list(new Sort(Sort.Direction.ASC, "description"));
	}	

	@Transactional
	public List<CountryDTO> list(Sort sort) {

		List<Country> countries = countryDAO.findAll(sort);

		List<Country> countriesFiltered = new ArrayList<Country>();
		for (Country country : countries) {
			if (country.getIsEnabled()) {
				countriesFiltered.add(country);
			}
		}

		return countryTransformation.transformToApi(countriesFiltered, false);
	}

	@Transactional
	public void saveOrUpdate(CountryDTO country) {
		countryDAO.save(countryTransformation.transformToModel(country));
	}

	@Transactional
	public CountryDTO get(Long id) {
		Country country = countryDAO.getOne(id);
		if (country == null) {
			throw new RuntimeException("country not found");
		}

		if (country.getIsEnabled()) {
			return countryTransformation.transformToApi(country, false);
		} else {
			return null;
		}
	}
	
	@Transactional
	public Country find(String code) {
		Country country = countryDAO.findByCode(code);
		if (country == null) {
			throw new RuntimeException("country not found");
		}

		if (country.getIsEnabled()) {
			return country;
		} else {
			return null;
		}
	}	

	@Transactional
	public CountryDTO findByCode(String code) {
		Country country = countryDAO.findByCode(code);
		if (country == null) {
			throw new RuntimeException("country not found");
		}

		if (country.getIsEnabled()) {
			return countryTransformation.transformToApi(country, false);
		} else {
			return null;
		}
	}

	public void importGeo() {
		JSONParser parser = new JSONParser();

		try {

			Object obj = parser.parse(new FileReader(
					"/Users/aelingold/Documents/jsonCountries.txt"));

			JSONObject jsonObject = (JSONObject) obj;
			JSONObject countryStateOptionsProvider = (JSONObject) jsonObject
					.get("CountryStateOptionsProvider");
			JSONArray optionsArray = (JSONArray) countryStateOptionsProvider
					.get("options");
			for (Object optionArray : optionsArray) {
				JSONObject option = (JSONObject) optionArray;

				String value = (String) option.get("value");
				String key = (String) option.get("key");

				Country country = new Country();
				country.setCode(key);
				country.setDescription(value);
				country.setStates(new ArrayList<State>());

				JSONArray admDivisionsArray = (JSONArray) option
						.get("admDivisions");
				for (Object admDivisionArray : admDivisionsArray) {
					JSONObject admDivisionObject = (JSONObject) admDivisionArray;
					String admDivisionKey = (String) admDivisionObject
							.get("key");
					String admDivisionValue = (String) admDivisionObject
							.get("value");

					State state = new State();
					state.setCode(admDivisionKey);
					state.setDescription(admDivisionValue);
					state.setCities(new ArrayList<City>());

					country.getStates().add(state);

					JSONArray citiesArray = (JSONArray) admDivisionObject
							.get("cities");
					for (Object cityArray : citiesArray) {
						JSONObject cityObject = (JSONObject) cityArray;
						String cityValue = (String) cityObject.get("value");
						String cityKey = (String) cityObject.get("key");

						City city = new City();
						city.setCode(cityKey);
						city.setDescription(cityValue);

						state.getCities().add(city);
					}
				}
				countryDAO.save(country);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
