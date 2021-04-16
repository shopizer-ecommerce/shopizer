package com.salesmanager.shop.application.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.salesmanager.shop.admin.security.UserAuthenticationSuccessHandler;
import com.salesmanager.shop.admin.security.WebUserServices;
import com.salesmanager.shop.store.controller.customer.facade.CustomerFacade;
import com.salesmanager.shop.store.security.AuthenticationTokenFilter;
import com.salesmanager.shop.store.security.ServicesAuthenticationSuccessHandler;
import com.salesmanager.shop.store.security.admin.JWTAdminAuthenticationProvider;
import com.salesmanager.shop.store.security.admin.JWTAdminServicesImpl;
import com.salesmanager.shop.store.security.customer.JWTCustomerAuthenticationProvider;
import com.salesmanager.shop.store.security.services.CredentialsService;
import com.salesmanager.shop.store.security.services.CredentialsServiceImpl;

/**
 * Main entry point for security - admin - customer - auth - private - services
 * 
 * @author dur9213
 *
 */
@Configuration
@EnableWebSecurity
public class MultipleEntryPointsSecurityConfig {

	private static final String API_VERSION = "/api/v*";

	@Bean
	public AuthenticationTokenFilter authenticationTokenFilter() {
		return new AuthenticationTokenFilter();
	}
	
	@Bean
	public CredentialsService credentialsService() {
		return new CredentialsServiceImpl();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public UserAuthenticationSuccessHandler userAuthenticationSuccessHandler() {
		return new UserAuthenticationSuccessHandler();
	}

	@Bean
	public ServicesAuthenticationSuccessHandler servicesAuthenticationSuccessHandler() {
		return new ServicesAuthenticationSuccessHandler();
	}

	@Bean
	public CustomerFacade customerFacade() {
		return new com.salesmanager.shop.store.controller.customer.facade.CustomerFacadeImpl();
	}

	
	
	/**
	 * shop / customer
	 * 
	 * @author dur9213
	 *
	 */
	@Configuration
	@Order(1)
	public static class CustomerConfigurationAdapter extends WebSecurityConfigurerAdapter {

		@Bean("customerAuthenticationManager")
		@Override
		public AuthenticationManager authenticationManagerBean() throws Exception {
			return super.authenticationManagerBean();
		}

		@Autowired
		private UserDetailsService customerDetailsService;

		public CustomerConfigurationAdapter() {
			super();
		}
		
		@Override
		public void configure(WebSecurity web) {
			web.ignoring().antMatchers("/");
			web.ignoring().antMatchers("/shop");
			web.ignoring().antMatchers("/admin");
			web.ignoring().antMatchers("/error");
			web.ignoring().antMatchers("/resources/**");
			web.ignoring().antMatchers("/static/**");
			web.ignoring().antMatchers("/WEB-INF/**");
			web.ignoring().antMatchers("/services/public/**");
		}


		@Override
		public void configure(AuthenticationManagerBuilder auth) throws Exception {
			auth.userDetailsService(customerDetailsService);
		}

		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http
			.antMatcher("/shop/**")
			.csrf().disable()			
			.authorizeRequests()
					.antMatchers("/shop/").permitAll()
					.antMatchers("/shop/**").permitAll()
					.antMatchers("/shop/customer/logon*").permitAll()
					.antMatchers("/shop/customer/registration*").permitAll()
					.antMatchers("/shop/customer/logout*").permitAll()
					.antMatchers("/shop/customer/customLogon*").permitAll()
					.antMatchers("/shop/customer/denied*").permitAll()
					.antMatchers("/shop/customer/**").hasRole("AUTH_CUSTOMER")
					.anyRequest().authenticated()
					.and()
					.httpBasic()
					.authenticationEntryPoint(shopAuthenticationEntryPoint())
					.and()
					.logout()
					.logoutUrl("/shop/customer/logout")
					.logoutSuccessUrl("/shop/")
					.invalidateHttpSession(true)
					.deleteCookies("JSESSIONID")

					.invalidateHttpSession(false)
					.and()
					.exceptionHandling().accessDeniedPage("/shop/");

		}

		@Bean
		public AuthenticationEntryPoint shopAuthenticationEntryPoint() {
			BasicAuthenticationEntryPoint entryPoint = new BasicAuthenticationEntryPoint();
			entryPoint.setRealmName("shop-realm");
			return entryPoint;
		}

	}
	
	/**
	 * services api v0
	 * 
	 * @author dur9213
	 * @deprecated
	 *
	 */
	@Configuration
	@Order(2)
	public static class ServicesApiConfigurationAdapter extends WebSecurityConfigurerAdapter {

		@Autowired
		private WebUserServices userDetailsService;

		@Autowired
		private ServicesAuthenticationSuccessHandler servicesAuthenticationSuccessHandler;

		public ServicesApiConfigurationAdapter() {
			super();
		}

		@Override
		public void configure(AuthenticationManagerBuilder auth) throws Exception {
			auth.userDetailsService(userDetailsService);
		}

		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http
			.antMatcher("/services/**")
			.csrf().disable()
					.authorizeRequests()
					.antMatchers("/services/public/**").permitAll()
					.antMatchers("/services/private/**").hasRole("AUTH")
					.anyRequest().authenticated()
					.and().httpBasic().authenticationEntryPoint(servicesAuthenticationEntryPoint())
					.and().formLogin()
					.successHandler(servicesAuthenticationSuccessHandler);

		}

		@Bean
		public AuthenticationEntryPoint servicesAuthenticationEntryPoint() {
			BasicAuthenticationEntryPoint entryPoint = new BasicAuthenticationEntryPoint();
			entryPoint.setRealmName("rest-customer-realm");
			return entryPoint;
		}

	}

	/**
	 * admin
	 * 
	 * @author dur9213
	 *
	 */
	@Configuration
	@Order(3)
	public static class AdminConfigurationAdapter extends WebSecurityConfigurerAdapter {

		@Autowired
		private WebUserServices userDetailsService;

		@Autowired
		private UserAuthenticationSuccessHandler userAuthenticationSuccessHandler;

		public AdminConfigurationAdapter() {
			super();
		}

		@Override
		public void configure(AuthenticationManagerBuilder auth) throws Exception {
			auth.userDetailsService(userDetailsService);
		}
		
		@Override
		public void configure(WebSecurity web) {
		}

		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http
			.antMatcher("/admin/**")
					.authorizeRequests()
					.antMatchers("/admin/logon*").permitAll()
					.antMatchers("/admin/resources/**").permitAll()
					.antMatchers("/admin/layout/**").permitAll()
					.antMatchers("/admin/denied*").permitAll()
					.antMatchers("/admin/unauthorized*").permitAll()
					.antMatchers("/admin/users/resetPassword*").permitAll()
					.antMatchers("/admin/").hasRole("AUTH")
					.antMatchers("/admin/**").hasRole("AUTH")
					.antMatchers("/admin/**").hasRole("AUTH")
					.antMatchers("/admin/users/resetPasswordSecurityQtn*").permitAll()
					.anyRequest()
					.authenticated()
					.and()
					.httpBasic()
					.authenticationEntryPoint(adminAuthenticationEntryPoint())
					.and()
					.formLogin().usernameParameter("username").passwordParameter("password")
					.loginPage("/admin/logon.html")
					.loginProcessingUrl("/admin/performUserLogin")
					.successHandler(userAuthenticationSuccessHandler)
					.failureUrl("/admin/logon.html?login_error=true")
					.and()
					.csrf().disable()
					.logout().logoutUrl("/admin/logout").logoutSuccessUrl("/admin/home.html")
					.invalidateHttpSession(true).and().exceptionHandling().accessDeniedPage("/admin/denied.html");
			

		}

		@Bean
		public AuthenticationEntryPoint adminAuthenticationEntryPoint() {
			BasicAuthenticationEntryPoint entryPoint = new BasicAuthenticationEntryPoint();
			entryPoint.setRealmName("admin-realm");
			return entryPoint;
		}

	}

	/**
	 * api - private
	 * 
	 * @author dur9213
	 *
	 */
	@Configuration
	@Order(5)
	public static class UserApiConfigurationAdapter extends WebSecurityConfigurerAdapter {

		@Autowired
		private AuthenticationTokenFilter authenticationTokenFilter;

		@Autowired
		JWTAdminServicesImpl jwtUserDetailsService;

		@Bean("jwtAdminAuthenticationManager")
		@Override
		public AuthenticationManager authenticationManagerBean() throws Exception {
			AuthenticationManager mgr = super.authenticationManagerBean();
			return mgr;
		}
		
		

		public UserApiConfigurationAdapter() {
			super();
		}

		@Override
		public void configure(AuthenticationManagerBuilder auth) throws Exception {
		       auth.userDetailsService(jwtUserDetailsService)
	            .and()
	            .authenticationProvider(authenticationProvider());
		}
		
		@Override
		public void configure(WebSecurity web) {
			web.ignoring().antMatchers("/swagger-ui.html");
		}

		
		/**
		 * Admin user api
		 */
		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http
					.antMatcher(API_VERSION + "/private/**")
					.authorizeRequests()
					.antMatchers(API_VERSION + "/private/login*").permitAll()
					.antMatchers(API_VERSION + "/private/refresh").permitAll()
					.antMatchers(HttpMethod.OPTIONS, API_VERSION + "/private/**").permitAll()
					.antMatchers(API_VERSION + "/private/**").hasRole("AUTH")
					.anyRequest().authenticated()
					.and()
					.httpBasic().authenticationEntryPoint(apiAdminAuthenticationEntryPoint())
					.and()
					.addFilterAfter(authenticationTokenFilter, BasicAuthenticationFilter.class)
					.csrf().disable();

		}
		
	    @Bean
	    public AuthenticationProvider authenticationProvider() {
	    	JWTAdminAuthenticationProvider provider = new JWTAdminAuthenticationProvider();
	        provider.setUserDetailsService(jwtUserDetailsService);
	        return provider;
	    }

		@Bean
		public AuthenticationEntryPoint apiAdminAuthenticationEntryPoint() {
			BasicAuthenticationEntryPoint entryPoint = new BasicAuthenticationEntryPoint();
			entryPoint.setRealmName("api-admin-realm");
			return entryPoint;
		}

	}



	/**
	 * customer api
	 * 
	 * @author dur9213
	 *
	 */
	@Configuration
	@Order(6)
	public static class CustomeApiConfigurationAdapter extends WebSecurityConfigurerAdapter {

		@Autowired
		private AuthenticationTokenFilter authenticationTokenFilter;

		@Autowired
		private UserDetailsService jwtCustomerDetailsService;

		public CustomeApiConfigurationAdapter() {
			super();
		}
		
		@Bean("jwtCustomerAuthenticationManager")
		@Override
		public AuthenticationManager authenticationManagerBean() throws Exception {
			return super.authenticationManagerBean();
		}

		@Override
		public void configure(AuthenticationManagerBuilder auth) throws Exception {
			auth.userDetailsService(jwtCustomerDetailsService);
		}

		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http
			
				.antMatcher(API_VERSION + "/auth/**")
				.authorizeRequests()
					.antMatchers(API_VERSION + "/auth/refresh").permitAll()
					.antMatchers(API_VERSION + "/auth/login").permitAll()
					.antMatchers(API_VERSION + "/auth/register").permitAll()
					.antMatchers(HttpMethod.OPTIONS, API_VERSION + "/auth/**").permitAll()
					.antMatchers(API_VERSION + "/auth/**")
					.hasRole("AUTH_CUSTOMER").anyRequest().authenticated()
					.and()
					.httpBasic()
					.authenticationEntryPoint(apiCustomerAuthenticationEntryPoint()).and().csrf().disable()
					.addFilterAfter(authenticationTokenFilter, BasicAuthenticationFilter.class);

		}
		
	    @Bean
	    public AuthenticationProvider authenticationProvider() {
	    	JWTCustomerAuthenticationProvider provider = new JWTCustomerAuthenticationProvider();
	        provider.setUserDetailsService(jwtCustomerDetailsService);
	        return provider;
	    }

		@Bean
		public AuthenticationEntryPoint apiCustomerAuthenticationEntryPoint() {
			BasicAuthenticationEntryPoint entryPoint = new BasicAuthenticationEntryPoint();
			entryPoint.setRealmName("api-customer-realm");
			return entryPoint;
		}

	}



}
