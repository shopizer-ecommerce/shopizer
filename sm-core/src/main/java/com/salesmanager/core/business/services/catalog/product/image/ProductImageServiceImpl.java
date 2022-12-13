package com.salesmanager.core.business.services.catalog.product.image;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.helper.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.salesmanager.core.business.configuration.events.products.DeleteProductImageEvent;
import com.salesmanager.core.business.configuration.events.products.SaveProductImageEvent;
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
import com.salesmanager.core.model.merchant.MerchantStore;

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
	
	@Autowired
	private ApplicationEventPublisher eventPublisher;

	public ProductImage getById(Long id) {

		return productImageRepository.findOne(id);
	}

	@Override
	public void addProductImages(Product product, List<ProductImage> productImages) throws ServiceException {

		try {
			for (ProductImage productImage : productImages) {

				Assert.notNull(productImage.getImage());

				InputStream inputStream = productImage.getImage();
				ImageContentFile cmsContentImage = new ImageContentFile();
				cmsContentImage.setFileName(productImage.getProductImage());
				cmsContentImage.setFile(inputStream);
				cmsContentImage.setFileContentType(FileContentType.PRODUCT);

				addProductImage(product, productImage, cmsContentImage);
			}

		} catch (Exception e) {
			throw new ServiceException(e);
		}

	}

	@Override
	public void addProductImage(Product product, ProductImage productImage, ImageContentFile inputImage)
			throws ServiceException {

		productImage.setProduct(product);

		try {
			if (productImage.getImageType() == 0) {
				Assert.notNull(inputImage.getFile(), "ImageContentFile.file cannot be null");
				productFileManager.addProductImage(productImage, inputImage);
			}

			// insert ProductImage
			ProductImage img = saveOrUpdate(productImage);
			//manual workaround since aspect is not working
			eventPublisher.publishEvent(new SaveProductImageEvent(eventPublisher, img, product));

		} catch (Exception e) {
			throw new ServiceException(e);
		} finally {
			try {

				if (inputImage.getFile() != null) {
					inputImage.getFile().close();
				}

			} catch (Exception ignore) {

			}
		}

	}

	@Override
	public ProductImage saveOrUpdate(ProductImage productImage) throws ServiceException {

		return productImageRepository.save(productImage);

	}

	public void addProductImageDescription(ProductImage productImage, ProductImageDescription description)
			throws ServiceException {

		if (productImage.getDescriptions() == null) {
			productImage.setDescriptions(new ArrayList<ProductImageDescription>());
		}

		productImage.getDescriptions().add(description);
		description.setProductImage(productImage);
		update(productImage);

	}

	// TODO get default product image

	@Override
	public OutputContentFile getProductImage(ProductImage productImage, ProductImageSize size) throws ServiceException {

		ProductImage pi = new ProductImage();
		String imageName = productImage.getProductImage();
		if (size == ProductImageSize.LARGE) {
			imageName = "L-" + imageName;
		}

		if (size == ProductImageSize.SMALL) {
			imageName = "S-" + imageName;
		}

		pi.setProductImage(imageName);
		pi.setProduct(productImage.getProduct());

		return productFileManager.getProductImage(pi);

	}

	@Override
	public OutputContentFile getProductImage(final String storeCode, final String productCode, final String fileName,
			final ProductImageSize size) throws ServiceException {
		return productFileManager.getProductImage(storeCode, productCode, fileName, size);

	}

	@Override
	public List<OutputContentFile> getProductImages(Product product) throws ServiceException {
		return productFileManager.getImages(product);
	}

	@Override
	public void removeProductImage(ProductImage productImage) throws ServiceException {

		if (!StringUtils.isBlank(productImage.getProductImage())) {
			productFileManager.removeProductImage(productImage);// managed internally
		}
		ProductImage p = getById(productImage.getId());
		
		Product product = p.getProduct();
		
		
		delete(p);
		/**
		 * workaround for aspect
		 */
		eventPublisher.publishEvent(new DeleteProductImageEvent(eventPublisher, p, product));


	}

	@Override
	public Optional<ProductImage> getProductImage(Long imageId, Long productId, MerchantStore store) {

		Optional<ProductImage> image = Optional.empty();

		ProductImage img = productImageRepository.finById(imageId, productId, store.getCode());
		if (img != null) {
			image = Optional.of(img);
		}

		return image;
	}

	@Override
	public void updateProductImage(Product product, ProductImage productImage) {
		Validate.notNull(product, "Product cannot be null");
		Validate.notNull(productImage, "ProductImage cannot be null");
		productImage.setProduct(product);
		productImageRepository.save(productImage);

	}
}
