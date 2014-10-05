package org.ucema.sgsp.api.transformation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.ucema.sgsp.api.dto.MailDTO;
import org.ucema.sgsp.persistence.model.Mail;

import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class MailTransformation {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(MailTransformation.class);	
	
	public List<MailDTO> transformToApi(
			List<Mail> mails) {
		List<MailDTO> result = new ArrayList<MailDTO>();

		for (Mail mail : mails) {
			result.add(transformToApi(mail));
		}

		return result;
	}

	@SuppressWarnings("unchecked")
	public MailDTO transformToApi(Mail mail) {
		MailDTO result = new MailDTO();

		result.setId(mail.getId());
		result.setMailFrom(mail.getMailFrom());
		result.setMailTo(mail.getMailTo());
		
		ObjectMapper om = new ObjectMapper();
		HashMap<String, Object> model;
		try {
			model = om.readValue(mail.getModel(), HashMap.class);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(),e);
			throw new RuntimeException(e);
		}		
		result.setModel(model);
		
		result.setStatusType(mail.getStatusType().name());
		result.setSubject(mail.getSubject());

		return result;
	}
}
