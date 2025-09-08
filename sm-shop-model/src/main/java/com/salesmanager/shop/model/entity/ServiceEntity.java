package com.salesmanager.shop.model.entity;

import com.salesmanager.shop.model.constants.SmShopModelLiterals;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Abstract base class for service entities with status and message.
 *
 * @lastUpdated 2025-09-08 By Kuntal
 */
@Getter
@Setter
@NoArgsConstructor
public abstract class ServiceEntity {

    private int status = SmShopModelLiterals.ZERO;
    private String message;
}
