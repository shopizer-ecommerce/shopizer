package com.salesmanager.test.shop.unit.utils;

import com.salesmanager.shop.utils.FileNameUtils;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class FileNameUtilsTest {

    private FileNameUtils fileNameUtils;

    @Before
    public void setUp() {
        fileNameUtils = new FileNameUtils();
    }

    @Test
    public void validFileName_withValidName_returnsTrue() {
        assertTrue(fileNameUtils.validFileName("image.jpg"));
    }

    @Test
    public void validFileName_withNoExtension_returnsFalse() {
        assertFalse(fileNameUtils.validFileName("imagefile"));
    }

    @Test
    public void validFileName_withNoBaseName_returnsFalse() {
        assertFalse(fileNameUtils.validFileName(".jpg"));
    }

    @Test
    public void validFileName_withEmptyString_returnsFalse() {
        assertFalse(fileNameUtils.validFileName(""));
    }

    @Test
    public void validFileName_withDotOnly_returnsFalse() {
        assertFalse(fileNameUtils.validFileName("."));
    }

    @Test
    public void validFileName_withMultipleDots_returnsTrue() {
        assertTrue(fileNameUtils.validFileName("my.product.image.png"));
    }
}
