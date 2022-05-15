package com.salesmanager.core.business.services.catalog.product.instance;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.salesmanager.core.business.repositories.catalog.product.instance.ProductInstanceImageRepository;
import com.salesmanager.core.business.services.common.generic.SalesManagerEntityServiceImpl;
import com.salesmanager.core.model.catalog.product.instance.ProductInstanceImage;
import com.salesmanager.core.model.merchant.MerchantStore;


@Service("productInstanceImageService")
public class ProductInstanceImageServiceImpl extends SalesManagerEntityServiceImpl<Long, ProductInstanceImage> implements ProductInstanceImageService {

	@Autowired
	private ProductInstanceImageRepository productInstanceImageRepository;
	
	public ProductInstanceImageServiceImpl(ProductInstanceImageRepository productInstanceImageRepository) {
		super(productInstanceImageRepository);
		this.productInstanceImageRepository = productInstanceImageRepository;
	}

	@Override
	public List<ProductInstanceImage> list(Long productInstanceId, MerchantStore store) {
		return productInstanceImageRepository.finByProductInstance(productInstanceId, store.getCode());
	}

	@Override
	public List<ProductInstanceImage> listByProduct(Long productId, MerchantStore store) {
		return productInstanceImageRepository.finByProduct(productId, store.getCode());
	}

}
