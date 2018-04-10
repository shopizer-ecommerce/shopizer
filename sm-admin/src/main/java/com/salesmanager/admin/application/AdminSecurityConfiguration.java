package com.salesmanager.admin.application;

import javax.inject.Inject;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.access.AccessDeniedHandler;

//https://www.future-processing.pl/blog/exploring-spring-boot-and-spring-security-custom-token-based-authentication-of-rest-services-with-spring-security-and-pinch-of-spring-java-configuration-and-spring-integration-testing/
@Configuration
public class AdminSecurityConfiguration extends WebSecurityConfigurerAdapter{
	
	@Inject
	private AccessDeniedHandler accessDeniedHandler;
	
	@Inject
	private AuthenticationProvider authenticationProvider;
	
	  @Override
	  protected void configure(final AuthenticationManagerBuilder auth)
	      throws Exception {
	    auth.authenticationProvider(authenticationProvider);
	    super.configure(auth);
	  }


	
    // roles auth allow to access /admin/**
    // custom 403 access denied handler
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable()
                .authorizeRequests()
					.antMatchers("/").permitAll()//todo secure so only /login is public
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
