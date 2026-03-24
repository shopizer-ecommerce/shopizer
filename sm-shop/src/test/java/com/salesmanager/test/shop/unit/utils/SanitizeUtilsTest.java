package com.salesmanager.test.shop.unit.utils;

import com.salesmanager.shop.utils.SanitizeUtils;
import org.junit.Test;

import static org.junit.Assert.*;

public class SanitizeUtilsTest {

    @Test
    public void getSafeRequestParamString_withCleanInput_returnsSameValue() {
        String result = SanitizeUtils.getSafeRequestParamString("hello");
        assertEquals("hello", result);
    }

    @Test
    public void getSafeRequestParamString_withNull_returnsEmpty() {
        String result = SanitizeUtils.getSafeRequestParamString(null);
        assertEquals("", result);
    }

    @Test
    public void getSafeRequestParamString_withEmptyString_returnsEmpty() {
        String result = SanitizeUtils.getSafeRequestParamString("");
        assertEquals("", result);
    }

    @Test
    public void getSafeRequestParamString_stripsBlacklistedChars() {
        // semicolons, percent signs, etc. should be stripped
        String result = SanitizeUtils.getSafeRequestParamString("hello;world%test");
        assertFalse(result.contains(";"));
        assertFalse(result.contains("%"));
    }

    @Test
    public void getSafeRequestParamString_withAlphanumeric_preservesContent() {
        String result = SanitizeUtils.getSafeRequestParamString("Product123");
        assertTrue(result.contains("Product123"));
    }

    @Test
    public void getSafeRequestParamString_withScriptTag_stripsAngleBrackets() {
        String result = SanitizeUtils.getSafeRequestParamString("<script>");
        assertFalse(result.contains("<"));
        assertFalse(result.contains(">"));
    }
}
