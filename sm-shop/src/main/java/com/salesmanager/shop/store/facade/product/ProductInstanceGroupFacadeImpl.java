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
import com.salesmanager.core.business.services.catalog.product.instance.ProductInstanceGroupService;
import com.salesmanager.core.business.services.catalog.product.instance.ProductInstanceImageService;
import com.salesmanager.core.business.services.catalog.product.instance.ProductInstanceService;
import com.salesmanager.core.business.services.content.ContentService;
import com.salesmanager.core.model.catalog.product.instance.ProductInstance;
import com.salesmanager.core.model.catalog.product.instance.ProductInstanceGroup;
import com.salesmanager.core.model.catalog.product.instance.ProductInstanceImage;
import com.salesmanager.core.model.content.FileContentType;
import com.salesmanager.core.model.content.InputContentFile;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.shop.mapper.catalog.product.PersistableProductIntanceGroupMapper;
import com.salesmanager.shop.mapper.catalog.product.ReadableProductInstanceGroupMapper;
import com.salesmanager.shop.model.catalog.product.product.instanceGroup.PersistableProductInstanceGroup;
import com.salesmanager.shop.model.catalog.product.product.instanceGroup.ReadableProductInstanceGroup;
import com.salesmanager.shop.model.entity.ReadableEntityList;
import com.salesmanager.shop.store.api.exception.ResourceNotFoundException;
import com.salesmanager.shop.store.api.exception.ServiceRuntimeException;
import com.salesmanager.shop.store.controller.product.facade.ProductInstanceGroupFacade;


@Component
public class ProductInstanceGroupFacadeImpl implements ProductInstanceGroupFacade {
	
	@Autowired
	private ProductInstanceGroupService productInstanceGroupService;
	
	@Autowired
	private ProductInstanceService productInstanceService;
	
	@Autowired
	private ProductInstanceImageService productInstanceImageService;
	
	@Autowired
	private PersistableProductIntanceGroupMapper persistableProductIntanceGroupMapper;
	
	@Autowired
	private ReadableProductInstanceGroupMapper readableProductInstanceGroupMapper;
	
	@Autowired
	private ContentService contentService; //file management

	@Override
	public ReadableProductInstanceGroup get(Long instanceGroupId, MerchantStore store, Language language) {
		
		ProductInstanceGroup group = this.group(instanceGroupId, store);
		return readableProductInstanceGroupMapper.convert(group, store, language);
	}

	@Override
	public Long create(PersistableProductInstanceGroup productInstanceGroup, MerchantStore store, Language language) {
		
		ProductInstanceGroup group = persistableProductIntanceGroupMapper.convert(productInstanceGroup, store, language);
		try {
			productInstanceGroupService.saveOrUpdate(group);
		} catch (ServiceException e) {
			throw new ServiceRuntimeException("Cannot save product instance group [" + productInstanceGroup + "] for store [" + store.getCode() + "]"); 
		}
		
		return group.getId();
	}

	@Override
	public void update(Long productInstanceGroup, PersistableProductInstanceGroup instance, MerchantStore store,
			Language language) {
		ProductInstanceGroup group = this.group(productInstanceGroup, store);
		instance.setId(productInstanceGroup);
		
		group = persistableProductIntanceGroupMapper.merge(instance, group, store, language);
		
		try {
			productInstanceGroupService.saveOrUpdate(group);
		} catch (ServiceException e) {
			throw new ServiceRuntimeException("Cannot save product instance group [" + productInstanceGroup + "] for store [" + store.getCode() + "]"); 
		}
		
	}

	@Override
	public void delete(Long productInstanceGroup, Long productId, MerchantStore store) {

		ProductInstanceGroup group = this.group(productInstanceGroup, store);
		
		if(group == null) {
			throw new ResourceNotFoundException("Product instance group [" + group.getId() + " not found for store [" + store.getCode() + "]");
		}
		
		try {
		
			//null all group from instances
			for(ProductInstance instance : group.getProductInstances()) {
				Optional<ProductInstance> p = productInstanceService.getById(instance.getId(), store);
				if(p.isEmpty()) {
					throw new ResourceNotFoundException("Product instance [" + instance.getId() + " not found for store [" + store.getCode() + "]");
				}
				instance.setProductInstanceGroup(null);
				productInstanceService.save(instance);
			}

			//now delete
			productInstanceGroupService.delete(group);
		} catch (ServiceException e) {
			throw new ServiceRuntimeException("Cannot remove product instance group [" + productInstanceGroup + "] for store [" + store.getCode() + "]");
		}

	}

	@Override
	public ReadableEntityList<ReadableProductInstanceGroup> list(Long productId, MerchantStore store, Language language,
			int page, int count) {
		
		
		Page<ProductInstanceGroup> groups = productInstanceGroupService.getByProductId(store, productId, language, page, count);
		
		List<ReadableProductInstanceGroup> readableInstances = groups.stream()
				.map(rp -> this.readableProductInstanceGroupMapper.convert(rp, store, language)).collect(Collectors.toList());

	    return createReadableList(groups, readableInstances);

	}
	
	
	private ProductInstanceGroup group(Long productOptionGroupId,MerchantStore store) {
		Optional<ProductInstanceGroup> group = productInstanceGroupService.getById(productOptionGroupId, store);
		if(group.isEmpty()) {
			throw new ResourceNotFoundException("Product instance group [" + productOptionGroupId + "] not found");
		}
		
		return group.get();
	}
	
	@Override
	public void addImage(MultipartFile image, Long instanceGroupId,
			MerchantStore store, Language language) {
		
		
		Validate.notNull(instanceGroupId,"productInstanceGroupId must not be null");
		Validate.notNull(image,"Image must not be null");
		Validate.notNull(store,"MerchantStore must not be null");
		//get option group
		
		ProductInstanceGroup group = this.group(instanceGroupId, store);
		ProductInstanceImage instanceImage = new ProductInstanceImage();
		
		try {
			
			String path = new StringBuilder().append("group").append(Constants.SLASH).append(instanceGroupId).toString();
			
			
			
			instanceImage.setProductImage(image.getOriginalFilename());
			instanceImage.setProductInstanceGroup(group);
			String imageName = image.getOriginalFilename();
			InputStream inputStream = image.getInputStream();
			InputContentFile cmsContentImage = new InputContentFile();
			cmsContentImage.setFileName(imageName);
			cmsContentImage.setMimeType(image.getContentType());
			cmsContentImage.setFile(inputStream);
			cmsContentImage.setPath(path);
			cmsContentImage.setFileContentType(FileContentType.INSTANCE);

			contentService.addContentFile(store.getCode(), cmsContentImage);

			group.getImages().add(instanceImage);
			
			productInstanceGroupService.saveOrUpdate(group);
		} catch (Exception e) {
			throw new ServiceRuntimeException("Exception while adding instance group image", e);
		}


		return;
	}

	@Override
	public void removeImage(Long imageId, Long productInstanceGroupId, MerchantStore store) {
		
		Validate.notNull(productInstanceGroupId,"productInstanceGroupId must not be null");
		Validate.notNull(store,"MerchantStore must not be null");
		
		ProductInstanceImage image = productInstanceImageService.getById(imageId);
		
		if(image == null) {
			throw new ResourceNotFoundException("ProductInstanceImage [" + imageId + "] was not found");
		}
		
		ProductInstanceGroup group = this.group(productInstanceGroupId, store);

		
		try {
			contentService.removeFile(Constants.SLASH + store.getCode() + Constants.SLASH + productInstanceGroupId, FileContentType.INSTANCE, image.getProductImage());
			group.getImages().removeIf(i -> (i.getId() == image.getId()));
			//update productinstanceroup
			productInstanceGroupService.update(group);
		} catch (ServiceException e) {
			throw new ServiceRuntimeException("An exception occured while removing instance image [" + imageId + "]",e);
		}
			
	}

}
