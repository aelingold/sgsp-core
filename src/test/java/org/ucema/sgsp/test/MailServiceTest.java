package org.ucema.sgsp.test;

import java.util.HashMap;
import java.util.Map;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.ucema.sgsp.BaseTest;
import org.ucema.sgsp.service.MailService;

@Ignore
public class MailServiceTest extends BaseTest {

	@Autowired
	private MailService mailService;

	@Test
	public void sendTestEmail() {

		String to = "alexis.elingold@gmail.com";
		String subject = "Nuevo presupuesto";

		Map<String, Object> model = new HashMap<String, Object>();
		model.put("orderFirstName", "Juan");
		model.put("orderLastName", "Gonzalez");

		mailService.sendEmail(to, MailService.FROM_EMAIL, subject,
				"mail/sendOrder.ftl", model);
	}
}
