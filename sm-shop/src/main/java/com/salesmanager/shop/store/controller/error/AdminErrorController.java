package com.salesmanager.shop.store.controller.error;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice("com.salesmanager.shop.admin")
public class AdminErrorController {
	
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AdminErrorController.class);
	
    
	@ExceptionHandler(Exception.class)
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	@Produces({MediaType.APPLICATION_JSON})
	public ModelAndView handleException(Exception ex) {
		
		LOGGER.error("Error page controller",ex);

		ModelAndView model = null;
		if(ex instanceof AccessDeniedException) {
			
			model = new ModelAndView("error/access_denied");
			
		} else {
			
			model = new ModelAndView("error/generic_error");
			model.addObject("stackError", ExceptionUtils.getStackTrace(ex));
			model.addObject("errMsg", ex.getMessage());
			
		}

		return model;
 
	}
	

	
	@ExceptionHandler(RuntimeException.class)
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	@Produces({MediaType.APPLICATION_JSON})
	public ModelAndView handleRuntimeException(Exception ex) {
		
		LOGGER.error("Error page controller",ex);
		
		ModelAndView model = null;

			
		model = new ModelAndView("error/generic_error");
		model.addObject("stackError", ExceptionUtils.getStackTrace(ex));
		model.addObject("errMsg", ex.getMessage());

		
		
 
		return model;
 
	}
	
	/**
	 * Generic exception catch allpage
	 * @param ex
	 * @return
	 */
	@RequestMapping(value="/error", method=RequestMethod.GET)
	public ModelAndView handleCatchAllException(Model model) {

		
		ModelAndView modelAndView = null;

			
		modelAndView = new ModelAndView("error/generic_error");
 
		return modelAndView;
 
	}


}
