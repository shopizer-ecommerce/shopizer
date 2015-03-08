package com.salesmanager.core.business.catalog.product.service.type;

import com.salesmanager.core.business.catalog.product.model.type.ProductType;
import com.salesmanager.core.business.generic.exception.ServiceException;
import com.salesmanager.core.business.generic.service.SalesManagerEntityService;

public interface ProductTypeService extends SalesManagerEntityService<Long, ProductType> {

	ProductType getProductType(String productTypeCode) throws ServiceException;

}
