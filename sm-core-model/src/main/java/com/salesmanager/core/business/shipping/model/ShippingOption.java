package com.salesmanager.core.business.shipping.model;

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

	private String optionName;
	private String optionCode;
	private String optionDeliveryDate;
	private String optionShippingDate;
	private String optionPriceText;
	private String optionId;
	private String description;
	
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



}
