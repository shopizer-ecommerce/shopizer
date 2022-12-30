package com.salesmanager.core.business.modules.cms.product;

import com.salesmanager.core.business.exception.ServiceException;
import com.salesmanager.core.model.catalog.product.image.ProductImage;
import com.salesmanager.core.model.content.ImageContentFile;


public interface ProductImagePut {

  void addProductImage(ProductImage productImage, ImageContentFile contentImage)
      throws ServiceException;

}
