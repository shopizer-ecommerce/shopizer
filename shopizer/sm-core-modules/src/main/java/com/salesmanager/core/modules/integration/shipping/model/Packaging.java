package com.salesmanager.core.modules.integration.shipping.model;

import java.util.List;

import com.salesmanager.core.business.generic.exception.ServiceException;
import com.salesmanager.core.business.merchant.model.MerchantStore;
import com.salesmanager.core.business.shipping.model.PackageDetails;
import com.salesmanager.core.business.shipping.model.ShippingProduct;

public interface Packaging {
	
	public List<PackageDetails> getBoxPackagesDetails(
			List<ShippingProduct> products, MerchantStore store) throws ServiceException;
	
	public List<PackageDetails> getItemPackagesDetails(
			List<ShippingProduct> products, MerchantStore store) throws ServiceException;

}
