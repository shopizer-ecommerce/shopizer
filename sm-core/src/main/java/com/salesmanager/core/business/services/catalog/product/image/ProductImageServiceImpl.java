package com.salesmanager.core.business.services.catalog.product.image;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.salesmanager.core.business.exception.ServiceException;
import com.salesmanager.core.business.modules.cms.product.ProductFileManager;
import com.salesmanager.core.business.repositories.catalog.product.image.ProductImageRepository;
import com.salesmanager.core.business.services.common.generic.SalesManagerEntityServiceImpl;
import com.salesmanager.core.model.catalog.product.Product;
import com.salesmanager.core.model.catalog.product.file.ProductImageSize;
import com.salesmanager.core.model.catalog.product.image.ProductImage;
import com.salesmanager.core.model.catalog.product.image.ProductImageDescription;
import com.salesmanager.core.model.content.FileContentType;
import com.salesmanager.core.model.content.ImageContentFile;
import com.salesmanager.core.model.content.OutputContentFile;

@Service("productImage")
public class ProductImageServiceImpl extends SalesManagerEntityServiceImpl<Long, ProductImage> 
	implements ProductImageService {
	
	private ProductImageRepository productImageRepository;

	@Inject
	public ProductImageServiceImpl(ProductImageRepository productImageRepository) {
		super(productImageRepository);
		this.productImageRepository = productImageRepository;
	}
	
	@Inject
	private ProductFileManager productFileManager;
	

	
	
	public ProductImage getById(Long id) {
		
		
		return productImageRepository.findOne(id);
	}
	
	
	@Override
	public void addProductImages(Product product, List<ProductImage> productImages) throws ServiceException {
		
		try {
			for(ProductImage productImage : productImages) {
				
				Assert.notNull(productImage.getImage());
				
		        InputStream inputStream = productImage.getImage();
		        ImageContentFile cmsContentImage = new ImageContentFile();
		        cmsContentImage.setFileName( productImage.getProductImage() );
		        cmsContentImage.setFile( inputStream );
		        cmsContentImage.setFileContentType(FileContentType.PRODUCT);
		        

		        
	
				addProductImage(product,productImage,cmsContentImage);			
			}
		
		} catch (Exception e) {
			throw new ServiceException(e);
		}

	}
	
	
	@Override
	public void addProductImage(Product product, ProductImage productImage, ImageContentFile inputImage) throws ServiceException {
		
		
		
		
		productImage.setProduct(product);

		try {
			
			Assert.notNull(inputImage.getFile(),"ImageContentFile.file cannot be null");


			
			productFileManager.addProductImage(productImage, inputImage);
	
			//insert ProductImage
			this.saveOrUpdate(productImage);
			

		
		} catch (Exception e) {
			throw new ServiceException(e);
		} finally {
			try {

				if(inputImage.getFile()!=null) {
					inputImage.getFile().close();
				}

			} catch(Exception ignore) {
				
			}
		}
		
		
	}
	
	@Override
	public void saveOrUpdate(ProductImage productImage) throws ServiceException {
		
				
		super.save(productImage);
		
	}
	
	public void addProductImageDescription(ProductImage productImage, ProductImageDescription description)
	throws ServiceException {

		
			if(productImage.getDescriptions()==null) {
				productImage.setDescriptions(new ArrayList<ProductImageDescription>());
			}
			
			productImage.getDescriptions().add(description);
			description.setProductImage(productImage);
			update(productImage);


	}
	
	//TODO get default product image

	
	@Override
	public OutputContentFile getProductImage(ProductImage productImage, ProductImageSize size) throws ServiceException {

		
		ProductImage pi = new ProductImage();
		String imageName = productImage.getProductImage();
		if(size == ProductImageSize.LARGE) {
			imageName = "L-" + imageName;
		}
		
		if(size == ProductImageSize.SMALL) {
			imageName = "S-" + imageName;
		}
		
		pi.setProductImage(imageName);
		pi.setProduct(productImage.getProduct());
		
		OutputContentFile outputImage = productFileManager.getProductImage(pi);
		
		return outputImage;
		
	}
	
	@Override
	public OutputContentFile getProductImage(final String storeCode, final String productCode, final String fileName, final ProductImageSize size) throws ServiceException {
		OutputContentFile outputImage = productFileManager.getProductImage(storeCode, productCode, fileName, size);
		return outputImage;
		
	}
	
	@Override
	public List<OutputContentFile> getProductImages(Product product) throws ServiceException {
		return productFileManager.getImages(product);
	}
	
	@Override
	public void removeProductImage(ProductImage productImage) throws ServiceException {

		if(!StringUtils.isBlank(productImage.getProductImage())) {
			productFileManager.removeProductImage(productImage);//managed internally
		}
		
		ProductImage p = this.getById(productImage.getId());
		
		
		this.delete(p);
		
	}
}
