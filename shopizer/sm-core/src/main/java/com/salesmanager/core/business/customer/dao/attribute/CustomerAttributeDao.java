package com.salesmanager.core.business.customer.dao.attribute;

import java.util.List;

import com.salesmanager.core.business.customer.model.attribute.CustomerAttribute;
import com.salesmanager.core.business.generic.dao.SalesManagerEntityDao;
import com.salesmanager.core.business.merchant.model.MerchantStore;

public interface CustomerAttributeDao extends SalesManagerEntityDao<Long, CustomerAttribute> {

	CustomerAttribute getByOptionId(MerchantStore store, Long customerId, Long id);

	List<CustomerAttribute> getByOptionValueId(MerchantStore store, Long id);

	List<CustomerAttribute> getByOptionId(MerchantStore store, Long id);

	List<CustomerAttribute> getByCustomerId(MerchantStore store, Long customerId);



}
