package org.ucema.sgsp.service;

import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.ucema.sgsp.api.dto.UserWorkRateDTO;
import org.ucema.sgsp.api.transformation.UserWorkRateTransformation;
import org.ucema.sgsp.persistence.UserWorkRateDAO;
import org.ucema.sgsp.persistence.model.UserWorkRate;

@Service
public class UserWorkRateService {

	@Autowired
	private UserWorkRateTransformation userWorkRateTransformation;

	@Autowired
	private UserWorkRateDAO userWorkRateDAO;

	@Transactional
	public List<UserWorkRateDTO> findByUser_Email(Set<String> usernames) {
		return userWorkRateTransformation.transformToApi(userWorkRateDAO
				.findByUser_Email(usernames));
	}

	@Transactional
	public List<UserWorkRateDTO> list() {
		return userWorkRateTransformation.transformToApi(userWorkRateDAO
				.findAll());
	}

	@Transactional
	public void saveOrUpdate(UserWorkRateDTO userWorkRate) {
		userWorkRateDAO.save(userWorkRateTransformation
				.transformToModel(userWorkRate));
	}

	@Transactional
	public void delete(UserWorkRateDTO userWorkRate) {
		userWorkRateDAO.delete(userWorkRateTransformation
				.transformToModel(userWorkRate));
	}

	@Transactional
	public void delete(Long id) {
		UserWorkRate userWorkRate = userWorkRateDAO.getOne(id);
		if (userWorkRate == null) {
			throw new RuntimeException("userWorkRate not found");
		}
		userWorkRateDAO.delete(userWorkRate);
	}

	@Transactional
	public UserWorkRateDTO get(Long id) {
		UserWorkRate userWorkRate = userWorkRateDAO.getOne(id);
		if (userWorkRate == null) {
			throw new RuntimeException("userWorkRate not found");
		}
		return userWorkRateTransformation.transformToApi(userWorkRate);
	}
}
