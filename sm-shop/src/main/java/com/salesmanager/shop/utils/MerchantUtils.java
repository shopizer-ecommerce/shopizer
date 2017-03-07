package com.salesmanager.shop.utils;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;

import com.salesmanager.core.model.merchant.MerchantStore;

public class MerchantUtils {
	
	public String getFooterMessage(MerchantStore store, String prefix, String suffix) {
		
		StringBuilder footerMessage = new StringBuilder();
		
		if(!StringUtils.isBlank(prefix)) {
			footerMessage.append(prefix).append(" ");
		}
		
		Date sinceDate = null;
		String inBusinessSince = store.getDateBusinessSince();
		
		
		return null;
	}

}
