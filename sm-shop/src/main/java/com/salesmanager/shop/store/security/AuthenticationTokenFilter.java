package com.salesmanager.shop.store.security;

import java.io.IOException;
import java.util.Enumeration;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.inject.Inject;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.filter.OncePerRequestFilter;

import com.salesmanager.core.model.common.UserContext;
import com.salesmanager.shop.store.security.common.CustomAuthenticationManager;
import com.salesmanager.shop.utils.GeoLocationUtils;


public class AuthenticationTokenFilter extends OncePerRequestFilter {


	private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationTokenFilter.class);

    
    @Value("${authToken.header}")
    private String tokenHeader;
    
    private final static String BEARER_TOKEN ="Bearer ";
    
    private final static String FACEBOOK_TOKEN ="FB ";
    
    //private final static String privateApiPatternString = "/api/v*/private";
    
    //private final static Pattern pattern = Pattern.compile(privateApiPatternString);
    

    
    @Inject
    private CustomAuthenticationManager jwtCustomCustomerAuthenticationManager;
    
    @Inject
    private CustomAuthenticationManager jwtCustomAdminAuthenticationManager;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        

    	String origin = "*";
    	if(!StringUtils.isBlank(request.getHeader("origin"))) {
    		origin = request.getHeader("origin");
    	}
    	//in flight
    	response.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, OPTIONS, DELETE, PATCH");
    	response.setHeader("Access-Control-Allow-Origin", origin);
    	response.setHeader("Access-Control-Allow-Headers", "X-Auth-Token, Content-Type, Authorization, Cache-Control, X-Requested-With");
    	response.setHeader("Access-Control-Allow-Credentials", "true");

    	try {
    		
    		String ipAddress = GeoLocationUtils.getClientIpAddress(request);
    		
    		UserContext userContext = UserContext.create();
    		userContext.setIpAddress(ipAddress);
    		
    	} catch(Exception s) {
    		LOGGER.error("Error while getting ip address ", s);
    	}
    	
    	String requestUrl = request.getRequestURL().toString();


    	if(requestUrl.contains("/api/v1/auth")) {
    		//setHeader(request,response);   	
	    	final String requestHeader = request.getHeader(this.tokenHeader);//token
	    	
	    	try {
		        if (requestHeader != null && requestHeader.startsWith(BEARER_TOKEN)) {//Bearer
		        	
		        	jwtCustomCustomerAuthenticationManager.authenticateRequest(request, response);
	
		        } else if(requestHeader != null && requestHeader.startsWith(FACEBOOK_TOKEN)) {
		        	//Facebook
		        	//facebookCustomerAuthenticationManager.authenticateRequest(request, response);
		        } else {
		        	LOGGER.warn("couldn't find any authorization token, will ignore the header");
		        }
	        
	    	} catch(Exception e) {
	    		throw new ServletException(e);
	    	}
    	}
 
    	
    	if(requestUrl.contains("/api/v1/private") || requestUrl.contains("/api/v2/private")) {
    		
    		//setHeader(request,response);  
    		
    		Enumeration<String> headers = request.getHeaderNames();
    		//while(headers.hasMoreElements()) {
    			//LOGGER.debug(headers.nextElement());
    		//}

	    	final String requestHeader = request.getHeader(this.tokenHeader);//token
	    	
	    	try {
		        if (requestHeader != null && requestHeader.startsWith(BEARER_TOKEN)) {//Bearer
		        	
		        	jwtCustomAdminAuthenticationManager.authenticateRequest(request, response);
	
		        } else {
		        	LOGGER.warn("couldn't find any authorization token, will ignore the header, might be a preflight check");
		        }
	        
	    	} catch(Exception e) {
	    		throw new ServletException(e);
	    	}
    	}

        chain.doFilter(request, response);
        postFilter(request, response, chain);
    }
    
    
    private void postFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
    	
    	try {
    		
    		UserContext userContext = UserContext.getCurrentInstance();
    		if(userContext!=null) {
    			userContext.close();
    		}
    		
    	} catch(Exception s) {
    		LOGGER.error("Error while getting ip address ", s);
    	}
    	
    }


}
