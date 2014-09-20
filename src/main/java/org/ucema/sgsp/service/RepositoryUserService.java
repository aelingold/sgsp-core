package org.ucema.sgsp.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.joda.time.DateTime;
import org.joda.time.YearMonth;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionKey;
import org.springframework.social.connect.UserProfile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.ucema.sgsp.api.dto.DashBoardUserDTO;
import org.ucema.sgsp.api.dto.RegistrationDTO;
import org.ucema.sgsp.api.dto.ReportUserDTO;
import org.ucema.sgsp.api.dto.UserDTO;
import org.ucema.sgsp.api.dto.UserTypeDTO;
import org.ucema.sgsp.api.transformation.UserTransformation;
import org.ucema.sgsp.exception.DuplicateEmailException;
import org.ucema.sgsp.persistence.model.WorkArea;
import org.ucema.sgsp.security.model.SocialMediaService;
import org.ucema.sgsp.security.model.User;
import org.ucema.sgsp.security.service.UserRepository;

@Service
public class RepositoryUserService implements UserService {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(RepositoryUserService.class);

	private PasswordEncoder passwordEncoder;
	@Autowired
	private WorkAreaService workAreaService;
	private UserRepository repository;
	@Autowired
	private CountryService countryService;

	@Autowired
	private UserTransformation userTransformation;

	@Autowired
	public RepositoryUserService(PasswordEncoder passwordEncoder,
			UserRepository repository) {
		this.passwordEncoder = passwordEncoder;
		this.repository = repository;
	}

	/**
	 * Creates the form object used in the registration form.
	 * 
	 * @param connection
	 * @return If a user is signing in by using a social provider, this method
	 *         returns a form object populated by the values given by the
	 *         provider. Otherwise this method returns an empty form object
	 *         (normal form registration).
	 */
	public RegistrationDTO createRegistrationDTO(Connection<?> connection) {
		RegistrationDTO dto = new RegistrationDTO();

		if (connection != null) {

			LOGGER.debug("Connection object received with information: "
					+ connection);

			UserProfile socialMediaProfile = connection.fetchUserProfile();

			LOGGER.debug("UserProfile received with information: "
					+ socialMediaProfile);

			dto.setEmail(socialMediaProfile.getEmail());
			dto.setFirstName(socialMediaProfile.getFirstName());
			dto.setLastName(socialMediaProfile.getLastName());

			ConnectionKey providerKey = connection.getKey();

			LOGGER.debug("ConnectionKey received with information: "
					+ providerKey);

			dto.setSignInProvider(SocialMediaService.valueOf(providerKey
					.getProviderId().toUpperCase()));
		}

		return dto;
	}

	@Transactional
	public Map<YearMonth, ReportUserDTO> countUsers() {
		List<Object> items = repository.countUsers();

		Map<YearMonth, ReportUserDTO> itemsMap = new HashMap<YearMonth, ReportUserDTO>();
		for (Object item : items) {
			Object[] tuple = (Object[]) item;
			DateTime creationTime = (DateTime) tuple[0];
			Boolean professional = (Boolean) tuple[1];

			YearMonth yearMonth = new YearMonth(creationTime.getYear(),
					creationTime.getMonthOfYear());

			if (itemsMap.get(yearMonth) == null) {

				ReportUserDTO reportUserDTO = new ReportUserDTO();

				if (professional) {
					reportUserDTO.setIsProfessionalCount(reportUserDTO
							.getIsProfessionalCount() + 1);
				} else {
					reportUserDTO
							.setUserCount(reportUserDTO.getUserCount() + 1);
				}

				itemsMap.put(yearMonth, reportUserDTO);

			} else {

				ReportUserDTO reportUserDTO = itemsMap.get(yearMonth);

				if (professional) {
					reportUserDTO.setIsProfessionalCount(reportUserDTO
							.getIsProfessionalCount() + 1);
				} else {
					reportUserDTO
							.setUserCount(reportUserDTO.getUserCount() + 1);
				}

				itemsMap.put(yearMonth, reportUserDTO);
			}
		}

		return itemsMap;
	}

	@Transactional
	public Long countByIsProfessional(Boolean isProfessional) {
		return repository.countByIsProfessional(isProfessional);
	}

	@Transactional
	public UserDTO findByEmail(String email) {

		User user = find(email);
		if (user.getIsEnabled()) {
			return userTransformation.transformToApi(user);
		} else {
			return null;
		}
	}

	private User find(String email) {
		User user = repository.findByEmail(email);
		if (user.getIsEnabled()) {
			return user;
		} else {
			return null;
		}
	}

	@Transactional
	public List<UserDTO> findByWorkAreas_CodeAndIsEnabledAndIsProfessional(
			List<String> codes, Boolean isEnabled, Boolean isProfessional) {
		List<User> users = repository
				.findByWorkAreas_CodeAndIsEnabledAndIsProfessional(codes,
						isEnabled, isProfessional);
		return userTransformation.transformToApi(users);
	}

	@Transactional
	public List<UserDTO> findByWorkAreas_CodeAndIsEnabledAndIsProfessionalAndUserWorkZones_City_Code(
			List<String> codes, Boolean isEnabled, Boolean isProfessional,
			List<String> cityCodes) {
		List<User> users = repository
				.findByWorkAreas_CodeAndIsEnabledAndIsProfessionalAndUserWorkZones_City_Code(
						codes, isEnabled, isProfessional, cityCodes);
		return userTransformation.transformToApi(users);
	}

	@Transactional
	public List<UserDTO> list() {

		List<User> users = repository.findAll().stream()
				.filter(u -> u.getIsEnabled().equals(true))
				.collect(Collectors.toList());

		return userTransformation.transformToApi(users);
	}

	@Transactional
	public UserDTO saveOrUpdate(UserDTO userDTO) {

		User user = find(userDTO.getEmail());
		User response = repository.save(userTransformation.updateFields(user,
				userDTO));
		userDTO.setId(response.getId());
		return userDTO;
	}

	@Transactional
	public void update(DashBoardUserDTO dashBoardUserDTO) {

		User user = find(dashBoardUserDTO.getEmail());
		repository
				.save(userTransformation.updateFields(user, dashBoardUserDTO));
	}

	@Transactional
	public void delete(Long id) {
		User user = repository.getOne(id);
		if (user == null) {
			throw new RuntimeException("user not found");
		}
		repository.delete(user);
	}

	@Transactional
	public UserDTO get(Long id) {
		User user = repository.getOne(id);
		if (user == null) {
			throw new RuntimeException("user not found");
		}

		if (user.getIsEnabled()) {
			return userTransformation.transformToApi(user);
		} else {
			return null;
		}
	}

	@Transactional
	public User registerNewUserAccount(RegistrationDTO userAccountData)
			throws DuplicateEmailException {
		LOGGER.debug("Registering new user account with information: {}",
				userAccountData);

		if (emailExist(userAccountData.getEmail())) {
			LOGGER.debug("Email: {} exists. Throwing exception.",
					userAccountData.getEmail());
			throw new DuplicateEmailException("The email address: "
					+ userAccountData.getEmail() + " is already in use.");
		}

		LOGGER.debug("Email: {} does not exist. Continuing registration.",
				userAccountData.getEmail());

		String encodedPassword = encodePassword(userAccountData);

		User.Builder user = User
				.getBuilder()
				.email(userAccountData.getEmail())
				.firstName(userAccountData.getFirstName())
				.lastName(userAccountData.getLastName())
				.password(encodedPassword)
				.telephone(userAccountData.getTelephone())
				.professional(
						userAccountData.getUserType().equals(
								UserTypeDTO.professional)).enabled(true)
				.country(countryService.find(userAccountData.getCountryCode()));

		if (userAccountData.getWorkAreaCodes() != null
				&& userAccountData.getWorkAreaCodes().size() > 0) {
			user = user.workAreas(buildWorkAreaCodes(userAccountData
					.getWorkAreaCodes()));
		}

		if (userAccountData.isSocialSignIn()) {
			user.signInProvider(userAccountData.getSignInProvider());
		}

		User registered = user.build();

		LOGGER.debug("Persisting new user with information: {}", registered);

		return repository.save(registered);
	}

	private List<WorkArea> buildWorkAreaCodes(List<String> workAreaCodes) {

		List<WorkArea> result = new ArrayList<WorkArea>();

		if (workAreaCodes != null) {
			for (String workAreaCode : workAreaCodes) {
				if (workAreaCode != null) {
					result.add(new WorkArea(workAreaService.findByCode(
							workAreaCode).getId()));
				}
			}
		}

		return result;
	}

	private boolean emailExist(String email) {
		LOGGER.debug(
				"Checking if email {} is already found from the database.",
				email);

		User user = repository.findByEmail(email);

		if (user != null) {
			LOGGER.debug(
					"User account: {} found with email: {}. Returning true.",
					user, email);
			return true;
		}

		LOGGER.debug("No user account found with email: {}. Returning false.",
				email);

		return false;
	}

	private String encodePassword(RegistrationDTO dto) {
		String encodedPassword = null;

		if (dto.isNormalRegistration()) {
			LOGGER.debug("Registration is normal registration. Encoding password.");
			encodedPassword = passwordEncoder.encode(dto.getPassword());
		}

		return encodedPassword;
	}

	@Transactional
	public void disable(Long id) {
		User user = repository.getOne(id);
		if (user == null) {
			throw new RuntimeException("user not found");
		}
		user.setIsEnabled(false);
		repository.save(user);
	}

	@Transactional
	public void enable(Long id) {
		User user = repository.getOne(id);
		if (user == null) {
			throw new RuntimeException("user not found");
		}
		user.setIsEnabled(true);
		repository.save(user);
	}

	@Transactional
	public void disable(String username) {
		User user = repository.findByEmail(username);
		if (user == null) {
			throw new RuntimeException("user not found");
		}
		user.setIsEnabled(false);
		repository.save(user);	
	}

	@Transactional
	public void enable(String username) {
		User user = repository.findByEmail(username);
		if (user == null) {
			throw new RuntimeException("user not found");
		}
		user.setIsEnabled(true);
		repository.save(user);
	}
}
