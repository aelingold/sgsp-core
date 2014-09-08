package org.ucema.sgsp.api.transformation;

import org.springframework.stereotype.Component;
import org.ucema.sgsp.api.dto.DashBoardUserDTO;
import org.ucema.sgsp.api.dto.UserDTO;

@Component
public class DashBoardUserTransformation {

	public DashBoardUserDTO transformToApi(UserDTO user) {
		DashBoardUserDTO result = new DashBoardUserDTO();

		result.setEmail(user.getEmail());
		result.setFirstName(user.getFirstName());
		result.setPassword(user.getPassword());
		result.setLastName(user.getLastName());
		result.setTelephone(user.getTelephone());

		return result;
	}
}
