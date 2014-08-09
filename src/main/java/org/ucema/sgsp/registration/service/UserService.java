package org.ucema.sgsp.registration.service;

import java.util.List;

import org.ucema.sgsp.api.dto.UserDTO;
import org.ucema.sgsp.exception.DuplicateEmailException;
import org.ucema.sgsp.registration.dto.RegistrationForm;
import org.ucema.sgsp.security.model.User;

public interface UserService {
	 
    public User registerNewUserAccount(RegistrationForm userAccountData) throws DuplicateEmailException;
    
    public List<UserDTO> list();
    
    public UserDTO saveOrUpdate(UserDTO user);
    
    public void delete(UserDTO user);
    
    public void delete(Long id);
    
    public UserDTO get(Long id);
}
