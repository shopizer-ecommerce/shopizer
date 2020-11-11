package com.salesmanager.shop.store.facade.tax;

import org.apache.commons.lang.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.salesmanager.core.business.exception.ServiceException;
import com.salesmanager.core.business.services.tax.TaxClassService;
import com.salesmanager.core.business.services.tax.TaxRateService;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.core.model.tax.taxclass.TaxClass;
import com.salesmanager.shop.mapper.tax.PersistableTaxClassMapper;
import com.salesmanager.shop.model.entity.ReadableList;
import com.salesmanager.shop.model.tax.PersistableTaxClass;
import com.salesmanager.shop.model.tax.PersistableTaxRate;
import com.salesmanager.shop.model.tax.ReadableTaxClass;
import com.salesmanager.shop.model.tax.ReadableTaxRate;
import com.salesmanager.shop.store.api.exception.ServiceRuntimeException;
import com.salesmanager.shop.store.api.exception.UnauthorizedException;
import com.salesmanager.shop.store.controller.tax.facade.TaxFacade;

@Service
public class TaxFacadeImpl implements TaxFacade {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(TaxFacadeImpl.class);
	
	
	@Autowired
	private TaxClassService taxClassService;
	
	@Autowired
	private TaxRateService taxRateService;
	
	@Autowired
	private PersistableTaxClassMapper persistableTaxClassMapper;

	@Override
	public void saveTaxClass(PersistableTaxClass taxClass, MerchantStore store, Language language) {
		Validate.notNull(taxClass,"TaxClass cannot be null");
		Validate.notNull(store,"MerchantStore cannot be null");
		Validate.notNull(store.getCode(),"MerchantStore code cannot be null");
		try {
			TaxClass model = taxClassService.getByCode(taxClass.getCode(), store);
			if(model != null) {
				if(!model.getMerchantStore().getCode().equals(store.getCode())) {
					throw new UnauthorizedException("MerchantStore [" + store.getCode() + "] cannot update tax class [" + taxClass.getCode() + "]");
				}
				taxClass.setId(model.getId());
				model = persistableTaxClassMapper.convert(taxClass, model, store, language);
			} else {
				taxClass.setStore(store.getCode());
				model = persistableTaxClassMapper.convert(taxClass, store, language);
			}
			
			taxClassService.save(model);

		} catch (ServiceException e) {
			LOGGER.error("Error while saving taxClass [" +  taxClass.getCode() + "] for store [" + store.getCode() + "]", e);
			throw new ServiceRuntimeException("Error while saving taxClass [" +  taxClass.getCode() + "] for store [" + store.getCode() + "]", e);
		}
		

	}

	@Override
	public void deleteTaxClass(Long id, MerchantStore store, Language language) {
		// TODO Auto-generated method stub

	}

	@Override
	public ReadableList taxClasses(MerchantStore store, Language language) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ReadableTaxClass taxClass(String code, MerchantStore store, Language language) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveTaxRate(PersistableTaxRate taxRate, MerchantStore store, Language language) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteTaxRate(Long id, MerchantStore store, Language language) {
		// TODO Auto-generated method stub

	}

	@Override
	public ReadableList taxRates(MerchantStore store, Language language) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ReadableTaxRate taxRate(Long id, MerchantStore store, Language language) {
		// TODO Auto-generated method stub
		return null;
	}

}
