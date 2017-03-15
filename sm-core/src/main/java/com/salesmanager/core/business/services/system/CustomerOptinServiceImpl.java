package com.salesmanager.core.business.services.system;

import java.util.List;
import com.salesmanager.core.business.repositories.system.CustomerOptinRepository;
import com.salesmanager.core.business.services.common.generic.SalesManagerEntityServiceImpl;
import com.salesmanager.core.model.system.optin.CustomerOptin;

public class CustomerOptinServiceImpl extends SalesManagerEntityServiceImpl<Long, CustomerOptin> implements CustomerOptinService{
	
	private CustomerOptinRepository customerOptinRepository; 
	
	public CustomerOptinServiceImpl(CustomerOptinRepository customerOptinRepository) {
		super(customerOptinRepository);
		this.customerOptinRepository = customerOptinRepository;
	}

	@Override
	public List<CustomerOptin> findByCode(Integer storeId, String code) 	
	{
		return customerOptinRepository.findByCode(storeId, code);
	}

}
