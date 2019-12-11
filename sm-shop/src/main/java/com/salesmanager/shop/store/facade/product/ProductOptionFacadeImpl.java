package com.salesmanager.shop.store.facade.product;

import java.io.InputStream;
import java.util.List;
import java.util.stream.Collectors;

import org.jsoup.helper.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.salesmanager.core.business.exception.ServiceException;
import com.salesmanager.core.business.services.catalog.product.ProductService;
import com.salesmanager.core.business.services.catalog.product.attribute.ProductAttributeService;
import com.salesmanager.core.business.services.catalog.product.attribute.ProductOptionService;
import com.salesmanager.core.business.services.catalog.product.attribute.ProductOptionValueService;
import com.salesmanager.core.business.services.content.ContentService;
import com.salesmanager.core.model.catalog.product.Product;
import com.salesmanager.core.model.catalog.product.attribute.ProductAttribute;
import com.salesmanager.core.model.catalog.product.attribute.ProductOption;
import com.salesmanager.core.model.catalog.product.attribute.ProductOptionValue;
import com.salesmanager.core.model.content.FileContentType;
import com.salesmanager.core.model.content.InputContentFile;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.shop.mapper.catalog.PersistableProductAttributeMapper;
import com.salesmanager.shop.mapper.catalog.PersistableProductOptionMapper;
import com.salesmanager.shop.mapper.catalog.PersistableProductOptionValueMapper;
import com.salesmanager.shop.mapper.catalog.ReadableProductAttributeMapper;
import com.salesmanager.shop.mapper.catalog.ReadableProductOptionMapper;
import com.salesmanager.shop.mapper.catalog.ReadableProductOptionValueMapper;
import com.salesmanager.shop.model.catalog.product.attribute.PersistableProductAttribute;
import com.salesmanager.shop.model.catalog.product.attribute.api.PersistableProductOptionEntity;
import com.salesmanager.shop.model.catalog.product.attribute.api.PersistableProductOptionValueEntity;
import com.salesmanager.shop.model.catalog.product.attribute.api.ReadableProductAttributeEntity;
import com.salesmanager.shop.model.catalog.product.attribute.api.ReadableProductAttributeList;
import com.salesmanager.shop.model.catalog.product.attribute.api.ReadableProductOptionEntity;
import com.salesmanager.shop.model.catalog.product.attribute.api.ReadableProductOptionList;
import com.salesmanager.shop.model.catalog.product.attribute.api.ReadableProductOptionValueEntity;
import com.salesmanager.shop.model.catalog.product.attribute.api.ReadableProductOptionValueList;
import com.salesmanager.shop.store.api.exception.ResourceNotFoundException;
import com.salesmanager.shop.store.api.exception.ServiceRuntimeException;
import com.salesmanager.shop.store.controller.product.facade.ProductOptionFacade;

@Service
public class ProductOptionFacadeImpl implements ProductOptionFacade {

	@Autowired
	private ProductOptionService productOptionService;

	@Autowired
	private ProductOptionValueService productOptionValueService;

	@Autowired
	private ReadableProductOptionMapper readableMapper;

	@Autowired
	private PersistableProductOptionMapper persistableeMapper;

	@Autowired
	private PersistableProductOptionValueMapper persistableOptionValueMapper;

	@Autowired
	private ReadableProductOptionValueMapper readableOptionValueMapper;

	@Autowired
	private ContentService contentService;

	@Autowired
	private ProductAttributeService productAttributeService;

	@Autowired
	private PersistableProductAttributeMapper persistableProductAttributeMapper;

	@Autowired
	private ReadableProductAttributeMapper readableProductAttributeMapper;

	@Autowired
	private ProductService productService;

	@Override
	public ReadableProductOptionEntity saveOption(PersistableProductOptionEntity option, MerchantStore store,
			Language language) {
		Validate.notNull(option, "ProductOption cannot be null");
		Validate.notNull(store, "MerchantStore cannot be null");

		ProductOption optionModel = new ProductOption();
		if (option.getId() != null && option.getId().longValue() > 0) {
			optionModel = productOptionService.getById(store, option.getId());
			if (optionModel == null) {
				throw new ResourceNotFoundException(
						"ProductOption not found for if [" + option.getId() + "] and store [" + store.getCode() + "]");
			}
		}

		optionModel = persistableeMapper.convert(option, optionModel, store, language);
		try {
			productOptionService.saveOrUpdate(optionModel);
		} catch (ServiceException e) {
			throw new ServiceRuntimeException("An exception occured while saving ProductOption", e);
		}

		optionModel = productOptionService.getById(store, optionModel.getId());
		ReadableProductOptionEntity readable = readableMapper.convert(optionModel, store, language);
		return readable;

	}

	@Override
	public void deleteOption(Long optionId, MerchantStore store) {
		ProductOption optionModel = productOptionService.getById(store, optionId);
		if (optionModel == null) {
			throw new ResourceNotFoundException(
					"ProductOption not found for [" + optionId + "] and store [" + store.getCode() + "]");
		}
		try {
			productOptionService.delete(optionModel);
		} catch (ServiceException e) {
			throw new ServiceRuntimeException("An exception occured while deleting ProductOption [" + optionId + "]",
					e);
		}
	}

	@Override
	public void deleteOptionValue(Long optionValueId, MerchantStore store) {
		ProductOptionValue optionModel = productOptionValueService.getById(store, optionValueId);
		if (optionModel == null) {
			throw new ResourceNotFoundException(
					"ProductOptionValue not found for  [" + optionValueId + "] and store [" + store.getCode() + "]");
		}
		try {
			productOptionValueService.delete(optionModel);
		} catch (ServiceException e) {
			throw new ServiceRuntimeException(
					"An exception occured while deleting ProductOptionValue [" + optionValueId + "]", e);
		}

	}

	@Override
	public ReadableProductOptionValueList optionValues(MerchantStore store, Language language, String name, int page,
			int count) {
		Validate.notNull(store, "MerchantStore should not be null");

		Page<ProductOptionValue> options = productOptionValueService.getByMerchant(store, null, name, page, count);
		ReadableProductOptionValueList valueList = new ReadableProductOptionValueList();
		valueList.setTotalPages(options.getTotalPages());
		valueList.setRecordsTotal(options.getTotalElements());
		valueList.setNumber(options.getNumber());

		List<ReadableProductOptionValueEntity> values = options.getContent().stream()
				.map(option -> readableOptionValueMapper.convert(option, store, null)).collect(Collectors.toList());

		valueList.setOptionValues(values);

		return valueList;
	}

	@Override
	public ReadableProductOptionList options(MerchantStore store, Language language, String name, int page, int count) {
		Validate.notNull(store, "MerchantStore should not be null");

		Page<ProductOption> options = productOptionService.getByMerchant(store, null, name, page, count);
		ReadableProductOptionList valueList = new ReadableProductOptionList();
		valueList.setTotalPages(options.getTotalPages());
		valueList.setRecordsTotal(options.getTotalElements());
		valueList.setNumber(options.getNumber());

		List<ReadableProductOptionEntity> values = options.getContent().stream()
				.map(option -> readableMapper.convert(option, store, null)).collect(Collectors.toList());

		valueList.setOptions(values);

		return valueList;
	}

	@Override
	public ReadableProductOptionEntity getOption(Long optionId, MerchantStore store, Language language) {

		Validate.notNull(optionId, "Option id cannot be null");
		Validate.notNull(store, "Store cannot be null");

		ProductOption option = productOptionService.getById(store, optionId);

		if (option == null) {
			throw new ResourceNotFoundException("Option id [" + optionId + "] not found");
		}

		return readableMapper.convert(option, store, language);
	}

	@Override
	public boolean optionExists(String code, MerchantStore store) {
		Validate.notNull(code, "Option code must not be null");
		Validate.notNull(store, "Store code must not be null");
		boolean exists = false;
		ProductOption option = productOptionService.getByCode(store, code);
		if (option != null) {
			exists = true;
		}
		return exists;
	}

	@Override
	public boolean optionValueExists(String code, MerchantStore store) {
		Validate.notNull(code, "Option value code must not be null");
		Validate.notNull(store, "Store code must not be null");
		boolean exists = false;
		ProductOptionValue optionValue = productOptionValueService.getByCode(store, code);
		if (optionValue != null) {
			exists = true;
		}
		return exists;
	}

	@Override
	public ReadableProductOptionValueEntity saveOptionValue(PersistableProductOptionValueEntity optionValue,
			MerchantStore store, Language language) {
		Validate.notNull(optionValue, "Option value code must not be null");
		Validate.notNull(store, "Store code must not be null");

		ProductOptionValue value = new ProductOptionValue();
		if (optionValue.getId() != null && optionValue.getId().longValue() > 0) {
			value = productOptionValueService.getById(store, optionValue.getId());
			if (value == null) {
				throw new ResourceNotFoundException("ProductOptionValue [" + optionValue.getId()
						+ "] does not exists for store [" + store.getCode() + "]");
			}
		}

		value = persistableOptionValueMapper.convert(optionValue, value, store, language);


		try {
			productOptionValueService.saveOrUpdate(value);
		} catch (ServiceException e) {
			throw new ServiceRuntimeException("Exception while saving option value", e);
		}

		ProductOptionValue optValue = productOptionValueService.getById(store, value.getId());

		// convert to readable
		ReadableProductOptionValueEntity readableProductOptionValue = new ReadableProductOptionValueEntity();
		readableProductOptionValue = readableOptionValueMapper.convert(optValue, readableProductOptionValue, store,
				language);

		return readableProductOptionValue;
	}

	@Override
	public ReadableProductOptionValueEntity getOptionValue(Long optionValueId, MerchantStore store, Language language) {

		Validate.notNull(optionValueId, "OptionValue id cannot be null");
		Validate.notNull(store, "Store cannot be null");

		ProductOptionValue optionValue = productOptionValueService.getById(store, optionValueId);

		if (optionValue == null) {
			throw new ResourceNotFoundException("OptionValue id [" + optionValueId + "] not found");
		}

		return readableOptionValueMapper.convert(optionValue, store, language);
	}

	@Override
	public ReadableProductAttributeEntity saveAttribute(Long productId, PersistableProductAttribute attribute,
			MerchantStore store, Language language) {
		Validate.notNull(productId, "Product id cannot be null");
		Validate.notNull(attribute, "ProductAttribute cannot be null");
		Validate.notNull(store, "Store cannot be null");

		attribute.setProductId(productId);
		ProductAttribute attr = new ProductAttribute();
		if (attribute.getId() != null && attribute.getId().longValue() > 0) {
			attr = productAttributeService.getById(attribute.getId());
			if (attr == null) {
				throw new ResourceNotFoundException("Product attribute [" + attribute.getId() + "] not found");
			}

			if (productId != attr.getProduct().getId().longValue()) {
				throw new ResourceNotFoundException(
						"Product attribute [" + attribute.getId() + "] not found for product [" + productId + "]");
			}
		}

		attr = persistableProductAttributeMapper.convert(attribute, attr, store, language);

		try {
			productAttributeService.saveOrUpdate(attr);
		} catch (ServiceException e) {
			throw new ServiceRuntimeException("Exception while saving ProductAttribute", e);
		}

		// refresh
		attr = productAttributeService.getById(attr.getId());
		ReadableProductAttributeEntity readable = readableProductAttributeMapper.convert(attr, store, language);

		return readable;
	}

	@Override
	public ReadableProductAttributeEntity getAttribute(Long productId, Long attributeId, MerchantStore store,
			Language language) {

		ProductAttribute attr = productAttributeService.getById(attributeId);

		if (attr == null) {
			throw new ResourceNotFoundException(
					"ProductAttribute not found for [" + attributeId + "] and store [" + store.getCode() + "]");
		}

		if (attr.getProduct().getId().longValue() != productId) {
			throw new ResourceNotFoundException(
					"ProductAttribute not found for [" + attributeId + "] and product [" + productId + "]");
		}

		if (attr.getProduct().getMerchantStore().getId().intValue() != store.getId().intValue()) {
			throw new ResourceNotFoundException("ProductAttribute not found for [" + attributeId + "] and product ["
					+ productId + "] and store [" + store.getCode() + "]");
		}

		ReadableProductAttributeEntity readable = readableProductAttributeMapper.convert(attr, store, language);

		return readable;
	}

	@Override
	public ReadableProductAttributeList getAttributesList(Long productId, MerchantStore store, Language language) {

		try {

			Product product = productService.getById(productId);

			if (product == null) {
				throw new ResourceNotFoundException("Productnot found for id [" + productId + "]");
			}

			if (product.getMerchantStore().getId().intValue() != store.getId().intValue()) {
				throw new ResourceNotFoundException(
						"Productnot found id [" + productId + "] for store [" + store.getCode() + "]");
			}

			List<ProductAttribute> attributes = productAttributeService.getByProductId(store, product, language);
			ReadableProductAttributeList attrList = new ReadableProductAttributeList();
			attrList.setRecordsTotal(attributes.size());
			attrList.setNumber(attributes.size());

			List<ReadableProductAttributeEntity> values = attributes.stream()
					.map(attribute -> readableProductAttributeMapper.convert(attribute, store, language))
					.collect(Collectors.toList());

			attrList.setAttributes(values);

			return attrList;

		} catch (ServiceException e) {
			throw new ServiceRuntimeException("Error while getting attributes", e);
		}

	}

	@Override
	public void deleteAttribute(Long productId, Long attributeId, MerchantStore store) {
		try {

			ProductAttribute attr = productAttributeService.getById(attributeId);
			if (attr == null) {
				throw new ResourceNotFoundException(
						"ProductAttribute not found for [" + attributeId + "] and store [" + store.getCode() + "]");
			}

			if (attr.getProduct().getId().longValue() != productId) {
				throw new ResourceNotFoundException(
						"ProductAttribute not found for [" + attributeId + "] and product [" + productId + "]");
			}

			if (attr.getProduct().getMerchantStore().getId().intValue() != store.getId().intValue()) {
				throw new ResourceNotFoundException("ProductAttribute not found for [" + attributeId + "] and product ["
						+ productId + "] and store [" + store.getCode() + "]");
			}

			productAttributeService.delete(attr);

		} catch (ServiceException e) {
			throw new ServiceRuntimeException(
					"An exception occured while deleting ProductAttribute [" + attributeId + "]", e);
		}

	}



	@Override
	public void addOptionValueImage(MultipartFile image, Long optionValueId,
			MerchantStore store, Language language) {
		
		
		Validate.notNull(optionValueId,"OptionValueId must not be null");
		Validate.notNull(image,"Image must not be null");
		//get option value
		ProductOptionValue value = productOptionValueService.getById(store, optionValueId);
		if(value == null) {
			throw new ResourceNotFoundException("Product option value [" + optionValueId + "] not found");
		}
		
		try {
			String imageName = image.getOriginalFilename();
			InputStream inputStream = image.getInputStream();
			InputContentFile cmsContentImage = new InputContentFile();
			cmsContentImage.setFileName(imageName);
			cmsContentImage.setMimeType(image.getContentType());
			cmsContentImage.setFile(inputStream);

			contentService.addOptionImage(store.getCode(), cmsContentImage);
			value.setProductOptionValueImage(imageName);
			productOptionValueService.saveOrUpdate(value);
		} catch (Exception e) {
			throw new ServiceRuntimeException("Exception while adding option value image", e);
		}


		
		
		return;
	}

	@Override
	public void removeOptionValueImage(Long optionValueId, MerchantStore store,
			Language language) {
		Validate.notNull(optionValueId,"OptionValueId must not be null");
		ProductOptionValue value = productOptionValueService.getById(store, optionValueId);
		if(value == null) {
			throw new ResourceNotFoundException("Product option value [" + optionValueId + "] not found");
		}
		
		try {

			contentService.removeFile(store.getCode(), FileContentType.PROPERTY, value.getProductOptionValueImage());
			value.setProductOptionValueImage(null);
			productOptionValueService.saveOrUpdate(value);
		} catch (Exception e) {
			throw new ServiceRuntimeException("Exception while removing option value image", e);
		}


		
		
		return;
	}

}