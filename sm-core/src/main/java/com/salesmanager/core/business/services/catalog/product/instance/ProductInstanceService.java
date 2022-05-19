package com.salesmanager.core.business.services.catalog.product.instance;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;

import com.salesmanager.core.business.services.common.generic.SalesManagerEntityService;
import com.salesmanager.core.model.catalog.product.Product;
import com.salesmanager.core.model.catalog.product.instance.ProductInstance;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;

public interface ProductInstanceService extends SalesManagerEntityService<Long, ProductInstance> {
	
	Optional<ProductInstance> getById(Long id, Long productId, MerchantStore store);
	
	List<ProductInstance> getByIds(List<Long> ids, MerchantStore store);
	
	Optional<ProductInstance> getById(Long id, MerchantStore store);
	
	Optional<ProductInstance> getBySku(String sku, Long productId, MerchantStore store, Language language);
	
	List<ProductInstance> getByProductId(MerchantStore store, Product product, Language language);
	
	
	Page<ProductInstance> getByProductId(MerchantStore store, Product product, Language language, int page, int count);
	
	
	boolean exist(String sku, Long productId);
	


}
