package com.salesmanager.shop.mapper.catalog.product;

import java.util.HashSet;
import java.util.List;

import org.jsoup.helper.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.salesmanager.core.business.services.catalog.product.variant.ProductVariantImageService;
import com.salesmanager.core.business.services.catalog.product.variant.ProductVariantService;
import com.salesmanager.core.model.catalog.product.variant.ProductVariantImage;
import com.salesmanager.core.model.catalog.product.variant.ProductVariant;
import com.salesmanager.core.model.catalog.product.variant.ProductVariantGroup;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.shop.mapper.Mapper;
import com.salesmanager.shop.model.catalog.product.product.variantGroup.PersistableProductVariantGroup;

@Component
public class PersistableProductVariantGroupMapper implements Mapper<PersistableProductVariantGroup, ProductVariantGroup>{

	
	@Autowired
	private ProductVariantService productVariantService;
	
	@Autowired
	private ProductVariantImageService productVariantImageService;
	
	@Override
	public ProductVariantGroup convert(PersistableProductVariantGroup source, MerchantStore store,
			Language language) {
		Validate.notNull(source, "PersistableproductVariantGroup cannot be null");
		Validate.notNull(store, "MerchantStore cannot be null");
		Validate.notNull(language, "Language cannot be null");
		return this.merge(source, new ProductVariantGroup(), store, language);
	}

	@Override
	public ProductVariantGroup merge(PersistableProductVariantGroup source, ProductVariantGroup destination,
			MerchantStore store, Language language) {
		
		Validate.notNull(source, "PersistableproductVariantGroup cannot be null");
		Validate.notNull(store, "MerchantStore cannot be null");
		Validate.notNull(language, "Language cannot be null");
		Validate.notNull(source.getproductVariants(), "Product instances cannot be null");	
		
		if(destination == null) {
			destination = new ProductVariantGroup();
		}
		
		destination.setId(source.getId());
		
		
		List<ProductVariant> productVariants = productVariantService.getByIds(source.getproductVariants(), store);
		
		for(ProductVariant p : productVariants) {
			p.setProductVariantGroup(destination);
		}

		//images are not managed from this object
		if(source.getId() != null) {
			List<ProductVariantImage> images = productVariantImageService.listByProductVariantGroup(source.getId(), store);
			destination.setImages(images);
		}
		destination.setMerchantStore(store);
		destination.setProductVariants(new HashSet<ProductVariant>(productVariants));
		return destination;
	}
	
	private ProductVariant instance(ProductVariant instance, ProductVariantGroup group, MerchantStore store) {
		
		instance.setProductVariantGroup(group);
		return instance;

	}

}
