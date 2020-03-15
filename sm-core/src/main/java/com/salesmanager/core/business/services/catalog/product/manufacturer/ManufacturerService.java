package com.salesmanager.core.business.services.catalog.product.manufacturer;

import java.util.List;
import org.springframework.data.domain.Page;
import com.salesmanager.core.business.exception.ServiceException;
import com.salesmanager.core.business.services.common.generic.SalesManagerEntityService;
import com.salesmanager.core.model.catalog.category.Category;
import com.salesmanager.core.model.catalog.product.manufacturer.Manufacturer;
import com.salesmanager.core.model.catalog.product.manufacturer.ManufacturerDescription;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;

public interface ManufacturerService extends SalesManagerEntityService<Long, Manufacturer> {

	List<Manufacturer> listByStore(MerchantStore store, Language language)
			throws ServiceException;

	List<Manufacturer> listByStore(MerchantStore store) throws ServiceException;
	
	Page<Manufacturer> listByStore(MerchantStore store, Language language, int page, int count) throws ServiceException;

	Page<Manufacturer> listByStore(MerchantStore store, Language language, String name, int page, int count) throws ServiceException;
	
	void saveOrUpdate(Manufacturer manufacturer) throws ServiceException;
	
	void addManufacturerDescription(Manufacturer manufacturer, ManufacturerDescription description) throws ServiceException;
	
	Long getCountManufAttachedProducts( Manufacturer manufacturer )  throws ServiceException;
	
	void delete(Manufacturer manufacturer) throws ServiceException;
	
	Manufacturer getByCode(MerchantStore store, String code);

	/**
	 * List manufacturers by products from a given list of categories
	 * @param store
	 * @param ids
	 * @param language
	 * @return
	 * @throws ServiceException
	 */
	List<Manufacturer> listByProductsByCategoriesId(MerchantStore store,
			List<Long> ids, Language language) throws ServiceException;
	
	/**
	 * List by product in category lineage
	 * @param store
	 * @param category
	 * @param language
	 * @return
	 * @throws ServiceException
	 */
	List<Manufacturer> listByProductsInCategory(MerchantStore store,
        Category category, Language language) throws ServiceException;
	
	public Page<Manufacturer> listByStore(MerchantStore store, String name,
	      int page, int count) throws ServiceException;
	
	int count(MerchantStore store);

	
}
