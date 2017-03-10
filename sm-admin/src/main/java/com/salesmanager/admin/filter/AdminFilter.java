package com.salesmanager.admin.filter;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.salesmanager.admin.constants.Constants;
import com.salesmanager.admin.data.Menu;
import com.salesmanager.core.business.services.merchant.MerchantStoreService;
import com.salesmanager.core.business.services.reference.language.LanguageService;
import com.salesmanager.core.business.services.user.UserService;
import com.salesmanager.core.business.utils.CacheUtils;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.core.model.user.User;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.support.RequestContextUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by umesh on 3/6/17.
 */

@Component
public class AdminFilter extends HandlerInterceptorAdapter {

    private static final Logger LOG = LoggerFactory.getLogger(AdminFilter.class);

    @Inject
    private MerchantStoreService merchantService;

    @Inject
    private UserService userService;

    @Inject
    private LanguageService languageService;

    @Inject
    private CacheUtils cache;





    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {


        LOG.info("Init admin pannel data");
       // fill menu options for admin dashboard

        Map<String, Menu> menus = (Map<String, Menu>) cache.getFromCache(Constants.MENUMAP);



        User user = (User)request.getSession().getAttribute(Constants.ADMIN_USER);


        String storeCode = MerchantStore.DEFAULT_STORE;
        MerchantStore store = (MerchantStore)request.getSession().getAttribute(Constants.ADMIN_STORE);


        String userName = request.getRemoteUser();

        if(userName==null) {//** IMPORTANT FOR SPRING SECURITY **//
            //response.sendRedirect(new StringBuilder().append(request.getContextPath()).append("/").append("/admin").toString());
        } else {

            if(user==null) {
                user = userService.getByUserName(userName);
                request.getSession().setAttribute(Constants.ADMIN_USER, user);
                if(user!=null) {
                    storeCode = user.getMerchantStore().getCode();
                } else {
                    LOG.warn("User name not found " + userName);
                }
                store=null;
            }

            if(user==null) {
                response.sendRedirect(request.getContextPath() + "/admin/unauthorized.html");
                return true;
            }

            if(!user.getAdminName().equals(userName)) {
                user = userService.getByUserName(userName);
                if(user!=null) {
                    storeCode = user.getMerchantStore().getCode();
                } else {
                    LOG.warn("User name not found " + userName);
                }
                store=null;
            }

        }

        if(store==null) {
            store = merchantService.getByCode(storeCode);
            request.getSession().setAttribute(Constants.ADMIN_STORE, store);
        }
        request.setAttribute(Constants.ADMIN_STORE, store);


        Language language = getRequestLanguage(request, response);

        //Language language = (Language) request.getSession().getAttribute("LANGUAGE");

        if(language==null) {

            //TODO get the Locale from Spring API, is it simply request.getLocale() ???
            //if so then based on the Locale language locale.getLanguage() get the appropriate Language
            //object as represented below
            if(user!=null) {
                language = user.getDefaultLanguage();
                if(language==null) {
                    language = store.getDefaultLanguage();
                }
            } else {
                language = store.getDefaultLanguage();
            }

            request.getSession().setAttribute("LANGUAGE", language);

        }


        request.setAttribute(Constants.LANGUAGE, language);



        if (menus == null) {

            LOG.info("Menu in cache is empty.., building admin menu");
            ObjectMapper mapper = new ObjectMapper();
            InputStream menuData = null;
            try {
                menuData = this.getClass().getClassLoader().getResourceAsStream("admin/menu.json");
                Map<String, Object> data = mapper.readValue(menuData, Map.class);

                menus = new LinkedHashMap<String,Menu>();
                List objects = (List)data.get("menus");

                for(Object object : objects) {
                    Menu m = getMenu(object);
                    menus.put(m.getCode(),m);
                }

                cache.putInCache(menus,Constants.MENUMAP);

            } catch (JsonParseException e) {
                LOG.error("Error while creating menu {} ", e);
            } catch (JsonMappingException e) {
                LOG.error("Error while creating menu {} ", e);
            } catch (IOException e) {
                LOG.error("Error while creating menu {} ", e);
            } finally {
                if (menuData != null) {
                    try {
                        menuData.close();
                    } catch (Exception ignore) {
                        LOG.error("unable to parse menu data {}", ignore);
                    }
                }
            }



        }

        List<Menu> list = new ArrayList<Menu>(menus.values());

        request.setAttribute("MENULIST", list);



        request.setAttribute(Constants.MENUMAP, menus);
        return true;
    }


    private Menu getMenu(Object object) {

        Map o = (Map)object;
        Map menu = (Map)o.get("menu");

        Menu adminMenu = new Menu();
        adminMenu.setCode((String)menu.get("code"));


        adminMenu.setUrl((String)menu.get("url"));
        adminMenu.setIcon((String)menu.get("icon"));
        adminMenu.setRole((String)menu.get("role"));

        List menus = (List)menu.get("menus");
        if(menus!=null) {
            for(Object oo : menus) {

                Menu mm = getMenu(oo);
                adminMenu.getMenus().add(mm);
            }

        }

        return adminMenu;

    }


    private Language getRequestLanguage(HttpServletRequest request, HttpServletResponse response) {

        Locale locale = null;

        Language language = (Language) request.getSession().getAttribute(Constants.LANGUAGE);


        if(language==null) {
            try {

                locale = LocaleContextHolder.getLocale();//should be browser locale


                MerchantStore store = (MerchantStore)request.getSession().getAttribute(Constants.MERCHANT_STORE);
                if(store!=null) {
                    language = store.getDefaultLanguage();
                    if(language!=null) {
                        locale = languageService.toLocale(language);
                        if(locale!=null) {
                            LocaleContextHolder.setLocale(locale);
                        }
                        request.getSession().setAttribute(Constants.LANGUAGE, language);
                    }

                    if(language==null) {
                        language = languageService.toLanguage(locale);
                        request.getSession().setAttribute(Constants.LANGUAGE, language);
                    }

                }

            } catch(Exception e) {
                if(language==null) {
                    try {
                        language = languageService.getByCode(Constants.DEFAULT_LANGUAGE);
                    } catch(Exception ignore) {}
                }
            }
        } else {


            Locale localeFromContext = LocaleContextHolder.getLocale();//should be browser locale
            if(!language.getCode().equals(localeFromContext.getLanguage())) {
                //get locale context
                language = languageService.toLanguage(localeFromContext);
            }

        }

        if(language != null) {
            locale = languageService.toLocale(language);
        } else {
            language = languageService.toLanguage(locale);
        }

        LocaleResolver localeResolver = RequestContextUtils.getLocaleResolver(request);
        if(localeResolver!=null) {
            localeResolver.setLocale(request, response, locale);
        }
        response.setLocale(locale);

        return language;
    }

}
