package org.ucema.sgsp.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.ucema.sgsp.persistence.model.UserWorkZone;

public interface UserWorkZoneDAO extends JpaRepository<UserWorkZone, Long> {

}
