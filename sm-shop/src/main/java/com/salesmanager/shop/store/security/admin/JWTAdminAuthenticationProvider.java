package com.salesmanager.shop.store.security.admin;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;


/**
 * Custom authautentication provider for admin api
 * @author carlsamson
 *
 */
public class JWTAdminAuthenticationProvider extends DaoAuthenticationProvider {
	
    @Autowired
    private UserDetailsService jwtAdminDetailsService;
    
    @Inject
    private PasswordEncoder passwordEncoder;

	public UserDetailsService getJwtAdminDetailsService() {
		return jwtAdminDetailsService;
	}
	public void setJwtAdminDetailsService(UserDetailsService jwtAdminDetailsService) {
		this.jwtAdminDetailsService = jwtAdminDetailsService;
	}
	@Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        UsernamePasswordAuthenticationToken auth = (UsernamePasswordAuthenticationToken) authentication;
        String name = auth.getName();
        Object credentials = auth.getCredentials();
        UserDetails user = jwtAdminDetailsService.loadUserByUsername(name);
        if (user == null) {
            throw new BadCredentialsException("Username/Password does not match for " + auth.getPrincipal());
        }
        
        String pass = credentials.toString();
        String usr = name;
        
        if(!passwordMatch(pass, usr)) {
        	throw new BadCredentialsException("Username/Password does not match for " + auth.getPrincipal());
        }
        
        
        /**
         * username password auth
         */

        
        return new UsernamePasswordAuthenticationToken(user, credentials, user.getAuthorities());
    }
	
	
    private boolean passwordMatch(String rawPassword, String user) {
		    return passwordEncoder.matches(rawPassword, user);
	}
	
    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }

}
