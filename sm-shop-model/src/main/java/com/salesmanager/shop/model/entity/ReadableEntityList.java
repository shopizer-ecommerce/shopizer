package com.salesmanager.shop.model.entity;

import java.util.List;

import com.salesmanager.shop.model.constants.SmShopModelLiterals;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Generic wrapper for a list of readable entities.
 *
 * @param <T> the type of items in the list
 * @lastUpdated 2025-09-08 By Kuntal
 */
@Getter
@Setter
@NoArgsConstructor
public class ReadableEntityList<T> extends ReadableList {

    private static final long serialVersionUID = SmShopModelLiterals.SERIAL_VERSION_UID;

    private List<T> items;
}
