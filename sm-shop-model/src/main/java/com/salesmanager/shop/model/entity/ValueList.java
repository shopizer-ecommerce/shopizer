package com.salesmanager.shop.model.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.salesmanager.shop.model.constants.SmShopModelLiterals;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Simple wrapper for a list of string values.
 *
 * @lastUpdated 2025-09-08 By Kuntal
 */
@Getter
@Setter
@NoArgsConstructor
public class ValueList implements Serializable {

    private static final long serialVersionUID = SmShopModelLiterals.SERIAL_VERSION_UID;

    private List<String> values = new ArrayList<>();
}
