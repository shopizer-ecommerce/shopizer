package com.salesmanager.web.populator.catalog;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.Validate;
import org.apache.commons.lang3.StringUtils;

import com.salesmanager.core.business.catalog.product.model.Product;
import com.salesmanager.core.business.catalog.product.model.availability.ProductAvailability;
import com.salesmanager.core.business.catalog.product.model.description.ProductDescription;
import com.salesmanager.core.business.catalog.product.model.image.ProductImage;
import com.salesmanager.core.business.catalog.product.model.manufacturer.ManufacturerDescription;
import com.salesmanager.core.business.catalog.product.model.price.FinalPrice;
import com.salesmanager.core.business.catalog.product.model.price.ProductPrice;
import com.salesmanager.core.business.catalog.product.service.PricingService;
import com.salesmanager.core.business.generic.exception.ConversionException;
import com.salesmanager.core.business.merchant.model.MerchantStore;
import com.salesmanager.core.business.reference.language.model.Language;
import com.salesmanager.core.constants.Constants;
import com.salesmanager.core.utils.AbstractDataPopulator;
import com.salesmanager.web.entity.catalog.ReadableImage;
import com.salesmanager.web.entity.catalog.manufacturer.ReadableManufacturer;
import com.salesmanager.web.entity.catalog.product.ReadableProduct;
import com.salesmanager.web.utils.ImageFilePathUtils;

public class ReadableProductPopulator extends
		AbstractDataPopulator<Product, ReadableProduct> {
	
	private PricingService pricingService;

	public PricingService getPricingService() {
		return pricingService;
	}




	public void setPricingService(PricingService pricingService) {
		this.pricingService = pricingService;
	}




	@Override
	public ReadableProduct populate(Product source,
			ReadableProduct target, MerchantStore store, Language language)
			throws ConversionException {
		Validate.notNull(pricingService, "Requires to set PricingService");
		
		try {
			

			ProductDescription description = source.getProductDescription();
	
			target.setId(source.getId());
			target.setAvailable(source.isAvailable());
			if(source.getProductReviewAvg()!=null) {
				double avg = source.getProductReviewAvg().doubleValue();
				double rating = Math.round(avg * 2) / 2.0f;
				target.setRating(rating);
			}
			target.setProductVirtual(source.getProductVirtual());
			if(source.getProductReviewCount()!=null) {
				target.setRatingCount(source.getProductReviewCount().intValue());
			}
			if(description!=null) {
				com.salesmanager.web.entity.catalog.product.ProductDescription tragetDescription = new com.salesmanager.web.entity.catalog.product.ProductDescription();
				tragetDescription.setFriendlyUrl(description.getSeUrl());
				tragetDescription.setName(description.getName());
				if(!StringUtils.isBlank(description.getMetatagTitle())) {
					tragetDescription.setTitle(description.getMetatagTitle());
				} else {
					tragetDescription.setTitle(description.getName());
				}
				tragetDescription.setMetaDescription(description.getMetatagDescription());
				tragetDescription.setDescription(description.getDescription());
				tragetDescription.setHighlights(description.getProductHighlight());
				target.setDescription(tragetDescription);
			}
			
			if(source.getManufacturer()!=null) {
				ManufacturerDescription manufacturer = source.getManufacturer().getDescriptions().iterator().next(); 
				ReadableManufacturer manufacturerEntity = new ReadableManufacturer();
				com.salesmanager.web.entity.catalog.manufacturer.ManufacturerDescription d = new com.salesmanager.web.entity.catalog.manufacturer.ManufacturerDescription(); 
				d.setName(manufacturer.getName());
				manufacturerEntity.setDescription(d);
				manufacturerEntity.setId(manufacturer.getId());
				manufacturerEntity.setOrder(source.getManufacturer().getOrder());
				manufacturerEntity.setCode(source.getManufacturer().getCode());
				target.setManufacturer(manufacturerEntity);
			}
			
			ProductImage image = source.getProductImage();
			if(image!=null) {
				ReadableImage rimg = new ReadableImage();
				rimg.setImageName(image.getProductImage());
				String imagePath = ImageFilePathUtils.buildProductImageFilePath(store, source.getSku(), image.getProductImage());
				rimg.setImageUrl(imagePath);
				rimg.setId(image.getId());
				target.setImage(rimg);
				
				//other images
				Set<ProductImage> images = source.getImages();
				if(images!=null && images.size()>0) {
					List<ReadableImage> imageList = new ArrayList<ReadableImage>();
					for(ProductImage img : images) {
						ReadableImage prdImage = new ReadableImage();
						prdImage.setImageName(img.getProductImage());
						String imgPath = ImageFilePathUtils.buildProductImageFilePath(store, source.getSku(), img.getProductImage());
						prdImage.setImageUrl(imgPath);
						prdImage.setId(img.getId());
						imageList.add(prdImage);
					}
					target
					.setImages(imageList);
				}
			}
	
			target.setSku(source.getSku());
			//target.setLanguage(language.getCode());
	
			FinalPrice price = pricingService.calculateProductPrice(source);

			target.setFinalPrice(pricingService.getDisplayAmount(price.getFinalPrice(), store));
			target.setPrice(price.getFinalPrice());
	
			if(price.isDiscounted()) {
				target.setDiscounted(true);
				target.setOriginalPrice(pricingService.getDisplayAmount(price.getOriginalPrice(), store));
			}
			
			//availability
			for(ProductAvailability availability : source.getAvailabilities()) {
				if(availability.getRegion().equals(Constants.ALL_REGIONS)) {//TODO REL 2.1 accept a region
					target.setQuantity(availability.getProductQuantity());
					target.setQuantityOrderMaximum(availability.getProductQuantityOrderMax());
					target.setQuantityOrderMinimum(availability.getProductQuantityOrderMin());
					Set<ProductPrice> prices = availability.getPrices();
					if(prices != null) {
						for(ProductPrice productPrice : prices) {
							
						}
					}
				}
			}
			
			
			return target;
		
		} catch (Exception e) {
			throw new ConversionException(e);
		}
	}




	@Override
	protected ReadableProduct createTarget() {
		// TODO Auto-generated method stub
		return null;
	}

}
