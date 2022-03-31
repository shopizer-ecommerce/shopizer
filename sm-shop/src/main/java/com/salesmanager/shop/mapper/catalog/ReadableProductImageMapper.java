package com.salesmanager.shop.mapper.catalog;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.salesmanager.core.model.catalog.product.image.ProductImage;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.shop.mapper.Mapper;
import com.salesmanager.shop.model.catalog.product.ReadableImage;
import com.salesmanager.shop.utils.ImageFilePath;

@Component
public class ReadableProductImageMapper implements Mapper<ProductImage, ReadableImage> {

	
	@Inject
	@Qualifier("img")
	private ImageFilePath imageUtils;

	@Override
	public ReadableImage convert(ProductImage source, MerchantStore store, Language language) {
		ReadableImage destination = new ReadableImage();
		return merge(source, destination, store, language);
	}

	@Override
	public ReadableImage merge(ProductImage source, ReadableImage destination, MerchantStore store, Language language) {
		
		String contextPath = imageUtils.getContextPath();
		
		destination.setImageName(source.getProductImage());
		destination.setDefaultImage(source.isDefaultImage());
		destination.setOrder(source.getSortOrder() != null ? source.getSortOrder().intValue() : 0);

		if (source.getImageType() == 1 && source.getProductImageUrl()!=null) {
			destination.setImageUrl(source.getProductImageUrl());
		} else {
			StringBuilder imgPath = new StringBuilder();
			imgPath.append(contextPath).append(imageUtils.buildProductImageUtils(store, source.getProduct().getSku(), source.getProductImage()));
			destination.setImageUrl(imgPath.toString());
		}
		destination.setId(source.getId());
		destination.setImageType(source.getImageType());
		if(source.getProductImageUrl()!=null){
			destination.setExternalUrl(source.getProductImageUrl());
		}
		if(source.getImageType()==1 && source.getProductImageUrl()!=null) {//video
			destination.setVideoUrl(source.getProductImageUrl());
		}
		
		return destination;
	}

}
