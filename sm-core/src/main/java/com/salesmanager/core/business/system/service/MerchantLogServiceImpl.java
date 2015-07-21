package com.salesmanager.core.business.system.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.salesmanager.core.business.generic.service.SalesManagerEntityServiceImpl;
import com.salesmanager.core.business.system.dao.MerchantLogDao;
import com.salesmanager.core.business.system.model.MerchantLog;

@Service("merchantLogService")
public class MerchantLogServiceImpl extends
		SalesManagerEntityServiceImpl<Long, MerchantLog> implements
		MerchantLogService {
	
	@SuppressWarnings("unused")
	private static final Logger LOGGER = LoggerFactory.getLogger(MerchantLogServiceImpl.class);


	
	private MerchantLogDao merchantLogDao;
	
	@Autowired
	public MerchantLogServiceImpl(
			MerchantLogDao merchantLogDao) {
			super(merchantLogDao);
			this.merchantLogDao = merchantLogDao;
	}






}
