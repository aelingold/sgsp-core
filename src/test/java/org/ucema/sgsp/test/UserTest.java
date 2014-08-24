package org.ucema.sgsp.test;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.ucema.sgsp.BaseTest;
import org.ucema.sgsp.api.dto.UserDTO;
import org.ucema.sgsp.api.dto.UserWorkRateDTO;
import org.ucema.sgsp.api.dto.WorkAreaDTO;
import org.ucema.sgsp.security.model.Role;
import org.ucema.sgsp.service.UserService;

public class UserTest extends BaseTest {

	@Autowired
	private UserService userService;
	
	@Test
	public void insertAndVerifyUser() {
		Long id = null;
		String firstName = "firstName";
		String lastName = "lastName";
		String email = "email";
		String telephone = "telephone";
		String password = "password";
		List<WorkAreaDTO> workAreas = null;
		List<UserWorkRateDTO> userWorkRates = null;
		Boolean isProfessional = false;		
		Role role = Role.ROLE_USER;
		
		UserDTO user = new UserDTO(id, firstName, lastName, email, telephone, password, workAreas, userWorkRates, isProfessional,role);
		
		UserDTO response = userService.saveOrUpdate(user);
		
		UserDTO userRetrieved = userService.get(response.getId());
		Assert.assertNotNull(userRetrieved);
		
		Assert.assertEquals(email, userRetrieved.getEmail());
	}
}
