package com.salesmanager.shop.store.facade.product;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.salesmanager.core.business.exception.ServiceException;
import com.salesmanager.core.business.services.catalog.product.variation.ProductVariationService;
import com.salesmanager.core.model.catalog.product.variation.ProductVariation;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.shop.mapper.catalog.PersistableProductVariationMapper;
import com.salesmanager.shop.mapper.catalog.ReadableProductVariationMapper;
import com.salesmanager.shop.model.catalog.product.variation.PersistableProductVariation;
import com.salesmanager.shop.model.catalog.product.variation.ReadableProductVariation;
import com.salesmanager.shop.model.entity.ReadableEntityList;
import com.salesmanager.shop.store.api.exception.OperationNotAllowedException;
import com.salesmanager.shop.store.api.exception.ResourceNotFoundException;
import com.salesmanager.shop.store.api.exception.ServiceRuntimeException;
import com.salesmanager.shop.store.controller.product.facade.ProductVariationFacade;

@Service
public class ProductVariationFacadeImpl implements ProductVariationFacade {
	
	@Autowired
	private PersistableProductVariationMapper persistableProductVariationMapper;
	
	@Autowired
	private ReadableProductVariationMapper readableProductVariationMapper;
	
	@Autowired
	private ProductVariationService productVariationService;


	@Override
	public ReadableProductVariation get(Long id, MerchantStore store, Language language) {
		Validate.notNull(store, "MerchantStore cannot be null");
		Validate.notNull(language, "Language cannot be null");
		ProductVariation variation =  productVariationService.getById(store, id, language);
		if(variation == null) {
			throw new ResourceNotFoundException("ProductVariation not found for id [" + id +"] and store [" + store.getCode() + "]");
		}
		
		return readableProductVariationMapper.convert(variation, store, language);
	}

	@Override
	public ReadableEntityList<ReadableProductVariation> list(MerchantStore store, Language language, int page, int count) {
		Validate.notNull(store, "MerchantStore cannot be null");
		Validate.notNull(language, "Language cannot be null");
		

			Page<ProductVariation> vars = productVariationService.getByMerchant(store, language, null, page, count);
			List<ReadableProductVariation> variations = vars.stream().map(opt -> this.convert(opt, store, language)).collect(Collectors.toList());
			ReadableEntityList<ReadableProductVariation> returnList = new ReadableEntityList<ReadableProductVariation>();
			returnList.setItems(variations);
			returnList.setNumber(variations.size());
			returnList.setRecordsTotal(vars.getTotalElements());
			returnList.setTotalPages(vars.getTotalPages());
			return returnList;
		

	}
	
	private ReadableProductVariation convert(ProductVariation var, MerchantStore store, Language language) {
		return readableProductVariationMapper.convert(var, store, language);
	}

	@Override
	public void create(PersistableProductVariation var, MerchantStore store, Language language) {
		Validate.notNull(store, "MerchantStore cannot be null");
		Validate.notNull(language, "Language cannot be null");
		Validate.notNull(var, "PersistableProductVariation cannot be null");
		
		if(this.exists(var.getCode(), store)) {
			throw new OperationNotAllowedException("Option set with code [" + var.getCode() + "] already exist");
		}
		
		ProductVariation p = persistableProductVariationMapper.convert(var, store, language);
		try {
			p.setMerchantStore(store);
			productVariationService.create(p);
		} catch (ServiceException e) {
			throw new ServiceRuntimeException("Exception while creating ProductOptionSet", e);
		}

	}

	@Override
	public void update(Long id, PersistableProductVariation var, MerchantStore store, Language language) {
		Validate.notNull(store, "MerchantStore cannot be null");
		Validate.notNull(language, "Language cannot be null");
		Validate.notNull(var, "PersistableProductVariation cannot be null");
		
		ProductVariation p =  productVariationService.getById(store, id, language);
		if(p == null) {
			throw new ResourceNotFoundException("ProductVariation not found for id [" + id +"] and store [" + store.getCode() + "]");
		}
		
		p.setId(id);
		p.setCode(var.getCode());
		ProductVariation model = persistableProductVariationMapper.merge(var, p, store, language);
		try {
			model.setMerchantStore(store);
			productVariationService.save(model);
		} catch (ServiceException e) {
			throw new ServiceRuntimeException("Exception while creating ProductVariation", e);
		}

	}

	@Override
	public void delete(Long id, MerchantStore store) {
		Validate.notNull(store, "MerchantStore cannot be null");
		Validate.notNull(id, "id cannot be null");
		ProductVariation opt =  productVariationService.getById(id);
		if(opt == null) {
			throw new ResourceNotFoundException("ProductVariation not found for id [" + id +"] and store [" + store.getCode() + "]");
		}
		if(!opt.getMerchantStore().getCode().equals(store.getCode())) {
			throw new ResourceNotFoundException("ProductVariation not found for id [" + id +"] and store [" + store.getCode() + "]");
		}
		try {
			productVariationService.delete(opt);
		} catch (ServiceException e) {
			throw new ServiceRuntimeException("Exception while deleting ProductVariation", e);
		}

	}

	@Override
	public boolean exists(String code, MerchantStore store) {
		Validate.notNull(store, "MerchantStore cannot be null");
		Validate.notNull(code, "code cannot be null");
		ProductVariation var =  productVariationService.getByCode(store, code);
		if(var != null) {
			return true;
		}
		
		return false;
	}

}
