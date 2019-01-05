package com.salesmanager.core.business.repositories.merchant;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.salesmanager.core.business.exception.ServiceException;
import com.salesmanager.core.model.common.GenericEntityList;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.merchant.MerchantStoreCriteria;

public class MerchantRepositoryImpl implements MerchantRepositoryCustom {

    @PersistenceContext
    private EntityManager em;
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public GenericEntityList listByCriteria(MerchantStoreCriteria criteria)
			throws ServiceException {
		try {
			StringBuilder req = new StringBuilder();req.append("select distinct m from MerchantStore m left join fetch m.country mc left join fetch m.currency mc left join fetch m.zone mz left join fetch m.defaultLanguage md left join fetch m.languages mls ");
			StringBuilder countBuilder = new StringBuilder();
			countBuilder.append("select count(distinct m) from MerchantStore m");
			if(criteria.getCode()!=null) {
				req.append("  where m.code like:code");
				countBuilder.append(" where m.code like:code");
			}
			if(criteria.getUser()!=null) {
				if(criteria.getCode()==null) {
					req.append(" where" );
					countBuilder.append(" where ");
				} else {
					req.append(" and" );
					countBuilder.append(" and ");					
				}
				req.append(" m.auditSection.modifiedBy like:user");
				countBuilder.append(" m.auditSection.modifiedBy like:user");
			}
			
			Query countQ = this.em.createQuery(
					countBuilder.toString());
			
	    	String hql = req.toString();
			Query q = this.em.createQuery(hql);
			
			if(criteria.getCode()!=null) {
				countQ.setParameter("code", criteria.getCode());
				q.setParameter("code", criteria.getCode());
			}
			if(criteria.getUser()!=null) {
				countQ.setParameter("user", criteria.getUser());
				q.setParameter("user", criteria.getUser());
			}
			
			Number count = (Number) countQ.getSingleResult ();
			
			GenericEntityList entityList = new GenericEntityList();
			entityList.setTotalCount(count.intValue());
			
	    	if(criteria.getMaxCount()>0) {
	    		
	    		
		    	q.setFirstResult(criteria.getStartIndex());
		    	if(criteria.getMaxCount()<count.intValue()) {
		    		q.setMaxResults(criteria.getMaxCount());
		    	}
		    	else {
		    		q.setMaxResults(count.intValue());
		    	}
	    	}
	    	
	    	@SuppressWarnings("unchecked")
			List<MerchantStore> stores =  q.getResultList();
	    	entityList.setList(stores);
	    	
	    	return entityList;
			

			
		} catch(javax.persistence.NoResultException ers) {
			return null;
		} catch(Exception e) {
			
		}
		return null;
	}

}
