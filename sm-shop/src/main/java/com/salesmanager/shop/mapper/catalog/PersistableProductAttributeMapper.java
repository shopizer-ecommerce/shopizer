package com.salesmanager.shop.mapper.catalog;

import javax.inject.Inject;

import org.apache.commons.lang.Validate;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.salesmanager.core.business.services.catalog.product.ProductService;
import com.salesmanager.core.business.services.catalog.product.attribute.ProductOptionService;
import com.salesmanager.core.business.services.catalog.product.attribute.ProductOptionValueService;
import com.salesmanager.core.model.catalog.product.Product;
import com.salesmanager.core.model.catalog.product.attribute.ProductAttribute;
import com.salesmanager.core.model.catalog.product.attribute.ProductOption;
import com.salesmanager.core.model.catalog.product.attribute.ProductOptionValue;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.shop.mapper.Mapper;
import com.salesmanager.shop.model.catalog.product.attribute.PersistableProductAttribute;
import com.salesmanager.shop.store.api.exception.ConversionRuntimeException;

@Component
public class PersistableProductAttributeMapper implements Mapper<PersistableProductAttribute, ProductAttribute> {

	@Inject
	private ProductOptionService productOptionService;
	@Inject
	private ProductOptionValueService productOptionValueService;
	@Inject
	private ProductService productService;
	
	@Override
	public ProductAttribute convert(PersistableProductAttribute source, MerchantStore store, Language language) {
		ProductAttribute attribute = new ProductAttribute();
		return convert(source,attribute,store,language);
	}

	@Override
	public ProductAttribute convert(PersistableProductAttribute source, ProductAttribute destination,
			MerchantStore store, Language language) {

		
		ProductOption productOption = null;
		
		if(!StringUtils.isBlank(source.getOption().getCode())) {
			productOption = productOptionService.getByCode(store, source.getOption().getCode());
		} else {
			Validate.notNull(source.getOption().getId(),"Product option id is null");
			productOption = productOptionService.getById(source.getOption().getId());
		}

		if(productOption==null) {
			throw new ConversionRuntimeException("Product option id " + source.getOption().getId() + " does not exist");
		}
		
		ProductOptionValue productOptionValue = null;
		
		if(!StringUtils.isBlank(source.getOptionValue().getCode())) {
			productOptionValue = productOptionValueService.getByCode(store, source.getOptionValue().getCode());
		} else {
			productOptionValue = productOptionValueService.getById(source.getOptionValue().getId());
		}
		
		if(productOptionValue==null) {
			throw new ConversionRuntimeException("Product option value id " + source.getOptionValue().getId() + " does not exist");
		}
		
		if(productOption.getMerchantStore().getId().intValue()!=store.getId().intValue()) {
			throw new ConversionRuntimeException("Invalid product option id ");
		}
		
		if(productOptionValue.getMerchantStore().getId().intValue()!=store.getId().intValue()) {
			throw new ConversionRuntimeException("Invalid product option value id ");
		}
		
		if(source.getProductId() != null && source.getProductId().longValue() >0 ) {
			Product p = productService.getById(source.getProductId());
			if(p == null) {
				throw new ConversionRuntimeException("Invalid product id ");
			}
			destination.setProduct(p);
		}

		
		if(destination.getId()!=null && destination.getId().longValue()>0) {
			destination.setId(destination.getId());
		} else {
			destination.setId(null);
		}
		destination.setProductOption(productOption);
		destination.setProductOptionValue(productOptionValue);
		destination.setProductAttributePrice(source.getProductAttributePrice());
		destination.setProductAttributeWeight(source.getProductAttributeWeight());
		destination.setProductAttributePrice(source.getProductAttributePrice());
		destination.setAttributeDisplayOnly(source.isAttributeDisplayOnly());

		
		return destination;
	}

}
