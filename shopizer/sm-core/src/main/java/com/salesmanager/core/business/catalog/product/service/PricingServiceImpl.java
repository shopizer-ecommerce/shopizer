package com.salesmanager.core.business.catalog.product.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.salesmanager.core.business.catalog.product.model.Product;
import com.salesmanager.core.business.catalog.product.model.attribute.ProductAttribute;
import com.salesmanager.core.business.catalog.product.model.price.FinalPrice;
import com.salesmanager.core.business.customer.model.Customer;
import com.salesmanager.core.business.generic.exception.ServiceException;
import com.salesmanager.core.business.merchant.model.MerchantStore;
import com.salesmanager.core.business.reference.currency.model.Currency;
import com.salesmanager.core.utils.ProductPriceUtils;

/**
 * Contains all the logic required to calculate product price
 * @author Carl Samson
 *
 */
@Service("pricingService")
public class PricingServiceImpl implements PricingService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(PricingServiceImpl.class);
	

	@Autowired
	private ProductPriceUtils priceUtil;
	
	@Override
	public FinalPrice calculateProductPrice(Product product) throws ServiceException {
		return priceUtil.getFinalPrice(product);
	}
	
	@Override
	public FinalPrice calculateProductPrice(Product product, Customer customer) throws ServiceException {
		/** TODO add rules for price calculation **/
		return priceUtil.getFinalPrice(product);
	}
	
	@Override
	public FinalPrice calculateProductPrice(Product product, List<ProductAttribute> attributes) throws ServiceException {
		return priceUtil.getFinalProductPrice(product, attributes);
	}
	
	@Override
	public FinalPrice calculateProductPrice(Product product, List<ProductAttribute> attributes, Customer customer) throws ServiceException {
		/** TODO add rules for price calculation **/
		return priceUtil.getFinalProductPrice(product, attributes);
	}

	@Override
	public String getDisplayAmount(BigDecimal amount, MerchantStore store) throws ServiceException {
		try {
			String price= priceUtil.getStoreFormatedAmountWithCurrency(store,amount);
			return price;
		} catch (Exception e) {
			LOGGER.error("An error occured when trying to format an amount " + amount.toString());
			throw new ServiceException(e);
		}
	}
	
	@Override
	public String getDisplayAmount(BigDecimal amount, Locale locale,
			Currency currency, MerchantStore store) throws ServiceException {
		try {
			String price= priceUtil.getFormatedAmountWithCurrency(locale, currency, amount);
			return price;
		} catch (Exception e) {
			LOGGER.error("An error occured when trying to format an amunt " + amount.toString() + " using locale " + locale.toString() + " and currency " + currency.toString());
			throw new ServiceException(e);
		}
	}

	@Override
	public String getStringAmount(BigDecimal amount, MerchantStore store)
			throws ServiceException {
		try {
			String price = priceUtil.getAdminFormatedAmount(store, amount);
			return price;
		} catch (Exception e) {
			LOGGER.error("An error occured when trying to format an amount " + amount.toString());
			throw new ServiceException(e);
		}
	}


	
}
