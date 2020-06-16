package com.salesmanager.shop.store.security.customer;

import javax.inject.Inject;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Custom authautentication provider for customer api
 * @author carlsamson
 *
 */
public class JWTCustomerAuthenticationProvider extends DaoAuthenticationProvider {
	
    @Inject
    private UserDetailsService jwtCustomerDetailsService;
    
    @Inject
    private PasswordEncoder passwordEncoder;


	@Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        UsernamePasswordAuthenticationToken auth = (UsernamePasswordAuthenticationToken) authentication;
        String name = auth.getName();
        Object credentials = auth.getCredentials();
        UserDetails customer = jwtCustomerDetailsService.loadUserByUsername(name);
        if (customer == null) {
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

        
        return new UsernamePasswordAuthenticationToken(customer, credentials, customer.getAuthorities());
    }
	
	
    private boolean passwordMatch(String rawPassword, String user) {
		    return passwordEncoder.matches(rawPassword, user);
	}
	
    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }


	public UserDetailsService getJwtCustomerDetailsService() {
		return jwtCustomerDetailsService;
	}


	public void setJwtCustomerDetailsService(UserDetailsService jwtCustomerDetailsService) {
		this.jwtCustomerDetailsService = jwtCustomerDetailsService;
	}

}
