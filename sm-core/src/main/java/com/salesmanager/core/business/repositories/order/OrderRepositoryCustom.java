package com.salesmanager.core.business.repositories.order;

import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.order.OrderCriteria;
import com.salesmanager.core.model.order.OrderList;




public interface OrderRepositoryCustom {

	OrderList listByStore(MerchantStore store, OrderCriteria criteria);
	OrderList getOrders(OrderCriteria criteria);
}
