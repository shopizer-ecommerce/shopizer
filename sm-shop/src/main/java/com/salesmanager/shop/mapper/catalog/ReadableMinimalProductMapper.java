package com.salesmanager.shop.mapper.catalog;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.jsoup.helper.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.salesmanager.core.business.exception.ServiceException;
import com.salesmanager.core.business.services.catalog.pricing.PricingService;
import com.salesmanager.core.model.catalog.product.Product;
import com.salesmanager.core.model.catalog.product.description.ProductDescription;
import com.salesmanager.core.model.catalog.product.image.ProductImage;
import com.salesmanager.core.model.catalog.product.price.FinalPrice;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.shop.mapper.Mapper;
import com.salesmanager.shop.model.catalog.product.ReadableImage;
import com.salesmanager.shop.model.catalog.product.ReadableMinimalProduct;
import com.salesmanager.shop.model.catalog.product.product.ProductSpecification;
import com.salesmanager.shop.model.entity.ReadableDescription;
import com.salesmanager.shop.store.api.exception.ConversionRuntimeException;
import com.salesmanager.shop.utils.DateUtil;
import com.salesmanager.shop.utils.ImageFilePath;

@Component
public class ReadableMinimalProductMapper implements Mapper<Product, ReadableMinimalProduct> {

	@Autowired
	private PricingService pricingService;

	
	@Autowired
	@Qualifier("img")
	private ImageFilePath imageUtils;

	@Override
	public ReadableMinimalProduct convert(Product source, MerchantStore store, Language language) {
		// TODO Auto-generated method stub
		ReadableMinimalProduct minimal = new ReadableMinimalProduct();
		return this.merge(source, minimal, store, language);
	}

	@Override
	public ReadableMinimalProduct merge(Product source, ReadableMinimalProduct destination, MerchantStore store,
			Language language) {
		Validate.notNull(source, "Product cannot be null");
		Validate.notNull(destination, "ReadableMinimalProduct cannot be null");


		for (ProductDescription desc : source.getDescriptions()) {
			if (language != null && desc.getLanguage() != null
					&& desc.getLanguage().getId().intValue() == language.getId().intValue()) {
				destination.setDescription(this.description(desc));
				break;
			}
		}
		
		destination.setId(source.getId());
		destination.setAvailable(source.isAvailable());
		destination.setProductShipeable(source.isProductShipeable());
		
		ProductSpecification specifications = new ProductSpecification();
		specifications.setHeight(source.getProductHeight());
		specifications.setLength(source.getProductLength());
		specifications.setWeight(source.getProductWeight());
		specifications.setWidth(source.getProductWidth());
		destination.setProductSpecifications(specifications);
		
		destination.setPreOrder(source.isPreOrder());
		destination.setRefSku(source.getRefSku());
		destination.setSortOrder(source.getSortOrder());
		destination.setSku(source.getSku());
		
		if(source.getDateAvailable() != null) {
			destination.setDateAvailable(DateUtil.formatDate(source.getDateAvailable()));
		}
		
		if(source.getProductReviewAvg()!=null) {
			double avg = source.getProductReviewAvg().doubleValue();
			double rating = Math.round(avg * 2) / 2.0f;
			destination.setRating(rating);
		}
		
		destination.setProductVirtual(source.getProductVirtual());
		if(source.getProductReviewCount()!=null) {
			destination.setRatingCount(source.getProductReviewCount().intValue());
		}

		//price

		try {
			FinalPrice price = pricingService.calculateProductPrice(source);
			if(price != null) {

				destination.setFinalPrice(pricingService.getDisplayAmount(price.getFinalPrice(), store));
				destination.setPrice(price.getFinalPrice());
				destination.setOriginalPrice(pricingService.getDisplayAmount(price.getOriginalPrice(), store));
						
			}
		} catch (ServiceException e) {
			throw new ConversionRuntimeException("An error occured during price calculation", e);
		}
		

		
		//image
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
					destination.setImage(prdImage);
				}
				
				imageList.add(prdImage);
			}
			destination
			.setImages(imageList);
		}
		

		return null;
	}

	private ReadableDescription description(ProductDescription description) {
		ReadableDescription desc = new ReadableDescription();
		desc.setDescription(description.getDescription());
		desc.setName(description.getName());
		desc.setId(description.getId());
		desc.setLanguage(description.getLanguage().getCode());
		return desc;
	}

}
