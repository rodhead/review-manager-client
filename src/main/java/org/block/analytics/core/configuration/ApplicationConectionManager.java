package org.block.analytics.core.configuration;

import java.util.Properties;


import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.mysql.cj.jdbc.MysqlDataSource;

@Configuration
@EnableTransactionManagement
@PropertySource("classpath:hibernateJPA.properties")
@EnableJpaRepositories(basePackages = "org.block.analytics.*", entityManagerFactoryRef = "dbEntityManager", transactionManagerRef = "dbTransactionManager")
public class ApplicationConectionManager {

	private static final Logger log = LoggerFactory.getLogger(ApplicationConectionManager.class);

	@Autowired
	Environment env;

	@Primary
	@Bean("dbEntityManager")
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean entityManager = new LocalContainerEntityManagerFactoryBean();
		JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		entityManager.setJpaVendorAdapter(vendorAdapter);
		entityManager.setPackagesToScan("org.block.analytics");
		entityManager.setDataSource( dbDataSource());
		entityManager.setJpaProperties(additionalProperties());
		entityManager.setPersistenceUnitName("dbPUnit");
		return entityManager;
	}

	Properties additionalProperties() {
		Properties properties = new Properties();
		if (null != env.getProperty("spring.jpa.properties.hibernate.dialect")) {
			properties.setProperty("spring.jpa.properties.hibernate.dialect",
					env.getProperty("spring.jpa.properties.hibernate.dialect"));
		} else {
			log.error("spring.jpa.properties.hibernate.dialect property is missing in hibernate property file.");
			System.exit(0);
		}
		if (null != env.getProperty("spring.jpa.hibernate.ddl-auto")) {
			properties.setProperty("spring.jpa.hibernate.ddl-auto", env.getProperty("spring.jpa.hibernate.ddl-auto"));
		} else {
			log.error("spring.jpa.hibernate.ddl-auto property is missing in hibernate property file.");
			System.exit(0);
		}
		return properties;
	}

	@Primary
	@Bean("dbDataSource")
	public DataSource dbDataSource() {
		MysqlDataSource dataSource = new MysqlDataSource();
		if (null != env) {
			if (!StringUtils.isBlank(env.getProperty("spring.datasource.password"))) {
				dataSource.setPassword(env.getProperty("spring.datasource.password"));
			} else {
				log.error("spring.datasource.password property is missing in hibernate property file.");
				System.exit(0);
			}
			if (!StringUtils.isBlank(env.getProperty("spring.datasource.username"))) {
				dataSource.setUser(env.getProperty("spring.datasource.username"));
			} else {
				log.error("spring.datasource.username property is missing in hibernate property file.");
				System.exit(0);
			}
			if (!StringUtils.isBlank(env.getProperty("spring.datasource.url"))) {
				dataSource.setUrl(env.getProperty("spring.datasource.url"));
			} else {
				log.error("spring.datasource.url property is missing in hibernate property file.");
				System.exit(0);
			}
		} else {
			log.error("hibernate Property file is not found in classpath");
		}
		return (DataSource) dataSource;
	}

	@Autowired
	@Bean("sessionFactory")
	public SessionFactory getSessionFactory(DataSource dataSource) {
		LocalSessionFactoryBuilder sessionBuilder = new LocalSessionFactoryBuilder((javax.sql.DataSource) dataSource);
		sessionBuilder.scanPackages("org.block.analytics");
		return sessionBuilder.buildSessionFactory();
	}

	@Primary
	@Bean("dbTransactionManager")
	public PlatformTransactionManager transactionManager(EntityManagerFactory entityManageractory) {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(entityManageractory);
		return transactionManager;
	}

	@Bean
	public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
		return new PersistenceExceptionTranslationPostProcessor();
	}
}
