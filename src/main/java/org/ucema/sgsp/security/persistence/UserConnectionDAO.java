package org.ucema.sgsp.security.persistence;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.ucema.sgsp.security.model.UserConnection;

public interface UserConnectionDAO extends JpaRepository<UserConnection, Long> {

	UserConnection findByUserConnectionPK_ProviderUserId(String providerUserId);

	@Query("select u.userId from UserConnection u where u.providerId=:providerId and u.providerUserId=:providerUserId")
	List<String> findUserIds(@Param("providerId") String providerId,
			@Param("providerUserId") String providerUserId);

	@Query("select u.userId from UserConnection u where u.providerId=:providerId and u.providerUserId in(:providerUserIds)")
	Set<String> findUserIds(@Param("providerId") String providerId,
			@Param("providerUserIds") Set<String> providerUserIds);

	@Query("from UserConnection u where u.userId=:userId ORDER BY u.providerId DESC,u.rank DESC")
	List<UserConnection> findAllConnections(@Param("userId") String userId);

	@Query("from UserConnection u where u.userId=:userId and u.providerId=:providerId ORDER BY u.rank DESC")
	List<UserConnection> findConnections(@Param("userId") String userId,
			@Param("providerId") String providerId);

	@Query("from UserConnection u where u.userId=:userId and u.providerId=:providerId and u.providerUserId=:providerUserId")
	UserConnection getConnection(@Param("userId") String userId,
			@Param("providerId") String providerId,
			@Param("providerUserId") String providerUserId);

	@Query("select coalesce(max(rank) + 1, 1) as rank from UserConnection u where u.userId=:userId and u.providerId=:providerId")
	List<Object> addConnection(@Param("userId") String userId,
			@Param("providerId") String providerId);
}
