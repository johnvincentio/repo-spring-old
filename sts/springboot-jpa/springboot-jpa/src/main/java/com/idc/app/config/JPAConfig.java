package com.idc.app.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@PropertySource("classpath:application.properties")
@ComponentScan("com.idc")
@EntityScan(basePackages = { "com.idc" })
@EnableJpaRepositories(basePackages = { "com.idc.persistence" })
@EnableTransactionManagement
public class JPAConfig {

	@Autowired
    private Environment env;		// used to get properties.

	/*
	 * could have put config info into application.properties.
	 */
	@Bean
	public DataSource dataSource() {
		System.out.println(">>> dataSource");
		DriverManagerDataSource driver = new DriverManagerDataSource();
		driver.setDriverClassName("org.postgresql.Driver");
		driver.setUrl("jdbc:postgresql://192.168.1.146:5432/springdb");
		driver.setUsername("spring");
		driver.setPassword("spring");
		System.out.println("<<< dataSource");
		return driver;
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		System.out.println(">>> entityManagerFactory");
		LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
		System.out.println("entityManagerFactory - stage 1");

		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
//		vendorAdapter.setDatabase(Database.POSTGRESQL);
		vendorAdapter.setGenerateDdl(Boolean.TRUE);
		vendorAdapter.setShowSql(Boolean.TRUE);
		System.out.println("entityManagerFactory - stage 2");

		factory.setDataSource(dataSource());
		factory.setJpaVendorAdapter(vendorAdapter);
		factory.setPackagesToScan("com.idc.persistence");
		System.out.println("entityManagerFactory - stage 3");

		Properties jpaProperties = new Properties();
		jpaProperties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQL9Dialect");
		jpaProperties.put("hibernate.globally_quoted_identifiers", "true");
		factory.setJpaProperties(jpaProperties);
		System.out.println("entityManagerFactory - stage 4");

/*
 * not sure if this is needed.
		factory.afterPropertiesSet();
		factory.setLoadTimeWeaver(new InstrumentationLoadTimeWeaver());
 */
		System.out.println("<<< entityManagerFactory");
		return factory;
	}

	@Bean
	public JpaTransactionManager transactionManager() {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
		return transactionManager;
	}
}
