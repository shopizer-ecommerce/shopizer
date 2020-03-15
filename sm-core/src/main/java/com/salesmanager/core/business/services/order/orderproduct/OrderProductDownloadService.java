package com.salesmanager.core.business.services.order.orderproduct;

import java.util.List;

import com.salesmanager.core.business.services.common.generic.SalesManagerEntityService;
import com.salesmanager.core.model.order.orderproduct.OrderProductDownload;


public interface OrderProductDownloadService extends SalesManagerEntityService<Long, OrderProductDownload> {

	/**
	 * List {@link OrderProductDownload} by order id
	 * @param orderId
	 * @return
	 */
	List<OrderProductDownload> getByOrderId(Long orderId);

}
