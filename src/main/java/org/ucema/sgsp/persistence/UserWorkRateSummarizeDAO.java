package org.ucema.sgsp.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.ucema.sgsp.persistence.model.UserWorkRateSummarize;

public interface UserWorkRateSummarizeDAO extends JpaRepository<UserWorkRateSummarize, Long> {

}
