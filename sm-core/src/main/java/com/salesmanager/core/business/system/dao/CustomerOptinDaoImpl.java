package com.salesmanager.core.business.system.dao;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.salesmanager.core.business.generic.dao.SalesManagerEntityDaoImpl;
import com.salesmanager.core.business.merchant.model.MerchantStore;
import com.salesmanager.core.business.system.optin.model.CustomerOptin;
import com.salesmanager.core.business.system.optin.model.Optin;

@Repository("customerOptinDao")
public class CustomerOptinDaoImpl extends SalesManagerEntityDaoImpl<Long, CustomerOptin>
		implements CustomerOptinDao {

	@Override
	public List<CustomerOptin> listByMerchantAndOptin(MerchantStore store,
			Optin optin) {
		
		StringBuilder qs = new StringBuilder();
		qs.append("select distinct c from CustomerOptin as c ");
		qs.append("left join fetch c.optin o ");
		qs.append("where o.store.id=:storeId ");
		qs.append("and o.code=:code ");




    	String hql = qs.toString();
		Query q = super.getEntityManager().createQuery(hql);

    	q.setParameter("storeId", store.getId());
    	q.setParameter("code", optin.getCode());


    	
    	@SuppressWarnings("unchecked")
		List<CustomerOptin> optins =  q.getResultList();

    	
    	return optins;
		
		
	}


}
