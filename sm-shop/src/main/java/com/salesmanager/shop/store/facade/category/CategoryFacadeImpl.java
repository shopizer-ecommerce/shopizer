package com.salesmanager.shop.store.facade.category;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.inject.Inject;

import org.apache.commons.lang3.Validate;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.salesmanager.core.business.exception.ConversionException;
import com.salesmanager.core.business.exception.ServiceException;
import com.salesmanager.core.business.services.catalog.category.CategoryService;
import com.salesmanager.core.business.services.catalog.product.attribute.ProductAttributeService;
import com.salesmanager.core.business.services.merchant.MerchantStoreService;
import com.salesmanager.core.model.catalog.category.Category;
import com.salesmanager.core.model.catalog.product.attribute.ProductAttribute;
import com.salesmanager.core.model.catalog.product.attribute.ProductOption;
import com.salesmanager.core.model.catalog.product.attribute.ProductOptionDescription;
import com.salesmanager.core.model.catalog.product.attribute.ProductOptionValueDescription;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.shop.mapper.Mapper;
import com.salesmanager.shop.mapper.catalog.ReadableCategoryMapper;
import com.salesmanager.shop.model.catalog.category.PersistableCategory;
import com.salesmanager.shop.model.catalog.category.ReadableCategory;
import com.salesmanager.shop.model.catalog.category.ReadableCategoryList;
import com.salesmanager.shop.model.catalog.product.attribute.ReadableProductVariant;
import com.salesmanager.shop.model.catalog.product.attribute.ReadableProductVariantValue;
import com.salesmanager.shop.model.entity.ListCriteria;
import com.salesmanager.shop.populator.catalog.PersistableCategoryPopulator;
import com.salesmanager.shop.populator.catalog.ReadableCategoryPopulator;
import com.salesmanager.shop.store.api.exception.OperationNotAllowedException;
import com.salesmanager.shop.store.api.exception.ResourceNotFoundException;
import com.salesmanager.shop.store.api.exception.ServiceRuntimeException;
import com.salesmanager.shop.store.api.exception.UnauthorizedException;
import com.salesmanager.shop.store.controller.category.facade.CategoryFacade;

@Service(value = "categoryFacade")
public class CategoryFacadeImpl implements CategoryFacade {

	@Inject
	private CategoryService categoryService;

	@Inject
	private MerchantStoreService merchantStoreService;

	@Inject
	private PersistableCategoryPopulator persistableCatagoryPopulator;

	@Inject
	private ReadableCategoryMapper readableCategoryMapper;

	@Inject
	private ProductAttributeService productAttributeService;

	private static final String FEATURED_CATEGORY = "featured";
	private static final String VISIBLE_CATEGORY = "visible";
	private static final String ADMIN_CATEGORY = "admin";

	@Override
	public ReadableCategoryList getCategoryHierarchy(MerchantStore store, ListCriteria criteria, int depth,
			Language language, List<String> filter, int page, int count) {

		Validate.notNull(store,"MerchantStore can not be null");


		//get parent store
		try {

			MerchantStore parent = merchantStoreService.getParent(store.getCode());


			List<Category> categories = null;
			ReadableCategoryList returnList = new ReadableCategoryList();
			if (!CollectionUtils.isEmpty(filter) && filter.contains(FEATURED_CATEGORY)) {
				categories = categoryService.getListByDepthFilterByFeatured(parent, depth, language);
				returnList.setRecordsTotal(categories.size());
				returnList.setNumber(categories.size());
				returnList.setTotalPages(1);
			} else {
				org.springframework.data.domain.Page<Category> pageable = categoryService.getListByDepth(parent, language,
						criteria != null ? criteria.getName() : null, depth, page, count);
				categories = pageable.getContent();
				returnList.setRecordsTotal(pageable.getTotalElements());
				returnList.setTotalPages(pageable.getTotalPages());
				returnList.setNumber(categories.size());
			}



			List<ReadableCategory> readableCategories = null;
			if (filter != null && filter.contains(VISIBLE_CATEGORY)) {
				readableCategories = categories.stream().filter(Category::isVisible)
						.map(cat -> readableCategoryMapper.convert(cat, store, language))
						.collect(Collectors.toList());
			} else {
				readableCategories = categories.stream()
						.map(cat -> readableCategoryMapper.convert(cat, store, language))
						.collect(Collectors.toList());
			}

			Map<Long, ReadableCategory> readableCategoryMap = readableCategories.stream()
					.collect(Collectors.toMap(ReadableCategory::getId, Function.identity()));

			readableCategories.stream()
					// .filter(ReadableCategory::isVisible)
					.filter(cat -> Objects.nonNull(cat.getParent()))
					.filter(cat -> readableCategoryMap.containsKey(cat.getParent().getId())).forEach(readableCategory -> {
						ReadableCategory parentCategory = readableCategoryMap.get(readableCategory.getParent().getId());
						if (parentCategory != null) {
							parentCategory.getChildren().add(readableCategory);
						}
					});
			
			List<ReadableCategory> filteredList = readableCategoryMap.values().stream().collect(Collectors.toList());

			//execute only if not admin filtered
			if(filter == null || (filter!=null && !filter.contains(ADMIN_CATEGORY))) {
					filteredList = readableCategoryMap.values().stream().filter(cat -> cat.getDepth() == 0)
						.sorted(Comparator.comparing(ReadableCategory::getSortOrder)).collect(Collectors.toList());
				
					returnList.setNumber(filteredList.size());

			}
			
			returnList.setCategories(filteredList);

			
			
			return returnList;

		} catch (ServiceException e) {
			throw new ServiceRuntimeException(e);
		}

	}

	@Override
	public boolean existByCode(MerchantStore store, String code) {
		try {
			Category c = categoryService.getByCode(store, code);
			return c != null ? true : false;
		} catch (ServiceException e) {
			throw new ServiceRuntimeException(e);
		}
	}

	@Override
	public PersistableCategory saveCategory(MerchantStore store, PersistableCategory category) {
		try {

			Long categoryId = category.getId();
			Category target = Optional.ofNullable(categoryId)
					.filter(merchant -> store !=null)
					.filter(id -> id > 0)
					.map(categoryService::getById)
					.orElse(new Category());

			Category dbCategory = populateCategory(store, category, target);
			saveCategory(store, dbCategory, null);

			// set category id
			category.setId(dbCategory.getId());
			return category;
		} catch (ServiceException e) {
			throw new ServiceRuntimeException("Error while updating category", e);
		}
	}

	private Category populateCategory(MerchantStore store, PersistableCategory category, Category target) {
		try {
			return persistableCatagoryPopulator.populate(category, target, store, store.getDefaultLanguage());
		} catch (ConversionException e) {
			throw new ServiceRuntimeException(e);
		}
	}

	private void saveCategory(MerchantStore store, Category category, Category parent) throws ServiceException {

		/**
		 * c.children1
		 *
		 * <p>
		 * children1.children1 children1.children2
		 *
		 * <p>
		 * children1.children2.children1
		 */

		/** set lineage * */
		if (parent != null) {
			category.setParent(category);

			String lineage = parent.getLineage();
			int depth = parent.getDepth();

			category.setDepth(depth + 1);
			category.setLineage(new StringBuilder().append(lineage).toString());// service
																										// will
																										// adjust
																										// lineage
		}

		category.setMerchantStore(store);

		// remove children
		List<Category> children = category.getCategories();
		List<Category> saveAfter = children.stream().filter(c -> c.getId() == null || c.getId().longValue()==0).collect(Collectors.toList());
		List<Category> saveNow = children.stream().filter(c -> c.getId() != null && c.getId().longValue()>0).collect(Collectors.toList());
		category.setCategories(saveNow);

		/** set parent * */
		if (parent != null) {
			category.setParent(parent);
		}

		categoryService.saveOrUpdate(category);

		if (!CollectionUtils.isEmpty(saveAfter)) {
			parent = category;
			for(Category c: saveAfter) {
				if(c.getId() == null || c.getId().longValue()==0) {
					for (Category sub : children) {
						saveCategory(store, sub, parent);
					}
				}
			}
		}

	}

	@Override
	public ReadableCategory getById(MerchantStore store, Long id, Language language) {

			Category categoryModel = null;
			if (language != null) {
				categoryModel = getCategoryById(id, language);
			} else {// all langs
				categoryModel = getById(store, id);
			}

			if (categoryModel == null)
				throw new ResourceNotFoundException("Categori id [" + id + "] not found");

			StringBuilder lineage = new StringBuilder().append(categoryModel.getLineage());

			ReadableCategory readableCategory = readableCategoryMapper.convert(categoryModel, store,
					language);

			// get children
			List<Category> children = getListByLineage(store, lineage.toString());

			List<ReadableCategory> childrenCats = children.stream()
					.map(cat -> readableCategoryMapper.convert(cat, store, language))
					.collect(Collectors.toList());

			addChildToParent(readableCategory, childrenCats);
			return readableCategory;

	}

	private void addChildToParent(ReadableCategory readableCategory, List<ReadableCategory> childrenCats) {
		Map<Long, ReadableCategory> categoryMap = childrenCats.stream()
				.collect(Collectors.toMap(ReadableCategory::getId, Function.identity()));
		categoryMap.put(readableCategory.getId(), readableCategory);

		// traverse map and add child to parent
		for (ReadableCategory readable : childrenCats) {

			if (readable.getParent() != null) {

				ReadableCategory rc = categoryMap.get(readable.getParent().getId());
				if (rc != null) {
					rc.getChildren().add(readable);
				}
			}
		}
	}

	private List<Category> getListByLineage(MerchantStore store, String lineage) {
		try {
			return categoryService.getListByLineage(store, lineage);
		} catch (ServiceException e) {
			throw new ServiceRuntimeException(String.format("Error while getting root category %s", e.getMessage()), e);
		}
	}

	private Category getCategoryById(Long id, Language language) {
		return Optional.ofNullable(categoryService.getOneByLanguage(id, language))
				.orElseThrow(() -> new ResourceNotFoundException("Category id [" + id + "] not found"));
	}

	@Override
	public void deleteCategory(Category category) {
		try {
			categoryService.delete(category);
		} catch (ServiceException e) {
			throw new ServiceRuntimeException("Error while deleting category", e);
		}
	}

	@Override
	public ReadableCategory getByCode(MerchantStore store, String code, Language language) throws Exception {

		Validate.notNull(code, "category code must not be null");
		ReadableCategoryPopulator categoryPopulator = new ReadableCategoryPopulator();
		ReadableCategory readableCategory = new ReadableCategory();

		Category category = categoryService.getByCode(store, code);
		categoryPopulator.populate(category, readableCategory, store, language);

		return readableCategory;
	}

	@Override
	public ReadableCategory getCategoryByFriendlyUrl(MerchantStore store, String friendlyUrl, Language language) throws Exception {
		Validate.notNull(friendlyUrl, "Category search friendly URL must not be null");


		Category category = categoryService.getBySeUrl(store, friendlyUrl, language);
		
		if(category == null) {
			throw new ResourceNotFoundException("Category with friendlyUrl [" + friendlyUrl + "] was not found");
		}
		
		ReadableCategoryPopulator categoryPopulator = new ReadableCategoryPopulator();
		ReadableCategory readableCategory = new ReadableCategory();
		
		
		categoryPopulator.populate(category, readableCategory, store, language);

		return readableCategory;
	}

	private Category getById(MerchantStore store, Long id) {
		Validate.notNull(id, "category id must not be null");
		Validate.notNull(store, "MerchantStore must not be null");
		Category category = categoryService.getById(id, store.getId());
		if (category == null) {
			throw new ResourceNotFoundException("Category with id [" + id + "] not found");
		}
		if (category.getMerchantStore().getId().intValue() != store.getId().intValue()) {
			throw new UnauthorizedException("Unauthorized");
		}
		return category;
	}

	@Override
	public void deleteCategory(Long categoryId, MerchantStore store) {
		Category category = getOne(categoryId, store.getId());
		deleteCategory(category);
	}

	private Category getOne(Long categoryId, int storeId) {
		return Optional.ofNullable(categoryService.getById(categoryId)).orElseThrow(
				() -> new ResourceNotFoundException(String.format("No Category found for ID : %s", categoryId)));
	}

	@Override
	public List<ReadableProductVariant> categoryProductVariants(Long categoryId, MerchantStore store,
			Language language) {
		Category category = categoryService.getById(categoryId, store.getId());

		List<ReadableProductVariant> variants = new ArrayList<ReadableProductVariant>();

		if (category == null) {
			throw new ResourceNotFoundException("Category [" + categoryId + "] not found");
		}

		try {
			List<ProductAttribute> attributes = productAttributeService.getProductAttributesByCategoryLineage(store,
					category.getLineage(), language);

			/**
			 * Option NAME OptionValueName OptionValueName
			 **/
			Map<String, List<com.salesmanager.shop.model.catalog.product.attribute.ProductOptionValue>> rawFacet = new HashMap<String, List<com.salesmanager.shop.model.catalog.product.attribute.ProductOptionValue>>();
			Map<String, ProductOption> references = new HashMap<String, ProductOption>();
			for (ProductAttribute attr : attributes) {
				references.put(attr.getProductOption().getCode(), attr.getProductOption());
				List<com.salesmanager.shop.model.catalog.product.attribute.ProductOptionValue> values = rawFacet.get(attr.getProductOption().getCode());
				if (values == null) {
					values = new ArrayList<com.salesmanager.shop.model.catalog.product.attribute.ProductOptionValue>();
					rawFacet.put(attr.getProductOption().getCode(), values);
				}
				
				if(attr.getProductOptionValue() != null) {
					Optional<ProductOptionValueDescription> desc = attr.getProductOptionValue().getDescriptions()
					.stream().filter(o -> o.getLanguage().getId() == language.getId()).findFirst();
					
					com.salesmanager.shop.model.catalog.product.attribute.ProductOptionValue val = new com.salesmanager.shop.model.catalog.product.attribute.ProductOptionValue();
					val.setCode(attr.getProductOption().getCode());
					String order = attr.getAttributeSortOrder();
					val.setSortOrder(order == null ? attr.getId().intValue(): Integer.parseInt(attr.getAttributeSortOrder()));
					if(desc.isPresent()) {
						val.setName(desc.get().getName());
					} else {
						val.setName(attr.getProductOption().getCode());
					}
					values.add(val);
				}
			}

			// for each reference set Option
			Iterator<Entry<String, ProductOption>> it = references.entrySet().iterator();
			while (it.hasNext()) {
				@SuppressWarnings("rawtypes")
				Map.Entry pair = (Map.Entry) it.next();
				ProductOption option = (ProductOption) pair.getValue();
				List<com.salesmanager.shop.model.catalog.product.attribute.ProductOptionValue> values = rawFacet.get(option.getCode());

				ReadableProductVariant productVariant = new ReadableProductVariant();
				Optional<ProductOptionDescription>  optionDescription = option.getDescriptions().stream().filter(o -> o.getLanguage().getId() == language.getId()).findFirst();
				if(optionDescription.isPresent()) {
					productVariant.setName(optionDescription.get().getName());
					productVariant.setId(optionDescription.get().getId());
					productVariant.setCode(optionDescription.get().getProductOption().getCode());
					List<ReadableProductVariantValue> optionValues = new ArrayList<ReadableProductVariantValue>();
					for (com.salesmanager.shop.model.catalog.product.attribute.ProductOptionValue value : values) {
						ReadableProductVariantValue v = new ReadableProductVariantValue();
						v.setCode(value.getCode());
						v.setName(value.getName());
						v.setDescription(value.getName());
						v.setOption(option.getId());
						v.setValue(value.getId());
						v.setOrder(option.getProductOptionSortOrder());
						optionValues.add(v);
					}
					
				    Comparator<ReadableProductVariantValue> orderComparator
				      = Comparator.comparingInt(ReadableProductVariantValue::getOrder);
				    
				    //Arrays.sort(employees, employeeSalaryComparator);
					
				    List<ReadableProductVariantValue> readableValues = optionValues.stream()
				    			.sorted(orderComparator)
				    	    	.collect(Collectors.toList());
				    	        

					
					//sort by name
					// remove duplicates
					readableValues = optionValues.stream().distinct().collect(Collectors.toList());
					readableValues.sort(Comparator.comparing(ReadableProductVariantValue::getName));
					
					productVariant.setOptions(readableValues);
					variants.add(productVariant);
				}
			}


			return variants;
		} catch (Exception e) {
			throw new ServiceRuntimeException("An error occured while retrieving ProductAttributes", e);
		}
	}

	@Override
	public void move(Long child, Long parent, MerchantStore store) {

		Validate.notNull(child, "Child category must not be null");
		Validate.notNull(parent, "Parent category must not be null");
		Validate.notNull(store, "Merhant must not be null");


		try {

			Category c = categoryService.getById(child, store.getId());

			if(c == null) {
				throw new ResourceNotFoundException("Category with id [" + child + "] for store [" + store.getCode() + "]");
			}

			if(parent.longValue()==-1) {
				categoryService.addChild(null, c);
				return;

			}

			Category p = categoryService.getById(parent, store.getId());

			if(p == null) {
				throw new ResourceNotFoundException("Category with id [" + parent + "] for store [" + store.getCode() + "]");
			}

			if (c.getParent() != null && c.getParent().getId() == parent) {
				return;
			}

			if (c.getMerchantStore().getId().intValue() != store.getId().intValue()) {
				throw new OperationNotAllowedException(
						"Invalid identifiers for Merchant [" + c.getMerchantStore().getCode() + "]");
			}

			if (p.getMerchantStore().getId().intValue() != store.getId().intValue()) {
				throw new OperationNotAllowedException(
						"Invalid identifiers for Merchant [" + c.getMerchantStore().getCode() + "]");
			}

			p.getAuditSection().setModifiedBy("Api");
			categoryService.addChild(p, c);
		} catch (ResourceNotFoundException re) {
			throw re;
		} catch (OperationNotAllowedException oe) {
			throw oe;
		} catch (Exception e) {
			throw new ServiceRuntimeException(e);
		}

	}

	@Override
	public Category getByCode(String code, MerchantStore store) {
		try {
			return categoryService.getByCode(store, code);
		} catch (ServiceException e) {
			throw new ServiceRuntimeException("Exception while reading category code [" + code + "]",e);
		}
	}

	@Override
	public void setVisible(PersistableCategory category, MerchantStore store) {
		Validate.notNull(category, "Category must not be null");
		Validate.notNull(store, "Store must not be null");
		try {
			Category c = this.getById(store, category.getId());
			c.setVisible(category.isVisible());
			categoryService.saveOrUpdate(c);
		} catch (Exception e) {
			throw new ServiceRuntimeException("Error while getting category [" + category.getId() + "]",e);
		}
	}

	@Override
	public ReadableCategoryList listByProduct(MerchantStore store, Long product, Language language) {
		Validate.notNull(product, "Product id must not be null");
		Validate.notNull(store, "Store must not be null");
		
		List<ReadableCategory> readableCategories = new ArrayList<ReadableCategory>();

			List<Category> categories = categoryService.getByProductId(product, store);

			readableCategories = categories.stream()
						.map(cat -> readableCategoryMapper.convert(cat, store, language))
						.collect(Collectors.toList());
			
			ReadableCategoryList readableList = new ReadableCategoryList();
			readableList.setCategories(readableCategories);
			readableList.setTotalPages(1);
			readableList.setNumber(readableCategories.size());
			readableList.setRecordsTotal(readableCategories.size());

		
		return readableList;
	}
}
