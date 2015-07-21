package com.salesmanager.core.business.catalog.product.dao.relationship;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.salesmanager.core.business.catalog.product.model.Product;
import com.salesmanager.core.business.catalog.product.model.relationship.ProductRelationship;
import com.salesmanager.core.business.generic.dao.SalesManagerEntityDaoImpl;
import com.salesmanager.core.business.merchant.model.MerchantStore;
import com.salesmanager.core.business.reference.language.model.Language;

@Repository("productRelationshipDao")
public class ProductRelationshipDaoImpl extends SalesManagerEntityDaoImpl<Long, ProductRelationship>
		implements ProductRelationshipDao {
	
	@Override
	public List<ProductRelationship> getByType(MerchantStore store, String type, Product product, Language language) {
		//QDSL cannot interpret the following query, that is why it is in native format
		
		StringBuilder qs = new StringBuilder();
		qs.append("select distinct pr from ProductRelationship as pr ");
		qs.append("left join fetch pr.product p ");
		qs.append("join fetch pr.relatedProduct rp ");
		qs.append("left join fetch rp.descriptions rpd ");

		qs.append("where pr.code=:code ");
		qs.append("and pr.store.id=:storeId ");
		qs.append("and p.id=:id ");
		qs.append("and rpd.language.id=:langId");



    	String hql = qs.toString();
		Query q = super.getEntityManager().createQuery(hql);

    	q.setParameter("code", type);
    	q.setParameter("id", product.getId());
    	q.setParameter("storeId", store.getId());
    	qs.append("and pr.store.id=:storeId ");
    	q.setParameter("langId", language.getId());


    	
    	@SuppressWarnings("unchecked")
		List<ProductRelationship> relations =  q.getResultList();

    	
    	return relations;
		

	}
	
	@Override
	public List<ProductRelationship> getByType(MerchantStore store, String type, Language language) {
		//QDSL cannot interpret the following query, that is why it is in native format
		
		StringBuilder qs = new StringBuilder();
		qs.append("select distinct pr from ProductRelationship as pr ");
		qs.append("left join fetch pr.product p ");
		qs.append("join fetch pr.relatedProduct rp ");
		
		qs.append("left join fetch rp.attributes pattr ");
		qs.append("left join fetch rp.descriptions rpd ");
		qs.append("left join fetch rp.images pd ");
		qs.append("left join fetch rp.merchantStore rpm ");
		qs.append("left join fetch rpm.currency rpmc ");
		qs.append("left join fetch rp.availabilities pa ");
		qs.append("left join fetch rp.manufacturer m ");
		qs.append("left join fetch m.descriptions md ");
		qs.append("left join fetch pa.prices pap ");
		qs.append("left join fetch pap.descriptions papd ");

		qs.append("where pr.code=:code ");
		qs.append("and pr.store.id=:storeId ");
		qs.append("and rpd.language.id=:langId");



    	String hql = qs.toString();
		Query q = super.getEntityManager().createQuery(hql);

    	q.setParameter("code", type);
    	q.setParameter("langId", language.getId());
    	q.setParameter("storeId", store.getId());


    	
    	@SuppressWarnings("unchecked")
		List<ProductRelationship> relations =  q.getResultList();

    	
    	return relations;
		

	}
	
	@Override
	public List<ProductRelationship> getByGroup(MerchantStore store, String group) {
		//QDSL cannot interpret the following query, that is why it is in native format
		
		StringBuilder qs = new StringBuilder();
		qs.append("select distinct pr from ProductRelationship as pr ");
		qs.append("left join fetch pr.product p ");
		qs.append("left join fetch pr.relatedProduct rp ");
		
		qs.append("left join fetch rp.attributes pattr ");
		qs.append("left join fetch rp.descriptions rpd ");
		qs.append("left join fetch rp.images pd ");
		qs.append("left join fetch rp.merchantStore rpm ");
		qs.append("left join fetch rpm.currency rpmc ");
		qs.append("left join fetch rp.availabilities pa ");
		qs.append("left join fetch pa.prices pap ");
		qs.append("left join fetch pap.descriptions papd ");
		qs.append("left join fetch rp.manufacturer manuf ");
		qs.append("left join fetch manuf.descriptions manufd ");
		qs.append("left join fetch rp.type type ");

		qs.append("where pr.code=:code ");
		qs.append("and pr.store.id=:storeId ");




    	String hql = qs.toString();
		Query q = super.getEntityManager().createQuery(hql);

    	q.setParameter("code", group);
    	q.setParameter("storeId", store.getId());



    	
    	@SuppressWarnings("unchecked")
		List<ProductRelationship> relations =  q.getResultList();

    	
    	return relations;
		

	}
	
	@Override
	public List<ProductRelationship> getGroups(MerchantStore store) {

		StringBuilder qs = new StringBuilder();
		qs.append("select distinct pr from ProductRelationship as pr ");
		qs.append("where pr.store.id=:store ");
		
		qs.append("and pr.product=null");



    	String hql = qs.toString();
		Query q = super.getEntityManager().createQuery(hql);

    	q.setParameter("store", store.getId());


    	
    	@SuppressWarnings("unchecked")
		List<ProductRelationship> relations =  q.getResultList();
    	
    	Map<String,ProductRelationship> relationMap = new HashMap<String,ProductRelationship>();
    	for(ProductRelationship relationship : relations) {
    		if(!relationMap.containsKey(relationship.getCode())) {
    			relationMap.put(relationship.getCode(), relationship);
    		}
    	}
    	
    	List<ProductRelationship> returnList = new ArrayList<ProductRelationship>(relationMap.values());

    	
    	return returnList;
		

	}
	
	
	@Override
	public List<ProductRelationship> getByType(MerchantStore store, String type) {
		//QDSL cannot interpret the following query, that is why it is in native format
		
		StringBuilder qs = new StringBuilder();
		qs.append("select distinct pr from ProductRelationship as pr ");
		qs.append("left join fetch pr.product p ");
		qs.append("join fetch pr.relatedProduct rp ");
		qs.append("left join fetch rp.descriptions rpd ");

		qs.append("where pr.code=:code ");
		qs.append("and pr.store.id=:storeId ");




    	String hql = qs.toString();
		Query q = super.getEntityManager().createQuery(hql);

    	q.setParameter("code", type);
    	q.setParameter("storeId", store.getId());


    	@SuppressWarnings("unchecked")
		List<ProductRelationship> relations =  q.getResultList();

    	
    	return relations;
		

	}
	
	@Override
	public List<ProductRelationship> listByProducts(Product product) {
		//QDSL cannot interpret the following query, that is why it is in native format
		
		StringBuilder qs = new StringBuilder();
		qs.append("select pr from ProductRelationship as pr ");
		qs.append("left join fetch pr.product p ");
		qs.append("left join fetch pr.relatedProduct rp ");
		qs.append("left join fetch rp.attributes pattr ");
		qs.append("left join fetch p.descriptions pd ");
		qs.append("left join fetch rp.descriptions rpd ");

		qs.append("where p.id=:id");
		qs.append(" or rp.id=:id");




    	String hql = qs.toString();
		Query q = super.getEntityManager().createQuery(hql);

    	q.setParameter("id", product.getId());


    	@SuppressWarnings("unchecked")
		List<ProductRelationship> relations =  q.getResultList();

    	
    	return relations;
		

	}
	
	@Override
	public List<ProductRelationship> getByType(MerchantStore store, String type, Product product) {
		//QDSL cannot interpret the following query, that is why it is in native format
		
		
		StringBuilder qs = new StringBuilder();
		
		qs.append("select distinct pr from ProductRelationship as pr ");
		qs.append("left join fetch pr.product p ");
		qs.append("left join fetch pr.relatedProduct rp ");
		
		qs.append("left join fetch rp.attributes pattr ");
		qs.append("left join fetch rp.descriptions rpd ");
		qs.append("left join fetch rp.images pd ");
		qs.append("left join fetch rp.merchantStore rpm ");
		qs.append("left join fetch rpm.currency rpmc ");
		qs.append("left join fetch rp.availabilities pa ");
		qs.append("left join fetch pa.prices pap ");
		qs.append("left join fetch pap.descriptions papd ");
		
		qs.append("left join fetch rp.manufacturer manuf ");
		qs.append("left join fetch manuf.descriptions manufd ");
		qs.append("left join fetch rp.type type ");

		qs.append("where pr.code=:code ");
		qs.append("and p.id=:pId");




    	String hql = qs.toString();
		Query q = super.getEntityManager().createQuery(hql);

    	q.setParameter("code", type);
    	q.setParameter("pId", product.getId());


		@SuppressWarnings("unchecked")
		List<ProductRelationship> relations =  q.getResultList();

    	
    	return relations;
		

	}

	


}
