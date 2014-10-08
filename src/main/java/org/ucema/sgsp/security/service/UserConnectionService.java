package org.ucema.sgsp.security.service;

import java.util.List;
import java.util.Set;

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

	@Transactional
	public Set<String> findUserIds(String providerId,
			Set<String> providerUserIds) {
		return userConnectionDAO.findUserIds(providerId, providerUserIds);
	}

	@Transactional
	public List<String> findUserIds(String providerId, String providerUserId) {
		return userConnectionDAO.findUserIds(providerId, providerUserId);
	}

	@Transactional
	public List<UserConnection> findAllConnections(String userId) {
		return userConnectionDAO.findAllConnections(userId);
	}

	@Transactional
	public List<UserConnection> findConnections(String userId, String providerId) {
		return userConnectionDAO.findConnections(userId, providerId);
	}

	@Transactional
	public UserConnection getConnection(String userId, String providerId,
			String providerUserId) {
		return userConnectionDAO.getConnection(userId, providerId,
				providerUserId);
	}

	@Transactional
	public void removeConnection(String userId, String providerId,
			String providerUserId) {

		UserConnection connection = getConnection(userId, providerId,
				providerUserId);

		userConnectionDAO.delete(connection);
	}

	@Transactional
	public void removeConnections(String userId, String providerId) {

		List<UserConnection> connections = findConnections(userId, providerId);

		userConnectionDAO.delete(connections);
	}

	@Transactional
	public void updateConnection(UserConnection userConnection) {
		userConnectionDAO.save(userConnection);
	}

	@Transactional
	public void addConnection(UserConnection userConnection) {
		userConnectionDAO.save(userConnection);
	}

	@Transactional
	public Integer rank(String userId, String providerId) {
		Object object = userConnectionDAO.rank(userId, providerId);
		return (Integer) object;
	}
}
