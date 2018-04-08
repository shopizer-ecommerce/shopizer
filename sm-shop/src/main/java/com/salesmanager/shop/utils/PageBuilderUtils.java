package com.salesmanager.shop.utils;

import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.shop.constants.Constants;
import com.salesmanager.shop.store.controller.ControllerConstants;

public class PageBuilderUtils {
	
	public static String build404(MerchantStore store) {
		return new StringBuilder().append(ControllerConstants.Tiles.Pages.notFound).append(".").append(store.getStoreTemplate()).toString();
	}
	
	public static String buildHomePage(MerchantStore store) {
		return "redirect:" + Constants.SHOP_URI;
	}

}
