package com.salesmanager.web.admin.security;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import com.salesmanager.core.business.user.model.User;
import com.salesmanager.core.business.user.service.UserService;

public class UserAuthenticationSuccessHandler extends
		SavedRequestAwareAuthenticationSuccessHandler {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UserAuthenticationSuccessHandler.class);
	
	@Autowired
	private UserService userService;
	
	  @Override
	    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
		  // last access timestamp
		  String userName = authentication.getName();
		  
		  try {
			  User user = userService.getByUserName(userName);
			  
			  Date lastAccess = user.getLoginTime();
			  if(lastAccess==null) {
				  lastAccess = new Date();
			  }
			  user.setLastAccess(lastAccess);
			  user.setLoginTime(new Date());
			  
			  userService.saveOrUpdate(user);
			  
			  response.sendRedirect(request.getContextPath() + "/admin/home.html");
		  } catch (Exception e) {
			  LOGGER.error("User authenticationSuccess",e);
		  }
	   }

}
