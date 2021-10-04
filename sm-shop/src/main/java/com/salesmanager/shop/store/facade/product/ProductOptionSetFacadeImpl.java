package com.salesmanager.shop.store.facade.product;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.salesmanager.core.business.exception.ServiceException;
import com.salesmanager.core.business.services.catalog.product.attribute.ProductOptionSetService;
import com.salesmanager.core.model.catalog.product.attribute.ProductOptionSet;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.shop.mapper.catalog.PersistableProductOptionSetMapper;
import com.salesmanager.shop.mapper.catalog.ReadableProductOptionSetMapper;
import com.salesmanager.shop.model.catalog.product.attribute.optionset.PersistableProductOptionSet;
import com.salesmanager.shop.model.catalog.product.attribute.optionset.ReadableProductOptionSet;
import com.salesmanager.shop.model.catalog.product.type.ReadableProductType;
import com.salesmanager.shop.store.api.exception.OperationNotAllowedException;
import com.salesmanager.shop.store.api.exception.ResourceNotFoundException;
import com.salesmanager.shop.store.api.exception.ServiceRuntimeException;
import com.salesmanager.shop.store.controller.product.facade.ProductOptionSetFacade;
import com.salesmanager.shop.store.controller.product.facade.ProductTypeFacade;

@Service
public class ProductOptionSetFacadeImpl implements ProductOptionSetFacade {

	@Autowired
	private PersistableProductOptionSetMapper persistableProductOptionSetMapper;

	@Autowired
	private ReadableProductOptionSetMapper readableProductOptionSetMapper;

	@Autowired
	private ProductOptionSetService productOptionSetService;

	@Autowired
	private ProductTypeFacade productTypeFacade;;

	@Override
	public ReadableProductOptionSet get(Long id, MerchantStore store, Language language) {
		Validate.notNull(store, "MerchantStore cannot be null");
		Validate.notNull(language, "Language cannot be null");
		ProductOptionSet optionSet = productOptionSetService.getById(store, id, language);
		if (optionSet == null) {
			throw new ResourceNotFoundException(
					"ProductOptionSet not found for id [" + id + "] and store [" + store.getCode() + "]");
		}

		return readableProductOptionSetMapper.convert(optionSet, store, language);
	}

	@Override
	public List<ReadableProductOptionSet> list(MerchantStore store, Language language) {
		Validate.notNull(store, "MerchantStore cannot be null");
		Validate.notNull(language, "Language cannot be null");

		try {
			List<ProductOptionSet> optionSets = productOptionSetService.listByStore(store, language);
			return optionSets.stream().map(opt -> this.convert(opt, store, language)).collect(Collectors.toList());
		} catch (ServiceException e) {
			throw new ServiceRuntimeException("Exception while listing ProductOptionSet", e);
		}

	}

	private ReadableProductOptionSet convert(ProductOptionSet optionSet, MerchantStore store, Language language) {
		return readableProductOptionSetMapper.convert(optionSet, store, language);
	}

	@Override
	public void create(PersistableProductOptionSet optionSet, MerchantStore store, Language language) {
		Validate.notNull(store, "MerchantStore cannot be null");
		Validate.notNull(language, "Language cannot be null");
		Validate.notNull(optionSet, "PersistableProductOptionSet cannot be null");

		if (this.exists(optionSet.getCode(), store)) {
			throw new OperationNotAllowedException("Option set with code [" + optionSet.getCode() + "] already exist");
		}

		ProductOptionSet opt = persistableProductOptionSetMapper.convert(optionSet, store, language);
		try {
			opt.setStore(store);
			productOptionSetService.create(opt);
		} catch (ServiceException e) {
			throw new ServiceRuntimeException("Exception while creating ProductOptionSet", e);
		}

	}

	@Override
	public void update(Long id, PersistableProductOptionSet optionSet, MerchantStore store, Language language) {
		Validate.notNull(store, "MerchantStore cannot be null");
		Validate.notNull(language, "Language cannot be null");
		Validate.notNull(optionSet, "PersistableProductOptionSet cannot be null");

		ProductOptionSet opt = productOptionSetService.getById(store, id, language);
		if (opt == null) {
			throw new ResourceNotFoundException(
					"ProductOptionSet not found for id [" + id + "] and store [" + store.getCode() + "]");
		}

		optionSet.setId(id);
		optionSet.setCode(opt.getCode());
		ProductOptionSet model = persistableProductOptionSetMapper.convert(optionSet, store, language);
		try {
			model.setStore(store);
			productOptionSetService.save(model);
		} catch (ServiceException e) {
			throw new ServiceRuntimeException("Exception while creating ProductOptionSet", e);
		}

	}

	@Override
	public void delete(Long id, MerchantStore store) {
		Validate.notNull(store, "MerchantStore cannot be null");
		Validate.notNull(id, "id cannot be null");
		ProductOptionSet opt = productOptionSetService.getById(id);
		if (opt == null) {
			throw new ResourceNotFoundException(
					"ProductOptionSet not found for id [" + id + "] and store [" + store.getCode() + "]");
		}
		if (!opt.getStore().getCode().equals(store.getCode())) {
			throw new ResourceNotFoundException(
					"ProductOptionSet not found for id [" + id + "] and store [" + store.getCode() + "]");
		}
		try {
			productOptionSetService.delete(opt);
		} catch (ServiceException e) {
			throw new ServiceRuntimeException("Exception while deleting ProductOptionSet", e);
		}

	}

	@Override
	public boolean exists(String code, MerchantStore store) {
		Validate.notNull(store, "MerchantStore cannot be null");
		Validate.notNull(code, "code cannot be null");
		ProductOptionSet optionSet = productOptionSetService.getCode(store, code);
		if (optionSet != null) {
			return true;
		}

		return false;
	}

	@Override
	public List<ReadableProductOptionSet> list(MerchantStore store, Language language, String type) {
		Validate.notNull(store, "MerchantStore cannot be null");
		Validate.notNull(language, "Language cannot be null");
		Validate.notNull(type, "Product type cannot be null");

		// find product type by id
		ReadableProductType readable = productTypeFacade.get(store, type, language);
		
		if(readable == null) {
			throw new ResourceNotFoundException("Can't fing product type [" + type + "] fpr merchand [" + store.getCode() +"]");
		}

		List<ProductOptionSet> optionSets = productOptionSetService.getByProductType(readable.getId(), store, language);
		return optionSets.stream().map(opt -> this.convert(opt, store, language)).collect(Collectors.toList());

	}

}
