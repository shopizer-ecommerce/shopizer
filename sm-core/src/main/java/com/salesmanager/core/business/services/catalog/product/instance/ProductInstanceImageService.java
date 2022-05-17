package com.salesmanager.core.business.services.catalog.product.instance;

import java.util.List;

import com.salesmanager.core.business.services.common.generic.SalesManagerEntityService;
import com.salesmanager.core.model.catalog.product.instance.ProductInstanceImage;
import com.salesmanager.core.model.merchant.MerchantStore;

public interface ProductInstanceImageService extends SalesManagerEntityService<Long, ProductInstanceImage> {

	
	List<ProductInstanceImage> list(Long productInstanceId, MerchantStore store);
	List<ProductInstanceImage> listByProduct(Long productId, MerchantStore store);
	List<ProductInstanceImage> listByProductInstanceGroup(Long productInstanceGroupId, MerchantStore store);
	
}
