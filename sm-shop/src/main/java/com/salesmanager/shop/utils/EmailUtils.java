package com.salesmanager.shop.utils;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.shop.constants.Constants;


@Component
public class EmailUtils {
	
	private final static String EMAIL_STORE_NAME = "EMAIL_STORE_NAME";
	private final static String EMAIL_FOOTER_COPYRIGHT = "EMAIL_FOOTER_COPYRIGHT";
	private final static String EMAIL_DISCLAIMER = "EMAIL_DISCLAIMER";
	private final static String EMAIL_SPAM_DISCLAIMER = "EMAIL_SPAM_DISCLAIMER";
	private final static String EMAIL_ADMIN_LABEL = "EMAIL_ADMIN_LABEL";
	private final static String LOGOPATH = "LOGOPATH";
	
	@Inject
	@Qualifier("img")
	private ImageFilePath imageUtils;
	
	/**
	 * Builds generic html email information
	 * @param store
	 * @param messages
	 * @param locale
	 * @return
	 */
	public Map<String, String> createEmailObjectsMap(String contextPath, MerchantStore store, LabelUtils messages, Locale locale){
		
		Map<String, String> templateTokens = new HashMap<String, String>();
		
		String[] adminNameArg = {store.getStorename()};
		String[] adminEmailArg = {store.getStoreEmailAddress()};
		String[] copyArg = {store.getStorename(), DateUtil.getPresentYear()};
		
		templateTokens.put(EMAIL_ADMIN_LABEL, messages.getMessage("email.message.from", adminNameArg, locale));
		templateTokens.put(EMAIL_STORE_NAME, store.getStorename());
		templateTokens.put(EMAIL_FOOTER_COPYRIGHT, messages.getMessage("email.copyright", copyArg, locale));
		templateTokens.put(EMAIL_DISCLAIMER, messages.getMessage("email.disclaimer", adminEmailArg, locale));
		templateTokens.put(EMAIL_SPAM_DISCLAIMER, messages.getMessage("email.spam.disclaimer", locale));
		
		if(store.getStoreLogo()!=null) {
			//TODO revise
			StringBuilder logoPath = new StringBuilder();
			String scheme = Constants.HTTP_SCHEME;
			logoPath.append("<img src='").append(scheme).append("://").append(store.getDomainName()).append(contextPath).append("/").append(imageUtils.buildStoreLogoFilePath(store)).append("' style='max-width:400px;'>");
			templateTokens.put(LOGOPATH, logoPath.toString());
		} else {
			templateTokens.put(LOGOPATH, store.getStorename());
		}

		return templateTokens;
	}

}
