package com.salesmanager.core.business.customer.dao.attribute;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.mysema.query.jpa.JPQLQuery;
import com.mysema.query.jpa.impl.JPAQuery;
import com.salesmanager.core.business.customer.model.attribute.CustomerOption;
import com.salesmanager.core.business.customer.model.attribute.QCustomerOption;
import com.salesmanager.core.business.customer.model.attribute.QCustomerOptionDescription;
import com.salesmanager.core.business.generic.dao.SalesManagerEntityDaoImpl;
import com.salesmanager.core.business.generic.exception.ServiceException;
import com.salesmanager.core.business.merchant.model.MerchantStore;
import com.salesmanager.core.business.reference.language.model.Language;

@Repository("customerOptionDao")
public class CustomerOptionDaoImpl extends SalesManagerEntityDaoImpl<Long, CustomerOption>
		implements CustomerOptionDao {
	
	@Override
	public List<CustomerOption> listByStore(MerchantStore store, Language language) {
		

		QCustomerOption qCustomerOption = QCustomerOption.customerOption;
		QCustomerOptionDescription qCustomerOptionDescription = QCustomerOptionDescription.customerOptionDescription;
		
		
		
		JPQLQuery query = new JPAQuery (getEntityManager());
		
		query.from(qCustomerOption)
			.leftJoin(qCustomerOption.descriptions, qCustomerOptionDescription).fetch()
			.leftJoin(qCustomerOption.merchantStore).fetch()
			.where(qCustomerOption.merchantStore.id.eq(store.getId())
			.and(qCustomerOptionDescription.language.id.eq(language.getId())))
			.orderBy(qCustomerOption.sortOrder.asc());
		
		return query.distinct().list(qCustomerOption);
				//listDistinct(qCustomerOption);

	}
	
	@Override
	public CustomerOption getById(Long id) {
		
		
		QCustomerOption qCustomerOption = QCustomerOption.customerOption;
		QCustomerOptionDescription qCustomerOptionDescription = QCustomerOptionDescription.customerOptionDescription;
		
		
		
		JPQLQuery query = new JPAQuery (getEntityManager());
		
		query.from(qCustomerOption)
			.leftJoin(qCustomerOption.descriptions, qCustomerOptionDescription).fetch()
			.leftJoin(qCustomerOption.merchantStore).fetch()
			.where(qCustomerOption.id.eq(id));
		
		return query.uniqueResult(qCustomerOption);
		


	}
	
	@Override
	public CustomerOption getByCode(MerchantStore store, String optionCode) {
		
		
		QCustomerOption qCustomerOption = QCustomerOption.customerOption;
		QCustomerOptionDescription qCustomerOptionDescription = QCustomerOptionDescription.customerOptionDescription;
		
		
		
		JPQLQuery query = new JPAQuery (getEntityManager());
		
		query.from(qCustomerOption)
			.leftJoin(qCustomerOption.descriptions, qCustomerOptionDescription).fetch()
			.leftJoin(qCustomerOption.merchantStore).fetch()
			.where(qCustomerOption.merchantStore.id.eq(store.getId())
			.and(qCustomerOption.code.eq(optionCode)));
		
		return query.uniqueResult(qCustomerOption);
		


	}
	

	
	@Override
	public void saveOrUpdate(CustomerOption entity) throws ServiceException {

		if(entity.getId()!=null && entity.getId()>0) {

			super.update(entity);
			
		} else {
			
			super.save(entity);
			
		}
		
	}



}
