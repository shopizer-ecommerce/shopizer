package com.salesmanager.test.shop.unit.utils;

import com.salesmanager.shop.utils.DateUtil;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

public class DateUtilTest {

    @Test
    public void generateTimeStamp_returnsNonNull() {
        assertNotNull(DateUtil.generateTimeStamp());
    }

    @Test
    public void generateTimeStamp_hasExpectedLength() {
        // yyyyMMddHHmmSS — SS is milliseconds (2 digits) = 14 or 15 chars
        int len = DateUtil.generateTimeStamp().length();
        assertTrue("Expected 14 or 15 chars but got " + len, len == 14 || len == 15);
    }

    @Test
    public void formatDate_withNullReturnsToday() {
        assertNotNull(DateUtil.formatDate(null));
    }

    @Test
    public void formatDate_withDateReturnsFormattedString() {
        String result = DateUtil.formatDate(new Date());
        // yyyy-MM-dd pattern
        assertTrue(result.matches("\\d{4}-\\d{2}-\\d{2}"));
    }

    @Test
    public void formatYear_withNullReturnsNull() {
        assertNull(DateUtil.formatYear(null));
    }

    @Test
    public void formatYear_withDateReturnsYear() {
        assertNotNull(DateUtil.formatYear(new Date()));
    }

    @Test
    public void formatLongDate_withNullReturnsNull() {
        assertNull(DateUtil.formatLongDate(null));
    }

    @Test
    public void formatLongDate_withDateReturnsNonNull() {
        assertNotNull(DateUtil.formatLongDate(new Date()));
    }

    @Test
    public void getDate_returnsNonNull() throws Exception {
        Date d = DateUtil.getDate("2024-01-15");
        assertNotNull(d);
    }

    @Test(expected = Exception.class)
    public void getDate_withInvalidFormat_throws() throws Exception {
        DateUtil.getDate("15/01/2024");
    }

    @Test
    public void addDaysToCurrentDate_returnsDateInFuture() {
        Date future = DateUtil.addDaysToCurrentDate(5);
        assertTrue(future.after(new Date()));
    }

    @Test
    public void addDaysToCurrentDate_returnsDateInPast() {
        Date past = DateUtil.addDaysToCurrentDate(-5);
        assertTrue(past.before(new Date()));
    }

    @Test
    public void dateBeforeEqualsDate_withNullFirstDate_returnsTrue() {
        assertTrue(DateUtil.dateBeforeEqualsDate(null, new Date()));
    }

    @Test
    public void dateBeforeEqualsDate_withNullSecondDate_returnsTrue() {
        assertTrue(DateUtil.dateBeforeEqualsDate(new Date(), null));
    }

    @Test
    public void dateBeforeEqualsDate_firstBeforeSecond_returnsTrue() {
        Date first = DateUtil.addDaysToCurrentDate(-1);
        Date second = new Date();
        assertTrue(DateUtil.dateBeforeEqualsDate(first, second));
    }

    @Test
    public void dateBeforeEqualsDate_firstAfterSecond_returnsFalse() {
        Date first = DateUtil.addDaysToCurrentDate(1);
        Date second = new Date();
        assertFalse(DateUtil.dateBeforeEqualsDate(first, second));
    }

    @Test
    public void dateBeforeEqualsDate_equalDates_returnsTrue() {
        Date d = new Date(1000000L);
        assertTrue(DateUtil.dateBeforeEqualsDate(d, d));
    }

    @Test
    public void getPresentDate_returnsNonNull() {
        assertNotNull(DateUtil.getPresentDate());
    }

    @Test
    public void getPresentYear_returnsFourDigitYear() {
        assertTrue(DateUtil.getPresentYear().matches("\\d{4}"));
    }
}
