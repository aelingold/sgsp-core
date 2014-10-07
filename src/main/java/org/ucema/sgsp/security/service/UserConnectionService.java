package org.ucema.sgsp.security.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.ucema.sgsp.security.model.UserConnection;
import org.ucema.sgsp.security.persistence.UserConnectionDAO;

@Service
public class UserConnectionService {

	@Autowired
	private UserConnectionDAO userConnectionDAO;

	@Transactional
	public UserConnection findByUserConnectionPK_ProviderUserId(
			String providerUserId) {
		return userConnectionDAO
				.findByUserConnectionPK_ProviderUserId(providerUserId);
	}
}
