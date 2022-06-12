package com.salesmanager.shop.store.controller.product.facade;

import org.springframework.web.multipart.MultipartFile;

import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.shop.model.catalog.product.product.instanceGroup.PersistableProductInstanceGroup;
import com.salesmanager.shop.model.catalog.product.product.instanceGroup.ReadableProductInstanceGroup;
import com.salesmanager.shop.model.entity.ReadableEntityList;

public interface ProductInstanceGroupFacade {
	
	ReadableProductInstanceGroup get(Long instanceGroupId, MerchantStore store, Language language);
	Long create(PersistableProductInstanceGroup productInstanceGroup, MerchantStore store, Language language);
	void update(Long productInstanceGroup, PersistableProductInstanceGroup instance, MerchantStore store, Language language);
	void delete(Long productInstance, Long productId, MerchantStore store);
	ReadableEntityList<ReadableProductInstanceGroup> list(Long productId, MerchantStore store, Language language, int page, int count);
	
	void addImage(MultipartFile image, Long instanceGroupId,
			MerchantStore store, Language language);
	
	void removeImage(Long imageId, Long instanceGroupId, MerchantStore store);

}
