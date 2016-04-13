package com.salesmanager.core.business.system.dao;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.salesmanager.core.business.generic.dao.SalesManagerEntityDaoImpl;
import com.salesmanager.core.business.merchant.model.MerchantStore;
import com.salesmanager.core.business.system.optin.model.Optin;

@Repository("optinDao")
public class OptinDaoImpl extends SalesManagerEntityDaoImpl<Long, Optin>
		implements OptinDao {

	@Override
	public List<Optin> listByMerchant(MerchantStore store) {
		
		
		StringBuilder qs = new StringBuilder();
		qs.append("select distinct o from Optin as o ");
		qs.append("where o.store.id=:storeId ");




    	String hql = qs.toString();
		Query q = super.getEntityManager().createQuery(hql);

    	q.setParameter("storeId", store.getId());


    	
    	@SuppressWarnings("unchecked")
		List<Optin> optins =  q.getResultList();

    	
    	return optins;
		
		
	}


}
