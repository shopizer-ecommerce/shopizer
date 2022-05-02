package com.salesmanager.core.business.services.catalog.product.instance;

import java.util.Optional;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.salesmanager.core.business.repositories.catalog.product.instance.PageableProductInstanceRepositoty;
import com.salesmanager.core.business.repositories.catalog.product.instance.ProductInstanceRepository;
import com.salesmanager.core.business.services.common.generic.SalesManagerEntityServiceImpl;
import com.salesmanager.core.model.catalog.product.Product;
import com.salesmanager.core.model.catalog.product.instance.ProductInstance;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;

@Service("productInstanceService")
public class ProductInstanceServiceImpl extends SalesManagerEntityServiceImpl<Long, ProductInstance> implements ProductInstanceService {
	
	

	private ProductInstanceRepository productInstanceRepository;
	
	@Autowired
	private PageableProductInstanceRepositoty pageableProductInstanceRepositoty;
	
	@Inject
	public ProductInstanceServiceImpl(ProductInstanceRepository productInstanceRepository) {
	    super(productInstanceRepository);
	    this.productInstanceRepository = productInstanceRepository;
	}

	@Override
	public Optional<ProductInstance> getById(Long id, MerchantStore store) {
		return productInstanceRepository.findById(id, store.getId());
	}


	@Override
	public Page<ProductInstance> getByProductId(MerchantStore store, Product product, Language language, int page,
			int count) {
		Pageable pageRequest = PageRequest.of(page, count);
		return pageableProductInstanceRepositoty.findByProductId(store.getId(), product.getId(), pageRequest);
	}

	@Override
	public Optional<ProductInstance> getBySku(String sku, MerchantStore store, Language language) {
		return productInstanceRepository.findBySku(sku, store.getId(), language.getId());
	}




	
	



}
