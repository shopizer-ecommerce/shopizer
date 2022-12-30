package com.salesmanager.shop.mapper.catalog.product;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.salesmanager.core.model.catalog.category.Category;
import com.salesmanager.core.model.catalog.product.Product;
import com.salesmanager.core.model.catalog.product.availability.ProductAvailability;
import com.salesmanager.core.model.catalog.product.description.ProductDescription;
import com.salesmanager.core.model.catalog.product.image.ProductImage;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.shop.mapper.Mapper;
import com.salesmanager.shop.mapper.catalog.ReadableCategoryMapper;
import com.salesmanager.shop.mapper.catalog.ReadableManufacturerMapper;
import com.salesmanager.shop.mapper.catalog.ReadableProductTypeMapper;
import com.salesmanager.shop.mapper.inventory.ReadableInventoryMapper;
import com.salesmanager.shop.model.catalog.category.ReadableCategory;
import com.salesmanager.shop.model.catalog.manufacturer.ReadableManufacturer;
import com.salesmanager.shop.model.catalog.product.ReadableImage;
import com.salesmanager.shop.model.catalog.product.inventory.ReadableInventory;
import com.salesmanager.shop.model.catalog.product.product.ProductSpecification;
import com.salesmanager.shop.model.catalog.product.product.definition.ReadableProductDefinition;
import com.salesmanager.shop.model.catalog.product.product.definition.ReadableProductDefinitionFull;
import com.salesmanager.shop.model.catalog.product.type.ReadableProductType;
import com.salesmanager.shop.model.references.DimensionUnitOfMeasure;
import com.salesmanager.shop.model.references.WeightUnitOfMeasure;
import com.salesmanager.shop.utils.DateUtil;
import com.salesmanager.shop.utils.ImageFilePath;

@Component
public class ReadableProductDefinitionMapper implements Mapper<Product, ReadableProductDefinition> {

	@Autowired
	private ReadableCategoryMapper readableCategoryMapper;

	@Autowired
	private ReadableProductTypeMapper readableProductTypeMapper;

	@Autowired
	private ReadableManufacturerMapper readableManufacturerMapper;
	
	@Autowired
	private ReadableInventoryMapper readableInventoryMapper;
	
	@Autowired
	@Qualifier("img")
	private ImageFilePath imageUtils;

	@Override
	public ReadableProductDefinition convert(Product source, MerchantStore store, Language language) {
		ReadableProductDefinition target = new ReadableProductDefinition();
		return this.merge(source, target, store, language);
	}

	@Override
	public ReadableProductDefinition merge(Product source, ReadableProductDefinition destination, MerchantStore store,
			Language language) {
		Validate.notNull(source, "Product cannot be null");
		Validate.notNull(destination, "Product destination cannot be null");

		ReadableProductDefinition returnDestination = destination;
		if (language == null) {
			returnDestination = new ReadableProductDefinitionFull();
		}

		List<com.salesmanager.shop.model.catalog.product.ProductDescription> fulldescriptions = new ArrayList<com.salesmanager.shop.model.catalog.product.ProductDescription>();

		returnDestination.setIdentifier(source.getSku());
		returnDestination.setId(source.getId());
		returnDestination.setVisible(source.isAvailable());
		returnDestination.setDateAvailable(DateUtil.formatDate(source.getDateAvailable()));
		returnDestination.setSku(source.getSku());
		ProductDescription description = null;
		if (source.getDescriptions() != null && source.getDescriptions().size() > 0) {
			for (ProductDescription desc : source.getDescriptions()) {
				if (language != null && desc.getLanguage() != null
						&& desc.getLanguage().getId().intValue() == language.getId().intValue()) {
					description = desc;
					break;
				} else {
					fulldescriptions.add(populateDescription(desc));
				}
			}
		}

/*		if (source.getProductReviewAvg() != null) {
			double avg = source.getProductReviewAvg().doubleValue();
			double rating = Math.round(avg * 2) / 2.0f;
			returnDestination.setRating(rating);
		}

		if (source.getProductReviewCount() != null) {
			returnDestination.setRatingCount(source.getProductReviewCount().intValue());
		}*/
		
		if (description != null) {
			com.salesmanager.shop.model.catalog.product.ProductDescription tragetDescription = populateDescription(
					description);
			returnDestination.setDescription(tragetDescription);

		}

		if (source.getManufacturer() != null) {
			ReadableManufacturer manufacturer = readableManufacturerMapper.convert(source.getManufacturer(), store,
					language);
			returnDestination.setManufacturer(manufacturer);
		}

		if (!CollectionUtils.isEmpty(source.getCategories())) {
			List<ReadableCategory> categoryList = new ArrayList<ReadableCategory>();
			for (Category category : source.getCategories()) {
				ReadableCategory readableCategory = readableCategoryMapper.convert(category, store, language);
				categoryList.add(readableCategory);

			}
			returnDestination.setCategories(categoryList);
		}
		
		
		ProductSpecification specifications = new ProductSpecification();
		specifications.setHeight(source.getProductHeight());
		specifications.setLength(source.getProductLength());
		specifications.setWeight(source.getProductWeight());
		specifications.setWidth(source.getProductWidth());
		if(!StringUtils.isBlank(store.getSeizeunitcode())) {
			specifications.setDimensionUnitOfMeasure(DimensionUnitOfMeasure.valueOf(store.getSeizeunitcode().toLowerCase()));
		}
		if(!StringUtils.isBlank(store.getWeightunitcode())) {
			specifications.setWeightUnitOfMeasure(WeightUnitOfMeasure.valueOf(store.getWeightunitcode().toLowerCase()));
		}
		returnDestination.setProductSpecifications(specifications);

		if (source.getType() != null) {
			ReadableProductType readableType = readableProductTypeMapper.convert(source.getType(), store, language);
			returnDestination.setType(readableType);
		}
		
		returnDestination.setSortOrder(source.getSortOrder());
		
		//images
		Set<ProductImage> images = source.getImages();
		if(CollectionUtils.isNotEmpty(images)) {

			List<ReadableImage> imageList = images.stream().map(i -> this.convertImage(source, i, store)).collect(Collectors.toList());
			returnDestination.setImages(imageList);
		}
		
		//quantity
		ProductAvailability availability = null;
		for(ProductAvailability a : source.getAvailabilities()) {
				availability = a;
				if(a.getProductVariant() != null) {
					continue;
				}	
		}
		
		if(availability != null) {
			returnDestination.setCanBePurchased(availability.getProductStatus());
			ReadableInventory inventory = readableInventoryMapper.convert(availability, store, language);
			returnDestination.setInventory(inventory);
		}
		

		if (returnDestination instanceof ReadableProductDefinitionFull) {
			((ReadableProductDefinitionFull) returnDestination).setDescriptions(fulldescriptions);
		}
		

		

		return returnDestination;
	}
	
	private ReadableImage convertImage(Product product, ProductImage image, MerchantStore store) {
		ReadableImage prdImage = new ReadableImage();
		prdImage.setImageName(image.getProductImage());
		prdImage.setDefaultImage(image.isDefaultImage());

		StringBuilder imgPath = new StringBuilder();
		imgPath.append(imageUtils.getContextPath()).append(imageUtils.buildProductImageUtils(store, product.getSku(), image.getProductImage()));

		prdImage.setImageUrl(imgPath.toString());
		prdImage.setId(image.getId());
		prdImage.setImageType(image.getImageType());
		if(image.getProductImageUrl()!=null){
			prdImage.setExternalUrl(image.getProductImageUrl());
		}
		if(image.getImageType()==1 && image.getProductImageUrl()!=null) {//video
			prdImage.setVideoUrl(image.getProductImageUrl());
		}
		
		if(prdImage.isDefaultImage()) {
			prdImage.setDefaultImage(true);
		}
		
		return prdImage;
	}

	private com.salesmanager.shop.model.catalog.product.ProductDescription populateDescription(ProductDescription description) {
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

}
