package com.salesmanager.core.business.order.dao.orderaccount;

import org.springframework.stereotype.Repository;

import com.salesmanager.core.business.generic.dao.SalesManagerEntityDaoImpl;
import com.salesmanager.core.business.order.model.orderaccount.OrderAccount;

@Repository("orderAccountDao")
public class OrderAccountDaoImpl extends SalesManagerEntityDaoImpl<Long, OrderAccount> implements OrderAccountDao {

	public OrderAccountDaoImpl() {
		super();
	}
	
}