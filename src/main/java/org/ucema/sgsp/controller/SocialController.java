package org.ucema.sgsp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.ucema.sgsp.api.dto.UserDTO;
import org.ucema.sgsp.security.model.UserConnection;
import org.ucema.sgsp.security.service.UserConnectionService;
import org.ucema.sgsp.service.UserService;

@Controller
public class SocialController {

	@Autowired
	private ConnectionRepository connectionRepository;
	@Autowired
	private UserConnectionService userConnectionService;
	@Autowired
	private UserService userService;

	@RequestMapping(value = "/social/friends", method = RequestMethod.GET)
	public @ResponseBody void socialFriends() {
		
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		
		String email = auth.getName();
		
		UserDTO user = userService.findByEmail(email);
		
		Connection<Facebook> connection = connectionRepository
				.findPrimaryConnection(Facebook.class);
		
		if (connection != null) {
			
			Facebook facebook = connection.getApi();
			
			List<String> friendIds = facebook.friendOperations().getFriendIds();
			friendIds.forEach(fi -> {
				UserConnection userConnection = userConnectionService.findByUserConnectionPK_ProviderUserId(fi);
				System.out.println(userConnection);
			});
		}
	}
}
