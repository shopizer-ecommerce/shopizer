package com.salesmanager.admin.controller.order;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.salesmanager.admin.constants.Constants;
import com.salesmanager.admin.controller.ControllerConstants;
import com.salesmanager.admin.controller.pages.AbstractAdminController;
import com.salesmanager.admin.data.order.OrderData;
import com.salesmanager.core.business.services.order.OrderService;
import com.salesmanager.core.business.utils.ajax.AjaxDataTableResponse;
import com.salesmanager.core.model.common.CriteriaOrderBy;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.order.Order;
import com.salesmanager.core.model.order.OrderCriteria;
import com.salesmanager.core.model.order.OrderList;

import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

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
    @RequestMapping(value = {"/admin/orderManagement.html","/admin/orderManagement"}, method = RequestMethod.GET)
    public String orderView(){
        System.out.println("hello");
        return ControllerConstants.Tiles.Order.ordersDashBoard;
    }


    @PreAuthorize("hasRole('ORDER')")
    @RequestMapping(value = {"/admin/orders.html","/admin/orders"}, method = RequestMethod.GET)
    public @ResponseBody
    ResponseEntity<String> orders(final Model model
            , HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {

        int startRow = Integer.parseInt(request.getParameter("start"));
        int endRow = Integer.parseInt(request.getParameter("length"));
        String orderBy = request.getParameter("order[0][dir]");
        LOG.info("Fetching order list {} .... {}", startRow,endRow);
        OrderCriteria criteria = new OrderCriteria();
        //System.out.println(CriteriaOrderBy.valueOf(orderBy));
        System.out.println(CriteriaOrderBy.valueOf(orderBy.toUpperCase()));
        criteria.setOrderBy(CriteriaOrderBy.valueOf(orderBy.toUpperCase()));
        criteria.setStartIndex(startRow);
        criteria.setMaxCount(endRow);
        // MapStruct will be used in future for data population
        MerchantStore store = (MerchantStore)request.getAttribute(Constants.ADMIN_STORE);
        AjaxDataTableResponse ajaxDataTableResponse = new AjaxDataTableResponse();
        OrderList orderList = orderService.listByStore(store, criteria);
        List<OrderData> orders = new ArrayList<OrderData>();
        if(CollectionUtils.isNotEmpty(orderList.getOrders())) {
            ajaxDataTableResponse.setiTotalRecords(orderList.getTotalCount());
            ajaxDataTableResponse.setiTotalDisplayRecords(orderList.getTotalCount());
            for(Order order : orderList.getOrders()) {
                OrderData orderData = new OrderData();
                orderData.setOrderNumber(order.getId());
                orderData.setCustomerId(order.getCustomerId());
                orderData.setOrderTotal(order.getTotal().doubleValue());
                orderData.setStatus(order.getStatus().name());
                orderData.setDate(order.getDatePurchased());

                orders.add(orderData);
            }
        }

        final HttpHeaders httpHeaders= new HttpHeaders();

        ajaxDataTableResponse.setData(orders);
        ObjectMapper mapper = new ObjectMapper();
        String returnString = mapper.writeValueAsString(ajaxDataTableResponse);
        httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);
        return new ResponseEntity<String>(returnString,httpHeaders, HttpStatus.OK);


    }

}
