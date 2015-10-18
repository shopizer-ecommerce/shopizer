package com.salesmanager.core.business.catalog.product.service.relationship;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.salesmanager.core.business.catalog.product.dao.relationship.ProductRelationshipDao;
import com.salesmanager.core.business.catalog.product.model.Product;
import com.salesmanager.core.business.catalog.product.model.relationship.ProductRelationship;
import com.salesmanager.core.business.catalog.product.model.relationship.ProductRelationshipType;
import com.salesmanager.core.business.generic.exception.ServiceException;
import com.salesmanager.core.business.generic.service.SalesManagerEntityServiceImpl;
import com.salesmanager.core.business.merchant.model.MerchantStore;
import com.salesmanager.core.business.reference.language.model.Language;

@Service("productRelationshipService")
public class ProductRelationshipServiceImpl extends
		SalesManagerEntityServiceImpl<Long, ProductRelationship> implements
		ProductRelationshipService {

	
	private ProductRelationshipDao productRelationshipDao;
	
	@Autowired
	public ProductRelationshipServiceImpl(
			ProductRelationshipDao productRelationshipDao) {
			super(productRelationshipDao);
			this.productRelationshipDao = productRelationshipDao;
	}
	
	@Override
	public void saveOrUpdate(ProductRelationship relationship) throws ServiceException {
		
		if(relationship.getId()!=null && relationship.getId()>0) {
			
			this.update(relationship);
			
		} else {
			this.create(relationship);
		}
		
	}
	
	
	@Override
	public void addGroup(MerchantStore store, String groupName) throws ServiceException {
		ProductRelationship relationship = new ProductRelationship();
		relationship.setCode(groupName);
		relationship.setStore(store);
		relationship.setActive(true);
		this.save(relationship);
	}
	
	@Override
	public List<ProductRelationship> getGroups(MerchantStore store) {
		return productRelationshipDao.getGroups(store);
	}
	
	@Override
	public void deleteGroup(MerchantStore store, String groupName) throws ServiceException {
		List<ProductRelationship> entities = productRelationshipDao.getByGroup(store, groupName);
		for(ProductRelationship relation : entities) {
			this.delete(relation);
		}
	}
	
	@Override
	public void deactivateGroup(MerchantStore store, String groupName) throws ServiceException {
		List<ProductRelationship> entities = productRelationshipDao.getByGroup(store, groupName);
		for(ProductRelationship relation : entities) {
			relation.setActive(false);
			this.saveOrUpdate(relation);
		}
	}
	
	@Override
	public void activateGroup(MerchantStore store, String groupName) throws ServiceException {
		List<ProductRelationship> entities = this.getByGroup(store, groupName);
		for(ProductRelationship relation : entities) {
			relation.setActive(true);
			this.saveOrUpdate(relation);
		}
	}
	
	public void delete(ProductRelationship relationship) throws ServiceException {
		
		//throws detached exception so need to query first
		relationship = this.getById(relationship.getId());
		super.delete(relationship);
		
		
	}
	
	@Override
	public List<ProductRelationship> listByProduct(Product product) throws ServiceException {

		return productRelationshipDao.listByProducts(product);

	}
	
	
	@Override
	public List<ProductRelationship> getByType(MerchantStore store, Product product, ProductRelationshipType type, Language language) throws ServiceException {

		return productRelationshipDao.getByType(store, type.name(), product, language);

	}
	
	@Override
	public List<ProductRelationship> getByType(MerchantStore store, ProductRelationshipType type, Language language) throws ServiceException {
		return productRelationshipDao.getByType(store, type.name(), language);
	}
	
	@Override
	public List<ProductRelationship> getByType(MerchantStore store, ProductRelationshipType type) throws ServiceException {

		return productRelationshipDao.getByType(store, type.name());

	}
	
	@Override
	public List<ProductRelationship> getByGroup(MerchantStore store, String groupName) throws ServiceException {

		return productRelationshipDao.getByType(store, groupName);

	}
	
	@Override
	public List<ProductRelationship> getByGroup(MerchantStore store, String groupName, Language language) throws ServiceException {

		return productRelationshipDao.getByType(store, groupName, language);

	}
	
	@Override
	public List<ProductRelationship> getByType(MerchantStore store, Product product, ProductRelationshipType type) throws ServiceException {
		

		return productRelationshipDao.getByType(store, type.name(), product);
				
		
	}



}
