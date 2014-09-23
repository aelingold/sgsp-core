package org.ucema.sgsp.security.config;

import java.util.Arrays;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.access.vote.AffirmativeBased;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.PortResolverImpl;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;
import org.springframework.security.web.access.expression.WebExpressionVoter;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.security.web.authentication.rememberme.TokenBasedRememberMeServices;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.social.security.SocialUserDetailsService;
import org.springframework.social.security.SpringSocialConfigurer;
import org.ucema.sgsp.security.service.RepositoryUserDetailsService;
import org.ucema.sgsp.security.service.SimpleSocialUserDetailsService;
import org.ucema.sgsp.security.service.UserRepository;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private DataSource dataSource;

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth)
			throws Exception {
		auth.jdbcAuthentication().dataSource(dataSource);
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web
		// Spring Security ignores request to static resources such as CSS or JS
		// files.
		.ignoring().antMatchers("/static/**");
	}

	protected void configure(HttpSecurity http) throws Exception {
		http
		// Configures form login
		.formLogin()
				.loginPage("/login")
				.loginProcessingUrl("/login/authenticate")
				.failureUrl("/login?error=bad_credentials")
				// Configures the logout function
				.and()
				.logout()
				.deleteCookies("JSESSIONID")
				.logoutUrl("/logout")
				.logoutSuccessUrl("/login")
				// Configures url based uthorization
				.and()
				.requestCache()
				.requestCache(customRequestCache())
				.and()
				.rememberMe()
				.key("your_key")
				.rememberMeServices(rememberMeServices())
				.and()
				.authorizeRequests()
				.accessDecisionManager(
						defaultAccessDecisionManager(roleHierarchy()))
				// Anyone can access the urls
				.antMatchers("/connect/**", "/auth/**", "/login", "/signin/**",
						"/signup/**", "/user/register/**", "/register/**",
						"/workAreas/**", "/workAreaItems/**", "/users/**",
						"/cities/**", "/states/**", "/countries/**",
						"/currencies/**", "/quotes/**", "/quote-questions/**",
						"/user-work-zones/**", "/user-notifies/**",
						"/rate-plans/**", "/favicon.ico", "/")
				.permitAll()
				// The rest of the our application is protected.
				.antMatchers("/**")
				.hasRole("USER")
				// .antMatchers("/admin/**").hasRole("ADMIN")
				// .anyRequest().authenticated()
				// Adds the SocialAuthenticationFilter to Spring Security's
				// filter chain.
				.and().exceptionHandling().accessDeniedPage("/403").and()
				.apply(new SpringSocialConfigurer());
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth)
			throws Exception {
		auth.userDetailsService(userDetailsService()).passwordEncoder(
				passwordEncoder());
	}

	// @SuppressWarnings("rawtypes")
	// @Bean
	// public AffirmativeBased accessDecisionManager() {
	//
	// List<AccessDecisionVoter> decisionVoters = new
	// ArrayList<AccessDecisionVoter>();
	// WebExpressionVoter webExpressionVoter = new WebExpressionVoter();
	// decisionVoters.add(roleVoter());
	// decisionVoters.add(webExpressionVoter);
	//
	// AffirmativeBased affirmativeBased = new AffirmativeBased(decisionVoters);
	//
	// return affirmativeBased;
	// }

	@SuppressWarnings("rawtypes")
	@Bean
	public AffirmativeBased defaultAccessDecisionManager(
			RoleHierarchy roleHierarchy) {
		WebExpressionVoter webExpressionVoter = new WebExpressionVoter();
		DefaultWebSecurityExpressionHandler expressionHandler = new DefaultWebSecurityExpressionHandler();
		expressionHandler.setRoleHierarchy(roleHierarchy);
		webExpressionVoter.setExpressionHandler(expressionHandler);
		return new AffirmativeBased(
				Arrays.asList((AccessDecisionVoter) webExpressionVoter));
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(10);
	}

	@Bean
	public SocialUserDetailsService socialUserDetailsService() {
		return new SimpleSocialUserDetailsService(userDetailsService());
	}

	@Bean
	public UserDetailsService userDetailsService() {
		return new RepositoryUserDetailsService(userRepository);
	}

	@Bean
	public RememberMeServices rememberMeServices() {
		// Key must be equal to rememberMe().key()
		TokenBasedRememberMeServices rememberMeServices = new TokenBasedRememberMeServices(
				"your_key", userDetailsService());
		rememberMeServices.setCookieName("remember_me_cookie");
		rememberMeServices.setParameter("remember_me_checkbox");
		rememberMeServices.setTokenValiditySeconds(2678400); // 1month
		return rememberMeServices;
	}

	@Bean
	public RequestCache customRequestCache() {
		HttpSessionRequestCache requestCache = new HttpSessionRequestCache();
		requestCache.setCreateSessionAllowed(true);
		requestCache.setPortResolver(new PortResolverImpl());

		return requestCache;
	}

	@Bean
	public RoleHierarchyImpl roleHierarchy() {
		RoleHierarchyImpl roleHierarchyImpl = new RoleHierarchyImpl();

		roleHierarchyImpl.setHierarchy("ROLE_ADMIN > ROLE_USER");

		return roleHierarchyImpl;
	}

	// @Bean
	// public RoleHierarchyVoter roleVoter() {
	// return new RoleHierarchyVoter(roleHierarchy());
	// }

	// private RequestMatcher createDefaultSavedRequestMatcher() {
	// ContentNegotiationStrategy contentNegotiationStrategy = new
	// HeaderContentNegotiationStrategy();
	//
	// RequestMatcher notFavIcon = new NegatedRequestMatcher(
	// new AntPathRequestMatcher("/**/favicon.ico"));
	//
	// MediaTypeRequestMatcher jsonRequest = new MediaTypeRequestMatcher(
	// contentNegotiationStrategy, MediaType.APPLICATION_JSON);
	// jsonRequest.setIgnoredMediaTypes(Collections.singleton(MediaType.ALL));
	// RequestMatcher notJson = new NegatedRequestMatcher(jsonRequest);
	//
	// RequestMatcher notXRequestedWith = new NegatedRequestMatcher(
	// new RequestHeaderRequestMatcher("X-Requested-With",
	// "XMLHttpRequest"));
	//
	// List<RequestMatcher> matchers = new ArrayList<RequestMatcher>();
	// RequestMatcher getRequests = new AntPathRequestMatcher("/**", "POST");
	// matchers.add(0, getRequests);
	// matchers.add(notFavIcon);
	// matchers.add(notJson);
	// matchers.add(notXRequestedWith);
	//
	// org.springframework.security.web.util.matcher.AndRequestMatcher
	// andRequestMatcher = new AndRequestMatcher(matchers);
	//
	// return andRequestMatcher;
	// }
}