package org.ucema.sgsp.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.ucema.sgsp.persistence.model.UserWorkZone;

public interface UserWorkZoneDAO extends JpaRepository<UserWorkZone, Long> {

	List<UserWorkZone> findByUser_Email(String username);
	
	Long deleteByUser_Email(String username);
}
