package com.salesmanager.shop.mapper.catalog.product;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.jsoup.helper.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.salesmanager.core.business.services.catalog.product.instance.ProductInstanceImageService;
import com.salesmanager.core.business.services.catalog.product.instance.ProductInstanceService;
import com.salesmanager.core.model.catalog.product.instance.ProductInstance;
import com.salesmanager.core.model.catalog.product.instance.ProductInstanceGroup;
import com.salesmanager.core.model.catalog.product.instance.ProductInstanceImage;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.shop.mapper.Mapper;
import com.salesmanager.shop.model.catalog.product.product.instanceGroup.PersistableProductInstanceGroup;

@Component
public class PersistableProductIntanceGroupMapper implements Mapper<PersistableProductInstanceGroup, ProductInstanceGroup>{

	
	@Autowired
	private ProductInstanceService productInstanceService;
	
	@Autowired
	private ProductInstanceImageService productInstanceImageService;
	
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
		
		Validate.notNull(source, "PersistableProductInstanceGroup cannot be null");
		Validate.notNull(store, "MerchantStore cannot be null");
		Validate.notNull(language, "Language cannot be null");
		Validate.notNull(source.getProductInstances(), "Product instances cannot be null");	
		
		if(destination == null) {
			destination = new ProductInstanceGroup();
		}
		
		destination.setId(source.getId());
		
		
		List<ProductInstance> productInstances = productInstanceService.getByIds(source.getProductInstances(), store);
		
		for(ProductInstance p : productInstances) {
			p.setProductInstanceGroup(destination);
		}

		//images are not managed from this object
		if(source.getId() != null) {
			List<ProductInstanceImage> images = productInstanceImageService.listByProductInstanceGroup(source.getId(), store);
			destination.setImages(images);
		}
		destination.setMerchantStore(store);
		destination.setProductInstances(new HashSet<ProductInstance>(productInstances));
		return destination;
	}
	
	private ProductInstance instance(ProductInstance instance, ProductInstanceGroup group, MerchantStore store) {
		
		instance.setProductInstanceGroup(group);
		return instance;

	}

}
