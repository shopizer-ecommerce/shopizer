package com.salesmanager.admin.data.order;

import com.salesmanager.core.model.common.Billing;
import com.salesmanager.core.model.common.Delivery;
import com.salesmanager.core.model.order.Order;
import com.salesmanager.core.model.order.orderstatus.OrderStatus;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.persistence.Embedded;

/**
 * Created by umesh on 3/28/17.
 */
public class OrderData implements Serializable{

    private Long Id;
    private String orderHistoryComment = "";

    List<OrderStatus> orderStatusList = Arrays.asList(OrderStatus.values());
    private String datePurchased = "";
    private Order order;

    @Embedded
    private com.salesmanager.core.model.common.Delivery delivery = null;  // this will be a DTO inplace of  entity

    @Embedded
    private com.salesmanager.core.model.common.Billing billing = null; // this will be a DTO inplace of  entity




    public String getDatePurchased() {
        return datePurchased;
    }

    public void setDatePurchased(String datePurchased) {
        this.datePurchased = datePurchased;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getOrderHistoryComment() {
        return orderHistoryComment;
    }

    public void setOrderHistoryComment(String orderHistoryComment) {
        this.orderHistoryComment = orderHistoryComment;
    }

    public List<OrderStatus> getOrderStatusList() {
        return orderStatusList;
    }

    public void setOrderStatusList(List<OrderStatus> orderStatusList) {
        this.orderStatusList = orderStatusList;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Delivery getDelivery() {
        return delivery;
    }

    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
    }

    public Billing getBilling() {
        return billing;
    }

    public void setBilling(Billing billing) {
        this.billing = billing;
    }

}
