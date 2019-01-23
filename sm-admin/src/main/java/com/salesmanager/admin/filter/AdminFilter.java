package com.salesmanager.admin.filter;

import java.util.List;
import java.util.Locale;
import java.util.Map;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import com.salesmanager.admin.components.security.AdminAuthenticationToken;
import com.salesmanager.admin.model.references.Language;
import com.salesmanager.admin.model.web.Menu;
import com.salesmanager.admin.utils.CacheHelper;
import com.salesmanager.admin.utils.Constants;


/**
 * Get Authentication and set objects in HttpRequest This class makes sure non REST API requests are
 * managed Puts reference objects in HttpServletRequest
 * 
 * @author carl samson
 *
 */
public class AdminFilter extends HandlerInterceptorAdapter {

  private static final Logger logger = LoggerFactory.getLogger(AdminFilter.class);

  @Inject
  CacheHelper cacheHelper;

  @Value("${shopizer.api.url}")
  private String backend;

  @Value("${shopizer.images.url}")
  private String imagesHost;

  @Value("${version.number}")
  private String versionNumber;

  @SuppressWarnings("unchecked")
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
      throws Exception {

    Locale locale = LocaleContextHolder.getLocale();

    Authentication auth = SecurityContextHolder.getContext().getAuthentication();

    // push token to request
    if (auth != null && auth instanceof AdminAuthenticationToken && auth.getDetails() != null) {
      @SuppressWarnings("unchecked")
      Map<String, String> details = (Map<String, String>) auth.getDetails();
      String token = details.get(Constants.TOKEN);
      if (!StringUtils.isEmpty(token)) {
        request.setAttribute(Constants.TOKEN, token);
      }
    }

    // push supported languages to request
    /**
     * Query cache for languages
     */

    try {

      List<Language> languages = cacheHelper.getLanguages(locale);
      request.setAttribute(Constants.Cache.LANGUAGE, languages);

    } catch (Exception e) {
      logger.error("Cannot retrieve languages" + e.getMessage());
    }

    /*
     * //push supported country to request
     *//**
       * Query cache for country
       *//*
         * 
         * try { //TODO set default Country and Zone code List<Country> country =
         * cacheHelper.getCountry(locale); request.setAttribute(Constants.Cache.COUNTRY,country);
         * 
         * } catch(Exception e) { logger.error("Cannot retrieve country" + e.getMessage()); }
         */


    // push user details in request
    /**
     * User details are in Auth details First name Store code
     */
    if (auth != null && auth instanceof AdminAuthenticationToken && auth.getDetails() != null) {
      Map<String, String> details = (Map<String, String>) auth.getDetails();
      request.setAttribute(Constants.User.FIRST_NAME, details.get(Constants.User.FIRST_NAME));
      request.setAttribute(Constants.User.USER_NAME, details.get(Constants.User.FIRST_NAME));
      request.setAttribute(Constants.User.LAST_ACCESS, details.get(Constants.User.LAST_ACCESS));
      request.setAttribute(Constants.User.DEFAULT_LANGUAGE, details.get(Constants.User.DEFAULT_LANGUAGE));
      request.setAttribute(Constants.User.MERCHANT_CODE, details.get(Constants.User.MERCHANT_CODE));
      request.setAttribute("code", details.get(Constants.User.MERCHANT_CODE));
    }

    // put menus to ArrayList
    try {
      List<Menu> menu = cacheHelper.getMenu(locale);
      request.setAttribute(Constants.Cache.MENU, menu);
    } catch (Exception e) {
      logger.error("Cannot retrieve menus" + e.getMessage());
    }

    request.setAttribute("backend", backend);
    request.setAttribute("imagesHost", imagesHost);
    request.setAttribute("version", versionNumber);

    return true;
  }


}
