package com.salesmanager.core.business.services.catalog.product.variant;

import java.util.List;

import org.jsoup.helper.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.salesmanager.core.business.repositories.catalog.product.variant.ProductVariantImageRepository;
import com.salesmanager.core.business.services.common.generic.SalesManagerEntityServiceImpl;
import com.salesmanager.core.model.catalog.product.variant.ProductVariantImage;
import com.salesmanager.core.model.merchant.MerchantStore;


@Service("productVariantImageService")
public class ProductVariantImageServiceImpl extends SalesManagerEntityServiceImpl<Long, ProductVariantImage> implements ProductVariantImageService {

	@Autowired
	private ProductVariantImageRepository productVariantImageRepository;
	
	public ProductVariantImageServiceImpl(ProductVariantImageRepository productVariantImageRepository) {
		super(productVariantImageRepository);
		this.productVariantImageRepository = productVariantImageRepository;
	}

	@Override
	public List<ProductVariantImage> list(Long productVariantId, MerchantStore store) {
		Validate.notNull(store, "MerchantStore cannot be null");
		return productVariantImageRepository.finByProductVariant(productVariantId, store.getCode());
	}

	@Override
	public List<ProductVariantImage> listByProduct(Long productId, MerchantStore store) {
		Validate.notNull(store, "MerchantStore cannot be null");
		return productVariantImageRepository.finByProduct(productId, store.getCode());
	}

	@Override
	public List<ProductVariantImage> listByProductVariantGroup(Long productVariantGroupId, MerchantStore store) {
		Validate.notNull(store, "MerchantStore cannot be null");
		return productVariantImageRepository.finByProductVariantGroup(productVariantGroupId, store.getCode());
	}

}
