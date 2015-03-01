package com.salesmanager.core.modules.cms.product;

import com.salesmanager.core.business.catalog.product.model.image.ProductImage;
import com.salesmanager.core.business.content.model.ImageContentFile;
import com.salesmanager.core.business.generic.exception.ServiceException;


public interface ProductImagePut {
	
	
	public void addProductImage(ProductImage productImage, ImageContentFile contentImage) throws ServiceException;


}
