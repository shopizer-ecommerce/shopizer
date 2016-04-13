/**
 *
 */
package com.salesmanager.web.shop.controller;

import javax.servlet.http.HttpServletRequest;

import org.jopendocument.util.ExceptionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import com.salesmanager.core.business.merchant.model.MerchantStore;
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
    

 /*   
	@ExceptionHandler(Exception.class)
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	public String handleException(Model model, Exception ex, HttpServletRequest request) {
		
		
		MerchantStore store = getSessionAttribute(Constants.MERCHANT_STORE, request);
		StringBuilder template = null;
		//ModelAndView model = null;
		if(ex instanceof AccessDeniedException) {
			
			
			if(store != null) {
			
				
				template = new StringBuilder().append(ControllerConstants.Tiles.Error.accessDenied).append(".").append(store.getStoreTemplate());
				
				
			} else {
				
				template = new StringBuilder().append(ControllerConstants.Tiles.Error.accessDenied);
				
			}
			
			//model = new ModelAndView("error/access_denied");
			
		} else {
			
			model.addAttribute("stackError", ExceptionUtils.getStackTrace(ex));
			model.addAttribute("errMsg", ex.getMessage());
			
			if(store != null) {
			
				
				template = new StringBuilder().append(ControllerConstants.Tiles.Error.error).append(".").append(store.getStoreTemplate());
				
				
			} else {
				
				template = new StringBuilder().append(ControllerConstants.Tiles.Error.error);
				
			}
			
			//model = new ModelAndView("error/generic_error");
			//model.addObject("stackError", ExceptionUtils.getStackTrace(ex));
			//model.addObject("errMsg", ex.getMessage());
			
		}
		
		
 
		return template.toString();
 
	}
*/
    protected PaginationData createPaginaionData( final int pageNumber, final int pageSize )
    {
        final PaginationData paginaionData = new PaginationData(pageSize,pageNumber);
       
        return paginaionData;
    }
    
    protected PaginationData calculatePaginaionData( final PaginationData paginationData, final int pageSize, final int resultCount){
        
    	int currentPage = paginationData.getCurrentPage();


    	int count = Math.min((currentPage * pageSize), resultCount);  
    	paginationData.setCountByPage(count);

    	paginationData.setTotalCount( resultCount );
        return paginationData;
    }
}
