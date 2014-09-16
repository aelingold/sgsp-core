package org.ucema.sgsp.persistence;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.ucema.sgsp.persistence.model.UserWorkRate;

public interface UserWorkRateDAO extends JpaRepository<UserWorkRate, Long> {

	@Query("from UserWorkRate u where u.user.email in(:usernames)")
	List<UserWorkRate> findByUser_Email(@Param("usernames") Set<String> usernames);
}
