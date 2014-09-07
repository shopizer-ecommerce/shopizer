/**
 *
 */
package com.salesmanager.web.shop.controller;

import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.jopendocument.util.ExceptionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import com.salesmanager.core.business.generic.exception.ServiceException;
import com.salesmanager.core.business.reference.country.model.Country;
import com.salesmanager.core.business.reference.language.model.Language;
import com.salesmanager.web.constants.Constants;
import com.salesmanager.web.shop.model.paging.PaginationData;

/**
 * @author Umesh A
 *
 */
public abstract class AbstractController {


    /**
     * Method which will help to retrieving values from Session
     * based on the key being passed to the method.
     * @param key
     * @return value stored in session corresponding to the key
     */
    @SuppressWarnings( "unchecked" )
    protected <T> T getSessionAttribute(final String key, HttpServletRequest request) {
	          return (T) com.salesmanager.web.utils.SessionUtil.getSessionAttribute(key, request);

	}
    
    protected void setSessionAttribute(final String key, final Object value, HttpServletRequest request) {
    	com.salesmanager.web.utils.SessionUtil.setSessionAttribute(key, value, request);
	}
    
    
    protected void removeAttribute(final String key, HttpServletRequest request) {
    	com.salesmanager.web.utils.SessionUtil.removeSessionAttribute(key, request);
	}
    
    protected Language getLanguage(HttpServletRequest request) {
    	return (Language)request.getAttribute(Constants.LANGUAGE);
    }
    

    
	@ExceptionHandler(Exception.class)
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	public ModelAndView handleException(Exception ex) {
		
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

    protected PaginationData createPaginaionData( final int pageNumber, final int pageSize )
    {
        final PaginationData paginaionData = new PaginationData(pageSize,pageNumber);
       
        return paginaionData;
    }
    
    protected PaginationData calculatePaginaionData( final PaginationData paginaionData, final int resultCount){
        paginaionData.setTotalCount( resultCount );
        return paginaionData;
    }
}
