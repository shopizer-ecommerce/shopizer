package com.salesmanager.core.business.catalog.product.service.file;

import com.salesmanager.core.business.catalog.product.model.Product;
import com.salesmanager.core.business.catalog.product.model.file.DigitalProduct;
import com.salesmanager.core.business.content.model.InputContentFile;
import com.salesmanager.core.business.generic.exception.ServiceException;
import com.salesmanager.core.business.generic.service.SalesManagerEntityService;
import com.salesmanager.core.business.merchant.model.MerchantStore;


public interface DigitalProductService extends SalesManagerEntityService<Long, DigitalProduct> {

	void saveOrUpdate(DigitalProduct digitalProduct) throws ServiceException;

	void addProductFile(Product product, DigitalProduct digitalProduct,
			InputContentFile inputFile) throws ServiceException;



	DigitalProduct getByProduct(MerchantStore store, Product product)
			throws ServiceException;

	
}
