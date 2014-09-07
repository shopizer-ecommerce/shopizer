package com.salesmanager.core.business.customer.dao.attribute;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.mysema.query.jpa.JPQLQuery;
import com.mysema.query.jpa.impl.JPAQuery;
import com.salesmanager.core.business.customer.model.attribute.CustomerOptionValue;
import com.salesmanager.core.business.customer.model.attribute.QCustomerOptionValue;
import com.salesmanager.core.business.customer.model.attribute.QCustomerOptionValueDescription;
import com.salesmanager.core.business.generic.dao.SalesManagerEntityDaoImpl;
import com.salesmanager.core.business.merchant.model.MerchantStore;
import com.salesmanager.core.business.reference.language.model.Language;

@Repository("customerOptionValueDao")
public class CustomerOptionValueDaoImpl extends SalesManagerEntityDaoImpl<Long, CustomerOptionValue>
		implements CustomerOptionValueDao {
	
	
	@Override
	public List<CustomerOptionValue> listByStore(MerchantStore store, Language language) {
		
		
		QCustomerOptionValue qCustomerOptionValue = QCustomerOptionValue.customerOptionValue;
		QCustomerOptionValueDescription qCustomerOptionValueDescription = QCustomerOptionValueDescription.customerOptionValueDescription;
		

		JPQLQuery query = new JPAQuery (getEntityManager());
		
		query.from(qCustomerOptionValue)
			.leftJoin(qCustomerOptionValue.descriptions, qCustomerOptionValueDescription).fetch()
			.leftJoin(qCustomerOptionValue.merchantStore).fetch()
			.where(qCustomerOptionValue.merchantStore.id.eq(store.getId())
			.and(qCustomerOptionValueDescription.language.id.eq(language.getId())))
			.orderBy(qCustomerOptionValue.sortOrder.asc());
		
		return query.distinct().list(qCustomerOptionValue);
				//listDistinct(qCustomerOptionValue);

		
	}
	

	

	
	@Override
	public CustomerOptionValue getById(Long id) {
		
		
		
		QCustomerOptionValue qCustomerOptionValue = QCustomerOptionValue.customerOptionValue;
		QCustomerOptionValueDescription qCustomerOptionValueDescription = QCustomerOptionValueDescription.customerOptionValueDescription;
		
		
		
		JPQLQuery query = new JPAQuery (getEntityManager());
		
		query.from(qCustomerOptionValue)
			.leftJoin(qCustomerOptionValue.descriptions, qCustomerOptionValueDescription).fetch()
			.leftJoin(qCustomerOptionValue.merchantStore).fetch()
			.where(qCustomerOptionValue.id.eq(id));
		
		return query.uniqueResult(qCustomerOptionValue);

	}
	
	@Override
	public CustomerOptionValue getByCode(MerchantStore store, String optionValueCode) {
		
		
		
		QCustomerOptionValue qCustomerOptionValue = QCustomerOptionValue.customerOptionValue;
		QCustomerOptionValueDescription qCustomerOptionValueDescription = QCustomerOptionValueDescription.customerOptionValueDescription;
		
		
		
		JPQLQuery query = new JPAQuery (getEntityManager());
		
		query.from(qCustomerOptionValue)
			.leftJoin(qCustomerOptionValue.descriptions, qCustomerOptionValueDescription).fetch()
			.leftJoin(qCustomerOptionValue.merchantStore).fetch()
			.where(qCustomerOptionValue.merchantStore.id.eq(store.getId())
			.and(qCustomerOptionValue.code.eq(optionValueCode)));
		
		return query.uniqueResult(qCustomerOptionValue);

	}
	


}
