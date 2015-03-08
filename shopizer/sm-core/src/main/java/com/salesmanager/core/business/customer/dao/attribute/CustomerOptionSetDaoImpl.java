package com.salesmanager.core.business.customer.dao.attribute;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.mysema.query.jpa.JPQLQuery;
import com.mysema.query.jpa.impl.JPAQuery;
import com.salesmanager.core.business.customer.model.attribute.CustomerOptionSet;
import com.salesmanager.core.business.customer.model.attribute.QCustomerOption;
import com.salesmanager.core.business.customer.model.attribute.QCustomerOptionDescription;
import com.salesmanager.core.business.customer.model.attribute.QCustomerOptionSet;
import com.salesmanager.core.business.customer.model.attribute.QCustomerOptionValue;
import com.salesmanager.core.business.customer.model.attribute.QCustomerOptionValueDescription;
import com.salesmanager.core.business.generic.dao.SalesManagerEntityDaoImpl;
import com.salesmanager.core.business.merchant.model.MerchantStore;
import com.salesmanager.core.business.reference.language.model.Language;

@Repository("customerOptionSetDao")
public class CustomerOptionSetDaoImpl extends SalesManagerEntityDaoImpl<Long, CustomerOptionSet> implements CustomerOptionSetDao {
	

	@Override
	public CustomerOptionSet getById(Long customerOptionSetId) {
		QCustomerOptionSet qCustomerOptionSet= QCustomerOptionSet.customerOptionSet;
		QCustomerOption qCustomerOption = QCustomerOption.customerOption;
		QCustomerOptionValue qCustomerOptionValue = QCustomerOptionValue.customerOptionValue;
		
		
		JPQLQuery query = new JPAQuery (getEntityManager());
		
		query.from(qCustomerOptionSet)
			.join(qCustomerOptionSet.customerOption,qCustomerOption).fetch()
			.join(qCustomerOptionSet.customerOptionValue,qCustomerOptionValue).fetch()
			.join(qCustomerOption.merchantStore).fetch()
			.join(qCustomerOptionValue.merchantStore).fetch()
			.leftJoin(qCustomerOption.descriptions).fetch()
			.leftJoin(qCustomerOptionValue.descriptions).fetch()
			.where(qCustomerOptionSet.id.eq(customerOptionSetId)
			);
		
		return query.uniqueResult(qCustomerOptionSet);


	}
	
	@Override
	public List<CustomerOptionSet> getByOptionId(MerchantStore store, Long id) {
		QCustomerOptionSet qCustomerOptionSet= QCustomerOptionSet.customerOptionSet;
		QCustomerOption qCustomerOption = QCustomerOption.customerOption;
		QCustomerOptionValue qCustomerOptionValue = QCustomerOptionValue.customerOptionValue;
		
		
		JPQLQuery query = new JPAQuery (getEntityManager());
		
		query.from(qCustomerOptionSet)
			.join(qCustomerOptionSet.customerOption,qCustomerOption).fetch()
			.join(qCustomerOption.merchantStore).fetch()
			.join(qCustomerOptionSet.customerOptionValue,qCustomerOptionValue).fetch()
			.join(qCustomerOptionValue.merchantStore).fetch()
			.leftJoin(qCustomerOption.descriptions).fetch()
			.leftJoin(qCustomerOptionValue.descriptions).fetch()
			.where(qCustomerOption.id.eq(id)
					.and(qCustomerOption.merchantStore.id.eq(store.getId())));

		
		return query.list(qCustomerOptionSet);
	}
	
	@Override
	public List<CustomerOptionSet> getByOptionValueId(MerchantStore store, Long id) {
		QCustomerOptionSet qCustomerOptionSet= QCustomerOptionSet.customerOptionSet;
		QCustomerOption qCustomerOption = QCustomerOption.customerOption;
		QCustomerOptionValue qCustomerOptionValue = QCustomerOptionValue.customerOptionValue;
		
		
		JPQLQuery query = new JPAQuery (getEntityManager());
		
		query.from(qCustomerOptionSet)
			.join(qCustomerOptionSet.customerOption,qCustomerOption).fetch()
			.join(qCustomerOption.merchantStore).fetch()
			.join(qCustomerOptionSet.customerOptionValue,qCustomerOptionValue).fetch()
			.join(qCustomerOptionValue.merchantStore).fetch()
			.leftJoin(qCustomerOption.descriptions).fetch()
			.leftJoin(qCustomerOptionValue.descriptions).fetch()
			.where(qCustomerOptionValue.id.eq(id)
					.and(qCustomerOption.merchantStore.id.eq(store.getId())));

		
		return query.list(qCustomerOptionSet);
	}
	
	@Override
	public List<CustomerOptionSet> listByStore(MerchantStore store, Language language) {

		
		QCustomerOptionSet qCustomerOptionSet= QCustomerOptionSet.customerOptionSet;
		QCustomerOption qCustomerOption = QCustomerOption.customerOption;
		QCustomerOptionDescription qCustomerOptionDescription = QCustomerOptionDescription.customerOptionDescription;
		QCustomerOptionValue qCustomerOptionValue = QCustomerOptionValue.customerOptionValue;
		QCustomerOptionValueDescription qCustomerOptionValueDescription = QCustomerOptionValueDescription.customerOptionValueDescription;
		
		
		JPQLQuery query = new JPAQuery (getEntityManager());
		
		query.from(qCustomerOptionSet)
			.join(qCustomerOptionSet.customerOption,qCustomerOption).fetch()
			.join(qCustomerOptionSet.customerOptionValue,qCustomerOptionValue).fetch()
			.leftJoin(qCustomerOption.descriptions,qCustomerOptionDescription).fetch()
			.leftJoin(qCustomerOptionValue.descriptions,qCustomerOptionValueDescription).fetch()
			.where(qCustomerOption.merchantStore.id.eq(store.getId())
			.and(qCustomerOptionDescription.language.id.eq(language.getId()))
			.and(qCustomerOptionValueDescription.language.id.eq(language.getId()))
			).orderBy(qCustomerOptionSet.sortOrder.asc());

		
		return query.list(qCustomerOptionSet);
		
		
		
/*		StringBuilder qs = new StringBuilder();
		qs.append("select distinct cos from CustomerOptionSet as cos ");
		qs.append("join fetch cos.customerOption po ");
		qs.append("join fetch cos.customerOptionValue ov ");
		qs.append("join fetch po.merchantStore pm ");
		qs.append("left join fetch po.descriptions pop ");
		qs.append("left join fetch ov.descriptions ovd ");
		qs.append("where pm.id =:mid ");
		qs.append("and pop.language.id =:lid ");
		qs.append("and ovd.language.id =:lid ");
		qs.append("order by cos.sortOrder asc");
		
		String hql = qs.toString();
		Query q = super.getEntityManager().createQuery(hql);


    	q.setParameter("mid", store.getId());
    	q.setParameter("lid", language.getId());

		
		@SuppressWarnings("unchecked")
		List<CustomerOptionSet> returnList = q.getResultList();
		return returnList;*/
	}
	

	

	
}
