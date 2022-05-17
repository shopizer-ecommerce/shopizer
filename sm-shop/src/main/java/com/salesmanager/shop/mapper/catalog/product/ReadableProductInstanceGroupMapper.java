package com.salesmanager.shop.mapper.catalog.product;

import java.util.Set;
import java.util.stream.Collectors;

import org.jsoup.helper.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.salesmanager.core.model.catalog.product.instance.ProductInstance;
import com.salesmanager.core.model.catalog.product.instance.ProductInstanceGroup;
import com.salesmanager.core.model.catalog.product.instance.ProductInstanceImage;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.shop.mapper.Mapper;
import com.salesmanager.shop.model.catalog.product.ReadableImage;
import com.salesmanager.shop.model.catalog.product.product.instance.ReadableProductInstance;
import com.salesmanager.shop.model.catalog.product.product.instanceGroup.ReadableProductInstanceGroup;

@Component
public class ReadableProductInstanceGroupMapper implements Mapper<ProductInstanceGroup, ReadableProductInstanceGroup> {

	
	@Autowired
	private ReadableProductInstanceMapper readableProductInstanceMapper;
	
	@Override
	public ReadableProductInstanceGroup convert(ProductInstanceGroup source, MerchantStore store, Language language) {
		Validate.notNull(source, "ProductInstanceGroup cannot be null");
		Validate.notNull(store, "MerchantStore cannot be null");
		Validate.notNull(language, "Language cannot be null");
		return this.merge(source, new ReadableProductInstanceGroup(), store, language);
	}

	@Override
	public ReadableProductInstanceGroup merge(ProductInstanceGroup source, ReadableProductInstanceGroup destination,
			MerchantStore store, Language language) {
		Validate.notNull(source, "ProductInstanceGroup cannot be null");
		Validate.notNull(store, "MerchantStore cannot be null");
		Validate.notNull(language, "Language cannot be null");
		if(destination == null) {
			destination = new ReadableProductInstanceGroup();
		}
		
		Set<ProductInstance> instances = source.getProductInstances();
		destination.setProductInstances(instances.stream().map(i -> this.instance(i, store, language)).collect(Collectors.toList()));

		
		//transform images
		
		
		return null;
	}
	
	private ReadableProductInstance instance(ProductInstance instance, MerchantStore store, Language language) {
		
		return readableProductInstanceMapper.convert(instance, store, language);
	}
	
	private ReadableImage image(ProductInstanceImage img, MerchantStore store, Language language) {
		
		ReadableImage readable = new ReadableImage();
		readable.setId(img.getId());
		readable.setImageName(img.getProductImage());
		
		
		return readable;
	}

}
