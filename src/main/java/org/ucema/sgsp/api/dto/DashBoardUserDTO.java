package org.ucema.sgsp.api.dto;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.ucema.sgsp.common.validator.PasswordsNotEmpty;
import org.ucema.sgsp.common.validator.PasswordsNotEqual;

@PasswordsNotEmpty(
		triggerFieldName = "password",
        passwordFieldName = "newPassword",
        passwordVerificationFieldName = "newPasswordVerification"
)
@PasswordsNotEqual(
        passwordFieldName = "newPassword",
        passwordVerificationFieldName = "newPasswordVerification"
)
public class DashBoardUserDTO {

    public static final String FIELD_NAME_EMAIL = "email";
    public static final String FIELD_NAME_WORK_AREAS = "workAreaCodes";
    public static final String FIELD_NAME_TELEPHONE = "telephone";

    @Email
    @NotEmpty
    @Size(max = 100)
    private String email;

    @NotEmpty
    @Size(max = 100)
    private String firstName;

    @NotEmpty
    @Size(max = 100)
    private String lastName;
    
    @Size(max = 100)
    private String telephone;
    
    private String password;
    
    private String newPassword;

    private String newPasswordVerification;
    
    private Boolean isProfessional;
    
    private String ratePlanCode;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getNewPasswordVerification() {
		return newPasswordVerification;
	}

	public void setNewPasswordVerification(String newPasswordVerification) {
		this.newPasswordVerification = newPasswordVerification;
	}

	public Boolean getIsProfessional() {
		return isProfessional;
	}

	public void setIsProfessional(Boolean isProfessional) {
		this.isProfessional = isProfessional;
	}

	public String getRatePlanCode() {
		return ratePlanCode;
	}

	public void setRatePlanCode(String ratePlanCode) {
		this.ratePlanCode = ratePlanCode;
	}

	@Override
	public String toString() {
		return "DashBoardUserDTO [email=" + email + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", telephone=" + telephone
				+ ", password=" + password + ", newPassword=" + newPassword
				+ ", newPasswordVerification=" + newPasswordVerification
				+ ", isProfessional=" + isProfessional + ", ratePlanCode="
				+ ratePlanCode + "]";
	}
}
