package org.ucema.sgsp.persistence;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.ucema.sgsp.persistence.model.UserWorkRateSummarize;

public interface UserWorkRateSummarizeDAO extends
		JpaRepository<UserWorkRateSummarize, Long> {

	UserWorkRateSummarize findByUser_Email(String username);

	@Query("from UserWorkRateSummarize u where u.user.email in(:usernames)")
	List<UserWorkRateSummarize> findByUser_Email(
			@Param("usernames") Set<String> usernames);
}
