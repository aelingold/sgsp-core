package org.ucema.sgsp.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.ucema.sgsp.api.dto.CityDTO;
import org.ucema.sgsp.api.transformation.CityTransformation;
import org.ucema.sgsp.persistence.CityDAO;
import org.ucema.sgsp.persistence.model.City;

@Service
public class CityService {

	@Autowired
	private CityTransformation cityTransformation;

	@Autowired
	private CityDAO cityDAO;

	@Transactional
	public List<CityDTO> list() {
		return list(new Sort(Sort.Direction.ASC, "description"));
	}	
	
	@Transactional
	public List<CityDTO> list(Sort sort) {

		List<City> cities = cityDAO.findAll(sort);

		List<City> citiesFiltered = new ArrayList<City>();
		for (City city : cities) {
			if (city.getIsEnabled()) {
				citiesFiltered.add(city);
			}
		}

		return cityTransformation.transformToApi(citiesFiltered);
	}

	@Transactional
	public List<CityDTO> list(String stateCode) {

		List<City> cities = cityDAO.findAll();

		List<City> citiesFiltered = new ArrayList<City>();
		for (City city : cities) {
			if (city.getIsEnabled()
					&& city.getState().getCode().equals(stateCode)) {
				citiesFiltered.add(city);
			}
		}

		return cityTransformation.transformToApi(citiesFiltered);
	}

	@Transactional
	public CityDTO get(Long id) {
		City city = cityDAO.getOne(id);
		if (city == null) {
			throw new RuntimeException("city not found");
		}

		if (city.getIsEnabled()) {
			return cityTransformation.transformToApi(city);
		} else {
			return null;
		}
	}

	@Transactional
	public CityDTO findByCode(String code) {
		City city = cityDAO.findByCode(code);
		if (city == null) {
			throw new RuntimeException("city not found");
		}

		if (city.getIsEnabled()) {
			return cityTransformation.transformToApi(city);
		} else {
			return null;
		}
	}
}
