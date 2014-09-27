package org.ucema.sgsp.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.ucema.sgsp.api.dto.UserWorkRateSummarizeDTO;
import org.ucema.sgsp.api.transformation.UserWorkRateSummarizeTransformation;
import org.ucema.sgsp.persistence.UserWorkRateSummarizeDAO;
import org.ucema.sgsp.persistence.model.UserWorkRateSummarize;

@Service
public class UserWorkRateSummarizeService {

	@Autowired
	private UserWorkRateSummarizeTransformation userWorkRateSummarizeTransformation;

	@Autowired
	private UserWorkRateSummarizeDAO userWorkRateSummarizeDAO;

	@Transactional
	public List<UserWorkRateSummarizeDTO> list() {
		return userWorkRateSummarizeTransformation
				.transformToApi(userWorkRateSummarizeDAO.findAll());
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
