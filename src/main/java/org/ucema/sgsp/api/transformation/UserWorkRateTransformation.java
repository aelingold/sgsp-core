package org.ucema.sgsp.api.transformation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.ucema.sgsp.api.dto.UserWorkRateDTO;
import org.ucema.sgsp.persistence.model.Quote;
import org.ucema.sgsp.persistence.model.UserWorkRate;
import org.ucema.sgsp.persistence.model.UserWorkRateStatusType;
import org.ucema.sgsp.security.model.User;
import org.ucema.sgsp.service.UserService;

@Component
public class UserWorkRateTransformation {

	@Autowired
	private UserTransformation userTransformation;
	@Autowired
	private UserService userService;

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
			result.setUsername(userWorkRate.getUser().getEmail());
		}
		result.setQuoteId(userWorkRate.getQuote().getId());
		result.setQuoteUsername(userWorkRate.getQuote().getUser().getEmail());
		result.setStatusType(userWorkRate.getStatusType().name());
		result.setRecommended(userWorkRate.getRecommended());

		return result;
	}

	public UserWorkRate transformToModel(UserWorkRateDTO userWorkRate) {
		UserWorkRate result = new UserWorkRate();

		result.setId(userWorkRate.getId());
		result.setComment(userWorkRate.getComment());
		result.setRate(userWorkRate.getRate());
		if (userWorkRate.getUsername() != null) {
			result.setUser(new User(userService.findByEmail(
					userWorkRate.getUsername()).getId()));
		}
		result.setQuote(new Quote(userWorkRate.getQuoteId()));
		result.setStatusType(UserWorkRateStatusType.valueOf(userWorkRate.getStatusType()));
		result.setRecommended(userWorkRate.getRecommended());

		return result;
	}
}
