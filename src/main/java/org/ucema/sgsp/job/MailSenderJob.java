package org.ucema.sgsp.job;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.ucema.sgsp.api.dto.MailDTO;
import org.ucema.sgsp.persistence.model.MailStatusType;
import org.ucema.sgsp.service.MailSenderService;
import org.ucema.sgsp.service.MailService;


@Component
public class MailSenderJob {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(MailSenderJob.class);
	
	@Autowired
	private MailService mailService;
	@Autowired
	private MailSenderService mailSenderService;
	
	@Scheduled(fixedRate = 30000)
	public void sendMails() {
		LOGGER.info("Sending pending mails");
		
		List<MailDTO> mails = mailService.findByStatusType(MailStatusType.PENDING);
		mails.forEach(m -> {
			
			mailSenderService.sendEmail(m);			
			mailService.updateStatus(m.getId(), MailStatusType.SENT);
		});
	}
}
