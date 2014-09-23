package org.ucema.sgsp.security.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.ucema.sgsp.security.model.CustomUserDetails;
import org.ucema.sgsp.security.model.User;

public class RepositoryUserDetailsService implements UserDetailsService {

	private UserRepository repository;

	@Autowired
	public RepositoryUserDetailsService(UserRepository repository) {
		this.repository = repository;
	}

	@Transactional
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		User user = repository.findByEmail(username);

		if (user == null) {
			throw new UsernameNotFoundException("No user found with username: "
					+ username);
		}

		if (!user.getIsEnabled()) {
			throw new UsernameNotFoundException("No user found with username: "
					+ username);
		}

		CustomUserDetails principal = CustomUserDetails
				.getBuilder()
				.firstName(user.getFirstName())
				.id(user.getId())
				.lastName(user.getLastName())
				.password(user.getPassword())
				.role(user.getRole())
				.socialSignInProvider(user.getSignInProvider())
				.username(user.getEmail())
				.telephone(user.getTelephone())
				.isProfessional(user.getIsProfessional())
				.workAreasCodes(user.getWorkAreas())
				.country(user.getCountry().getCode())
				.cityCodes(user.getUserWorkZones())
				.ratePlanCode(
						user.getUserRatePlan() != null ? user.getUserRatePlan()
								.getRatePlan().getCode() : null).build();

		return principal;
	}
}