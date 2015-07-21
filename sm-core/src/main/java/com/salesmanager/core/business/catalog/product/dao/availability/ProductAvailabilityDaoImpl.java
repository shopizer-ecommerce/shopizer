package com.salesmanager.core.business.catalog.product.dao.availability;

import org.springframework.stereotype.Repository;

import com.salesmanager.core.business.catalog.product.model.availability.ProductAvailability;
import com.salesmanager.core.business.generic.dao.SalesManagerEntityDaoImpl;

@Repository("productAvailabilityDao")
public class ProductAvailabilityDaoImpl extends SalesManagerEntityDaoImpl<Long, ProductAvailability>
		implements ProductAvailabilityDao {



}
