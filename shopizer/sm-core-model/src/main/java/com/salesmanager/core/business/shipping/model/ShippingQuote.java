package com.salesmanager.core.business.shipping.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public class ShippingQuote implements Serializable {
	
	
	
	



	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public final static String NO_SHIPPING_TO_SELECTED_COUNTRY = "NO_SHIPPING_TO_SELECTED_COUNTRY";
	public final static String NO_SHIPPING_MODULE_CONFIGURED= "NO_SHIPPING_MODULE_CONFIGURED";
	public final static String ERROR= "ERROR";

	private String shippingModuleCode;
	private List<ShippingOption> shippingOptions = null;
	/** if an error occurs, this field will be populated from constants defined above **/
	private String shippingReturnCode = null;
	/** indicates if this quote is configured with free shipping **/
	private boolean freeShipping;
	/** the threshold amount for being free shipping **/
	private BigDecimal freeShippingAmount;
	/** handling fees to be added on top of shipping fees **/
	private BigDecimal handlingFees;
	/** apply tax on shipping **/
	private boolean applyTaxOnShipping;
	
	private ShippingOption selectedShippingOption = null;
	
	private String quoteError = null;
	
	
	
	public void setShippingOptions(List<ShippingOption> shippingOptions) {
		this.shippingOptions = shippingOptions;
	}
	public List<ShippingOption> getShippingOptions() {
		return shippingOptions;
	}
	public void setShippingModuleCode(String shippingModuleCode) {
		this.shippingModuleCode = shippingModuleCode;
	}
	public String getShippingModuleCode() {
		return shippingModuleCode;
	}
	public void setShippingReturnCode(String shippingReturnCode) {
		this.shippingReturnCode = shippingReturnCode;
	}
	public String getShippingReturnCode() {
		return shippingReturnCode;
	}
	public void setFreeShipping(boolean freeShipping) {
		this.freeShipping = freeShipping;
	}
	public boolean isFreeShipping() {
		return freeShipping;
	}
	public void setFreeShippingAmount(BigDecimal freeShippingAmount) {
		this.freeShippingAmount = freeShippingAmount;
	}
	public BigDecimal getFreeShippingAmount() {
		return freeShippingAmount;
	}
	public void setHandlingFees(BigDecimal handlingFees) {
		this.handlingFees = handlingFees;
	}
	public BigDecimal getHandlingFees() {
		return handlingFees;
	}
	public void setApplyTaxOnShipping(boolean applyTaxOnShipping) {
		this.applyTaxOnShipping = applyTaxOnShipping;
	}
	public boolean isApplyTaxOnShipping() {
		return applyTaxOnShipping;
	}
	public void setSelectedShippingOption(ShippingOption selectedShippingOption) {
		this.selectedShippingOption = selectedShippingOption;
	}
	public ShippingOption getSelectedShippingOption() {
		return selectedShippingOption;
	}
	public String getQuoteError() {
		return quoteError;
	}
	public void setQuoteError(String quoteError) {
		this.quoteError = quoteError;
	}
	
	

}
