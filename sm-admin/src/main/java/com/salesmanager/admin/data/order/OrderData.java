package com.salesmanager.admin.data.order;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by umesh on 3/15/17.
 */
public class OrderData implements Serializable {

    private long orderNumber;
    private long customerId;
    private double orderTotal;
    private Date date;
    private String status;

    public long getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(long orderNumber) {
        this.orderNumber = orderNumber;
    }

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    public double getOrderTotal() {
        return orderTotal;
    }

    public void setOrderTotal(double orderTotal) {
        this.orderTotal = orderTotal;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}