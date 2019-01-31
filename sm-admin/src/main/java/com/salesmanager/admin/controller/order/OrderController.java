package com.salesmanager.admin.controller.order;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping(value = "/admin")
public class OrderController {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderController.class);

    @GetMapping(value = "/orders/list")
    @Secured({"ROLE_STORE"})
    public String getOrders(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return "order/orderDashboard";
    }
}
