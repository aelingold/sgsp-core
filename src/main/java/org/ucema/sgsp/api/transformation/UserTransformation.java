package org.ucema.sgsp.api.transformation;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.ucema.sgsp.api.dto.DashBoardUserDTO;
import org.ucema.sgsp.api.dto.UserDTO;
import org.ucema.sgsp.security.model.User;
import org.ucema.sgsp.service.CountryService;

@Component
public class UserTransformation {

	@Autowired
	private UserWorkRateTransformation userWorkRateTransformation;
	@Autowired
	private WorkAreaTransformation workAreaTransformation;
	@Autowired
	private CountryService countryService;

	public List<UserDTO> transformToApi(List<User> users) {
		List<UserDTO> result = new ArrayList<UserDTO>();

		for (User user : users) {
			result.add(transformToApi(user));
		}

		return result;
	}

	public UserDTO transformToApi(User user) {
		UserDTO result = new UserDTO();

		result.setId(user.getId());
		result.setEmail(user.getEmail());
		result.setIsProfessional(user.getIsProfessional());
		result.setFirstName(user.getFirstName());
		result.setPassword(user.getPassword());
		result.setLastName(user.getLastName());
		result.setTelephone(user.getTelephone());

		if (user.getWorkAreas() != null) {
			result.setWorkAreas(workAreaTransformation.transformToApi(user
					.getWorkAreas()));
		}

		if (user.getCountry() != null) {
			result.setCountryCode(user.getCountry().getCode());
		}

		if (user.getUserWorkZones() != null) {
			result.setCityCodes(user.getUserWorkZones().stream()
					.map(uwz -> uwz.getCity().getCode())
					.collect(Collectors.toList()));
		}

		if (user.getUserRatePlan() != null) {
			result.setRatePlanCode(user.getUserRatePlan().getRatePlan().getCode());
		}

		result.setRole(user.getRole());

		return result;
	}

	public User updateFields(User user, UserDTO userDTO) {

		user.setEmail(userDTO.getEmail());
		user.setFirstName(userDTO.getFirstName());
		user.setLastName(userDTO.getLastName());
		user.setTelephone(userDTO.getTelephone());

		return user;
	}

	public User updateFields(User user, DashBoardUserDTO dashBoardUserDTO) {

		user.setEmail(dashBoardUserDTO.getEmail());
		user.setFirstName(dashBoardUserDTO.getFirstName());
		user.setLastName(dashBoardUserDTO.getLastName());
		user.setTelephone(dashBoardUserDTO.getTelephone());

		return user;
	}
}
