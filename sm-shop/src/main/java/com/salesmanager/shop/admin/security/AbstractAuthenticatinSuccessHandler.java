package com.salesmanager.shop.admin.security;

import java.util.Date;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import com.salesmanager.core.business.services.user.UserService;
import com.salesmanager.core.model.user.User;

public abstract class AbstractAuthenticatinSuccessHandler extends
SavedRequestAwareAuthenticationSuccessHandler {
	
	abstract protected void redirectAfterSuccess(HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AbstractAuthenticatinSuccessHandler.class);
	
	
	@Inject
	private UserService userService;
	
	    @Override
	    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
		  // last access timestamp
		  String userName = authentication.getName();
		  
		  /**
		   * Spring Security 4 does not seem to add security context in the session
		   * creating the authentication to be lost during the login
		   */
		  SecurityContext securityContext = SecurityContextHolder.getContext();
		  HttpSession session = request.getSession(true);
		  session.setAttribute("SPRING_SECURITY_CONTEXT", securityContext);
		  
		  try {
			  User user = userService.getByUserName(userName);
			  
			  Date lastAccess = user.getLoginTime();
			  if(lastAccess==null) {
				  lastAccess = new Date();
			  }
			  user.setLastAccess(lastAccess);
			  user.setLoginTime(new Date());
			  
			  userService.saveOrUpdate(user);
			  
			  redirectAfterSuccess(request,response);

		  
		  } catch (Exception e) {
			  LOGGER.error("User authenticationSuccess",e);
		  }
		  

	   }

}
