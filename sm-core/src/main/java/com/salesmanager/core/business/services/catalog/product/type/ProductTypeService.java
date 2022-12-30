package com.salesmanager.core.business.services.catalog.product.type;

import java.util.List;

import org.springframework.data.domain.Page;

import com.salesmanager.core.business.exception.ServiceException;
import com.salesmanager.core.business.services.common.generic.SalesManagerEntityService;
import com.salesmanager.core.model.catalog.product.type.ProductType;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;

public interface ProductTypeService extends SalesManagerEntityService<Long, ProductType> {

	ProductType getProductType(String productTypeCode);
	Page<ProductType> getByMerchant(MerchantStore store, Language language, int page, int count) throws ServiceException;
    ProductType getByCode(String code, MerchantStore store, Language language) throws ServiceException;
    ProductType getById(Long id, MerchantStore store, Language language) throws ServiceException;
    ProductType getById(Long id, MerchantStore store) throws ServiceException;
    void update(String code, MerchantStore store, ProductType type) throws ServiceException;
    ProductType saveOrUpdate(ProductType productType) throws ServiceException;
    List<ProductType> listProductTypes(List<Long> ids, MerchantStore store, Language language) throws ServiceException;
    

}
