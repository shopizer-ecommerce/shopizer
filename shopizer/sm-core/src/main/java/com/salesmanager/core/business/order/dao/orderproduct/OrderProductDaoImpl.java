package com.salesmanager.core.business.order.dao.orderproduct;

import org.springframework.stereotype.Repository;

import com.salesmanager.core.business.generic.dao.SalesManagerEntityDaoImpl;
import com.salesmanager.core.business.order.model.orderproduct.OrderProduct;

@Repository("orderProductDao")
public class OrderProductDaoImpl extends SalesManagerEntityDaoImpl<Long, OrderProduct> implements OrderProductDao{

	
	public OrderProductDaoImpl() {
		super();
	}
}
