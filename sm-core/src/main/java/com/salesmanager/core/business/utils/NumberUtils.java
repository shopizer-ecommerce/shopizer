package com.salesmanager.core.business.utils;

import java.util.Objects;

public final class NumberUtils {

    public static boolean isPositive(Long id) {
        return Objects.nonNull(id) && id > 0;
    }
}
