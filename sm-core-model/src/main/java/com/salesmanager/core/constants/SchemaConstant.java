package com.salesmanager.core.constants;

import java.util.HashMap;
import java.util.Locale;

public class SchemaConstant {

	public final static String SALESMANAGER_SCHEMA = "SALESMANAGER";

	/**
	 * Languages iso codes
	 * 
	 */
	public static final String[] LANGUAGE_ISO_CODE = {"en", "tm"};
	
	public final static int DESCRIPTION_ID_ALLOCATION_SIZE = 1;
	public final static int DESCRIPTION_ID_START_VALUE = 2000;
	
	/**
	 * All regions
	 */
	public static final String ALL_REGIONS = "*";

	/**
	 * Country iso codes
	 */
	public static final String[] COUNTRY_ISO_CODE = { "IN" };

	/**
	 * Locale per country iso codes
	 */
	public static final HashMap<String, Locale> LOCALES = new HashMap<String, Locale>();

	static {
		for (Locale locale : Locale.getAvailableLocales()) {
			LOCALES.put(locale.getCountry(), locale);
		}
	}
	
	/**
	 * Currency codes with name
	 */
	public static final HashMap<String, String> CURRENCY_MAP = new HashMap<String, String>();
	
	static {
		CURRENCY_MAP.put("INR", "Indian Rupee");
	}
}
