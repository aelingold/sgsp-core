package org.ucema.sgsp.api.transformation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.ucema.sgsp.api.dto.UserDTO;
import org.ucema.sgsp.security.model.User;

@Component
public class UserTransformation {

	@Autowired
	private UserWorkRateTransformation userWorkRateTransformation;
	@Autowired
	private WorkAreaTransformation workAreaTransformation;

	public List<UserDTO> transformToApi(List<User> users) {
		List<UserDTO> result = new ArrayList<UserDTO>();

		for (User user : users) {
			result.add(transformToApi(user));
		}

		return result;
	}

	public List<User> transformToModel(List<UserDTO> users) {
		List<User> result = new ArrayList<User>();

		for (UserDTO user : users) {
			result.add(transformToModel(user));
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
		if (user.getUserWorkRates() != null) {
			result.setUserWorkRates(userWorkRateTransformation
					.transformToApi(user.getUserWorkRates()));
		}
		if (user.getWorkAreas() != null) {
			result.setWorkAreas(workAreaTransformation.transformToApi(user
					.getWorkAreas()));
		}
		
		result.setRole(user.getRole());

		return result;
	}

	public User transformToModel(UserDTO user) {
		User result = new User();

		result.setId(user.getId());
		result.setEmail(user.getEmail());
		result.setIsProfessional(user.getIsProfessional());
		result.setFirstName(user.getFirstName());
		result.setPassword(user.getPassword());
		result.setLastName(user.getLastName());
		result.setTelephone(user.getTelephone());
		if (user.getUserWorkRates() != null) {
			result.setUserWorkRates(userWorkRateTransformation
					.transformToModel(user.getUserWorkRates()));
		}
		if (user.getWorkAreas() != null) {
			result.setWorkAreas(workAreaTransformation.transformToModel(user
					.getWorkAreas()));
		}
		
		result.setRole(user.getRole());

		return result;
	}
}
