package com.salesmanager.core.business.services.merchant;

import java.util.List;
import java.util.Optional;

import javax.inject.Inject;

import org.jsoup.helper.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.salesmanager.core.business.exception.ServiceException;
import com.salesmanager.core.business.repositories.merchant.MerchantRepository;
import com.salesmanager.core.business.repositories.merchant.PageableMerchantRepository;
import com.salesmanager.core.business.services.catalog.product.type.ProductTypeService;
import com.salesmanager.core.business.services.common.generic.SalesManagerEntityServiceImpl;
import com.salesmanager.core.model.common.GenericEntityList;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.merchant.MerchantStoreCriteria;

@Service("merchantService")
public class MerchantStoreServiceImpl extends SalesManagerEntityServiceImpl<Integer, MerchantStore>
		implements MerchantStoreService {

	@Inject
	protected ProductTypeService productTypeService;

	@Autowired
	private PageableMerchantRepository pageableMerchantRepository;

	private MerchantRepository merchantRepository;

	@Inject
	public MerchantStoreServiceImpl(MerchantRepository merchantRepository) {
		super(merchantRepository);
		this.merchantRepository = merchantRepository;
	}

	@Override
	//@CacheEvict(value="store", key="#store.code")
	public void saveOrUpdate(MerchantStore store) throws ServiceException {
		super.save(store);
	}

	@Override
	/**
	 * cache moved in facades
	 */
	//@Cacheable(value = "store")
	public MerchantStore getByCode(String code) throws ServiceException {
		return merchantRepository.findByCode(code);
	}

	@Override
	public boolean existByCode(String code) {
		return merchantRepository.existsByCode(code);
	}

	@Override
	public GenericEntityList<MerchantStore> getByCriteria(MerchantStoreCriteria criteria) throws ServiceException {
		return merchantRepository.listByCriteria(criteria);
	}

	@Override
	public Page<MerchantStore> listChildren(String code, int page, int count) throws ServiceException {
		Pageable pageRequest = PageRequest.of(page, count);
		return pageableMerchantRepository.listByStore(code, pageRequest);
	}

	@Override
	public Page<MerchantStore> listAll(Optional<String> storeName, int page, int count) throws ServiceException {
		String store = null;
		if (storeName != null && storeName.isPresent()) {
			store = storeName.get();
		}
		Pageable pageRequest = PageRequest.of(page, count);
		return pageableMerchantRepository.listAll(store, pageRequest);

	}

	@Override
	public List<MerchantStore> findAllStoreCodeNameEmail() throws ServiceException {
		return merchantRepository.findAllStoreCodeNameEmail();
	}

	@Override
	public Page<MerchantStore> listAllRetailers(Optional<String> storeName, int page, int count)
			throws ServiceException {
		String store = null;
		if (storeName != null && storeName.isPresent()) {
			store = storeName.get();
		}
		Pageable pageRequest = PageRequest.of(page, count);
		return pageableMerchantRepository.listAllRetailers(store, pageRequest);

	}

	@Override
	public List<MerchantStore> findAllStoreNames() throws ServiceException {
		return merchantRepository.findAllStoreNames();
	}

	@Override
	public MerchantStore getParent(String code) throws ServiceException {
		Validate.notNull(code, "MerchantStore code cannot be null");

		
		//get it
		MerchantStore storeModel = this.getByCode(code);
		
		if(storeModel == null) {
			throw new ServiceException("Store with code [" + code + "] is not found");
		}
		
		if(storeModel.isRetailer() != null && storeModel.isRetailer() && storeModel.getParent() == null) {
			return storeModel;
		}
		
		if(storeModel.getParent() == null) {
			return storeModel;
		}
	
		return merchantRepository.getById(storeModel.getParent().getId());
	}


	@Override
	public List<MerchantStore> findAllStoreNames(String code) throws ServiceException {
		return merchantRepository.findAllStoreNames(code);
	}

	/**
	 * Store might be alone (known as retailer)
	 * A retailer can have multiple child attached
	 * 
	 * This method from a store code is able to retrieve parent and childs.
	 * Method can also filter on storeName
	 */
	@Override
	public Page<MerchantStore> listByGroup(Optional<String> storeName, String code, int page, int count) throws ServiceException {
		
		String name = null;
		if (storeName != null && storeName.isPresent()) {
			name = storeName.get();
		}

		
		MerchantStore store = getByCode(code);//if exist
		Optional<Integer> id = Optional.ofNullable(store.getId());

		
		Pageable pageRequest = PageRequest.of(page, count);


		return pageableMerchantRepository.listByGroup(code, id.get(), name, pageRequest);
		
		
	}

	@Override
	public boolean isStoreInGroup(String code) throws ServiceException{
		
		MerchantStore store = getByCode(code);//if exist
		Optional<Integer> id = Optional.ofNullable(store.getId());
		
		List<MerchantStore> stores = merchantRepository.listByGroup(code, id.get());
		
		
		return stores.size() > 0;
	}


}
