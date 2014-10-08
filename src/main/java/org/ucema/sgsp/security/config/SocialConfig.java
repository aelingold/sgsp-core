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
import org.springframework.social.connect.web.ConnectController;
import org.springframework.social.connect.web.ProviderSignInController;
import org.springframework.social.facebook.connect.FacebookConnectionFactory;
import org.springframework.social.security.AuthenticationNameUserIdSource;
import org.springframework.social.twitter.connect.TwitterConnectionFactory;
import org.ucema.sgsp.controller.CustomConnectController;
import org.ucema.sgsp.security.adapter.SpringSecuritySignInAdapter;
import org.ucema.sgsp.security.service.CustomsUsersConnectionRepository;
import org.ucema.sgsp.security.service.UserConnectionService;
import org.ucema.sgsp.service.UserService;

@Configuration
@EnableSocial
public class SocialConfig implements SocialConfigurer {

	@Autowired
	private DataSource dataSource;
	@Autowired
	private UserService userService;
	@Autowired
	private UserConnectionService userConnectionService;

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
		return new CustomsUsersConnectionRepository(userConnectionService,
				connectionFactoryLocator, Encryptors.noOpText());
	}
	
//    /**
//     * Request-scoped data access object providing access to the current user's connections.
//     */
//    @Bean
//    @Scope(value = "request", proxyMode = ScopedProxyMode.INTERFACES)
//    public ConnectionRepository connectionRepository(UsersConnectionRepository usersConnectionRepository) {
//		
//    	Authentication auth = SecurityContextHolder.getContext()
//				.getAuthentication();
//
//		CustomUserDetails userDetails = (CustomUserDetails) auth.getPrincipal();
//		
//        return usersConnectionRepository.createConnectionRepository(userDetails.getUsername());
//    }

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
		ProviderSignInController providerSignInController = new ProviderSignInController(
				connectionFactoryLocator, usersConnectionRepository,
				new SpringSecuritySignInAdapter(userService));

		providerSignInController.setSignInUrl("/register");
		providerSignInController.setSignUpUrl("/register");

		return providerSignInController;
	}
}
