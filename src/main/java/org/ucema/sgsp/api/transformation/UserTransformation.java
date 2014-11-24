package org.ucema.sgsp.api.transformation;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.ucema.sgsp.api.dto.DashBoardUserDTO;
import org.ucema.sgsp.api.dto.UserDTO;
import org.ucema.sgsp.persistence.model.UserRatePlan;
import org.ucema.sgsp.persistence.model.UserWorkArea;
import org.ucema.sgsp.persistence.model.WorkArea;
import org.ucema.sgsp.security.model.User;
import org.ucema.sgsp.service.CountryService;
import org.ucema.sgsp.service.UserService;
import org.ucema.sgsp.service.WorkAreaService;

@Component
public class UserTransformation {

	@Autowired
	private UserWorkRateTransformation userWorkRateTransformation;
	@Autowired
	private WorkAreaTransformation workAreaTransformation;
	@Autowired
	private CountryService countryService;
	@Autowired
	private WorkAreaService workAreaService;
	@Autowired
	private UserService userService;

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

		if (user.getSignInProvider() != null) {
			result.setSignInProvider(user.getSignInProvider().name());
		}

		if (user.getUserWorkAreas() != null && !user.getUserWorkAreas().isEmpty()) {
			result.setWorkAreas(workAreaTransformation.transformToApi(user
					.getUserWorkAreas()));
		}

		if (user.getCountry() != null) {
			result.setCountryCode(user.getCountry().getCode());
		}

		if (user.getUserWorkZones() != null && !user.getUserWorkZones().isEmpty()) {
			result.setCityCodes(user.getUserWorkZones().stream()
					.map(uwz -> uwz.getCity().getCode())
					.collect(Collectors.toList()));
		}

		if (user.getUserRatePlans() != null && !user.getUserRatePlans().isEmpty()) {
			
			UserRatePlan validUserRatePlan = User.getValidUserRatePlan(user
					.getUserRatePlans());
			
			result.setRatePlanCode(validUserRatePlan.getRatePlan().getCode());
			result.setRatePlanValidFrom(validUserRatePlan.getCreatedAt());
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

	public User updateUserWorkAreas(User user, DashBoardUserDTO dashBoardUserDTO) {

		user.getUserWorkAreas().addAll(
				buildUserWorkAreas(dashBoardUserDTO.getEmail(),
						dashBoardUserDTO.getWorkAreaCodes()));

		return user;
	}

	private List<UserWorkArea> buildUserWorkAreas(String username,
			List<String> workAreaCodes) {

		List<UserWorkArea> userWorkAreas = new ArrayList<UserWorkArea>();

		for (String workAreaCode : workAreaCodes) {
			UserWorkArea userWorkArea = new UserWorkArea();

			userWorkArea.setUser(new User(userService.findByEmail(username)
					.getId()));
			userWorkArea.setWorkArea(new WorkArea(workAreaService.findByCode(
					workAreaCode).getId()));

			userWorkAreas.add(userWorkArea);
		}

		return userWorkAreas;
	}
}
