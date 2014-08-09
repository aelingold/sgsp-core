package org.ucema.sgsp.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.ucema.sgsp.security.config.SecurityConfig;
import org.ucema.sgsp.security.persistence.PersistenceContext;
import org.ucema.sgsp.security.persistence.SocialContext;

@Configuration
@ComponentScan(basePackages = { "org.ucema.sgsp" })
@Import({ WebMvcConfiguration.class, PersistenceContext.class,
		SecurityConfig.class, SocialContext.class })
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
}
