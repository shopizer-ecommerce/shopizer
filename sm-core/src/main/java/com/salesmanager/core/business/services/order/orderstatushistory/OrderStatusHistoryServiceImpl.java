package com.salesmanager.core.business.services.order.orderstatushistory;

import com.salesmanager.core.business.repositories.order.OrderStatusHistoryRepository;
import com.salesmanager.core.model.order.Order;
import com.salesmanager.core.model.order.orderstatus.OrderStatusHistory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderStatusHistoryServiceImpl implements OrderStatusHistoryService{
    @Autowired
    private OrderStatusHistoryRepository orderStatusHistoryRepository;

    @Override
    public List<OrderStatusHistory> findByOrder(Order order) {
        return orderStatusHistoryRepository.findByOrderId(order.getId());
    }
}
