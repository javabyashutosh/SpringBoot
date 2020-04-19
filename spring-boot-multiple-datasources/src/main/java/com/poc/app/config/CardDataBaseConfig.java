package com.poc.app.config;

import java.util.HashMap;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com.poc.app.card.repo", entityManagerFactoryRef = "cardEntityManagerFactory",
transactionManagerRef = "cardTxManager")
public class CardDataBaseConfig {

	@Autowired
	private Environment environment;

	@Bean("cardDataSource")
	@ConfigurationProperties(prefix = "card.datasource")
	public DataSource dataSource() {
		System.out.println("inside cardDataSource()");
		BasicDataSource cardDataSource = new BasicDataSource();
		cardDataSource.setUrl(environment.getProperty("card.datasource.url"));
		cardDataSource.setUsername(environment.getProperty("card.datasource.username"));
		cardDataSource.setPassword(environment.getProperty("card.datasource.password"));

		return cardDataSource;
	}

	@Bean("cardEntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(@Qualifier("cardDataSource") DataSource cardDataSource) {

		LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();

		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		vendorAdapter.setGenerateDdl(true);

		factoryBean.setDataSource(cardDataSource);
		factoryBean.setPackagesToScan("com.poc.app.card.entity");
		factoryBean.setPersistenceUnitName("Card");
		factoryBean.setJpaVendorAdapter(vendorAdapter);

		HashMap<String, Object> properties = new HashMap<>();
		properties.put("hibernate.hbm2ddl.auto", environment.getProperty("spring.jpa.hibernate.ddl-auto"));
		properties.put("hibernate.dialect", environment.getProperty("spring.jpa.properties.hibernate.dialect"));
		factoryBean.setJpaPropertyMap(properties);
		return factoryBean;
	}

	@Bean("cardTxManager")
	public PlatformTransactionManager transactionManager(@Qualifier("cardEntityManagerFactory") EntityManagerFactory cardEntityManagerFactory) {
		JpaTransactionManager txManager = new JpaTransactionManager();

		txManager.setEntityManagerFactory(cardEntityManagerFactory);

		return txManager;
		
	}

}
