package com.salesmanager.core.business.repositories.catalog.product.relationship;

import com.google.api.client.util.Lists;
import com.salesmanager.core.model.catalog.product.Product;
import com.salesmanager.core.model.catalog.product.relationship.ProductRelationship;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


public class ProductRelationshipRepositoryImpl implements ProductRelationshipRepositoryCustom {

  private static final String HQL_GET_BY_CODE_AND_STORE_ID_AND_PRODUCT_ID =
      "select distinct pr from ProductRelationship as pr "
          + "left join fetch pr.product p "
          + "join fetch pr.relatedProduct rp "
          + "left join fetch rp.descriptions rpd "
          + "where pr.code=:code "
          + "and pr.store.id=:storeId "
          + "and p.id=:id "
          + "and rpd.language.id=:langId";
  private static final String HQL_GET_BY_CODE_AND_STORE_ID_AND_RP_PRODUCT_ID =
	      "select distinct pr from ProductRelationship as pr "
	          + "left join fetch pr.relatedProduct rp "
	          + "where pr.code=:code "
	          + "and pr.store.id=:storeId "
	          + "and rp.available=:available "
	          + "and rp.id=:rpid";
  private static final String HQL_GET_BY_PRODUCT_ID_AND_CODE_AVAILABLE =
      "select distinct pr from ProductRelationship as pr "
          + "left join fetch pr.product p "
          + "left join fetch pr.relatedProduct rp "
          + "left join fetch rp.attributes pattr "
          + "left join fetch rp.categories rpc "
          + "left join fetch rpc.descriptions rpcd "
          + "left join fetch rp.descriptions rpd "
          + "left join fetch rp.owner rpo "
          + "left join fetch rp.images pd "
          + "left join fetch rp.merchantStore rpm "
          + "left join fetch rpm.currency rpmc "
          + "left join fetch rp.availabilities pa "
          + "left join fetch pa.prices pap "
          + "left join fetch pap.descriptions papd "
          + "left join fetch rp.manufacturer manuf "
          + "left join fetch manuf.descriptions manufd "
          + "left join fetch rp.type type "
          + "where pr.code=:code "
          + "and rp.available=:available "
          + "and p.id=:pId";
  private static final String HQL_GET_PRODUCTS_BY_PRODUCT_ID =
      "select distinct pr from ProductRelationship as pr "
          + "left join fetch pr.product p "
          + "left join fetch pr.relatedProduct rp "
          + "left join fetch rp.attributes pattr "
          + "left join fetch rp.categories rpc "
          + "left join fetch p.descriptions pd "
          + "left join fetch rp.descriptions rpd "
          + "where p.id=:id"
          + " or rp.id=:id";
  private static final String HQL_GET_PRODUCT_BY_CODE_AND_STORE_ID =
      "select distinct pr from ProductRelationship as pr "
          + "left join fetch pr.product p "
          + "join fetch pr.relatedProduct rp "
          + "left join fetch rp.descriptions rpd "
          + "where pr.code=:code "
          + "and pr.store.id=:storeId ";
  private static final String HQL_GET_PRODUCT_BY_CODE_AND_STORE_ID_AND_LANG_ID =
      "select distinct pr from ProductRelationship as pr "
          + "left join fetch pr.product p "
          + "join fetch pr.relatedProduct rp "
          + "left join fetch rp.attributes pattr "
          + "left join fetch pattr.productOption po "
          + "left join fetch po.descriptions pod "
          + "left join fetch pattr.productOptionValue pov "
          + "left join fetch pov.descriptions povd "
          + "left join fetch rp.categories rpc "
          + "left join fetch rpc.descriptions rpcd "
          + "left join fetch rp.descriptions rpd "
          + "left join fetch rp.owner rpo "
          + "left join fetch rp.images pd "
          + "left join fetch rp.merchantStore rpm "
          + "left join fetch rpm.currency rpmc "
          + "left join fetch rp.availabilities pa "
          + "left join fetch rp.manufacturer m "
          + "left join fetch m.descriptions md "
          + "left join fetch pa.prices pap "
          + "left join fetch pap.descriptions papd "
          + "where pr.code=:code "
          + "and pr.store.id=:storeId "
          + "and rpd.language.id=:langId";
  private static final String HQL_GET_GROUP_BY_CODE_AND_STORE_ID =
      "select distinct pr from ProductRelationship as pr "
          + "left join fetch pr.product p "
          + "left join fetch pr.relatedProduct rp "
          + "left join fetch rp.attributes pattr "
          + "left join fetch rp.categories rpc "
          + "left join fetch rpc.descriptions rpcd "
          + "left join fetch rp.descriptions rpd "
          + "left join fetch rp.owner rpo "
          + "left join fetch rp.images pd "
          + "left join fetch rp.merchantStore rpm "
          + "left join fetch rpm.currency rpmc "
          + "left join fetch rp.availabilities pa "
          + "left join fetch pa.prices pap "
          + "left join fetch pap.descriptions papd "
          + "left join fetch rp.manufacturer manuf "
          + "left join fetch manuf.descriptions manufd "
          + "left join fetch rp.type type "
          + "where pr.code=:code "
          + "and pr.store.id=:storeId ";
  private static final String HQL_GET_PRODUCT_RELATIONSHIP_BY_STORE_ID =
      "select distinct pr from ProductRelationship as pr "
          + "where pr.store.id=:store "
          + "and pr.product=null";
  private static final String HQL_GET_PRODUCT_RELATIONSHIP_BY_CODE_AND_STORE_ID =
      "select distinct pr from ProductRelationship as pr "
          + "left join fetch pr.product p "
          + "left join fetch pr.relatedProduct rp "
          + "left join fetch rp.descriptions rpd "
          + "where pr.code=:code "
          + "and pr.store.id=:storeId "
          + "and rpd.language.id=:langId";

  @PersistenceContext
  private EntityManager entityManager;

  @Override
  @SuppressWarnings("unchecked")
  public List<ProductRelationship> getByType(MerchantStore store, String type, Product product,
      Language language) {
    return entityManager.createQuery(HQL_GET_BY_CODE_AND_STORE_ID_AND_PRODUCT_ID)
        .setParameter("code", type)
        .setParameter("id", product.getId())
        .setParameter("storeId", store.getId())
        .setParameter("langId", language.getId())
        .getResultList();
  }

  @Override
  @SuppressWarnings("unchecked")
  public List<ProductRelationship> getGroupByType(MerchantStore store, String type) {
    return entityManager.createQuery(HQL_GET_PRODUCT_RELATIONSHIP_BY_CODE_AND_STORE_ID)
        .setParameter("code", type)
        .setParameter("storeId", store.getId())
        .getResultList();
  }

  @Override
  @SuppressWarnings("unchecked")
  public List<ProductRelationship> getByType(MerchantStore store, String type, Language language) {
    return entityManager.createQuery(HQL_GET_PRODUCT_BY_CODE_AND_STORE_ID_AND_LANG_ID)
        .setParameter("code", type)
        .setParameter("langId", language.getId())
        .setParameter("storeId", store.getId())
        .getResultList();
  }

  @Override
  @SuppressWarnings("unchecked")
  public List<ProductRelationship> getByGroup(MerchantStore store, String group) {
    return entityManager.createQuery(HQL_GET_GROUP_BY_CODE_AND_STORE_ID)
        .setParameter("code", group)
        .setParameter("storeId", store.getId())
        .getResultList();
  }

  @Override
  public List<ProductRelationship> getGroups(MerchantStore store) {
    @SuppressWarnings("unchecked")
    List<ProductRelationship> relations = entityManager.createQuery(HQL_GET_PRODUCT_RELATIONSHIP_BY_STORE_ID)
        .setParameter("store", store.getId())
        .getResultList();
    Map<String, ProductRelationship> relationMap = relations.stream()
        .collect(Collectors.toMap(ProductRelationship::getCode, p -> p, (p, q) -> p));
    return Lists.newArrayList(relationMap.values());
  }


  @Override
  @SuppressWarnings("unchecked")
  public List<ProductRelationship> getByType(MerchantStore store, String type) {
    return entityManager.createQuery(HQL_GET_PRODUCT_BY_CODE_AND_STORE_ID)
        .setParameter("code", type)
        .setParameter("storeId", store.getId())
        .getResultList();
  }

  @Override
  @SuppressWarnings("unchecked")
  public List<ProductRelationship> listByProducts(Product product) {
    return entityManager.createQuery(HQL_GET_PRODUCTS_BY_PRODUCT_ID)
        .setParameter("id", product.getId())
        .getResultList();
  }

  @Override
  @SuppressWarnings("unchecked")
  public List<ProductRelationship> getByType(MerchantStore store, String type, Product product) {
    return entityManager.createQuery(HQL_GET_BY_PRODUCT_ID_AND_CODE_AVAILABLE)
        .setParameter("code", type)
        .setParameter("available", true)
        .setParameter("pId", product.getId())
        .getResultList();
  }

	@SuppressWarnings("unchecked")
	@Override
	public List<ProductRelationship> getByTypeAndRelatedProduct(MerchantStore store, String type, Product product) {
	    return entityManager.createQuery(HQL_GET_BY_CODE_AND_STORE_ID_AND_RP_PRODUCT_ID)
	            .setParameter("code", type)
	            .setParameter("available", true)
	            .setParameter("rpid", product.getId())
	            .setParameter("storeId", store.getId())
	            .getResultList();
	}
}
