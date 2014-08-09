package org.ucema.sgsp.security.persistence;

import java.util.Properties;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableJpaRepositories(basePackages = { "org.ucema.sgsp" })
@EnableTransactionManagement
public class PersistenceContext {

	@Resource
	private Environment env;
	 
	 @Bean
	public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
		return new PersistenceExceptionTranslationPostProcessor();
	}	 
	
	@Bean
	public DataSource dataSource() {
		BasicDataSource dataSource = new BasicDataSource();

		dataSource.setDriverClassName(env.getRequiredProperty("db.driver"));
		dataSource.setUrl(env.getRequiredProperty("db.url"));
		dataSource.setUsername(env.getRequiredProperty("db.username"));
		dataSource.setPassword(env.getRequiredProperty("db.password"));

		return dataSource;
	}

	@Bean
	public JpaTransactionManager transactionManager() {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(entityManagerFactory()
				.getObject());
		return transactionManager;
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();

		entityManagerFactoryBean.setDataSource(dataSource());
		entityManagerFactoryBean
				.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
		entityManagerFactoryBean.setPackagesToScan(
				"org.ucema.sgsp.security.model","org.ucema.sgsp.persistence.model");

		Properties jpaProperties = new Properties();
		jpaProperties.put("hibernate.dialect",
				env.getRequiredProperty("hibernate.dialect"));
		jpaProperties.put("hibernate.format_sql",
				env.getRequiredProperty("hibernate.format_sql"));
		jpaProperties.put("hibernate.hbm2ddl.auto",
				env.getRequiredProperty("hibernate.hbm2ddl.auto"));
		jpaProperties.put("hibernate.ejb.naming_strategy",
				env.getRequiredProperty("hibernate.ejb.naming_strategy"));
		jpaProperties.put("hibernate.show_sql",
				env.getRequiredProperty("hibernate.show_sql"));

		entityManagerFactoryBean.setJpaProperties(jpaProperties);

		return entityManagerFactoryBean;
	}
}
