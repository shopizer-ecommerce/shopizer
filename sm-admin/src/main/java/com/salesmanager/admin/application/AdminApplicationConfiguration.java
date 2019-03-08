package com.salesmanager.admin.application;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import com.salesmanager.admin.filter.AdminFilter;

@Configuration
@ComponentScan({"com.salesmanager.admin"})
public class AdminApplicationConfiguration extends WebMvcConfigurerAdapter{
	
	/**
	 * locale based on the session, cookies, the Accept-Language header, or a fixed value.
	 * @return LocaleResolver Locale resolver
	 */
	@Bean
	public LocaleResolver localeResolver() {
	    SessionLocaleResolver slr = new SessionLocaleResolver();
	    slr.setDefaultLocale(Locale.CANADA);
	    return slr;
	}
	
	/**
	 * Will switch to a new locale based on the value of the lang parameter appended to a request ?lang=en|fr.
	 * @return LocaleChangeInterceptor locale change interceptor
	 */
	@Bean
	public LocaleChangeInterceptor localeChangeInterceptor() {
	    LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
	    lci.setParamName("lang");
	    return lci;
	}
	
	@Bean
	public AdminFilter getAdminFilter() {
		return new AdminFilter();
	}
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
	    registry.addInterceptor(localeChangeInterceptor());
	    registry.addInterceptor(getAdminFilter());
	}
	
	@Bean
	public MessageSource messageSource() {
	    final ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
	    messageSource.setBasenames("classpath:/messages");
	    messageSource.setUseCodeAsDefaultMessage(true);
	    messageSource.setDefaultEncoding("ISO-8859-1");
	    messageSource.setCacheSeconds(15);
	    return messageSource;
	}
	


}
