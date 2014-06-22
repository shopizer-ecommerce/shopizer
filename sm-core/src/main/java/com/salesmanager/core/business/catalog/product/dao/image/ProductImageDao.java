package com.salesmanager.core.business.catalog.product.dao.image;

import com.salesmanager.core.business.catalog.product.model.image.ProductImage;
import com.salesmanager.core.business.generic.dao.SalesManagerEntityDao;

public interface ProductImageDao extends SalesManagerEntityDao<Long, ProductImage> {

	ProductImage getProductImageById(Long id);

}
