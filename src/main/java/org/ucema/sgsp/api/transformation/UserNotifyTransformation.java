package org.ucema.sgsp.api.transformation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.ucema.sgsp.api.dto.UserNotifyDTO;
import org.ucema.sgsp.persistence.model.Order;
import org.ucema.sgsp.persistence.model.UserNotify;
import org.ucema.sgsp.persistence.model.UserNotifyType;
import org.ucema.sgsp.security.model.User;
import org.ucema.sgsp.service.UserService;

@Component
public class UserNotifyTransformation {

	@Autowired
	private OrderTransformation orderTransformation;
	@Autowired
	private UserService userService;

	public List<UserNotifyDTO> transformToApi(List<UserNotify> userNotifies) {
		List<UserNotifyDTO> result = new ArrayList<UserNotifyDTO>();

		for (UserNotify userNotify : userNotifies) {
			result.add(transformToApi(userNotify));
		}

		return result;
	}

	public List<UserNotify> transformToModel(List<UserNotifyDTO> userNotifies) {
		List<UserNotify> result = new ArrayList<UserNotify>();

		for (UserNotifyDTO userNotify : userNotifies) {
			result.add(transformToModel(userNotify));
		}

		return result;
	}

	public UserNotifyDTO transformToApi(UserNotify userNotify) {
		UserNotifyDTO result = new UserNotifyDTO();

		result.setId(userNotify.getId());
		if (userNotify.getOrder() != null) {
			result.setOrderId(userNotify.getOrder().getId());
		}
		result.setType(userNotify.getType().name());
		if (userNotify.getUser() != null) {
			result.setUsername(userNotify.getUser().getEmail());
		}

		return result;
	}

	public UserNotify transformToModel(UserNotifyDTO userNotify) {
		UserNotify result = new UserNotify();

		result.setId(userNotify.getId());
		if (userNotify.getOrderId() != null) {
			result.setOrder(new Order(userNotify.getOrderId()));
		}
		result.setType(UserNotifyType.valueOf(userNotify.getType()));
		if (userNotify.getUsername() != null) {
			result.setUser(new User(userService.findByEmail(
					userNotify.getUsername()).getId()));
		}

		return result;
	}
}
