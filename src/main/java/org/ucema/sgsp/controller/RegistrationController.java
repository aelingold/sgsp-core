package org.ucema.sgsp.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionKey;
import org.springframework.social.connect.UserProfile;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.context.request.WebRequest;
import org.ucema.sgsp.api.dto.RegistrationDTO;
import org.ucema.sgsp.exception.DuplicateEmailException;
import org.ucema.sgsp.security.model.SocialMediaService;
import org.ucema.sgsp.security.model.User;
import org.ucema.sgsp.security.util.SecurityUtil;
import org.ucema.sgsp.service.UserService;
import org.ucema.sgsp.service.WorkAreaService;

@Controller
@SessionAttributes("user")
public class RegistrationController {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(RegistrationController.class);

	protected static final String ERROR_CODE_EMAIL_EXIST = "NotExist.user.email";
	protected static final String MODEL_NAME_REGISTRATION_DTO = "user";
	protected static final String VIEW_NAME_REGISTRATION_PAGE = "registration";

	private UserService service;
	private WorkAreaService workAreaService;

	private final ProviderSignInUtils providerSignInUtils = new ProviderSignInUtils();

	@Autowired
	public RegistrationController(UserService service,
			WorkAreaService workAreaService) {
		this.service = service;
		this.workAreaService = workAreaService;
	}

	/**
	 * Renders the registration page.
	 */
	@RequestMapping(value = "/user/register", method = RequestMethod.GET)
	public String showRegistrationDTO(WebRequest request, Model model) {
		LOGGER.debug("Rendering registration page.");

		Connection<?> connection = providerSignInUtils
				.getConnectionFromSession(request);

		RegistrationDTO registration = createRegistrationDTO(connection);
		LOGGER.debug("Rendering registration form with information: {}",
				registration);

		model.addAttribute(MODEL_NAME_REGISTRATION_DTO, registration);

		return VIEW_NAME_REGISTRATION_PAGE;
	}

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String showUserRegistrationDTO(WebRequest request, Model model) {
		LOGGER.debug("Rendering registration page.");

		Connection<?> connection = providerSignInUtils
				.getConnectionFromSession(request);

		RegistrationDTO registration = createRegistrationDTO(connection);
		LOGGER.debug("Rendering registration form with information: {}",
				registration);

		model.addAttribute(MODEL_NAME_REGISTRATION_DTO, registration);
		
		model.addAttribute("workAreas", workAreaService.list());

		return "registration";
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
	private RegistrationDTO createRegistrationDTO(Connection<?> connection) {
		RegistrationDTO dto = new RegistrationDTO();

		if (connection != null) {
			UserProfile socialMediaProfile = connection.fetchUserProfile();
			dto.setEmail(socialMediaProfile.getEmail());
			dto.setFirstName(socialMediaProfile.getFirstName());
			dto.setLastName(socialMediaProfile.getLastName());

			ConnectionKey providerKey = connection.getKey();
			dto.setSignInProvider(SocialMediaService.valueOf(providerKey
					.getProviderId().toUpperCase()));
		}

		return dto;
	}

	/**
	 * Processes the form submissions of the registration form.
	 */
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String registerUserAccount(
			@Valid @ModelAttribute("user") RegistrationDTO userAccountData,
			BindingResult result, WebRequest request)
			throws DuplicateEmailException {
		LOGGER.debug("Registering user account with information: {}",
				userAccountData);
		if (result.hasErrors()) {
			LOGGER.debug("Validation errors found. Rendering form view.");
			return VIEW_NAME_REGISTRATION_PAGE;
		}

		LOGGER.debug("No validation errors found. Continuing registration process.");

		User registered = createUserAccount(userAccountData, result);

		// If email address was already found from the database, render the form
		// view.
		if (registered == null) {
			LOGGER.debug("An email address was found from the database. Rendering form view.");
			return VIEW_NAME_REGISTRATION_PAGE;
		}

		LOGGER.debug("Registered user account with information: {}", registered);

		// Logs the user in.
		SecurityUtil.logInUser(registered);
		LOGGER.debug("User {} has been signed in", registered);
		// If the user is signing in by using a social provider, this method
		// call stores
		// the connection to the UserConnection table. Otherwise, this method
		// does not
		// do anything.
		providerSignInUtils.doPostSignUp(registered.getEmail(), request);

		return "redirect:/";
	}

	/**
	 * Creates a new user account by calling the service method. If the email
	 * address is found from the database, this method adds a field error to the
	 * email field of the form object.
	 */
	private User createUserAccount(RegistrationDTO userAccountData,
			BindingResult result) {
		LOGGER.debug("Creating user account with information: {}",
				userAccountData);
		User registered = null;

		try {
			registered = service.registerNewUserAccount(userAccountData);
		} catch (DuplicateEmailException ex) {
			LOGGER.debug("An email address: {} exists.",
					userAccountData.getEmail());
			addFieldError(MODEL_NAME_REGISTRATION_DTO,
					RegistrationDTO.FIELD_NAME_EMAIL,
					userAccountData.getEmail(), ERROR_CODE_EMAIL_EXIST, result);
		}

		return registered;
	}

	private void addFieldError(String objectName, String fieldName,
			String fieldValue, String errorCode, BindingResult result) {
		LOGGER.debug(
				"Adding field error object's: {} field: {} with error code: {}",
				objectName, fieldName, errorCode);
		FieldError error = new FieldError(objectName, fieldName, fieldValue,
				false, new String[] { errorCode }, new Object[] {}, errorCode);

		result.addError(error);
		LOGGER.debug("Added field error: {} to binding result: {}", error,
				result);
	}
}
