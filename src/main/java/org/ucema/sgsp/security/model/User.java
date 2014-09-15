package org.ucema.sgsp.security.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.ucema.sgsp.persistence.model.Country;
import org.ucema.sgsp.persistence.model.UserWorkRate;
import org.ucema.sgsp.persistence.model.UserWorkZone;
import org.ucema.sgsp.persistence.model.WorkArea;

@Entity
@Table(name = "user_accounts")
public class User extends BaseEntity<Long> {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "email", length = 100, nullable = false, unique = true)
	private String email;

	@Column(name = "first_name", length = 100, nullable = false)
	private String firstName;

	@Column(name = "last_name", length = 100, nullable = false)
	private String lastName;

	@Column(name = "password", length = 255)
	private String password;

	@Enumerated(EnumType.STRING)
	@Column(name = "role", length = 20, nullable = false)
	private Role role;

	@Enumerated(EnumType.STRING)
	@Column(name = "sign_in_provider", length = 20)
	private SocialMediaService signInProvider;

	private String telephone;

	@Column(name = "is_enabled")
	private Boolean isEnabled;
	
	@OneToOne
	@JoinColumn(name = "country_id", foreignKey = @ForeignKey(name = "fk_country_user"))
	private Country country;

	@ManyToMany
	@JoinTable(name = "user_work_areas", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "work_area_id"))
	private List<WorkArea> workAreas;
	
	@OneToMany(mappedBy = "user")
	private List<UserWorkRate> userWorkRates;
	
	@Column(name = "is_professional")
	private boolean isProfessional;
	
	@OneToMany
	@Cascade(org.hibernate.annotations.CascadeType.ALL)
	@JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "fk_user_work_zone_user"))
	private List<UserWorkZone> userWorkZones;	

	public User() {
	}

	public User(Long id) {
		super();
		this.id = id;
	}

	public static Builder getBuilder() {
		return new Builder();
	}

	@Override
	public Long getId() {
		return id;
	}

	public String getEmail() {
		return email;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getPassword() {
		return password;
	}

	public Role getRole() {
		return role;
	}

	public SocialMediaService getSignInProvider() {
		return signInProvider;
	}

	public String getTelephone() {
		return telephone;
	}

	public List<WorkArea> getWorkAreas() {
		return workAreas;
	}

	public List<UserWorkRate> getUserWorkRates() {
		return userWorkRates;
	}

	public Boolean getIsProfessional() {
		return isProfessional;
	}

	public Boolean getIsEnabled() {
		return isEnabled;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public void setSignInProvider(SocialMediaService signInProvider) {
		this.signInProvider = signInProvider;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public void setWorkAreas(List<WorkArea> workAreas) {
		this.workAreas = workAreas;
	}

	public void setUserWorkRates(List<UserWorkRate> userWorkRates) {
		this.userWorkRates = userWorkRates;
	}

	public void setIsProfessional(boolean isProfessional) {
		this.isProfessional = isProfessional;
	}

	public void setIsEnabled(Boolean isEnabled) {
		this.isEnabled = isEnabled;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public List<UserWorkZone> getUserWorkZones() {
		return userWorkZones;
	}

	public void setUserWorkZones(List<UserWorkZone> userWorkZones) {
		this.userWorkZones = userWorkZones;
	}

	public static class Builder {

		private User user;

		public Builder() {
			user = new User();
			user.role = Role.ROLE_USER;
		}

		public Builder email(String email) {
			user.email = email;
			return this;
		}

		public Builder firstName(String firstName) {
			user.firstName = firstName;
			return this;
		}

		public Builder lastName(String lastName) {
			user.lastName = lastName;
			return this;
		}

		public Builder password(String password) {
			user.password = password;
			return this;
		}

		public Builder signInProvider(SocialMediaService signInProvider) {
			user.signInProvider = signInProvider;
			return this;
		}

		public Builder country(Country country) {
			user.country = country;
			return this;
		}		
		
		public Builder telephone(String telephone) {
			user.telephone = telephone;
			return this;
		}

		public Builder professional(boolean isProfessional) {
			user.isProfessional = isProfessional;
			return this;
		}

		public Builder enabled(boolean isEnabled) {
			user.isEnabled = isEnabled;
			return this;
		}

		public Builder workAreas(List<WorkArea> workAreas) {
			user.workAreas = workAreas;
			return this;
		}

		public Builder userWorkRates(List<UserWorkRate> userWorkRates) {
			user.userWorkRates = userWorkRates;
			return this;
		}
		
		public Builder userWorkZones(List<UserWorkZone> userWorkZones) {
			user.userWorkZones = userWorkZones;
			return this;
		}		

		public User build() {
			return user;
		}
	}
}