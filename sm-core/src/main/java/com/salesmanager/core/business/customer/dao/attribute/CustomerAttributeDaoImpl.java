package com.salesmanager.core.business.customer.dao.attribute;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.mysema.query.jpa.JPQLQuery;
import com.mysema.query.jpa.impl.JPAQuery;
import com.salesmanager.core.business.customer.model.QCustomer;
import com.salesmanager.core.business.customer.model.attribute.CustomerAttribute;
import com.salesmanager.core.business.customer.model.attribute.QCustomerAttribute;
import com.salesmanager.core.business.customer.model.attribute.QCustomerOption;
import com.salesmanager.core.business.customer.model.attribute.QCustomerOptionValue;
import com.salesmanager.core.business.generic.dao.SalesManagerEntityDaoImpl;
import com.salesmanager.core.business.merchant.model.MerchantStore;

@Repository("customerAttributeDao")
public class CustomerAttributeDaoImpl extends SalesManagerEntityDaoImpl<Long, CustomerAttribute> 
	implements CustomerAttributeDao {
	
	
	@Override
	public CustomerAttribute getById(Long id) {
		QCustomerAttribute qCustomerAttribute = QCustomerAttribute.customerAttribute;
		QCustomerOption qCustomerOption = QCustomerOption.customerOption;
		QCustomerOptionValue qCustomerOptionValue = QCustomerOptionValue.customerOptionValue;
		
		
		JPQLQuery query = new JPAQuery (getEntityManager());
		
		query.from(qCustomerAttribute)
			.join(qCustomerAttribute.customer).fetch()
			.leftJoin(qCustomerAttribute.customerOption, qCustomerOption).fetch()
			.leftJoin(qCustomerAttribute.customerOptionValue, qCustomerOptionValue).fetch()
			.leftJoin(qCustomerOption.descriptions).fetch()
			.leftJoin(qCustomerOptionValue.descriptions).fetch()
			.where(qCustomerAttribute.id.eq(id));
		
		return query.uniqueResult(qCustomerAttribute);

	}
	
	@Override
	public CustomerAttribute getByOptionId(MerchantStore store, Long customerId, Long id) {
		QCustomerAttribute qCustomerAttribute = QCustomerAttribute.customerAttribute;
		QCustomerOption qCustomerOption = QCustomerOption.customerOption;
		QCustomer qCustomer = QCustomer.customer;
		QCustomerOptionValue qCustomerOptionValue = QCustomerOptionValue.customerOptionValue;
		
		
		JPQLQuery query = new JPAQuery (getEntityManager());
		
		query.from(qCustomerAttribute)
		.join(qCustomerAttribute.customer,qCustomer).fetch()
		.leftJoin(qCustomerAttribute.customerOption, qCustomerOption).fetch()
		.leftJoin(qCustomerAttribute.customerOptionValue, qCustomerOptionValue).fetch()
		.leftJoin(qCustomerOption.descriptions).fetch()
		.leftJoin(qCustomerOptionValue.descriptions).fetch()
		.where(qCustomerOption.merchantStore.id.eq(store.getId())
				.and(qCustomerOption.id.eq(id))
				.and(qCustomer.id.eq(customerId)));

		
		return query.uniqueResult(qCustomerAttribute);
	}
	
	@Override
	public List<CustomerAttribute> getByCustomerId(MerchantStore store, Long customerId) {
		QCustomerAttribute qCustomerAttribute = QCustomerAttribute.customerAttribute;
		QCustomerOption qCustomerOption = QCustomerOption.customerOption;
		QCustomer qCustomer = QCustomer.customer;
		QCustomerOptionValue qCustomerOptionValue = QCustomerOptionValue.customerOptionValue;
		
		
		JPQLQuery query = new JPAQuery (getEntityManager());
		
		
		query.from(qCustomerAttribute)
		.join(qCustomerAttribute.customer,qCustomer).fetch()
		.leftJoin(qCustomerAttribute.customerOption, qCustomerOption).fetch()
		.leftJoin(qCustomerAttribute.customerOptionValue, qCustomerOptionValue).fetch()
		.leftJoin(qCustomerOption.descriptions).fetch()
		.leftJoin(qCustomerOptionValue.descriptions).fetch()
		.where(qCustomerOption.merchantStore.id.eq(store.getId())
				.and(qCustomer.id.eq(customerId)));

		System.out.println("done");
		
		return query.distinct().list(qCustomerAttribute);
				//listDistinct(qCustomerAttribute);
	}
	
	@Override
	public List<CustomerAttribute> getByOptionValueId(MerchantStore store, Long id) {
		QCustomerAttribute qCustomerAttribute = QCustomerAttribute.customerAttribute;
		QCustomerOption qCustomerOption = QCustomerOption.customerOption;
		QCustomerOptionValue qCustomerOptionValue = QCustomerOptionValue.customerOptionValue;
		
		
		JPQLQuery query = new JPAQuery (getEntityManager());
		
		query.from(qCustomerAttribute)
		.join(qCustomerAttribute.customer).fetch()
		.leftJoin(qCustomerAttribute.customerOption, qCustomerOption).fetch()
		.leftJoin(qCustomerAttribute.customerOptionValue, qCustomerOptionValue).fetch()
		.leftJoin(qCustomerOption.descriptions).fetch()
		.leftJoin(qCustomerOptionValue.descriptions).fetch()
		.where(qCustomerOptionValue.merchantStore.id.eq(store.getId())
				.and(qCustomerOptionValue.id.eq(id)));

		
		return query.list(qCustomerAttribute);
	}
	
	@Override
	public List<CustomerAttribute> getByOptionId(MerchantStore store, Long id) {
		QCustomerAttribute qCustomerAttribute = QCustomerAttribute.customerAttribute;
		QCustomerOption qCustomerOption = QCustomerOption.customerOption;
		QCustomerOptionValue qCustomerOptionValue = QCustomerOptionValue.customerOptionValue;
		
		
		JPQLQuery query = new JPAQuery (getEntityManager());
		
		query.from(qCustomerAttribute)
		.join(qCustomerAttribute.customer).fetch()
		.leftJoin(qCustomerAttribute.customerOption, qCustomerOption).fetch()
		.leftJoin(qCustomerAttribute.customerOptionValue, qCustomerOptionValue).fetch()
		.leftJoin(qCustomerOption.descriptions).fetch()
		.leftJoin(qCustomerOptionValue.descriptions).fetch()
		.where(qCustomerOptionValue.merchantStore.id.eq(store.getId())
				.and(qCustomerOption.id.eq(id)));

		
		return query.list(qCustomerAttribute);
	}


	

	
}
