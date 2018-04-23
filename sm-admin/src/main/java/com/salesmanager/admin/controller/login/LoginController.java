package com.salesmanager.admin.controller.login;

import java.util.Locale;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.salesmanager.admin.model.common.Constants;
import com.salesmanager.admin.model.common.Messages;
import com.salesmanager.admin.model.common.ResponseToJson;
import com.salesmanager.admin.model.user.LogonUser;

@Controller
public class LoginController {
	
	
	@Inject
	private AuthenticationManager authenticationManager;
	
	@Inject
	private Messages messages;
	
	@RequestMapping("/login")
	public String display(HttpServletRequest request, HttpServletResponse response, Locale locale) throws Exception {
		return "login/login";
	}
	
	@RequestMapping(value="/login/process", method = RequestMethod.POST)
	public ResponseEntity<String> login(@Valid @RequestBody LogonUser user, BindingResult bindingResult, HttpServletRequest request, HttpServletResponse response, Locale locale) throws Exception {
		
		//http://www.springboottutorial.com/spring-boot-validation-for-rest-services
		//http://blog.codeleak.pl/2013/09/request-body-validation-in-spring-mvc-3.2.html
		
		//return "login/login";
		//retrun RESTEntity
		
		//authenticationManager.authenticate(auth)
		
	    if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(ResponseToJson.toObject(messages.get("message.credentials.required", locale), Constants.Code.VALIDATION_ERROR));
        }

		try {
			

	        // Perform the security
	    	Authentication authentication = null;
	    	try {
	    		
	    		authentication = authenticationManager.authenticate(
	                    new UsernamePasswordAuthenticationToken(
	                            user.getUserName(),
	                            user.getPassword()
	                    )
	    		 );
	    		
	    	} catch(Exception e) {
	    		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    	}
	    	
	    	if(authentication == null) {
	    		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    	}

/*	        SecurityContextHolder.getContext().setAuthentication(authentication);

	        // Reload password post-security so we can generate token
	        final JWTUser userDetails = (JWTUser)jwtCustomerDetailsService.loadUserByUsername(customer.getUserName());
	        final String token = jwtTokenUtil.generateToken(userDetails, device);

	        // Return the token
	        return ResponseEntity.ok(new AuthenticationResponse(customer.getId(),token));*/
	    	
	    	return null;

			
		} catch (Exception e) {
			//LOGGER.error("Error while registering customer",e);
			try {
				response.sendError(503, "Error while registering customer " + e.getMessage());
			} catch (Exception ignore) {
			}
			
			return null;
		}
	}

}
