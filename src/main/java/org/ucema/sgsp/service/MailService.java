package org.ucema.sgsp.service;

import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.ucema.sgsp.api.dto.MailDTO;
import org.ucema.sgsp.api.transformation.MailTransformation;
import org.ucema.sgsp.persistence.MailDAO;
import org.ucema.sgsp.persistence.model.Mail;
import org.ucema.sgsp.persistence.model.MailStatusType;

@Service
public class MailService {

	public static final String FROM_EMAIL = "no-reply@singuia.com";

	@Autowired
	private MailDAO mailDAO;
	@Autowired
	private MailTransformation mailTransformation;

	@Transactional
	public void updateStatus(Long id, MailStatusType statusType) {
		Mail mail = mailDAO.getOne(id);
		if (mail == null) {
			throw new RuntimeException("mail not found");
		}		
		
		mail.setStatusType(statusType);
		mailDAO.save(mail);
	}	
	
	@Transactional
	public List<MailDTO> findByStatusType(MailStatusType mailStatusType) {
		return mailTransformation.transformToApi(mailDAO
				.findByStatusType(mailStatusType));
	}

	@Transactional
	public void save(String to, String from, String subject,
			String templatePath, Map<String, Object> model) {

		Mail mail = new Mail();
		mail.setMailFrom(from);
		mail.setMailTo(to);
		mail.setModel(JSONObject.toJSONString(model));
		mail.setStatusType(MailStatusType.PENDING);
		mail.setSubject(subject);
		mail.setTemplatePath(templatePath);

		mailDAO.save(mail);
	}
}
