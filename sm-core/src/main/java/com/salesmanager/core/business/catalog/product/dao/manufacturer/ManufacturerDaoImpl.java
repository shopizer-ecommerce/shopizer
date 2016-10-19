package com.salesmanager.core.business.catalog.product.dao.manufacturer;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.mysema.query.jpa.JPQLQuery;
import com.mysema.query.jpa.impl.JPAQuery;
import com.salesmanager.core.business.catalog.product.model.manufacturer.Manufacturer;
import com.salesmanager.core.business.catalog.product.model.manufacturer.QManufacturer;
import com.salesmanager.core.business.catalog.product.model.manufacturer.QManufacturerDescription;
import com.salesmanager.core.business.generic.dao.SalesManagerEntityDaoImpl;
import com.salesmanager.core.business.merchant.model.MerchantStore;
import com.salesmanager.core.business.reference.language.model.Language;

@Repository("manufacturerDao")
public class ManufacturerDaoImpl extends SalesManagerEntityDaoImpl<Long, Manufacturer>
		implements ManufacturerDao {
	
	@Override
	public int getCountManufAttachedProducts(  Manufacturer manufacturer  ){
		StringBuilder countBuilderSelect = new StringBuilder();
		countBuilderSelect.append("select count(distinct p) from Product as p");
		
		StringBuilder countBuilderWhere = new StringBuilder();
		countBuilderWhere.append(" where p.manufacturer.id=:mId");
		
		Query countQ = super.getEntityManager().createQuery(
				countBuilderSelect.toString() + countBuilderWhere.toString());

		countQ.setParameter("mId", manufacturer.getId() );
		
		Number count = (Number) countQ.getSingleResult ();

		return count.intValue();
	}
	
	@Override
	public List<Manufacturer> listByStore(MerchantStore store, Language language) {
		QManufacturer qManufacturer = QManufacturer.manufacturer;
		QManufacturerDescription qManufacturerDescription = QManufacturerDescription.manufacturerDescription;
		
		JPQLQuery query = new JPAQuery (getEntityManager());
		
		query.from(qManufacturer)
			.leftJoin(qManufacturer.descriptions, qManufacturerDescription).fetch()
			.leftJoin(qManufacturer.merchantStore).fetch()
			.where(qManufacturerDescription.language.id.eq(language.getId())
			.and(qManufacturer.merchantStore.id.eq(store.getId())));
		

		
		List<Manufacturer> manufacturers = query.list(qManufacturer);
		return manufacturers;
	}
	
	//TODO get by code %

	public Manufacturer getById(Long id) {
		QManufacturer qManufacturer = QManufacturer.manufacturer;
		QManufacturerDescription qManufacturerDescription = QManufacturerDescription.manufacturerDescription;
		
		JPQLQuery query = new JPAQuery (getEntityManager());
		
		query.from(qManufacturer)
			.leftJoin(qManufacturer.descriptions, qManufacturerDescription).fetch()
			.leftJoin(qManufacturer.merchantStore).fetch()
			.where(qManufacturer.id.eq(id));
		

		
		return query.uniqueResult(qManufacturer);
	}
	
	@Override
	public List<Manufacturer> listByStore(MerchantStore store) {
		QManufacturer qManufacturer = QManufacturer.manufacturer;
		QManufacturerDescription qManufacturerDescription = QManufacturerDescription.manufacturerDescription;
		
		JPQLQuery query = new JPAQuery (getEntityManager());
		
		query.from(qManufacturer)
			.leftJoin(qManufacturer.descriptions, qManufacturerDescription).fetch()
			.leftJoin(qManufacturer.merchantStore).fetch()
			.where(qManufacturer.merchantStore.id.eq(store.getId()));
		

		
		List<Manufacturer> manufacturers = query.list(qManufacturer);
		return manufacturers;
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Manufacturer> listByProductsByCategoriesId(MerchantStore store, List<Long> ids, Language language) {
		StringBuilder builderSelect = new StringBuilder();
		builderSelect.append("select distinct manufacturer from Product as p ");
		builderSelect.append("join p.manufacturer manufacturer ");
		builderSelect.append("join manufacturer.descriptions md ");
		builderSelect.append("join p.categories categs ");
		builderSelect.append("where categs.id in (:cid) ");
		builderSelect.append("and md.language.id=:lang");

		Query query = super.getEntityManager().createQuery(
				builderSelect.toString());

		query.setParameter("cid", ids);
		query.setParameter("lang", language.getId());
		
		return query.getResultList();
		
	}

	@Override
	public Manufacturer getByCode(MerchantStore store, String code) {
		QManufacturer qManufacturer = QManufacturer.manufacturer;
		QManufacturerDescription qManufacturerDescription = QManufacturerDescription.manufacturerDescription;
		
		JPQLQuery query = new JPAQuery (getEntityManager());
		
		query.from(qManufacturer)
			.leftJoin(qManufacturer.descriptions, qManufacturerDescription).fetch()
			.leftJoin(qManufacturer.merchantStore).fetch()
			.where(qManufacturer.merchantStore.id.eq(store.getId())
					.and(qManufacturer.code.eq(code)));
		

		return query.uniqueResult(qManufacturer);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Manufacturer> getByUrl(MerchantStore store, String url,
			Language language) {
		
		StringBuilder qs = new StringBuilder();
		qs.append("select m from Manufacturer as m join m.merchantStore mm left join fetch m.descriptions md join fetch md.language mdl where mm.id=:storeId and mdl.id=:languageId and md.url like:url");
		
		Query query = super.getEntityManager().createQuery(
				qs.toString());

		query.setParameter("storeId", store.getId());
		query.setParameter("languageId", language.getId());
		query.setParameter("url", new StringBuilder().append("%").append(url.toLowerCase()).append("%").toString());
		
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Manufacturer> findByCode(MerchantStore store, String code, Language language) {
		
		StringBuilder qs = new StringBuilder();
		qs.append("select m from Manufacturer as m join m.merchantStore mm left join fetch m.descriptions md join join fetch md.language mdl where mm.id=:storeId and mdl.id=:languageId and m.code like:code");
		
		Query query = super.getEntityManager().createQuery(
				qs.toString());

		query.setParameter("storeId", store.getId());
		query.setParameter("languageId", language.getId());
		query.setParameter("code", new StringBuilder().append("%").append(code.toLowerCase()).append("%").toString());
		
		return query.getResultList();
	}

}
