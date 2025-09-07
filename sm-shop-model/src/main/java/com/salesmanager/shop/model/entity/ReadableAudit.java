package com.salesmanager.shop.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @lastUpdated 2025-09-07 By Kuntal
 */
@Getter
@Setter
@NoArgsConstructor
public class ReadableAudit {

	private String created;
	private String modified;
	private String user;
}
