package com.salesmanager.core.business.services.catalog.product.attribute;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.salesmanager.core.business.exception.ServiceException;
import com.salesmanager.core.business.repositories.catalog.product.attribute.ProductOptionValueRepository;
import com.salesmanager.core.business.services.common.generic.SalesManagerEntityServiceImpl;
import com.salesmanager.core.model.catalog.product.attribute.ProductAttribute;
import com.salesmanager.core.model.catalog.product.attribute.ProductOptionValue;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;

@Service("productOptionValueService")
public class ProductOptionValueServiceImpl extends
		SalesManagerEntityServiceImpl<Long, ProductOptionValue> implements
		ProductOptionValueService {

	@Inject
	private ProductAttributeService productAttributeService;
	
	private ProductOptionValueRepository productOptionValueRepository;
	
	@Inject
	public ProductOptionValueServiceImpl(
			ProductOptionValueRepository productOptionValueRepository) {
			super(productOptionValueRepository);
			this.productOptionValueRepository = productOptionValueRepository;
	}
	
	
	@Override
	public List<ProductOptionValue> listByStore(MerchantStore store, Language language) throws ServiceException {
		
		return productOptionValueRepository.findByStoreId(store.getId(), language.getId());
	}
	
	@Override
	public List<ProductOptionValue> listByStoreNoReadOnly(MerchantStore store, Language language) throws ServiceException {
		
		return productOptionValueRepository.findByReadOnly(store.getId(), language.getId(), false);
	}

	@Override
	public List<ProductOptionValue> getByName(MerchantStore store, String name, Language language) throws ServiceException {
		
		try {
			return productOptionValueRepository.findByName(store.getId(), name, language.getId());
		} catch (Exception e) {
			throw new ServiceException(e);
		}
		
		
	}
	
	@Override
	public void saveOrUpdate(ProductOptionValue entity) throws ServiceException {
		
		
		//save or update (persist and attach entities
		if(entity.getId()!=null && entity.getId()>0) {

			super.update(entity);
			
		} else {
			
			super.save(entity);
			
		}
		
	}
	
	
	public void delete(ProductOptionValue entity) throws ServiceException {
		
		//remove all attributes having this option
		List<ProductAttribute> attributes = productAttributeService.getByOptionValueId(entity.getMerchantStore(), entity.getId());
		
		for(ProductAttribute attribute : attributes) {
			productAttributeService.delete(attribute);
		}
		
		ProductOptionValue option = getById(entity.getId());
		
		//remove option
		super.delete(option);
		
	}
	
	@Override
	public ProductOptionValue getByCode(MerchantStore store, String optionValueCode) {
		return productOptionValueRepository.findByCode(store.getId(), optionValueCode);
	}


	@Override
	public ProductOptionValue getById(MerchantStore store, Long optionValueId) {
		return productOptionValueRepository.findOne(store.getId(), optionValueId);
	}



}
