package com.salesmanager.core.business.catalog.category.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.salesmanager.core.business.catalog.category.dao.CategoryDao;
import com.salesmanager.core.business.catalog.category.model.Category;
import com.salesmanager.core.business.catalog.category.model.CategoryDescription;
import com.salesmanager.core.business.catalog.product.model.Product;
import com.salesmanager.core.business.catalog.product.service.ProductService;
import com.salesmanager.core.business.generic.exception.ServiceException;
import com.salesmanager.core.business.generic.service.SalesManagerEntityServiceImpl;
import com.salesmanager.core.business.merchant.model.MerchantStore;
import com.salesmanager.core.business.reference.language.model.Language;
import com.salesmanager.core.constants.Constants;

@Service("categoryService")
public class CategoryServiceImpl extends SalesManagerEntityServiceImpl<Long, Category> implements CategoryService {
	
	private CategoryDao categoryDao;
	

	  
	  @Autowired
	  private ProductService productService;
	  

	
	@Autowired
	public CategoryServiceImpl(CategoryDao categoryDao) {
		super(categoryDao);
		
		this.categoryDao = categoryDao;
	}
	
	public void create(Category category) throws ServiceException {
		
		super.create(category);
		
		StringBuilder lineage = new StringBuilder();
		Category parent = category.getParent();
		if(parent!=null && parent.getId()!=null && parent.getId()!=0) {
			lineage.append(parent.getLineage()).append("/").append(parent.getId());
			category.setDepth(parent.getDepth()+1);
		} else {
			lineage.append("/");
			category.setDepth(0);
		}
		category.setLineage(lineage.toString());
		super.update(category);
		
		
	}
	
	@Override
	public List<Object[]> countProductsByCategories(MerchantStore store,
			List<Long> categoryIds) throws ServiceException {
		
		return categoryDao.countProductsByCategories(store, categoryIds);
		
	}
	
	@Override
	public List<Category> listByCodes(MerchantStore store, List<String> codes,
			Language language) {
		return categoryDao.getByCodes(store, codes, language);
	}
	
	@Override
	public List<Category> listByIds(MerchantStore store, List<Long> ids,
			Language language) {
		return categoryDao.getByIds(store, ids, language);
	}
	
	@Override
	public Category getByLanguage(long categoryId, Language language) {
		return categoryDao.getByLanguage(categoryId, language);
	}
	
	@Override
	public void saveOrUpdate(Category category) throws ServiceException {
		
		
		//save or update (persist and attach entities
		if(category.getId()!=null && category.getId()>0) {

			super.update(category);
			
		} else {
			
			super.save(category);
			
		}
		
	}

	@Override
	public List<Category> listByLineage(MerchantStore store, String lineage) throws ServiceException {
		try {
			return categoryDao.listByLineage(store, lineage);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
		
		
	}
	
	@Override
	public List<Category> listByLineage(String storeCode, String lineage) throws ServiceException {
		try {
			return categoryDao.listByLineage(storeCode, lineage);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
		
		
	}
	

	@Override
	public List<Category> listBySeUrl(MerchantStore store, String seUrl) throws ServiceException{
		
		try {
			return categoryDao.listBySeUrl(store, seUrl);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
		
	}
	
	@Override
	public Category getBySeUrl(MerchantStore store,String seUrl) {
		return categoryDao.getBySeUrl(store, seUrl);
	}
	
	
	@Override
	public Category getByCode(MerchantStore store, String code) throws ServiceException {
		
		try {
			return categoryDao.getByCode(store, code);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
		
	}
	
	@Override
	public Category getByCode(String storeCode, String code) throws ServiceException {
		
		try {
			return categoryDao.getByCode(storeCode, code);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
		
	}
	
	
	@Override
	public Category getById(Long id) {
		

			return categoryDao.getById(id);

		
	}
	
	@Override
	public List<Category> listByParent(Category category) throws ServiceException {
		
		try {
			return categoryDao.listByStoreAndParent(null, category);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
		
	}
	
	@Override
	public List<Category> listByStoreAndParent(MerchantStore store, Category category) throws ServiceException {
		
		try {
			return categoryDao.listByStoreAndParent(store, category);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
		
	}
	
	@Override
	public List<Category> listByParent(Category category, Language language) {
		Assert.notNull(category, "Category cannot be null");
		Assert.notNull(language, "Language cannot be null");
		Assert.notNull(category.getMerchantStore(), "category.merchantStore cannot be null");
		
		return categoryDao.listByParent(category, language);
	}

	
	@Override
	public void addCategoryDescription(Category category, CategoryDescription description)
			throws ServiceException {
		
		
		
		try {
			category.getDescriptions().add(description);
			description.setCategory(category);
			update(category);
		} catch (Exception e) {
			throw new ServiceException(e);
		}

	}

	
	//@Override
	public void delete(Category category) throws ServiceException {
		
		//get category with lineage (subcategories)
		StringBuilder lineage = new StringBuilder();
		lineage.append(category.getLineage()).append(category.getId()).append(Constants.SLASH);
		List<Category> categories = this.listByLineage(category.getMerchantStore(), lineage.toString());
		
		Category dbCategory = this.getById( category.getId() );
		
		
		if(dbCategory != null && dbCategory.getId().longValue() == category.getId().longValue() ) {			
			
			
			categories.add(dbCategory);
			
			
			Collections.reverse(categories);
			
			List<Long> categoryIds = new ArrayList<Long>();
	
				
			for(Category c : categories) {		
					categoryIds.add(c.getId());
			}

			List<Product> products = productService.getProducts(categoryIds);
			org.hibernate.Session session = this.categoryDao.getEntityManager().unwrap(org.hibernate.Session.class);//need to refresh the session to update all product categories

			
			for(Product product : products) {
				session.evict(product);//refresh product so we get all product categories
				Product dbProduct = productService.getById(product.getId());
				Set<Category> productCategories = dbProduct.getCategories();
				if(productCategories.size()>1) {
					for(Category c : categories) {
						productCategories.remove(c);
						productService.update(dbProduct);
					}
					
					if(product.getCategories()==null || product.getCategories().size()==0) {
						productService.delete(dbProduct);
					}
					
				} else {
					productService.delete(dbProduct);
				}
				
				
			}
			
			Category categ = this.getById(category.getId());
			categoryDao.delete(categ);
			
		}
		
	}



	@Override
	public CategoryDescription getDescription(Category category, Language language) {
		
		
		for (CategoryDescription description : category.getDescriptions()) {
			if (description.getLanguage().equals(language)) {
				return description;
			}
		}
		return null;
	}
	
	@Override
	public void addChild(Category parent, Category child) throws ServiceException {
		
		
		if(child==null || child.getMerchantStore()==null) {
			throw new ServiceException("Child category and merchant store should not be null");
		}
		
		try {
			
			if(parent==null) {
				
				//assign to root
				child.setParent(null);
				child.setDepth(0);
				//child.setLineage(new StringBuilder().append("/").append(child.getId()).append("/").toString());
				child.setLineage("/");
				
			} else {
				
				Category p = this.getById(parent.getId());//parent
				
				

				
				String lineage = p.getLineage();
				int depth = p.getDepth();//TODO sometimes null
				
				child.setParent(p);
				child.setDepth(depth+1);
				child.setLineage(new StringBuilder().append(lineage).append(p.getId()).append("/").toString());
				
				
			}
			

			update(child);
			StringBuilder childLineage = new StringBuilder();
			childLineage.append(child.getLineage()).append(child.getId()).append("/");
			List<Category> subCategories = listByLineage(child.getMerchantStore(),childLineage.toString());
			
			
			//ajust all sub categories lineages
			if(subCategories!=null && subCategories.size()>0) {
				for(Category subCategory : subCategories) {
					if(child.getId()!=subCategory.getId()) {
						addChild(child, subCategory);
					}
				}
				
			}
		} catch (Exception e) {
			throw new ServiceException(e);
		}
		

	}
	
	@Override
	public List<Category> listByDepth(MerchantStore store, int depth) {
		return categoryDao.listByDepth(store, depth);
	}
	
	@Override
	public List<Category> listByDepth(MerchantStore store, int depth, Language language) {
		return categoryDao.listByDepth(store, depth, language);
	}

	@Override
	public List<Category> getByName(MerchantStore store, String name, Language language) throws ServiceException {
		
		try {
			return categoryDao.getByName(store, name, language);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
		
		
	}
	
	

	@Override
	public List<Category> listByStore(MerchantStore store)
			throws ServiceException {

		try {
			return categoryDao.listByStore(store);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}
	
	@Override
	public List<Category> listByStore(MerchantStore store, Language language)
			throws ServiceException {

		try {
			return categoryDao.listByStore(store, language);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}
	
	


}
