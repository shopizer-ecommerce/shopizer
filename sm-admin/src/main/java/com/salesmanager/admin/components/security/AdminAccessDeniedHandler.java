package com.salesmanager.admin.components.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

@Component("accessDeniedHandler")
public class AdminAccessDeniedHandler implements AccessDeniedHandler {


	    private static final Logger logger = LoggerFactory.getLogger(AdminAccessDeniedHandler.class);
	 
	    @Override
	    public void handle(
	      HttpServletRequest request,
	      HttpServletResponse response, 
	      AccessDeniedException exc) throws IOException, ServletException {
	         
	        Authentication auth 
	          = SecurityContextHolder.getContext().getAuthentication();
	        if (auth != null) {
	        	logger.warn("User: " + auth.getName() 
	              + " attempted to access the protected URL: "
	              + request.getRequestURI());
	        }
	 
	        response.sendRedirect(request.getContextPath() + "/login/accessDenied");
	    }


}
