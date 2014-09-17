package org.ucema.sgsp.persistence;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.ucema.sgsp.persistence.model.UserWorkRate;
import org.ucema.sgsp.persistence.model.UserWorkRateStatusType;

public interface UserWorkRateDAO extends JpaRepository<UserWorkRate, Long> {

	@Query("from UserWorkRate u where u.quote.user.email in(:usernames)")
	List<UserWorkRate> findByQuoteUser_Email(
			@Param("usernames") Set<String> usernames);

	@Query("from UserWorkRate u where u.statusType=:statusType and u.quote.user.email in(:usernames)")
	List<UserWorkRate> findByStatusTypeAndQuoteUser_Email(
			@Param("usernames") Set<String> usernames,
			@Param("statusType") UserWorkRateStatusType statusType);

	List<UserWorkRate> findByUser_Email(String username);

	List<UserWorkRate> findByUser_EmailAndStatusType(String username,
			UserWorkRateStatusType statusType);
}
