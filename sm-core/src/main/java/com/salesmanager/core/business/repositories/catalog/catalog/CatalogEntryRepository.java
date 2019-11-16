package com.salesmanager.core.business.repositories.catalog.catalog;

import org.springframework.data.jpa.repository.JpaRepository;

import com.salesmanager.core.model.catalog.catalog.CatalogEntry;

public interface CatalogEntryRepository extends JpaRepository<CatalogEntry, Long> {

}
