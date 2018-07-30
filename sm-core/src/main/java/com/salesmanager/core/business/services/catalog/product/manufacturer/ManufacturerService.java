package com.salesmanager.core.business.services.catalog.product.manufacturer;

import com.salesmanager.core.business.exception.ServiceException;
import com.salesmanager.core.business.services.common.generic.SalesManagerEntityService;
import com.salesmanager.core.model.catalog.product.manufacturer.Manufacturer;
import com.salesmanager.core.model.catalog.product.manufacturer.ManufacturerDescription;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;

import java.util.List;

public interface ManufacturerService extends SalesManagerEntityService<Long, Manufacturer> {

    List<Manufacturer> listByStore(MerchantStore store, Language language)
            throws ServiceException;

    List<Manufacturer> listByStore(MerchantStore store) throws ServiceException;

    void saveOrUpdate(Manufacturer manufacturer) throws ServiceException;

    void addManufacturerDescription(Manufacturer manufacturer, ManufacturerDescription description) throws ServiceException;

    Long getCountManufAttachedProducts(Manufacturer manufacturer) throws ServiceException;

    void delete(Manufacturer manufacturer) throws ServiceException;

    Manufacturer getByCode(MerchantStore store, String code);

    /**
     * List manufacturers by products from a given list of categories
     */
    List<Manufacturer> listByProductsByCategoriesId(MerchantStore store,
                                                    List<Long> ids, Language language) throws ServiceException;

}
