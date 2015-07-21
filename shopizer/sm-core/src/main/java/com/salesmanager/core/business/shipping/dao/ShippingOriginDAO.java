package com.salesmanager.core.business.shipping.dao;

import com.salesmanager.core.business.generic.dao.SalesManagerEntityDao;
import com.salesmanager.core.business.merchant.model.MerchantStore;
import com.salesmanager.core.business.shipping.model.ShippingOrigin;

public interface ShippingOriginDAO extends SalesManagerEntityDao<Long, ShippingOrigin> {

	ShippingOrigin getByStore(MerchantStore store);

	
	
}
