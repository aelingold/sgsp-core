package org.ucema.sgsp.service;

import java.util.List;

import org.ucema.sgsp.api.dto.RegistrationDTO;
import org.ucema.sgsp.api.dto.UserDTO;
import org.ucema.sgsp.exception.DuplicateEmailException;
import org.ucema.sgsp.security.model.User;

public interface UserService {
	 
    User registerNewUserAccount(RegistrationDTO userAccountData) throws DuplicateEmailException;
    
    List<UserDTO> list();
    
    UserDTO saveOrUpdate(UserDTO user);
    
    void delete(UserDTO user);
    
    void delete(Long id);
    
    UserDTO get(Long id);
    
    UserDTO findByEmail(String email);
}
