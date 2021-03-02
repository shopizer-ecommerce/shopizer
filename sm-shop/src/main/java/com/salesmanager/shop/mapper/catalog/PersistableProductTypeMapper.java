package com.salesmanager.shop.mapper.catalog;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.salesmanager.core.business.exception.ServiceException;
import com.salesmanager.core.business.services.reference.language.LanguageService;
import com.salesmanager.core.model.catalog.product.type.ProductType;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.shop.mapper.Mapper;
import com.salesmanager.shop.model.catalog.product.type.PersistableProductType;
import com.salesmanager.shop.model.catalog.product.type.ProductTypeDescription;
import com.salesmanager.shop.store.api.exception.ConversionRuntimeException;


@Component
public class PersistableProductTypeMapper implements Mapper<PersistableProductType, ProductType> {
	
	@Autowired
	private LanguageService languageService;

	@Override
	public ProductType convert(PersistableProductType source, MerchantStore store, Language language) {
		ProductType type = new ProductType();
		return this.merge(source, type, store, language);
	}

	@Override
	public ProductType merge(PersistableProductType source, ProductType destination, MerchantStore store,
							 Language language) {
		Validate.notNull(destination, "ReadableProductType cannot be null");
		return this.type(source);
	}
	
	private ProductType type (PersistableProductType type) {
		ProductType typeModel = new ProductType();
		typeModel.setCode(type.getCode());
		typeModel.setId(type.getId());

		if(!CollectionUtils.isEmpty(type.getDescriptions())) {
			List<com.salesmanager.core.model.catalog.product.type.ProductTypeDescription> descriptions = type.getDescriptions().stream().map(d -> {
				try {
					return this.typeDescription(d, typeModel);
				} catch (ServiceException e) {
					throw new ConversionRuntimeException("Exception occured while converting type", e);
				}
			}).collect(Collectors.toList());
			typeModel.setDescriptions(new HashSet<com.salesmanager.core.model.catalog.product.type.ProductTypeDescription>(descriptions));
		}
		return typeModel;
	}
	
	private com.salesmanager.core.model.catalog.product.type.ProductTypeDescription typeDescription(ProductTypeDescription description, ProductType typeModel) throws ServiceException {
		com.salesmanager.core.model.catalog.product.type.ProductTypeDescription desc = new com.salesmanager.core.model.catalog.product.type.ProductTypeDescription();
		desc.setId(description.getId());
		desc.setName(description.getName());
		desc.setDescription(description.getDescription());
		desc.setLanguage(languageService.getByCode(description.getLanguage()));
		desc.setProductType(typeModel);
		return desc;
	}

}
