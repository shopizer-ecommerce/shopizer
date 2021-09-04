package com.salesmanager.core.business.services.customer.optin;



import javax.inject.Inject;

import org.apache.commons.lang3.Validate;
import org.springframework.stereotype.Service;

import com.salesmanager.core.business.exception.ServiceException;
import com.salesmanager.core.business.repositories.customer.optin.CustomerOptinRepository;
import com.salesmanager.core.business.services.common.generic.SalesManagerEntityServiceImpl;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.system.optin.CustomerOptin;


@Service
public class CustomerOptinServiceImpl extends SalesManagerEntityServiceImpl<Long, CustomerOptin> implements CustomerOptinService {
	
	
	private CustomerOptinRepository customerOptinRepository;
	
	
	@Inject
	public CustomerOptinServiceImpl(CustomerOptinRepository customerOptinRepository) {
		super(customerOptinRepository);
		this.customerOptinRepository = customerOptinRepository;
	}

	@Override
	public void optinCumtomer(CustomerOptin optin) throws ServiceException {
		Validate.notNull(optin,"CustomerOptin must not be null");
		
		customerOptinRepository.save(optin);
		

	}

	@Override
	public void optoutCumtomer(CustomerOptin optin) throws ServiceException {
		Validate.notNull(optin,"CustomerOptin must not be null");
		
		customerOptinRepository.delete(optin);

	}

	@Override
	public CustomerOptin findByEmailAddress(MerchantStore store, String emailAddress, String code) throws ServiceException {
		return customerOptinRepository.findByMerchantAndCodeAndEmail(store.getId(), code, emailAddress);
	}

}
