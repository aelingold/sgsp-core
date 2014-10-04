package org.ucema.sgsp.config;

import java.util.Properties;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.ucema.sgsp.security.config.PersistenceConfig;
import org.ucema.sgsp.security.config.SecurityConfig;
import org.ucema.sgsp.security.config.SocialConfig;

@Configuration
@ComponentScan(basePackages = { "org.ucema.sgsp" })
@Import({ WebMvcConfiguration.class, PersistenceConfig.class,
		SecurityConfig.class, SocialConfig.class })
@PropertySource({"classpath:application.properties"})
public class Config {

	@Bean
	public MessageSource messageSource() {
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();

		messageSource.setBasename("i18n/messages");
		messageSource.setUseCodeAsDefaultMessage(true);

		return messageSource;
	}

	@Bean
	public PropertySourcesPlaceholderConfigurer propertyPlaceHolderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}
	
	@Bean
	public JavaMailSenderImpl mailSender(){
		JavaMailSenderImpl javaMailSenderImpl = new JavaMailSenderImpl();
		javaMailSenderImpl.setHost("email-smtp.us-west-2.amazonaws.com");
		javaMailSenderImpl.setPort(587);
		javaMailSenderImpl.setUsername("AKIAIUWTPZNGYUOY4QGQ");
		javaMailSenderImpl.setPassword("AtHmUW1MCdQ9Qzj0EPdMXmNm2glvwzvH+0/JqUle/rNh");
		Properties properties = new Properties();
		properties.put("mail.smtp.auth", true);
		properties.put("mail.smtp.starttls.enable", true);
		properties.put("mail.smtp.ssl.trust", "email-smtp.us-west-2.amazonaws.com");
		javaMailSenderImpl.setJavaMailProperties(properties);
		return javaMailSenderImpl;
	}
	
	@Bean
	public SimpleMailMessage templateMessage(){
		SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
		simpleMailMessage.setFrom("contacto@singuia.com");
		simpleMailMessage.setSubject("Realizar un presupuesto");
		
		return simpleMailMessage;
	}
}
