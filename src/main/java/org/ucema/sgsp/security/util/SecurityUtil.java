package org.ucema.sgsp.security.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.ucema.sgsp.security.model.CustomUserDetails;
import org.ucema.sgsp.security.model.User;

public class SecurityUtil {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(SecurityUtil.class);

	public static void logInUser(User user) {
		LOGGER.info("Logging in user: {}", user);

		CustomUserDetails userDetails = CustomUserDetails
				.getBuilder()
				.firstName(user.getFirstName())
				.id(user.getId())
				.lastName(user.getLastName())
				.password(user.getPassword())
				.role(user.getRole())
				.socialSignInProvider(user.getSignInProvider())
				.username(user.getEmail())
				.telephone(user.getTelephone())
				.isProfessional(user.getIsProfessional())
				.workAreasCodes(user.getWorkAreas())
				.country(user.getCountry().getCode())
				.cityCodes(user.getUserWorkZones())
				.ratePlanCode(
						user.getUserRatePlan() != null ? user.getUserRatePlan()
								.getRatePlan().getCode() : null).build();

		LOGGER.debug("Logging in principal: {}", userDetails);

		Authentication authentication = new UsernamePasswordAuthenticationToken(
				userDetails, null, userDetails.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(authentication);

		LOGGER.info("User: {} has been logged in.", userDetails);
	}
}
