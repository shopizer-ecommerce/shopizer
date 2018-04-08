package com.salesmanager.shop.application;


import javax.inject.Inject;
import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.PropertyConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.context.event.EventListener;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.jdbc.JdbcUsersConnectionRepository;
import org.springframework.social.connect.support.ConnectionFactoryRegistry;
import org.springframework.social.facebook.connect.FacebookConnectionFactory;
import org.springframework.social.facebook.security.FacebookAuthenticationService;
import org.springframework.social.security.SocialAuthenticationServiceLocator;
import org.springframework.social.security.SocialAuthenticationServiceRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesView;
import org.springframework.web.servlet.view.tiles3.TilesViewResolver;

import com.salesmanager.core.business.configuration.CoreApplicationConfiguration;
import com.salesmanager.core.constants.SchemaConstant;

@Configuration
@ComponentScan({"com.salesmanager.shop","com.salesmanager.core.business"})
@EnableAutoConfiguration
@Import(CoreApplicationConfiguration.class)//import sm-core configurations
@ImportResource({"classpath:/spring/shopizer-shop-context.xml"})
@EnableWebSecurity
public class ShopApplicationConfiguration extends WebMvcConfigurerAdapter{

	protected final Log logger = LogFactory.getLog(getClass());
	
	@Value("${facebook.app.id}")
	private String facebookAppId;
	
	@Value("${facebook.app.secret}")
	private String facebookAppSecret;
	
    @Inject
    private DataSource dataSource;

    @Inject
    private TextEncryptor textEncryptor;
	
	@EventListener(ApplicationReadyEvent.class)
	public void applicationReadyCode() {
		String workingDir = System.getProperty("user.dir");
		System.out.println("Current working directory : " + workingDir);
	}
	

    /**
     * Configure TilesConfigurer.
     */
    @Bean
    public TilesConfigurer tilesConfigurer(){
        TilesConfigurer tilesConfigurer = new TilesConfigurer();
        tilesConfigurer.setDefinitions(new String[] {"/WEB-INF/tiles/tiles-admin.xml","/WEB-INF/tiles/tiles-shop.xml"});
        tilesConfigurer.setCheckRefresh(true);
        return tilesConfigurer;
    }
 
    /**
     * Configure ViewResolvers to deliver preferred views.
     */

    @Bean
    public TilesViewResolver tilesViewResolver() {
        final TilesViewResolver resolver = new TilesViewResolver();
        resolver.setViewClass(TilesView.class);
        return resolver;
    }
    
/*    @Bean
    public ConnectionFactoryLocator connectionFactoryLocator() {
        ConnectionFactoryRegistry registry = new ConnectionFactoryRegistry();
        
        registry.addConnectionFactory(new FacebookConnectionFactory(
        		facebookAppId,
        		facebookAppSecret));
            
        return registry;
    }*/
    
    @Bean
    @Scope(value = "singleton", proxyMode = ScopedProxyMode.INTERFACES)
    public SocialAuthenticationServiceLocator authenticationServiceLocator() {
		 
    	 try {
    		 
    		 logger.debug("Creating social authenticators");
    		 
    	
	    	 SocialAuthenticationServiceRegistry registry = new SocialAuthenticationServiceRegistry();
			 registry.addAuthenticationService(
				new FacebookAuthenticationService(
						facebookAppId, 
						facebookAppSecret));

			 // registry.addConnectionFactory(new
			 // FacebookConnectionFactory(environment
			 // .getProperty("facebook.clientId"), environment
			 // .getProperty("facebook.clientSecret")));
			 
			 return registry;
		 
    	 } catch(Exception e) {
    		 logger.error("Eror while creating social authenticators");
    		 return null;
    	 }
    }
    
    @Bean
    public UsersConnectionRepository socialUsersConnectionRepository() {
    	JdbcUsersConnectionRepository conn = new JdbcUsersConnectionRepository(dataSource, authenticationServiceLocator(), 
            textEncryptor);
    	conn.setTablePrefix(SchemaConstant.SALESMANAGER_SCHEMA + ".");
    	return conn;

    }


    
    


}
