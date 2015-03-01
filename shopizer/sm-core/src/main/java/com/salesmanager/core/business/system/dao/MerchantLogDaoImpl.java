package com.salesmanager.core.business.system.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.salesmanager.core.business.generic.dao.SalesManagerEntityDaoImpl;
import com.salesmanager.core.business.merchant.model.MerchantStore;
import com.salesmanager.core.business.system.model.MerchantLog;

@Repository("merchantLogDao")
public class MerchantLogDaoImpl extends SalesManagerEntityDaoImpl<Long, MerchantLog>
		implements MerchantLogDao {



	
	@Override
	public List<MerchantLog> listByMerchant(MerchantStore store) {

/*		QMerchantLog qMerchantLog = QMerchantLog.

		
		JPQLQuery query = new JPAQuery (getEntityManager());
		query.from(qMerchantCnfiguration)
			.innerJoin(qMerchantCnfiguration.merchantStore).fetch()
			.where(qMerchantCnfiguration.merchantStore.id.eq(store.getId()));
		
		return query.list(qMerchantCnfiguration);*/
		
		return null;

	}
}
