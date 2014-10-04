package org.ucema.sgsp.security.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.ucema.sgsp.security.model.UserToken;

public interface UserTokenDAO extends JpaRepository<UserToken, Long> {
	
	UserToken findByToken(String token);
}
