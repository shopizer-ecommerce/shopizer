package com.salesmanager.shop.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.Locale;

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

	/**
	 * Locale based bigdecimal parser
	 * @return
	 */
	public static BigDecimal getBigDecimal(String bigDecimal) throws ParseException {
		NumberFormat decimalFormat = NumberFormat.getInstance(Locale.getDefault());
		BigDecimal value;
		if(decimalFormat instanceof DecimalFormat) {
			((DecimalFormat) decimalFormat).setParseBigDecimal(true);
			value = (BigDecimal) decimalFormat.parse(bigDecimal);
		} else {
			value = new BigDecimal(bigDecimal);
		}
		return value;
	}
}
