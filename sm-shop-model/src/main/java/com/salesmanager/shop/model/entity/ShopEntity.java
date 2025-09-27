package com.salesmanager.shop.model.entity;

import java.io.Serializable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Abstract shop entity that includes language support.
 *
 * @lastUpdated 2025-09-08 By Kuntal
 */
@Getter
@Setter
@NoArgsConstructor
public abstract class ShopEntity extends Entity {

    private String language;
}
