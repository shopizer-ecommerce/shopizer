/**
 *
 */
package com.salesmanager.shop.utils;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Umesh Awasthi
 *
 */
public class SessionUtil
{


    
    @SuppressWarnings("unchecked")
	public static <T> T getSessionAttribute(final String key, HttpServletRequest request) {
        return (T) request.getSession().getAttribute( key );
    }
    
	public static void removeSessionAttribute(final String key, HttpServletRequest request) {
        request.getSession().removeAttribute( key );
    }

    public static void setSessionAttribute(final String key, final Object value, HttpServletRequest request) {
    	request.getSession().setAttribute( key, value );
    }


}
