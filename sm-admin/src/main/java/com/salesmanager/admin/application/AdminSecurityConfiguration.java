package com.salesmanager.admin.application;

import com.salesmanager.admin.security.WebUserServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

/**
 * Created by umesh on 3/6/17.
 */
@Configuration
@EnableWebSecurity
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
@EnableGlobalMethodSecurity(prePostEnabled = true,jsr250Enabled = true,securedEnabled = true)
//extends WebSecurityConfigurerAdapter
public class AdminSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private WebUserServices userDetailsService;

    @Autowired
    private AuthenticationSuccessHandler userAuthenticationSuccessHandler;

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception{
        httpSecurity.authorizeRequests().antMatchers("/admin/logon*").permitAll()
                .antMatchers("/admin/denied.html").permitAll()
                .antMatchers("/admin/unauthorized.html").permitAll()
                .antMatchers("/admin/users/resetPassword.html*").permitAll()
                .antMatchers("/admin/users/resetPasswordSecurityQtn.html*").permitAll()
                .antMatchers("/admin").hasRole("AUTH")
                .antMatchers("/admin/" ).hasRole("AUTH")
                .antMatchers("/admin/*.html*").hasRole("AUTH")
                .antMatchers("/admin/*/*.html*").hasRole("AUTH")
                .antMatchers("/admin/*/*/*.html*").hasRole("AUTH").anyRequest().authenticated()
                .and().csrf().disable().formLogin().loginPage("/admin/logon.html")
                .failureUrl("/admin/logon.html?login_error=true")
                .loginProcessingUrl("/login").usernameParameter("username").passwordParameter("password")
                .successHandler(userAuthenticationSuccessHandler).permitAll().and().exceptionHandling().accessDeniedPage("/admin/denied.html")
                .and().logout().logoutUrl("/admin/logout").logoutSuccessUrl("/admin/home.html").invalidateHttpSession(true);

    }


    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
                .antMatchers("/resources/**", "/pages/**","/plugins/**",
                        "/dist/**","/bootstrap/**","/static/**", "/css/**", "/js/**",
                        "/images/**", "/dist/img/**");
    }

    public void configure(AuthenticationManagerBuilder auth)throws Exception {

        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(11);
    }
}
