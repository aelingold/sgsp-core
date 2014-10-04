package org.ucema.sgsp.api.transformation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.ucema.sgsp.api.dto.UserTokenDTO;
import org.ucema.sgsp.security.model.User;
import org.ucema.sgsp.security.model.UserToken;
import org.ucema.sgsp.service.UserService;

@Component
public class UserTokenTransformation {

	@Autowired
	private UserService userService;	
	
	public List<UserTokenDTO> transformToApi(
			List<UserToken> userTokens) {
		List<UserTokenDTO> result = new ArrayList<UserTokenDTO>();

		for (UserToken userToken : userTokens) {
			result.add(transformToApi(userToken));
		}

		return result;
	}

	public UserTokenDTO transformToApi(UserToken userToken) {
		UserTokenDTO result = new UserTokenDTO();

		result.setId(userToken.getId());
		result.setToken(userToken.getToken());
		result.setUsername(userToken.getUser().getEmail());
		result.setCreatedAt(userToken.getCreatedAt());
		result.setUpdatedAt(userToken.getUpdatedAt());
		result.setValid(userToken.getValid());

		return result;
	}

	public List<UserToken> transformToModel(
			List<UserTokenDTO> userTokens) {
		List<UserToken> result = new ArrayList<UserToken>();

		for (UserTokenDTO userToken : userTokens) {
			result.add(transformToModel(userToken));
		}

		return result;
	}

	public UserToken transformToModel(UserTokenDTO userToken) {
		UserToken result = new UserToken();

		result.setId(userToken.getId());
		result.setToken(userToken.getToken());

		if (userToken.getUsername() != null) {
			result.setUser(new User(userService.findByEmail(
					userToken.getUsername()).getId()));
		}

		result.setCreatedAt(userToken.getCreatedAt());
		result.setUpdatedAt(userToken.getUpdatedAt());
		result.setValid(userToken.getValid());

		return result;
	}
}
