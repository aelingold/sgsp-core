package org.ucema.sgsp.service;

import java.util.Collection;
import java.util.List;

import org.springframework.social.connect.Connection;
import org.ucema.sgsp.api.dto.DashBoardUserDTO;
import org.ucema.sgsp.api.dto.RegistrationDTO;
import org.ucema.sgsp.api.dto.ReportUserDTO;
import org.ucema.sgsp.api.dto.UserDTO;
import org.ucema.sgsp.exception.DuplicateEmailException;
import org.ucema.sgsp.persistence.model.RatePlanPackageType;
import org.ucema.sgsp.security.model.User;

public interface UserService {

	RegistrationDTO createRegistrationDTO(Connection<?> connection);

	Collection<ReportUserDTO> countUsers();

	Long countByIsProfessional(Boolean isProfessional);

	User registerNewUserAccount(RegistrationDTO userAccountData)
			throws DuplicateEmailException;

	List<UserDTO> list();

	List<UserDTO> findByWorkAreas_CodeAndIsEnabledAndIsProfessional(
			List<String> codes, Boolean isEnabled, Boolean isProfessional);

	List<UserDTO> findByWorkAreas_CodeAndIsEnabledAndIsProfessionalAndUserWorkZones_City_Code(
			List<String> codes, Boolean isEnabled, Boolean isProfessional,
			List<String> cityCodes);

	UserDTO saveOrUpdate(UserDTO user);

	void update(DashBoardUserDTO dashBoardUserDTO);

	void delete(Long id);

	void disable(Long id);

	void enable(Long id);

	void disable(String username);

	void enable(String username);

	UserDTO get(Long id);

	UserDTO findByEmail(String email);

	List<UserDTO> findByUserRatePlan_RatePlan_PackageTypeAndIsProfessional(RatePlanPackageType packageType, Boolean isProfessional);
}
