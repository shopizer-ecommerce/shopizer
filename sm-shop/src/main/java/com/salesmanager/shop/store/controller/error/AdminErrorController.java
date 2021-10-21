package com.salesmanager.shop.store.controller.error;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.ui.Model;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice("com.salesmanager.shop.admin")
public class AdminErrorController {


	private static final Logger LOGGER = LoggerFactory.getLogger(AdminErrorController.class);
	private static final String LOG_ERROR_MESSAGE = "Error page controller";

	/**
	 * Handles specific Spring MVC internal exceptions as HTTP status 400
	 * @param ex the actual exception
	 * @return an error model
	 */
	// list of "BAD REQUEST"-related exceptions are taken over from Springs DefaultHandlerExceptionResolver
	@ExceptionHandler({
			MissingServletRequestParameterException.class,
			ServletRequestBindingException.class,
			TypeMismatchException.class,
			HttpMessageNotReadableException.class,
			MethodArgumentNotValidException.class,
			MissingServletRequestPartException.class,
			BindException.class
	})
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	@Produces({MediaType.APPLICATION_JSON})
	public ModelAndView handleBadRequest(Exception ex) {

		LOGGER.error(LOG_ERROR_MESSAGE,ex);
		return createGenericErrorModel(ex);

	}
    
	@ExceptionHandler(Exception.class)
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	@Produces({MediaType.APPLICATION_JSON})
	public ModelAndView handleException(Exception ex) {
		
		LOGGER.error(LOG_ERROR_MESSAGE,ex);

		ModelAndView model;
		if(ex instanceof AccessDeniedException) {
			
			model = new ModelAndView("error/access_denied");
			
		} else {

			model = createGenericErrorModel(ex);

		}

		return model;
 
	}

	@ExceptionHandler(RuntimeException.class)
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	@Produces({MediaType.APPLICATION_JSON})
	public ModelAndView handleRuntimeException(Exception ex) {
		
		LOGGER.error(LOG_ERROR_MESSAGE,ex);
		
		return createGenericErrorModel(ex);

	}
	
	/**
	 * Generic exception catch allpage
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/error", method=RequestMethod.GET)
	public ModelAndView handleCatchAllException(Model model) {
		
		return new ModelAndView("error/generic_error");

	}

	private ModelAndView createGenericErrorModel(Exception ex) {
		ModelAndView model = new ModelAndView("error/generic_error");
		model.addObject("stackError", ExceptionUtils.getStackTrace(ex));
		model.addObject("errMsg", ex.getMessage());
		return model;
	}
}
