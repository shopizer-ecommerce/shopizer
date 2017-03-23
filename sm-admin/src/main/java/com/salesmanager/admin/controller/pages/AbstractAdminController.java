package com.salesmanager.admin.controller.pages;

import com.salesmanager.admin.constants.Constants;
import com.salesmanager.admin.data.Menu;
import com.salesmanager.core.business.exception.ServiceException;
import com.salesmanager.core.business.services.reference.country.CountryService;
import com.salesmanager.core.business.services.user.UserService;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.country.Country;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.core.model.user.User;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by umesh on 3/11/17.
 */
public abstract class AbstractAdminController {

    @Inject
    CountryService countryService;

    @Inject
    UserService userService;

    @ModelAttribute("store")
    public MerchantStore getStore(HttpServletRequest request, HttpServletResponse response){
        MerchantStore store = (MerchantStore)request.getAttribute(Constants.ADMIN_STORE);
        return store;
    }

    @ModelAttribute("country")
    public Country getCountry(HttpServletRequest request, HttpServletResponse response) throws ServiceException {

        Language language = (Language)request.getAttribute("LANGUAGE");
        MerchantStore store = (MerchantStore)request.getAttribute(Constants.ADMIN_STORE);
        Map<String,Country> countries = countryService.getCountriesMap(language);
        Country storeCountry = store.getCountry();
        return countries.get(storeCountry.getIsoCode());

    }

    @ModelAttribute("user")
    public User getUser(HttpServletRequest request) throws ServiceException {
        String currentUser = request.getRemoteUser();
        return userService.getByUserName(currentUser);
    }

    protected void setMenu(Model model,  Map<String, Menu> menus, final String activeMenu) throws Exception {

        //display menu
        Map<String,String> activeMenus = new HashMap<String,String>();
        activeMenus.put(activeMenu, activeMenu);
        Menu currentMenu = (Menu)menus.get(activeMenu);
        model.addAttribute("currentMenu",currentMenu);
        model.addAttribute("activeMenus",activeMenus);
        //
    }

    protected void setMenu(Model model,  Map<String, Menu> menus, final String activeMenu,  Map<String,String> activeMenus) throws Exception {

        activeMenus.put(activeMenu, activeMenu);
        Menu currentMenu = (Menu)menus.get(activeMenu);
        model.addAttribute("currentMenu",currentMenu);
        model.addAttribute("activeMenus",activeMenus);
       //
    }



}
