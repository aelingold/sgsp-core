package org.ucema.sgsp.api.transformation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.ucema.sgsp.api.dto.UserWorkRateSummarizeDTO;
import org.ucema.sgsp.persistence.model.UserWorkRateSummarize;
import org.ucema.sgsp.security.model.User;
import org.ucema.sgsp.service.UserWorkRateSummarizeService;

@Component
public class UserWorkRateSummarizeTransformation {

	@Autowired
	private UserWorkRateSummarizeService userWorkRateSummarizeService;

	public List<UserWorkRateSummarizeDTO> transformToApi(
			List<UserWorkRateSummarize> userWorkRateSummarizes) {
		List<UserWorkRateSummarizeDTO> result = new ArrayList<UserWorkRateSummarizeDTO>();

		for (UserWorkRateSummarize userWorkRateSummarize : userWorkRateSummarizes) {
			result.add(transformToApi(userWorkRateSummarize));
		}

		return result;
	}

	public List<UserWorkRateSummarize> transformToModel(
			List<UserWorkRateSummarizeDTO> userWorkRateSummarizes) {
		List<UserWorkRateSummarize> result = new ArrayList<UserWorkRateSummarize>();

		for (UserWorkRateSummarizeDTO userWorkRateSummarize : userWorkRateSummarizes) {
			result.add(transformToModel(userWorkRateSummarize));
		}

		return result;
	}

	public UserWorkRateSummarizeDTO transformToApi(
			UserWorkRateSummarize userWorkRateSummarizes) {
		UserWorkRateSummarizeDTO result = new UserWorkRateSummarizeDTO();

		result.setId(userWorkRateSummarizes.getId());
		result.setCreatedAt(userWorkRateSummarizes.getCreatedAt());
		result.setUpdatedAt(userWorkRateSummarizes.getUpdatedAt());
		result.setNegativeQuantity(userWorkRateSummarizes.getNegativeQuantity());
		result.setNeutralQuantity(userWorkRateSummarizes.getNeutralQuantity());
		result.setPositiveQuantity(userWorkRateSummarizes.getPositiveQuantity());
		result.setUserId(userWorkRateSummarizes.getUser().getId());
		result.setUsername(userWorkRateSummarizes.getUser().getEmail());

		return result;
	}

	public UserWorkRateSummarize transformToModel(
			UserWorkRateSummarizeDTO userWorkRateSummarizes) {
		UserWorkRateSummarize result = new UserWorkRateSummarize();

		result.setId(userWorkRateSummarizes.getId());
		result.setCreatedAt(userWorkRateSummarizes.getCreatedAt());
		result.setUpdatedAt(userWorkRateSummarizes.getUpdatedAt());
		result.setNegativeQuantity(userWorkRateSummarizes.getNegativeQuantity());
		result.setNeutralQuantity(userWorkRateSummarizes.getNeutralQuantity());
		result.setPositiveQuantity(userWorkRateSummarizes.getPositiveQuantity());
		result.setUser(new User(userWorkRateSummarizes.getUserId()));

		return result;
	}
}
