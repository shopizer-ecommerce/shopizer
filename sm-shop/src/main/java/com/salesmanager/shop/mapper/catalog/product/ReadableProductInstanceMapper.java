package com.salesmanager.shop.mapper.catalog.product;


import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.inject.Inject;

import org.apache.commons.collections.CollectionUtils;
import org.jsoup.helper.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.salesmanager.core.model.catalog.product.Product;
import com.salesmanager.core.model.catalog.product.instance.ProductInstance;
import com.salesmanager.core.model.catalog.product.instance.ProductInstanceImage;
import com.salesmanager.core.model.content.FileContentType;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.shop.mapper.Mapper;
import com.salesmanager.shop.mapper.catalog.ReadableProductVariationMapper;
import com.salesmanager.shop.mapper.inventory.ReadableInventoryMapper;
import com.salesmanager.shop.model.catalog.product.ReadableImage;
import com.salesmanager.shop.model.catalog.product.inventory.ReadableInventory;
import com.salesmanager.shop.model.catalog.product.product.instance.ReadableProductInstance;
import com.salesmanager.shop.store.api.exception.ResourceNotFoundException;
import com.salesmanager.shop.utils.DateUtil;
import com.salesmanager.shop.utils.ImageFilePath;

@Component
public class ReadableProductInstanceMapper implements Mapper<ProductInstance, ReadableProductInstance> {

	
	@Autowired
	private ReadableProductVariationMapper readableProductVariationMapper;
	
	@Autowired
	private ReadableInventoryMapper readableInventoryMapper;
	
	@Inject
	@Qualifier("img")
	private ImageFilePath imagUtils;

	@Override
	public ReadableProductInstance convert(ProductInstance source, MerchantStore store, Language language) {
		ReadableProductInstance readableProductInstance = new ReadableProductInstance();
		return this.merge(source, readableProductInstance, store, language);
	}

	@Override
	public ReadableProductInstance merge(ProductInstance source, ReadableProductInstance destination,
			MerchantStore store, Language language) {
		
		Validate.notNull(source, "Product instance cannot be null");
		Validate.notNull(source.getProduct(), "Product cannot be null");
		
		if(destination == null) {
			destination = new ReadableProductInstance();
		}
		
		destination.setSortOrder(source.getSortOrder() != null ? source.getSortOrder().intValue():0);
		destination.setAvailable(source.isAvailable());
		destination.setDateAvailable(DateUtil.formatDate(source.getDateAvailable()));
		destination.setId(source.getId());
		destination.setDefaultSelection(source.isDefaultSelection());
		destination.setProductId(source.getProduct().getId());
		destination.setSku(source.getSku());
		destination.setSortOrder(source.getSortOrder());
		destination.setCode(source.getCode());
		
		//get product
		Product baseProduct = source.getProduct();
		if(baseProduct == null) {
			throw new ResourceNotFoundException("Product instances do not include the parent product [" + destination.getSku() + "]");
		}
		
		destination.setProductShipeable(baseProduct.isProductShipeable());
		
		//destination.setStore(null);
		destination.setStore(store.getCode());
		destination.setVariant(readableProductVariationMapper.convert(source.getVariant(), store, language));
		if(source.getVariantValue() != null) {
			destination.setVariantValue(readableProductVariationMapper.convert(source.getVariantValue(), store, language));
		}

		if(source.getProductInstanceGroup() != null) {
			Set<String> nameSet = new HashSet<>();
			List<ReadableImage> instanceImages = source.getProductInstanceGroup().getImages().stream().map(i -> this.image(i, store, language))
					.filter(e -> nameSet.add(e.getImageUrl()))
					.collect(Collectors.toList());
			destination.setImages(instanceImages);
		}
		
		if(!CollectionUtils.isEmpty(source.getAvailabilities())) {
			List<ReadableInventory> inventories = source.getAvailabilities().stream().map(i -> readableInventoryMapper.convert(i, store, language)).collect(Collectors.toList());
			destination.setInventory(inventories);
		}
		
		return destination;
	}
	
	private ReadableImage image(ProductInstanceImage instanceImage, MerchantStore store, Language language) {
		ReadableImage img = new ReadableImage();
		img.setDefaultImage(instanceImage.isDefaultImage());
		img.setId(instanceImage.getId());
		img.setImageName(instanceImage.getProductImage());
		img.setImageUrl(imagUtils.buildCustomTypeImageUtils(store, img.getImageName(), FileContentType.INSTANCE));
		return img;
	}


}
