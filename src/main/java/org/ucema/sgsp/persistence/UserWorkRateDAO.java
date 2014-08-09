package org.ucema.sgsp.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.ucema.sgsp.persistence.model.UserWorkRate;

public interface UserWorkRateDAO extends JpaRepository<UserWorkRate, Long> {

}
