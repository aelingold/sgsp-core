package org.ucema.sgsp.security.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.ucema.sgsp.api.dto.UserTokenDTO;
import org.ucema.sgsp.api.transformation.UserTokenTransformation;
import org.ucema.sgsp.security.model.UserToken;
import org.ucema.sgsp.security.persistence.UserTokenDAO;

@Service
public class UserTokenService {

	@Autowired
	private UserTokenTransformation userTokenTransformation;
	
	@Autowired
	private UserTokenDAO userTokenDAO;

	@Transactional
	public void update(Long id, Boolean valid) {
		UserToken userToken = userTokenDAO.getOne(id);
		if (userToken == null){
			throw new RuntimeException("userToken not found");
		}	
		
		userToken.setValid(valid);
		userTokenDAO.save(userToken);
	}	
	
	@Transactional
	public void delete(Long id) {
		UserToken userToken = userTokenDAO.getOne(id);
		if (userToken == null){
			throw new RuntimeException("userToken not found");
		}		
		userTokenDAO.delete(id);
	}	
	
	@Transactional
	public UserTokenDTO get(Long id){
		UserToken userToken = userTokenDAO.getOne(id);
		if (userToken == null){
			throw new RuntimeException("userToken not found");
		}		
		
		if (userToken.getValid()){
			return userTokenTransformation.transformToApi(userToken);	
		}else{
			return null;
		}
		
	}
	
	@Transactional
	public UserTokenDTO findByToken(String token){
		UserToken userToken = userTokenDAO.findByToken(token);		

		if (userToken.getValid()){
			return userTokenTransformation.transformToApi(userToken);	
		}else{
			return null;
		}
	}	
}
