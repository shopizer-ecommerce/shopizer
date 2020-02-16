package com.salesmanager.shop.store.security.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public class JWTAdminAuthenticationProvider extends DaoAuthenticationProvider {
	
    @Autowired
    private UserDetailsService jwtAdminDetailsService;

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
        return new UsernamePasswordAuthenticationToken(user, credentials, user.getAuthorities());
    }
    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }

}
