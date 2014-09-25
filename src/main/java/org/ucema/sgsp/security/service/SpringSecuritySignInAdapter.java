package org.ucema.sgsp.security.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.web.SignInAdapter;
import org.springframework.web.context.request.NativeWebRequest;
import org.ucema.sgsp.api.dto.UserDTO;
import org.ucema.sgsp.security.util.SecurityUtil;
import org.ucema.sgsp.service.UserService;

public class SpringSecuritySignInAdapter implements SignInAdapter {
	
	private static final Logger LOGGER = LoggerFactory
			.getLogger(SpringSecuritySignInAdapter.class);
	
	private UserService userService;
	
	public SpringSecuritySignInAdapter(UserService userService) {
		this.userService = userService;
	}

	public String signIn(String localUserId, Connection<?> connection,
			NativeWebRequest request) {
		
		UserDTO user = userService.findByEmail(localUserId);
		
		// Logs the user in.
		SecurityUtil.logInUser(user);
		LOGGER.debug("User {} has been signed in", user);		
		
		return null;
	}
}
