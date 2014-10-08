package org.ucema.sgsp.security.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionData;
import org.springframework.social.connect.ConnectionFactory;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.ConnectionKey;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.connect.DuplicateConnectionException;
import org.springframework.social.connect.NoSuchConnectionException;
import org.springframework.social.connect.NotConnectedException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.ucema.sgsp.security.model.UserConnection;
import org.ucema.sgsp.security.model.UserConnectionPK;

public class CustomConnectionRepository implements ConnectionRepository {

	private final String userId;
	private final UserConnectionService userConnectionService;
	private final ConnectionFactoryLocator connectionFactoryLocator;
	private final TextEncryptor textEncryptor;

	public CustomConnectionRepository(String userId,
			UserConnectionService userConnectionService,
			ConnectionFactoryLocator connectionFactoryLocator,
			TextEncryptor textEncryptor) {
		this.userId = userId;
		this.userConnectionService = userConnectionService;
		this.connectionFactoryLocator = connectionFactoryLocator;
		this.textEncryptor = textEncryptor;
	}

	public MultiValueMap<String, Connection<?>> findAllConnections() {
		List<UserConnection> userConnections = userConnectionService
				.findAllConnections(userId);

		List<Connection<?>> resultList = mapUserConnections(userConnections);

		MultiValueMap<String, Connection<?>> connections = new LinkedMultiValueMap<String, Connection<?>>();
		Set<String> registeredProviderIds = connectionFactoryLocator
				.registeredProviderIds();
		for (String registeredProviderId : registeredProviderIds) {
			connections.put(registeredProviderId,
					Collections.<Connection<?>> emptyList());
		}
		for (Connection<?> connection : resultList) {
			String providerId = connection.getKey().getProviderId();
			if (connections.get(providerId).size() == 0) {
				connections.put(providerId, new LinkedList<Connection<?>>());
			}
			connections.add(providerId, connection);
		}
		return connections;
	}

	public List<Connection<?>> findConnections(String providerId) {
		List<UserConnection> userConnections = userConnectionService
				.findConnections(userId, providerId);

		return mapUserConnections(userConnections);
	}

	@SuppressWarnings("unchecked")
	public <A> List<Connection<A>> findConnections(Class<A> apiType) {
		List<?> connections = findConnections(getProviderId(apiType));
		return (List<Connection<A>>) connections;
	}

	public MultiValueMap<String, Connection<?>> findConnectionsToUsers(
			MultiValueMap<String, String> providerUsers) {
		// if (providerUsers == null || providerUsers.isEmpty()) {
		// throw new IllegalArgumentException(
		// "Unable to execute find: no providerUsers provided");
		// }
		// StringBuilder providerUsersCriteriaSql = new StringBuilder();
		// MapSqlParameterSource parameters = new MapSqlParameterSource();
		// parameters.addValue("userId", userId);
		// for (Iterator<Entry<String, List<String>>> it = providerUsers
		// .entrySet().iterator(); it.hasNext();) {
		// Entry<String, List<String>> entry = it.next();
		// String providerId = entry.getKey();
		// providerUsersCriteriaSql.append("providerId = :providerId_")
		// .append(providerId)
		// .append(" and providerUserId in (:providerUserIds_")
		// .append(providerId).append(")");
		// parameters.addValue("providerId_" + providerId, providerId);
		// parameters.addValue("providerUserIds_" + providerId,
		// entry.getValue());
		// if (it.hasNext()) {
		// providerUsersCriteriaSql.append(" or ");
		// }
		// }
		// List<Connection<?>> resultList = new NamedParameterJdbcTemplate(
		// jdbcTemplate).query(selectFromUserConnection()
		// + " where userId = :userId and " + providerUsersCriteriaSql
		// + " order by providerId, rank", parameters, connectionMapper);
		// MultiValueMap<String, Connection<?>> connectionsForUsers = new
		// LinkedMultiValueMap<String, Connection<?>>();
		// for (Connection<?> connection : resultList) {
		// String providerId = connection.getKey().getProviderId();
		// List<String> userIds = providerUsers.get(providerId);
		// List<Connection<?>> connections = connectionsForUsers
		// .get(providerId);
		// if (connections == null) {
		// connections = new ArrayList<Connection<?>>(userIds.size());
		// for (int i = 0; i < userIds.size(); i++) {
		// connections.add(null);
		// }
		// connectionsForUsers.put(providerId, connections);
		// }
		// String providerUserId = connection.getKey().getProviderUserId();
		// int connectionIndex = userIds.indexOf(providerUserId);
		// connections.set(connectionIndex, connection);
		// }
		// return connectionsForUsers;
		return null;
	}

	public Connection<?> getConnection(ConnectionKey connectionKey) {
		try {
			UserConnection userConnection = userConnectionService
					.getConnection(userId, connectionKey.getProviderId(),
							connectionKey.getProviderUserId());
			return mapUserConnection(userConnection);
		} catch (EmptyResultDataAccessException e) {
			throw new NoSuchConnectionException(connectionKey);
		}
	}

	@SuppressWarnings("unchecked")
	public <A> Connection<A> getConnection(Class<A> apiType,
			String providerUserId) {
		String providerId = getProviderId(apiType);
		return (Connection<A>) getConnection(new ConnectionKey(providerId,
				providerUserId));
	}

	@SuppressWarnings("unchecked")
	public <A> Connection<A> getPrimaryConnection(Class<A> apiType) {
		String providerId = getProviderId(apiType);
		Connection<A> connection = (Connection<A>) findPrimaryConnection(providerId);
		if (connection == null) {
			throw new NotConnectedException(providerId);
		}
		return connection;
	}

	@SuppressWarnings("unchecked")
	public <A> Connection<A> findPrimaryConnection(Class<A> apiType) {
		String providerId = getProviderId(apiType);
		return (Connection<A>) findPrimaryConnection(providerId);
	}

	@Transactional
	public void addConnection(Connection<?> connection) {
		try {
			ConnectionData data = connection.createData();
			int rank = userConnectionService.rank(userId, data.getProviderId());

			UserConnection userConnection = mapUserConnection(data, rank);
			userConnectionService.addConnection(userConnection);

		} catch (DuplicateKeyException e) {
			throw new DuplicateConnectionException(connection.getKey());
		}
	}

	@Transactional
	public void updateConnection(Connection<?> connection) {
		ConnectionData data = connection.createData();
		UserConnection userConnection = userConnectionService.getConnection(
				userId, data.getProviderId(), data.getProviderUserId());
		userConnection.setDisplayName(data.getDisplayName());
		userConnection.setProfileUrl(data.getProfileUrl());
		userConnection.setImageUrl(data.getImageUrl());
		userConnection.setAccessToken(encrypt(data.getAccessToken()));
		userConnection.setSecret(encrypt(data.getSecret()));
		userConnection.setRefreshToken(encrypt(data.getRefreshToken()));
		userConnection.setExpireTime(data.getExpireTime());
		userConnectionService.updateConnection(userConnection);
	}

	@Transactional
	public void removeConnections(String providerId) {
		userConnectionService.removeConnections(userId, providerId);
	}

	@Transactional
	public void removeConnection(ConnectionKey connectionKey) {
		userConnectionService.removeConnection(userId,
				connectionKey.getProviderId(),
				connectionKey.getProviderUserId());
	}

	private Connection<?> findPrimaryConnection(String providerId) {

		List<UserConnection> userConnections = userConnectionService
				.findConnections(userId, providerId);

		List<Connection<?>> connections = mapUserConnections(userConnections);
		if (connections.size() > 0) {
			return connections.get(0);
		} else {
			return null;
		}
	}

	private List<Connection<?>> mapUserConnections(
			List<UserConnection> userConnections) {

		List<Connection<?>> result = new ArrayList<>();

		for (UserConnection userConnection : userConnections) {
			ConnectionData connectionData = mapConnectionData(userConnection);
			ConnectionFactory<?> connectionFactory = connectionFactoryLocator
					.getConnectionFactory(connectionData.getProviderId());
			result.add(connectionFactory.createConnection(connectionData));
		}
		return result;
	}

	private Connection<?> mapUserConnection(UserConnection userConnection) {
		ConnectionData connectionData = mapConnectionData(userConnection);
		ConnectionFactory<?> connectionFactory = connectionFactoryLocator
				.getConnectionFactory(connectionData.getProviderId());
		return connectionFactory.createConnection(connectionData);
	}

	private UserConnection mapUserConnection(ConnectionData connectionData,
			Integer rank) {

		UserConnection userConnection = new UserConnection();
		userConnection.setAccessToken(encrypt(connectionData.getAccessToken()));
		userConnection.setDisplayName(connectionData.getDisplayName());
		userConnection.setExpireTime(connectionData.getExpireTime());
		userConnection.setImageUrl(connectionData.getImageUrl());
		userConnection.setProfileUrl(connectionData.getProfileUrl());
		userConnection.setRank(rank);
		userConnection
				.setRefreshToken(encrypt(connectionData.getRefreshToken()));
		userConnection.setSecret(encrypt(connectionData.getSecret()));

		UserConnectionPK userConnectionPK = new UserConnectionPK();
		userConnectionPK.setProviderId(connectionData.getProviderId());
		userConnectionPK.setProviderUserId(connectionData.getProviderUserId());
		userConnectionPK.setUserId(userId);
		userConnection.setUserConnectionPK(userConnectionPK);

		return userConnection;
	}

	private ConnectionData mapConnectionData(UserConnection userConnection) {

		return new ConnectionData(userConnection.getUserConnectionPK()
				.getProviderId(), userConnection.getUserConnectionPK()
				.getProviderUserId(), userConnection.getDisplayName(),
				userConnection.getProfileUrl(), userConnection.getImageUrl(),
				decrypt(userConnection.getAccessToken()),
				decrypt(userConnection.getSecret()),
				decrypt(userConnection.getRefreshToken()),
				expireTime(userConnection.getExpireTime()));
	}

	private String decrypt(String encryptedText) {
		return encryptedText != null ? textEncryptor.decrypt(encryptedText)
				: encryptedText;
	}

	private Long expireTime(long expireTime) {
		return expireTime == 0 ? null : expireTime;
	}

	private <A> String getProviderId(Class<A> apiType) {
		return connectionFactoryLocator.getConnectionFactory(apiType)
				.getProviderId();
	}

	private String encrypt(String text) {
		return text != null ? textEncryptor.encrypt(text) : text;
	}

}
