package com.shopizer.search.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
	
	public final static String DATE_FORMAT = "yyyy-MM-dd";
	
	public static Date formatDate(String date) throws Exception {
		
		DateFormat myDateFormat = new SimpleDateFormat(DATE_FORMAT);
		return myDateFormat.parse(date);
	}

}
