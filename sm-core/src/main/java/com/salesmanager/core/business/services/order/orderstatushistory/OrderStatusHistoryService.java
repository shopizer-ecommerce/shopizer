package com.salesmanager.core.business.services.order.orderstatushistory;

import com.salesmanager.core.model.order.Order;
import com.salesmanager.core.model.order.orderstatus.OrderStatusHistory;

import java.util.List;

public interface OrderStatusHistoryService {
    List<OrderStatusHistory> findByOrder(Order order);
}
