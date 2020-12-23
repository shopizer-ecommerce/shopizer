package com.salesmanager.shop.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;


//@Component
//@Order(5) //after other defined filters
public class XssFilter implements Filter {

	 /**
	  * Description: Log
	  */
	 private static final Logger LOGGER = LoggerFactory.getLogger(XssFilter.class);

	 @Override
	 public void init(FilterConfig filterConfig) throws ServletException {
	  LOGGER.debug("(XssFilter) initialize");
	 }

/*	 @Override
	 public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
	   throws IOException, ServletException {
	  XssHttpServletRequestWrapper xssRequest =
	    new XssHttpServletRequestWrapper((HttpServletRequest) request);
	  chain.doFilter(xssRequest, response);
	 }*/
	 

/*	 protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
	 		FilterChain filterChain) throws ServletException, IOException {

	 	filterChain.doFilter(new XssHttpServletRequestWrapper(request) {


	 	}, new HttpServletResponseWrapper(response));
	 }*/
	 
	 @Override
	 public void doFilter(ServletRequest srequest, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {

/*	 		HttpServletRequest request = (HttpServletRequest) srequest;
	 		//final String realIp = request.getHeader(X_FORWARDED_FOR);

	 		//if (realIp != null) {
	 			filterChain.doFilter(new XssHttpServletRequestWrapper(request) {
	 				*//**
	 				public String getRemoteAddr() {
	 					return realIp;
	 				}

	 				public String getRemoteHost() {
	 					return realIp;
	 				}
	 				**//*
	 			}, response);

	 			return;
	 		//}

*/

	 }

	 @Override
	 public void destroy() {
	  LOGGER.debug("(XssFilter) destroy");
	 }

}
