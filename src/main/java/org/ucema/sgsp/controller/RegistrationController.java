package org.ucema.sgsp.controller;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.Connection;
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
import org.ucema.sgsp.api.dto.CountryDTO;
import org.ucema.sgsp.api.dto.RegistrationDTO;
import org.ucema.sgsp.api.dto.UserTypeDTO;
import org.ucema.sgsp.api.dto.WorkAreaDTO;
import org.ucema.sgsp.exception.DuplicateEmailException;
import org.ucema.sgsp.security.model.User;
import org.ucema.sgsp.security.util.SecurityUtil;
import org.ucema.sgsp.service.CountryService;
import org.ucema.sgsp.service.UserService;
import org.ucema.sgsp.service.WorkAreaService;

@Controller
@SessionAttributes("user")
public class RegistrationController {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(RegistrationController.class);

	protected static final String ERROR_CODE_EMAIL_EXIST = "NotExist.user.email";
	protected static final String ERROR_CODE_EMPTY_WORK_AREAS = "EmptyWorkAreas.user.workAreaCodes";
	protected static final String ERROR_CODE_EMPTY_TELEPHONE = "EmptyTelephone.user.workAreaCodes";
	protected static final String MODEL_NAME_REGISTRATION_DTO = "user";
	protected static final String VIEW_NAME_REGISTRATION_PAGE = "registration";

	@Autowired
	private UserService service;
	@Autowired
	private WorkAreaService workAreaService;
	@Autowired
	private CountryService countryService;

	private final ProviderSignInUtils providerSignInUtils = new ProviderSignInUtils();

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String showUserRegistrationDTO(WebRequest request, Model model) {
		LOGGER.debug("Rendering registration page.");

		Connection<?> connection = providerSignInUtils
				.getConnectionFromSession(request);

		RegistrationDTO registration = service
				.createRegistrationDTO(connection);
		LOGGER.debug("Rendering registration form with information: {}",
				registration);

		model.addAttribute(MODEL_NAME_REGISTRATION_DTO, registration);
		model.addAttribute("workAreas", workAreaService.list());
		model.addAttribute("userTypeMap", getUserTypeMap());
		model.addAttribute("workAreaMap", getWorkAreaCodesMap());
		model.addAttribute("countriesMap", getCountries());

		return VIEW_NAME_REGISTRATION_PAGE;
	}

	private Map<String, String> getCountries() {
		return countryService
				.list()
				.stream()
				.collect(
						Collectors.toMap(CountryDTO::getCode,
								CountryDTO::getDescription));
	}

	private Map<String, String> getUserTypeMap() {

		Map<String, String> userTypeMap = new LinkedHashMap<String, String>();
		userTypeMap.put("user", "Usuario");
		userTypeMap.put("professional", "Profesional");

		return userTypeMap;
	}

	private Map<String, String> getWorkAreaCodesMap() {

		Map<String, String> workAreaMap = new LinkedHashMap<String, String>();

		for (WorkAreaDTO workArea : workAreaService.list()) {
			workAreaMap.put(workArea.getCode(), workArea.getDescription());
		}

		return workAreaMap;
	}

	/**
	 * Processes the form submissions of the registration form.
	 */
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String registerUserAccount(
			@Valid @ModelAttribute("user") RegistrationDTO userAccountData,
			BindingResult result, WebRequest request, Model model)
			throws DuplicateEmailException {
		LOGGER.debug("Registering user account with information: {}",
				userAccountData);

		model.addAttribute("workAreas", workAreaService.list());
		model.addAttribute("userTypeMap", getUserTypeMap());
		model.addAttribute("workAreaMap", getWorkAreaCodesMap());
		model.addAttribute("countriesMap", getCountries());

		if (result.hasErrors()) {
			LOGGER.debug("Validation errors found. Rendering form view.");
			return VIEW_NAME_REGISTRATION_PAGE;
		}

		if (userAccountData.getUserType().equals(UserTypeDTO.professional)
				&& (userAccountData.getWorkAreaCodes() == null || userAccountData
						.getWorkAreaCodes().isEmpty())) {

			LOGGER.debug("WorkAreaCodes can not be empty for professional users");
			addFieldError(MODEL_NAME_REGISTRATION_DTO,
					RegistrationDTO.FIELD_NAME_WORK_AREAS, null,
					ERROR_CODE_EMPTY_WORK_AREAS, result);
			return VIEW_NAME_REGISTRATION_PAGE;
		}

		if (userAccountData.getUserType().equals(UserTypeDTO.professional)
				&& (userAccountData.getTelephone() == null || userAccountData
						.getTelephone().isEmpty())) {

			LOGGER.debug("Telephone can not be empty for professional users");
			addFieldError(MODEL_NAME_REGISTRATION_DTO,
					RegistrationDTO.FIELD_NAME_TELEPHONE, null,
					ERROR_CODE_EMPTY_TELEPHONE, result);
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
