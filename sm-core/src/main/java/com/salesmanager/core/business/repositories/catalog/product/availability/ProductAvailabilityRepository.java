package com.salesmanager.core.business.repositories.catalog.product.availability;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.salesmanager.core.model.catalog.product.availability.ProductAvailability;

public interface ProductAvailabilityRepository extends JpaRepository<ProductAvailability, Long> {
  
  @Query("select count(distinct p) from ProductAvailability as p where p.product.id=?1")
  int count(Long productId);
  
  @Query(value = "select distinct p from ProductAvailability p "
      + "left join fetch p.merchantStore pm "
      + "left join fetch p.prices pp "
      + "left join fetch pp.descriptions ppd "
      + "left join fetch p.merchantStore pm "
      + "join fetch p.product ppr "
      + "join fetch ppr.merchantStore pprm "
      + "where p.id=?1 ")
  ProductAvailability getById(Long availabilityId);
  
  @Query(value = "select distinct p from ProductAvailability p "
      + "left join fetch p.merchantStore pm "
      + "left join fetch p.prices pp "
      + "left join fetch pp.descriptions ppd "
      + "left join fetch p.merchantStore pm "
      + "join fetch p.product ppr "
      + "join fetch ppr.merchantStore pprm "
      + "where p.id=?1 "
      + "and pprm.id=?2")
  ProductAvailability getById(Long availabilityId, int merchantId);
  
  @Query(value = "select distinct p from ProductAvailability p "
      + "left join fetch p.merchantStore pm "
      + "left join fetch p.prices pp "
      + "left join fetch pp.descriptions ppd "
      + "left join fetch p.merchantStore pm "
      + "join fetch p.product ppr "
      + "join fetch ppr.merchantStore pprm "
      + "where ppr.id=?1 "
      + "and pm.code=?2")
  ProductAvailability getByStore(Long productId, String store);
  
  @Query(value = "select distinct p from ProductAvailability p "
      + "left join fetch p.merchantStore pm "
      + "left join fetch p.prices pp "
      + "left join fetch pp.descriptions ppd "
      + "left join fetch p.merchantStore pm "
      + "join fetch p.product ppr "
      + "join fetch ppr.merchantStore pprm "
      + "where ppr.id=?1 "
      + "and p.id=?2")
  ProductAvailability getByStore(Long productId, Long inventory);

}
