package com.salesmanager.shop.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class CorsFilter extends HandlerInterceptorAdapter {

		public CorsFilter() {
			
		}

		/**
		 * Allows public web services to work from remote hosts
		 */
	   public boolean preHandle(
	            HttpServletRequest request,
	            HttpServletResponse response,
	            Object handler) throws Exception {
		   
        	HttpServletResponse httpResponse = (HttpServletResponse) response;
	
	        httpResponse.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, OPTIONS, DELETE");
        	httpResponse.setHeader("Access-Control-Allow-Headers", "X-Auth-Token, Content-Type");
        	httpResponse.setHeader("Access-Control-Allow-Origin", "*");
	        
        	return true;
			
		}
}
