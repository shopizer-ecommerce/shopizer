package com.salesmanager.admin.controller.configurations;

import com.salesmanager.admin.constants.Constants;
import com.salesmanager.admin.controller.ControllerConstants;
import com.salesmanager.admin.controller.pages.AbstractAdminController;
import com.salesmanager.admin.data.Menu;
import com.salesmanager.core.business.utils.CacheUtils;
import com.salesmanager.core.model.merchant.MerchantStore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by umesh on 3/21/17.
 */
@Controller
public class CacheController extends AbstractAdminController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CacheController.class);

    @Inject
    private CacheUtils cache;

    @PreAuthorize("hasRole('AUTH')")
    @RequestMapping(value = "/admin/cache/cacheManagement.html", method = RequestMethod.GET)
    public String displayAccounts(Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {

        setMenu(model, (Map<String, Menu>) request.getAttribute("MENUMAP"), "cache");
        MerchantStore store = (MerchantStore) request.getAttribute(Constants.ADMIN_STORE);
        List<String> cacheKeysList = cache.getCacheKeys(store);
        model.addAttribute("keys", cacheKeysList);

        return ControllerConstants.Tiles.Configuration.cache;
    }

    @PreAuthorize("hasRole('AUTH')")
    @RequestMapping(value = "/admin/cache/clear.html", method = RequestMethod.POST)
    public String clearCache(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
        String cacheKey = request.getParameter("cacheKey");
        setMenu(model, (Map<String, Menu>) request.getAttribute("MENUMAP"), "cache");
        try {

            MerchantStore store = (MerchantStore) request.getAttribute(Constants.ADMIN_STORE);

            StringBuilder key = new StringBuilder();
            key.append(store.getId()).append("_").append(cacheKey);
            model.addAttribute("status", HttpStatus.OK);
            if (cacheKey != null) {
                cache.removeFromCache(key.toString());
            } else {
                cache.removeAllFromCache(store);
            }

        } catch (Exception e) {
            LOGGER.error("Error while updateing groups", e);
            model.addAttribute("status", HttpStatus.BAD_REQUEST);
        }

        return ControllerConstants.Tiles.Configuration.cache;
    }
}
