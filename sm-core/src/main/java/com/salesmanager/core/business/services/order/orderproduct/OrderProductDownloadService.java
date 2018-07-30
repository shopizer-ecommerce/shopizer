package com.salesmanager.core.business.services.order.orderproduct;

import com.salesmanager.core.business.services.common.generic.SalesManagerEntityService;
import com.salesmanager.core.model.order.orderproduct.OrderProductDownload;

import java.util.List;


public interface OrderProductDownloadService extends SalesManagerEntityService<Long, OrderProductDownload> {

    List<OrderProductDownload> getByOrderId(Long orderId);

}
