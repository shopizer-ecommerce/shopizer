package com.salesmanager.shop.validation;

import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.List;

public class SanitizeUtils {

    private static List<Character> blackList = Arrays.asList(';','%', '&', '=', '|', '/', '*', '+', '_',
            '^', '%','$','(', ')', '{', '}', '<', '>', '[',
            ']', '`', '\'', '~','\\', '?', ',');

    private SanitizeUtils() {
        //Utility class
    }
    public static String getSafeString(String source) {
        //TODO Make configurable escape of characters html or xml
        StringBuilder safe = new StringBuilder();
        if(StringUtils.isNotEmpty(source)) {
            // Fastest way for short strings - https://stackoverflow.com/a/11876086/195904
            for(int i=0; i<source.length(); i++) {
                char current = source.charAt(i);
                if(!blackList.contains(current)) {
                    safe.append(current);
                }
            }
        }
        return StringEscapeUtils.escapeXml11(safe.toString());
    }

}
