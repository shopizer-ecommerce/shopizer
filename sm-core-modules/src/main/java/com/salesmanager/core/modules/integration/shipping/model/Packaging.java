package com.salesmanager.core.modules.integration.shipping.model;

import java.util.List;

import com.salesmanager.core.business.exception.ServiceException;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.shipping.PackageDetails;
import com.salesmanager.core.model.shipping.ShippingProduct;

public interface Packaging {
	
	public List<PackageDetails> getBoxPackagesDetails(
			List<ShippingProduct> products, MerchantStore store) throws ServiceException;
	
	public List<PackageDetails> getItemPackagesDetails(
			List<ShippingProduct> products, MerchantStore store) throws ServiceException;

}
