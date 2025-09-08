package com.salesmanager.shop.model.entity;

import java.io.Serializable;
import javax.validation.constraints.NotNull;

import com.salesmanager.shop.model.constants.SmShopModelLiterals;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Represents an entity identified by a unique value and a merchant.
 *
 * @lastUpdated 2025-09-08 By Kuntal
 */
@Getter
@Setter
@NoArgsConstructor
public class UniqueEntity implements Serializable {

    private static final long serialVersionUID = SmShopModelLiterals.SERIAL_VERSION_UID;

    @NotNull
    private String unique;

    @NotNull
    private String merchant;
}
