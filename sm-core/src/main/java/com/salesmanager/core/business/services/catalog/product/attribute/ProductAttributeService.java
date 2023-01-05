package com.salesmanager.core.business.services.catalog.product.attribute;

import java.util.List;

import org.springframework.data.domain.Page;

import com.salesmanager.core.business.exception.ServiceException;
import com.salesmanager.core.business.services.common.generic.SalesManagerEntityService;
import com.salesmanager.core.model.catalog.product.Product;
import com.salesmanager.core.model.catalog.product.attribute.ProductAttribute;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;

public interface ProductAttributeService extends
		SalesManagerEntityService<Long, ProductAttribute> {

	ProductAttribute saveOrUpdate(ProductAttribute productAttribute)
			throws ServiceException;
	
	List<ProductAttribute> getByOptionId(MerchantStore store,
			Long id) throws ServiceException;

	List<ProductAttribute> getByOptionValueId(MerchantStore store,
			Long id) throws ServiceException;

	Page<ProductAttribute> getByProductId(MerchantStore store, Product product, Language language, int page, int count)
			throws ServiceException;
	
	Page<ProductAttribute> getByProductId(MerchantStore store, Product product, int page, int count)
			throws ServiceException;

	List<ProductAttribute> getByAttributeIds(MerchantStore store, Product product, List<Long> ids)
			throws ServiceException;
	
	List<ProductAttribute> getProductAttributesByCategoryLineage(MerchantStore store, String lineage, Language language) throws Exception;
}
