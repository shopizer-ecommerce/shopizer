package com.salesmanager.shop.store.facade.tax;

import java.util.List;
import java.util.stream.Collectors;

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
import com.salesmanager.core.model.tax.taxrate.TaxRate;
import com.salesmanager.shop.mapper.tax.PersistableTaxClassMapper;
import com.salesmanager.shop.mapper.tax.PersistableTaxRateMapper;
import com.salesmanager.shop.mapper.tax.ReadableTaxClassMapper;
import com.salesmanager.shop.mapper.tax.ReadableTaxRateMapper;
import com.salesmanager.shop.model.entity.Entity;
import com.salesmanager.shop.model.entity.ReadableEntityList;
import com.salesmanager.shop.model.tax.PersistableTaxClass;
import com.salesmanager.shop.model.tax.PersistableTaxRate;
import com.salesmanager.shop.model.tax.ReadableTaxClass;
import com.salesmanager.shop.model.tax.ReadableTaxRate;
import com.salesmanager.shop.store.api.exception.OperationNotAllowedException;
import com.salesmanager.shop.store.api.exception.ResourceNotFoundException;
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
	
	@Autowired
	private ReadableTaxClassMapper readableTaxClassMapper;
	
	@Autowired
	private PersistableTaxRateMapper persistableTaxRateMapper;
	
	@Autowired
	private ReadableTaxRateMapper readableTaxRateMapper;

	@Override
	public Entity createTaxClass(PersistableTaxClass taxClass, MerchantStore store, Language language) {
		Validate.notNull(taxClass,"TaxClass cannot be null");
		Validate.notNull(store,"MerchantStore cannot be null");
		Validate.notNull(store.getCode(),"MerchantStore code cannot be null");
		try {
			
			
			if(this.existsTaxClass(taxClass.getCode(), store, language)) {
				throw new OperationNotAllowedException("Tax class [" + taxClass.getCode() + "] already exist for store [" + store.getCode() + "]");
			}

			taxClass.setStore(store.getCode());
			TaxClass model = persistableTaxClassMapper.convert(taxClass, store, language);
			model = taxClassService.saveOrUpdate(model);;
			Entity id = new Entity();
			id.setId(model.getId());
			return id;

		} catch (ServiceException e) {
			LOGGER.error("Error while saving taxClass [" +  taxClass.getCode() + "] for store [" + store.getCode() + "]", e);
			throw new ServiceRuntimeException("Error while saving taxClass [" +  taxClass.getCode() + "] for store [" + store.getCode() + "]", e);
		}
		
	}

	@Override
	public void deleteTaxClass(Long id, MerchantStore store, Language language) {
		Validate.notNull(id,"TaxClass id cannot be null");
		Validate.notNull(store,"MerchantStore cannot be null");
		Validate.notNull(store.getCode(),"MerchantStore code cannot be null");
		try {
			TaxClass model = taxClassService.getById(id);
			if(model == null) {
				throw new ResourceNotFoundException("TaxClass not found [" + id + "] for store [" + store.getCode() + "]");
			} else {
				if(!model.getMerchantStore().getCode().equals(store.getCode())) {
					throw new UnauthorizedException("MerchantStore [" + store.getCode() + "] cannot delete tax class [" + id + "]");
				}
			}
			taxClassService.delete(model);
				
		} catch (ServiceException e) {
			LOGGER.error("Error while getting taxClasse [" + id + "] for store [" + store.getCode() + "]", e);
			throw new ServiceRuntimeException("Error while getting taxClasse [" + id + "] for store [" + store.getCode() + "]", e);
		}

	}

	@Override
	public ReadableEntityList<ReadableTaxClass> taxClasses(MerchantStore store, Language language) {
		Validate.notNull(store,"MerchantStore cannot be null");
		Validate.notNull(store.getCode(),"MerchantStore code cannot be null");
		try {
			List<TaxClass> models = taxClassService.listByStore(store);
			
			List<ReadableTaxClass> taxClasses = models.stream().map(t -> convertToReadableTaxClass(t, store, language)).collect(Collectors.toList());

			ReadableEntityList<ReadableTaxClass> list = new ReadableEntityList<ReadableTaxClass>();
			list.setItems(taxClasses);
			list.setNumber(taxClasses.size());
			list.setTotalPages(1);
			list.setRecordsTotal(taxClasses.size());
			
			return list;
			
		} catch (ServiceException e) {
			LOGGER.error("Error while getting taxClasses for store [" + store.getCode() + "]", e);
			throw new ServiceRuntimeException("Error while getting taxClasses for store [" + store.getCode() + "]", e);
		}
	}
	
	private ReadableTaxClass convertToReadableTaxClass(TaxClass t, MerchantStore store, Language language) {
		return readableTaxClassMapper.convert(t, store, language);
	}
	
	@Override
	public void updateTaxClass(Long id, PersistableTaxClass taxClass, MerchantStore store, Language language) {
		Validate.notNull(taxClass,"TaxClass cannot be null");
		Validate.notNull(store,"MerchantStore cannot be null");
		Validate.notNull(store.getCode(),"MerchantStore code cannot be null");
		try {
			TaxClass model = taxClassService.getById(id);
			if(model == null) {
				throw new ResourceNotFoundException("TaxClass not found [" + id + "] for store [" + store.getCode() + "]");
			} else {
				if(!model.getMerchantStore().getCode().equals(store.getCode())) {
					throw new UnauthorizedException("MerchantStore [" + store.getCode() + "] cannot update tax class [" + taxClass.getCode() + "]");
				}
			}
			model = persistableTaxClassMapper.convert(taxClass, store, language);
			taxClassService.saveOrUpdate(model);

		} catch (ServiceException e) {
			LOGGER.error("Error while saving taxClass [" +  taxClass.getCode() + "] for store [" + store.getCode() + "]", e);
			throw new ServiceRuntimeException("Error while saving taxClass [" +  taxClass.getCode() + "] for store [" + store.getCode() + "]", e);
		}
	}

	@Override
	public ReadableTaxClass taxClass(String code, MerchantStore store, Language language) {
		
		Validate.notNull(code,"TaxClass code cannot be null");
		Validate.notNull(store,"MerchantStore cannot be null");
		Validate.notNull(store.getCode(),"MerchantStore code cannot be null");
		
		try {
			TaxClass model = taxClassService.getByCode(code, store);
			if(model == null) {
				throw new ResourceNotFoundException("TaxClass not found [" + code + "] for store [" + store.getCode() + "]");
			}
			if(model != null) {
				if(!model.getMerchantStore().getCode().equals(store.getCode())) {
					throw new UnauthorizedException("MerchantStore [" + store.getCode() + "] cannot get tax class [" + code + "]");
				}
			}
			return readableTaxClassMapper.convert(model, store, language);
		} catch (ServiceException e) {
			LOGGER.error("Error while getting taxClass [" +  code + "] for store [" + store.getCode() + "]", e);
			throw new ServiceRuntimeException("Error while getting taxClass [" +  code + "] for store [" + store.getCode() + "]", e);
		}

	}
	
	@Override
	public boolean existsTaxClass(String code, MerchantStore store, Language language) {
		try {
			boolean exist = taxClassService.exists(code, store);
			return exist;
		} catch (ServiceException e) {
			LOGGER.error("Error while getting taxClass [" +  code + "] for store [" + store.getCode() + "]", e);
			throw new ServiceRuntimeException("Error while saving taxClass [" +  code + "] for store [" + store.getCode() + "]", e);
		}
	}
	
	
	//get by code
	private TaxRate taxRateByCode(String code, MerchantStore store, Language language) {
		
		Validate.notNull(code,"TaxRate code cannot be null");
		Validate.notNull(store,"MerchantStore cannot be null");
		Validate.notNull(store.getCode(),"MerchantStore code cannot be null");
		
		try {
			TaxRate model = taxRateService.getByCode(code, store);
			if(model == null) {
				throw new ResourceNotFoundException("TaxRate not found [" + code + "] for store [" + store.getCode() + "]");
			}
			if(model != null) {
				if(!model.getMerchantStore().getCode().equals(store.getCode())) {
					throw new UnauthorizedException("MerchantStore [" + store.getCode() + "] cannot get tax rate [" + code + "]");
				}
			}
			return model;
		} catch (ServiceException e) {
			LOGGER.error("Error while getting taxRate [" +  code + "] for store [" + store.getCode() + "]", e);
			throw new ServiceRuntimeException("Error while getting taxRate [" +  code + "] for store [" + store.getCode() + "]", e);
		}
		
	}
	
	//get by id
	private TaxRate taxRateById(Long id, MerchantStore store, Language language) {
		
		Validate.notNull(id,"TaxRate id cannot be null");
		Validate.notNull(store,"MerchantStore cannot be null");
		Validate.notNull(store.getCode(),"MerchantStore code cannot be null");
		
		try {
			TaxRate model = taxRateService.getById(id, store);
			if(model == null) {
				throw new ResourceNotFoundException("TaxRate not found [" + id + "]");
			} 
			return model;
		} catch (Exception e) {
			LOGGER.error("Error while getting taxRate [" +  id + "] for store [" + store.getCode() + "]", e);
			throw new ServiceRuntimeException("Error while getting taxRate [" +  id + "] for store [" + store.getCode() + "]", e);
		}
		
	}


	@Override
	public void deleteTaxRate(Long id, MerchantStore store, Language language) {
		TaxRate model = taxRateById(id, store, language);
		try {
			taxRateService.delete(model);
		} catch (ServiceException e) {
			LOGGER.error("Error while deleting taxRate [" +  id + "] for store [" + store.getCode() + "]", e);
			throw new ServiceRuntimeException("Error deleting taxRate [" +  id + "] for store [" + store.getCode() + "]", e);
		}

	}

	@Override
	public ReadableTaxRate taxRate(Long id, MerchantStore store, Language language) {
		
		TaxRate model = taxRateById(id, store, language);
		return readableTaxRateMapper.convert(model, store, language);
	}

	@Override
	public Entity createTaxRate(PersistableTaxRate taxRate, MerchantStore store, Language language) {
		
		Validate.notNull(taxRate,"TaxRate cannot be null");
		Validate.notNull(taxRate.getCode(),"TaxRate code cannot be null");
		Validate.notNull(store,"MerchantStore cannot be null");
		Validate.notNull(store.getCode(),"MerchantStore code cannot be null");
		

		
		try {
			
			TaxRate model = taxRateService.getByCode(taxRate.getCode(), store);
			if(model!=null) {
				throw new OperationNotAllowedException("Tax rate [" + taxRate.getCode() + "] already exist for store [" + store.getCode() + "]");
			}

			
			model = persistableTaxRateMapper.convert(taxRate, store, language);
			
			model = taxRateService.saveOrUpdate(model);
			
			Entity id = new Entity();
			id.setId(model.getId());
			return id;
		} catch (ServiceException e) {
			LOGGER.error("Error while saving taxRate [" +  taxRate.getCode() + "] for store [" + store.getCode() + "]", e);
			throw new ServiceRuntimeException("Error while saving taxRate [" +  taxRate.getCode() + "] for store [" + store.getCode() + "]", e);
		}
		

	}

	@Override
	public void updateTaxRate(Long id, PersistableTaxRate taxRate, MerchantStore store, Language language) {
		
		Validate.notNull(taxRate,"TaxRate cannot be null");
		Validate.notNull(id,"TaxRate id cannot be null");
		Validate.notNull(store,"MerchantStore cannot be null");
		Validate.notNull(store.getCode(),"MerchantStore code cannot be null");

		TaxRate model = taxRateById(id, store, language);
		
		model = persistableTaxRateMapper.convert(taxRate, model, store, language);
		
		try {
			model = taxRateService.saveOrUpdate(model);

		} catch (ServiceException e) {
			LOGGER.error("Error while saving taxRate [" +  taxRate.getCode() + "] for store [" + store.getCode() + "]", e);
			throw new ServiceRuntimeException("Error while saving taxRate [" +  taxRate.getCode() + "] for store [" + store.getCode() + "]", e);
		}
		
		
	}

	@Override
	public boolean existsTaxRate(String code, MerchantStore store, Language language) {

		Validate.notNull(code,"TaxRate code cannot be null");
		Validate.notNull(store,"MerchantStore cannot be null");
		Validate.notNull(store.getCode(),"MerchantStore code cannot be null");
		
		TaxRate rate = taxRateByCode(code, store, language);
		if(rate == null) {
			return false;
		}
		return true;
	}

	@Override
	public ReadableEntityList<ReadableTaxRate> taxRates(MerchantStore store, Language language) {
		
		Validate.notNull(store,"MerchantStore cannot be null");
		Validate.notNull(store.getCode(),"MerchantStore code cannot be null");
		
		try {
			List<TaxRate> rates = taxRateService.listByStore(store, language);
			List<ReadableTaxRate> readableRates = rates.stream().map(r -> readableTaxRateMapper.convert(r, store, language)).collect(Collectors.toList());
			
			ReadableEntityList<ReadableTaxRate> returnRates = new ReadableEntityList<ReadableTaxRate>();
			returnRates.setItems(readableRates);
			returnRates.setTotalPages(1);
			returnRates.setNumber(readableRates.size());
			returnRates.setRecordsTotal(readableRates.size());
			
			return returnRates;
		} catch (ServiceException e) {
			LOGGER.error("Error while getting taxRates for store [" + store.getCode() + "]", e);
			throw new ServiceRuntimeException("Error while getting taxRates for store [" + store.getCode() + "]", e);
		}

	}

}
