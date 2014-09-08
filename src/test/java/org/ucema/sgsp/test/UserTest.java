package org.ucema.sgsp.test;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.ucema.sgsp.BaseTest;
import org.ucema.sgsp.api.dto.UserDTO;
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
		Boolean isProfessional = false;
		Role role = Role.ROLE_USER;

		UserDTO user = UserDTO.newInstance().withId(id).withEmail(email)
				.withFirstName(firstName).withLastName(lastName)
				.withIsProfessionl(isProfessional).withTelephone(telephone).withRole(role)
				.build();

		UserDTO response = userService.saveOrUpdate(user);

		UserDTO userRetrieved = userService.get(response.getId());
		Assert.assertNotNull(userRetrieved);

		Assert.assertEquals(email, userRetrieved.getEmail());
	}
}
