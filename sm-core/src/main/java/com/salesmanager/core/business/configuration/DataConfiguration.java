package com.salesmanager.core.business.configuration;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import com.zaxxer.hikari.HikariDataSource;


@Configuration
@EnableCaching
public class DataConfiguration {

	/**
	 * Datasource
	 */
    @Value("${db.driverClass}")
    private String driverClassName;
    
    @Value("${db.jdbcUrl}")
    private String url;
    
    @Value("${db.user}")
    private String user;
    
    @Value("${db.password}")
    private String password;
    
    /**
     * Other connection properties
     */
    
    @Value("${db.preferredTestQuery}")
    private String preferredTestQuery;
    
    @Value("${db.preferredTestQuery}")
    private String testQuery;
    
    @Value("${db.minPoolSize}")
    private int minPoolSize;
    
    @Value("${db.maxPoolSize}")
    private int maxPoolSize;

    private HibernateConfigurations hibernate;

    @Bean
    public HikariDataSource dataSource() {
    	HikariDataSource dataSource = DataSourceBuilder.create().type(HikariDataSource.class)
    	.driverClassName(driverClassName)
    	.url(url)
    	.username(user)
    	.password(password)
    	.build();
    	
    	/** Datasource config **/
    	dataSource.setIdleTimeout(minPoolSize);
    	dataSource.setMaximumPoolSize(maxPoolSize);
    	dataSource.setConnectionTestQuery(testQuery);
    	
    	return dataSource;
    }

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {

		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		vendorAdapter.setGenerateDdl(true);

		LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
		factory.setJpaVendorAdapter(vendorAdapter);
		factory.setPackagesToScan("com.salesmanager.core.model");
		factory.setJpaProperties(hibernate.getProperties());
		factory.setDataSource(dataSource());
		return factory;
	}

	@Bean
	public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {

		JpaTransactionManager txManager = new JpaTransactionManager();
		txManager.setEntityManagerFactory(entityManagerFactory);
		return txManager;
	}

}
