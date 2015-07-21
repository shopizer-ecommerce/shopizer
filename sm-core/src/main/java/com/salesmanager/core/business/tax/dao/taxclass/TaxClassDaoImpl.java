package com.salesmanager.core.business.tax.dao.taxclass;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.mysema.query.jpa.JPQLQuery;
import com.mysema.query.jpa.impl.JPAQuery;
import com.salesmanager.core.business.generic.dao.SalesManagerEntityDaoImpl;
import com.salesmanager.core.business.merchant.model.MerchantStore;
import com.salesmanager.core.business.tax.model.taxclass.QTaxClass;
import com.salesmanager.core.business.tax.model.taxclass.TaxClass;

@Repository("taxClassDao")
public class TaxClassDaoImpl extends SalesManagerEntityDaoImpl<Long, TaxClass> implements TaxClassDao{
	
	public TaxClassDaoImpl() {
		super();
	}
	
	
	@Override
	public List<TaxClass> listByStore(MerchantStore store) {
		QTaxClass qTax = QTaxClass.taxClass;

		JPQLQuery query = new JPAQuery (getEntityManager());
		
		query.from(qTax)
			.leftJoin(qTax.merchantStore).fetch()
			.where(qTax.merchantStore.id.eq(store.getId())
			.or(qTax.merchantStore.isNull()));
		
		List<TaxClass> taxes = query.list(qTax);
		return taxes;
	}
	
	
	@Override
	public TaxClass getByCode(String code) {
		QTaxClass qTax = QTaxClass.taxClass;

		JPQLQuery query = new JPAQuery (getEntityManager());
		
		query.from(qTax)
			.leftJoin(qTax.merchantStore).fetch()
			.where(qTax.code.eq(code));
		
		return query.uniqueResult(qTax);
	}
	
	@Override
	public TaxClass getByCode(String code, MerchantStore store) {
		QTaxClass qTax = QTaxClass.taxClass;

		JPQLQuery query = new JPAQuery (getEntityManager());
		
		query.from(qTax)
			.leftJoin(qTax.merchantStore).fetch()
			.where(qTax.code.eq(code).and(qTax.merchantStore.id.eq(store.getId())));
		
		return query.uniqueResult(qTax);
	}
	
	@Override
	public TaxClass getById(Long id) {
		QTaxClass qTax = QTaxClass.taxClass;

		JPQLQuery query = new JPAQuery (getEntityManager());
		
		query.from(qTax)
			.leftJoin(qTax.merchantStore).fetch()
			.where(qTax.id.eq(id));
		
		return query.uniqueResult(qTax);
	}
	
	
}