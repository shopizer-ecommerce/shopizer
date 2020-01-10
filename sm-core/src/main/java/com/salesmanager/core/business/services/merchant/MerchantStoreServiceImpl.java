package com.salesmanager.core.business.services.merchant;

import java.util.List;
import java.util.Optional;

import javax.inject.Inject;

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

	public MerchantStore getMerchantStore(String merchantStoreCode) throws ServiceException {
		return merchantRepository.findByCode(merchantStoreCode);
	}

	@Override
	public void saveOrUpdate(MerchantStore store) throws ServiceException {

		super.save(store);

	}

	@Override
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
		Pageable pageRequest = new PageRequest(page, count);
		return pageableMerchantRepository.listByStore(code, pageRequest);
	}

	@Override
	public Page<MerchantStore> listAll(Optional<String> storeName, int page, int count) throws ServiceException {
		String store = null;
		if (storeName != null && storeName.isPresent()) {
			store = storeName.get();
		}
		Pageable pageRequest = new PageRequest(page, count);
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
		Pageable pageRequest = new PageRequest(page, count);
		return pageableMerchantRepository.listAllRetailers(store, pageRequest);

	}

	@Override
	public List<MerchantStore> findAllStoreNames() throws ServiceException {
		return merchantRepository.findAllStoreNames();
	}

	/*	@Override
	public void delete(MerchantStore merchant) throws ServiceException {
		
		merchant = this.getById(merchant.getId());
		
		
		//reference
		List<Manufacturer> manufacturers = manufacturerService.listByStore(merchant);
		for(Manufacturer manufacturer : manufacturers) {
			manufacturerService.delete(manufacturer);
		}
		
		List<MerchantConfiguration> configurations = merchantConfigurationService.listByStore(merchant);
		for(MerchantConfiguration configuration : configurations) {
			merchantConfigurationService.delete(configuration);
		}
	}

	@Override
	public List<MerchantStore> findAllStoreNames() throws ServiceException {

		return merchantRepository.findAllStoreNames();
	}

	/*
	 * @Override public void delete(MerchantStore merchant) throws
	 * ServiceException {
	 * 
	 * merchant = this.getById(merchant.getId());
	 * 
	 * 
	 * //reference List<Manufacturer> manufacturers =
	 * manufacturerService.listByStore(merchant); for(Manufacturer manufacturer
	 * : manufacturers) { manufacturerService.delete(manufacturer); }
	 * 
	 * List<MerchantConfiguration> configurations =
	 * merchantConfigurationService.listByStore(merchant);
	 * for(MerchantConfiguration configuration : configurations) {
	 * merchantConfigurationService.delete(configuration); }
	 * 
	 * 
	 * //TODO taxService List<TaxClass> taxClasses =
	 * taxClassService.listByStore(merchant); for(TaxClass taxClass :
	 * taxClasses) { taxClassService.delete(taxClass); }
	 * 
	 * //content contentService.removeFiles(merchant.getCode()); //TODO
	 * staticContentService.removeImages
	 * 
	 * //category / product List<Category> categories =
	 * categoryService.listByStore(merchant); for(Category category :
	 * categories) { categoryService.delete(category); }
	 * 
	 * //users List<User> users = userService.listByStore(merchant); for(User
	 * user : users) { userService.delete(user); }
	 * 
	 * //customers List<Customer> customers =
	 * customerService.listByStore(merchant); for(Customer customer : customers)
	 * { customerService.delete(customer); }
	 * 
	 * //orders List<Order> orders = orderService.listByStore(merchant);
	 * for(Order order : orders) { orderService.delete(order); }
	 * 
	 * super.delete(merchant);
	 * 
	 * }
	 */

}
