package com.salesmanager.shop.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Used for filtering lists
 * @author carlsamson
 * @lastUpdated 2025-09-07 By Kuntal
 */

@Getter
@Setter
@NoArgsConstructor
public class ListCriteria {
	private String name;
	private String type;
}
