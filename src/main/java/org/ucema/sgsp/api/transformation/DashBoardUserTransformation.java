package org.ucema.sgsp.api.transformation;

import org.springframework.stereotype.Component;
import org.ucema.sgsp.api.dto.DashBoardUserDTO;
import org.ucema.sgsp.api.dto.UserDTO;
import org.ucema.sgsp.security.model.CustomUserDetails;

@Component
public class DashBoardUserTransformation {

	public DashBoardUserDTO transformToApi(UserDTO user) {
		DashBoardUserDTO result = new DashBoardUserDTO();

		result.setEmail(user.getEmail());
		result.setFirstName(user.getFirstName());
		result.setPassword(user.getPassword());
		result.setLastName(user.getLastName());
		result.setTelephone(user.getTelephone());
		result.setIsProfessional(user.getIsProfessional());
		result.setRatePlanCode(user.getRatePlanCode());

		return result;
	}
	
	public DashBoardUserDTO transformToApi(CustomUserDetails user) {
		DashBoardUserDTO result = new DashBoardUserDTO();

		result.setEmail(user.getUsername());
		result.setFirstName(user.getFirstName());
		result.setPassword(user.getPassword());
		result.setLastName(user.getLastName());
		result.setTelephone(user.getTelephone());
		result.setIsProfessional(user.isProfessional());
		result.setRatePlanCode(user.getRatePlanCode());

		return result;
	}	
}
