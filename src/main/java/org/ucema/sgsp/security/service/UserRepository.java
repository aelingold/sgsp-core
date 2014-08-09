package org.ucema.sgsp.security.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.ucema.sgsp.security.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
 
    public User findByEmail(String email);
}
