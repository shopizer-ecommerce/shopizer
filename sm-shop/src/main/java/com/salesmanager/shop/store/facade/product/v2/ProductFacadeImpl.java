package com.salesmanager.shop.store.facade.product.v2;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.shop.model.catalog.product.product.instance.PersistableProductInstance;
import com.salesmanager.shop.model.catalog.product.product.instance.ReadableProductInstance;
import com.salesmanager.shop.model.entity.ReadableEntityList;
import com.salesmanager.shop.store.controller.product.facade.v2.ProductFacadeV2;


@Service("productFacadeV2")
@Profile({ "default", "cloud", "gcp", "aws", "mysql" , "local" })
public class ProductFacadeImpl implements ProductFacadeV2 {

	@Override
	public Long saveOrUpdate(PersistableProductInstance productInstance, MerchantStore store, Language language) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ReadableProductInstance get(Long id, MerchantStore store, Language language) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ReadableEntityList<ReadableProductInstance> listByProduct(Long productId, MerchantStore store,
			Language language) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Long id, PersistableProductInstance instance, MerchantStore store, Language language) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Long id, MerchantStore store) {
		// TODO Auto-generated method stub

	}

}
