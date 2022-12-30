package com.salesmanager.shop.mapper.catalog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.salesmanager.core.model.catalog.catalog.CatalogCategoryEntry;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.shop.mapper.Mapper;
import com.salesmanager.shop.model.catalog.catalog.ReadableCatalogCategoryEntry;
import com.salesmanager.shop.model.catalog.category.ReadableCategory;
import com.salesmanager.shop.store.api.exception.ConversionRuntimeException;
import com.salesmanager.shop.utils.ImageFilePath;

import java.util.Optional;

@Component
public class ReadableCatalogCategoryEntryMapper implements Mapper<CatalogCategoryEntry, ReadableCatalogCategoryEntry> {
	
	
	@Autowired
	private ReadableCategoryMapper readableCategoryMapper;
	
	@Autowired
	@Qualifier("img")
	private ImageFilePath imageUtils;

	@Override
	public ReadableCatalogCategoryEntry convert(CatalogCategoryEntry source, MerchantStore store, Language language) {
		ReadableCatalogCategoryEntry destination = new ReadableCatalogCategoryEntry();
		return merge(source, destination, store, language);
	}

	@Override
	public ReadableCatalogCategoryEntry merge(CatalogCategoryEntry source, ReadableCatalogCategoryEntry destination, MerchantStore store,
											  Language language) {
		ReadableCatalogCategoryEntry convertedDestination = Optional.ofNullable(destination)
				.orElse(new ReadableCatalogCategoryEntry());
		
		try {
			//ReadableProductPopulator readableProductPopulator = new ReadableProductPopulator();
			//readableProductPopulator.setimageUtils(imageUtils);
			//readableProductPopulator.setPricingService(pricingService);
			
			//ReadableProduct readableProduct = readableProductPopulator.populate(source.getProduct(), store, language);
			ReadableCategory readableCategory = readableCategoryMapper.convert(source.getCategory(), store, language);

			convertedDestination.setCatalog(source.getCatalog().getCode());

			convertedDestination.setId(source.getId());
			convertedDestination.setVisible(source.isVisible());
			convertedDestination.setCategory(readableCategory);
			//destination.setProduct(readableProduct);
			return convertedDestination;
		} catch (Exception e) {
			throw new ConversionRuntimeException("Error while creating ReadableCatalogEntry", e);
		}
	}
}
