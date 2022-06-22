package com.salesmanager.core.business.services.catalog.product.instance;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.salesmanager.core.business.exception.ServiceException;
import com.salesmanager.core.business.repositories.catalog.product.instance.PageableProductInstanceGroupRepository;
import com.salesmanager.core.business.repositories.catalog.product.instance.ProductInstanceGroupRepository;
import com.salesmanager.core.business.services.common.generic.SalesManagerEntityServiceImpl;
import com.salesmanager.core.model.catalog.product.instance.ProductInstanceGroup;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;


@Service("productInstanceGroupService")
public class ProductInstanceGroupServiceImpl extends SalesManagerEntityServiceImpl<Long, ProductInstanceGroup> implements ProductInstanceGroupService {

	
	@Autowired
	private PageableProductInstanceGroupRepository pageableProductInstanceGroupRepository;
	
	private ProductInstanceGroupRepository productInstanceGroupRepository;
	
	public ProductInstanceGroupServiceImpl(ProductInstanceGroupRepository repository) {
		super(repository);
		this.productInstanceGroupRepository = repository;
	}

	@Override
	public Optional<ProductInstanceGroup> getById(Long id, MerchantStore store) {
		return  productInstanceGroupRepository.findOne(id, store.getCode());

	}

	@Override
	public Optional<ProductInstanceGroup> getByProductInstance(Long productInstanceId, MerchantStore store,
			Language language) {
		return productInstanceGroupRepository.finByProductInstance(productInstanceId, store.getCode());
	}

	/*
	 * @Override public List<ProductInstanceGroup> getByProductId(Product product,
	 * MerchantStore store) { return
	 * productInstanceGroupRepository.finByProduct(product.getId(),
	 * store.getCode()); }
	 */
	
	@Override
	public void saveOrUpdate(ProductInstanceGroup entity) throws ServiceException {
		
		
		//save or update (persist and attach entities
		if(entity.getId()!=null && entity.getId()>0) {
			super.update(entity);
		} else {
			super.saveAndFlush(entity);
		}
		
	}

	@Override
	public Page<ProductInstanceGroup> getByProductId(MerchantStore store, Long productId, Language language, int page,
			int count) {
		
		Pageable pageRequest = PageRequest.of(page, count);
		return pageableProductInstanceGroupRepository.findByProductId(store.getId(), productId, pageRequest);
	}


}
