package com.salesmanager.core.business.shipping.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.salesmanager.core.business.generic.service.SalesManagerEntityServiceImpl;
import com.salesmanager.core.business.merchant.model.MerchantStore;
import com.salesmanager.core.business.shipping.dao.ShippingOriginDAO;
import com.salesmanager.core.business.shipping.model.ShippingOrigin;

@Service("shippingOriginService")
public class ShippingOriginServiceImpl extends SalesManagerEntityServiceImpl<Long, ShippingOrigin> implements ShippingOriginService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ShippingOriginServiceImpl.class);
	
	private ShippingOriginDAO shippingOriginDAO;
	

	@Autowired
	public ShippingOriginServiceImpl(ShippingOriginDAO shippingOriginDAO) {
		super(shippingOriginDAO);
		this.shippingOriginDAO = shippingOriginDAO;
	}


	@Override
	public ShippingOrigin getByStore(MerchantStore store) {
		// TODO Auto-generated method stub
		return shippingOriginDAO.getByStore(store);
	}
	

}
