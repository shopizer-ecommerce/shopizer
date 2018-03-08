package com.salesmanager.shop.admin.security;

import com.salesmanager.core.business.services.user.UserService;
import com.salesmanager.core.model.user.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.Date;

public class UserAuthenticationSuccessHandler extends
		SavedRequestAwareAuthenticationSuccessHandler {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UserAuthenticationSuccessHandler.class);
	
	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
	
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
			  
			  //response.sendRedirect(request.getContextPath() + "/admin/home.html");
			  redirectStrategy.sendRedirect(request, response, "/admin/home.html");
			  
		  
		  
		  } catch (Exception e) {
			  LOGGER.error("User authenticationSuccess",e);
		  }
		  

	   }
	    
	    public void setRedirectStrategy(RedirectStrategy redirectStrategy) {
	        this.redirectStrategy = redirectStrategy;
	    }
	    protected RedirectStrategy getRedirectStrategy() {
	        return redirectStrategy;
	    }

}
