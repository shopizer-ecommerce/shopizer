package com.salesmanager.core.business.catalog.product.dao.attribute;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.mysema.query.jpa.JPQLQuery;
import com.mysema.query.jpa.impl.JPAQuery;
import com.salesmanager.core.business.catalog.product.model.attribute.ProductOptionValue;
import com.salesmanager.core.business.catalog.product.model.attribute.QProductOptionValue;
import com.salesmanager.core.business.catalog.product.model.attribute.QProductOptionValueDescription;
import com.salesmanager.core.business.generic.dao.SalesManagerEntityDaoImpl;
import com.salesmanager.core.business.merchant.model.MerchantStore;
import com.salesmanager.core.business.reference.language.model.Language;

@Repository("productOptionValueDao")
public class ProductOptionValueDaoImpl extends SalesManagerEntityDaoImpl<Long, ProductOptionValue>
		implements ProductOptionValueDao {
	
	
	@Override
	public List<ProductOptionValue> listByStore(MerchantStore store, Language language) {
		
		QProductOptionValue qProductOption = QProductOptionValue.productOptionValue;
		QProductOptionValueDescription qDescription = QProductOptionValueDescription.productOptionValueDescription;
		
		JPQLQuery query = new JPAQuery (getEntityManager());
		
		query.from(qProductOption)
			.leftJoin(qProductOption.descriptions, qDescription).fetch()
			.leftJoin(qProductOption.merchantStore).fetch()
			.where(qProductOption.merchantStore.id.eq(store.getId())
			.and(qDescription.language.id.eq(language.getId())));
			query.orderBy(qProductOption.id.asc());
			
			
		
		return query.distinct().list(qProductOption);
				//listDistinct(qProductOption);
		
	}
	
	@Override
	public List<ProductOptionValue> listByStoreNoReadOnly(MerchantStore store, Language language) {
		
		QProductOptionValue qProductOption = QProductOptionValue.productOptionValue;
		QProductOptionValueDescription qDescription = QProductOptionValueDescription.productOptionValueDescription;
		
		JPQLQuery query = new JPAQuery (getEntityManager());
		
		query.from(qProductOption)
			.leftJoin(qProductOption.descriptions, qDescription).fetch()
			.leftJoin(qProductOption.merchantStore).fetch()
			.where(qProductOption.merchantStore.id.eq(store.getId())
			.and(qProductOption.productOptionDisplayOnly.eq(false))
			.and(qDescription.language.id.eq(language.getId())));
			query.orderBy(qProductOption.id.asc());
			
			
		
		return query.distinct().list(qProductOption);
				//listDistinct(qProductOption);
		
	}
	

	
	@Override
	public ProductOptionValue getById(MerchantStore store, Long id) {
		QProductOptionValue qProductOption = QProductOptionValue.productOptionValue;
		QProductOptionValueDescription qDescription = QProductOptionValueDescription.productOptionValueDescription;
		
		JPQLQuery query = new JPAQuery (getEntityManager());
		
		query.from(qProductOption)
			.leftJoin(qProductOption.descriptions, qDescription).fetch()
			.leftJoin(qProductOption.merchantStore).fetch()
			.where(qProductOption.id.eq(id)
			.and(qProductOption.merchantStore.id.eq(store.getId())));
		
		return query.uniqueResult(qProductOption);
	}
	
	@Override
	public List<ProductOptionValue> getByName(MerchantStore store, String name, Language language) {
		QProductOptionValue qProductOption = QProductOptionValue.productOptionValue;
		QProductOptionValueDescription qDescription = QProductOptionValueDescription.productOptionValueDescription;
		
		JPQLQuery query = new JPAQuery (getEntityManager());
		
		query.from(qProductOption)
			.leftJoin(qProductOption.descriptions, qDescription).fetch()
			.leftJoin(qProductOption.merchantStore).fetch()
			.where(qDescription.name.like("%" + name + "%")
			.and(qDescription.language.id.eq(language.getId()))
			.and(qProductOption.merchantStore.id.eq(store.getId())));
		

		
		List<ProductOptionValue> options = query.list(qProductOption);
		return options;
	}
	
	@Override
	public ProductOptionValue getByCode(MerchantStore store, String optionValueCode) {
		QProductOptionValue qProductOption = QProductOptionValue.productOptionValue;
		QProductOptionValueDescription qDescription = QProductOptionValueDescription.productOptionValueDescription;
		
		JPQLQuery query = new JPAQuery (getEntityManager());
		
		query.from(qProductOption)
			.leftJoin(qProductOption.descriptions, qDescription).fetch()
			.leftJoin(qProductOption.merchantStore).fetch()
			.where(qProductOption.code.eq(optionValueCode)
			.and(qProductOption.merchantStore.id.eq(store.getId())));
		
		return query.uniqueResult(qProductOption);
	}



}
