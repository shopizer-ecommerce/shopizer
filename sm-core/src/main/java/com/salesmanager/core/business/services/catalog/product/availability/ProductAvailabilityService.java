package com.salesmanager.core.business.services.catalog.product.availability;

import com.salesmanager.core.business.exception.ServiceException;
import com.salesmanager.core.business.services.common.generic.SalesManagerEntityService;
import com.salesmanager.core.model.catalog.product.availability.ProductAvailability;

public interface ProductAvailabilityService extends
		SalesManagerEntityService<Long, ProductAvailability> {

	void saveOrUpdate(ProductAvailability availability) throws ServiceException;

}
