package com.salesmanager.core.modules.cms.product;

import com.salesmanager.core.business.catalog.product.model.Product;
import com.salesmanager.core.business.catalog.product.model.image.ProductImage;
import com.salesmanager.core.business.generic.exception.ServiceException;
import com.salesmanager.core.modules.cms.common.ImageRemove;


public interface ProductImageRemove extends ImageRemove {
	
	
	public void removeProductImage(ProductImage productImage) throws ServiceException;
	public void removeProductImages(Product product) throws ServiceException;
	


}
