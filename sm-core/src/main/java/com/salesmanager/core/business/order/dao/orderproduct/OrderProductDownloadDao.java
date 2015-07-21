package com.salesmanager.core.business.order.dao.orderproduct;

import java.util.List;

import com.salesmanager.core.business.generic.dao.SalesManagerEntityDao;
import com.salesmanager.core.business.order.model.orderproduct.OrderProductDownload;

public interface OrderProductDownloadDao extends SalesManagerEntityDao<Long, OrderProductDownload> {

	List<OrderProductDownload> getByOrderId(Long orderId);

}
