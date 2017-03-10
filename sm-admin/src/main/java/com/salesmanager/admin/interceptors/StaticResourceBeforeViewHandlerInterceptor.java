package com.salesmanager.admin.interceptors;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by umesh on 3/9/17.
 */
//@Component()
public class StaticResourceBeforeViewHandlerInterceptor extends HandlerInterceptorAdapter {

    protected static final String CONTEXT_PATH = "contextPath";
    @Override
    public void postHandle(
            HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)
            throws Exception {

            final String contextPath = request.getContextPath();
            if(null !=modelAndView) {
                modelAndView.addObject(CONTEXT_PATH, contextPath);
            }
    }
}
