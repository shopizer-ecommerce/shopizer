package com.salesmanager.core.business.services.catalog.product.price;

import java.util.List;

import com.salesmanager.core.business.exception.ServiceException;
import com.salesmanager.core.business.services.common.generic.SalesManagerEntityService;
import com.salesmanager.core.model.catalog.product.price.ProductPrice;
import com.salesmanager.core.model.catalog.product.price.ProductPriceDescription;
import com.salesmanager.core.model.merchant.MerchantStore;

public interface ProductPriceService extends SalesManagerEntityService<Long, ProductPrice> {

	void addDescription(ProductPrice price, ProductPriceDescription description) throws ServiceException;

	ProductPrice saveOrUpdate(ProductPrice price) throws ServiceException;
	
	List<ProductPrice> findByProductSku(String sku, MerchantStore store);
	
	ProductPrice findById(Long priceId, String sku, MerchantStore store);
	
	List<ProductPrice> findByInventoryId(Long productInventoryId, String sku, MerchantStore store);
	

}
