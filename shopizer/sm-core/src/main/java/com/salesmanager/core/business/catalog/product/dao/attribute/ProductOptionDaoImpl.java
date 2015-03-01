package com.salesmanager.core.business.catalog.product.dao.attribute;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.mysema.query.jpa.JPQLQuery;
import com.mysema.query.jpa.impl.JPAQuery;
import com.salesmanager.core.business.catalog.product.model.attribute.ProductOption;
import com.salesmanager.core.business.catalog.product.model.attribute.QProductOption;
import com.salesmanager.core.business.catalog.product.model.attribute.QProductOptionDescription;
import com.salesmanager.core.business.generic.dao.SalesManagerEntityDaoImpl;
import com.salesmanager.core.business.generic.exception.ServiceException;
import com.salesmanager.core.business.merchant.model.MerchantStore;
import com.salesmanager.core.business.reference.language.model.Language;

@Repository("productOptionDao")
public class ProductOptionDaoImpl extends SalesManagerEntityDaoImpl<Long, ProductOption>
		implements ProductOptionDao {
	
	@Override
	public List<ProductOption> listByStore(MerchantStore store, Language language) {
		
		QProductOption qProductOption = QProductOption.productOption;
		QProductOptionDescription qDescription = QProductOptionDescription.productOptionDescription;
		
		JPQLQuery query = new JPAQuery (getEntityManager());
		
		query.from(qProductOption)
			.leftJoin(qProductOption.descriptions, qDescription).fetch()
			.leftJoin(qProductOption.merchantStore).fetch()
			.where(qProductOption.merchantStore.id.eq(store.getId())
			.and(qDescription.language.id.eq(language.getId())))
			.orderBy(qProductOption.id.asc());
		
		return query.distinct().list(qProductOption);
				//listDistinct(qProductOption);
		
	}
	
	@Override
	public ProductOption getById(Long id) {
		QProductOption qProductOption = QProductOption.productOption;
		QProductOptionDescription qDescription = QProductOptionDescription.productOptionDescription;
		
		JPQLQuery query = new JPAQuery (getEntityManager());
		
		query.from(qProductOption)
			.leftJoin(qProductOption.descriptions, qDescription).fetch()
			.leftJoin(qProductOption.merchantStore).fetch()
			.where(qProductOption.id.eq(id));
		
		return query.uniqueResult(qProductOption);
	}
	
	@Override
	public List<ProductOption> getByName(MerchantStore store, String name, Language language) {
		QProductOption qProductOption = QProductOption.productOption;
		QProductOptionDescription qDescription = QProductOptionDescription.productOptionDescription;
		
		JPQLQuery query = new JPAQuery (getEntityManager());
		
		query.from(qProductOption)
			.leftJoin(qProductOption.descriptions, qDescription).fetch()
			.leftJoin(qProductOption.merchantStore).fetch()
			.where(qDescription.name.like("%" + name + "%")
			.and(qDescription.language.id.eq(language.getId()))
			.and(qProductOption.merchantStore.id.eq(store.getId())));
		

		
		List<ProductOption> options = query.list(qProductOption);
		return options;
	}
	
	@Override
	public List<ProductOption> getReadOnly(MerchantStore store, Language language) {
		QProductOption qProductOption = QProductOption.productOption;
		QProductOptionDescription qDescription = QProductOptionDescription.productOptionDescription;
		
		JPQLQuery query = new JPAQuery (getEntityManager());
		
		query.from(qProductOption)
			.leftJoin(qProductOption.descriptions, qDescription).fetch()
			.leftJoin(qProductOption.merchantStore).fetch()
			.where(qProductOption.readOnly.eq(true)
			.and(qDescription.language.id.eq(language.getId()))
			.and(qProductOption.merchantStore.id.eq(store.getId())));
		

		
		List<ProductOption> options = query.list(qProductOption);
		return options;
	}
	
	@Override
	public void saveOrUpdate(ProductOption entity) throws ServiceException {
		
		
		//save or update (persist and attach entities
		if(entity.getId()!=null && entity.getId()>0) {
			super.update(entity);
		} else {
			super.save(entity);
		}
	}
	
	@Override
	public ProductOption getByCode(MerchantStore store, String optionCode) {
		QProductOption qProductOption = QProductOption.productOption;
		QProductOptionDescription qDescription = QProductOptionDescription.productOptionDescription;
		
		JPQLQuery query = new JPAQuery (getEntityManager());
		
		query.from(qProductOption)
			.leftJoin(qProductOption.descriptions, qDescription).fetch()
			.leftJoin(qProductOption.merchantStore).fetch()
			.where(qProductOption.merchantStore.id.eq(store.getId())
			.and(qProductOption.code.eq(optionCode)));
		
		return query.uniqueResult(qProductOption);
	}



}
