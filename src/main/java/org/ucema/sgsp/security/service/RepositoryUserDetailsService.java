package org.ucema.sgsp.security.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.ucema.sgsp.persistence.model.WorkArea;
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

		CustomUserDetails principal = CustomUserDetails.getBuilder()
				.firstName(user.getFirstName()).id(user.getId())
				.lastName(user.getLastName()).password(user.getPassword())
				.role(user.getRole())
				.socialSignInProvider(user.getSignInProvider())
				.username(user.getEmail()).telephone(user.getTelephone())
				.isProfessional(user.getIsProfessional())
				.workAreasCodes(buildWorkAreaCodes(user.getWorkAreas()))
				.country(user.getCountry().getCode()).build();

		return principal;
	}

	private List<String> buildWorkAreaCodes(List<WorkArea> workAreas) {

		List<String> result = new ArrayList<String>();

		if (workAreas != null) {
			for (WorkArea workArea : workAreas) {
				result.add(workArea.getCode());
			}
		}

		return result;
	}
}