package org.ucema.sgsp.service;

import java.util.List;

import org.springframework.social.connect.Connection;
import org.ucema.sgsp.api.dto.DashBoardUserDTO;
import org.ucema.sgsp.api.dto.RegistrationDTO;
import org.ucema.sgsp.api.dto.UserDTO;
import org.ucema.sgsp.exception.DuplicateEmailException;
import org.ucema.sgsp.security.model.User;

public interface UserService {
	
	RegistrationDTO createRegistrationDTO(Connection<?> connection);
	
    User registerNewUserAccount(RegistrationDTO userAccountData) throws DuplicateEmailException;
    
    List<UserDTO> list();
    
    UserDTO saveOrUpdate(UserDTO user);
    
    void update(DashBoardUserDTO dashBoardUserDTO);
    
    void delete(Long id);
    
    UserDTO get(Long id);
    
    UserDTO findByEmail(String email);
}
