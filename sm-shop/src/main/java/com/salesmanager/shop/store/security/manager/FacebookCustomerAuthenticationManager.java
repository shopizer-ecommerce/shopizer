package com.salesmanager.shop.store.security.manager;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationDetailsSource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.social.UserIdSource;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionData;
//import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.ConnectionKey;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.connect.UserProfile;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.support.OAuth2ConnectionFactory;
import org.springframework.social.oauth2.AccessGrant;
import org.springframework.social.security.SocialAuthenticationException;
import org.springframework.social.security.SocialAuthenticationRedirectException;
import org.springframework.social.security.SocialAuthenticationServiceLocator;
import org.springframework.social.security.SocialAuthenticationToken;
import org.springframework.social.security.SocialUserDetails;
import org.springframework.social.security.provider.SocialAuthenticationService;
import org.springframework.social.support.URIBuilder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.salesmanager.core.business.services.user.UserService;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.shop.constants.Constants;
import com.salesmanager.shop.model.customer.Address;
import com.salesmanager.shop.model.customer.CustomerEntity;
import com.salesmanager.shop.model.customer.PersistableCustomer;
import com.salesmanager.shop.model.customer.UserAlreadyExistException;
import com.salesmanager.shop.store.controller.customer.facade.CustomerFacade;
import com.salesmanager.shop.store.controller.store.facade.StoreFacade;
import com.salesmanager.shop.store.security.user.CustomerDetails;
import com.salesmanager.shop.utils.LanguageUtils;



@Component("facebookCustomerAuthenticationManager")
public class FacebookCustomerAuthenticationManager extends CustomAuthenticationManager {
	
	protected final Log logger = LogFactory.getLog(getClass());
	
	@Value("${facebook.app.access_token}")
	private String access_token;

	private static final String providerId = "facebook";
	
	@Inject
	private AuthenticationManager facebookAuthenticationManager;
	
	@Inject
	private CustomerFacade customerFacade;
	
	@Inject
	private StoreFacade storeFacade;
	
	@Inject
	private LanguageUtils languageUtils;
	
	//@Inject
	//private UserService service;

	@Inject
	private SocialAuthenticationServiceLocator authenticationServiceLocator;
	//private ConnectionFactoryLocator connectionFactoryLocator;
	
	@Inject
	private UsersConnectionRepository socialUsersConnectionRepository;
	private AuthenticationDetailsSource<HttpServletRequest, ?> authenticationDetailsSource = new WebAuthenticationDetailsSource();
	private UserIdSource userIdSource = new org.springframework.social.security.AuthenticationNameUserIdSource();
	private SimpleUrlAuthenticationFailureHandler delegateAuthenticationFailureHandler;
	
	/** Entry point of facebook authentication, requires FB <token> **/
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException, Exception {

		Authentication auth = null;
		Set<String> authProviders = authenticationServiceLocator.registeredAuthenticationProviderIds();
		if (!authProviders.isEmpty() && authProviders.contains(providerId)) {
			SocialAuthenticationService<?> authService = authenticationServiceLocator.getAuthenticationService(providerId);
			auth = attemptAuthService(authService, request, response);
			if (auth == null) {
				throw new AuthenticationServiceException("authentication failed");
			}
		}
		return auth;
	}


/*	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		// --> //super.successfulAuthentication(request, response, chain, authResult);
		chain.doFilter(request, response);
	}*/

	@Deprecated
	protected boolean requiresAuthentication(HttpServletRequest request, HttpServletResponse response) {
		return true;
	}

	protected Connection<?> addConnection(SocialAuthenticationService<?> authService, String userId,
			ConnectionData data) {
		HashSet<String> userIdSet = new HashSet<String>();
		userIdSet.add(data.getProviderUserId());
		Set<String> connectedUserIds = socialUsersConnectionRepository.findUserIdsConnectedTo(data.getProviderId(),
				userIdSet);
		if (connectedUserIds.contains(userId)) {
			// already connected
			return null;
		} else if (!authService.getConnectionCardinality().isMultiUserId() && !connectedUserIds.isEmpty()) {
			return null;
		}

		ConnectionRepository repo = socialUsersConnectionRepository.createConnectionRepository(userId);

		if (!authService.getConnectionCardinality().isMultiProviderUserId()) {
			List<Connection<?>> connections = repo.findConnections(data.getProviderId());
			if (!connections.isEmpty()) {
				// TODO maybe throw an exception to allow UI feedback?
				return null;
			}
		}

		// add new connection
		Connection<?> connection = authService.getConnectionFactory().createConnection(data);
		connection.sync();
		repo.addConnection(connection);
		return connection;
	}

	private Authentication attemptAuthService(final SocialAuthenticationService<?> authService,
			final HttpServletRequest request, HttpServletResponse response)
					throws SocialAuthenticationRedirectException, AuthenticationException, Exception {
		
		final String requestHeader = request.getHeader(super.getTokenHeader());//token
		
		if (requestHeader == null) {
			throw new SocialAuthenticationException("No token in the request");
		}
		
		if (!requestHeader.startsWith("FB ")) {//FB token
			throw new SocialAuthenticationException("Token must start with FB");
		}
		
		String input_token = requestHeader.substring(3);
		
		URIBuilder builder = URIBuilder.fromUri(String.format("%s/debug_token", "https://graph.facebook.com"));
		builder.queryParam("access_token", access_token);
		builder.queryParam("input_token", input_token);
		URI uri = builder.build();
		RestTemplate restTemplate = new RestTemplate();

		JsonNode resp = null;
		try {
			resp = restTemplate.getForObject(uri, JsonNode.class);
		} catch (HttpClientErrorException e) {
			throw new SocialAuthenticationException("Error validating token");
		}
		Boolean isValid = resp.path("data").findValue("is_valid").asBoolean();
		if (!isValid)
			throw new SocialAuthenticationException("Token is not valid");

		AccessGrant accessGrant = new AccessGrant(input_token, null, null,
				resp.path("data").findValue("expires_at").longValue());

		Connection<?> connection = ((OAuth2ConnectionFactory<?>) authService.getConnectionFactory())
				.createConnection(accessGrant);
		SocialAuthenticationToken token = new SocialAuthenticationToken(connection, null);
		Assert.notNull(token.getConnection());

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth == null || !auth.isAuthenticated()) {
			return doAuthentication(authService, request, token);
		} else {
			addConnection(authService, request, token);
			return null;
		}

	}

	private void addConnection(final SocialAuthenticationService<?> authService, HttpServletRequest request,
			SocialAuthenticationToken token) {
		// already authenticated - add connection instead
		String userId = userIdSource.getUserId();
		Object principal = token.getPrincipal();
		if (userId == null || !(principal instanceof ConnectionData))
			return;

		addConnection(authService, userId, (ConnectionData) principal);

	}

	private Authentication doAuthentication(SocialAuthenticationService<?> authService, HttpServletRequest request,
			SocialAuthenticationToken token) throws Exception {
		try {
			if (!authService.getConnectionCardinality().isAuthenticatePossible())
				return null;
			token.setDetails(authenticationDetailsSource.buildDetails(request));
			Authentication success = facebookAuthenticationManager.authenticate(token);
			Assert.isInstanceOf(SocialUserDetails.class, success.getPrincipal(), "unexpected principle type");
			updateConnections(authService, token, success);
			return success;
			
		} catch (BadCredentialsException e) {
			
			CustomerDetails registered = null;
			PersistableCustomer registration = null;
			try {
				
				MerchantStore merchantStore = storeFacade.getByCode(com.salesmanager.core.business.constants.Constants.DEFAULT_STORE);
				Language language = languageUtils.getRESTLanguage(request, merchantStore);	

				registration = register(token.getConnection());
				
				
				PersistableCustomer c = customerFacade.registerCustomer(registration, merchantStore, language);
				
				Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
				GrantedAuthority role = new SimpleGrantedAuthority("ROLE_" + Constants.PERMISSION_CUSTOMER_AUTHENTICATED);//required to login
				authorities.add(role);
				
				registered = new CustomerDetails(
						c.getEmailAddress(),
						c.getEncodedPassword(),
						authorities);
				
			} catch (UserAlreadyExistException e1) {
				//we create the user anyway
				//throw new SocialAuthenticationException("An email address was found from the database.");
			}
			ConnectionRepository repo = socialUsersConnectionRepository.createConnectionRepository(registration.getEmailAddress());
			repo.addConnection(token.getConnection());
			Authentication success = facebookAuthenticationManager.authenticate(token);
			return success;

		}
		
	}

	private void updateConnections(SocialAuthenticationService<?> authService, SocialAuthenticationToken token,
			Authentication success) {

		String userId = ((SocialUserDetails) success.getPrincipal()).getUserId();
		Connection<?> connection = token.getConnection();
		ConnectionRepository repo = socialUsersConnectionRepository.createConnectionRepository(userId);
		repo.updateConnection(connection);

	}


	@Override
	void successfullAuthentication(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws AuthenticationException {
		logger.debug("Successfull FB authentication");
		
	}


	@Override
	void unSuccessfullAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		logger.debug("Un successfull FB authentication");
		
	}



	private PersistableCustomer register(Connection<?> connection) {
		PersistableCustomer customer = new PersistableCustomer();
		if (connection != null) {
			UserProfile socialMediaProfile = connection.fetchUserProfile();
			
			ConnectionKey providerKey = connection.getKey();
			
			customer.setEmailAddress(socialMediaProfile.getEmail());
			customer.setUserName(socialMediaProfile.getEmail());
			customer.setFirstName(socialMediaProfile.getFirstName());
			customer.setLastName(socialMediaProfile.getLastName());
			customer.setProvider(providerKey.getProviderId());
			
			//create dummy password
			
			Address address = new Address();
			address.setFirstName(socialMediaProfile.getFirstName());
			address.setLastName(socialMediaProfile.getLastName());
			address.setCountry(com.salesmanager.core.business.constants.Constants.DEFAULT_COUNTRY);
			
			customer.setBilling(address);

		}

		return customer;
	}
	
	

}
