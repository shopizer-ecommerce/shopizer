package com.salesmanager.test.shop.controller.system.rest;

import org.junit.Test;

import com.salesmanager.shop.store.api.v1.user.UserApi;

public class CoverageTest {

    @Test
    public void justForCoverageConfig() {
        new UserApi();
    }
}
