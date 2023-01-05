package com.salesmanager.shop.application.config;

import static org.springframework.http.MediaType.APPLICATION_OCTET_STREAM;
import static org.springframework.http.MediaType.IMAGE_GIF;
import static org.springframework.http.MediaType.IMAGE_JPEG;
import static org.springframework.http.MediaType.IMAGE_PNG;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.event.EventListener;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.http.MediaType;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import com.salesmanager.core.business.configuration.CoreApplicationConfiguration;
import com.salesmanager.shop.filter.CorsFilter;
import com.salesmanager.shop.filter.XssFilter;
import com.salesmanager.shop.utils.LabelUtils;

@Configuration
@ComponentScan({"com.salesmanager.shop"})
@ServletComponentScan
@Import({CoreApplicationConfiguration.class}) // import sm-core configurations
@EnableWebSecurity
public class ShopApplicationConfiguration implements WebMvcConfigurer {

  protected final Log logger = LogFactory.getLog(getClass());

  @EventListener(ApplicationReadyEvent.class)
  public void applicationReadyCode() {
    String workingDir = System.getProperty("user.dir");
    logger.info("Current working directory : " + workingDir);
  }

  @Bean
  public FilterRegistrationBean<XssFilter> croseSiteFilter(){
      FilterRegistrationBean<XssFilter> registrationBean 
        = new FilterRegistrationBean<>();
          
      registrationBean.setFilter(new XssFilter());
      registrationBean.addUrlPatterns("/shop/**");
      registrationBean.addUrlPatterns("/api/**");
      registrationBean.addUrlPatterns("/customer/**");
          
      return registrationBean;    
  }

  @Override
  public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
    converters.add(new MappingJackson2HttpMessageConverter());
  }

  @Override
  public void addViewControllers(ViewControllerRegistry registry) {
    registry.addViewController("/").setViewName("shop");
  }

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    // Changes the locale when a 'locale' request parameter is sent; e.g. /?locale=de
    registry.addInterceptor(localeChangeInterceptor());

    /**
    registry
        .addInterceptor(storeFilter())
        // store web front filter
        .addPathPatterns("/shop/**")
        // customer section filter
        .addPathPatterns("/customer/**");
     **/

    registry
        .addInterceptor(corsFilter())
        // public services cors filter
        .addPathPatterns("/services/**")
        // REST api
        .addPathPatterns("/api/**");

  }

  @Bean
  public ByteArrayHttpMessageConverter byteArrayHttpMessageConverter() {
    List<MediaType> supportedMediaTypes = Arrays.asList(IMAGE_JPEG, IMAGE_GIF, IMAGE_PNG, APPLICATION_OCTET_STREAM);

    ByteArrayHttpMessageConverter byteArrayHttpMessageConverter =
        new ByteArrayHttpMessageConverter();
    byteArrayHttpMessageConverter.setSupportedMediaTypes(supportedMediaTypes);
    return byteArrayHttpMessageConverter;
  }

  @Bean
  public LocaleChangeInterceptor localeChangeInterceptor() {
    return new LocaleChangeInterceptor();
  }

	/*
	 * @Bean public StoreFilter storeFilter() { return new StoreFilter(); }
	 */

  @Bean
  public CorsFilter corsFilter() {
    return new CorsFilter();
  }


  @Bean
  public SessionLocaleResolver localeResolver() {
    SessionLocaleResolver slr = new SessionLocaleResolver();
    slr.setDefaultLocale(Locale.getDefault());
    return slr;
  }

  @Bean
  public ReloadableResourceBundleMessageSource messageSource() {
    ReloadableResourceBundleMessageSource messageSource =
        new ReloadableResourceBundleMessageSource();
    messageSource.setBasenames(
        "classpath:bundles/shopizer",
        "classpath:bundles/messages",
        "classpath:bundles/shipping",
        "classpath:bundles/payment");

    messageSource.setDefaultEncoding("UTF-8");
    return messageSource;
  }

  @Bean
  public LabelUtils messages() {
    return new LabelUtils();
  }

}
