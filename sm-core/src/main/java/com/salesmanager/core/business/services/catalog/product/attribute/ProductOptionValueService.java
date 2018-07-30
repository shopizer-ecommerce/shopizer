package com.salesmanager.core.business.services.catalog.product.attribute;

import com.salesmanager.core.business.exception.ServiceException;
import com.salesmanager.core.business.services.common.generic.SalesManagerEntityService;
import com.salesmanager.core.model.catalog.product.attribute.ProductOptionValue;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;

import java.util.List;

public interface ProductOptionValueService extends SalesManagerEntityService<Long, ProductOptionValue> {

    void saveOrUpdate(ProductOptionValue entity) throws ServiceException;

    List<ProductOptionValue> getByName(MerchantStore store, String name,
                                       Language language) throws ServiceException;


    List<ProductOptionValue> listByStore(MerchantStore store, Language language)
            throws ServiceException;

    List<ProductOptionValue> listByStoreNoReadOnly(MerchantStore store,
                                                   Language language) throws ServiceException;

    ProductOptionValue getByCode(MerchantStore store, String optionValueCode);

    ProductOptionValue getById(MerchantStore store, Long optionValueId);

}
