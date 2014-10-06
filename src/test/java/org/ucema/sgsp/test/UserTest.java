package org.ucema.sgsp.test;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.ucema.sgsp.BaseTest;
import org.ucema.sgsp.api.dto.RegistrationDTO;
import org.ucema.sgsp.api.dto.UserDTO;
import org.ucema.sgsp.exception.DuplicateEmailException;
import org.ucema.sgsp.security.model.SocialMediaService;
import org.ucema.sgsp.security.model.User;
import org.ucema.sgsp.service.UserService;

public class UserTest extends BaseTest {

	@Autowired
	private UserService userService;

	@Test
	public void insertAndVerifyUser() throws DuplicateEmailException {
		String firstName = "firstName";
		String lastName = "lastName";
		String email = "email";
		String telephone = "telephone";
		Boolean isProfessional = false;
		String password = "abc345";		
		String countryCode = "AR";
		
		RegistrationDTO userAccountData = RegistrationDTO.newInstance().withEmail(email)
		.withFirstName(firstName).withLastName(lastName).withPassword(password)
		.withIsProfessional(isProfessional).withTelephone(telephone).withCountryCode(countryCode)
		.build();
		User response = userService.registerNewUserAccount(userAccountData);

		UserDTO userRetrieved = userService.get(response.getId());

		Assert.assertNull(userRetrieved);
	}
	
	@Test
	public void insertAndVerifyUser2() throws DuplicateEmailException {
		String firstName = "firstName";
		String lastName = "lastName";
		String email = "email";
		String telephone = "telephone";
		Boolean isProfessional = false;
		String password = "abc345";		
		String countryCode = "AR";		
		SocialMediaService signInProvider = SocialMediaService.FACEBOOK;
		
		RegistrationDTO userAccountData = RegistrationDTO.newInstance().withEmail(email)
		.withFirstName(firstName).withLastName(lastName).withPassword(password)
		.withIsProfessional(isProfessional).withTelephone(telephone).withCountryCode(countryCode).withSignInProvider(signInProvider)
		.build();
		User response = userService.registerNewUserAccount(userAccountData);

		UserDTO userRetrieved = userService.get(response.getId());
		Assert.assertNotNull(userRetrieved);

		Assert.assertEquals(email, userRetrieved.getEmail());
	}	
}
