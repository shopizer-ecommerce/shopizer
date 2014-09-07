package com.salesmanager.core.modules.cms.product;

import java.util.List;

import com.salesmanager.core.business.catalog.product.model.Product;
import com.salesmanager.core.business.catalog.product.model.file.ProductImageSize;
import com.salesmanager.core.business.catalog.product.model.image.ProductImage;
import com.salesmanager.core.business.content.model.OutputContentFile;
import com.salesmanager.core.business.generic.exception.ServiceException;
import com.salesmanager.core.modules.cms.common.ImageGet;

public interface ProductImageGet extends ImageGet{
	
	/**
	 * Used for accessing the path directly
	 * @param merchantStoreCode
	 * @param product
	 * @param imageName
	 * @return
	 * @throws ServiceException
	 */
	public OutputContentFile getProductImage(final String merchantStoreCode, final String productCode, final String imageName) throws ServiceException;
	public OutputContentFile getProductImage(final String merchantStoreCode, final String productCode, final String imageName, final ProductImageSize size) throws ServiceException;
	public OutputContentFile getProductImage(ProductImage productImage) throws ServiceException;
	public List<OutputContentFile> getImages(Product product) throws ServiceException;


}
