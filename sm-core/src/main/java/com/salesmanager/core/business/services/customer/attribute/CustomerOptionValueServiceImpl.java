package com.salesmanager.core.business.services.customer.attribute;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.salesmanager.core.business.exception.ServiceException;
import com.salesmanager.core.business.repositories.customer.attribute.CustomerOptionValueRepository;
import com.salesmanager.core.business.services.common.generic.SalesManagerEntityServiceImpl;
import com.salesmanager.core.model.customer.attribute.CustomerAttribute;
import com.salesmanager.core.model.customer.attribute.CustomerOptionSet;
import com.salesmanager.core.model.customer.attribute.CustomerOptionValue;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;


@Service("customerOptionValueService")
public class CustomerOptionValueServiceImpl extends
		SalesManagerEntityServiceImpl<Long, CustomerOptionValue> implements
		CustomerOptionValueService {

	@Inject
	private CustomerAttributeService customerAttributeService;
	
	private CustomerOptionValueRepository customerOptionValueRepository;
	
	@Inject
	private CustomerOptionSetService customerOptionSetService;
	
	@Inject
	public CustomerOptionValueServiceImpl(
			CustomerOptionValueRepository customerOptionValueRepository) {
			super(customerOptionValueRepository);
			this.customerOptionValueRepository = customerOptionValueRepository;
	}
	
	
	@Override
	public List<CustomerOptionValue> listByStore(MerchantStore store, Language language) throws ServiceException {
		
		return customerOptionValueRepository.findByStore(store.getId(), language.getId());
	}
	


	
	@Override
	public void saveOrUpdate(CustomerOptionValue entity) throws ServiceException {
		
		
		//save or update (persist and attach entities
		if(entity.getId()!=null && entity.getId()>0) {

			super.update(entity);
			
		} else {
			
			super.save(entity);
			
		}
		
	}
	
	
	public void delete(CustomerOptionValue customerOptionValue) throws ServiceException {
		
		//remove all attributes having this option
		List<CustomerAttribute> attributes = customerAttributeService.getByCustomerOptionValueId(customerOptionValue.getMerchantStore(), customerOptionValue.getId());
		
		for(CustomerAttribute attribute : attributes) {
			customerAttributeService.delete(attribute);
		}
		
		List<CustomerOptionSet> optionSets = customerOptionSetService.listByOptionValue(customerOptionValue, customerOptionValue.getMerchantStore());
		
		for(CustomerOptionSet optionSet : optionSets) {
			customerOptionSetService.delete(optionSet);
		}
		
		CustomerOptionValue option = super.getById(customerOptionValue.getId());
		
		//remove option
		super.delete(option);
		
	}
	
	@Override
	public CustomerOptionValue getByCode(MerchantStore store, String optionValueCode) {
		return customerOptionValueRepository.findByCode(store.getId(), optionValueCode);
	}



}
