package com.salesmanager.shop.application;

import com.salesmanager.core.business.configuration.CoreApplicationConfiguration;
import com.salesmanager.core.constants.SchemaConstant;
import javax.inject.Inject;
import javax.sql.DataSource;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.jdbc.JdbcUsersConnectionRepository;
import org.springframework.social.facebook.security.FacebookAuthenticationService;
import org.springframework.social.security.SocialAuthenticationServiceLocator;
import org.springframework.social.security.SocialAuthenticationServiceRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesView;
import org.springframework.web.servlet.view.tiles3.TilesViewResolver;


@Configuration
@ComponentScan({"com.salesmanager.shop", "com.salesmanager.core.business"})
@EnableAutoConfiguration
@Import(CoreApplicationConfiguration.class) //import sm-core configurations
@ImportResource({"classpath:/spring/shopizer-shop-context.xml"})
@EnableWebSecurity
public class ShopApplicationConfiguration extends WebMvcConfigurerAdapter {

  protected final Log LOGGER = LogFactory.getLog(getClass());

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
  public TilesConfigurer tilesConfigurer() {
    TilesConfigurer tilesConfigurer = new TilesConfigurer();
    tilesConfigurer.setDefinitions(
        new String[]{"/WEB-INF/tiles/tiles-admin.xml", "/WEB-INF/tiles/tiles-shop.xml"});
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

  @Bean
  @Scope(value = "singleton", proxyMode = ScopedProxyMode.INTERFACES)
  public SocialAuthenticationServiceLocator authenticationServiceLocator() {

    try {

      LOGGER.debug("Creating social authenticators");
      SocialAuthenticationServiceRegistry registry = new SocialAuthenticationServiceRegistry();
      registry.addAuthenticationService(
          new FacebookAuthenticationService(
              facebookAppId,
              facebookAppSecret));
      return registry;
    } catch (Exception e) {
      LOGGER.error("Error while creating social authenticators");
      return null;
    }
  }

  @Bean
  public UsersConnectionRepository socialUsersConnectionRepository() {
    JdbcUsersConnectionRepository conn = new JdbcUsersConnectionRepository(dataSource,
        authenticationServiceLocator(),
        textEncryptor);
    conn.setTablePrefix(SchemaConstant.SALESMANAGER_SCHEMA + ".");
    return conn;
  }
}
