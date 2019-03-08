package com.salesmanager.admin.components.content.util;

import java.util.HashSet;
import java.util.Set;

import org.json.simple.JSONArray;

public class JsonUtils {

    public static Set<String> jsonArrayToSet(JSONArray array){

        Set<String> result = new HashSet();
        for (Object o : array) {
            result.add(o.toString());
        }
        return result;
    }
}
