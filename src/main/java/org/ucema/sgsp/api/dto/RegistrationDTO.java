package org.ucema.sgsp.api.dto;

import java.util.ArrayList;
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
    
    @NotNull
    private Boolean isProfessional = false;
    
    private List<String> workAreaCodes = new ArrayList<String>();

    private String password;

    private String passwordVerification;

    private SocialMediaService signInProvider;
    
    private String countryCode;

    public RegistrationDTO() {
    }
    
	public static RegistrationDTO newInstance() {
		return new RegistrationDTO();
	}

	public RegistrationDTO build() {
		RegistrationDTO result = new RegistrationDTO();

		result.setCountryCode(countryCode);
		result.setEmail(email);
		result.setFirstName(firstName);
		result.setIsProfessional(isProfessional);
		result.setLastName(lastName);
		result.setPassword(password);
		result.setPasswordVerification(passwordVerification);
		result.setSignInProvider(signInProvider);
		result.setTelephone(telephone);
		result.setWorkAreaCodes(workAreaCodes);
		
		return result;
	}
	
	public RegistrationDTO withWorkAreaCodes(List<String> workAreaCodes) {
		this.workAreaCodes = workAreaCodes;
		return this;
	}	
	
	public RegistrationDTO withTelephone(String telephone) {
		this.telephone = telephone;
		return this;
	}	
	
	public RegistrationDTO withSignInProvider(SocialMediaService signInProvider) {
		this.signInProvider = signInProvider;
		return this;
	}	
	
	public RegistrationDTO withPasswordVerification(String passwordVerification) {
		this.passwordVerification = passwordVerification;
		return this;
	}	
	
	public RegistrationDTO withPassword(String password) {
		this.password = password;
		return this;
	}	
	
	public RegistrationDTO withLastName(String lastName) {
		this.lastName = lastName;
		return this;
	}	
	
	public RegistrationDTO withIsProfessional(Boolean isProfessional) {
		this.isProfessional = isProfessional;
		return this;
	}	
	
	public RegistrationDTO withFirstName(String firstName) {
		this.firstName = firstName;
		return this;
	}	
	
	public RegistrationDTO withEmail(String email) {
		this.email = email;
		return this;
	}	
	
	public RegistrationDTO withCountryCode(String countryCode) {
		this.countryCode = countryCode;
		return this;
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

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public Boolean getIsProfessional() {
		return isProfessional;
	}

	public void setIsProfessional(Boolean isProfessional) {
		this.isProfessional = isProfessional;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("RegistrationDTO [email=");
		builder.append(email);
		builder.append(", firstName=");
		builder.append(firstName);
		builder.append(", lastName=");
		builder.append(lastName);
		builder.append(", telephone=");
		builder.append(telephone);
		builder.append(", isProfessional=");
		builder.append(isProfessional);
		builder.append(", workAreaCodes=");
		builder.append(workAreaCodes);
		builder.append(", password=");
		builder.append(password);
		builder.append(", passwordVerification=");
		builder.append(passwordVerification);
		builder.append(", signInProvider=");
		builder.append(signInProvider);
		builder.append(", countryCode=");
		builder.append(countryCode);
		builder.append("]");
		return builder.toString();
	}
}
