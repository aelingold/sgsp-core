package org.ucema.sgsp.api.transformation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.ucema.sgsp.api.dto.UserWorkRateDTO;
import org.ucema.sgsp.persistence.model.UserWorkRate;

@Component
public class UserWorkRateTransformation {

	@Autowired
	private UserTransformation userTransformation;

	public List<UserWorkRateDTO> transformToApi(List<UserWorkRate> userWorkRates) {
		List<UserWorkRateDTO> result = new ArrayList<UserWorkRateDTO>();

		for (UserWorkRate userWorkRate : userWorkRates) {
			result.add(transformToApi(userWorkRate));
		}

		return result;
	}

	public List<UserWorkRate> transformToModel(
			List<UserWorkRateDTO> userWorkRates) {
		List<UserWorkRate> result = new ArrayList<UserWorkRate>();

		for (UserWorkRateDTO userWorkRate : userWorkRates) {
			result.add(transformToModel(userWorkRate));
		}

		return result;
	}

	public UserWorkRateDTO transformToApi(UserWorkRate userWorkRate) {
		UserWorkRateDTO result = new UserWorkRateDTO();

		result.setId(userWorkRate.getId());
		result.setComment(userWorkRate.getComment());
		result.setRate(userWorkRate.getRate());
		if (userWorkRate.getUser() != null) {
			result.setUser(userTransformation.transformToApi(userWorkRate
					.getUser()));
		}

		return result;
	}

	public UserWorkRate transformToModel(UserWorkRateDTO userWorkRate) {
		UserWorkRate result = new UserWorkRate();

		result.setId(userWorkRate.getId());
		result.setComment(userWorkRate.getComment());
		result.setRate(userWorkRate.getRate());
		if (userWorkRate.getUser() != null) {
			result.setUser(userTransformation.transformToModel(userWorkRate
					.getUser()));
		}

		return result;
	}
}
