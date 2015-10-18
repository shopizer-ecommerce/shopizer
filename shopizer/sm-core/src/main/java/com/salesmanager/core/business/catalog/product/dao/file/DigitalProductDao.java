package com.salesmanager.core.business.catalog.product.dao.file;

import com.salesmanager.core.business.catalog.product.model.Product;
import com.salesmanager.core.business.catalog.product.model.file.DigitalProduct;
import com.salesmanager.core.business.generic.dao.SalesManagerEntityDao;
import com.salesmanager.core.business.merchant.model.MerchantStore;

public interface DigitalProductDao extends SalesManagerEntityDao<Long, DigitalProduct> {

	DigitalProduct getByProduct(MerchantStore store, Product product);


}
