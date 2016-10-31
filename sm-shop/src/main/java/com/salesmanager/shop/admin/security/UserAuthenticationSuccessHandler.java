package com.salesmanager.shop.admin.security;

import com.salesmanager.core.business.services.user.UserService;
import com.salesmanager.core.model.user.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

public class UserAuthenticationSuccessHandler extends
		SavedRequestAwareAuthenticationSuccessHandler {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UserAuthenticationSuccessHandler.class);
	
	@Inject
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
