package com.salesmanager.shop.store.api.v1.user;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.http.auth.AuthenticationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.salesmanager.shop.store.security.AuthenticationRequest;
import com.salesmanager.shop.store.security.AuthenticationResponse;
import com.salesmanager.shop.store.security.JWTTokenUtil;
import com.salesmanager.shop.store.security.user.JWTUser;

import io.swagger.annotations.Api;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;

/**
 * Authenticates a User (Administration purpose)
 * @author c.samson
 *
 */
@Controller
@RequestMapping("/api/v1")
@Api(tags = { "User authentication api Api" })
@SwaggerDefinition(tags = {
		@Tag(name = "User authentication resource", description = "Login for administrator users") })
public class AuthenticateUserApi {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticateUserApi.class);

    @Value("${authToken.header}")
    private String tokenHeader;

    @Inject
    private AuthenticationManager jwtAdminAuthenticationManager;
    
    @Inject
    private UserDetailsService jwtAdminDetailsService;

    @Inject
    private JWTTokenUtil jwtTokenUtil;

	/**
	 * Authenticate a user using username & password
	 * @param authenticationRequest
	 * @param device
	 * @return
	 * @throws AuthenticationException
	 */
    @RequestMapping(value = "/private/login", method = RequestMethod.POST)
    public ResponseEntity<?> authenticate(@RequestBody @Valid AuthenticationRequest authenticationRequest) throws AuthenticationException {

    	//TODO SET STORE in flow
        // Perform the security
    	Authentication authentication = null;
    	try {
    		
	
        		//to be used when username and password are set
        		authentication = jwtAdminAuthenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(
                                authenticationRequest.getUsername(),
                                authenticationRequest.getPassword()
                        )
                );

    		
    	} catch(Exception e) {
    		if(e instanceof BadCredentialsException) {
    			return new ResponseEntity<>("{\"message\":\"Bad credentials\"}",HttpStatus.UNAUTHORIZED);
    		}
    		LOGGER.error("Error during authentication " + e.getMessage());
    		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    	}
    	
    	if(authentication == null) {
    		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    	}

        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Reload password post-security so we can generate token
        final JWTUser userDetails = (JWTUser)jwtAdminDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        
        final String token = jwtTokenUtil.generateToken(userDetails);

        // Return the token
        return ResponseEntity.ok(new AuthenticationResponse(userDetails.getId(),token));

    }

    @RequestMapping(value = "/auth/refresh", method = RequestMethod.GET)
    public ResponseEntity<AuthenticationResponse> refreshAndGetAuthenticationToken(HttpServletRequest request) {
        String token = request.getHeader(tokenHeader);

        if(token != null && token.contains("Bearer")) {
          token = token.substring("Bearer ".length(),token.length());
        }
        
        String username = jwtTokenUtil.getUsernameFromToken(token);
        JWTUser user = (JWTUser) jwtAdminDetailsService.loadUserByUsername(username);

        if (jwtTokenUtil.canTokenBeRefreshedWithGrace(token, user.getLastPasswordResetDate())) {
            String refreshedToken = jwtTokenUtil.refreshToken(token);
            return ResponseEntity.ok(new AuthenticationResponse(user.getId(),refreshedToken));
        } else {
            return ResponseEntity.badRequest().body(null);
        }
    }
    


}
