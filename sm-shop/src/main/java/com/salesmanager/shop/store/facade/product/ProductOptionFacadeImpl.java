package com.salesmanager.shop.store.facade.product;

import java.io.InputStream;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.jsoup.helper.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.salesmanager.core.business.exception.ServiceException;
import com.salesmanager.core.business.services.catalog.product.attribute.ProductOptionService;
import com.salesmanager.core.business.services.catalog.product.attribute.ProductOptionValueService;
import com.salesmanager.core.business.services.content.ContentService;
import com.salesmanager.core.model.catalog.product.attribute.ProductOption;
import com.salesmanager.core.model.catalog.product.attribute.ProductOptionValue;
import com.salesmanager.core.model.content.InputContentFile;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.shop.mapper.catalog.PersistableProductOptionMapper;
import com.salesmanager.shop.mapper.catalog.PersistableProductOptionValueMapper;
import com.salesmanager.shop.mapper.catalog.ReadableProductOptionMapper;
import com.salesmanager.shop.mapper.catalog.ReadableProductOptionValueMapper;
import com.salesmanager.shop.model.catalog.product.attribute.ReadableProductOptionValue;
import com.salesmanager.shop.model.catalog.product.attribute.api.PersistableProductOptionEntity;
import com.salesmanager.shop.model.catalog.product.attribute.api.PersistableProductOptionValueEntity;
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
			throw new ServiceRuntimeException("An exception occured while deleting ProductOptionValue [" + optionValueId + "]",
					e);
		}

	}

	@Override
	public ReadableProductOptionValueList optionValues(MerchantStore store, Language language, String name, int page,
			int count) {
		Validate.notNull(store, "MerchantStore should not be null");

		Page<ProductOptionValue> options = productOptionValueService.getByMerchant(store, language, name, page, count);
		ReadableProductOptionValueList valueList = new ReadableProductOptionValueList();
		valueList.setTotalPages(options.getTotalPages());
		valueList.setRecordsTotal(options.getSize());

		List<ReadableProductOptionValueEntity> values = options.getContent().stream()
				.map(option -> readableOptionValueMapper.convert(option, store, language)).collect(Collectors.toList());

		valueList.setOptionValues(values);

		return valueList;
	}

	@Override
	public ReadableProductOptionList options(MerchantStore store, Language language, String name, int page, int count) {
		Validate.notNull(store, "MerchantStore should not be null");

		Page<ProductOption> options = productOptionService.getByMerchant(store, language, name, page, count);
		ReadableProductOptionList valueList = new ReadableProductOptionList();
		valueList.setTotalPages(options.getTotalPages());
		valueList.setRecordsTotal(options.getSize());

		List<ReadableProductOptionEntity> values = options.getContent().stream()
				.map(option -> readableMapper.convert(option, store, language)).collect(Collectors.toList());

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
		Validate.notNull(code,"Option code must not be null");
		Validate.notNull(store,"Store code must not be null");
		boolean exists = false;
		ProductOption option = productOptionService.getByCode(store, code);
		if(option != null) {
			exists = true;
		}
		return exists;
	}

	@Override
	public boolean optionValueExists(String code, MerchantStore store) {
		Validate.notNull(code,"Option value code must not be null");
		Validate.notNull(store,"Store code must not be null");
		boolean exists = false;
		ProductOptionValue optionValue = productOptionValueService.getByCode(store, code);
		if(optionValue != null) {
			exists = true;
		}
		return exists;
	}


	@Override
	public ReadableProductOptionValueEntity saveOptionValue(Optional<MultipartFile> image,
			PersistableProductOptionValueEntity optionValue, MerchantStore store, Language language) {
		Validate.notNull(optionValue,"Option value code must not be null");
		Validate.notNull(store,"Store code must not be null");
		
		
		ProductOptionValue value = new ProductOptionValue();
		if(optionValue.getId() != null && optionValue.getId().longValue() > 0) {
			value = productOptionValueService.getById(store, optionValue.getId());
			if(value == null) {
				throw new ResourceNotFoundException("ProductOptionValue [" + optionValue.getId() + "] does not exists for store [" + store.getCode() + "]");
			}
		}
		
		value = persistableOptionValueMapper.convert(optionValue, value, store, language);
		if(image.isPresent()) {
			try {
				String imageName = image.get().getOriginalFilename();
	            InputStream inputStream = image.get().getInputStream();
	            InputContentFile cmsContentImage = new InputContentFile();
	            cmsContentImage.setFileName(imageName);
	            cmsContentImage.setMimeType( image.get().getContentType() );
	            cmsContentImage.setFile( inputStream );
	            
				contentService.addOptionImage(store.getCode(), cmsContentImage);
				value.setProductOptionValueImage(imageName);
			} catch (Exception e) {
				throw new ServiceRuntimeException("Exception while saving option value image",e);
			}
            
           
		}
		
		try {
			productOptionValueService.saveOrUpdate(value);
		} catch (ServiceException e) {
			throw new ServiceRuntimeException("Exception while saving option value",e);
		}
		
		ProductOptionValue optValue = productOptionValueService.getById(store, value.getId());
		
		//convert to readable
		ReadableProductOptionValueEntity readableProductOptionValue = new ReadableProductOptionValueEntity();
		readableProductOptionValue = readableOptionValueMapper.convert(optValue, readableProductOptionValue, store, language);
		
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

}