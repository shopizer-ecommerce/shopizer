package com.salesmanager.core.business.modules.cms.product.aws;

import java.util.List;

import com.salesmanager.core.business.exception.ServiceException;
import com.salesmanager.core.business.modules.cms.impl.CMSManager;
import com.salesmanager.core.business.modules.cms.product.ProductImageGet;
import com.salesmanager.core.business.modules.cms.product.ProductImagePut;
import com.salesmanager.core.business.modules.cms.product.ProductImageRemove;
import com.salesmanager.core.model.catalog.product.Product;
import com.salesmanager.core.model.catalog.product.file.ProductImageSize;
import com.salesmanager.core.model.catalog.product.image.ProductImage;
import com.salesmanager.core.model.content.FileContentType;
import com.salesmanager.core.model.content.ImageContentFile;
import com.salesmanager.core.model.content.OutputContentFile;

/**
 * Product content file manager with AWS S3
 * @author carlsamson
 *
 */
public class S3ProductContentFileManager implements ProductImagePut, ProductImageGet, ProductImageRemove {
	
    
	private static S3ProductContentFileManager fileManager = null;
	
	private CMSManager cmsManager;
	
	public static S3ProductContentFileManager getInstance()
    {

        if ( fileManager == null )
        {
            fileManager = new S3ProductContentFileManager();
        }

        return fileManager;

    }

	@Override
	public List<OutputContentFile> getImages(String merchantStoreCode, FileContentType imageContentType)
			throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void removeImages(String merchantStoreCode) throws ServiceException {
		// TODO Auto-generated method stub

	}

	@Override
	public void removeProductImage(ProductImage productImage) throws ServiceException {
		// TODO Auto-generated method stub

	}

	@Override
	public void removeProductImages(Product product) throws ServiceException {
		// TODO Auto-generated method stub

	}

	@Override
	public OutputContentFile getProductImage(String merchantStoreCode, String productCode, String imageName)
			throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OutputContentFile getProductImage(String merchantStoreCode, String productCode, String imageName,
			ProductImageSize size) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OutputContentFile getProductImage(ProductImage productImage) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<OutputContentFile> getImages(Product product) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addProductImage(ProductImage productImage, ImageContentFile contentImage) throws ServiceException {
		// TODO Auto-generated method stub

	}

	public CMSManager getCmsManager() {
		return cmsManager;
	}

	public void setCmsManager(CMSManager cmsManager) {
		this.cmsManager = cmsManager;
	}


}
