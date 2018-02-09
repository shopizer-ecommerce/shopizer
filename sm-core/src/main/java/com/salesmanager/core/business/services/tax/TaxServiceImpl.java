package com.salesmanager.core.business.services.tax;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.salesmanager.core.business.exception.ServiceException;
import com.salesmanager.core.business.services.system.MerchantConfigurationService;
import com.salesmanager.core.model.common.Billing;
import com.salesmanager.core.model.common.Delivery;
import com.salesmanager.core.model.customer.Customer;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.order.OrderSummary;
import com.salesmanager.core.model.reference.country.Country;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.core.model.reference.zone.Zone;
import com.salesmanager.core.model.shipping.ShippingSummary;
import com.salesmanager.core.model.shoppingcart.ShoppingCartItem;
import com.salesmanager.core.model.system.MerchantConfiguration;
import com.salesmanager.core.model.tax.TaxBasisCalculation;
import com.salesmanager.core.model.tax.TaxConfiguration;
import com.salesmanager.core.model.tax.TaxItem;
import com.salesmanager.core.model.tax.taxclass.TaxClass;
import com.salesmanager.core.model.tax.taxrate.TaxRate;

@Service("taxService")
public class TaxServiceImpl 
		implements TaxService {
	
	private final static String TAX_CONFIGURATION = "TAX_CONFIG";
	private final static String DEFAULT_TAX_CLASS = "DEFAULT";
	
	@Inject
	private MerchantConfigurationService merchantConfigurationService;
	
	@Inject
	private TaxRateService taxRateService;
	
	@Inject
	private TaxClassService taxClassService;
	
	@Override
	public TaxConfiguration getTaxConfiguration(MerchantStore store) throws ServiceException {
		
		
		
		MerchantConfiguration configuration = merchantConfigurationService.getMerchantConfiguration(TAX_CONFIGURATION, store);
		TaxConfiguration taxConfiguration = null;
		if(configuration!=null) {
			String value = configuration.getValue();
			
			ObjectMapper mapper = new ObjectMapper();
			try {
				taxConfiguration = mapper.readValue(value, TaxConfiguration.class);
			} catch(Exception e) {
				throw new ServiceException("Cannot parse json string " + value);
			}
		}
		return taxConfiguration;
	}
	
	
	@Override
	public void saveTaxConfiguration(TaxConfiguration shippingConfiguration, MerchantStore store) throws ServiceException {
		
		MerchantConfiguration configuration = merchantConfigurationService.getMerchantConfiguration(TAX_CONFIGURATION, store);

		if(configuration==null) {
			configuration = new MerchantConfiguration();
			configuration.setMerchantStore(store);
			configuration.setKey(TAX_CONFIGURATION);
		}
		
		String value = shippingConfiguration.toJSONString();
		configuration.setValue(value);
		merchantConfigurationService.saveOrUpdate(configuration);
		
	}
	
	@Override
	public List<TaxItem> calculateTax(OrderSummary orderSummary, Customer customer, MerchantStore store, Language language) throws ServiceException {
		

		if(customer==null) {
			return null;
		}

		List<ShoppingCartItem> items = orderSummary.getProducts();
		
		List<TaxItem> taxLines = new ArrayList<TaxItem>();
		
		if(items==null) {
			return taxLines;
		}
		
		//determine tax calculation basis
		TaxConfiguration taxConfiguration = this.getTaxConfiguration(store);
		if(taxConfiguration==null) {
			taxConfiguration = new TaxConfiguration();
			taxConfiguration.setTaxBasisCalculation(TaxBasisCalculation.SHIPPINGADDRESS);
		}
		
		Country country = customer.getBilling().getCountry();
		Zone zone = customer.getBilling().getZone();
		String stateProvince = customer.getBilling().getState();
		
		TaxBasisCalculation taxBasisCalculation = taxConfiguration.getTaxBasisCalculation();
		if(taxBasisCalculation.name().equals(TaxBasisCalculation.SHIPPINGADDRESS)){
			Delivery shipping = customer.getDelivery();
			if(shipping!=null) {
				country = shipping.getCountry();
				zone = shipping.getZone();
				stateProvince = shipping.getState();
			}
		} else if(taxBasisCalculation.name().equals(TaxBasisCalculation.BILLINGADDRESS)){
			Billing billing = customer.getBilling();
			if(billing!=null) {
				country = billing.getCountry();
				zone = billing.getZone();
				stateProvince = billing.getState();
			}
		} else if(taxBasisCalculation.name().equals(TaxBasisCalculation.STOREADDRESS)){
			country = store.getCountry();
			zone = store.getZone();
			stateProvince = store.getStorestateprovince();
		}
		
		//check other conditions
		//do not collect tax on other provinces of same country
		if(!taxConfiguration.isCollectTaxIfDifferentProvinceOfStoreCountry()) {
			if((zone!=null && store.getZone()!=null) && (zone.getId().longValue() != store.getZone().getId().longValue())) {
				return null;
			}
			if(!StringUtils.isBlank(stateProvince)) {
				if(store.getZone()!=null) {
					if(!store.getZone().getName().equals(stateProvince)) {
						return null;
					}
				}
				else if(!StringUtils.isBlank(store.getStorestateprovince())) {

					if(!store.getStorestateprovince().equals(stateProvince)) {
						return null;
					}
				}
			}
		}
		
		//collect tax in different countries
		if(taxConfiguration.isCollectTaxIfDifferentCountryOfStoreCountry()) {
			//use store country
			country = store.getCountry();
			zone = store.getZone();
			stateProvince = store.getStorestateprovince();
		}
		
		if(zone == null && StringUtils.isBlank(stateProvince)) {
			return null;
		}
		
		Map<Long,TaxClass> taxClasses =  new HashMap<Long,TaxClass>();
			
		//put items in a map by tax class id
		Map<Long,BigDecimal> taxClassAmountMap = new HashMap<Long,BigDecimal>();
		for(ShoppingCartItem item : items) {
				
				BigDecimal itemPrice = item.getItemPrice();
				TaxClass taxClass = item.getProduct().getTaxClass();
				int quantity = item.getQuantity();
				itemPrice = itemPrice.multiply(new BigDecimal(quantity));
				if(taxClass==null) {
					taxClass = taxClassService.getByCode(DEFAULT_TAX_CLASS);
				}
				BigDecimal subTotal = taxClassAmountMap.get(taxClass.getId());
				if(subTotal==null) {
					subTotal = new BigDecimal(0);
					subTotal.setScale(2, RoundingMode.HALF_UP);
				}
					
				subTotal = subTotal.add(itemPrice);
				taxClassAmountMap.put(taxClass.getId(), subTotal);
				taxClasses.put(taxClass.getId(), taxClass);
				
		}
		
		//tax on shipping ?
		//ShippingConfiguration shippingConfiguration = shippingService.getShippingConfiguration(store);	
		
		/** always calculate tax on shipping **/
		//if(shippingConfiguration!=null) {
			//if(shippingConfiguration.isTaxOnShipping()){
				//use default tax class for shipping
				TaxClass defaultTaxClass = taxClassService.getByCode(TaxClass.DEFAULT_TAX_CLASS);
				//taxClasses.put(defaultTaxClass.getId(), defaultTaxClass);
				BigDecimal amnt = taxClassAmountMap.get(defaultTaxClass.getId());
				if(amnt==null) {
					amnt = new BigDecimal(0);
					amnt.setScale(2, RoundingMode.HALF_UP);
				}
				ShippingSummary shippingSummary = orderSummary.getShippingSummary();
				if(shippingSummary!=null && shippingSummary.getShipping()!=null && shippingSummary.getShipping().doubleValue()>0) {
					amnt = amnt.add(shippingSummary.getShipping());
					if(shippingSummary.getHandling()!=null && shippingSummary.getHandling().doubleValue()>0) {
						amnt = amnt.add(shippingSummary.getHandling());
					}
				}
				taxClassAmountMap.put(defaultTaxClass.getId(), amnt);
			//}
		//}
		
		
		List<TaxItem> taxItems = new ArrayList<TaxItem>();
		
		//iterate through the tax class and get appropriate rates
		for(Long taxClassId : taxClassAmountMap.keySet()) {
			
			//get taxRate by tax class
			List<TaxRate> taxRates = null; 
			if(!StringUtils.isBlank(stateProvince)&& zone==null) {
				taxRates = taxRateService.listByCountryStateProvinceAndTaxClass(country, stateProvince, taxClasses.get(taxClassId), store, language);
			} else {
				taxRates = taxRateService.listByCountryZoneAndTaxClass(country, zone, taxClasses.get(taxClassId), store, language);
			}
			
			if(taxRates==null || taxRates.size()==0){
				continue;
			}
			BigDecimal taxedItemValue = null;
			BigDecimal totalTaxedItemValue = new BigDecimal(0);
			totalTaxedItemValue.setScale(2, RoundingMode.HALF_UP);
			BigDecimal beforeTaxeAmount = taxClassAmountMap.get(taxClassId);
			for(TaxRate taxRate : taxRates) {
				
				double taxRateDouble = taxRate.getTaxRate().doubleValue();//5% ... 8% ...
				

				if(taxRate.isPiggyback()) {//(compound)
					if(totalTaxedItemValue.doubleValue()>0) {
						beforeTaxeAmount = totalTaxedItemValue;
					}
				} //else just use nominal taxing (combine)
				
				double value  = (beforeTaxeAmount.doubleValue() * taxRateDouble)/100;
				double roundedValue = new BigDecimal(value).setScale(2, RoundingMode.HALF_UP).doubleValue();
				taxedItemValue = new BigDecimal(roundedValue).setScale(2, RoundingMode.HALF_UP);
				totalTaxedItemValue = beforeTaxeAmount.add(taxedItemValue);
				
				TaxItem taxItem = new TaxItem();
				taxItem.setItemPrice(taxedItemValue);
				taxItem.setLabel(taxRate.getDescriptions().get(0).getName());
				taxItem.setTaxRate(taxRate);
				taxItems.add(taxItem);
				
			}
			
		}
		
		
		
		Map<String,TaxItem> taxItemsMap = new TreeMap<String,TaxItem>();
		//consolidate tax rates of same code
		for(TaxItem taxItem : taxItems) {
			
			TaxRate taxRate = taxItem.getTaxRate();
			if(!taxItemsMap.containsKey(taxRate.getCode())) {
				taxItemsMap.put(taxRate.getCode(), taxItem);
			} 
			
			TaxItem item = taxItemsMap.get(taxRate.getCode());
			BigDecimal amount = item.getItemPrice();
			amount = amount.add(taxItem.getItemPrice());			
			
		}
		
		if(taxItemsMap.size()==0) {
			return null;
		}
			
			
		@SuppressWarnings("rawtypes")
		Collection<TaxItem> values = taxItemsMap.values();
		
		
		@SuppressWarnings("unchecked")
		List<TaxItem> list = new ArrayList<TaxItem>(values);
		return list;

	}


}
