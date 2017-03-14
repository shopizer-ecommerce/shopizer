package com.salesmanager.admin.controller.order;

import com.salesmanager.admin.constants.Constants;
import com.salesmanager.admin.controller.ControllerConstants;
import com.salesmanager.admin.controller.pages.AbstractAdminController;
import com.salesmanager.core.business.services.order.OrderService;
import com.salesmanager.core.model.common.CriteriaOrderBy;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.order.OrderCriteria;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by umesh on 3/11/17.
 */
@Controller
public class OrderController extends AbstractAdminController{

    private static final Logger LOG= LoggerFactory.getLogger(OrderController.class);

    protected static final String ORDER_LIST="orderList";
    @Inject
    OrderService orderService;

    @PreAuthorize("hasRole('ORDER')")
    @RequestMapping(value = {"/admin/orders.html","/admin/orders"}, method = RequestMethod.GET)
    public String orders(@RequestParam(value = "page", defaultValue = "0") final int page
            , @RequestParam(value = "pageSize",defaultValue = "10") final int pageSize, @RequestParam(value = "sort", required = false) final String sortCode, final Model model
            , HttpServletRequest request, HttpServletResponse response){

        LOG.info("Fetching order list");
        OrderCriteria criteria = new OrderCriteria();
        criteria.setOrderBy(CriteriaOrderBy.DESC);
        criteria.setStartIndex(page);
        criteria.setMaxCount(pageSize);

        MerchantStore store = (MerchantStore)request.getAttribute(Constants.ADMIN_STORE);
        model.addAttribute(ORDER_LIST,orderService.listByStore(store, criteria));
        return ControllerConstants.Tiles.Order.orders;
        }

}
