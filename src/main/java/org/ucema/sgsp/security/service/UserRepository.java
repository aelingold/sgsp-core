package org.ucema.sgsp.security.service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.ucema.sgsp.security.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

	User findByEmail(String email);

	List<User> findByWorkAreas_CodeAndIsEnabledAndIsProfessional(
			List<String> codes, Boolean isEnabled, Boolean isProfessional);

	List<User> findByWorkAreas_CodeAndIsEnabledAndIsProfessionalAndUserWorkZones_City_Code(
			List<String> codes, Boolean isEnabled, Boolean isProfessional,
			List<String> cityCodes);
}
