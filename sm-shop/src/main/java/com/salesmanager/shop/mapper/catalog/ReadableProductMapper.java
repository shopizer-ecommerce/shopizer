package com.salesmanager.shop.mapper.catalog;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.salesmanager.core.business.services.catalog.product.PricingService;
import com.salesmanager.core.model.catalog.category.Category;
import com.salesmanager.core.model.catalog.product.Product;
import com.salesmanager.core.model.catalog.product.attribute.ProductAttribute;
import com.salesmanager.core.model.catalog.product.attribute.ProductOptionDescription;
import com.salesmanager.core.model.catalog.product.attribute.ProductOptionValue;
import com.salesmanager.core.model.catalog.product.attribute.ProductOptionValueDescription;
import com.salesmanager.core.model.catalog.product.availability.ProductAvailability;
import com.salesmanager.core.model.catalog.product.description.ProductDescription;
import com.salesmanager.core.model.catalog.product.image.ProductImage;
import com.salesmanager.core.model.catalog.product.price.FinalPrice;
import com.salesmanager.core.model.catalog.product.price.ProductPrice;
import com.salesmanager.core.model.catalog.product.price.ProductPriceDescription;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.shop.mapper.Mapper;
import com.salesmanager.shop.model.catalog.category.ReadableCategory;
import com.salesmanager.shop.model.catalog.manufacturer.ReadableManufacturer;
import com.salesmanager.shop.model.catalog.product.ProductSpecification;
import com.salesmanager.shop.model.catalog.product.ReadableImage;
import com.salesmanager.shop.model.catalog.product.ReadableProduct;
import com.salesmanager.shop.model.catalog.product.ReadableProductPrice;
import com.salesmanager.shop.model.catalog.product.attribute.ReadableProductAttribute;
import com.salesmanager.shop.model.catalog.product.attribute.ReadableProductAttributeValue;
import com.salesmanager.shop.model.catalog.product.attribute.ReadableProductOption;
import com.salesmanager.shop.model.catalog.product.attribute.ReadableProductProperty;
import com.salesmanager.shop.model.catalog.product.attribute.ReadableProductPropertyValue;
import com.salesmanager.shop.model.catalog.product.attribute.api.ReadableProductOptionValueEntity;
import com.salesmanager.shop.model.catalog.product.type.ReadableProductType;
import com.salesmanager.shop.model.references.DimensionUnitOfMeasure;
import com.salesmanager.shop.model.references.WeightUnitOfMeasure;
import com.salesmanager.shop.store.api.exception.ConversionRuntimeException;
import com.salesmanager.shop.utils.DateUtil;
import com.salesmanager.shop.utils.ImageFilePath;

/**
 * Works for product v2 model
 * 
 * @author carlsamson
 *
 */
@Component
public class ReadableProductMapper implements Mapper<Product, ReadableProduct> {

	// uses code that is similar to ProductDefinition
	@Autowired
	@Qualifier("img")
	private ImageFilePath imageUtils;

	@Autowired
	private ReadableCategoryMapper readableCategoryMapper;

	@Autowired
	private ReadableProductTypeMapper readableProductTypeMapper;

	@Autowired
	private ReadableManufacturerMapper readableManufacturerMapper;

	@Autowired
	private PricingService pricingService;

	@Override
	public ReadableProduct convert(Product source, MerchantStore store, Language language) {
		ReadableProduct product = new ReadableProduct();
		return this.merge(source, product, store, language);
	}

	@Override
	public ReadableProduct merge(Product source, ReadableProduct destination, MerchantStore store, Language language) {

		Validate.notNull(source, "Product cannot be null");
		Validate.notNull(destination, "Product destination cannot be null");

		destination.setSku(source.getSku());
		destination.setRefSku(source.getRefSku());
		destination.setId(source.getId());
		destination.setDateAvailable(DateUtil.formatDate(source.getDateAvailable()));

		ProductDescription description = null;
		if (source.getDescriptions() != null && source.getDescriptions().size() > 0) {
			for (ProductDescription desc : source.getDescriptions()) {
				if (language != null && desc.getLanguage() != null
						&& desc.getLanguage().getId().intValue() == language.getId().intValue()) {
					description = desc;
					break;
				}
			}

			destination.setId(source.getId());
			destination.setAvailable(source.isAvailable());
			destination.setProductShipeable(source.isProductShipeable());

			ProductSpecification specifications = new ProductSpecification();
			specifications.setHeight(source.getProductHeight());
			specifications.setLength(source.getProductLength());
			specifications.setWeight(source.getProductWeight());
			specifications.setWidth(source.getProductWidth());
			destination.setProductSpecifications(specifications);

			destination.setPreOrder(source.isPreOrder());
			destination.setRefSku(source.getRefSku());
			destination.setSortOrder(source.getSortOrder());

			if (source.getType() != null) {
				ReadableProductType readableType = readableProductTypeMapper.convert(source.getType(), store, language);
				destination.setType(readableType);
			}

			if (source.getDateAvailable() != null) {
				destination.setDateAvailable(DateUtil.formatDate(source.getDateAvailable()));
			}

			if (source.getAuditSection() != null) {
				destination.setCreationDate(DateUtil.formatDate(source.getAuditSection().getDateCreated()));
			}

			destination.setProductVirtual(source.getProductVirtual());

			if (source.getProductReviewCount() != null) {
				destination.setRatingCount(source.getProductReviewCount().intValue());
			}

			if (source.getManufacturer() != null) {
				ReadableManufacturer manufacturer = readableManufacturerMapper.convert(source.getManufacturer(), store,
						language);
				destination.setManufacturer(manufacturer);
			}

			// images
			Set<ProductImage> images = source.getImages();
			if (CollectionUtils.isNotEmpty(images)) {

				List<ReadableImage> imageList = images.stream().map(i -> this.convertImage(source, i, store))
						.collect(Collectors.toList());
				destination.setImages(imageList);
			}

			// read only product values
			if (!CollectionUtils.isEmpty(source.getAttributes())) {

				Set<ProductAttribute> attributes = source.getAttributes();

				// split read only and options
				// Map<Long,ReadableProductAttribute> readOnlyAttributes = null;
				Map<Long, ReadableProductProperty> properties = null;
				Map<Long, ReadableProductOption> selectableOptions = null;

				if (!CollectionUtils.isEmpty(attributes)) {

					for (ProductAttribute attribute : attributes) {
						ReadableProductOption opt = null;
						ReadableProductAttribute attr = null;
						ReadableProductProperty property = null;
						ReadableProductPropertyValue propertyValue = null;
						ReadableProductOptionValueEntity optValue = new ReadableProductOptionValueEntity();
						ReadableProductAttributeValue attrValue = new ReadableProductAttributeValue();

						ProductOptionValue optionValue = attribute.getProductOptionValue();

						// we need to set readonly attributes only
						if (attribute.getAttributeDisplayOnly()) {// read only attribute = property

							property = createProperty(attribute, language);

							ReadableProductOption readableOption = new ReadableProductOption(); // that is the property
							ReadableProductPropertyValue readableOptionValue = new ReadableProductPropertyValue();

							readableOption.setCode(attribute.getProductOption().getCode());
							readableOption.setId(attribute.getProductOption().getId());

							Set<ProductOptionDescription> podescriptions = attribute.getProductOption()
									.getDescriptions();
							if (podescriptions != null && podescriptions.size() > 0) {
								for (ProductOptionDescription optionDescription : podescriptions) {
									if (optionDescription.getLanguage().getCode().equals(language.getCode())) {
										readableOption.setName(optionDescription.getName());
									}
								}
							}

							property.setProperty(readableOption);

							Set<ProductOptionValueDescription> povdescriptions = attribute.getProductOptionValue()
									.getDescriptions();
							readableOptionValue.setId(attribute.getProductOptionValue().getId());
							if (povdescriptions != null && povdescriptions.size() > 0) {
								for (ProductOptionValueDescription optionValueDescription : povdescriptions) {
									if (optionValueDescription.getLanguage().getCode().equals(language.getCode())) {
										readableOptionValue.setName(optionValueDescription.getName());
									}
								}
							}

							property.setPropertyValue(readableOptionValue);

							destination.getProperties().add(property);

						}

						if (selectableOptions != null) {
							List<ReadableProductOption> options = new ArrayList<ReadableProductOption>(
									selectableOptions.values());
							destination.setOptions(options);
						}

					}
				}
			}
		}

		// availability
		ProductAvailability availability = null;
		for (ProductAvailability a : source.getAvailabilities()) {
			// TODO validate region
			// if(availability.getRegion().equals(Constants.ALL_REGIONS)) {//TODO REL 2.1
			// accept a region
			availability = a;
			destination.setQuantity(availability.getProductQuantity() == null ? 1 : availability.getProductQuantity());
			destination.setQuantityOrderMaximum(
					availability.getProductQuantityOrderMax() == null ? 1 : availability.getProductQuantityOrderMax());
			destination.setQuantityOrderMinimum(
					availability.getProductQuantityOrderMin() == null ? 1 : availability.getProductQuantityOrderMin());
			if (availability.getProductQuantity().intValue() > 0 && destination.isAvailable()) {
				destination.setCanBePurchased(true);
			}
			// }
		}

		destination.setSku(source.getSku());

		try {

			FinalPrice price = pricingService.calculateProductPrice(source);

			if (price != null) {

				destination.setFinalPrice(pricingService.getDisplayAmount(price.getFinalPrice(), store));
				destination.setPrice(price.getFinalPrice());
				destination.setOriginalPrice(pricingService.getDisplayAmount(price.getOriginalPrice(), store));

				if (price.isDiscounted()) {
					destination.setDiscounted(true);
				}

				// price appender
				if (availability != null) {
					Set<ProductPrice> prices = availability.getPrices();
					if (!CollectionUtils.isEmpty(prices)) {
						ReadableProductPrice readableProductPrice = new ReadableProductPrice();
						readableProductPrice.setDiscounted(destination.isDiscounted());
						readableProductPrice.setFinalPrice(destination.getFinalPrice());
						readableProductPrice.setOriginalPrice(destination.getOriginalPrice());

						Optional<ProductPrice> pr = prices.stream()
								.filter(p -> p.getCode().equals(ProductPrice.DEFAULT_PRICE_CODE)).findFirst();

						destination.setProductPrice(readableProductPrice);

						if (pr.isPresent()) {
							readableProductPrice.setId(pr.get().getId());
							Optional<ProductPriceDescription> d = pr.get().getDescriptions().stream()
									.filter(desc -> desc.getLanguage().getCode().equals(language.getCode()))
									.findFirst();
							if (d.isPresent()) {
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

		} catch (Exception e) {
			throw new ConversionRuntimeException("An error while converting product price", e);
		}

		if (source.getProductReviewAvg() != null) {
			double avg = source.getProductReviewAvg().doubleValue();
			double rating = Math.round(avg * 2) / 2.0f;
			destination.setRating(rating);
		}

		if (source.getProductReviewCount() != null) {
			destination.setRatingCount(source.getProductReviewCount().intValue());
		}

		if (description != null) {
			com.salesmanager.shop.model.catalog.product.ProductDescription tragetDescription = populateDescription(
					description);
			destination.setDescription(tragetDescription);

		}

		if (!CollectionUtils.isEmpty(source.getCategories())) {
			List<ReadableCategory> categoryList = new ArrayList<ReadableCategory>();
			for (Category category : source.getCategories()) {
				ReadableCategory readableCategory = readableCategoryMapper.convert(category, store, language);
				categoryList.add(readableCategory);

			}
			destination.setCategories(categoryList);
		}

		ProductSpecification specifications = new ProductSpecification();
		specifications.setHeight(source.getProductHeight());
		specifications.setLength(source.getProductLength());
		specifications.setWeight(source.getProductWeight());
		specifications.setWidth(source.getProductWidth());
		if (!StringUtils.isBlank(store.getSeizeunitcode())) {
			specifications
					.setDimensionUnitOfMeasure(DimensionUnitOfMeasure.valueOf(store.getSeizeunitcode().toLowerCase()));
		}
		if (!StringUtils.isBlank(store.getWeightunitcode())) {
			specifications.setWeightUnitOfMeasure(WeightUnitOfMeasure.valueOf(store.getWeightunitcode().toLowerCase()));
		}
		destination.setProductSpecifications(specifications);

		destination.setSortOrder(source.getSortOrder());

		return destination;
	}

	private ReadableImage convertImage(Product product, ProductImage image, MerchantStore store) {
		ReadableImage prdImage = new ReadableImage();
		prdImage.setImageName(image.getProductImage());
		prdImage.setDefaultImage(image.isDefaultImage());

		// TODO product variant image
		StringBuilder imgPath = new StringBuilder();
		imgPath.append(imageUtils.getContextPath())
				.append(imageUtils.buildProductImageUtils(store, product.getSku(), image.getProductImage()));

		prdImage.setImageUrl(imgPath.toString());
		prdImage.setId(image.getId());
		prdImage.setImageType(image.getImageType());
		if (image.getProductImageUrl() != null) {
			prdImage.setExternalUrl(image.getProductImageUrl());
		}
		if (image.getImageType() == 1 && image.getProductImageUrl() != null) {// video
			prdImage.setVideoUrl(image.getProductImageUrl());
		}

		if (prdImage.isDefaultImage()) {
			prdImage.setDefaultImage(true);
		}

		return prdImage;
	}

	private com.salesmanager.shop.model.catalog.product.ProductDescription populateDescription(
			ProductDescription description) {
		if (description == null) {
			return null;
		}

		com.salesmanager.shop.model.catalog.product.ProductDescription tragetDescription = new com.salesmanager.shop.model.catalog.product.ProductDescription();
		tragetDescription.setFriendlyUrl(description.getSeUrl());
		tragetDescription.setName(description.getName());
		tragetDescription.setId(description.getId());
		if (!StringUtils.isBlank(description.getMetatagTitle())) {
			tragetDescription.setTitle(description.getMetatagTitle());
		} else {
			tragetDescription.setTitle(description.getName());
		}
		tragetDescription.setMetaDescription(description.getMetatagDescription());
		tragetDescription.setDescription(description.getDescription());
		tragetDescription.setHighlights(description.getProductHighlight());
		tragetDescription.setLanguage(description.getLanguage().getCode());
		tragetDescription.setKeyWords(description.getMetatagKeywords());

		if (description.getLanguage() != null) {
			tragetDescription.setLanguage(description.getLanguage().getCode());
		}
		return tragetDescription;
	}

	private ReadableProductProperty createProperty(ProductAttribute productAttribute, Language language) {

		ReadableProductProperty attr = new ReadableProductProperty();
		attr.setId(productAttribute.getProductOption().getId());// attribute of the option
		attr.setType(productAttribute.getProductOption().getProductOptionType());

		List<ProductOptionDescription> descriptions = productAttribute.getProductOption().getDescriptionsSettoList();

		ReadableProductPropertyValue propertyValue = new ReadableProductPropertyValue();

		if (descriptions != null && descriptions.size() > 0) {
			for (ProductOptionDescription optionDescription : descriptions) {
				com.salesmanager.shop.model.catalog.product.attribute.ProductOptionValueDescription productOptionValueDescription = new com.salesmanager.shop.model.catalog.product.attribute.ProductOptionValueDescription();
				productOptionValueDescription.setId(optionDescription.getId());
				productOptionValueDescription.setLanguage(optionDescription.getLanguage().getCode());
				productOptionValueDescription.setName(optionDescription.getName());
				propertyValue.getValues().add(productOptionValueDescription);

			}
		}

		attr.setCode(productAttribute.getProductOption().getCode());
		return attr;

	}

}
