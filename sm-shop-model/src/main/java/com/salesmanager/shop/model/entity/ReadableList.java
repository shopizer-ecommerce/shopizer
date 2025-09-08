package com.salesmanager.shop.model.entity;

import java.io.Serializable;

import com.salesmanager.shop.model.constants.SmShopModelLiterals;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Abstract base class representing a paginated list of readable entities.
 *
 * @lastUpdated 2025-09-08 By Kuntal
 */
@Getter
@Setter
@NoArgsConstructor
public abstract class ReadableList implements Serializable {

    private static final long serialVersionUID = SmShopModelLiterals.SERIAL_VERSION_UID;
    private int totalPages;
    private int number;
    private long recordsTotal;
    private int recordsFiltered;
}