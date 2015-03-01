package com.salesmanager.core.business.payments.dao;

import java.util.List;

import com.salesmanager.core.business.generic.dao.SalesManagerEntityDao;
import com.salesmanager.core.business.order.model.Order;
import com.salesmanager.core.business.payments.model.Transaction;

public interface TransactionDao extends SalesManagerEntityDao<Long, Transaction> {

	List<Transaction> listByOrder(Order order);


	
}
