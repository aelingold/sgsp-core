package org.ucema.sgsp.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.ucema.sgsp.api.dto.UserWorkRateSummarizeDTO;
import org.ucema.sgsp.api.transformation.UserWorkRateSummarizeTransformation;
import org.ucema.sgsp.persistence.UserWorkRateSummarizeDAO;
import org.ucema.sgsp.persistence.model.UserWorkRateRatingType;
import org.ucema.sgsp.persistence.model.UserWorkRateSummarize;

@Service
public class UserWorkRateSummarizeService {

	@Autowired
	private UserWorkRateSummarizeTransformation userWorkRateSummarizeTransformation;

	@Autowired
	private UserWorkRateSummarizeDAO userWorkRateSummarizeDAO;

	@Transactional
	public Map<String, Long> userWorkRateSummarizesMap(Set<String> usernames) {

		Map<String, Long> result = new HashMap<String, Long>();

		List<UserWorkRateSummarizeDTO> userWorkRateSummarizes = findByUser_Email(usernames);
		userWorkRateSummarizes.forEach(uwrs -> {
			result.put(uwrs.getUsername(),
					uwrs.getNegativeQuantity() + uwrs.getNeutralQuantity()
							+ uwrs.getPositiveQuantity());
		});

		return result;
	}

	@Transactional
	public List<UserWorkRateSummarizeDTO> findByUser_Email(Set<String> usernames) {
		return userWorkRateSummarizeTransformation
				.transformToApi(userWorkRateSummarizeDAO
						.findByUser_Email(usernames));
	}

	@Transactional
	public List<UserWorkRateSummarizeDTO> list() {
		return userWorkRateSummarizeTransformation
				.transformToApi(userWorkRateSummarizeDAO.findAll());
	}

	@Transactional
	public UserWorkRateSummarizeDTO findByUser_Email(String username) {
		return userWorkRateSummarizeTransformation
				.transformToApi(userWorkRateSummarizeDAO
						.findByUser_Email(username));
	}

	@Transactional
	public void increment(String username, UserWorkRateRatingType ratingType) {
		UserWorkRateSummarizeDTO userWorkRateSummarize = findByUser_Email(username);

		Long quantity = 0L;
		if (ratingType.equals(UserWorkRateRatingType.NEGATIVE)) {
			quantity = userWorkRateSummarize.getNegativeQuantity();
		} else if (ratingType.equals(UserWorkRateRatingType.POSITIVE)) {
			quantity = userWorkRateSummarize.getPositiveQuantity();
		} else {
			quantity = userWorkRateSummarize.getNeutralQuantity();
		}

		if (quantity == null) {
			quantity = 1L;
		} else {
			quantity++;
		}

		if (ratingType.equals(UserWorkRateRatingType.NEGATIVE)) {
			userWorkRateSummarize.setNegativeQuantity(quantity);
		} else if (ratingType.equals(UserWorkRateRatingType.POSITIVE)) {
			userWorkRateSummarize.setPositiveQuantity(quantity);
		} else {
			userWorkRateSummarize.setNeutralQuantity(quantity);
		}

		saveOrUpdate(userWorkRateSummarize);
	}

	@Transactional
	public UserWorkRateSummarizeDTO saveOrUpdate(
			UserWorkRateSummarizeDTO userWorkRateSummarize) {
		UserWorkRateSummarize response = userWorkRateSummarizeDAO
				.save(userWorkRateSummarizeTransformation
						.transformToModel(userWorkRateSummarize));
		userWorkRateSummarize.setId(response.getId());
		return userWorkRateSummarize;
	}

	@Transactional
	public void delete(UserWorkRateSummarizeDTO userWorkRateSummarize) {
		userWorkRateSummarizeDAO.delete(userWorkRateSummarizeTransformation
				.transformToModel(userWorkRateSummarize));
	}

	@Transactional
	public void delete(Long id) {
		UserWorkRateSummarize userWorkRateSummarize = userWorkRateSummarizeDAO
				.getOne(id);
		if (userWorkRateSummarize == null) {
			throw new RuntimeException("userWorkRateSummarize not found");
		}
		userWorkRateSummarizeDAO.delete(userWorkRateSummarize);
	}

	@Transactional
	public UserWorkRateSummarizeDTO get(Long id) {
		UserWorkRateSummarize userWorkRateSummarize = userWorkRateSummarizeDAO
				.getOne(id);
		if (userWorkRateSummarize == null) {
			throw new RuntimeException("userWorkRateSummarize not found");
		}
		return userWorkRateSummarizeTransformation
				.transformToApi(userWorkRateSummarize);
	}
}
