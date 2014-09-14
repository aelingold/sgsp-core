package org.ucema.sgsp.security.model;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.social.security.SocialUser;
 
public class CustomUserDetails extends SocialUser {
 
	private static final long serialVersionUID = 1L;
	private Long id;
    private String firstName;
    private String lastName;
    private Role role;
    private SocialMediaService socialSignInProvider;
	private String telephone;
	private boolean isProfessional;
	private List<String> workAreasCodes;
	private String countryCode;
    
    public static Builder getBuilder() {
        return new Builder();
    }    
    
    public CustomUserDetails(String username, String password, Collection<? extends GrantedAuthority> authorities) {
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

	public List<String> getWorkAreasCodes() {
		return workAreasCodes;
	}

	public void setWorkAreasCodes(List<String> workAreasCodes) {
		this.workAreasCodes = workAreasCodes;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getCountryCode() {
		return countryCode;
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
    	private List<String> workAreasCodes;
    	private String countryCode;
 
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
        
        public Builder workAreasCodes(List<String> workAreasCodes) {
            this.workAreasCodes = workAreasCodes;
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
 
            SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role.toString());
            this.authorities.add(authority);
 
            return this;
        }
 
        public Builder socialSignInProvider(SocialMediaService socialSignInProvider) {
            this.socialSignInProvider = socialSignInProvider;
            return this;
        }
 
        public Builder username(String username) {
            this.username = username;
            return this;
        }
 
        public CustomUserDetails build() {
            CustomUserDetails user = new CustomUserDetails(username, password, authorities);
 
            user.id = id;
            user.firstName = firstName;
            user.lastName = lastName;
            user.role = role;
            user.socialSignInProvider = socialSignInProvider;
            user.telephone = telephone;
            user.isProfessional = isProfessional;
            user.workAreasCodes = workAreasCodes;
            user.countryCode = countryCode;
 
            return user;
        }
    }
}
