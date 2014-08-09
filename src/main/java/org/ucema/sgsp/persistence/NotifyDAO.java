package org.ucema.sgsp.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.ucema.sgsp.persistence.model.Notify;

public interface NotifyDAO extends JpaRepository<Notify, Long> {

}
