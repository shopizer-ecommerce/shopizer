package com.salesmanager.shop.mapper.catalog;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.Validate;
import org.springframework.stereotype.Component;

import com.salesmanager.core.model.catalog.product.type.ProductType;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.shop.mapper.Mapper;
import com.salesmanager.shop.model.catalog.product.type.ProductTypeDescription;
import com.salesmanager.shop.model.catalog.product.type.ReadableProductType;
import com.salesmanager.shop.model.catalog.product.type.ReadableProductTypeFull;

@Component
public class ReadableProductTypeMapper implements Mapper<ProductType, ReadableProductType> {

	@Override
	public ReadableProductType convert(ProductType source, MerchantStore store, Language language) {
		ReadableProductType type = new ReadableProductType();
		return this.merge(source, type, store, language);
	}

	@Override
	public ReadableProductType merge(ProductType source, ReadableProductType destination, MerchantStore store,
									 Language language) {
		Validate.notNull(source, "ProductType cannot be null");
		Validate.notNull(destination, "ReadableProductType cannot be null");
		return type(source, language);
	}
	
	private ReadableProductType type (ProductType type, Language language) {
		ReadableProductType readableType = null;


		if(language != null) {
			readableType = new ReadableProductType();
			if(!CollectionUtils.isEmpty(type.getDescriptions())) {
				Optional<ProductTypeDescription> desc = type.getDescriptions().stream().filter(t -> t.getLanguage().getCode().equals(language.getCode()))
				.map(d -> typeDescription(d)).findFirst();
				if(desc.isPresent()) {
					readableType.setDescription(desc.get());
				}
			}
		} else {
			
			readableType = new ReadableProductTypeFull();
			List<ProductTypeDescription> descriptions = type.getDescriptions().stream().map(t -> this.typeDescription(t)).collect(Collectors.toList());
			((ReadableProductTypeFull)readableType).setDescriptions(descriptions);
			
		}
		
		readableType.setCode(type.getCode());
		readableType.setId(type.getId());
		readableType.setVisible(type.getVisible() != null && type.getVisible().booleanValue() ? true:false);
		readableType.setAllowAddToCart(type.getAllowAddToCart() != null && type.getAllowAddToCart().booleanValue() ? true:false);
		
		return readableType;
	}
	
	private ProductTypeDescription typeDescription(com.salesmanager.core.model.catalog.product.type.ProductTypeDescription description) {
		ProductTypeDescription desc = new ProductTypeDescription();
		desc.setId(description.getId());
		desc.setName(description.getName());
		desc.setDescription(description.getDescription());
		desc.setLanguage(description.getLanguage().getCode());
		return desc;
	}

}
