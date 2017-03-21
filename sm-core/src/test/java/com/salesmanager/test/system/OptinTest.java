package com.salesmanager.test.system;

import com.salesmanager.core.business.exception.ServiceException;
import com.salesmanager.core.business.services.system.OptinService;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.system.optin.Optin;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.util.Assert;

import java.util.Date;

import javax.inject.Inject;

@Ignore
public class OptinTest extends com.salesmanager.test.common.AbstractSalesManagerCoreTestCase{
	
	MerchantStore store;

	@Inject
    protected OptinService optinService;
	
	@Before
	public void setUpClass() throws ServiceException {
		store = merchantService.getByCode(MerchantStore.DEFAULT_STORE);	
	}
	
	@Test
	public void createOptin() throws ServiceException
	{
		
		Optin optin = new Optin();
		optin.setCode("0");
		optin.setDescription("optin description");
		optin.setEndDate(new Date());
		optin.setMerchant(store);
		optin.setStartDate(new Date());
		optinService.create(optin);
	}
	
	@Test
	public void updateOptin() throws ServiceException
	{
		
		Optin optin = new Optin();
		optin.setCode("0");
		optin.setDescription("optin description updated");
		optin.setEndDate(new Date());
		optin.setMerchant(store);
		optin.setStartDate(new Date());
		optinService.update(optin);
	}
	
	@Test
	public void findByMerchant() throws ServiceException
	{
		
		Assert.notEmpty(optinService.findByMerchant(store.getId()));
	}
	
	@Test
	public void deleteOptin() throws ServiceException
	{
		
		Optin optin = new Optin();
		optin.setCode("0");
		optin.setDescription("optin description updated");
		optin.setEndDate(new Date());
		optin.setMerchant(store);
		optin.setStartDate(new Date());
		optinService.delete(optin);
	}
}
