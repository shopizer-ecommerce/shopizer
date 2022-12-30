package com.salesmanager.shop.mapper.catalog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.salesmanager.core.model.catalog.catalog.Catalog;
import com.salesmanager.core.model.catalog.category.Category;
import com.salesmanager.core.model.common.audit.AuditSection;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.shop.mapper.Mapper;
import com.salesmanager.shop.model.catalog.catalog.ReadableCatalog;
import com.salesmanager.shop.model.catalog.category.ReadableCategory;
import com.salesmanager.shop.model.store.ReadableMerchantStore;
import com.salesmanager.shop.store.controller.store.facade.StoreFacade;
import com.salesmanager.shop.utils.DateUtil;

@Component
public class ReadableCatalogMapper implements Mapper<Catalog, ReadableCatalog> {
	
	@Autowired
	private StoreFacade storeFacade;
	

	@Autowired
	private ReadableCategoryMapper readableCategoryMapper;

	@Override
	public ReadableCatalog convert(Catalog source, MerchantStore store, Language language) {
		ReadableCatalog destination = new ReadableCatalog();
		return merge(source, destination, store, language);
	}

	@Override
	public ReadableCatalog merge(Catalog source, ReadableCatalog destination, MerchantStore store,
								 Language language) {
		if(destination == null) {
			destination = new ReadableCatalog();
		}
		
		if(isPositive(source.getId())) {
			destination.setId(source.getId());
		}
		
		destination.setCode(source.getCode());
		destination.setDefaultCatalog(source.isDefaultCatalog());
		destination.setVisible(source.isVisible());

		Optional<ReadableMerchantStore> readableStore = Optional.ofNullable(source.getMerchantStore())
				.map(MerchantStore::getCode)
				.map(code -> storeFacade.getByCode(code, language));
		readableStore.ifPresent(destination::setStore);
		
		destination.setDefaultCatalog(source.isDefaultCatalog());

		Optional<String> formattedCreationDate = Optional.ofNullable(source.getAuditSection())
				.map(AuditSection::getDateCreated)
				.map(DateUtil::formatDate);
		formattedCreationDate.ifPresent(destination::setCreationDate);
		
		if(CollectionUtils.isNotEmpty(source.getEntry())) {
			
			//hierarchy temp object
			Map<Long, ReadableCategory> hierarchy = new HashMap<Long, ReadableCategory>();

			source.getEntry().forEach(entry -> {
				processCategory(entry.getCategory(), store, language, hierarchy, new HashMap<>());
			});
			
			destination.setCategory(new ArrayList<>(hierarchy.values()));
		}
		
		return destination;
		
	}

	private boolean isPositive(Long id) {
		return Objects.nonNull(id) && id > 0;
	}

	/**
	 * B
	 * 	1
	 * 	  D
	 * 	2
	 * C
	 * 	1
	 * 	4
	 * A
	 * @param parent
	 * @param c
	 * @param store
	 * @param language
	 * @param hierarchy
	 */

	//TODO it needs to cover by unit tests
	private void processCategory(Category c, MerchantStore store, Language language, Map<Long, ReadableCategory> hierarchy, Map<Long, ReadableCategory> processed ) {
		
		//build category hierarchy
		
		ReadableCategory rc = null;
		ReadableCategory rp = null;
		
		if(CollectionUtils.isNotEmpty(c.getCategories())) {
			c.getCategories().stream().forEach(element -> {
				processCategory(element, store, language, hierarchy, processed);
			});
		}

		Category parent = c.getParent();
		if(Objects.nonNull(parent)) {
			rp = hierarchy.computeIfAbsent(parent.getId(), i -> toReadableCategory(c.getParent(), store, language, processed));
		}

		rc = toReadableCategory(c, store, language, processed);
		if(Objects.nonNull(rp)) {
			rp.getChildren().add(rc);
		} else {
			hierarchy.put(c.getId(), rc);
		}

	}

	private ReadableCategory toReadableCategory(Category c, MerchantStore store, Language lang, Map<Long, ReadableCategory> processed) {
		Long id = c.getId();
		return processed.computeIfAbsent(id, it -> readableCategoryMapper.convert(c, store, lang));
	}

}
