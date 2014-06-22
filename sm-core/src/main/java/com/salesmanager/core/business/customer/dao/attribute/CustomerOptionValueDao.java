package com.salesmanager.core.business.customer.dao.attribute;

import java.util.List;

import com.salesmanager.core.business.customer.model.attribute.CustomerOptionValue;
import com.salesmanager.core.business.generic.dao.SalesManagerEntityDao;
import com.salesmanager.core.business.merchant.model.MerchantStore;
import com.salesmanager.core.business.reference.language.model.Language;

public interface CustomerOptionValueDao extends SalesManagerEntityDao<Long, CustomerOptionValue> {

	List<CustomerOptionValue> listByStore(MerchantStore store, Language language);

	CustomerOptionValue getByCode(MerchantStore store, String optionValueCode);



}
