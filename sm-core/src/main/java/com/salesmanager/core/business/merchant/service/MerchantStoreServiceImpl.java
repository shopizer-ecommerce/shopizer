package com.salesmanager.core.business.merchant.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.salesmanager.core.business.catalog.category.model.Category;
import com.salesmanager.core.business.catalog.category.service.CategoryService;
import com.salesmanager.core.business.catalog.product.model.manufacturer.Manufacturer;
import com.salesmanager.core.business.catalog.product.service.manufacturer.ManufacturerService;
import com.salesmanager.core.business.catalog.product.service.type.ProductTypeService;
import com.salesmanager.core.business.content.service.ContentService;
import com.salesmanager.core.business.customer.model.Customer;
import com.salesmanager.core.business.customer.service.CustomerService;
import com.salesmanager.core.business.generic.exception.ServiceException;
import com.salesmanager.core.business.generic.service.SalesManagerEntityServiceImpl;
import com.salesmanager.core.business.merchant.dao.MerchantStoreDao;
import com.salesmanager.core.business.merchant.model.MerchantStore;
import com.salesmanager.core.business.order.model.Order;
import com.salesmanager.core.business.order.service.OrderService;
import com.salesmanager.core.business.system.model.MerchantConfiguration;
import com.salesmanager.core.business.system.service.MerchantConfigurationService;
import com.salesmanager.core.business.tax.model.taxclass.TaxClass;
import com.salesmanager.core.business.tax.service.TaxClassService;
import com.salesmanager.core.business.user.model.User;
import com.salesmanager.core.business.user.service.UserService;

@Service("merchantService")
public class MerchantStoreServiceImpl extends SalesManagerEntityServiceImpl<Integer, MerchantStore> 
		implements MerchantStoreService {
	

		
	@Autowired
	protected ProductTypeService productTypeService;
	
	@Autowired
	private TaxClassService taxClassService;
	
	@Autowired
	private ContentService contentService;
	
	@Autowired
	private MerchantConfigurationService merchantConfigurationService;
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private ManufacturerService manufacturerService;
	
	private MerchantStoreDao merchantStoreDao;
	
	@Autowired
	public MerchantStoreServiceImpl(MerchantStoreDao merchantStoreDao) {
		super(merchantStoreDao);
		this.merchantStoreDao = merchantStoreDao;
	}

	
	//@Override
	public MerchantStore getMerchantStore(String merchantStoreCode) throws ServiceException {
		return merchantStoreDao.getMerchantStore(merchantStoreCode);
	}
	
	@Override
	public void saveOrUpdate(MerchantStore store) throws ServiceException {
		
		if(store.getId()==null) {
			super.save(store);
		} else {
			super.update(store);
		}
	}
	

	
	//@Override
	//public Collection<Product> getProducts(MerchantStore merchantStore) throws ServiceException {
		

	//	return merchantStoreDao.getProducts(merchantStore);
		
		
	//}

	@Override
	public MerchantStore getByCode(String code) throws ServiceException {
		
		return merchantStoreDao.getMerchantStore(code);
	}
	
	@Override
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
		

		//TODO taxService
		List<TaxClass> taxClasses = taxClassService.listByStore(merchant);
		for(TaxClass taxClass : taxClasses) {
			taxClassService.delete(taxClass);
		}
		
		//content
		contentService.removeFiles(merchant.getCode());
		//TODO staticContentService.removeImages
		
		//category / product
		List<Category> categories = categoryService.listByStore(merchant);
		for(Category category : categories) {
			categoryService.delete(category);
		}

		//users
		List<User> users = userService.listByStore(merchant);
		for(User user : users) {
			userService.delete(user);
		}
		
		//customers
		List<Customer> customers = customerService.listByStore(merchant);
		for(Customer customer : customers) {
			customerService.delete(customer);
		}
		
		//orders
		List<Order> orders = orderService.listByStore(merchant);
		for(Order order : orders) {
			orderService.delete(order);
		}
		
		super.delete(merchant);
		
	}

}
