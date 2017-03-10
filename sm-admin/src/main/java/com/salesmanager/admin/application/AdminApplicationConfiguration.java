package com.salesmanager.admin.application;

import com.salesmanager.admin.filter.AdminFilter;
import com.salesmanager.core.business.configuration.CoreApplicationConfiguration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesView;
import org.springframework.web.servlet.view.tiles3.TilesViewResolver;

import java.util.Locale;

/**
 * Created by umesh on 3/5/17.
 */
@Configuration
@ComponentScan({"com.salesmanager.admin","com.salesmanager.core.business"})
@EnableAutoConfiguration
@Import({AdminSecurityConfiguration.class,CoreApplicationConfiguration.class})//import sm-core configurations
//@ImportResource("classpath:/spring/shopizer-admin-security.xml")
@EnableWebSecurity
public class AdminApplicationConfiguration extends WebMvcConfigurerAdapter {

    @Autowired
    AdminFilter adminFilter;


    @Bean
    public TilesConfigurer tilesConfigurer(){
        TilesConfigurer tilesConfigurer = new TilesConfigurer();
        tilesConfigurer.setDefinitions(new String[] {"/WEB-INF/tiles/tiles-admin.xml"});
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


    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(adminFilter).addPathPatterns("/**");
    }

    @Bean(name = "shopizer-properties")
    public PropertiesFactoryBean mapper() {
        PropertiesFactoryBean bean = new PropertiesFactoryBean();
        // bean.setLocation(new ClassPathResource("com/foo/jdbc-production.properties"));
        return bean;
    }

    @Bean(name = "localeResolver")
    public SessionLocaleResolver sessionLocaleResolver() {
        SessionLocaleResolver localeResolver = new SessionLocaleResolver();
        localeResolver.setDefaultLocale(Locale.US);
        return localeResolver;
    }

}
