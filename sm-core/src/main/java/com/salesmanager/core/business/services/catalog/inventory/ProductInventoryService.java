package com.salesmanager.core.business.services.catalog.inventory;

import com.salesmanager.core.business.exception.ServiceException;
import com.salesmanager.core.model.catalog.product.Product;
import com.salesmanager.core.model.catalog.product.inventory.ProductInventory;
import com.salesmanager.core.model.catalog.product.variant.ProductVariant;

public interface ProductInventoryService {
	
	
	ProductInventory inventory(Product product) throws ServiceException;
	ProductInventory inventory(ProductVariant variant) throws ServiceException;

}
