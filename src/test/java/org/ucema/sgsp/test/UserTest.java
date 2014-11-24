package org.ucema.sgsp.test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.ucema.sgsp.BaseTest;
import org.ucema.sgsp.api.dto.RegistrationDTO;
import org.ucema.sgsp.api.dto.UserDTO;
import org.ucema.sgsp.exception.DuplicateEmailException;
import org.ucema.sgsp.persistence.model.RatePlan;
import org.ucema.sgsp.persistence.model.UserRatePlan;
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
	
	@Test
	public void getRatePlanCode(){
		
		List<UserRatePlan> userRatePlans = new ArrayList<UserRatePlan>();
		UserRatePlan userRatePlan = new UserRatePlan();
		userRatePlan.setCreatedAt(new DateTime(2014, 10, 10, 9, 29).toDate());
		RatePlan ratePlan = new RatePlan();
		ratePlan.setCode("XXX");
		userRatePlan.setRatePlan(ratePlan);
		
		userRatePlans.add(userRatePlan);
		
		Date validDate = new DateTime(2014, 10, 10, 9, 30).toDate();
		
		String ratePlanCode = User.getRatePlanCode(userRatePlans, validDate);
		Assert.assertNotNull(ratePlanCode);
		Assert.assertEquals("XXX", ratePlanCode);
	}
	
	@Test
	public void getRatePlanCode2(){
		
		List<UserRatePlan> userRatePlans = new ArrayList<UserRatePlan>();
		UserRatePlan userRatePlan = new UserRatePlan();
		userRatePlan.setCreatedAt(new DateTime(2014, 10, 10, 9, 31).toDate());
		RatePlan ratePlan = new RatePlan();
		ratePlan.setCode("XXX");
		userRatePlan.setRatePlan(ratePlan);
		
		userRatePlans.add(userRatePlan);
		
		Date validDate = new DateTime(2014, 10, 10, 9, 30).toDate();
		
		String ratePlanCode = User.getRatePlanCode(userRatePlans, validDate);
		Assert.assertNull(ratePlanCode);
	}	
	
	@Test
	public void getRatePlanCode3(){
		
		List<UserRatePlan> userRatePlans = new ArrayList<UserRatePlan>();
		
		UserRatePlan userRatePlan = new UserRatePlan();
		userRatePlan.setCreatedAt(new DateTime(2014, 10, 10, 9, 31).toDate());
		userRatePlan.setValidTo(new DateTime(2014, 10, 10, 9, 32).toDate());
		RatePlan ratePlan = new RatePlan();
		ratePlan.setCode("XXX");
		userRatePlan.setRatePlan(ratePlan);
		
		userRatePlans.add(userRatePlan);
		
		UserRatePlan userRatePlan2 = new UserRatePlan();
		userRatePlan2.setCreatedAt(new DateTime(2014, 10, 10, 9, 34).toDate());
		RatePlan ratePlan2 = new RatePlan();
		ratePlan2.setCode("XX1");
		userRatePlan2.setRatePlan(ratePlan2);
		
		userRatePlans.add(userRatePlan2);		
		
		Date validDate = new DateTime(2014, 10, 10, 9, 36).toDate();
		
		String ratePlanCode = User.getRatePlanCode(userRatePlans, validDate);
		Assert.assertNotNull(ratePlanCode);
		Assert.assertEquals("XX1", ratePlanCode);
	}
	
	@Test
	public void getRatePlanCode4(){
		
		List<UserRatePlan> userRatePlans = new ArrayList<UserRatePlan>();
		
		UserRatePlan userRatePlan = new UserRatePlan();
		userRatePlan.setCreatedAt(new DateTime(2014, 10, 10, 9, 31).toDate());
		userRatePlan.setValidTo(new DateTime(2014, 10, 10, 9, 33).toDate());
		RatePlan ratePlan = new RatePlan();
		ratePlan.setCode("XXX");
		userRatePlan.setRatePlan(ratePlan);
		
		userRatePlans.add(userRatePlan);
		
		UserRatePlan userRatePlan2 = new UserRatePlan();
		userRatePlan2.setCreatedAt(new DateTime(2014, 10, 10, 9, 34).toDate());
		RatePlan ratePlan2 = new RatePlan();
		ratePlan2.setCode("XX1");
		userRatePlan2.setRatePlan(ratePlan2);
		
		userRatePlans.add(userRatePlan2);		
		
		Date validDate = new DateTime(2014, 10, 10, 9, 32).toDate();
		
		String ratePlanCode = User.getRatePlanCode(userRatePlans, validDate);
		Assert.assertNotNull(ratePlanCode);
		Assert.assertEquals("XXX", ratePlanCode);
	}	
}
