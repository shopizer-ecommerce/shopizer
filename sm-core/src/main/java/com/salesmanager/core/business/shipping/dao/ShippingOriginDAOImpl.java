package com.salesmanager.core.business.shipping.dao;

import java.util.List;

import javax.persistence.NonUniqueResultException;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.salesmanager.core.business.generic.dao.SalesManagerEntityDaoImpl;
import com.salesmanager.core.business.merchant.model.MerchantStore;
import com.salesmanager.core.business.shipping.model.ShippingOrigin;

@Repository("shippingOriginDao")
public class ShippingOriginDAOImpl extends SalesManagerEntityDaoImpl<Long, ShippingOrigin> implements ShippingOriginDAO {

	public ShippingOriginDAOImpl() {
		super();
	}
	
	


	@Override
	public ShippingOrigin getByStore(MerchantStore store) {

		StringBuilder qs = new StringBuilder();
		qs.append("select s from ShippingOrigin as s ");
		qs.append("join fetch s.merchantStore sm ");
		qs.append("where sm.id=:sid");



    	String hql = qs.toString();
		Query q = super.getEntityManager().createQuery(hql);


    	q.setParameter("sid", store.getId());
    	@SuppressWarnings("unchecked")
		List<ShippingOrigin> results = (List<ShippingOrigin>)q.getResultList();
    	
    	
        if (results.isEmpty()) return null;
        
        else if (results.size() == 1) return results.get(0);
        throw new NonUniqueResultException();

	}

}
