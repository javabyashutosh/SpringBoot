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
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com.poc.app.member.repo", entityManagerFactoryRef = "memberEntityManagerFactory",
transactionManagerRef = "memberTxManager")
public class MemberDataBaseConfig {

	@Autowired
	private Environment environment;

	@Bean("memberDataSource")
	@Primary
	@ConfigurationProperties(prefix = "member.datasource")
	public DataSource dataSource() {
		System.out.println("inside memberDataSource()");
		BasicDataSource memberDataSource = new BasicDataSource();
		memberDataSource.setUrl(environment.getProperty("member.datasource.url"));
		memberDataSource.setUsername(environment.getProperty("member.datasource.username"));
		memberDataSource.setPassword(environment.getProperty("member.datasource.password"));

		return memberDataSource;
	}

	@Bean("memberEntityManagerFactory")
	@Primary
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(@Qualifier("memberDataSource") DataSource dataSource) {
		LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();

		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		vendorAdapter.setGenerateDdl(true);

		factoryBean.setDataSource(dataSource);
		factoryBean.setPackagesToScan("com.poc.app.member.entity");
		factoryBean.setPersistenceUnitName("Member");
		factoryBean.setJpaVendorAdapter(vendorAdapter);

		HashMap<String, Object> properties = new HashMap<>();
		properties.put("hibernate.hbm2ddl.auto", environment.getProperty("spring.jpa.hibernate.ddl-auto"));
		properties.put("hibernate.dialect", environment.getProperty("spring.jpa.properties.hibernate.dialect"));
		factoryBean.setJpaPropertyMap(properties);
		return factoryBean;
	}

	@Bean("memberTxManager")
	@Primary
	public PlatformTransactionManager transactionManager(@Qualifier("memberEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
		JpaTransactionManager txManager = new JpaTransactionManager();

		txManager.setEntityManagerFactory(entityManagerFactory);

		return txManager;
	}

}
