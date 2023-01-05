package com.salesmanager.test.business.utils;

import com.salesmanager.core.business.utils.DataUtils;
import com.salesmanager.core.constants.MeasureUnit;
import com.salesmanager.core.model.merchant.MerchantStore;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class DataUtilsTest {

    /**
     * This methods tests {@link DataUtils#trimPostalCode(String)}
     */
    @Test
    public void testTrimPostalCode(){
        String result = DataUtils.trimPostalCode(" 364856-A56 - B888@ ");
        assertEquals("364856A56B888", result);
    }

    /**
     * This methods tests {@link DataUtils#getWeight(double, MerchantStore, String)}
     */
    @Test
    public void testGetWeight_When_StoreUnit_LB_MeasurementUnit_LB(){
        MerchantStore store = mock(MerchantStore.class);
        when(store.getWeightunitcode()).thenReturn(MeasureUnit.LB.name());
        double result = DataUtils.getWeight(100.789, store, MeasureUnit.LB.name());
        assertEquals(100.79, result, 0);
    }

    /**
     * This methods tests {@link DataUtils#getWeight(double, MerchantStore, String)}
     */
    @Test
    public void testGetWeight_When_StoreUnit_KG_MeasurementUnit_LB(){
        MerchantStore store = mock(MerchantStore.class);
        when(store.getWeightunitcode()).thenReturn(MeasureUnit.KG.name());
        double result = DataUtils.getWeight(100.789, store, MeasureUnit.LB.name());
        assertEquals(221.74, result, 0);
    }

    /**
     * This methods tests {@link DataUtils#getWeight(double, MerchantStore, String)}
     */
    @Test
    public void testGetWeight_When_StoreUnit_KG_MeasurementUnit_KG(){
        MerchantStore store = mock(MerchantStore.class);
        when(store.getWeightunitcode()).thenReturn(MeasureUnit.KG.name());
        double result = DataUtils.getWeight(100.789, store, MeasureUnit.KG.name());
        assertEquals(100.79, result, 0);
    }

    /**
     * This methods tests {@link DataUtils#getWeight(double, MerchantStore, String)}
     */
    @Test
    public void testGetWeight_When_StoreUnit_LB_MeasurementUnit_KG(){
        MerchantStore store = mock(MerchantStore.class);
        when(store.getWeightunitcode()).thenReturn(MeasureUnit.LB.name());
        double result = DataUtils.getWeight(100.789, store, MeasureUnit.KG.name());
        assertEquals(45.81, result, 0);
    }
    
    /**
     * This methods tests {@link DataUtils#getMeasure(double, MerchantStore, String)}
     */
    @Test
    public void testGetMeasureWhen_StoreUnit_IN_MeasurementUnit_IN(){
        MerchantStore store = mock(MerchantStore.class);
        when(store.getSeizeunitcode()).thenReturn(MeasureUnit.IN.name());
        double result = DataUtils.getMeasure(100.789, store, MeasureUnit.IN.name());
        assertEquals(100.79, result, 0);
    }

    /**
     * This methods tests {@link DataUtils#getMeasure(double, MerchantStore, String)}
     */
    @Test
    public void testGetMeasureWhen_StoreUnit_CM_MeasurementUnit_IN(){
        MerchantStore store = mock(MerchantStore.class);
        when(store.getSeizeunitcode()).thenReturn(MeasureUnit.CM.name());
        double result = DataUtils.getMeasure(100.789, store, MeasureUnit.IN.name());
        assertEquals(256.00, result, 0);
    }

    /**
     * This methods tests {@link DataUtils#getMeasure(double, MerchantStore, String)}
     */
    @Test
    public void testGetMeasureWhen_StoreUnit_CM_MeasurementUnit_CM(){
        MerchantStore store = mock(MerchantStore.class);
        when(store.getSeizeunitcode()).thenReturn(MeasureUnit.CM.name());
        double result = DataUtils.getMeasure(100.789, store, MeasureUnit.CM.name());
        assertEquals(100.79, result, 0);
    }

    /**
     * This methods tests {@link DataUtils#getMeasure(double, MerchantStore, String)}
     */
    @Test
    public void testGetMeasureWhen_StoreUnit_IN_MeasurementUnit_CM(){
        MerchantStore store = mock(MerchantStore.class);
        when(store.getSeizeunitcode()).thenReturn(MeasureUnit.IN.name());
        double result = DataUtils.getMeasure(100.789, store, MeasureUnit.CM.name());
        assertEquals(39.31, result, 0);
    }
}
