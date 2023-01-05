package com.salesmanager.shop.store.facade.product;

import static com.salesmanager.shop.util.ReadableEntityUtil.createReadableList;

import java.io.InputStream;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.jsoup.helper.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.salesmanager.core.business.constants.Constants;
import com.salesmanager.core.business.exception.ServiceException;
import com.salesmanager.core.business.services.catalog.product.variant.ProductVariantGroupService;
import com.salesmanager.core.business.services.catalog.product.variant.ProductVariantImageService;
import com.salesmanager.core.business.services.catalog.product.variant.ProductVariantService;
import com.salesmanager.core.business.services.content.ContentService;
import com.salesmanager.core.model.catalog.product.variant.ProductVariantImage;
import com.salesmanager.core.model.catalog.product.variant.ProductVariant;
import com.salesmanager.core.model.catalog.product.variant.ProductVariantGroup;
import com.salesmanager.core.model.content.FileContentType;
import com.salesmanager.core.model.content.InputContentFile;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.shop.mapper.catalog.product.PersistableProductVariantGroupMapper;
import com.salesmanager.shop.mapper.catalog.product.ReadableProductVariantGroupMapper;
import com.salesmanager.shop.model.catalog.product.product.variantGroup.PersistableProductVariantGroup;
import com.salesmanager.shop.model.catalog.product.product.variantGroup.ReadableProductVariantGroup;
import com.salesmanager.shop.model.entity.ReadableEntityList;
import com.salesmanager.shop.store.api.exception.ResourceNotFoundException;
import com.salesmanager.shop.store.api.exception.ServiceRuntimeException;
import com.salesmanager.shop.store.controller.product.facade.ProductVariantGroupFacade;


@Component
public class ProductVariantGroupFacadeImpl implements ProductVariantGroupFacade {
	
	@Autowired
	private ProductVariantGroupService productVariantGroupService;
	
	@Autowired
	private ProductVariantService productVariantService;
	
	@Autowired
	private ProductVariantImageService productVariantImageService;
	
	@Autowired
	private PersistableProductVariantGroupMapper persistableProductIntanceGroupMapper;
	
	@Autowired
	private ReadableProductVariantGroupMapper readableProductVariantGroupMapper;
	
	@Autowired
	private ContentService contentService; //file management

	@Override
	public ReadableProductVariantGroup get(Long instanceGroupId, MerchantStore store, Language language) {
		
		ProductVariantGroup group = this.group(instanceGroupId, store);
		return readableProductVariantGroupMapper.convert(group, store, language);
	}

	@Override
	public Long create(PersistableProductVariantGroup productVariantGroup, MerchantStore store, Language language) {
		
		ProductVariantGroup group = persistableProductIntanceGroupMapper.convert(productVariantGroup, store, language);
		try {
			group = productVariantGroupService.saveOrUpdate(group);
		} catch (ServiceException e) {
			throw new ServiceRuntimeException("Cannot save product instance group [" + productVariantGroup + "] for store [" + store.getCode() + "]"); 
		}
		
		return group.getId();
	}

	@Override
	public void update(Long productVariantGroup, PersistableProductVariantGroup instance, MerchantStore store,
			Language language) {
		ProductVariantGroup group = this.group(productVariantGroup, store);
		instance.setId(productVariantGroup);
		
		group = persistableProductIntanceGroupMapper.merge(instance, group, store, language);
		
		try {
			productVariantGroupService.saveOrUpdate(group);
		} catch (ServiceException e) {
			throw new ServiceRuntimeException("Cannot save product instance group [" + productVariantGroup + "] for store [" + store.getCode() + "]"); 
		}
		
	}

	@Override
	public void delete(Long productVariantGroup, Long productId, MerchantStore store) {

		ProductVariantGroup group = this.group(productVariantGroup, store);
		
		if(group == null) {
			throw new ResourceNotFoundException("Product instance group [" + group.getId() + " not found for store [" + store.getCode() + "]");
		}
		
		try {
		
			//null all group from instances
			for(ProductVariant instance : group.getProductVariants()) {
				Optional<ProductVariant> p = productVariantService.getById(instance.getId(), store);
				if(p.isEmpty()) {
					throw new ResourceNotFoundException("Product instance [" + instance.getId() + " not found for store [" + store.getCode() + "]");
				}
				instance.setProductVariantGroup(null);
				productVariantService.save(instance);
			}

			//now delete
			productVariantGroupService.delete(group);
		} catch (ServiceException e) {
			throw new ServiceRuntimeException("Cannot remove product instance group [" + productVariantGroup + "] for store [" + store.getCode() + "]");
		}

	}

	@Override
	public ReadableEntityList<ReadableProductVariantGroup> list(Long productId, MerchantStore store, Language language,
			int page, int count) {
		
		
		Page<ProductVariantGroup> groups = productVariantGroupService.getByProductId(store, productId, language, page, count);
		
		List<ReadableProductVariantGroup> readableInstances = groups.stream()
				.map(rp -> this.readableProductVariantGroupMapper.convert(rp, store, language)).collect(Collectors.toList());

	    return createReadableList(groups, readableInstances);

	}
	
	
	private ProductVariantGroup group(Long productOptionGroupId,MerchantStore store) {
		Optional<ProductVariantGroup> group = productVariantGroupService.getById(productOptionGroupId, store);
		if(group.isEmpty()) {
			throw new ResourceNotFoundException("Product instance group [" + productOptionGroupId + "] not found");
		}
		
		return group.get();
	}
	
	@Override
	public void addImage(MultipartFile image, Long instanceGroupId,
			MerchantStore store, Language language) {
		
		
		Validate.notNull(instanceGroupId,"productVariantGroupId must not be null");
		Validate.notNull(image,"Image must not be null");
		Validate.notNull(store,"MerchantStore must not be null");
		//get option group
		
		ProductVariantGroup group = this.group(instanceGroupId, store);
		ProductVariantImage instanceImage = new ProductVariantImage();
		
		try {
			
			String path = new StringBuilder().append("group").append(Constants.SLASH).append(instanceGroupId).toString();
			
			
			
			instanceImage.setProductImage(image.getOriginalFilename());
			instanceImage.setProductVariantGroup(group);
			String imageName = image.getOriginalFilename();
			InputStream inputStream = image.getInputStream();
			InputContentFile cmsContentImage = new InputContentFile();
			cmsContentImage.setFileName(imageName);
			cmsContentImage.setMimeType(image.getContentType());
			cmsContentImage.setFile(inputStream);
			cmsContentImage.setPath(path);
			cmsContentImage.setFileContentType(FileContentType.VARIANT);

			contentService.addContentFile(store.getCode(), cmsContentImage);

			group.getImages().add(instanceImage);
			
			productVariantGroupService.saveOrUpdate(group);
		} catch (Exception e) {
			throw new ServiceRuntimeException("Exception while adding instance group image", e);
		}


		return;
	}

	@Override
	public void removeImage(Long imageId, Long productVariantGroupId, MerchantStore store) {
		
		Validate.notNull(productVariantGroupId,"productVariantGroupId must not be null");
		Validate.notNull(store,"MerchantStore must not be null");
		
		ProductVariantImage image = productVariantImageService.getById(imageId);
		
		if(image == null) {
			throw new ResourceNotFoundException("productVariantImage [" + imageId + "] was not found");
		}
		
		ProductVariantGroup group = this.group(productVariantGroupId, store);

		
		try {
			contentService.removeFile(Constants.SLASH + store.getCode() + Constants.SLASH + productVariantGroupId, FileContentType.VARIANT, image.getProductImage());
			group.getImages().removeIf(i -> (i.getId() == image.getId()));
			//update productVariantroup
			productVariantGroupService.update(group);
		} catch (ServiceException e) {
			throw new ServiceRuntimeException("An exception occured while removing instance image [" + imageId + "]",e);
		}
			
	}

}
