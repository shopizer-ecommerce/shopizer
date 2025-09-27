package com.salesmanager.shop.model.catalog.catalog;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.salesmanager.shop.model.constants.TableNameConstant;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity     
@Table(name = TableNameConstant.CATALOG)
@Getter
@Setter
@NoArgsConstructor
public class CatalogEntity extends com.salesmanager.shop.model.entity.Entity {
    private boolean visible;
    private boolean defaultCatalog;
    private String code;
}
