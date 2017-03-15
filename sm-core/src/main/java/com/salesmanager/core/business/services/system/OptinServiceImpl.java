package com.salesmanager.core.business.services.system;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.salesmanager.core.business.repositories.system.OptinRepository;
import com.salesmanager.core.business.services.common.generic.SalesManagerEntityServiceImpl;
import com.salesmanager.core.model.system.optin.Optin;
@Service("optinService")
public class OptinServiceImpl extends SalesManagerEntityServiceImpl<Long, Optin> implements OptinService{

	private OptinRepository optinRepository;
	
	@Inject
	public OptinServiceImpl(OptinRepository optinRepository) {
		super(optinRepository);
		this.optinRepository = optinRepository;
	}

	@Override
	public List<Optin> findByMerchant(Integer storeId) {
		return optinRepository.findByMerchant(storeId);
	}

}
