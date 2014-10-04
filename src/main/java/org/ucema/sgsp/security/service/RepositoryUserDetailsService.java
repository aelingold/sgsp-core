package org.ucema.sgsp.security.service;

import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.ucema.sgsp.security.model.CustomUserDetails;
import org.ucema.sgsp.security.model.User;
import org.ucema.sgsp.security.persistence.UserRepository;
import org.ucema.sgsp.security.util.SecurityUtil;

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

		CustomUserDetails principal = SecurityUtil.customUserDetails(
				user.getFirstName(),
				user.getId(),
				user.getLastName(),
				user.getPassword(),
				user.getRole(),
				user.getSignInProvider(),
				user.getEmail(),
				user.getTelephone(),
				user.getIsProfessional(),
				user.getWorkAreas() != null ? user.getWorkAreas().stream()
						.map(wa -> wa.getCode()).collect(Collectors.toList())
						: null,
				user.getCountry().getCode(),
				user.getUserWorkZones() != null ? user.getUserWorkZones()
						.stream().map(uwz -> uwz.getCity().getCode())
						.collect(Collectors.toList()) : null, user
						.getUserRatePlan() != null ? user.getUserRatePlan()
						.getRatePlan().getCode() : null);

		return principal;
	}
}