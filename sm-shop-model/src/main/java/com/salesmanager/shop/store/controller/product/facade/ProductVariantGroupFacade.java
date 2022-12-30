package com.salesmanager.shop.store.controller.product.facade;

import org.springframework.web.multipart.MultipartFile;

import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.shop.model.catalog.product.product.variantGroup.PersistableProductVariantGroup;
import com.salesmanager.shop.model.catalog.product.product.variantGroup.ReadableProductVariantGroup;
import com.salesmanager.shop.model.entity.ReadableEntityList;

public interface ProductVariantGroupFacade {
	
	ReadableProductVariantGroup get(Long instanceGroupId, MerchantStore store, Language language);
	Long create(PersistableProductVariantGroup productVariantGroup, MerchantStore store, Language language);
	void update(Long productVariantGroup, PersistableProductVariantGroup instance, MerchantStore store, Language language);
	void delete(Long productVariant, Long productId, MerchantStore store);
	ReadableEntityList<ReadableProductVariantGroup> list(Long productId, MerchantStore store, Language language, int page, int count);
	
	void addImage(MultipartFile image, Long instanceGroupId,
			MerchantStore store, Language language);
	
	void removeImage(Long imageId, Long instanceGroupId, MerchantStore store);

}
