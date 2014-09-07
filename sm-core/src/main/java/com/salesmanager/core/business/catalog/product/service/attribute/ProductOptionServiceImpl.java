package com.salesmanager.core.business.catalog.product.service.attribute;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.salesmanager.core.business.catalog.product.dao.attribute.ProductOptionDao;
import com.salesmanager.core.business.catalog.product.model.attribute.ProductAttribute;
import com.salesmanager.core.business.catalog.product.model.attribute.ProductOption;
import com.salesmanager.core.business.generic.exception.ServiceException;
import com.salesmanager.core.business.generic.service.SalesManagerEntityServiceImpl;
import com.salesmanager.core.business.merchant.model.MerchantStore;
import com.salesmanager.core.business.reference.language.model.Language;

@Service("productOptionService")
public class ProductOptionServiceImpl extends
		SalesManagerEntityServiceImpl<Long, ProductOption> implements ProductOptionService {

	
	private ProductOptionDao productOptionDao;
	
	@Autowired
	private ProductAttributeService productAttributeService;
	
	@Autowired
	public ProductOptionServiceImpl(
			ProductOptionDao productOptionDao) {
			super(productOptionDao);
			this.productOptionDao = productOptionDao;
	}
	
	@Override
	public List<ProductOption> listByStore(MerchantStore store, Language language) throws ServiceException {
		
		
		return productOptionDao.listByStore(store, language);
		
		
	}
	
	@Override
	public List<ProductOption> listReadOnly(MerchantStore store, Language language) throws ServiceException {

		return productOptionDao.getReadOnly(store, language);
		
		
	}
	

	
	@Override
	public List<ProductOption> getByName(MerchantStore store, String name, Language language) throws ServiceException {
		
		try {
			return productOptionDao.getByName(store, name, language);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
		
		
	}
	
	@Override
	public void saveOrUpdate(ProductOption entity) throws ServiceException {
		
		
		//save or update (persist and attach entities
		if(entity.getId()!=null && entity.getId()>0) {
			super.update(entity);
		} else {
			super.save(entity);
		}
		
	}
	
	@Override
	public void delete(ProductOption entity) throws ServiceException {
		
		//remove all attributes having this option
		List<ProductAttribute> attributes = productAttributeService.getByOptionId(entity.getMerchantStore(), entity.getId());
		
		for(ProductAttribute attribute : attributes) {
			productAttributeService.delete(attribute);
		}
		
		ProductOption option = this.getById(entity.getId());
		
		//remove option
		super.delete(option);
		
	}
	
	@Override
	public ProductOption getByCode(MerchantStore store, String optionCode) {
		return productOptionDao.getByCode(store, optionCode);
	}
	

	




}
