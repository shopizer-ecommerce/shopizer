package com.salesmanager.core.business.order.dao;

import org.springframework.stereotype.Repository;

import com.salesmanager.core.business.generic.dao.SalesManagerEntityDaoImpl;
import com.salesmanager.core.business.order.model.OrderTotal;

@Repository("orderTotalDao")
public class OrderTotalDaoImpl extends SalesManagerEntityDaoImpl<Long, OrderTotal> implements OrderTotalDao {

	public OrderTotalDaoImpl() {
		super();
	}
	
}
