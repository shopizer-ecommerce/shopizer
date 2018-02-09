package com.salesmanager.core.model.shipping;

import java.io.Serializable;
import java.math.BigDecimal;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ShippingOption implements Serializable {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ShippingOption.class);
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BigDecimal optionPrice;
	private Long shippingQuoteOptionId;


	private String optionName = null;
	private String optionCode = null;
	private String optionDeliveryDate = null;
	private String optionShippingDate = null;
	private String optionPriceText = null;
	private String optionId = null;
	private String description = null;
	private String shippingModuleCode = null;
	private String note = null;
	
	private String estimatedNumberOfDays;

	

	public BigDecimal getOptionPrice() {
		
		if(!StringUtils.isBlank(this.getOptionPriceText())) {
			try {
				this.optionPrice = new BigDecimal(this.getOptionPriceText());
			} catch(Exception e) {
				LOGGER.equals("Can't convert price text " + this.getOptionPriceText() + " to big decimal");
			}
		}
		
		return optionPrice;
	}
	
	public void setOptionPrice(BigDecimal optionPrice) {
		this.optionPrice = optionPrice;
	}

	public void setOptionCode(String optionCode) {
		this.optionCode = optionCode;
	}
	public String getOptionCode() {
		return optionCode;
	}
	public void setOptionName(String optionName) {
		this.optionName = optionName;
	}
	public String getOptionName() {
		return optionName;
	}

	public void setOptionPriceText(String optionPriceText) {
		this.optionPriceText = optionPriceText;
	}
	public String getOptionPriceText() {
		return optionPriceText;
	}
	public void setOptionId(String optionId) {
		this.optionId = optionId;
	}
	public String getOptionId() {
		return optionId;
	}
	public void setOptionDeliveryDate(String optionDeliveryDate) {
		this.optionDeliveryDate = optionDeliveryDate;
	}
	public String getOptionDeliveryDate() {
		return optionDeliveryDate;
	}
	public void setOptionShippingDate(String optionShippingDate) {
		this.optionShippingDate = optionShippingDate;
	}
	public String getOptionShippingDate() {
		return optionShippingDate;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getDescription() {
		return description;
	}
	public void setEstimatedNumberOfDays(String estimatedNumberOfDays) {
		this.estimatedNumberOfDays = estimatedNumberOfDays;
	}
	public String getEstimatedNumberOfDays() {
		return estimatedNumberOfDays;
	}

	public String getShippingModuleCode() {
		return shippingModuleCode;
	}

	public void setShippingModuleCode(String shippingModuleCode) {
		this.shippingModuleCode = shippingModuleCode;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Long getShippingQuoteOptionId() {
		return shippingQuoteOptionId;
	}

	public void setShippingQuoteOptionId(Long shippingQuoteOptionId) {
		this.shippingQuoteOptionId = shippingQuoteOptionId;
	}

}
