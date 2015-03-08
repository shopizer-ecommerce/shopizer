package com.salesmanager.core.business.catalog.product.service.type;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.salesmanager.core.business.catalog.product.dao.type.ProductTypeDao;
import com.salesmanager.core.business.catalog.product.model.type.ProductType;
import com.salesmanager.core.business.catalog.product.model.type.ProductType_;
import com.salesmanager.core.business.generic.exception.ServiceException;
import com.salesmanager.core.business.generic.service.SalesManagerEntityServiceImpl;

@Service("productTypeService")
public class ProductTypeServiceImpl extends SalesManagerEntityServiceImpl<Long, ProductType>
		implements ProductTypeService {

	private ProductTypeDao productTypeDao;
	
	@Autowired
	public ProductTypeServiceImpl(
			ProductTypeDao productTypeDao) {
			super(productTypeDao);
			this.productTypeDao = productTypeDao;
	}
	
	@Override
	public ProductType getProductType(String productTypeCode) throws ServiceException {
		
		return getByField(ProductType_.code, productTypeCode);
		
	}



}
