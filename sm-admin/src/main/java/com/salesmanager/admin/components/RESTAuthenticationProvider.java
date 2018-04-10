package com.salesmanager.admin.components;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import com.google.common.collect.Sets;

@Component("authenticationProvider")
public class RESTAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {
	

	@Inject
	private RESTAuthenticationApi authenticationApi;

	// ~ Instance fields
	// ================================================================================================


	private UserDetailsService userDetailsService;

	public RESTAuthenticationProvider() {

	}

	// ~ Methods
	// ========================================================================================================

	protected void additionalAuthenticationChecks(UserDetails userDetails,
			UsernamePasswordAuthenticationToken authentication)
			throws AuthenticationException {

		if (authentication.getCredentials() == null) {
			logger.debug("Authentication failed: no credentials provided");

			throw new BadCredentialsException(messages.getMessage(
					"AbstractUserDetailsAuthenticationProvider.badCredentials",
					"Bad credentials"));
		}

	}


	@Override
	protected UserDetails retrieveUser(String name, UsernamePasswordAuthenticationToken auth){
	    String password = auth.getCredentials().toString();
	    UserDetails loadedUser = null;
	    try {
	        ResponseEntity<Principal> authenticationResponse = 
	            authenticationApi.authenticate(name, password);
	        //TODO WTF
	        if (authenticationResponse.getStatusCode().value() == 401) {
	            return new User("wrongUsername", "wrongPass", 
	                new ArrayList<GrantedAuthority>());
	        }
	        Principal principalFromRest = authenticationResponse.getBody();
	        Set<String> privilegesFromRest = Sets.newHashSet(); 
	        // fill in the privilegesFromRest from the Principal
	        String[] authoritiesAsArray = 
	            privilegesFromRest.toArray(new String[privilegesFromRest.size()]);
	        List<GrantedAuthority> authorities = 
	            AuthorityUtils.createAuthorityList(authoritiesAsArray);
	        //TODO
	        //loadedUser = new User(name, password, true, authorities);
	    } catch (Exception ex) {
	        throw new AuthenticationServiceException("Authentication failure", ex);
	    }
	    return loadedUser;
	}

	public void setUserDetailsService(UserDetailsService userDetailsService) {
		this.userDetailsService = userDetailsService;
	}

	protected UserDetailsService getUserDetailsService() {
		return userDetailsService;
	}

}
