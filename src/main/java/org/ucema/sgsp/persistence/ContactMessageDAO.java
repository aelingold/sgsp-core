package org.ucema.sgsp.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.ucema.sgsp.persistence.model.ContactMessage;

public interface ContactMessageDAO extends JpaRepository<ContactMessage, Long> {

}
