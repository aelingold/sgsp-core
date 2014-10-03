package org.ucema.sgsp.service;

import java.util.Map;

import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import freemarker.template.Configuration;

@Service
public class MailService {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(MailService.class);
	@Autowired
	private JavaMailSender javaMailSender;
	@Autowired
	private SimpleMailMessage templateMessage;
	@Autowired
	private Configuration configuration;

	public void sendEmail(String to, String from, String subject,
			String templatePath, Map<String, Object> model) {

		try {

			// Merge the model into the template
			String result = "";
			result = FreeMarkerTemplateUtils.processTemplateIntoString(
					configuration.getTemplate(templatePath), model);

			MimeMessage message = javaMailSender.createMimeMessage();

			message.setSubject(subject);
			MimeMessageHelper helper;
			helper = new MimeMessageHelper(message, true);
			helper.setFrom(from);
			helper.setTo(to);
			helper.setText(result, true);
			javaMailSender.send(message);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
	}

	public void sendEmail(String to, String message) {
		SimpleMailMessage msg = new SimpleMailMessage(this.templateMessage);
		msg.setTo(to);
		msg.setText(message);
		try {
			this.javaMailSender.send(msg);
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
			this.javaMailSender.send(msg);
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
			this.javaMailSender.send(msg);
		} catch (MailException e) {
			LOGGER.error(e.getMessage(), e);
		}
	}
}
