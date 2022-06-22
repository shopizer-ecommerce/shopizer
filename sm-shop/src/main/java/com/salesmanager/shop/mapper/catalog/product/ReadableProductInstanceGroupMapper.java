package com.salesmanager.shop.mapper.catalog.product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.inject.Inject;

import org.jsoup.helper.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.salesmanager.core.model.catalog.product.instance.ProductInstance;
import com.salesmanager.core.model.catalog.product.instance.ProductInstanceGroup;
import com.salesmanager.core.model.catalog.product.instance.ProductInstanceImage;
import com.salesmanager.core.model.content.FileContentType;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.shop.mapper.Mapper;
import com.salesmanager.shop.model.catalog.product.ReadableImage;
import com.salesmanager.shop.model.catalog.product.product.instance.ReadableProductInstance;
import com.salesmanager.shop.model.catalog.product.product.instanceGroup.ReadableProductInstanceGroup;
import com.salesmanager.shop.utils.ImageFilePath;

@Component
public class ReadableProductInstanceGroupMapper implements Mapper<ProductInstanceGroup, ReadableProductInstanceGroup> {

	
	@Autowired
	private ReadableProductInstanceMapper readableProductInstanceMapper;
	
	@Inject
	@Qualifier("img")
	private ImageFilePath imageUtils;
	
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
		
		destination.setId(source.getId());
		
		Set<ProductInstance> instances = source.getProductInstances();
		destination.setProductInstances(instances.stream().map(i -> this.instance(i, store, language)).collect(Collectors.toList()));
		
		//image id should be unique in the list
		
		Map<Long,ReadableImage> finalList = new HashMap<Long, ReadableImage>();
		
		List<ReadableImage> originalList = source.getImages().stream()
				.map(i -> this.image(finalList, i, store, language))
				.collect(Collectors.toList());
		

		destination.setImages(new ArrayList<ReadableImage>(finalList.values()));
		
		return destination;
	}
	
	private ReadableProductInstance instance(ProductInstance instance, MerchantStore store, Language language) {
		
		return readableProductInstanceMapper.convert(instance, store, language);
	}
	
	private ReadableImage image(Map<Long,ReadableImage> finalList , ProductInstanceImage img, MerchantStore store, Language language) {
		ReadableImage readable = null;
		if(!finalList.containsKey(img.getId())) {
			readable = new ReadableImage();
			readable.setId(img.getId());
			readable.setImageName(img.getProductImage());
			readable.setImageUrl(imageUtils.buildCustomTypeImageUtils(store, img.getProductImage(), FileContentType.INSTANCE));
			readable.setDefaultImage(img.isDefaultImage());
			finalList.put(img.getId(), readable);
			
		}
		return readable;

	}

}


