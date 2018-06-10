package com.salesmanager.shop.store.security;

import java.io.IOException;
import java.util.Collection;
import java.util.Enumeration;

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

import com.salesmanager.shop.store.security.common.CustomAuthenticationManager;


public class AuthenticationTokenFilter extends OncePerRequestFilter {


	private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationTokenFilter.class);

    
    @Value("${authToken.header}")
    private String tokenHeader;
    
    private final static String BEARER_TOKEN ="Bearer ";
    
    private final static String FACEBOOK_TOKEN ="FB ";

    
    @Inject
    private CustomAuthenticationManager jwtCustomCustomerAuthenticationManager;
    
    @Inject
    private CustomAuthenticationManager jwtCustomAdminAuthenticationManager;

    @Inject
    private CustomAuthenticationManager facebookCustomerAuthenticationManager;
    

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        

    	String origin = "*";
    	if(!StringUtils.isBlank(request.getHeader("origin"))) {
    		origin = request.getHeader("origin");
    	}
    	response.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, OPTIONS, DELETE");
    	response.setHeader("Access-Control-Allow-Origin", origin);
    	response.setHeader("Access-Control-Allow-Headers", "X-Auth-Token, Content-Type, Authorization");
    	response.setHeader("Access-Control-Allow-Credentials", "true");

        	
    	//@TODO edit this
    	if(request.getRequestURL().toString().contains("/api/v1/auth")) {
    		//setHeader(request,response);   	
	    	final String requestHeader = request.getHeader(this.tokenHeader);//token
	    	
	    	try {
		        if (requestHeader != null && requestHeader.startsWith(BEARER_TOKEN)) {//Bearer
		        	
		        	jwtCustomCustomerAuthenticationManager.authenticateRequest(request, response);
	
		        } else if(requestHeader != null && requestHeader.startsWith(FACEBOOK_TOKEN)) {
		        	//Facebook
		        	facebookCustomerAuthenticationManager.authenticateRequest(request, response);
		        } else {
		        	LOGGER.warn("couldn't find any authorization token, will ignore the header");
		        }
	        
	    	} catch(Exception e) {
	    		throw new ServletException(e);
	    	}
    	}
    	
    	if(request.getRequestURL().toString().contains("/api/v1/private")) {
    		
    		//setHeader(request,response);  
    		
    		Enumeration<String> headers = request.getHeaderNames();
    		while(headers.hasMoreElements()) {
    			LOGGER.debug(headers.nextElement());
    		}

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
    }
    
/*    private void setHeader(HttpServletRequest request, HttpServletResponse response) {
    	
    

    	
    	String origin = request.getHeader("origin");
    	if(StringUtils.isBlank(origin)) {
    		origin = "*";
    	}
    	response.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, OPTIONS, DELETE");
    	response.setHeader("Access-Control-Allow-Origin", origin);
    	
    	*//**
    	 * Simplify options
    	 *//*
    	
    	if("options".equalsIgnoreCase(request.getMethod())) {
    		try {
                ((HttpServletResponse) response).setStatus(200);
                byte[]  b = "".getBytes();
                response.getOutputStream().write(b);
                return;
    		} catch(Exception e) {
    			e.printStackTrace();
    		}

    	}

    	response.setHeader("Access-Control-Allow-Headers", "X-Auth-Token, Content-Type, Authorization");
    	response.setHeader("Access-Control-Allow-Credentials", "true");
	
    	
    }*/
    
    private byte[] restOptionsResponseBytes() throws IOException {
        String serialized = "{\"status\":200}";
        return serialized.getBytes();
    }



}
