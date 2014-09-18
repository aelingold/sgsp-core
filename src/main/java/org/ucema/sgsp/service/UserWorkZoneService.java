package org.ucema.sgsp.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.ucema.sgsp.api.dto.DashBoardConfigDTO;
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
	public List<UserWorkZoneDTO> list(String username) {
		return userWorkZoneTransformation.transformToApi(userWorkZoneDAO
				.findByUser_Email(username));
	}

	@Transactional
	public UserWorkZoneDTO saveOrUpdate(UserWorkZoneDTO userWorkZone) {
		UserWorkZone response = userWorkZoneDAO.save(userWorkZoneTransformation
				.transformToModel(userWorkZone));
		userWorkZone.setId(response.getId());
		return userWorkZone;
	}
	
	@Transactional
	public void save(DashBoardConfigDTO config, String username){
		deleteByUser_Email(username);

		List<UserWorkZoneDTO> userWorkZonesNew = new ArrayList<UserWorkZoneDTO>();

		config.getCityCodes().forEach(cc -> {
			UserWorkZoneDTO uwz = new UserWorkZoneDTO();
			uwz.setCityCode(cc);
			uwz.setUsername(username);
			userWorkZonesNew.add(uwz);
		});
		saveOrUpdate(userWorkZonesNew);		
	}
	
	public Long deleteByUser_Email(String username){
		return userWorkZoneDAO.deleteByUser_Email(username);
	}

	@Transactional
	public List<UserWorkZoneDTO> saveOrUpdate(
			List<UserWorkZoneDTO> userWorkZonesDTO) {

		List<UserWorkZone> userWorkZones = userWorkZoneTransformation
				.transformToModel(userWorkZonesDTO);

		List<UserWorkZone> userWorkZonesSaved = userWorkZoneDAO
				.save(userWorkZones);

		return userWorkZoneTransformation.transformToApi(userWorkZonesSaved);
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
