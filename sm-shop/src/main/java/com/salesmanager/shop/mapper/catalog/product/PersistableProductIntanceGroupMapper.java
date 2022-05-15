package com.salesmanager.shop.mapper.catalog.product;

import org.jsoup.helper.Validate;

import com.salesmanager.core.model.catalog.product.instance.ProductInstanceGroup;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.shop.mapper.Mapper;
import com.salesmanager.shop.model.catalog.product.product.instanceGroup.PersistableProductInstanceGroup;

public class PersistableProductIntanceGroupMapper implements Mapper<PersistableProductInstanceGroup, ProductInstanceGroup>{

	@Override
	public ProductInstanceGroup convert(PersistableProductInstanceGroup source, MerchantStore store,
			Language language) {
		Validate.notNull(source, "PersistableProductInstanceGroup cannot be null");
		Validate.notNull(store, "MerchantStore cannot be null");
		Validate.notNull(language, "Language cannot be null");
		return this.merge(source, new ProductInstanceGroup(), store, language);
	}

	@Override
	public ProductInstanceGroup merge(PersistableProductInstanceGroup source, ProductInstanceGroup destination,
			MerchantStore store, Language language) {
		// TODO Auto-generated method stub
		return null;
	}

}
