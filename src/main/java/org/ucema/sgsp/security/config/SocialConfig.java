package org.ucema.sgsp.security.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.social.UserIdSource;
import org.springframework.social.config.annotation.ConnectionFactoryConfigurer;
import org.springframework.social.config.annotation.EnableSocial;
import org.springframework.social.config.annotation.SocialConfigurer;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.jdbc.JdbcUsersConnectionRepository;
import org.springframework.social.connect.web.ConnectController;
import org.springframework.social.connect.web.ProviderSignInController;
import org.springframework.social.facebook.connect.FacebookConnectionFactory;
import org.springframework.social.security.AuthenticationNameUserIdSource;
import org.springframework.social.twitter.connect.TwitterConnectionFactory;
import org.ucema.sgsp.controller.CustomConnectController;
import org.ucema.sgsp.security.adapter.SpringSecuritySignInAdapter;
import org.ucema.sgsp.service.UserService;

@Configuration
@EnableSocial
public class SocialConfig implements SocialConfigurer {

	@Autowired
	private DataSource dataSource;
	@Autowired
	private UserService userService;

	public void addConnectionFactories(ConnectionFactoryConfigurer cfConfig,
			Environment env) {
		cfConfig.addConnectionFactory(new TwitterConnectionFactory(env
				.getProperty("twitter.consumer.key"), env
				.getProperty("twitter.consumer.secret")));

		FacebookConnectionFactory facebookConnectionFactory = new FacebookConnectionFactory(
				env.getProperty("facebook.app.id"),
				env.getProperty("facebook.app.secret"));
		facebookConnectionFactory
				.setScope("public_profile,email,offline_access,user_friends");

		cfConfig.addConnectionFactory(facebookConnectionFactory);
	}

	public UserIdSource getUserIdSource() {
		return new AuthenticationNameUserIdSource();
	}

	public UsersConnectionRepository getUsersConnectionRepository(
			ConnectionFactoryLocator connectionFactoryLocator) {
		return new JdbcUsersConnectionRepository(dataSource,
				connectionFactoryLocator, Encryptors.noOpText());
	}

	@Bean
	public ConnectController connectController(
			ConnectionFactoryLocator connectionFactoryLocator,
			ConnectionRepository connectionRepository) {
		return new CustomConnectController(connectionFactoryLocator,
				connectionRepository);
	}

	@Bean
	public ProviderSignInController providerSignInController(
			ConnectionFactoryLocator connectionFactoryLocator,
			UsersConnectionRepository usersConnectionRepository) {
		ProviderSignInController providerSignInController = new ProviderSignInController(connectionFactoryLocator,
				usersConnectionRepository, new SpringSecuritySignInAdapter(userService));
		
		providerSignInController.setSignInUrl("/register");
		providerSignInController.setSignUpUrl("/register");
		
		return providerSignInController;
	}
}
