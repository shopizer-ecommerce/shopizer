package com.salesmanager.shop.mapper.catalog;

import java.util.Set;

import org.jsoup.helper.Validate;
import org.springframework.stereotype.Component;

import com.salesmanager.core.model.catalog.product.attribute.ProductOption;
import com.salesmanager.core.model.catalog.product.attribute.ProductOptionDescription;
import com.salesmanager.core.model.catalog.product.attribute.ProductOptionValue;
import com.salesmanager.core.model.catalog.product.attribute.ProductOptionValueDescription;
import com.salesmanager.core.model.catalog.product.variation.ProductVariation;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.shop.mapper.Mapper;
import com.salesmanager.shop.model.catalog.product.attribute.ReadableProductOption;
import com.salesmanager.shop.model.catalog.product.attribute.ReadableProductOptionValue;
import com.salesmanager.shop.model.catalog.product.variation.ReadableProductVariation;

@Component
public class ReadableProductVariationMapper implements Mapper<ProductVariation, ReadableProductVariation> {

	
	@Override
	public ReadableProductVariation convert(ProductVariation source, MerchantStore store, Language language) {
		ReadableProductVariation variation = new ReadableProductVariation();
		return merge(source, variation, store, language);
	}

	@Override
	public ReadableProductVariation merge(ProductVariation source, ReadableProductVariation destination,
										  MerchantStore store, Language language) {
		
		Validate.notNull(source,"ProductVariation must not be null");
		Validate.notNull(destination,"ReadableProductVariation must not be null");
		
		
		destination.setId(source.getId());
		destination.setCode(source.getCode());

		
		destination.setOption(this.option(source.getProductOption(), language));
		destination.setOptionValue(this.optionValue(source.getProductOptionValue(), store, language));

		return destination;
	}
	
	private ReadableProductOption option (ProductOption option, Language lang) {

		ReadableProductOption opt = new ReadableProductOption();
		opt.setCode(option.getCode());
		opt.setId(option.getId());
		opt.setLang(lang.getCode());
		opt.setReadOnly(option.isReadOnly());
		opt.setType(option.getProductOptionType());
		ProductOptionDescription desc = this.optionDescription(option.getDescriptions(), lang);
		if(desc != null) {
			opt.setName(desc.getName());
		}

		return opt;
	}
	
	private ReadableProductOptionValue optionValue (ProductOptionValue val, MerchantStore store, Language language) {

			ReadableProductOptionValue value = new ReadableProductOptionValue();
			value.setCode(val.getCode());
			value.setId(val.getId());
			ProductOptionValueDescription desc = optionValueDescription(val.getDescriptions(), language);
			if(desc!=null) {
				value.setName(desc.getName());
			}
			return value;

	}
	
	private ProductOptionDescription optionDescription(Set<ProductOptionDescription> descriptions, Language lang) {
		return descriptions.stream().filter(desc-> desc.getLanguage().getCode().equals(lang.getCode())).findAny().orElse(null);
	}
	
	private ProductOptionValueDescription optionValueDescription(Set<ProductOptionValueDescription> descriptions, Language lang) {
		return descriptions.stream().filter(desc-> desc.getLanguage().getCode().equals(lang.getCode())).findAny().orElse(null);
	}

}
