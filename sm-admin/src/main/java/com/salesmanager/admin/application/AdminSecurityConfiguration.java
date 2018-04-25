package com.salesmanager.admin.application;

import javax.inject.Inject;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.access.AccessDeniedHandler;

import com.salesmanager.admin.components.security.AdminAuthenticationProvider;

//https://www.future-processing.pl/blog/exploring-spring-boot-and-spring-security-custom-token-based-authentication-of-rest-services-with-spring-security-and-pinch-of-spring-java-configuration-and-spring-integration-testing/
@Configuration
@EnableWebSecurity
public class AdminSecurityConfiguration extends WebSecurityConfigurerAdapter{
	
	@Inject
	private AccessDeniedHandler accessDeniedHandler;
	
	@Inject
	private AdminAuthenticationProvider authenticationProvider;
	
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

	  
	@Override
	public void configure(AuthenticationManagerBuilder builder)
	          throws Exception {
	    builder.authenticationProvider(authenticationProvider);
	}



	
    // roles auth allow to access /admin/**
    // custom 403 access denied handler 
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authenticationProvider(authenticationProvider)
                .authorizeRequests()
					.antMatchers("/").permitAll()
					.antMatchers("/webjars/**").permitAll()
					.antMatchers("/img/**").permitAll()
					.antMatchers("/img/**/**").permitAll()
					.antMatchers("/css/**/**").permitAll()
					.antMatchers("/css/**").permitAll()
					.antMatchers("/js/**/**").permitAll()
					.antMatchers("/js/**").permitAll()
					.antMatchers("/robots**").permitAll()
					.antMatchers("/login/**").permitAll()
					.antMatchers("/admin/**").hasAnyRole("AUTH")
					.anyRequest().authenticated()
                .and()
                .formLogin()
					.loginPage("/login")
					.permitAll()
					.and()
                .logout()
					.permitAll()
					.and()
                .exceptionHandling().accessDeniedHandler(accessDeniedHandler);
    }

}
