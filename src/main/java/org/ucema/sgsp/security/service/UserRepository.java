package org.ucema.sgsp.security.service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.ucema.sgsp.security.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
 
    User findByEmail(String email);
    
    List<User> findByWorkAreas_CodeAndIsEnabled(List<String> codes, Boolean isEnabled);
}
