package org.ucema.sgsp.security.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.social.security.SocialUser;
import org.ucema.sgsp.persistence.model.UserWorkZone;
import org.ucema.sgsp.persistence.model.WorkArea;

public class CustomUserDetails extends SocialUser {

	private static final long serialVersionUID = 1L;
	private Long id;
	private String firstName;
	private String lastName;
	private Role role;
	private SocialMediaService socialSignInProvider;
	private String telephone;
	private boolean isProfessional;
	private List<String> workAreaCodes;
	private String countryCode;
	private List<String> cityCodes;
	private String ratePlanCode;

	public static Builder getBuilder() {
		return new Builder();
	}

	public CustomUserDetails(String username, String password,
			Collection<? extends GrantedAuthority> authorities) {
		super(username, password, authorities);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public SocialMediaService getSocialSignInProvider() {
		return socialSignInProvider;
	}

	public void setSocialSignInProvider(SocialMediaService socialSignInProvider) {
		this.socialSignInProvider = socialSignInProvider;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public boolean isProfessional() {
		return isProfessional;
	}

	public void setProfessional(boolean isProfessional) {
		this.isProfessional = isProfessional;
	}

	public List<String> getWorkAreaCodes() {
		return workAreaCodes;
	}

	public void setWorkAreaCodes(List<String> workAreaCodes) {
		this.workAreaCodes = workAreaCodes;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public List<String> getCityCodes() {
		return cityCodes;
	}

	public void setCityCodes(List<String> cityCodes) {
		this.cityCodes = cityCodes;
	}

	public String getRatePlanCode() {
		return ratePlanCode;
	}

	public void setRatePlanCode(String ratePlanCode) {
		this.ratePlanCode = ratePlanCode;
	}

	public static class Builder {

		private Long id;
		private String username;
		private String firstName;
		private String lastName;
		private String password;
		private Role role;
		private SocialMediaService socialSignInProvider;
		private Set<GrantedAuthority> authorities;
		private String telephone;
		private boolean isProfessional;
		private List<String> workAreaCodes;
		private String countryCode;
		private List<String> cityCodes;
		private String ratePlanCode;

		public Builder() {
			this.authorities = new HashSet<GrantedAuthority>();
		}

		public Builder country(String countryCode) {
			this.countryCode = countryCode;
			return this;
		}

		public Builder telephone(String telephone) {
			this.telephone = telephone;
			return this;
		}
		
		public Builder cityCodes(List<String> cityCodes) {
			this.cityCodes = cityCodes;
			return this;		
		}

		public Builder userWorkZones(List<UserWorkZone> userWorkZones) {

			List<String> result = new ArrayList<String>();

			if (userWorkZones != null) {
				for (UserWorkZone userWorkZone : userWorkZones) {
					result.add(userWorkZone.getCity().getCode());
				}
			}

			this.cityCodes = result;
			return this;
		}

		public Builder ratePlanCode(String ratePlanCode) {
			this.ratePlanCode = ratePlanCode;
			return this;
		}
		
		public Builder workAreaCodes(List<String> workAreaCodes) {
			this.workAreaCodes = workAreaCodes;
			return this;
		}

		public Builder workAreas(List<WorkArea> workAreas) {

			List<String> result = new ArrayList<String>();

			if (workAreas != null) {
				for (WorkArea workArea : workAreas) {
					result.add(workArea.getCode());
				}
			}

			this.workAreaCodes = result;
			return this;
		}

		public Builder isProfessional(boolean isProfessional) {
			this.isProfessional = isProfessional;
			return this;
		}

		public Builder firstName(String firstName) {
			this.firstName = firstName;
			return this;
		}

		public Builder id(Long id) {
			this.id = id;
			return this;
		}

		public Builder lastName(String lastName) {
			this.lastName = lastName;
			return this;
		}

		public Builder password(String password) {
			if (password == null) {
				password = "SocialUser";
			}

			this.password = password;
			return this;
		}

		public Builder role(Role role) {
			this.role = role;

			SimpleGrantedAuthority authority = new SimpleGrantedAuthority(
					role.toString());
			this.authorities.add(authority);

			return this;
		}

		public Builder socialSignInProvider(
				SocialMediaService socialSignInProvider) {
			this.socialSignInProvider = socialSignInProvider;
			return this;
		}

		public Builder username(String username) {
			this.username = username;
			return this;
		}

		public CustomUserDetails build() {
			CustomUserDetails user = new CustomUserDetails(username, password,
					authorities);

			user.id = id;
			user.firstName = firstName;
			user.lastName = lastName;
			user.role = role;
			user.socialSignInProvider = socialSignInProvider;
			user.telephone = telephone;
			user.isProfessional = isProfessional;
			user.workAreaCodes = workAreaCodes;
			user.countryCode = countryCode;
			user.cityCodes = cityCodes;
			user.ratePlanCode = ratePlanCode;

			return user;
		}
	}
}
