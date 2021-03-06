package org.ucema.sgsp.security.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.ucema.sgsp.persistence.model.RatePlanPackageType;
import org.ucema.sgsp.security.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

	@Query("select creationTime, isProfessional from User")
	List<Object> countUsers();

	User findByEmail(String email);

	Long countByIsProfessional(Boolean isProfessional);

	List<User> findByUserWorkAreas_WorkArea_CodeAndIsEnabledAndIsProfessional(
			List<String> codes, Boolean isEnabled, Boolean isProfessional);

	List<User> findByUserWorkAreas_WorkArea_CodeAndIsEnabledAndIsProfessionalAndUserWorkZones_City_Code(
			List<String> codes, Boolean isEnabled, Boolean isProfessional,
			List<String> cityCodes);

	List<User> findByUserRatePlans_RatePlan_PackageTypeAndIsProfessionalAndIsEnabled(
			RatePlanPackageType packageType, Boolean isProfessional,
			Boolean isEnabled);
}
