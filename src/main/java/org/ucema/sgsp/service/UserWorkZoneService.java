package org.ucema.sgsp.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.ucema.sgsp.api.dto.UserWorkZoneDTO;
import org.ucema.sgsp.api.transformation.UserWorkZoneTransformation;
import org.ucema.sgsp.persistence.UserWorkZoneDAO;
import org.ucema.sgsp.persistence.model.UserWorkZone;

@Service
public class UserWorkZoneService {

	@Autowired
	private UserWorkZoneTransformation userWorkZoneTransformation;

	@Autowired
	private UserWorkZoneDAO userWorkZoneDAO;

	@Transactional
	public List<UserWorkZoneDTO> list() {
		return userWorkZoneTransformation.transformToApi(userWorkZoneDAO
				.findAll());
	}

	@Transactional
	public UserWorkZoneDTO saveOrUpdate(UserWorkZoneDTO userWorkZone) {
		UserWorkZone response = userWorkZoneDAO.save(userWorkZoneTransformation
				.transformToModel(userWorkZone));
		userWorkZone.setId(response.getId());
		return userWorkZone;
	}

	@Transactional
	public void delete(UserWorkZoneDTO userWorkZone) {
		userWorkZoneDAO.delete(userWorkZoneTransformation
				.transformToModel(userWorkZone));
	}

	@Transactional
	public void delete(Long id) {
		UserWorkZone userWorkZone = userWorkZoneDAO.getOne(id);
		if (userWorkZone == null) {
			throw new RuntimeException("userWorkZone not found");
		}
		userWorkZoneDAO.delete(userWorkZone);
	}

	@Transactional
	public UserWorkZoneDTO get(Long id) {
		UserWorkZone userWorkZone = userWorkZoneDAO.getOne(id);
		if (userWorkZone == null) {
			throw new RuntimeException("userWorkZone not found");
		}
		return userWorkZoneTransformation.transformToApi(userWorkZone);
	}
}
