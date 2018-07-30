package com.salesmanager.core.business.repositories.catalog.product.relationship;

import com.salesmanager.core.model.catalog.product.relationship.ProductRelationship;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProductRelationshipRepository extends JpaRepository<ProductRelationship, Long>, ProductRelationshipRepositoryCustom {

}
