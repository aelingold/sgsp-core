package org.ucema.sgsp.security.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionData;
import org.springframework.social.connect.ConnectionFactory;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.ConnectionKey;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.connect.NotConnectedException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.MultiValueMap;
import org.ucema.sgsp.security.model.UserConnection;

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
//		List<Connection<?>> resultList = jdbcTemplate.query(
//				selectFromUserConnection()
//						+ " where userId = ? order by providerId, rank",
//				connectionMapper, userId);
//		MultiValueMap<String, Connection<?>> connections = new LinkedMultiValueMap<String, Connection<?>>();
//		Set<String> registeredProviderIds = connectionFactoryLocator
//				.registeredProviderIds();
//		for (String registeredProviderId : registeredProviderIds) {
//			connections.put(registeredProviderId,
//					Collections.<Connection<?>> emptyList());
//		}
//		for (Connection<?> connection : resultList) {
//			String providerId = connection.getKey().getProviderId();
//			if (connections.get(providerId).size() == 0) {
//				connections.put(providerId, new LinkedList<Connection<?>>());
//			}
//			connections.add(providerId, connection);
//		}
//		return connections;
		return null;
	}

	public List<Connection<?>> findConnections(String providerId) {
//		return jdbcTemplate.query(selectFromUserConnection()
//				+ " where userId = ? and providerId = ? order by rank",
//				connectionMapper, userId, providerId);
		return null;
	}

	@SuppressWarnings("unchecked")
	public <A> List<Connection<A>> findConnections(Class<A> apiType) {
		List<?> connections = findConnections(getProviderId(apiType));
		return (List<Connection<A>>) connections;
	}

	public MultiValueMap<String, Connection<?>> findConnectionsToUsers(
			MultiValueMap<String, String> providerUsers) {
//		if (providerUsers == null || providerUsers.isEmpty()) {
//			throw new IllegalArgumentException(
//					"Unable to execute find: no providerUsers provided");
//		}
//		StringBuilder providerUsersCriteriaSql = new StringBuilder();
//		MapSqlParameterSource parameters = new MapSqlParameterSource();
//		parameters.addValue("userId", userId);
//		for (Iterator<Entry<String, List<String>>> it = providerUsers
//				.entrySet().iterator(); it.hasNext();) {
//			Entry<String, List<String>> entry = it.next();
//			String providerId = entry.getKey();
//			providerUsersCriteriaSql.append("providerId = :providerId_")
//					.append(providerId)
//					.append(" and providerUserId in (:providerUserIds_")
//					.append(providerId).append(")");
//			parameters.addValue("providerId_" + providerId, providerId);
//			parameters.addValue("providerUserIds_" + providerId,
//					entry.getValue());
//			if (it.hasNext()) {
//				providerUsersCriteriaSql.append(" or ");
//			}
//		}
//		List<Connection<?>> resultList = new NamedParameterJdbcTemplate(
//				jdbcTemplate).query(selectFromUserConnection()
//				+ " where userId = :userId and " + providerUsersCriteriaSql
//				+ " order by providerId, rank", parameters, connectionMapper);
//		MultiValueMap<String, Connection<?>> connectionsForUsers = new LinkedMultiValueMap<String, Connection<?>>();
//		for (Connection<?> connection : resultList) {
//			String providerId = connection.getKey().getProviderId();
//			List<String> userIds = providerUsers.get(providerId);
//			List<Connection<?>> connections = connectionsForUsers
//					.get(providerId);
//			if (connections == null) {
//				connections = new ArrayList<Connection<?>>(userIds.size());
//				for (int i = 0; i < userIds.size(); i++) {
//					connections.add(null);
//				}
//				connectionsForUsers.put(providerId, connections);
//			}
//			String providerUserId = connection.getKey().getProviderUserId();
//			int connectionIndex = userIds.indexOf(providerUserId);
//			connections.set(connectionIndex, connection);
//		}
//		return connectionsForUsers;
		return null;
	}

	public Connection<?> getConnection(ConnectionKey connectionKey) {
//		try {
//			return jdbcTemplate
//					.queryForObject(
//							selectFromUserConnection()
//									+ " where userId = ? and providerId = ? and providerUserId = ?",
//							connectionMapper, userId,
//							connectionKey.getProviderId(),
//							connectionKey.getProviderUserId());
//		} catch (EmptyResultDataAccessException e) {
//			throw new NoSuchConnectionException(connectionKey);
//		}
		return null;
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
//		try {
//			ConnectionData data = connection.createData();
//			int rank = jdbcTemplate
//					.queryForObject(
//							"select coalesce(max(rank) + 1, 1) as rank from "
//									+ tablePrefix
//									+ "UserConnection where userId = ? and providerId = ?",
//							new Object[] { userId, data.getProviderId() },
//							Integer.class);
//			jdbcTemplate
//					.update("insert into "
//							+ tablePrefix
//							+ "UserConnection (userId, providerId, providerUserId, rank, displayName, profileUrl, imageUrl, accessToken, secret, refreshToken, expireTime) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
//							userId, data.getProviderId(),
//							data.getProviderUserId(), rank,
//							data.getDisplayName(), data.getProfileUrl(),
//							data.getImageUrl(), encrypt(data.getAccessToken()),
//							encrypt(data.getSecret()),
//							encrypt(data.getRefreshToken()),
//							data.getExpireTime());
//		} catch (DuplicateKeyException e) {
//			throw new DuplicateConnectionException(connection.getKey());
//		}
	}

	@Transactional
	public void updateConnection(Connection<?> connection) {
//		ConnectionData data = connection.createData();
//		jdbcTemplate
//				.update("update "
//						+ tablePrefix
//						+ "UserConnection set displayName = ?, profileUrl = ?, imageUrl = ?, accessToken = ?, secret = ?, refreshToken = ?, expireTime = ? where userId = ? and providerId = ? and providerUserId = ?",
//						data.getDisplayName(), data.getProfileUrl(),
//						data.getImageUrl(), encrypt(data.getAccessToken()),
//						encrypt(data.getSecret()),
//						encrypt(data.getRefreshToken()), data.getExpireTime(),
//						userId, data.getProviderId(), data.getProviderUserId());
	}

	@Transactional
	public void removeConnections(String providerId) {
//		jdbcTemplate.update("delete from " + tablePrefix
//				+ "UserConnection where userId = ? and providerId = ?", userId,
//				providerId);
	}

	@Transactional
	public void removeConnection(ConnectionKey connectionKey) {
//		jdbcTemplate
//				.update("delete from "
//						+ tablePrefix
//						+ "UserConnection where userId = ? and providerId = ? and providerUserId = ?",
//						userId, connectionKey.getProviderId(),
//						connectionKey.getProviderUserId());
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

	public List<Connection<?>> mapUserConnections(
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

	public Connection<?> mapUserConnection(UserConnection userConnection) {
		ConnectionData connectionData = mapConnectionData(userConnection);
		ConnectionFactory<?> connectionFactory = connectionFactoryLocator
				.getConnectionFactory(connectionData.getProviderId());
		return connectionFactory.createConnection(connectionData);
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
