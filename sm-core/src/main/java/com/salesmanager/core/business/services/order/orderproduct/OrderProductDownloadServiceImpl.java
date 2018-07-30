package com.salesmanager.core.business.services.order.orderproduct;

import com.salesmanager.core.business.repositories.order.orderproduct.OrderProductDownloadRepository;
import com.salesmanager.core.business.services.common.generic.SalesManagerEntityServiceImpl;
import com.salesmanager.core.model.order.orderproduct.OrderProductDownload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;


@Service("orderProductDownloadService")
public class OrderProductDownloadServiceImpl extends SalesManagerEntityServiceImpl<Long, OrderProductDownload> implements OrderProductDownloadService {

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
