package com.salesmanager.admin.controller.order;

import com.salesmanager.admin.constants.Constants;
import com.salesmanager.admin.controller.pages.AbstractAdminController;
import com.salesmanager.core.business.services.order.OrderService;
import com.salesmanager.core.model.common.CriteriaOrderBy;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.order.OrderCriteria;
import com.salesmanager.core.model.order.OrderList;

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

    @Inject
    OrderService orderService;

    @RequestMapping(value = "/admin/orders", method = RequestMethod.GET)
    public OrderList orders(@RequestParam(value = "page", defaultValue = "0") final int page
            , @RequestParam(value = "pageSize",defaultValue = "10") final int pageSize, @RequestParam(value = "sort", required = false) final String sortCode, final Model model
            , HttpServletRequest request, HttpServletResponse response){

        OrderCriteria criteria = new OrderCriteria();
        criteria.setOrderBy(CriteriaOrderBy.DESC);
        criteria.setStartIndex(page);
        criteria.setMaxCount(pageSize);

        MerchantStore store = (MerchantStore)request.getAttribute(Constants.ADMIN_STORE);

        return orderService.listByStore(store, criteria);

    }

}
