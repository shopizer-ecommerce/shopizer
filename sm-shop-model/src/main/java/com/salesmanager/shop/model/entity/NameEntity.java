package com.salesmanager.shop.model.entity;

import javax.validation.constraints.NotEmpty;

import com.salesmanager.shop.model.constants.SmShopModelLiterals;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Used as an input request object where an entity name and or id is important
 * @author carlsamson
 * @lastUpdated 2025-09-07 By Kuntal
 */
@Getter
@Setter
@NoArgsConstructor
public class NameEntity extends Entity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = SmShopModelLiterals.SERIAL_VERSION_UID;
		@NotEmpty
    private String name;

}
