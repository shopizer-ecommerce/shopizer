package com.salesmanager.shop.store.controller.customer;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;

import com.salesmanager.shop.constants.Constants;

public class CustomerLogoutSuccessHandler extends SimpleUrlLogoutSuccessHandler{

	public CustomerLogoutSuccessHandler(String defaultTargetURL) {
		this.setDefaultTargetUrl(defaultTargetURL);
	}
	
	@Override
	public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		// removing the shoping cart code from the cookie
		Cookie c = new Cookie(Constants.COOKIE_NAME_CART, "");
        c.setMaxAge(60 * 24 * 3600);
        c.setPath(Constants.SLASH);
        response.addCookie(c);
		super.onLogoutSuccess(request, response, authentication);

	}
}
