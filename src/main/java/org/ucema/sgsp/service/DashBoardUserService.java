package org.ucema.sgsp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.ucema.sgsp.api.dto.DashBoardUserDTO;
import org.ucema.sgsp.api.dto.UserDTO;
import org.ucema.sgsp.api.transformation.DashBoardUserTransformation;

@Service
public class DashBoardUserService {

	@Autowired
	private DashBoardUserTransformation dashBoardUserTransformation;
	@Autowired
	private UserService userService;
	
	public DashBoardUserDTO getDashBoardUser(String username){
		UserDTO user = userService.findByEmail(username);
		return dashBoardUserTransformation.transformToApi(user);
	}
}
