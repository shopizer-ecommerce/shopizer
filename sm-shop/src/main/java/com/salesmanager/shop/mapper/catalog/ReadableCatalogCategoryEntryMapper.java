package com.salesmanager.shop.mapper.catalog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.salesmanager.core.business.services.catalog.product.PricingService;
import com.salesmanager.core.model.catalog.catalog.CatalogCategoryEntry;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.shop.mapper.Mapper;
import com.salesmanager.shop.model.catalog.catalog.ReadableCatalogCategoryEntry;
import com.salesmanager.shop.model.catalog.category.ReadableCategory;
import com.salesmanager.shop.populator.catalog.ReadableProductPopulator;
import com.salesmanager.shop.store.api.exception.ConversionRuntimeException;
import com.salesmanager.shop.utils.ImageFilePath;

@Component
public class ReadableCatalogCategoryEntryMapper implements Mapper<CatalogCategoryEntry, ReadableCatalogCategoryEntry> {
	
	
	@Autowired
	private ReadableCategoryMapper readableCategoryMapper;
	
	//@Autowired
	//private PricingService pricingService;
	
	@Autowired
	@Qualifier("img")
	private ImageFilePath imageUtils;

	@Override
	public ReadableCatalogCategoryEntry convert(CatalogCategoryEntry source, MerchantStore store, Language language) {
		ReadableCatalogCategoryEntry destination = new ReadableCatalogCategoryEntry();
		return convert(source, destination, store, language);
	}

	@Override
	public ReadableCatalogCategoryEntry convert(CatalogCategoryEntry source, ReadableCatalogCategoryEntry destination, MerchantStore store,
			Language language) {
		if(destination == null) {
			destination = new ReadableCatalogCategoryEntry();
		}
		
		try {
			
			//ReadableProductPopulator readableProductPopulator = new ReadableProductPopulator();
			//readableProductPopulator.setimageUtils(imageUtils);
			//readableProductPopulator.setPricingService(pricingService);
			
			//ReadableProduct readableProduct = readableProductPopulator.populate(source.getProduct(), store, language);
			ReadableCategory readableCategory = readableCategoryMapper.convert(source.getCategory(), store, language);
			
			destination.setCatalog(source.getCatalog().getCode());
			
			destination.setId(source.getId());
			destination.setVisible(source.isVisible());
			destination.setCategory(readableCategory);
			//destination.setProduct(readableProduct);
			return destination;
			
		} catch (Exception e) {
			throw new ConversionRuntimeException("Error while creating ReadableCatalogEntry", e);
		}
		

	}

}
