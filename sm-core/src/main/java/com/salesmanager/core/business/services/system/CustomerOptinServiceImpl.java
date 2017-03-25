package com.salesmanager.core.business.services.system;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.salesmanager.core.business.repositories.system.CustomerOptinRepository;
import com.salesmanager.core.business.services.common.generic.SalesManagerEntityServiceImpl;
import com.salesmanager.core.model.system.optin.CustomerOptin;
@Service("customerOptinService")
public class CustomerOptinServiceImpl extends SalesManagerEntityServiceImpl<Long, CustomerOptin> implements CustomerOptinService{
	
	private CustomerOptinRepository customerOptinRepository; 
	@Inject
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
