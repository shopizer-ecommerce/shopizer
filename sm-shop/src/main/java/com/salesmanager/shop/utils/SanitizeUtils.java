package com.salesmanager.shop.utils;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;

public class SanitizeUtils {

	/**
	 * should not contain /
	 */
    private static List<Character> blackList = Arrays.asList(';','%', '&', '=', '|', '*', '+', '_',
            '^', '%','$','(', ')', '{', '}', '<', '>', '[',
            ']', '`', '\'', '~','\\', '?');

    private SanitizeUtils() {
        //Utility class
    }

	public static String getSafeString(String value) {
		
		/*
        //value = value.replaceAll("<", "& lt;").replaceAll(">", "& gt;");
        //value = value.replaceAll("\\(", "& #40;").replaceAll("\\)", "& #41;");
        //value = value.replaceAll("'", "& #39;");
        value = value.replaceAll("eval\\((.*)\\)", "");
        value = value.replaceAll("[\\\"\\\'][\\s]*javascript:(.*)[\\\"\\\']", "\"\"");

        value = value.replaceAll("(?i)<script.*?>.*?<script.*?>", "");
        value = value.replaceAll("(?i)<script.*?>.*?</script.*?>", "");
        value = value.replaceAll("(?i)<.*?javascript:.*?>.*?</.*?>", "");
        value = value.replaceAll("(?i)<.*?\\s+on.*?>.*?</.*?>", "");
        //value = value.replaceAll("<script>", "");
        //value = value.replaceAll("</script>", "");
        logger.info("OutnXSS RequestWrapper ........ value ......." + value);*/
        //return HtmlUtils.htmlEscape(value);	
		
        //TODO Make configurable escape of characters html or xml
        StringBuilder safe = new StringBuilder();
        if(StringUtils.isNotEmpty(value)) {
            // Fastest way for short strings - https://stackoverflow.com/a/11876086/195904
            for(int i=0; i<value.length(); i++) {
                char current = value.charAt(i);
                if(!blackList.contains(current)) {
                    safe.append(current);
                }
            }
        }
        return StringEscapeUtils.escapeXml11(safe.toString());
	}

}
