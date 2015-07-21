package com.salesmanager.core.business.catalog.product.dao.type;

import org.springframework.stereotype.Repository;

import com.salesmanager.core.business.catalog.product.model.type.ProductType;
import com.salesmanager.core.business.generic.dao.SalesManagerEntityDaoImpl;

@Repository("productTypeDao")
public class ProductTypeDaoImpl extends SalesManagerEntityDaoImpl<Long, ProductType>
		implements ProductTypeDao {



}
