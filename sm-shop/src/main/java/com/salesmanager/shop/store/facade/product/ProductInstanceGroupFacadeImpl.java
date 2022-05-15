package com.salesmanager.shop.store.facade.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.salesmanager.core.business.services.catalog.product.instance.ProductInstanceGroupService;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.shop.model.catalog.product.product.instanceGroup.PersistableProductInstanceGroup;
import com.salesmanager.shop.model.catalog.product.product.instanceGroup.ReadableProductInstanceGroup;
import com.salesmanager.shop.model.entity.ReadableEntityList;
import com.salesmanager.shop.store.controller.product.facade.ProductInstanceGroupFacade;


@Component
public class ProductInstanceGroupFacadeImpl implements ProductInstanceGroupFacade {
	
	@Autowired
	private ProductInstanceGroupService productInstanceGroupService;

	@Override
	public ReadableProductInstanceGroup get(Long instanceGroupId, MerchantStore store, Language language) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long create(PersistableProductInstanceGroup productInstanceGroup, MerchantStore store, Language language) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Long productInstanceGroup, PersistableProductInstanceGroup instance, MerchantStore store,
			Language language) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Long productInstance, Long productId, MerchantStore store) {
		// TODO Auto-generated method stub

	}

	@Override
	public ReadableEntityList<ReadableProductInstanceGroup> list(Long productId, MerchantStore store, Language language,
			int page, int count) {
		// TODO Auto-generated method stub
		return null;
	}

}
