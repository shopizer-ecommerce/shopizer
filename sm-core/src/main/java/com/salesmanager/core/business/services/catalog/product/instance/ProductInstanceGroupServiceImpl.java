package com.salesmanager.core.business.services.catalog.product.instance;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.salesmanager.core.business.repositories.catalog.product.instance.ProductInstanceGroupRepository;
import com.salesmanager.core.business.services.common.generic.SalesManagerEntityServiceImpl;
import com.salesmanager.core.model.catalog.product.Product;
import com.salesmanager.core.model.catalog.product.instance.ProductInstanceGroup;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;


@Service("productInstanceGroupService")
public class ProductInstanceGroupServiceImpl extends SalesManagerEntityServiceImpl<Long, ProductInstanceGroup> implements ProductInstanceGroupService {

	
	private ProductInstanceGroupRepository productInstanceGroupRepository;
	
	public ProductInstanceGroupServiceImpl(ProductInstanceGroupRepository repository) {
		super(repository);
		this.productInstanceGroupRepository = repository;
	}

	@Override
	public Optional<ProductInstanceGroup> getById(Long id, MerchantStore store) {
		return productInstanceGroupRepository.findOne(id, store.getCode());
	}

	@Override
	public Optional<ProductInstanceGroup> getByProductInstance(Long productInstanceId, MerchantStore store,
			Language language) {
		return productInstanceGroupRepository.finByProductInstance(productInstanceId, store.getCode());
	}

	@Override
	public List<ProductInstanceGroup> getByProductId(Product product, MerchantStore store) {
		return productInstanceGroupRepository.finByProduct(product.getId(), store.getCode());
	}


}
