package com.salesmanager.admin.filter;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.salesmanager.admin.utils.Constants;


/**
 * Get Authentication and set objects in HttpRequest
 * This class makes sure non REST API requests are managed
 * @author carl samson
 *
 */
public class AdminFilter extends HandlerInterceptorAdapter {
	
	public boolean preHandle(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler) throws Exception {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		//push token to request
		if(auth != null && auth.getDetails() != null) {
					@SuppressWarnings("unchecked")
					Map<String,String> details = (Map<String, String>) auth.getDetails();
					String token = details.get(Constants.TOKEN);
					if(!StringUtils.isEmpty(token)) {
						request.setAttribute(Constants.TOKEN, token);
					}
		}
		
		//push supported languages to request
		/**
		 * Query cache for languages
		 */
		
		//push user details in request
		/**
		 * User details are in Auth details
		 */
		
		return true;
		
	}
	

}
