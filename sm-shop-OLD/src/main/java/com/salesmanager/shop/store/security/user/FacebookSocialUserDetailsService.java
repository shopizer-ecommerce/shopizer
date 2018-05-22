package com.salesmanager.shop.store.security.user;


import java.util.Collection;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.social.security.SocialUserDetails;
import org.springframework.social.security.SocialUserDetailsService;
import org.springframework.stereotype.Service;

import com.salesmanager.core.business.services.customer.CustomerService;
import com.salesmanager.core.business.services.user.GroupService;
import com.salesmanager.core.business.services.user.PermissionService;
import com.salesmanager.core.model.customer.Customer;

@Service
public class FacebookSocialUserDetailsService implements SocialUserDetailsService {


	@Inject UserDetailsService customerDetailsService;//delegate to current customer

	
	private static final Logger LOGGER = LoggerFactory.getLogger(FacebookSocialUserDetailsService.class);


	@Override
	public SocialUserDetails loadUserByUserId(String userId) throws UsernameNotFoundException {

		
		CustomerDetails userDetails = (CustomerDetails) customerDetailsService.loadUserByUsername(userId);
		
		if (userDetails == null) {
			throw new UsernameNotFoundException("No user found with username: " + userId);
		}
		
		FacebookTokenUserDetails principal = new FacebookTokenUserDetails(
				userDetails.getUsername(),
				userDetails.getPassword(),
				userDetails.getAuthorities());
		
		principal.setFirstName(userDetails.getFirstName());
		principal.setId(String.valueOf(userDetails.getId()));
		principal.setLastName(userDetails.getLastName());

		LOGGER.debug("Found user details: {}", principal);

		return principal;

	}






}
