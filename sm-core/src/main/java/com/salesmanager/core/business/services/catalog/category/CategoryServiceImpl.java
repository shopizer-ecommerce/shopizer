package com.salesmanager.core.business.services.catalog.category;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.salesmanager.core.business.constants.Constants;
import com.salesmanager.core.business.exception.ServiceException;
import com.salesmanager.core.business.repositories.catalog.category.CategoryRepository;
import com.salesmanager.core.business.services.catalog.product.ProductService;
import com.salesmanager.core.business.services.common.generic.SalesManagerEntityServiceImpl;
import com.salesmanager.core.model.catalog.category.Category;
import com.salesmanager.core.model.catalog.category.CategoryDescription;
import com.salesmanager.core.model.catalog.product.Product;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;

@Service("categoryService")
public class CategoryServiceImpl extends SalesManagerEntityServiceImpl<Long, Category> implements CategoryService {
	
	 private CategoryRepository categoryRepository;
	
	 @PersistenceContext(unitName = "shopizerContainer")
	 private EntityManager em;
	  
	 @Inject
	 private ProductService productService;
	  

	
	@Inject
	public CategoryServiceImpl(CategoryRepository categoryRepository) {
		super(categoryRepository);
		this.categoryRepository = categoryRepository;
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
		
		return categoryRepository.countProductsByCategories(store, categoryIds);
		
	}
	
	@Override
	public List<Category> listByCodes(MerchantStore store, List<String> codes,
			Language language) {
		return categoryRepository.findByCodes(store.getId(), codes, language.getId());
	}
	
	@Override
	public List<Category> listByIds(MerchantStore store, List<Long> ids,
			Language language) {
		return categoryRepository.findByIds(store.getId(), ids, language.getId());
	}
	
	@Override
	public Category getByLanguage(long categoryId, Language language) {
		return categoryRepository.findById(categoryId, language.getId());
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
			return categoryRepository.findByLineage(store.getId(), lineage);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
		
		
	}
	
	@Override
	public List<Category> listByLineage(String storeCode, String lineage) throws ServiceException {
		try {
			return categoryRepository.findByLineage(storeCode, lineage);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
		
		
	}
	

	@Override
	public List<Category> listBySeUrl(MerchantStore store, String seUrl) throws ServiceException{
		
		try {
			return categoryRepository.listByFriendlyUrl(store.getId(), seUrl);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
		
	}
	
	@Override
	public Category getBySeUrl(MerchantStore store,String seUrl) {
		return categoryRepository.findByFriendlyUrl(store.getId(), seUrl);
	}
	
	
	@Override
	public Category getByCode(MerchantStore store, String code) throws ServiceException {
		
		try {
			return categoryRepository.findByCode(store.getId(), code);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
		
	}
	
	@Override
	public Category getByCode(String storeCode, String code) throws ServiceException {
		
		try {
			return categoryRepository.findByCode(storeCode, code);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
		
	}
	
	
	@Override
	public Category getById(Long id) {

			return categoryRepository.findOne(id);
		
	}
	
	@Override
	public List<Category> listByParent(Category category) throws ServiceException {
		
		try {
			return categoryRepository.listByStoreAndParent(null, category);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
		
	}
	
	@Override
	public List<Category> listByStoreAndParent(MerchantStore store, Category category) throws ServiceException {
		
		try {
			return categoryRepository.listByStoreAndParent(store, category);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
		
	}
	
	@Override
	public List<Category> listByParent(Category category, Language language) {
		Assert.notNull(category, "Category cannot be null");
		Assert.notNull(language, "Language cannot be null");
		Assert.notNull(category.getMerchantStore(), "category.merchantStore cannot be null");
		
		return categoryRepository.findByParent(category.getId(), language.getId());
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
			org.hibernate.Session session = em.unwrap(org.hibernate.Session.class);//need to refresh the session to update all product categories

			
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
			categoryRepository.delete(categ);
			
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
		return categoryRepository.findByDepth(store.getId(), depth);
	}
	
	@Override
	public List<Category> listByDepth(MerchantStore store, int depth, Language language) {
		return categoryRepository.findByDepth(store.getId(), depth, language.getId());
	}
	
	@Override
	public List<Category> listByDepthFilterByFeatured(MerchantStore store, int depth, Language language) {
		return categoryRepository.findByDepthFilterByFeatured(store.getId(), depth, language.getId());
	}

	@Override
	public List<Category> getByName(MerchantStore store, String name, Language language) throws ServiceException {
		
		try {
			return categoryRepository.findByName(store.getId(), name, language.getId());
		} catch (Exception e) {
			throw new ServiceException(e);
		}
		
		
	}
	
	

	@Override
	public List<Category> listByStore(MerchantStore store)
			throws ServiceException {

		try {
			return categoryRepository.findByStore(store.getId());
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}
	
	@Override
	public List<Category> listByStore(MerchantStore store, Language language)
			throws ServiceException {

		try {
			return categoryRepository.findByStore(store.getId(), language.getId());
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}
	
	


}
