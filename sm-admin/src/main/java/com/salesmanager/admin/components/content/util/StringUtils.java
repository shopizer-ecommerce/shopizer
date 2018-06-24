package com.salesmanager.admin.components.content.util;

public class StringUtils {

    public static boolean isEmpty(final CharSequence cs) {
        return cs == null || cs.length() == 0;
    }

    public static String normalize(String string){
        return normalize(string, true);
    }
    public static String normalize(String string, boolean removeSpace) {
        String result = string.replaceAll("[^a-zA-Z0-9 /._-]+", "").replaceAll("  ", " ").replaceAll("//", "/").trim();
        if(removeSpace) result = result.replace(" ", "_");
        return result;
    }
}