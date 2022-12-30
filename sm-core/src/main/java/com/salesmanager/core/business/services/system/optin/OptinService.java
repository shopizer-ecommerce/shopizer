package com.salesmanager.core.business.services.system.optin;

import com.salesmanager.core.business.exception.ServiceException;
import com.salesmanager.core.business.services.common.generic.SalesManagerEntityService;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.system.optin.Optin;
import com.salesmanager.core.model.system.optin.OptinType;

/**
 * Registers Optin events
 * @author carlsamson
 *
 */
public interface OptinService extends SalesManagerEntityService<Long, Optin> {
	
	
	Optin getOptinByMerchantAndType(MerchantStore store, OptinType type) throws ServiceException;
	Optin getOptinByCode(MerchantStore store, String code) throws ServiceException;

}
