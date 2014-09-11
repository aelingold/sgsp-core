package org.ucema.sgsp.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

@Service
public class MailService {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(MailService.class);
	@Autowired
	private MailSender mailSender;
	@Autowired
	private SimpleMailMessage templateMessage;

	public void sendEmail(String to, String message) {
		SimpleMailMessage msg = new SimpleMailMessage(this.templateMessage);
		msg.setTo(to);
		msg.setText(message);
		try {
			this.mailSender.send(msg);
		} catch (MailException e) {
			LOGGER.error(e.getMessage(), e);
		}
	}

	public void sendEmail(String to, String subject, String message) {
		SimpleMailMessage msg = new SimpleMailMessage(this.templateMessage);
		msg.setTo(to);
		msg.setSubject(subject);
		msg.setText(message);
		try {
			this.mailSender.send(msg);
		} catch (MailException e) {
			LOGGER.error(e.getMessage(), e);
		}
	}

	public void sendEmail(String to, String from, String subject, String message) {
		SimpleMailMessage msg = new SimpleMailMessage();
		msg.setTo(to);
		msg.setFrom(from);
		msg.setText(message);
		msg.setSubject(subject);
		try {
			this.mailSender.send(msg);
		} catch (MailException e) {
			LOGGER.error(e.getMessage(), e);
		}
	}
}
