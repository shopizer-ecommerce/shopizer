package com.salesmanager.core.business.customer.dao.attribute;

import java.util.List;

import com.salesmanager.core.business.customer.model.attribute.CustomerOptionSet;
import com.salesmanager.core.business.generic.dao.SalesManagerEntityDao;
import com.salesmanager.core.business.merchant.model.MerchantStore;
import com.salesmanager.core.business.reference.language.model.Language;

public interface CustomerOptionSetDao extends SalesManagerEntityDao<Long, CustomerOptionSet> {

	List<CustomerOptionSet> getByOptionId(MerchantStore store, Long id);

	List<CustomerOptionSet> listByStore(MerchantStore store, Language language);

	CustomerOptionSet getById(Long customerOptionSetId);

	List<CustomerOptionSet> getByOptionValueId(MerchantStore store, Long id);







}
