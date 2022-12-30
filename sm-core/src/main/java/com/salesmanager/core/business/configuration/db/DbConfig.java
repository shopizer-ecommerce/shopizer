package com.salesmanager.core.business.configuration.db;

import javax.inject.Inject;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import com.salesmanager.core.model.system.credentials.DbCredentials;

public class DbConfig {
	
    @Inject Environment env;

    @Bean
    public DbCredentials dbCredentials() {
    	DbCredentials dbCredentials = new DbCredentials();
    	dbCredentials.setUserName(env.getProperty("user"));
    	dbCredentials.setPassword(env.getProperty("password"));
        return dbCredentials;
    }

}
