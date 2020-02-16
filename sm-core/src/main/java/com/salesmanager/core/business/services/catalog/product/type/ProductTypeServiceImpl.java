package com.salesmanager.core.business.services.catalog.product.type;

import java.util.List;
import javax.inject.Inject;
import org.springframework.stereotype.Service;
import com.salesmanager.core.business.exception.ServiceException;
import com.salesmanager.core.business.repositories.catalog.product.type.ProductTypeRepository;
import com.salesmanager.core.business.services.common.generic.SalesManagerEntityServiceImpl;
import com.salesmanager.core.model.catalog.product.type.ProductType;
import com.salesmanager.core.model.merchant.MerchantStore;

import com.salesmanager.core.model.reference.language.Language;

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

    @Override
    public List<ProductType> getByMerchant(String merchant, Language language) throws ServiceException {
      return productTypeRepository.findAll();
    }


  @Override
  public List<ProductType> getByMerchant(MerchantStore store, Language language)
      throws ServiceException {
    // TODO Auto-generated method stub
    return null;
  }





}
