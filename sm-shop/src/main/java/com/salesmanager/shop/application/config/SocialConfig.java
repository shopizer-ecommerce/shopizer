package com.salesmanager.shop.application.config;

import com.salesmanager.core.constants.SchemaConstant;
import javax.inject.Inject;
import javax.sql.DataSource;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.social.UserIdSource;
import org.springframework.social.config.annotation.ConnectionFactoryConfigurer;
import org.springframework.social.config.annotation.EnableSocial;
import org.springframework.social.config.annotation.SocialConfigurer;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.jdbc.JdbcUsersConnectionRepository;
import org.springframework.social.connect.web.ConnectController;
import org.springframework.social.facebook.connect.FacebookConnectionFactory;
import org.springframework.social.facebook.security.FacebookAuthenticationService;
import org.springframework.social.security.AuthenticationNameUserIdSource;
import org.springframework.social.security.SocialAuthenticationServiceLocator;
import org.springframework.social.security.SocialAuthenticationServiceRegistry;

@Configuration
@EnableSocial
public class SocialConfig implements SocialConfigurer {

  private static final Log logger = LogFactory.getLog(SocialConfig.class);

  @Inject private DataSource dataSource;

  @Inject private TextEncryptor textEncryptor;

  @Value("${facebook.app.id}")
  private String facebookAppId;

  @Value("${facebook.app.secret}")
  private String facebookAppSecret;

  @Bean
  @Scope(value = "singleton", proxyMode = ScopedProxyMode.INTERFACES)
  public SocialAuthenticationServiceLocator authenticationServiceLocator() {

    try {

      logger.debug("Creating social authenticators");

      SocialAuthenticationServiceRegistry registry = new SocialAuthenticationServiceRegistry();
      registry.addAuthenticationService(
          new FacebookAuthenticationService(facebookAppId, facebookAppSecret));

      // registry.addConnectionFactory(new
      // FacebookConnectionFactory(environment
      // .getProperty("facebook.clientId"), environment
      // .getProperty("facebook.clientSecret")));

      return registry;

    } catch (Exception e) {
      logger.error("Eror while creating social authenticators");
      return null;
    }
  }

  @Override
  public void addConnectionFactories(
      ConnectionFactoryConfigurer connectionFactoryConfigurer, Environment environment) {
    connectionFactoryConfigurer.addConnectionFactory(
        new FacebookConnectionFactory(facebookAppId, facebookAppSecret));
  }

  @Override
  public UserIdSource getUserIdSource() {
    return new AuthenticationNameUserIdSource();
  }

  @Override
  public UsersConnectionRepository getUsersConnectionRepository(
      ConnectionFactoryLocator connectionFactoryLocator) {
    return socialUsersConnectionRepository();
  }

  @Bean
  public UsersConnectionRepository socialUsersConnectionRepository() {
    JdbcUsersConnectionRepository conn =
        new JdbcUsersConnectionRepository(
            dataSource, authenticationServiceLocator(), textEncryptor);
    conn.setTablePrefix(SchemaConstant.SALESMANAGER_SCHEMA + ".");
    return conn;
  }

  @Bean
  public ConnectController connectController(
      ConnectionFactoryLocator connectionFactoryLocator,
      ConnectionRepository connectionRepository) {
    return new ConnectController(connectionFactoryLocator, connectionRepository);
  }
}
