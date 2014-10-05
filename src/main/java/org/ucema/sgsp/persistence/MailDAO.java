package org.ucema.sgsp.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.ucema.sgsp.persistence.model.Mail;
import org.ucema.sgsp.persistence.model.MailStatusType;

public interface MailDAO extends JpaRepository<Mail, Long> {

	List<Mail> findByStatusType(MailStatusType mailStatusType);
}
