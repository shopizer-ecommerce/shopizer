package com.salesmanager.core.business.services.order.orderproduct;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.salesmanager.core.business.repositories.order.orderproduct.OrderProductDownloadRepository;
import com.salesmanager.core.business.services.common.generic.SalesManagerEntityServiceImpl;
import com.salesmanager.core.model.order.orderproduct.OrderProductDownload;




@Service("orderProductDownloadService")
public class OrderProductDownloadServiceImpl  extends SalesManagerEntityServiceImpl<Long, OrderProductDownload> implements OrderProductDownloadService {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderProductDownloadServiceImpl.class);


    private final OrderProductDownloadRepository orderProductDownloadRepository;

    @Inject
    public OrderProductDownloadServiceImpl(OrderProductDownloadRepository orderProductDownloadRepository) {
        super(orderProductDownloadRepository);
        this.orderProductDownloadRepository = orderProductDownloadRepository;
    }
    
    @Override
    public List<OrderProductDownload> getByOrderId(Long orderId) {
    	return orderProductDownloadRepository.findByOrderId(orderId);
    }


}
