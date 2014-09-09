package org.ucema.sgsp.test;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.ucema.sgsp.BaseTest;
import org.ucema.sgsp.service.MailService;

@Ignore
public class MailServiceTest extends BaseTest{

	@Autowired
	private MailService mailService;

	@Test
	public void sendTestEmail() {
		mailService.sendEmail("info@singuia.com", "prueba amazon ec2 singuia");
	}
}
