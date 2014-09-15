package org.ucema.sgsp.api.dto;

import java.io.Serializable;
import java.util.List;

import org.ucema.sgsp.security.model.Role;

public class UserDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private String firstName;
	private String lastName;
	private String email;
	private String telephone;
	private String password;
	private List<WorkAreaDTO> workAreas;
	private List<UserWorkRateDTO> userWorkRates;
	private Boolean isProfessional;
	private Role role;
	private String countryCode;
	private List<String> cityCodes;
	
	public static UserDTO newInstance() {
		return new UserDTO();
	}

	public UserDTO build() {
		UserDTO result = new UserDTO();

		result.setId(id);
		result.setEmail(email);
		result.setFirstName(firstName);
		result.setIsProfessional(isProfessional);
		result.setLastName(lastName);
		result.setPassword(password);
		result.setRole(role);
		result.setTelephone(telephone);
		result.setUserWorkRates(userWorkRates);
		result.setWorkAreas(workAreas);
		result.setCountryCode(countryCode);

		return result;
	}
	
	public UserDTO withCityCodes(List<String> cityCodes) {
		this.cityCodes = cityCodes;
		return this;
	}	
	
	public UserDTO withRole(Role role) {
		this.role = role;
		return this;
	}

	public UserDTO withCountryCode(String countryCode) {
		this.countryCode = countryCode;
		return this;
	}	
	
	public UserDTO withTelephone(String telephone) {
		this.telephone = telephone;
		return this;
	}	
	
	public UserDTO withFirstName(String firstName) {
		this.firstName = firstName;
		return this;
	}
	
	public UserDTO withLastName(String lastName) {
		this.lastName = lastName;
		return this;
	}	
	
	public UserDTO withEmail(String email) {
		this.email = email;
		return this;
	}	
	
	public UserDTO withIsProfessionl(Boolean isProfessional) {
		this.isProfessional = isProfessional;
		return this;
	}	
	
	public UserDTO withId(Long id) {
		this.id = id;
		return this;
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean getIsProfessional() {
		return isProfessional;
	}

	public void setIsProfessional(Boolean isProfessional) {
		this.isProfessional = isProfessional;
	}

	public List<WorkAreaDTO> getWorkAreas() {
		return workAreas;
	}

	public void setWorkAreas(List<WorkAreaDTO> workAreas) {
		this.workAreas = workAreas;
	}

	public List<UserWorkRateDTO> getUserWorkRates() {
		return userWorkRates;
	}

	public void setUserWorkRates(List<UserWorkRateDTO> userWorkRates) {
		this.userWorkRates = userWorkRates;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public List<String> getCityCodes() {
		return cityCodes;
	}

	public void setCityCodes(List<String> cityCodes) {
		this.cityCodes = cityCodes;
	}
}
