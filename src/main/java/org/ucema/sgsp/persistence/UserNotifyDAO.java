package org.ucema.sgsp.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.ucema.sgsp.persistence.model.UserNotify;

public interface UserNotifyDAO extends JpaRepository<UserNotify, Long> {

}
