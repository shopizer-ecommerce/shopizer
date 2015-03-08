package com.salesmanager.core.business.reference.currency.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.mysema.query.jpa.JPQLQuery;
import com.mysema.query.jpa.impl.JPAQuery;
import com.salesmanager.core.business.generic.dao.SalesManagerEntityDaoImpl;
import com.salesmanager.core.business.reference.currency.model.Currency;
import com.salesmanager.core.business.reference.currency.model.QCurrency;

@Repository("currencyDao")
public class CurrencyDaoImpl extends SalesManagerEntityDaoImpl<Long, Currency> 
	implements CurrencyDao {

	
	@Override
	public List<Currency> list() {
		QCurrency qCurrency = QCurrency.currency1;

		
		JPQLQuery query = new JPAQuery (getEntityManager());
		
		query.from(qCurrency)
			.orderBy(qCurrency.code.asc());
		

		
		List<Currency> currencies = query.list(qCurrency);
		return currencies;
	}
	
	
}
