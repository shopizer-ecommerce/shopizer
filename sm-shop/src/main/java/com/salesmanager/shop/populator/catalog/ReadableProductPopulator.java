package com.salesmanager.shop.populator.catalog;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.TreeMap;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang.Validate;
import org.apache.commons.lang3.StringUtils;

import com.salesmanager.core.business.exception.ConversionException;
import com.salesmanager.core.business.services.catalog.product.PricingService;
import com.salesmanager.core.business.utils.AbstractDataPopulator;
import com.salesmanager.core.model.catalog.category.Category;
import com.salesmanager.core.model.catalog.product.Product;
import com.salesmanager.core.model.catalog.product.attribute.ProductAttribute;
import com.salesmanager.core.model.catalog.product.attribute.ProductOptionDescription;
import com.salesmanager.core.model.catalog.product.attribute.ProductOptionValue;
import com.salesmanager.core.model.catalog.product.attribute.ProductOptionValueDescription;
import com.salesmanager.core.model.catalog.product.availability.ProductAvailability;
import com.salesmanager.core.model.catalog.product.description.ProductDescription;
import com.salesmanager.core.model.catalog.product.image.ProductImage;
import com.salesmanager.core.model.catalog.product.manufacturer.ManufacturerDescription;
import com.salesmanager.core.model.catalog.product.price.FinalPrice;
import com.salesmanager.core.model.catalog.product.price.ProductPrice;
import com.salesmanager.core.model.catalog.product.price.ProductPriceDescription;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.shop.model.catalog.category.ReadableCategory;
import com.salesmanager.shop.model.catalog.manufacturer.ReadableManufacturer;
import com.salesmanager.shop.model.catalog.product.ProductSpecification;
import com.salesmanager.shop.model.catalog.product.ReadableImage;
import com.salesmanager.shop.model.catalog.product.ReadableProduct;
import com.salesmanager.shop.model.catalog.product.ReadableProductFull;
import com.salesmanager.shop.model.catalog.product.ReadableProductPrice;
import com.salesmanager.shop.model.catalog.product.RentalOwner;
import com.salesmanager.shop.model.catalog.product.attribute.ReadableProductAttribute;
import com.salesmanager.shop.model.catalog.product.attribute.ReadableProductAttributeValue;
import com.salesmanager.shop.model.catalog.product.attribute.ReadableProductOption;
import com.salesmanager.shop.model.catalog.product.attribute.api.ReadableProductOptionValueEntity;
import com.salesmanager.shop.model.catalog.product.type.ReadableProductType;
import com.salesmanager.shop.utils.DateUtil;
import com.salesmanager.shop.utils.ImageFilePath;



public class ReadableProductPopulator extends
		AbstractDataPopulator<Product, ReadableProduct> {
	
	private PricingService pricingService;
	
	private ImageFilePath imageUtils;

	public ImageFilePath getimageUtils() {
		return imageUtils;
	}

	public void setimageUtils(ImageFilePath imageUtils) {
		this.imageUtils = imageUtils;
	}

	public PricingService getPricingService() {
		return pricingService;
	}

	public void setPricingService(PricingService pricingService) {
		this.pricingService = pricingService;
	}

	@Override
	public ReadableProduct populate(Product source,
			ReadableProduct target, MerchantStore store, Language language)
			throws ConversionException {
		Validate.notNull(pricingService, "Requires to set PricingService");
		Validate.notNull(imageUtils, "Requires to set imageUtils");

		
		try {
		  
	        List<com.salesmanager.shop.model.catalog.product.ProductDescription> fulldescriptions = new ArrayList<com.salesmanager.shop.model.catalog.product.ProductDescription>();
	        if(language == null) {
	          target = new ReadableProductFull();
	        }

	        if(target==null) {
	        	target = new ReadableProduct();
	        }

	        ProductDescription description = source.getProductDescription();
	        
	        if(source.getDescriptions()!=null && source.getDescriptions().size()>0) {
	          for(ProductDescription desc : source.getDescriptions()) {
                if(language != null && desc.getLanguage()!=null && desc.getLanguage().getId().intValue() == language.getId().intValue()) {
                    description = desc;
                    break;
                } else {
                  fulldescriptions.add(populateDescription(desc));
                }
              }
	        }
	        
		     if(target instanceof ReadableProductFull) {
		          ((ReadableProductFull)target).setDescriptions(fulldescriptions);
		      }
		     
		        if(language == null) {
			          language = store.getDefaultLanguage();
			    }

		   final Language lang = language;
	
			target.setId(source.getId());
			target.setAvailable(source.isAvailable());
			target.setProductShipeable(source.isProductShipeable());
			
			ProductSpecification specifications = new ProductSpecification();
			specifications.setHeight(source.getProductHeight());
			specifications.setLength(source.getProductLength());
			specifications.setWeight(source.getProductWeight());
			specifications.setWidth(source.getProductWidth());
			target.setProductSpecifications(specifications);
			

			target.setPreOrder(source.isPreOrder());
			target.setRefSku(source.getRefSku());
			target.setSortOrder(source.getSortOrder());
			
			
			target.setCondition(source.getCondition());
			
			if(source.getType() != null) {
				ReadableProductType readableType = new ReadableProductType();
				readableType.setCode(source.getType().getCode());
				readableType.setName(source.getType().getCode());
				target.setType(readableType);
			}
			
			
			//RENTAL
			if(source.getRentalDuration()!=null) {
				target.setRentalDuration(source.getRentalDuration());
			}
			if(source.getRentalPeriod()!=null) {
				target.setRentalPeriod(source.getRentalPeriod());
			}
			target.setRentalStatus(source.getRentalStatus());
			
			/**
			 * END RENTAL
			 */
			
			if(source.getOwner() != null) {
				RentalOwner owner = new RentalOwner();
				owner.setId(source.getOwner().getId());
				owner.setEmailAddress(source.getOwner().getEmailAddress());
				owner.setFirstName(source.getOwner().getBilling().getFirstName());
				owner.setLastName(source.getOwner().getBilling().getLastName());
				com.salesmanager.shop.model.customer.address.Address address = new com.salesmanager.shop.model.customer.address.Address();
				address.setAddress(source.getOwner().getBilling().getAddress());
				address.setBillingAddress(true);
				address.setCity(source.getOwner().getBilling().getCity());
				address.setCompany(source.getOwner().getBilling().getCompany());
				address.setCountry(source.getOwner().getBilling().getCountry().getIsoCode());
				address.setZone(source.getOwner().getBilling().getZone().getCode());
				address.setLatitude(source.getOwner().getBilling().getLatitude());
				address.setLongitude(source.getOwner().getBilling().getLongitude());
				address.setPhone(source.getOwner().getBilling().getTelephone());
				address.setPostalCode(source.getOwner().getBilling().getPostalCode());
				owner.setAddress(address);
				target.setOwner(owner);
			}
			
			
			if(source.getDateAvailable() != null) {
				target.setDateAvailable(DateUtil.formatDate(source.getDateAvailable()));
			}
			
			if(source.getAuditSection()!=null) {
			  target.setCreationDate(DateUtil.formatDate(source.getAuditSection().getDateCreated()));
			}
			
			if(source.getProductReviewAvg()!=null) {
				double avg = source.getProductReviewAvg().doubleValue();
				double rating = Math.round(avg * 2) / 2.0f;
				target.setRating(rating);
			}
			target.setProductVirtual(source.getProductVirtual());
			if(source.getProductReviewCount()!=null) {
				target.setRatingCount(source.getProductReviewCount().intValue());
			}
			if(description!=null) {
			    com.salesmanager.shop.model.catalog.product.ProductDescription tragetDescription = populateDescription(description);
				target.setDescription(tragetDescription);
				
			}
			
			if(source.getManufacturer()!=null) {
				ManufacturerDescription manufacturer = source.getManufacturer().getDescriptions().iterator().next(); 
				ReadableManufacturer manufacturerEntity = new ReadableManufacturer();
				com.salesmanager.shop.model.catalog.manufacturer.ManufacturerDescription d = new com.salesmanager.shop.model.catalog.manufacturer.ManufacturerDescription(); 
				d.setName(manufacturer.getName());
				manufacturerEntity.setDescription(d);
				manufacturerEntity.setId(source.getManufacturer().getId());
				manufacturerEntity.setOrder(source.getManufacturer().getOrder());
				manufacturerEntity.setCode(source.getManufacturer().getCode());
				target.setManufacturer(manufacturerEntity);
			}
			
			if(source.getType() != null) {
			  ReadableProductType type = new ReadableProductType();
			  type.setId(source.getType().getId());
			  type.setCode(source.getType().getCode());
			  type.setName(source.getType().getCode());//need name
			  target.setType(type);
			}
			
			Set<ProductImage> images = source.getImages();
			if(images!=null && images.size()>0) {
				List<ReadableImage> imageList = new ArrayList<ReadableImage>();
				
				String contextPath = imageUtils.getContextPath();
				
				for(ProductImage img : images) {
					ReadableImage prdImage = new ReadableImage();
					prdImage.setImageName(img.getProductImage());
					prdImage.setDefaultImage(img.isDefaultImage());

					StringBuilder imgPath = new StringBuilder();
					imgPath.append(contextPath).append(imageUtils.buildProductImageUtils(store, source.getSku(), img.getProductImage()));

					prdImage.setImageUrl(imgPath.toString());
					prdImage.setId(img.getId());
					prdImage.setImageType(img.getImageType());
					if(img.getProductImageUrl()!=null){
						prdImage.setExternalUrl(img.getProductImageUrl());
					}
					if(img.getImageType()==1 && img.getProductImageUrl()!=null) {//video
						prdImage.setVideoUrl(img.getProductImageUrl());
					}
					
					if(prdImage.isDefaultImage()) {
						target.setImage(prdImage);
					}
					
					imageList.add(prdImage);
				}
				target
				.setImages(imageList);
			}
			
			if(!CollectionUtils.isEmpty(source.getCategories())) {
				
				ReadableCategoryPopulator categoryPopulator = new ReadableCategoryPopulator();
				List<ReadableCategory> categoryList = new ArrayList<ReadableCategory>();
				
				for(Category category : source.getCategories()) {
					
					ReadableCategory readableCategory = new ReadableCategory();
					categoryPopulator.populate(category, readableCategory, store, language);
					categoryList.add(readableCategory);
					
				}
				
				target.setCategories(categoryList);
				
			}
			
			if(!CollectionUtils.isEmpty(source.getAttributes())) {
			
				Set<ProductAttribute> attributes = source.getAttributes();
				

				//split read only and options
				Map<Long,ReadableProductAttribute> readOnlyAttributes = null;
				Map<Long,ReadableProductOption> selectableOptions = null;
				
				if(!CollectionUtils.isEmpty(attributes)) {
								
					for(ProductAttribute attribute : attributes) {
							ReadableProductOption opt = null;
							ReadableProductAttribute attr = null;
							ReadableProductOptionValueEntity optValue = new ReadableProductOptionValueEntity();
							ReadableProductAttributeValue attrValue = new ReadableProductAttributeValue();
							
							ProductOptionValue optionValue = attribute.getProductOptionValue();
							
							if(attribute.getAttributeDisplayOnly()) {//read only attribute
								if(readOnlyAttributes==null) {
									readOnlyAttributes = new TreeMap<Long,ReadableProductAttribute>();
								}
								attr = readOnlyAttributes.get(attribute.getProductOption().getId());
								if(attr==null) {
									attr = createAttribute(attribute, language);
								}
								if(attr!=null) {
									readOnlyAttributes.put(attribute.getProductOption().getId(), attr);
								}
								
								
								attrValue.setDefaultValue(attribute.getAttributeDefault());
								if(attribute.getProductOptionValue()!=null) {
								  attrValue.setId(attribute.getProductOptionValue().getId());//id of the option value
								} else {
								  attrValue.setId(attribute.getId());
								}
								attrValue.setLang(language.getCode());


								attrValue.setSortOrder(0);
								if(attribute.getProductOptionSortOrder()!=null) {
									attrValue.setSortOrder(attribute.getProductOptionSortOrder().intValue());
								}
								
								List<ProductOptionValueDescription> podescriptions = optionValue.getDescriptionsSettoList();
								ProductOptionValueDescription podescription = null;
								if(podescriptions!=null && podescriptions.size()>0) {
									podescription = podescriptions.get(0);
									if(podescriptions.size()>1) {
										for(ProductOptionValueDescription optionValueDescription : podescriptions) {
											if(optionValueDescription.getLanguage().getId().intValue()==language.getId().intValue()) {
												podescription = optionValueDescription;
												break;
											}
										}
									}
								}
								attrValue.setName(podescription.getName());
								attrValue.setDescription(podescription.getDescription());
								
								if(attr!=null) {
									attr.getAttributeValues().add(attrValue);
								}
								
								
							} else {//selectable option
								
								if(selectableOptions==null) {
									selectableOptions = new TreeMap<Long,ReadableProductOption>();
								}
								opt = selectableOptions.get(attribute.getProductOption().getId());
								if(opt==null) {
									opt = createOption(attribute, language);
								}
								if(opt!=null) {
									selectableOptions.put(attribute.getProductOption().getId(), opt);
								}
								
								optValue.setDefaultValue(attribute.getAttributeDefault());
								optValue.setId(attribute.getProductOptionValue().getId());
								optValue.setCode(attribute.getProductOptionValue().getCode());
								com.salesmanager.shop.model.catalog.product.attribute.ProductOptionValueDescription valueDescription = new com.salesmanager.shop.model.catalog.product.attribute.ProductOptionValueDescription();
								valueDescription.setLanguage(language.getCode());
								//optValue.setLang(language.getCode());
								if(attribute.getProductAttributePrice()!=null && attribute.getProductAttributePrice().doubleValue()>0) {
									String formatedPrice = pricingService.getDisplayAmount(attribute.getProductAttributePrice(), store);
									optValue.setPrice(formatedPrice);
								}
								
								if(!StringUtils.isBlank(attribute.getProductOptionValue().getProductOptionValueImage())) {
									optValue.setImage(imageUtils.buildProductPropertyImageUtils(store, attribute.getProductOptionValue().getProductOptionValueImage()));
								}
								optValue.setSortOrder(0);
								if(attribute.getProductOptionSortOrder()!=null) {
									optValue.setSortOrder(attribute.getProductOptionSortOrder().intValue());
								}
								
								List<ProductOptionValueDescription> podescriptions = optionValue.getDescriptionsSettoList();
								ProductOptionValueDescription podescription = null;
								if(podescriptions!=null && podescriptions.size()>0) {
									podescription = podescriptions.get(0);
									if(podescriptions.size()>1) {
										for(ProductOptionValueDescription optionValueDescription : podescriptions) {
											if(optionValueDescription.getLanguage().getId().intValue()==language.getId().intValue()) {
												podescription = optionValueDescription;
												break;
											}
										}
									}
								}
								valueDescription.setName(podescription.getName());
								valueDescription.setDescription(podescription.getDescription());
								optValue.setDescription(valueDescription);
								
								if(opt!=null) {
									opt.getOptionValues().add(optValue);
								}
							}

						}
						
					}
				
				if(selectableOptions != null) {
					List<ReadableProductOption> options = new ArrayList<ReadableProductOption>(selectableOptions.values());
					target.setOptions(options);
				}

			
			}
			

			
			//remove products from invisible category -> set visible = false
/*			Set<Category> categories = source.getCategories();
			boolean isVisible = true;
			if(!CollectionUtils.isEmpty(categories)) {
				for(Category c : categories) {
					if(c.isVisible()) {
						isVisible = true;
						break;
					} else {
						isVisible = false;
					}
				}
			}*/
			
			//target.setVisible(isVisible);
			
			//availability
			ProductAvailability availability = null;
			for(ProductAvailability a : source.getAvailabilities()) {
				//TODO validate region
				//if(availability.getRegion().equals(Constants.ALL_REGIONS)) {//TODO REL 2.1 accept a region
					availability = a;
					target.setQuantity(availability.getProductQuantity() == null ? 1:availability.getProductQuantity());
					target.setQuantityOrderMaximum(availability.getProductQuantityOrderMax() == null ? 1:availability.getProductQuantityOrderMax());
					target.setQuantityOrderMinimum(availability.getProductQuantityOrderMin()==null ? 1:availability.getProductQuantityOrderMin());
					if(availability.getProductQuantity().intValue() > 0 && target.isAvailable()) {
							target.setCanBePurchased(true);
					}
				//}
			}
			
	
			target.setSku(source.getSku());
	
			FinalPrice price = pricingService.calculateProductPrice(source);
			
			if(price != null) {

				target.setFinalPrice(pricingService.getDisplayAmount(price.getFinalPrice(), store));
				target.setPrice(price.getFinalPrice());
				target.setOriginalPrice(pricingService.getDisplayAmount(price.getOriginalPrice(), store));
				
				if(price.isDiscounted()) {
					target.setDiscounted(true);
				}
				
				//price appender
				if(availability != null) {
					Set<ProductPrice> prices = availability.getPrices();
					if(!CollectionUtils.isEmpty(prices)) {
						ReadableProductPrice readableProductPrice = new ReadableProductPrice();
						readableProductPrice.setDiscounted(target.isDiscounted());
						readableProductPrice.setFinalPrice(target.getFinalPrice());
						readableProductPrice.setOriginalPrice(target.getOriginalPrice());
						
						Optional<ProductPrice> pr = prices.stream().filter(p -> p.getCode().equals(ProductPrice.DEFAULT_PRICE_CODE))
								.findFirst();
						
						target.setProductPrice(readableProductPrice);
						
						if(pr.isPresent()) {
							readableProductPrice.setId(pr.get().getId());
							Optional<ProductPriceDescription> d = pr.get().getDescriptions().stream().filter(desc -> desc.getLanguage().getCode().equals(lang.getCode())).findFirst();
							if(d.isPresent()) {
								com.salesmanager.shop.model.catalog.product.ProductPriceDescription priceDescription = new com.salesmanager.shop.model.catalog.product.ProductPriceDescription();
								priceDescription.setLanguage(language.getCode());
								priceDescription.setId(d.get().getId());
								priceDescription.setPriceAppender(d.get().getPriceAppender());
								readableProductPrice.setDescription(priceDescription);
							}
						}

					}
				}
			
			}
	



		     if(target instanceof ReadableProductFull) {
		          ((ReadableProductFull)target).setDescriptions(fulldescriptions);
		      }

			
			return target;
		
		} catch (Exception e) {
			throw new ConversionException(e);
		}
	}
	

	
	private ReadableProductOption createOption(ProductAttribute productAttribute, Language language) {
		

		ReadableProductOption option = new ReadableProductOption();
		option.setId(productAttribute.getProductOption().getId());//attribute of the option
		option.setType(productAttribute.getProductOption().getProductOptionType());
		option.setCode(productAttribute.getProductOption().getCode());
		List<ProductOptionDescription> descriptions = productAttribute.getProductOption().getDescriptionsSettoList();
		ProductOptionDescription description = null;
		if(descriptions!=null && descriptions.size()>0) {
			description = descriptions.get(0);
			if(descriptions.size()>1) {
				for(ProductOptionDescription optionDescription : descriptions) {
					if(optionDescription.getLanguage().getCode().equals(language.getCode())) {
						description = optionDescription;
						break;
					}
				}
			}
		}
		
		if(description==null) {
			return null;
		}

		option.setLang(language.getCode());
		option.setName(description.getName());
		option.setCode(productAttribute.getProductOption().getCode());

		
		return option;
		
	}
	
	private ReadableProductAttribute createAttribute(ProductAttribute productAttribute, Language language) {
		

		ReadableProductAttribute attr = new ReadableProductAttribute();
		attr.setId(productAttribute.getProductOption().getId());//attribute of the option
		attr.setType(productAttribute.getProductOption().getProductOptionType());
		List<ProductOptionDescription> descriptions = productAttribute.getProductOption().getDescriptionsSettoList();
		ProductOptionDescription description = null;
		if(descriptions!=null && descriptions.size()>0) {
			description = descriptions.get(0);
			if(descriptions.size()>1) {
				for(ProductOptionDescription optionDescription : descriptions) {
					if(optionDescription.getLanguage().getId().intValue()==language.getId().intValue()) {
						description = optionDescription;
						break;
					}
				}
			}
		}
		
		if(description==null) {
			return null;
		}

		attr.setLang(language.getCode());
		attr.setName(description.getName());
		attr.setCode(productAttribute.getProductOption().getCode());

		
		return attr;
		
	}




	@Override
	protected ReadableProduct createTarget() {
		// TODO Auto-generated method stub
		return null;
	}
	
    com.salesmanager.shop.model.catalog.product.ProductDescription populateDescription(ProductDescription description) {
      if(description == null) {
        return null;
      }
     
      com.salesmanager.shop.model.catalog.product.ProductDescription tragetDescription = new com.salesmanager.shop.model.catalog.product.ProductDescription();
      tragetDescription.setFriendlyUrl(description.getSeUrl());
      tragetDescription.setName(description.getName());
      tragetDescription.setId(description.getId());
      if(!StringUtils.isBlank(description.getMetatagTitle())) {
          tragetDescription.setTitle(description.getMetatagTitle());
      } else {
          tragetDescription.setTitle(description.getName());
      }
      tragetDescription.setMetaDescription(description.getMetatagDescription());
      tragetDescription.setDescription(description.getDescription());
      tragetDescription.setHighlights(description.getProductHighlight());
      tragetDescription.setLanguage(description.getLanguage().getCode());
      tragetDescription.setKeyWords(description.getMetatagKeywords());

      if(description.getLanguage() != null) {
        tragetDescription.setLanguage(description.getLanguage().getCode());
      }
      return tragetDescription;
    }

}
