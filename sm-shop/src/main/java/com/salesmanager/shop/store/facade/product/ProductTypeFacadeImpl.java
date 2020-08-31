package com.salesmanager.shop.store.facade.product;

import java.util.stream.Collectors;

import org.jsoup.helper.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.salesmanager.core.business.services.catalog.product.type.ProductTypeService;
import com.salesmanager.core.model.catalog.product.type.ProductType;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.shop.mapper.catalog.ReadableProductTypeMapper;
import com.salesmanager.shop.model.catalog.product.type.PersistableProductType;
import com.salesmanager.shop.model.catalog.product.type.ReadableProductType;
import com.salesmanager.shop.model.catalog.product.type.ReadableProductTypeList;
import com.salesmanager.shop.store.api.exception.ResourceNotFoundException;
import com.salesmanager.shop.store.api.exception.ServiceRuntimeException;
import com.salesmanager.shop.store.controller.product.facade.ProductTypeFacade;

@Service("productTypeFacade")
public class ProductTypeFacadeImpl implements ProductTypeFacade {

	@Autowired
	private ProductTypeService productTypeService;
	
	@Autowired
	private ReadableProductTypeMapper readableProductTypeMapper;

	@Override
	public ReadableProductTypeList getByMerchant(MerchantStore store, Language language,  int count, int page) {

		Validate.notNull(store, "MerchantStore cannot be null");
		ReadableProductTypeList returnList = new ReadableProductTypeList();

		try {
			
			Page<ProductType> types = productTypeService.getByMerchant(store, language, page, count);

			if(types != null) {
				returnList.setList(types.getContent().stream().map(t -> readableProductTypeMapper.convert(t, store, language)).collect(Collectors.toList()));
				returnList.setTotalPages(types.getTotalPages());
				returnList.setRecordsTotal(types.getTotalElements());
				returnList.setRecordsFiltered(types.getSize());
			}

			return returnList;
		} catch (Exception e) {
			throw new ServiceRuntimeException(
					"An exception occured while getting product types for merchant[ " + store.getCode() + "]");
		}

	}

	@Override
	public ReadableProductType get(MerchantStore store, String code, Language language) {
		
		Validate.notNull(store, "MerchantStore cannot be null");
		Validate.notNull(code, "ProductType code cannot be empty");
		try {
			
			ProductType type = productTypeService.getByCode(code, store, language);
			ReadableProductType readableType = readableProductTypeMapper.convert(type, store, language);
			if(readableType == null) {
				throw new ResourceNotFoundException("Product type [" + code + "] not found for store [" + store.getCode() + "]");
			}
			
			return readableType;
			
		} catch(Exception e) {
			throw new ServiceRuntimeException(
					"An exception occured while getting product type [" + code + "] not found for store [" + store.getCode() + "]");
		}

	}

	@Override
	public void save(PersistableProductType type, MerchantStore store, Language language) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(PersistableProductType type, String code, MerchantStore store, Language language) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(String code, MerchantStore store) {
		// TODO Auto-generated method stub

	}

}
