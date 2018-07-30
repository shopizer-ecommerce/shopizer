package com.salesmanager.core.business.services.catalog.product.type;

import com.salesmanager.core.business.exception.ServiceException;
import com.salesmanager.core.business.repositories.catalog.product.type.ProductTypeRepository;
import com.salesmanager.core.business.services.common.generic.SalesManagerEntityServiceImpl;
import com.salesmanager.core.model.catalog.product.type.ProductType;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

@Service("productTypeService")
public class ProductTypeServiceImpl extends SalesManagerEntityServiceImpl<Long, ProductType>
        implements ProductTypeService {

    private ProductTypeRepository productTypeRepository;

    @Inject
    public ProductTypeServiceImpl(
            ProductTypeRepository productTypeRepository) {
        super(productTypeRepository);
        this.productTypeRepository = productTypeRepository;
    }

    @Override
    public ProductType getProductType(String productTypeCode) throws ServiceException {

        return productTypeRepository.findByCode(productTypeCode);

    }


}
