package com.salesmanager.shop.populator.catalog;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang.Validate;
import org.apache.commons.lang3.StringUtils;

import com.salesmanager.core.business.constants.Constants;
import com.salesmanager.core.business.exception.ConversionException;
import com.salesmanager.core.business.services.catalog.product.PricingService;
import com.salesmanager.core.business.utils.AbstractDataPopulator;
import com.salesmanager.core.model.catalog.category.Category;
import com.salesmanager.core.model.catalog.product.Product;
import com.salesmanager.core.model.catalog.product.availability.ProductAvailability;
import com.salesmanager.core.model.catalog.product.description.ProductDescription;
import com.salesmanager.core.model.catalog.product.image.ProductImage;
import com.salesmanager.core.model.catalog.product.manufacturer.ManufacturerDescription;
import com.salesmanager.core.model.catalog.product.price.FinalPrice;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.shop.model.catalog.category.ReadableCategory;
import com.salesmanager.shop.model.catalog.manufacturer.ReadableManufacturer;
import com.salesmanager.shop.model.catalog.product.ReadableImage;
import com.salesmanager.shop.model.catalog.product.ReadableProduct;
import com.salesmanager.shop.model.catalog.product.RentalOwner;
import com.salesmanager.shop.utils.DateUtil;
import com.salesmanager.shop.utils.ImageFilePath;



public class ReadableProductPopulator extends
		AbstractDataPopulator<Product, ReadableProduct> {
	
	private PricingService pricingService;
	
	private ImageFilePath imageUtils;

	public ImageFilePath getimageUtils() {
		return imageUtils;
	}




	public void setimageUtils(ImageFilePath imageUtils) {
		this.imageUtils = imageUtils;
	}




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
		Validate.notNull(imageUtils, "Requires to set imageUtils");
		Validate.notNull(language, "Language cannot be null");
		
		try {
			

			ProductDescription description = source.getProductDescription();
			
			Set<ProductDescription> descriptions = source.getDescriptions();
			for(ProductDescription desc : descriptions) {
				
				if(desc.getLanguage()!=null && desc.getLanguage().getId().intValue() == language.getId().intValue()) {
					description = desc;
					break;
				}
				
			}

	
			target.setId(source.getId());
			target.setAvailable(source.isAvailable());
			target.setProductHeight(source.getProductHeight());
			target.setProductLength(source.getProductLength());
			target.setProductWeight(source.getProductWeight());
			target.setProductWidth(source.getProductWidth());
			target.setPreOrder(source.isPreOrder());
			target.setRefSku(source.getRefSku());
			target.setSortOrder(source.getSortOrder());
			
			
			target.setCondition(source.getCondition());
			
			
			//RENTAL
			if(source.getRentalDuration()!=null) {
				target.setRentalDuration(source.getRentalDuration());
			}
			if(source.getRentalPeriod()!=null) {
				target.setRentalPeriod(source.getRentalPeriod());
			}
			target.setRentalStatus(source.getRentalStatus());
			
			/**
			 * END RENTAL
			 */
			
			if(source.getOwner() != null) {
				RentalOwner owner = new RentalOwner();
				owner.setId(source.getOwner().getId());
				owner.setEmailAddress(source.getOwner().getEmailAddress());
				owner.setFirstName(source.getOwner().getBilling().getFirstName());
				owner.setLastName(source.getOwner().getBilling().getLastName());
				com.salesmanager.shop.model.customer.Address address = new com.salesmanager.shop.model.customer.Address();
				address.setAddress(source.getOwner().getBilling().getAddress());
				address.setBillingAddress(true);
				address.setCity(source.getOwner().getBilling().getCity());
				address.setCompany(source.getOwner().getBilling().getCompany());
				address.setCountry(source.getOwner().getBilling().getCountry().getIsoCode());
				address.setZone(source.getOwner().getBilling().getZone().getCode());
				address.setLatitude(source.getOwner().getBilling().getLatitude());
				address.setLongitude(source.getOwner().getBilling().getLongitude());
				address.setPhone(source.getOwner().getBilling().getTelephone());
				address.setPostalCode(source.getOwner().getBilling().getPostalCode());
				owner.setAddress(address);
				target.setOwner(owner);
			}
			
			
			if(source.getDateAvailable() != null) {
				target.setDateAvailable(DateUtil.formatDate(source.getDateAvailable()));
			}
			
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
				com.salesmanager.shop.model.catalog.product.ProductDescription tragetDescription = new com.salesmanager.shop.model.catalog.product.ProductDescription();
				tragetDescription.setFriendlyUrl(description.getSeUrl());
				tragetDescription.setName(description.getName());
				tragetDescription.setId(description.getId());
				if(!StringUtils.isBlank(description.getMetatagTitle())) {
					tragetDescription.setTitle(description.getMetatagTitle());
				} else {
					tragetDescription.setTitle(description.getName());
				}
				tragetDescription.setMetaDescription(description.getMetatagDescription());
				tragetDescription.setDescription(description.getDescription());
				tragetDescription.setHighlights(description.getProductHighlight());
				tragetDescription.setLanguage(description.getLanguage().getCode());
				target.setDescription(tragetDescription);
				
			}
			
			if(source.getManufacturer()!=null) {
				ManufacturerDescription manufacturer = source.getManufacturer().getDescriptions().iterator().next(); 
				ReadableManufacturer manufacturerEntity = new ReadableManufacturer();
				com.salesmanager.shop.model.catalog.manufacturer.ManufacturerDescription d = new com.salesmanager.shop.model.catalog.manufacturer.ManufacturerDescription(); 
				d.setName(manufacturer.getName());
				manufacturerEntity.setDescription(d);
				manufacturerEntity.setId(source.getManufacturer().getId());
				manufacturerEntity.setOrder(source.getManufacturer().getOrder());
				manufacturerEntity.setCode(source.getManufacturer().getCode());
				target.setManufacturer(manufacturerEntity);
			}
			
			Set<ProductImage> images = source.getImages();
			if(images!=null && images.size()>0) {
				List<ReadableImage> imageList = new ArrayList<ReadableImage>();
				
				String contextPath = imageUtils.getContextPath();
				
				for(ProductImage img : images) {
					ReadableImage prdImage = new ReadableImage();
					prdImage.setImageName(img.getProductImage());
					prdImage.setDefaultImage(img.isDefaultImage());

					StringBuilder imgPath = new StringBuilder();
					imgPath.append(contextPath).append(imageUtils.buildProductImageUtils(store, source.getSku(), img.getProductImage()));

					prdImage.setImageUrl(imgPath.toString());
					prdImage.setId(img.getId());
					prdImage.setImageType(img.getImageType());
					if(img.getProductImageUrl()!=null){
						prdImage.setExternalUrl(img.getProductImageUrl());
					}
					if(img.getImageType()==1 && img.getProductImageUrl()!=null) {//video
						prdImage.setVideoUrl(img.getProductImageUrl());
					}
					
					if(prdImage.isDefaultImage()) {
						target.setImage(prdImage);
					}
					
					imageList.add(prdImage);
				}
				target
				.setImages(imageList);
			}
			
			if(!CollectionUtils.isEmpty(source.getCategories())) {
				
				ReadableCategoryPopulator categoryPopulator = new ReadableCategoryPopulator();
				List<ReadableCategory> categoryList = new ArrayList<ReadableCategory>();
				
				for(Category category : source.getCategories()) {
					
					ReadableCategory readableCategory = new ReadableCategory();
					categoryPopulator.populate(category, readableCategory, store, language);
					categoryList.add(readableCategory);
					
				}
				
				target.setCategories(categoryList);
				
			}
			

			
			//remove products from invisible category -> set visible = false
/*			Set<Category> categories = source.getCategories();
			boolean isVisible = true;
			if(!CollectionUtils.isEmpty(categories)) {
				for(Category c : categories) {
					if(c.isVisible()) {
						isVisible = true;
						break;
					} else {
						isVisible = false;
					}
				}
			}*/
			
			//target.setVisible(isVisible);
			
	
			target.setSku(source.getSku());
	
			FinalPrice price = pricingService.calculateProductPrice(source);

			target.setFinalPrice(pricingService.getDisplayAmount(price.getFinalPrice(), store));
			target.setPrice(price.getFinalPrice());
			target.setOriginalPrice(pricingService.getDisplayAmount(price.getOriginalPrice(), store));
	
			if(price.isDiscounted()) {
				target.setDiscounted(true);
			}
			
			//availability
			for(ProductAvailability availability : source.getAvailabilities()) {
				if(availability.getRegion().equals(Constants.ALL_REGIONS)) {//TODO REL 2.1 accept a region
					target.setQuantity(availability.getProductQuantity());
					target.setQuantityOrderMaximum(availability.getProductQuantityOrderMax());
					target.setQuantityOrderMinimum(availability.getProductQuantityOrderMin());
					if(availability.getProductQuantity().intValue() > 0 && target.isAvailable()) {
							target.setCanBePurchased(true);
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
