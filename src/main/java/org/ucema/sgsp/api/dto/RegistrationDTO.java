package org.ucema.sgsp.api.dto;

import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.ucema.sgsp.common.validator.PasswordsNotEmpty;
import org.ucema.sgsp.common.validator.PasswordsNotEqual;
import org.ucema.sgsp.security.model.SocialMediaService;

/**
 * @author Petri Kainulainen
 */
@PasswordsNotEmpty(
        triggerFieldName = "signInProvider",
        passwordFieldName = "password",
        passwordVerificationFieldName = "passwordVerification"
)
@PasswordsNotEqual(
        passwordFieldName = "password",
        passwordVerificationFieldName = "passwordVerification"
)
public class RegistrationDTO {

    public static final String FIELD_NAME_EMAIL = "email";

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
    
    @NotNull
    private UserTypeDTO userType;
    
    private List<Long> workAreaIds;
    
    private List<String> workAreaCodes;

    private String password;

    private String passwordVerification;

    private SocialMediaService signInProvider;

    public RegistrationDTO() {
    }

    public boolean isNormalRegistration() {
        return signInProvider == null;
    }

    public boolean isSocialSignIn() {
        return signInProvider != null;
    }

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordVerification() {
        return passwordVerification;
    }

    public void setPasswordVerification(String passwordVerification) {
        this.passwordVerification = passwordVerification;
    }

    public SocialMediaService getSignInProvider() {
        return signInProvider;
    }

    public void setSignInProvider(SocialMediaService signInProvider) {
        this.signInProvider = signInProvider;
    }

	public List<Long> getWorkAreaIds() {
		return workAreaIds;
	}

	public void setWorkAreaIds(List<Long> workAreaIds) {
		this.workAreaIds = workAreaIds;
	}

	public UserTypeDTO getUserType() {
		return userType;
	}

	public void setUserType(UserTypeDTO userType) {
		this.userType = userType;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public List<String> getWorkAreaCodes() {
		return workAreaCodes;
	}

	public void setWorkAreaCodes(List<String> workAreaCodes) {
		this.workAreaCodes = workAreaCodes;
	}
}
