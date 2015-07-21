package com.salesmanager.core.business.catalog.product.service.attribute;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.salesmanager.core.business.catalog.product.dao.attribute.ProductAttributeDao;
import com.salesmanager.core.business.catalog.product.model.Product;
import com.salesmanager.core.business.catalog.product.model.attribute.ProductAttribute;
import com.salesmanager.core.business.catalog.product.model.price.ProductPrice;
import com.salesmanager.core.business.generic.exception.ServiceException;
import com.salesmanager.core.business.generic.service.SalesManagerEntityServiceImpl;
import com.salesmanager.core.business.merchant.model.MerchantStore;
import com.salesmanager.core.business.reference.language.model.Language;

@Service("productAttributeService")
public class ProductAttributeServiceImpl extends
		SalesManagerEntityServiceImpl<Long, ProductAttribute> implements ProductAttributeService {
	
	private ProductAttributeDao productAttributeDao;

	@Autowired
	public ProductAttributeServiceImpl(ProductAttributeDao productAttributeDao) {
		super(productAttributeDao);
		this.productAttributeDao = productAttributeDao;
	}
	
	@Override
	public ProductAttribute getById(Long id) {
		
		return productAttributeDao.getById(id);
		
	}
	
	
	@Override
	public List<ProductAttribute> getByOptionId(MerchantStore store,
			Long id) throws ServiceException {
		
		return productAttributeDao.getByOptionId(store, id);
		
	}
	
	@Override
	public List<ProductAttribute> getByAttributeIds(MerchantStore store,
			List<Long> ids) throws ServiceException {
		
		return productAttributeDao.getByAttributeIds(store, ids);
		
	}
	
	@Override
	public List<ProductAttribute> getByOptionValueId(MerchantStore store,
			Long id) throws ServiceException {
		
		return productAttributeDao.getByOptionValueId(store, id);
		
	}
	
	/**
	 * Returns all product attributes
	 */
	@Override
	public List<ProductAttribute> getByProductId(MerchantStore store,
			Product product, Language language) throws ServiceException {
		return productAttributeDao.getByProduct(store, product, language);
		
	}


	@Override
	public void saveOrUpdate(ProductAttribute productAttribute)
			throws ServiceException {
		if(productAttribute.getId()!=null && productAttribute.getId()>0) {
			productAttributeDao.update(productAttribute);
		} else {
			productAttributeDao.save(productAttribute);
		}
		
	}
	
	@Override
	public void delete(ProductAttribute attribute) throws ServiceException {
		
		//override method, this allows the error that we try to remove a detached instance
		attribute = this.getById(attribute.getId());
		super.delete(attribute);
		
	}

}
