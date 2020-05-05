package com.salesmanager.shop.populator.catalog;

import java.io.ByteArrayInputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.inject.Inject;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang.Validate;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.salesmanager.core.business.constants.Constants;

import com.salesmanager.core.business.exception.ConversionException;
import com.salesmanager.core.business.services.catalog.category.CategoryService;
import com.salesmanager.core.business.services.catalog.product.attribute.ProductOptionService;
import com.salesmanager.core.business.services.catalog.product.attribute.ProductOptionValueService;
import com.salesmanager.core.business.services.catalog.product.manufacturer.ManufacturerService;
import com.salesmanager.core.business.services.catalog.product.type.ProductTypeService;
import com.salesmanager.core.business.services.customer.CustomerService;
import com.salesmanager.core.business.services.reference.language.LanguageService;
import com.salesmanager.core.business.services.tax.TaxClassService;
import com.salesmanager.core.business.utils.AbstractDataPopulator;
import com.salesmanager.core.model.catalog.category.Category;
import com.salesmanager.core.model.catalog.product.Product;
import com.salesmanager.core.model.catalog.product.attribute.ProductAttribute;
import com.salesmanager.core.model.catalog.product.availability.ProductAvailability;
import com.salesmanager.core.model.catalog.product.description.ProductDescription;
import com.salesmanager.core.model.catalog.product.image.ProductImage;
import com.salesmanager.core.model.catalog.product.manufacturer.Manufacturer;
import com.salesmanager.core.model.catalog.product.price.ProductPrice;
import com.salesmanager.core.model.catalog.product.price.ProductPriceDescription;
import com.salesmanager.core.model.catalog.product.type.ProductType;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.shop.mapper.catalog.PersistableProductAttributeMapper;
import com.salesmanager.shop.model.catalog.product.PersistableImage;
import com.salesmanager.shop.model.catalog.product.PersistableProduct;
import com.salesmanager.shop.model.catalog.product.ProductPriceEntity;
import com.salesmanager.shop.utils.DateUtil;


@Component
public class PersistableProductPopulator extends
		AbstractDataPopulator<PersistableProduct, Product> {
	
	@Inject
	private CategoryService categoryService;
	@Inject
	private ManufacturerService manufacturerService;
	@Inject
	private TaxClassService taxClassService;
	@Inject
	private LanguageService languageService;
	@Inject
	private ProductOptionService productOptionService;
	@Inject
	private ProductOptionValueService productOptionValueService;
	@Inject
	private CustomerService customerService;
	@Autowired
	private PersistableProductAttributeMapper persistableProductAttributeMapper;
	
	@Autowired
	private ProductTypeService productTypeService;

	



	@Override
	public Product populate(PersistableProduct source,
			Product target, MerchantStore store, Language language)
			throws ConversionException {
	  
	    Validate.notNull(target,"Product must not be null");

		try {

			target.setSku(source.getSku());
			target.setAvailable(source.isAvailable());
			target.setPreOrder(source.isPreOrder());
			target.setRefSku(source.getRefSku());
			if(source.getId() != null && source.getId().longValue()==0) {
				target.setId(null);
			} else {
				target.setId(source.getId());
			}
			
			target.setCondition(source.getCondition());
			
			
			//PRODUCT TYPE
			if(!StringUtils.isBlank(source.getType())) {
				ProductType type = productTypeService.getProductType(source.getType());
				if(type == null) {
					throw new ConversionException("Product type [" + source.getType() + "] does not exist");
				}
				
				//TODO
				//if(type.getMerchantStore().getId().intValue() != store.getId().intValue()) {
				//	throw new ConversionException("Product type [" + source.getType() + "] does not exist for store [" + store.getCode() + "]");
				//}
				
				target.setType(type);
			}
			
			
			//RENTAL
			target.setRentalDuration(source.getRentalDuration());
			target.setRentalStatus(source.getRentalStatus());
			target.setRentalPeriod(source.getRentalPeriod());
			
			/** end RENTAL **/
			
			if(source.getOwner()!=null && source.getOwner().getId()!=null) {
				com.salesmanager.core.model.customer.Customer owner = customerService.getById(source.getOwner().getId());
				target.setOwner(owner);
			}
			
			if(!StringUtils.isBlank(source.getDateAvailable())) {
				target.setDateAvailable(DateUtil.getDate(source.getDateAvailable()));
			}


			
			target.setMerchantStore(store);
			
			List<Language> languages = new ArrayList<Language>();
			Set<ProductDescription> descriptions = new HashSet<ProductDescription>();
			if(!CollectionUtils.isEmpty(source.getDescriptions())) {
				for(com.salesmanager.shop.model.catalog.product.ProductDescription description : source.getDescriptions()) {
					
				  ProductDescription productDescription = new ProductDescription();
				  Language lang = languageService.getByCode(description.getLanguage());
	              if(lang==null) {
	                    throw new ConversionException("Language code " + description.getLanguage() + " is invalid, use ISO code (en, fr ...)");
	               }
				   if(!CollectionUtils.isEmpty(target.getDescriptions())) {
				      for(ProductDescription desc : target.getDescriptions()) {
				        if(desc.getLanguage().getCode().equals(description.getLanguage())) {
				          productDescription = desc;
				          break;
				        }
				      }
				    }

					productDescription.setProduct(target);
					productDescription.setDescription(description.getDescription());

					productDescription.setProductHighlight(description.getHighlights());

					productDescription.setName(description.getName());
					productDescription.setSeUrl(description.getFriendlyUrl());
					productDescription.setMetatagKeywords(description.getKeyWords());
					productDescription.setMetatagDescription(description.getMetaDescription());
					productDescription.setTitle(description.getTitle());
					
					languages.add(lang);
					productDescription.setLanguage(lang);
					descriptions.add(productDescription);
				}
			}
			
			if(descriptions.size()>0) {
				target.setDescriptions(descriptions);
			}

			if(source.getProductSpecifications()!=null) {
    			target.setProductHeight(source.getProductSpecifications().getHeight());
    			target.setProductLength(source.getProductSpecifications().getLength());
    			target.setProductWeight(source.getProductSpecifications().getWeight());
    			target.setProductWidth(source.getProductSpecifications().getWidth());
    			
    			
    	         if(source.getProductSpecifications().getManufacturer()!=null) {
                   
                   Manufacturer manuf = null;
                   if(!StringUtils.isBlank(source.getProductSpecifications().getManufacturer())) {
                       manuf = manufacturerService.getByCode(store, source.getProductSpecifications().getManufacturer());
                   } 
                   
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
    			
			}
			target.setSortOrder(source.getSortOrder());
			target.setProductVirtual(source.isProductVirtual());
			target.setProductShipeable(source.isProductShipeable());
			if(source.getRating() != null) {
				target.setProductReviewAvg(new BigDecimal(source.getRating()));
			}
			target.setProductReviewCount(source.getRatingCount());
			
			if(CollectionUtils.isNotEmpty(source.getProductPrices())) {

				//get product availability
			  
			    //create new ProductAvailability
			    ProductAvailability productAvailability = new ProductAvailability();

			    productAvailability.setRegion(Constants.ALL_REGIONS);

				productAvailability.setProductQuantity(source.getQuantity());
				productAvailability.setProduct(target);
				productAvailability.setProductQuantityOrderMin(1);
				productAvailability.setProductQuantityOrderMax(1);
				productAvailability.setAvailable(Boolean.valueOf(target.isAvailable()));
				
				for(ProductPriceEntity priceEntity : source.getProductPrices()) {
					
					ProductPrice price = new ProductPrice();
					price.setProductAvailability(productAvailability);
					price.setDefaultPrice(priceEntity.isDefaultPrice());
					price.setProductPriceAmount(priceEntity.getOriginalPrice());
					price.setCode(priceEntity.getCode());
					price.setProductPriceSpecialAmount(priceEntity.getDiscountedPrice());
					if(priceEntity.getDiscountStartDate()!=null) {
						Date startDate = DateUtil.getDate(priceEntity.getDiscountStartDate());
						price.setProductPriceSpecialStartDate(startDate);
					}
					if(priceEntity.getDiscountEndDate()!=null) {
						Date endDate = DateUtil.getDate(priceEntity.getDiscountEndDate());
						price.setProductPriceSpecialEndDate(endDate);
					}
					productAvailability.getPrices().add(price);
					target.getAvailabilities().add(productAvailability);
					for(Language lang : languages) {
						ProductPriceDescription ppd = new ProductPriceDescription();
						ppd.setProductPrice(price);
						ppd.setLanguage(lang);
						ppd.setName(ProductPriceDescription.DEFAULT_PRICE_DESCRIPTION);
						price.getDescriptions().add(ppd);
					}
				}

			} else { //create 
			  
			    ProductAvailability productAvailability = null;
			    ProductPrice defaultPrice = null;
			    if(!CollectionUtils.isEmpty(target.getAvailabilities())) {
			      for(ProductAvailability avail : target.getAvailabilities()) {
    			        Set<ProductPrice> prices = avail.getPrices();
    			        for(ProductPrice p : prices) {
    			          if(p.isDefaultPrice()) {
    			            if(productAvailability == null) {
    			              productAvailability = avail;
    			              defaultPrice = p;
    			              break;
    			            }
    			            p.setDefaultPrice(false);
    			          }
    			        }
			      }
			    }
				
			    if(productAvailability == null) {
			      productAvailability = new ProductAvailability();
			      target.getAvailabilities().add(productAvailability);
			    }
			    
				productAvailability.setProduct(target);
				productAvailability.setProductQuantity(source.getQuantity());
				productAvailability.setProductQuantityOrderMin(1);
				productAvailability.setProductQuantityOrderMax(1);
				productAvailability.setRegion(Constants.ALL_REGIONS);
				productAvailability.setAvailable(Boolean.valueOf(target.isAvailable()));


				if(defaultPrice != null) {
				  defaultPrice.setProductPriceAmount(source.getPrice());
				} else {
				    defaultPrice = new ProductPrice();
				    defaultPrice.setDefaultPrice(true);
				    defaultPrice.setProductPriceAmount(source.getPrice());
				    defaultPrice.setCode(ProductPriceEntity.DEFAULT_PRICE_CODE);
				    defaultPrice.setProductAvailability(productAvailability);
	                productAvailability.getPrices().add(defaultPrice);
	                for(Language lang : languages) {
	                
                      ProductPriceDescription ppd = new ProductPriceDescription();
                      ppd.setProductPrice(defaultPrice);
                      ppd.setLanguage(lang);
                      ppd.setName(ProductPriceDescription.DEFAULT_PRICE_DESCRIPTION);
                      defaultPrice.getDescriptions().add(ppd);
                    }
				}

				
				
			}

			//image
			if(source.getImages()!=null) {
				for(PersistableImage img : source.getImages()) {
					ByteArrayInputStream in = new ByteArrayInputStream(img.getBytes());
					ProductImage productImage = new ProductImage();
					productImage.setProduct(target);
					productImage.setProductImage(img.getName());
					productImage.setImage(in);
					target.getImages().add(productImage);
				}
			}
			
			//attributes
			if(source.getAttributes()!=null) {
				for(com.salesmanager.shop.model.catalog.product.attribute.PersistableProductAttribute attr : source.getAttributes()) {
					ProductAttribute attribute = persistableProductAttributeMapper.convert(attr, store, language);
					
					/*
					ProductOption productOption = null;
							
					if(!StringUtils.isBlank(attr.getOption().getCode())) {
						productOption = productOptionService.getByCode(store, attr.getOption().getCode());
					} else {
						Validate.notNull(attr.getOption().getId(),"Product option id is null");
						productOption = productOptionService.getById(attr.getOption().getId());
					}

					if(productOption==null) {
						throw new ConversionException("Product option id " + attr.getOption().getId() + " does not exist");
					}
					
					ProductOptionValue productOptionValue = null;
					
					if(!StringUtils.isBlank(attr.getOptionValue().getCode())) {
						productOptionValue = productOptionValueService.getByCode(store, attr.getOptionValue().getCode());
					} else {
						productOptionValue = productOptionValueService.getById(attr.getOptionValue().getId());
					}
					
					if(productOptionValue==null) {
						throw new ConversionException("Product option value id " + attr.getOptionValue().getId() + " does not exist");
					}
					
					if(productOption.getMerchantStore().getId().intValue()!=store.getId().intValue()) {
						throw new ConversionException("Invalid product option id ");
					}
					
					if(productOptionValue.getMerchantStore().getId().intValue()!=store.getId().intValue()) {
						throw new ConversionException("Invalid product option value id ");
					}
*/					
					//ProductAttribute attribute = new ProductAttribute();
					attribute.setProduct(target);
/*					attribute.setProductOption(productOption);
					attribute.setProductOptionValue(productOptionValue);
					attribute.setProductAttributePrice(attr.getProductAttributePrice());
					attribute.setProductAttributeWeight(attr.getProductAttributeWeight());
					attribute.setProductAttributePrice(attr.getProductAttributePrice());*/
					target.getAttributes().add(attribute);

				}
			}

			
			//categories
			if(!CollectionUtils.isEmpty(source.getCategories())) {
				for(com.salesmanager.shop.model.catalog.category.Category categ : source.getCategories()) {
					
					Category c = null;
					if(!StringUtils.isBlank(categ.getCode())) {
						c = categoryService.getByCode(store, categ.getCode());
					} else {
						Validate.notNull(categ.getId(), "Category id nust not be null");
						c = categoryService.getById(categ.getId(), store.getId());
					}
					
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
		return null;
	}



	public CustomerService getCustomerService() {
		return customerService;
	}



	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}

}
