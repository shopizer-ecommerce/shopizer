package com.salesmanager.core.business.catalog.product.service.availability;

import com.salesmanager.core.business.catalog.product.model.availability.ProductAvailability;
import com.salesmanager.core.business.generic.exception.ServiceException;
import com.salesmanager.core.business.generic.service.SalesManagerEntityService;

public interface ProductAvailabilityService extends
		SalesManagerEntityService<Long, ProductAvailability> {

	void saveOrUpdate(ProductAvailability availability) throws ServiceException;

}
