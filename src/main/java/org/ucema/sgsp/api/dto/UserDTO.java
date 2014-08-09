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

	public UserDTO() {
		super();
	}

	public UserDTO(Long id, String firstName, String lastName, String email,
			String telephone, String password, List<WorkAreaDTO> workAreas,
			List<UserWorkRateDTO> userWorkRates, Boolean isProfessional,
			Role role) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.telephone = telephone;
		this.password = password;
		this.workAreas = workAreas;
		this.userWorkRates = userWorkRates;
		this.isProfessional = isProfessional;
		this.role = role;
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

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("UserDTO [id=");
		builder.append(id);
		builder.append(", firstName=");
		builder.append(firstName);
		builder.append(", lastName=");
		builder.append(lastName);
		builder.append(", email=");
		builder.append(email);
		builder.append(", telephone=");
		builder.append(telephone);
		builder.append(", password=");
		builder.append(password);
		builder.append(", workAreas=");
		builder.append(workAreas);
		builder.append(", userWorkRates=");
		builder.append(userWorkRates);
		builder.append(", isProfessional=");
		builder.append(isProfessional);
		builder.append(", role=");
		builder.append(role);
		builder.append("]");
		return builder.toString();
	}
}
