package org.ucema.sgsp.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import org.joda.time.DateTime;
import org.joda.time.YearMonth;
import org.joda.time.format.DateTimeFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionKey;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.connect.UserProfile;
import org.springframework.social.connect.support.OAuth2Connection;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.PagedList;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.ucema.sgsp.api.dto.DashBoardUserDTO;
import org.ucema.sgsp.api.dto.RatePlanDTO;
import org.ucema.sgsp.api.dto.RegistrationDTO;
import org.ucema.sgsp.api.dto.ReportUserDTO;
import org.ucema.sgsp.api.dto.UserDTO;
import org.ucema.sgsp.api.transformation.UserTransformation;
import org.ucema.sgsp.exception.DuplicateEmailException;
import org.ucema.sgsp.persistence.model.RatePlan;
import org.ucema.sgsp.persistence.model.RatePlanPackageType;
import org.ucema.sgsp.persistence.model.UserRatePlan;
import org.ucema.sgsp.persistence.model.UserWorkArea;
import org.ucema.sgsp.persistence.model.UserWorkRateSummarize;
import org.ucema.sgsp.persistence.model.WorkArea;
import org.ucema.sgsp.security.model.SocialMediaService;
import org.ucema.sgsp.security.model.User;
import org.ucema.sgsp.security.model.UserConnection;
import org.ucema.sgsp.security.model.UserToken;
import org.ucema.sgsp.security.persistence.UserRepository;
import org.ucema.sgsp.security.service.UserConnectionService;

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
	private RatePlanService ratePlanService;
	@Autowired
	private MailService mailService;
	@Autowired
	private UserTransformation userTransformation;
	@Autowired
	private ConnectionRepository connectionRepository;
	@Autowired
	private UserConnectionService userConnectionService;

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
	@SuppressWarnings("unchecked")
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

			OAuth2Connection<Facebook> oauth2Connection = (OAuth2Connection<Facebook>) connection;
			Facebook facebook = oauth2Connection.getApi();
			PagedList<String> friendIds = facebook.friendOperations()
					.getFriendIds();

			List<Long> userFriendIds = new ArrayList<Long>();
			friendIds.forEach(fi -> {
				UserConnection userConnection = userConnectionService
						.findByUserConnectionPK_ProviderUserId(fi);
				User user = repository.findByEmail(userConnection
						.getUserConnectionPK().getUserId());
				userFriendIds.add(user.getId());
			});
			dto.setUserFriendIds(userFriendIds);

			// Facebook facebook = facebookConnection.getApi();
			//
			// List<Long> userFriendIds = new ArrayList<Long>();
			// List<String> friendIds =
			// facebook.friendOperations().getFriendIds();
			// friendIds.forEach(fi -> {
			// UserConnection userConnection =
			// userConnectionService.findByUserConnectionPK_ProviderUserId(fi);
			// UserDTO user =
			// findByEmail(userConnection.getUserConnectionPK().getUserId());
			// userFriendIds.add(user.getId());
			// });
			// dto.setUserFriendIds(userFriendIds);
		}

		return dto;
	}

	@Transactional
	public Collection<ReportUserDTO> countUsers() {
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

				reportUserDTO.setYearMonth(yearMonth.toString(DateTimeFormat
						.forPattern("MM-yyyy")));
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

		return itemsMap.values();
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
				.findByUserWorkAreas_WorkArea_CodeAndIsEnabledAndIsProfessional(
						codes, isEnabled, isProfessional);
		return userTransformation.transformToApi(users);
	}

	@Transactional
	public List<UserDTO> findByWorkAreas_CodeAndIsEnabledAndIsProfessionalAndUserWorkZones_City_Code(
			List<String> codes, Boolean isEnabled, Boolean isProfessional,
			List<String> cityCodes) {
		List<User> users = repository
				.findByUserWorkAreas_WorkArea_CodeAndIsEnabledAndIsProfessionalAndUserWorkZones_City_Code(
						codes, isEnabled, isProfessional, cityCodes);
		return userTransformation.transformToApi(users);
	}

	@Transactional
	public List<UserDTO> findByUserRatePlans_RatePlan_PackageTypeAndIsProfessionalAndIsEnabled(
			RatePlanPackageType packageType, Boolean isProfessional,
			Boolean isEnabled) {
		List<User> users = repository
				.findByUserRatePlans_RatePlan_PackageTypeAndIsProfessionalAndIsEnabled(
						packageType, isProfessional, isEnabled);
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
	public User update(User user) {
		return repository.save(user);
	}

	@Transactional
	public void update(DashBoardUserDTO dashBoardUserDTO) {

		User user = userTransformation.updateFields(
				find(dashBoardUserDTO.getEmail()), dashBoardUserDTO);

		user.getUserWorkAreas().clear();

		repository.save(user);
	}
	
	@Transactional
	public void updateRatePlan(String ratePlanCode, String username) {
		User user = find(username);
		
		RatePlanDTO ratePlan = ratePlanService.findByCode(ratePlanCode);
		
		UserRatePlan validUserRatePlan = User.getValidUserRatePlan(user.getUserRatePlans());
		validUserRatePlan.setIsEnabled(false);
		validUserRatePlan.setValidTo(new Date());
		
		UserRatePlan newUserRatePlan = new UserRatePlan();
		newUserRatePlan.setRatePlan(new RatePlan(ratePlan.getId()));
		newUserRatePlan.setIsEnabled(true);
		newUserRatePlan.setUser(user);
		
		user.getUserRatePlans().add(newUserRatePlan);
		
		repository.save(user);
	}	

	@Transactional
	public void updateUserWorkAreas(DashBoardUserDTO dashBoardUserDTO) {

		User user = find(dashBoardUserDTO.getEmail());
		
		repository.save(userTransformation.updateUserWorkAreas(
				user, dashBoardUserDTO));
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

		User.Builder user = User.getBuilder().email(userAccountData.getEmail())
				.firstName(userAccountData.getFirstName())
				.lastName(userAccountData.getLastName())
				.password(encodedPassword)
				.telephone(userAccountData.getTelephone())
				.professional(userAccountData.getIsProfessional())
				.enabled(false)
				.country(countryService.find(userAccountData.getCountryCode()));

		if (userAccountData.getWorkAreaCodes() != null
				&& userAccountData.getWorkAreaCodes().size() > 0) {
			user = user.userWorkAreas(buildUserWorkAreas(userAccountData
					.getWorkAreaCodes()));
		}

		String token = UUID.randomUUID().toString();

		if (userAccountData.isSocialSignIn()) {
			user.signInProvider(userAccountData.getSignInProvider());
			user.enabled(true);

			List<User> userFriends = new ArrayList<User>();
			if (userAccountData.getUserFriendIds() != null) {
				userAccountData.getUserFriendIds().forEach(uf -> {
					userFriends.add(new User(uf));
				});
			}
			user.userFriends(userFriends);
		} else {
			UserToken userToken = new UserToken();
			userToken.setToken(token);
			userToken.setValid(true);
			user.userToken(userToken);
		}

		if (userAccountData.getIsProfessional()) {
			user.userRatePlans(buildUserRatePlans(userAccountData));
			user.userWorkRateSummarize(buildUserWorkRateSummarize(userAccountData));
		}

		User registered = user.build();

		LOGGER.debug("Persisting new user with information: {}", registered);

		User userSaved = repository.save(registered);

		if (!registered.getIsEnabled()) {

			Map<String, Object> model = new HashMap<String, Object>();
			model.put("token", token);

			mailService.save(userSaved.getEmail(), MailService.FROM_EMAIL,
					"Registracion en SinGuia", "mail/confirmRegistration.ftl",
					model);
		}

		return userSaved;
	}

	private UserWorkRateSummarize buildUserWorkRateSummarize(
			RegistrationDTO userAccountData) {

		UserWorkRateSummarize result = new UserWorkRateSummarize();

		result.setNegativeQuantity(0L);
		result.setPositiveQuantity(0L);
		result.setNeutralQuantity(0L);

		return result;
	}

	private List<UserRatePlan> buildUserRatePlans(RegistrationDTO userAccountData) {

		List<UserRatePlan> result = new ArrayList<UserRatePlan>();
		
		UserRatePlan userRatePlan = new UserRatePlan();

		userRatePlan.setIsEnabled(true);
		userRatePlan.setRatePlan(new RatePlan(ratePlanService.findByCode(
				RatePlanDTO.PLAN1).getId()));
		
		result.add(userRatePlan);

		return result;
	}

	private List<UserWorkArea> buildUserWorkAreas(List<String> workAreaCodes) {

		List<UserWorkArea> result = new ArrayList<UserWorkArea>();

		if (workAreaCodes != null) {
			for (String workAreaCode : workAreaCodes) {
				if (workAreaCode != null) {

					UserWorkArea userWorkArea = new UserWorkArea();

					userWorkArea.setWorkArea(new WorkArea(workAreaService
							.findByCode(workAreaCode).getId()));

					result.add(userWorkArea);
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

		// if (dto.isNormalRegistration()) {
		// LOGGER.debug("Registration is normal registration. Encoding password.");
		encodedPassword = passwordEncoder.encode(dto.getPassword());
		// }

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
