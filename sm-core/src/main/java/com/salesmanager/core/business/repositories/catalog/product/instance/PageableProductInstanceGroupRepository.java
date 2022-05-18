package com.salesmanager.core.business.repositories.catalog.product.instance;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.salesmanager.core.model.catalog.product.instance.ProductInstanceGroup;

public interface PageableProductInstanceGroupRepository extends PagingAndSortingRepository<ProductInstanceGroup, Long> {

}
