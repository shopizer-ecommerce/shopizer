package com.salesmanager.core.business.catalog.product.service.manufacturer;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.salesmanager.core.business.catalog.product.dao.manufacturer.ManufacturerDao;
import com.salesmanager.core.business.catalog.product.model.Product;
import com.salesmanager.core.business.catalog.product.model.description.ProductDescription;
import com.salesmanager.core.business.catalog.product.model.manufacturer.Manufacturer;
import com.salesmanager.core.business.catalog.product.model.manufacturer.ManufacturerDescription;
import com.salesmanager.core.business.customer.model.Customer;
import com.salesmanager.core.business.customer.service.CustomerServiceImpl;
import com.salesmanager.core.business.generic.exception.ServiceException;
import com.salesmanager.core.business.generic.service.SalesManagerEntityServiceImpl;
import com.salesmanager.core.business.merchant.model.MerchantStore;
import com.salesmanager.core.business.reference.language.model.Language;
import com.salesmanager.core.business.search.service.SearchService;



@Service("manufacturerService")
public class ManufacturerServiceImpl extends
		SalesManagerEntityServiceImpl<Long, Manufacturer> implements ManufacturerService {

	@Autowired
	SearchService searchService;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ManufacturerServiceImpl.class);
	private ManufacturerDao manufacturerDao;
	
	@Autowired
	public ManufacturerServiceImpl(
		ManufacturerDao manufacturerDao) {
		super(manufacturerDao);
		this.manufacturerDao = manufacturerDao;		
	}
	
	@Override 
	public void delete(Manufacturer manufacturer) throws ServiceException{
		manufacturer =  this.getById(manufacturer.getId() );
		super.delete( manufacturer );
	}
	
	@Override
	public int getCountManufAttachedProducts( Manufacturer manufacturer ) throws ServiceException {
		return manufacturerDao.getCountManufAttachedProducts( manufacturer );
	}
	
	
	@Override
	public List<Manufacturer> listByStore(MerchantStore store, Language language) throws ServiceException {
		return manufacturerDao.listByStore(store, language);
	}
	
	@Override
	public List<Manufacturer> listByStore(MerchantStore store) throws ServiceException {
		return manufacturerDao.listByStore(store);
	}
	
	@Override
	public List<Manufacturer> listByProductsByCategoriesId(MerchantStore store, List<Long> ids, Language language) throws ServiceException {
		return manufacturerDao.listByProductsByCategoriesId(store, ids, language);
	}

	@Override
	public void addManufacturerDescription(Manufacturer manufacturer, ManufacturerDescription description)
			throws ServiceException {
		
		
		if(manufacturer.getDescriptions()==null) {
			manufacturer.setDescriptions(new HashSet<ManufacturerDescription>());
		}
		
		manufacturer.getDescriptions().add(description);
		description.setManufacturer(manufacturer);
		update(manufacturer);
	}
	
	@Override	
	public void saveOrUpdate(Manufacturer manufacturer) throws ServiceException {

		LOGGER.debug("Creating Manufacturer");
		
		if(manufacturer.getId()!=null && manufacturer.getId()>0) {
		   super.update(manufacturer);  
			
		} else {						
		   super.create(manufacturer);

		}
	}

	@Override
	public Manufacturer getByCode(MerchantStore store, String code) {
		return manufacturerDao.getByCode(store, code);
	}
}
