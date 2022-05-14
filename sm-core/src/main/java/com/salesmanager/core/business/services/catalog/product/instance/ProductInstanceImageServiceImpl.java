package com.salesmanager.core.business.services.catalog.product.instance;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.salesmanager.core.business.repositories.catalog.product.instance.ProductInstanceImageRepository;
import com.salesmanager.core.business.services.common.generic.SalesManagerEntityServiceImpl;
import com.salesmanager.core.model.catalog.product.instance.ProductInstanceImage;
import com.salesmanager.core.model.merchant.MerchantStore;

public class ProductInstanceImageServiceImpl extends SalesManagerEntityServiceImpl<Long, ProductInstanceImage> implements ProductInstanceImageService {

	@Autowired
	private ProductInstanceImageRepository productInstanceImageRepository;
	
	public ProductInstanceImageServiceImpl(ProductInstanceImageRepository productInstanceImageRepository) {
		super(productInstanceImageRepository);
		//this.productInstanceRepository = productInstanceRepository;
	}

	@Override
	public List<ProductInstanceImage> list(Long productInstanceId, MerchantStore store) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ProductInstanceImage> listByProduct(Long productId, MerchantStore store) {
		// TODO Auto-generated method stub
		return null;
	}

}
