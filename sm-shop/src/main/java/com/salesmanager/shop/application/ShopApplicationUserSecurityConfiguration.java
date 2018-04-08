package com.salesmanager.shop.application;

/**
 * Configuration for admin basic authentication
 * 
 * --- Can't login with spring mvc java configuration ---
 * --- /login produces 404 error as is security filter is not found ---
 * --- need to stick with xml
 * @author carlsamson
 *
 */
//@Configuration
//@Order(1)
public class ShopApplicationUserSecurityConfiguration { //extends WebSecurityConfigurerAdapter {
	

	//private static final String ADMIN_REALM = "admin";
	
    //@Inject
    //private UserServicesImpl userDetailsService;
    
    //@Inject
    //private ServicesAuthenticationSuccessHandler userAuthenticationSuccessHandler;
	
/*    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
        	.formLogin().loginPage("/admin/logon.html").loginProcessingUrl("/login")
        	.usernameParameter("username").passwordParameter("password").successHandler(userAuthenticationSuccessHandler)
        	.failureUrl("/admin/logon.html?login_error=true")
        	.and()
        	.logout().invalidateHttpSession(false)
        	.logoutSuccessUrl("/shop")
        	.logoutUrl("/shop/customer/logout")
        	.and()
        	.exceptionHandling().accessDeniedPage("/shop")
        	.and()
        	.authenticationProvider(adminAuthenticationProvider())
        	.antMatcher("/admin/**").authorizeRequests()
        	
        	.antMatchers("/login*").permitAll()
        	.antMatchers("/admin/logon*").permitAll()
        	
        	.antMatchers("/admin/denied.html").permitAll()
        	.antMatchers("/admin/unauthorized.html").permitAll()
        	.antMatchers("/admin/users/resetPassword.html*").permitAll()
        	.antMatchers("/admin/users/resetPasswordSecurityQtn.html*").permitAll()
        	.antMatchers("/admin/unauthorized.html").permitAll()
        	.antMatchers("/admin").hasRole("AUTH")
        	.antMatchers("/admin/").hasRole("AUTH")
        	.antMatchers("/admin/*.html*").hasRole("AUTH")
        	.antMatchers("/admin/*//*.html*").hasRole("AUTH")
        	.antMatchers("/admin/*//*/*.html*").hasRole("AUTH")
        	.and().httpBasic().authenticationEntryPoint(adminAuthenticationEntryPoint());
        	
    }*/
    
/*    @Bean(name="passwordEncoder")
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder(11);
    }
	
	
    @Bean
    public AuthenticationEntryPoint adminAuthenticationEntryPoint(){
        BasicAuthenticationEntryPoint entryPoint = 
          new BasicAuthenticationEntryPoint();
        entryPoint.setRealmName(ADMIN_REALM);
        return entryPoint;
    }
    
    @Bean
    public AuthenticationProvider adminAuthenticationProvider() {
    	DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(encoder());
        return authenticationProvider;
    }
*/
}
