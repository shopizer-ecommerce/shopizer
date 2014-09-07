package com.salesmanager.core.business.payments.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.mysema.query.jpa.JPQLQuery;
import com.mysema.query.jpa.impl.JPAQuery;
import com.salesmanager.core.business.generic.dao.SalesManagerEntityDaoImpl;
import com.salesmanager.core.business.order.model.Order;
import com.salesmanager.core.business.payments.model.QTransaction;
import com.salesmanager.core.business.payments.model.Transaction;

@Repository("transactionDao")
public class TransactionDaoImpl extends SalesManagerEntityDaoImpl<Long, Transaction>
		implements TransactionDao {
	
	@Override
	public List<Transaction> listByOrder(Order order){
		QTransaction qTransaction = QTransaction.transaction;

		
		JPQLQuery query = new JPAQuery (getEntityManager());
		
		query.from(qTransaction)
			.join(qTransaction.order).fetch()
			.where(qTransaction.order.id.eq(order.getId()));
		
		return query.list(qTransaction);
	}


}
