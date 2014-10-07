package org.ucema.sgsp.security.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.ucema.sgsp.security.model.UserConnection;

public interface UserConnectionDAO extends JpaRepository<UserConnection, Long> {
	
	UserConnection findByUserConnectionPK_ProviderUserId(String providerUserId);
}
