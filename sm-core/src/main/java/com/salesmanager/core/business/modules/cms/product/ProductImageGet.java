package com.salesmanager.core.business.modules.cms.product;

import com.salesmanager.core.business.exception.ServiceException;
import com.salesmanager.core.business.modules.cms.common.ImageGet;
import com.salesmanager.core.model.catalog.product.Product;
import com.salesmanager.core.model.catalog.product.file.ProductImageSize;
import com.salesmanager.core.model.catalog.product.image.ProductImage;
import com.salesmanager.core.model.content.OutputContentFile;

import java.util.List;

public interface ProductImageGet extends ImageGet {

    /**
     * Used for accessing the path directly
     *
     * @param merchantStoreCode
     * @param imageName
     * @return
     * @throws ServiceException
     */
    public OutputContentFile getProductImage(final String merchantStoreCode, final String productCode, final String imageName) throws ServiceException;

    public OutputContentFile getProductImage(final String merchantStoreCode, final String productCode, final String imageName, final ProductImageSize size) throws ServiceException;

    public OutputContentFile getProductImage(ProductImage productImage) throws ServiceException;

    public List<OutputContentFile> getImages(Product product) throws ServiceException;


}
