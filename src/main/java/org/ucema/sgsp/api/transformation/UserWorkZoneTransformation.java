package org.ucema.sgsp.api.transformation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.ucema.sgsp.api.dto.UserWorkZoneDTO;
import org.ucema.sgsp.persistence.model.City;
import org.ucema.sgsp.persistence.model.UserWorkZone;
import org.ucema.sgsp.security.model.User;
import org.ucema.sgsp.service.CityService;
import org.ucema.sgsp.service.StateService;
import org.ucema.sgsp.service.UserService;

@Component
public class UserWorkZoneTransformation {

	@Autowired
	private StateService stateService;
	@Autowired
	private CityService cityService;
	@Autowired
	private UserService userService;

	public List<UserWorkZoneDTO> transformToApi(List<UserWorkZone> userWorkZones) {
		List<UserWorkZoneDTO> result = new ArrayList<UserWorkZoneDTO>();

		for (UserWorkZone userWorkZone : userWorkZones) {
			result.add(transformToApi(userWorkZone));
		}

		return result;
	}

	public List<UserWorkZone> transformToModel(
			List<UserWorkZoneDTO> userWorkZones) {
		List<UserWorkZone> result = new ArrayList<UserWorkZone>();

		for (UserWorkZoneDTO userWorkZone : userWorkZones) {
			result.add(transformToModel(userWorkZone));
		}

		return result;
	}

	public UserWorkZoneDTO transformToApi(UserWorkZone userWorkZone) {
		UserWorkZoneDTO result = new UserWorkZoneDTO();

		result.setId(userWorkZone.getId());
		result.setCityCode(userWorkZone.getCity().getCode());

		if (userWorkZone.getUser() != null) {
			result.setUsername(userWorkZone.getUser().getEmail());
		}

		return result;
	}

	public UserWorkZone transformToModel(UserWorkZoneDTO userWorkZone) {
		UserWorkZone result = new UserWorkZone();

		result.setId(userWorkZone.getId());
		result.setCity(new City(cityService.findByCode(
				userWorkZone.getCityCode()).getId()));

		if (userWorkZone.getUsername() != null) {
			result.setUser(new User(userService.findByEmail(
					userWorkZone.getUsername()).getId()));
		}

		return result;
	}
}
