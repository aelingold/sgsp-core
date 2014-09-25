package org.ucema.sgsp.security.util;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.ucema.sgsp.api.dto.UserDTO;
import org.ucema.sgsp.security.model.CustomUserDetails;
import org.ucema.sgsp.security.model.Role;
import org.ucema.sgsp.security.model.SocialMediaService;
import org.ucema.sgsp.security.model.User;

public class SecurityUtil {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(SecurityUtil.class);

	public static void logInUser(User user) {
		LOGGER.info("Logging in user: {}", user);

		CustomUserDetails userDetails = customUserDetails(
				user.getFirstName(),
				user.getId(),
				user.getLastName(),
				user.getPassword(),
				user.getRole(),
				user.getSignInProvider(),
				user.getEmail(),
				user.getTelephone(),
				user.getIsProfessional(),
				user.getWorkAreas().stream().map(wa -> wa.getCode())
						.collect(Collectors.toList()),
				user.getCountry().getCode(),
				user.getUserWorkZones().stream()
						.map(uwz -> uwz.getCity().getCode())
						.collect(Collectors.toList()),
				user.getUserRatePlan() != null ? user.getUserRatePlan()
						.getRatePlan().getCode() : null);

		LOGGER.debug("Logging in principal: {}", userDetails);

		Authentication authentication = new UsernamePasswordAuthenticationToken(
				userDetails, null, userDetails.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(authentication);

		LOGGER.info("User: {} has been logged in.", userDetails);
	}

	public static void logInUser(UserDTO user) {
		LOGGER.info("Logging in user: {}", user);

		CustomUserDetails userDetails = customUserDetails(
				user.getFirstName(),
				user.getId(),
				user.getLastName(),
				user.getPassword(),
				user.getRole(),
				user.getSignInProvider() != null ? SocialMediaService
						.valueOf(user.getSignInProvider()) : null,
				user.getEmail(),
				user.getTelephone(),
				user.getIsProfessional(),
				user.getWorkAreas().stream().map(wa -> wa.getCode())
						.collect(Collectors.toList()), user.getCountryCode(),
				user.getCityCodes(), user.getRatePlanCode());

		LOGGER.debug("Logging in principal: {}", userDetails);

		Authentication authentication = new UsernamePasswordAuthenticationToken(
				userDetails, null, userDetails.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(authentication);

		LOGGER.info("User: {} has been logged in.", userDetails);
	}

	public static CustomUserDetails customUserDetails(String firstName,
			Long userId, String lastName, String password, Role role,
			SocialMediaService socialMediaService, String email,
			String telephone, Boolean isProfessional,
			List<String> workAreaCodes, String countryCode,
			List<String> cityCodes, String ratePlanCode) {

		return CustomUserDetails.getBuilder().firstName(firstName).id(userId)
				.lastName(lastName).password(password).role(role)
				.socialSignInProvider(socialMediaService).username(email)
				.telephone(telephone).isProfessional(isProfessional)
				.workAreasCodes(workAreaCodes).country(countryCode)
				.cityCodes(cityCodes).ratePlanCode(ratePlanCode).build();
	}
}
