package com.salesmanager.shop.model.entity;

import java.io.Serializable;

import com.salesmanager.shop.model.constants.SmShopModelLiterals;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @lastUpdated 2025-09-07 By Kuntal 
 */
@Getter
@Setter
@NoArgsConstructor     
@AllArgsConstructor 
public class Entity implements Serializable {

	private static final long serialVersionUID = SmShopModelLiterals.SERIAL_VERSION_UID;
    private Long id = SmShopModelLiterals.ZERO_LONG;
}
