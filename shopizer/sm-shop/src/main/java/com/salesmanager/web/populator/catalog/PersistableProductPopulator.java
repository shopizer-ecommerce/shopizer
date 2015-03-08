package com.salesmanager.web.populator.catalog;

import java.io.ByteArrayInputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.Validate;
import org.apache.commons.lang3.StringUtils;

import com.salesmanager.core.business.catalog.category.model.Category;
import com.salesmanager.core.business.catalog.category.service.CategoryService;
import com.salesmanager.core.business.catalog.product.model.Product;
import com.salesmanager.core.business.catalog.product.model.attribute.ProductAttribute;
import com.salesmanager.core.business.catalog.product.model.attribute.ProductOption;
import com.salesmanager.core.business.catalog.product.model.attribute.ProductOptionValue;
import com.salesmanager.core.business.catalog.product.model.availability.ProductAvailability;
import com.salesmanager.core.business.catalog.product.model.description.ProductDescription;
import com.salesmanager.core.business.catalog.product.model.image.ProductImage;
import com.salesmanager.core.business.catalog.product.model.manufacturer.Manufacturer;
import com.salesmanager.core.business.catalog.product.model.price.ProductPrice;
import com.salesmanager.core.business.catalog.product.model.price.ProductPriceDescription;
import com.salesmanager.core.business.catalog.product.service.attribute.ProductOptionService;
import com.salesmanager.core.business.catalog.product.service.attribute.ProductOptionValueService;
import com.salesmanager.core.business.catalog.product.service.manufacturer.ManufacturerService;
import com.salesmanager.core.business.generic.exception.ConversionException;
import com.salesmanager.core.business.merchant.model.MerchantStore;
import com.salesmanager.core.business.reference.language.model.Language;
import com.salesmanager.core.business.reference.language.service.LanguageService;
import com.salesmanager.core.business.tax.service.TaxClassService;
import com.salesmanager.core.utils.AbstractDataPopulator;
import com.salesmanager.web.entity.catalog.product.PersistableImage;
import com.salesmanager.web.entity.catalog.product.PersistableProduct;
import com.salesmanager.web.utils.DateUtil;

public class PersistableProductPopulator extends
		AbstractDataPopulator<PersistableProduct, Product> {
	
	
	private CategoryService categoryService;
	private ManufacturerService manufacturerService;
	private TaxClassService taxClassService;
	private LanguageService languageService;
	
	private ProductOptionService productOptionService;
	private ProductOptionValueService productOptionValueService;
	

	@Override
	public Product populate(PersistableProduct source,
			Product target, MerchantStore store, Language language)
			throws ConversionException {
		
			Validate.notNull(manufacturerService, "Requires to set ManufacturerService");
			Validate.notNull(languageService, "Requires to set LanguageService");
			Validate.notNull(categoryService, "Requires to set CategoryService");
			Validate.notNull(taxClassService, "Requires to set TaxClassService");
			Validate.notNull(productOptionService, "Requires to set ProductOptionService");
			Validate.notNull(productOptionValueService, "Requires to set ProductOptionValueService");
		
		try {

			target.setSku(source.getSku());
			target.setAvailable(source.isAvailable());
			
			if(!StringUtils.isBlank(source.getDateAvailable())) {
				target.setDateAvailable(DateUtil.getDate(source.getDateAvailable()));
			}

			if(source.getManufacturer()!=null) {
				Manufacturer manuf = manufacturerService.getById(source.getManufacturer().getId());
				if(manuf==null) {
					throw new ConversionException("Invalid manufacturer id");
				}
				if(manuf!=null) {
					if(manuf.getMerchantStore().getId().intValue()!=store.getId().intValue()) {
						throw new ConversionException("Invalid manufacturer id");
					}
					target.setManufacturer(manuf);
					
				}
			}
			
			target.setMerchantStore(store);
			
			List<Language> languages = new ArrayList<Language>();
			Set<ProductDescription> descriptions = new HashSet<ProductDescription>();
			if(!CollectionUtils.isEmpty(source.getDescriptions())) {
				for(com.salesmanager.web.entity.catalog.product.ProductDescription description : source.getDescriptions()) {
					
					ProductDescription productDescription = new ProductDescription();
					productDescription.setProduct(target);
					productDescription.setDescription(description.getDescription());
					productDescription.setName(description.getName());
					productDescription.setSeUrl(description.getFriendlyUrl());
					productDescription.setMetatagDescription(description.getMetaDescription());
					productDescription.setTitle(description.getTitle());
					
					Language lang = languageService.getByCode(description.getLanguage());
					if(lang==null) {
						throw new ConversionException("Language code " + description.getLanguage() + " is invalid, use ISO code (en, fr ...)");
					}
					
					languages.add(lang);
					productDescription.setLanguage(lang);
					descriptions.add(productDescription);
				}
			}
			
			if(descriptions.size()>0) {
				target.setDescriptions(descriptions);
			}

			//target.setType(source.getType());//not implemented yet
			target.setProductHeight(source.getProductHeight());
			target.setProductLength(source.getProductLength());
			target.setProductWeight(source.getProductWeight());
			target.setProductWidth(source.getProductWidth());
			target.setProductVirtual(source.isProductVirtual());
			target.setProductShipeable(source.isProductShipeable());
			target.setProductReviewAvg(new BigDecimal(source.getRating()));
			target.setProductReviewCount(source.getRatingCount());
			
			
			ProductAvailability productAvailability = new ProductAvailability();
			ProductPrice price = new ProductPrice();
			price.setDefaultPrice(true);
			price.setProductPriceAmount(source.getPrice());
			price.setProductAvailability(productAvailability);
			productAvailability.getPrices().add(price);
			target.getAvailabilities().add(productAvailability);
			for(Language lang : languages) {
				ProductPriceDescription ppd = new ProductPriceDescription();
				ppd.setProductPrice(price);
				ppd.setLanguage(lang);
				ppd.setName(ProductPriceDescription.DEFAULT_PRICE_DESCRIPTION);
				price.getDescriptions().add(ppd);
			}
			
			//image
			if(source.getImages()!=null) {
				for(PersistableImage img : source.getImages()) {
					ByteArrayInputStream in = new ByteArrayInputStream(img.getBytes());
					ProductImage productImage = new ProductImage();
					productImage.setProduct(target);
					productImage.setProductImage(img.getImageName());
					productImage.setImage(in);
					target.getImages().add(productImage);
				}
			}
			
			//attributes
			if(source.getAttributes()!=null) {
				for(com.salesmanager.web.entity.catalog.product.attribute.ProductAttributeEntity attr : source.getAttributes()) {
					
					ProductOption productOption = productOptionService.getById(attr.getOption().getId());
					if(productOption==null) {
						throw new ConversionException("Product option id " + attr.getOption().getId() + " does not exist");
					}
					
					ProductOptionValue productOptionValue = productOptionValueService.getById(attr.getOptionValue().getId());
					if(productOptionValue==null) {
						throw new ConversionException("Product option value id " + attr.getOptionValue().getId() + " does not exist");
					}
					
					if(productOption.getMerchantStore().getId().intValue()!=store.getId().intValue()) {
						throw new ConversionException("Invalid product option id ");
					}
					
					if(productOptionValue.getMerchantStore().getId().intValue()!=store.getId().intValue()) {
						throw new ConversionException("Invalid product option value id ");
					}
					
					ProductAttribute attribute = new ProductAttribute();
					attribute.setProduct(target);
					attribute.setProductOption(productOption);
					attribute.setProductOptionValue(productOptionValue);
					attribute.setProductAttributePrice(attr.getProductAttributePrice());
					attribute.setProductAttributeWeight(attr.getProductAttributeWeight());
					attribute.setProductAttributePrice(attr.getProductAttributePrice());
					target.getAttributes().add(attribute);

				}
			}

			
			//categories
			if(!CollectionUtils.isEmpty(source.getCategories())) {
				for(com.salesmanager.web.entity.catalog.category.Category categ : source.getCategories()) {
					Category c = categoryService.getById(categ.getId());
					if(c==null) {
						throw new ConversionException("Category id " + categ.getId() + " does not exist");
					}
					if(c.getMerchantStore().getId().intValue()!=store.getId().intValue()) {
						throw new ConversionException("Invalid category id");
					}
					target.getCategories().add(c);
				}
			}
			return target;
		
		} catch (Exception e) {
			throw new ConversionException(e);
		}
	}



	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	public CategoryService getCategoryService() {
		return categoryService;
	}

	public void setManufacturerService(ManufacturerService manufacturerService) {
		this.manufacturerService = manufacturerService;
	}

	public ManufacturerService getManufacturerService() {
		return manufacturerService;
	}

	public void setTaxClassService(TaxClassService taxClassService) {
		this.taxClassService = taxClassService;
	}

	public TaxClassService getTaxClassService() {
		return taxClassService;
	}


	public LanguageService getLanguageService() {
		return languageService;
	}

	public void setLanguageService(LanguageService languageService) {
		this.languageService = languageService;
	}

	public ProductOptionService getProductOptionService() {
		return productOptionService;
	}

	public void setProductOptionService(ProductOptionService productOptionService) {
		this.productOptionService = productOptionService;
	}

	public ProductOptionValueService getProductOptionValueService() {
		return productOptionValueService;
	}

	public void setProductOptionValueService(
			ProductOptionValueService productOptionValueService) {
		this.productOptionValueService = productOptionValueService;
	}


	@Override
	protected Product createTarget() {
		// TODO Auto-generated method stub
		return null;
	}

}
