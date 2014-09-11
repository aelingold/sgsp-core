package org.ucema.sgsp.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.ucema.sgsp.api.dto.UserNotifyDTO;
import org.ucema.sgsp.api.transformation.UserNotifyTransformation;
import org.ucema.sgsp.persistence.UserNotifyDAO;
import org.ucema.sgsp.persistence.model.UserNotify;

@Service
public class UserNotifyService {

	@Autowired
	private UserNotifyTransformation userNotifyTransformation;
	
	@Autowired
	private UserNotifyDAO userNotifyDAO;

	@Transactional
	public List<UserNotifyDTO> list() {
		return userNotifyTransformation.transformToApi(userNotifyDAO.findAll());
	}

	@Transactional
	public UserNotifyDTO saveOrUpdate(UserNotifyDTO userNotify) {
		UserNotify response = userNotifyDAO.save(userNotifyTransformation.transformToModel(userNotify));
		userNotify.setId(response.getId());
		return userNotify;
	}

	@Transactional
	public void delete(UserNotifyDTO userNotify) {
		userNotifyDAO.delete(userNotifyTransformation.transformToModel(userNotify));
	}
	
	@Transactional
	public void delete(Long id) {
		UserNotify userNotify = userNotifyDAO.getOne(id);
		if (userNotify == null){
			throw new RuntimeException("userNotify not found");
		}				
		userNotifyDAO.delete(userNotify);
	}	
	
	@Transactional
	public UserNotifyDTO get(Long id){
		UserNotify userNotify = userNotifyDAO.getOne(id);
		if (userNotify == null){
			throw new RuntimeException("userNotify not found");
		}			
		return userNotifyTransformation.transformToApi(userNotify);
	}	
}
