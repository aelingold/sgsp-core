package org.ucema.sgsp.registration.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.ucema.sgsp.api.dto.UserDTO;
import org.ucema.sgsp.api.transformation.UserTransformation;
import org.ucema.sgsp.exception.DuplicateEmailException;
import org.ucema.sgsp.registration.dto.RegistrationForm;
import org.ucema.sgsp.security.model.User;
import org.ucema.sgsp.security.service.UserRepository;


@Service
public class RepositoryUserService implements UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(RepositoryUserService.class);

    private PasswordEncoder passwordEncoder;

    private UserRepository repository;
    
	@Autowired
	private UserTransformation userTransformation;    

    @Autowired
    public RepositoryUserService(PasswordEncoder passwordEncoder, UserRepository repository) {
        this.passwordEncoder = passwordEncoder;
        this.repository = repository;
    }
    
	@Transactional
	public List<UserDTO> list() {
		return userTransformation.transformToApi(repository.findAll());
	}
	
	@Transactional
	public UserDTO saveOrUpdate(UserDTO user) {
		User response = repository.save(userTransformation.transformToModel(user));
		user.setId(response.getId());
		return user;
	}

	@Transactional
	public void delete(UserDTO user) {
		repository.delete(userTransformation.transformToModel(user));
	}
	
	@Transactional
	public void delete(Long id) {
		User user = repository.getOne(id);
		if (user == null){
			throw new RuntimeException("user not found");
		}		
		repository.delete(user);
	}
	
	@Transactional
	public UserDTO get(Long id){
		User user = repository.getOne(id);
		if (user == null){
			throw new RuntimeException("user not found");
		}
		return userTransformation.transformToApi(user);
	}    

    @Transactional
    public User registerNewUserAccount(RegistrationForm userAccountData) throws DuplicateEmailException {
        LOGGER.debug("Registering new user account with information: {}", userAccountData);

        if (emailExist(userAccountData.getEmail())) {
            LOGGER.debug("Email: {} exists. Throwing exception.", userAccountData.getEmail());
            throw new DuplicateEmailException("The email address: " + userAccountData.getEmail() + " is already in use.");
        }

        LOGGER.debug("Email: {} does not exist. Continuing registration.", userAccountData.getEmail());

        String encodedPassword = encodePassword(userAccountData);

        User.Builder user = User.getBuilder()
                .email(userAccountData.getEmail())
                .firstName(userAccountData.getFirstName())
                .lastName(userAccountData.getLastName())
                .password(encodedPassword);

        if (userAccountData.isSocialSignIn()) {
            user.signInProvider(userAccountData.getSignInProvider());
        }

        User registered = user.build();

        LOGGER.debug("Persisting new user with information: {}", registered);

        return repository.save(registered);
    }

    private boolean emailExist(String email) {
        LOGGER.debug("Checking if email {} is already found from the database.", email);

        User user = repository.findByEmail(email);

        if (user != null) {
            LOGGER.debug("User account: {} found with email: {}. Returning true.", user, email);
            return true;
        }

        LOGGER.debug("No user account found with email: {}. Returning false.", email);

        return false;
    }

    private String encodePassword(RegistrationForm dto) {
        String encodedPassword = null;

        if (dto.isNormalRegistration()) {
            LOGGER.debug("Registration is normal registration. Encoding password.");
            encodedPassword = passwordEncoder.encode(dto.getPassword());
        }

        return encodedPassword;
    }
}
