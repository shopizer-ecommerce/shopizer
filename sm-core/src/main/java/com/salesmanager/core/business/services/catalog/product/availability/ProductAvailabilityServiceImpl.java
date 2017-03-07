package com.salesmanager.core.business.services.catalog.product.availability;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.salesmanager.core.business.exception.ServiceException;
import com.salesmanager.core.business.repositories.catalog.product.availability.ProductAvailabilityRepository;
import com.salesmanager.core.business.services.common.generic.SalesManagerEntityServiceImpl;
import com.salesmanager.core.model.catalog.product.availability.ProductAvailability;

@Service("productAvailabilityService")
public class ProductAvailabilityServiceImpl extends
		SalesManagerEntityServiceImpl<Long, ProductAvailability> implements
		ProductAvailabilityService {

	
	private ProductAvailabilityRepository productAvailabilityRepository;
	
	@Inject
	public ProductAvailabilityServiceImpl(
			ProductAvailabilityRepository productAvailabilityRepository) {
			super(productAvailabilityRepository);
			this.productAvailabilityRepository = productAvailabilityRepository;
	}
	
	
	@Override
	public void saveOrUpdate(ProductAvailability availability) throws ServiceException {
		
		if(availability.getId()!=null && availability.getId()>0) {
			
			this.update(availability);
			
		} else {
			this.create(availability);
		}
		
	}



}
