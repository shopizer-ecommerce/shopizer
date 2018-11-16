package com.salesmanager.admin.components.content.util;

import javax.servlet.http.HttpServletRequest;

public class ServletUtils {


    public static String getBaseUrl(HttpServletRequest request) {
        return request.getRequestURL().substring(0, request.getRequestURL().length() - request.getRequestURI().length()) + request.getContextPath();
    }
}
