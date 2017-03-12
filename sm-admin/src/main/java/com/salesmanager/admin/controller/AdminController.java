package com.salesmanager.admin.controller;

import com.salesmanager.admin.constants.Constants;
import com.salesmanager.admin.controller.pages.AbstractAdminController;
import com.salesmanager.admin.system.SmAccurator;
import com.salesmanager.core.business.services.order.OrderService;
import com.salesmanager.core.business.services.reference.country.CountryService;
import com.salesmanager.core.business.services.user.UserService;
import com.salesmanager.core.model.common.CriteriaOrderBy;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.order.OrderCriteria;
import com.salesmanager.core.model.order.OrderList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Controller
public class AdminController extends AbstractAdminController {
	
	@Inject
	CountryService countryService;
	
	@Inject
	UserService userService;

	@Inject
	SmAccurator smAccurator;

    @Inject
    OrderService orderService;
	
	//@PreAuthorize("hasRole('AUTH')")
	@RequestMapping(value={"/admin/home.html","/admin/","/admin"}, method=RequestMethod.GET)
	public String displayDashboard(Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {

        model.addAttribute("health", smAccurator.getHealthStatus());
        model.addAttribute("metrics", smAccurator.getMetrics());
        model.addAttribute("systemInfo", smAccurator.getMetrics());

        OrderCriteria criteria = new OrderCriteria();
        criteria.setOrderBy(CriteriaOrderBy.DESC);
        criteria.setStartIndex(0);
        criteria.setMaxCount(10);
        MerchantStore store = (MerchantStore)request.getAttribute(Constants.ADMIN_STORE);
        OrderList orderList = orderService.listByStore(store, criteria);
        model.addAttribute("orderList",orderList);


        Map<String,String> activeMenus = new HashMap<String,String>();
        activeMenus.put("home", "home");
        model.addAttribute("activeMenus",activeMenus);

	    return ControllerConstants.Tiles.adminDashboard;
	}
	



}
