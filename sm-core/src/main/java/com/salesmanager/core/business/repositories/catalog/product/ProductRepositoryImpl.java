package com.salesmanager.core.business.repositories.catalog.product;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.salesmanager.core.business.constants.Constants;
import com.salesmanager.core.business.utils.RepositoryHelper;
import com.salesmanager.core.model.catalog.product.Product;
import com.salesmanager.core.model.catalog.product.ProductCriteria;
import com.salesmanager.core.model.catalog.product.ProductList;
import com.salesmanager.core.model.catalog.product.attribute.AttributeCriteria;
import com.salesmanager.core.model.common.GenericEntityList;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.core.model.tax.taxclass.TaxClass;

public class ProductRepositoryImpl implements ProductRepositoryCustom {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProductRepositoryImpl.class);

	@PersistenceContext
	private EntityManager em;

	@Override
	public Product getById(Long productId, MerchantStore store) {
		return get(productId, store);
	}

	@Override
	public Product getById(Long productId) {
		return get(productId, null);
	}


	@Override
	public Product getProductWithOnlyMerchantStoreById(Long productId) {
		final String hql = "select distinct p from Product as p " +
				"join fetch p.merchantStore merch " +
				"where p.id=:pid";

		final Query q = this.em.createQuery(hql);
		q.setParameter("pid", productId);

		try {
			return (Product)q.getSingleResult();
		} catch (NoResultException ignored) {
			return null;
		}
   }



	private Product get(Long productId, MerchantStore merchant) {

		try {

			Integer merchantId = null;
			Integer parentId = null;
			List<Integer> ids = new ArrayList<Integer>();

			StringBuilder qs = new StringBuilder();
			qs.append(productQueryV2());

			qs.append("where p.id=:pid");
			if (merchant != null) {
				merchantId = merchant.getId();
				ids.add(merchantId);
				if(merchant.getParent()!=null) {
					parentId = merchant.getParent().getId();
					ids.add(parentId);
				}
			}

			if(merchantId != null) {
				//qs.append(" and merch.id=:mid");
				qs.append(" and merch.id in (:mid)");
			}

			String hql = qs.toString();
			Query q = this.em.createQuery(hql);

			q.setParameter("pid", productId);


			if(merchantId != null) {
				//q.setParameter("mid", merchant.getId());
				q.setParameter("mid", ids);
			}

			return (Product) q.getSingleResult();

		} catch (javax.persistence.NoResultException ers) {
			return null;
		}

	}

	@Override
	public Product getByCode(String productCode, Language language) {

		try {

			StringBuilder qs = new StringBuilder();
			qs.append("select distinct p from Product as p ");
			qs.append("join fetch p.availabilities pa ");
			qs.append("join fetch p.descriptions pd ");
			qs.append("join fetch p.merchantStore pm ");
			qs.append("left join fetch pa.prices pap ");
			qs.append("left join fetch pap.descriptions papd ");

			qs.append("left join fetch p.categories categs ");
			qs.append("left join fetch categs.descriptions categsd ");

			// images
			qs.append("left join fetch p.images images ");
			// options
			qs.append("left join fetch p.attributes pattr ");
			qs.append("left join fetch pattr.productOption po ");
			qs.append("left join fetch po.descriptions pod ");
			qs.append("left join fetch pattr.productOptionValue pov ");
			qs.append("left join fetch pov.descriptions povd ");
			qs.append("left join fetch p.relationships pr ");
			// other lefts
			qs.append("left join fetch p.manufacturer manuf ");
			qs.append("left join fetch manuf.descriptions manufd ");
			qs.append("left join fetch p.type type ");
			qs.append("left join fetch p.taxClass tx ");

			// RENTAL
			//qs.append("left join fetch p.owner owner ");

			qs.append("where p.sku=:code ");
			qs.append("and pd.language.id=:lang and papd.language.id=:lang");
			// this cannot be done on child elements from left join
			// qs.append("and pod.languageId=:lang and povd.languageId=:lang");

			String hql = qs.toString();
			Query q = this.em.createQuery(hql);

			q.setParameter("code", productCode);
			q.setParameter("lang", language.getId());

			return (Product) q.getSingleResult();

		} catch (javax.persistence.NoResultException ers) {
			return null;
		}

	}
	
	@Override
	public Product getByCode(String productCode, MerchantStore store) {

		try {

			StringBuilder qs = new StringBuilder();
			qs.append("select distinct p from Product as p ");
			qs.append("join fetch p.descriptions pd ");
			qs.append("join fetch p.merchantStore pm ");

			qs.append("left join fetch p.categories categs ");
			qs.append("left join fetch categs.descriptions categsd ");

			// options
			qs.append("left join fetch p.attributes pattr ");
			qs.append("left join fetch pattr.productOption po ");
			qs.append("left join fetch po.descriptions pod ");
			qs.append("left join fetch pattr.productOptionValue pov ");
			qs.append("left join fetch pov.descriptions povd ");
			qs.append("left join fetch p.relationships pr ");
			// other lefts
			qs.append("left join fetch p.manufacturer manuf ");
			qs.append("left join fetch manuf.descriptions manufd ");
			qs.append("left join fetch p.type type ");


			qs.append("where p.sku=:code and pm.id=:id");

			String hql = qs.toString();
			Query q = this.em.createQuery(hql);

			q.setParameter("code", productCode);
			q.setParameter("id", store.getId());

			return (Product) q.getSingleResult();

		} catch (javax.persistence.NoResultException ers) {
			return null;
		}

	}

	public Product getByFriendlyUrl(MerchantStore store, String seUrl, Locale locale) {

		List<String> regionList = new ArrayList<>();
		regionList.add("*");
		regionList.add(locale.getCountry());

		StringBuilder qs = new StringBuilder();
		qs.append("select distinct p from Product as p ");
		qs.append("join fetch p.availabilities pa ");
		qs.append("join fetch p.descriptions pd ");
		qs.append("join fetch p.merchantStore pm ");
		qs.append("left join fetch pa.prices pap ");
		qs.append("left join fetch pap.descriptions papd ");

		qs.append("left join fetch p.categories categs ");
		qs.append("left join fetch categs.descriptions categsd ");

		// images
		qs.append("left join fetch p.images images ");
		// options
		qs.append("left join fetch p.attributes pattr ");
		qs.append("left join fetch pattr.productOption po ");
		qs.append("left join fetch po.descriptions pod ");
		qs.append("left join fetch pattr.productOptionValue pov ");
		qs.append("left join fetch pov.descriptions povd ");
		qs.append("left join fetch p.relationships pr ");
		// other lefts
		qs.append("left join fetch p.manufacturer manuf ");
		qs.append("left join fetch manuf.descriptions manufd ");
		qs.append("left join fetch p.type type ");
		qs.append("left join fetch p.taxClass tx ");

		// RENTAL
		//qs.append("left join fetch p.owner owner ");

		qs.append("where pa.region in (:lid) ");
		qs.append("and pd.seUrl=:seUrl ");
		qs.append("and p.available=true and p.dateAvailable<=:dt ");
		qs.append("order by pattr.productOptionSortOrder ");

		String hql = qs.toString();
		Query q = this.em.createQuery(hql);

		q.setParameter("lid", regionList);
		q.setParameter("dt", new Date());
		q.setParameter("seUrl", seUrl);

		Product p = null;

		try {
			List<Product> products = q.getResultList();
			if (products.size() > 1) {
				LOGGER.error("Found multiple products for list of criterias with main criteria [" + seUrl + "]");
			}
			// p = (Product)q.getSingleResult();
			p = products.get(0);
		} catch (javax.persistence.NoResultException ignore) {

		}

		return p;

	}

	@Override
	public List<Product> getProductsForLocale(MerchantStore store, Set<Long> categoryIds, Language language,
			Locale locale) {

		ProductList products = this.getProductsListForLocale(store, categoryIds, language, locale, 0, -1);

		return products.getProducts();
	}

	@Override
	public Product getProductForLocale(long productId, Language language, Locale locale) {

		List regionList = new ArrayList();
		regionList.add("*");
		regionList.add(locale.getCountry());

		StringBuilder qs = new StringBuilder();
		qs.append("select distinct p from Product as p ");
		qs.append("join fetch p.availabilities pa ");
		qs.append("join fetch p.descriptions pd ");
		qs.append("join fetch p.merchantStore pm ");
		qs.append("left join fetch pa.prices pap ");
		qs.append("left join fetch pap.descriptions papd ");

		// images
		qs.append("left join fetch p.images images ");
		// options
		qs.append("left join fetch p.attributes pattr ");
		qs.append("left join fetch pattr.productOption po ");
		qs.append("left join fetch po.descriptions pod ");
		qs.append("left join fetch pattr.productOptionValue pov ");
		qs.append("left join fetch pov.descriptions povd ");
		qs.append("left join fetch p.relationships pr ");
		// other lefts
		qs.append("left join fetch p.manufacturer manuf ");
		qs.append("left join fetch manuf.descriptions manufd ");
		qs.append("left join fetch p.type type ");
		qs.append("left join fetch p.taxClass tx ");

		// RENTAL
		//qs.append("left join fetch p.owner owner ");

		qs.append("where p.id=:pid and pa.region in (:lid) ");
		qs.append("and pd.language.id=:lang and papd.language.id=:lang ");
		qs.append("and p.available=true and p.dateAvailable<=:dt ");
		// this cannot be done on child elements from left join
		// qs.append("and pod.languageId=:lang and povd.languageId=:lang");

		String hql = qs.toString();
		Query q = this.em.createQuery(hql);

		q.setParameter("pid", productId);
		q.setParameter("lid", regionList);
		q.setParameter("dt", new Date());
		q.setParameter("lang", language.getId());

		@SuppressWarnings("unchecked")
		List<Product> results = q.getResultList();
		if (results.isEmpty())
			return null;
		else if (results.size() == 1)
			return results.get(0);
		throw new NonUniqueResultException();

	}

	@SuppressWarnings("rawtypes")
	@Override
	public List<Product> getProductsListByCategories(Set categoryIds) {

		// List regionList = new ArrayList();
		// regionList.add("*");
		// regionList.add(locale.getCountry());

		// TODO Test performance
		/**
		 * Testing in debug mode takes a long time with this query running in
		 * normal mode is fine
		 */

		StringBuilder qs = new StringBuilder();
		qs.append("select distinct p from Product as p ");
		qs.append("join fetch p.merchantStore merch ");
		qs.append("join fetch p.availabilities pa ");
		qs.append("left join fetch pa.prices pap ");

		qs.append("join fetch p.descriptions pd ");
		qs.append("join fetch p.categories categs ");

		qs.append("left join fetch pap.descriptions papd ");

		// images
		qs.append("left join fetch p.images images ");

		// options (do not need attributes for listings)
		qs.append("left join fetch p.attributes pattr ");
		qs.append("left join fetch pattr.productOption po ");
		qs.append("left join fetch po.descriptions pod ");
		qs.append("left join fetch pattr.productOptionValue pov ");
		qs.append("left join fetch pov.descriptions povd ");

		// other lefts
		qs.append("left join fetch p.manufacturer manuf ");
		qs.append("left join fetch p.type type ");
		qs.append("left join fetch p.taxClass tx ");

		// RENTAL
		//qs.append("left join fetch p.owner owner ");

		// qs.append("where pa.region in (:lid) ");
		qs.append("where categs.id in (:cid)");

		String hql = qs.toString();
		Query q = this.em.createQuery(hql);

		q.setParameter("cid", categoryIds);

		@SuppressWarnings("unchecked")
		List<Product> products = q.getResultList();

		return products;

	}

	@Override
	public List<Product> getProductsListByCategories(Set<Long> categoryIds, Language language) {

		// List regionList = new ArrayList();
		// regionList.add("*");
		// regionList.add(locale.getCountry());

		// TODO Test performance
		/**
		 * Testing in debug mode takes a long time with this query running in
		 * normal mode is fine
		 */

		StringBuilder qs = new StringBuilder();
		qs.append("select distinct p from Product as p ");
		qs.append("join fetch p.merchantStore merch ");
		qs.append("join fetch p.availabilities pa ");
		qs.append("left join fetch pa.prices pap ");

		qs.append("join fetch p.descriptions pd ");
		qs.append("join fetch p.categories categs ");
		qs.append("left join fetch pap.descriptions papd ");

		// images
		qs.append("left join fetch p.images images ");

		// options (do not need attributes for listings)
		qs.append("left join fetch p.attributes pattr ");
		qs.append("left join fetch pattr.productOption po ");
		qs.append("left join fetch po.descriptions pod ");
		qs.append("left join fetch pattr.productOptionValue pov ");
		qs.append("left join fetch pov.descriptions povd ");

		// other lefts
		qs.append("left join fetch p.manufacturer manuf ");
		qs.append("left join fetch manuf.descriptions manufd ");
		qs.append("left join fetch p.type type ");
		qs.append("left join fetch p.taxClass tx ");

		// RENTAL
		//qs.append("left join fetch p.owner owner ");

		// qs.append("where pa.region in (:lid) ");
		qs.append("where categs.id in (:cid) ");
		// qs.append("and pd.language.id=:lang and papd.language.id=:lang and
		// manufd.language.id=:lang ");
		qs.append("and pd.language.id=:lang and papd.language.id=:lang ");
		qs.append("and p.available=true and p.dateAvailable<=:dt ");

		String hql = qs.toString();
		Query q = this.em.createQuery(hql);

		q.setParameter("cid", categoryIds);
		q.setParameter("lang", language.getId());
		q.setParameter("dt", new Date());

		@SuppressWarnings("unchecked")
		List<Product> products = q.getResultList();

		return products;

	}

	@Override
	public List<Product> getProductsListByIds(Set<Long> productds) {
		StringBuilder qs = new StringBuilder();
		qs.append(productQueryV2());
		qs.append("where p.id in (:pid) ");
		qs.append("and p.available=true and p.dateAvailable<=:dt ");

		String hql = qs.toString();
		Query q = this.em.createQuery(hql);

		q.setParameter("pid", productds);
		q.setParameter("dt", new Date());

		return q.getResultList();
	}

	/**
	 * This query is used for category listings. All collections are not fully
	 * loaded, only the required objects so the listing page can display
	 * everything related to all products
	 */
	@SuppressWarnings({ "rawtypes", "unchecked", "unused" })
	private ProductList getProductsListForLocale(MerchantStore store, Set categoryIds, Language language, Locale locale,
			int first, int max) {

		List regionList = new ArrayList();
		regionList.add(Constants.ALL_REGIONS);
		if (locale != null) {
			regionList.add(locale.getCountry());
		}

		ProductList productList = new ProductList();

		Query countQ = this.em.createQuery(
				"select count(p) from Product as p INNER JOIN p.availabilities pa INNER JOIN p.categories categs where p.merchantStore.id=:mId and categs.id in (:cid) and pa.region in (:lid) and p.available=1 and p.dateAvailable<=:dt");

		countQ.setParameter("cid", categoryIds);
		countQ.setParameter("lid", regionList);
		countQ.setParameter("dt", new Date());
		countQ.setParameter("mId", store.getId());

		Number count = (Number) countQ.getSingleResult();

		productList.setTotalCount(count.intValue());

		if (count.intValue() == 0)
			return productList;

		StringBuilder qs = new StringBuilder();
		qs.append("select p from Product as p ");
		qs.append("join fetch p.merchantStore merch ");
		qs.append("join fetch p.availabilities pa ");
		qs.append("left join fetch pa.prices pap ");

		qs.append("join fetch p.descriptions pd ");
		qs.append("join fetch p.categories categs ");

		// not necessary
		// qs.append("join fetch pap.descriptions papd ");

		// images
		qs.append("left join fetch p.images images ");

		// options (do not need attributes for listings)
		// qs.append("left join fetch p.attributes pattr ");
		// qs.append("left join fetch pattr.productOption po ");
		// qs.append("left join fetch po.descriptions pod ");
		// qs.append("left join fetch pattr.productOptionValue pov ");
		// qs.append("left join fetch pov.descriptions povd ");

		// other lefts
		qs.append("left join fetch p.manufacturer manuf ");
		qs.append("left join fetch manuf.descriptions manufd ");
		qs.append("left join fetch p.type type ");
		qs.append("left join fetch p.taxClass tx ");

		// RENTAL
		//qs.append("left join fetch p.owner owner ");

		// qs.append("where pa.region in (:lid) ");
		qs.append("where p.merchantStore.id=mId and categs.id in (:cid) and pa.region in (:lid) ");
		// qs.append("and p.available=true and p.dateAvailable<=:dt and
		// pd.language.id=:lang and manufd.language.id=:lang");
		qs.append("and p.available=true and p.dateAvailable<=:dt and pd.language.id=:lang");
		qs.append(" order by p.sortOrder asc");

		String hql = qs.toString();
		Query q = this.em.createQuery(hql);

		q.setParameter("cid", categoryIds);
		q.setParameter("lid", regionList);
		q.setParameter("dt", new Date());
		q.setParameter("lang", language.getId());
		q.setParameter("mId", store.getId());

		q.setFirstResult(first);
		if (max > 0) {
			int maxCount = first + max;

			q.setMaxResults(Math.min(maxCount, count.intValue()));
		}

		List<Product> products = q.getResultList();
		productList.setProducts(products);

		return productList;

	}

	/**
	 * This query is used for filtering products based on criterias
	 * Main query for getting product list based on input criteria
	 * ze method
	 *
	 * @param store
	 * @param first
	 * @param max
	 * @return
	 */
	@Override
	public ProductList listByStore(MerchantStore store, Language language, ProductCriteria criteria) {

		ProductList productList = new ProductList();

		StringBuilder countBuilderSelect = new StringBuilder();
		countBuilderSelect.append("select count(distinct p) from Product as p");

		StringBuilder countBuilderWhere = new StringBuilder();
		countBuilderWhere.append(" where p.merchantStore.id=:mId");

		if (!CollectionUtils.isEmpty(criteria.getProductIds())) {
			countBuilderWhere.append(" and p.id in (:pId)");
		}

		countBuilderSelect.append(" inner join p.descriptions pd");
		if (criteria.getLanguage() != null && !criteria.getLanguage().equals("_all")) {
			countBuilderWhere.append(" and pd.language.code=:lang");
		}

		if (!StringUtils.isBlank(criteria.getProductName())) {
			countBuilderWhere.append(" and lower(pd.name) like:nm");
		}

		if (!CollectionUtils.isEmpty(criteria.getCategoryIds())) {
			countBuilderSelect.append(" INNER JOIN p.categories categs");
			countBuilderWhere.append(" and categs.id in (:cid)");
		}

		if (criteria.getManufacturerId() != null) {
			countBuilderSelect.append(" INNER JOIN p.manufacturer manuf");
			countBuilderWhere.append(" and manuf.id = :manufid");
		}
		
		// todo type

		// sku
		if (!StringUtils.isBlank(criteria.getCode())) {
			countBuilderWhere.append(" and lower(p.sku) like :sku");
		}

		// RENTAL
		/**
		if (!StringUtils.isBlank(criteria.getStatus())) {
			countBuilderWhere.append(" and p.rentalStatus = :status");
		}
		**/

		/**
		if (criteria.getOwnerId() != null) {
			countBuilderSelect.append(" INNER JOIN p.owner owner");
			countBuilderWhere.append(" and owner.id = :ownerid");666
		}
		**/
		
		

		/**
		 * attributes / options values
		 * origin allows skipping attributes join in admin
		 */
		//attribute or option values
		if (criteria.getOrigin().equals(ProductCriteria.ORIGIN_SHOP) 
				&& CollectionUtils.isNotEmpty(criteria.getAttributeCriteria()) 
				|| CollectionUtils.isNotEmpty(criteria.getOptionValueIds())) {

			countBuilderSelect.append(" INNER JOIN p.attributes pattr");
			countBuilderSelect.append(" INNER JOIN pattr.productOption po");
			countBuilderSelect.append(" INNER JOIN pattr.productOptionValue pov ");
			countBuilderSelect.append(" INNER JOIN pov.descriptions povd ");
			
			if(CollectionUtils.isNotEmpty(criteria.getAttributeCriteria())) {
				int count = 0;
				for (AttributeCriteria attributeCriteria : criteria.getAttributeCriteria()) {
					if (count == 0) {
						countBuilderWhere.append(" and po.code =:").append(attributeCriteria.getAttributeCode());
						countBuilderWhere.append(" and povd.description like :").append("val").append(count)
								.append(attributeCriteria.getAttributeCode());
					}
					count++;
				}
				if (criteria.getLanguage() != null && !criteria.getLanguage().equals("_all")) {
					countBuilderWhere.append(" and povd.language.code=:lang");
				}
			}
			
			if(CollectionUtils.isNotEmpty(criteria.getOptionValueIds())) {
				countBuilderWhere.append(" and pov.id in (:povid)");
			}
		}

		if (criteria.getAvailable() != null) {
			if (criteria.getAvailable()) {
				countBuilderWhere.append(" and p.available=true and p.dateAvailable<=:dt");
			} else {
				countBuilderWhere.append(" and p.available=false or p.dateAvailable>:dt");
			}
		}
		
		

		Query countQ = this.em.createQuery(countBuilderSelect.toString() + countBuilderWhere.toString());

		countQ.setParameter("mId", store.getId());

		/**/
		if (criteria.getOrigin().equals(ProductCriteria.ORIGIN_SHOP) 
				&& !CollectionUtils.isEmpty(criteria.getCategoryIds())) {
			countQ.setParameter("cid", criteria.getCategoryIds());
		}
		
		/**/
		if(criteria.getOrigin().equals(ProductCriteria.ORIGIN_SHOP) 
				&& CollectionUtils.isNotEmpty(criteria.getOptionValueIds())) {
			countQ.setParameter("povid", criteria.getOptionValueIds());
		}

		if (criteria.getAvailable() != null) {
			countQ.setParameter("dt", new Date());
		}

		if (!StringUtils.isBlank(criteria.getCode())) {
			countQ.setParameter("sku",
					new StringBuilder().append("%").append(criteria.getCode().toLowerCase()).append("%").toString());
		}

		if (criteria.getManufacturerId() != null) {
			countQ.setParameter("manufid", criteria.getManufacturerId());
		}
		

		/**/
		if (criteria.getOrigin().equals(ProductCriteria.ORIGIN_SHOP) 
				&& !CollectionUtils.isEmpty(criteria.getAttributeCriteria())) {
			int count = 0;
			for (AttributeCriteria attributeCriteria : criteria.getAttributeCriteria()) {
				countQ.setParameter(attributeCriteria.getAttributeCode(), attributeCriteria.getAttributeCode());
				countQ.setParameter("val" + count + attributeCriteria.getAttributeCode(),
						"%" + attributeCriteria.getAttributeValue() + "%");
				count++;
			}
		}

		if (criteria.getLanguage() != null && !criteria.getLanguage().equals("_all")) {
			countQ.setParameter("lang", language.getCode());
		}

		if (!StringUtils.isBlank(criteria.getProductName())) {
			countQ.setParameter("nm", new StringBuilder().append("%").append(criteria.getProductName().toLowerCase())
					.append("%").toString());
		}

		if (!CollectionUtils.isEmpty(criteria.getProductIds())) {
			countQ.setParameter("pId", criteria.getProductIds());
		}

		// RENTAL
		/**
		if (!StringUtils.isBlank(criteria.getStatus())) {
			countQ.setParameter("status", criteria.getStatus());
		}
		**/

		if (criteria.getOwnerId() != null) {
			countQ.setParameter("ownerid", criteria.getOwnerId());
		}

		Number count = (Number) countQ.getSingleResult();
		productList.setTotalCount(count.intValue());

		if (count.intValue() == 0)
			return productList;

		StringBuilder qs = new StringBuilder();
		qs.append("select distinct p from Product as p ");
		qs.append("join fetch p.merchantStore merch ");
		qs.append("join fetch p.availabilities pa ");
		qs.append("left join fetch pa.prices pap ");
		qs.append("left join fetch pap.descriptions papd ");

		qs.append("left join fetch p.descriptions pd ");
		qs.append("left join fetch p.categories categs ");
		qs.append("left join fetch categs.descriptions cd ");
		

		// images
		qs.append("left join fetch p.images images ");

		// other lefts
		qs.append("left join fetch p.manufacturer manuf ");
		qs.append("left join fetch manuf.descriptions manufd ");
		qs.append("left join fetch p.type type ");
		qs.append("left join fetch p.taxClass tx ");

		// RENTAL
		//qs.append("left join fetch p.owner owner ");

		/**/
		// attributes
		if (criteria.getOrigin().equals(ProductCriteria.ORIGIN_SHOP) 
				&& !CollectionUtils.isEmpty(criteria.getAttributeCriteria())) {
			qs.append(" inner join p.attributes pattr");
			qs.append(" inner join pattr.productOption po");
			qs.append(" inner join po.descriptions pod");
			qs.append(" inner join pattr.productOptionValue pov ");
			qs.append(" inner join pov.descriptions povd");
		} else if(criteria.getOrigin().equals(ProductCriteria.ORIGIN_SHOP)) {
			qs.append(" left join fetch p.attributes pattr");
			qs.append(" left join fetch pattr.productOption po");
			/** prevent full table scan **/
			qs.append(" left join fetch po.descriptions pod");
			qs.append(" left join fetch pattr.productOptionValue pov");
			qs.append(" left join fetch pov.descriptions povd");
		}
		
		/**
		 * variants
		 */
		if(criteria.getOrigin().equals(ProductCriteria.ORIGIN_SHOP)) {
			qs.append(" left join fetch p.variants pinst ");
			qs.append(" left join fetch pinst.variation pv ");
			qs.append( "left join fetch pv.productOption pvpo ");
			qs.append(" left join fetch pv.productOptionValue pvpov ");
			qs.append(" left join fetch pvpo.descriptions pvpod ");
			qs.append(" left join fetch pvpov.descriptions pvpovd ");
			
			qs.append(" left join fetch pinst.variationValue pvv ");
			qs.append(" left join fetch pvv.productOption pvvpo ");
			qs.append(" left join fetch pvv.productOptionValue pvvpov ");
			qs.append(" left join fetch pvvpo.descriptions povvpod ");
			qs.append(" left join fetch pvpov.descriptions povvpovd ");	
			
			//variant availability and price
			qs.append(" left join fetch pinst.availabilities pinsta ");
			qs.append(" left join fetch pinsta.prices pinstap ");
			qs.append(" left join fetch pinstap.descriptions pinstapdesc ");
			qs.append(" left join fetch pinst.productVariantGroup pinstg ");
			qs.append(" left join fetch pinstg.images pinstgimg ");
			qs.append(" left join fetch pinstgimg.descriptions ");
		//end variants
		}

		/** not required at list level **/
		//qs.append(" left join fetch p.relationships pr");

		qs.append(" where merch.id=:mId");
		if (criteria.getLanguage() != null && !criteria.getLanguage().equals("_all")) {
			qs.append(" and pd.language.code=:lang");
		}

		if (!CollectionUtils.isEmpty(criteria.getProductIds())) {
			qs.append(" and p.id in (:pId)");
		}

		if (!CollectionUtils.isEmpty(criteria.getCategoryIds())) {
			qs.append(" and categs.id in (:cid)");
		}


		if (criteria.getManufacturerId() != null) {
			qs.append(" and manuf.id = :manufid");
		}

		if (criteria.getAvailable() != null) {
			if (criteria.getAvailable()) {
				qs.append(" and p.available=true and p.dateAvailable<=:dt");
			} else {
				qs.append(" and p.available=false and p.dateAvailable>:dt");
			}
		}

		if (!StringUtils.isBlank(criteria.getProductName())) {
			qs.append(" and lower(pd.name) like :nm");
		}

		if (!StringUtils.isBlank(criteria.getCode())) {
			qs.append(" and lower(p.sku) like :sku");
		}

		// RENTAL
		/**
		if (!StringUtils.isBlank(criteria.getStatus())) {
			qs.append(" and p.rentalStatus = :status");
		}
		**/

		/**
		if (criteria.getOwnerId() != null) {
			qs.append(" and owner.id = :ownerid");
		}
		**/

		/**/
		if (criteria.getOrigin().equals(ProductCriteria.ORIGIN_SHOP) 
				&& !CollectionUtils.isEmpty(criteria.getAttributeCriteria())) {
			int cnt = 0;
			for (AttributeCriteria attributeCriteria : criteria.getAttributeCriteria()) {
				qs.append(" and po.code =:").append(attributeCriteria.getAttributeCode());
				qs.append(" and povd.description like :").append("val").append(cnt)
						.append(attributeCriteria.getAttributeCode());
				cnt++;
			}
			if (criteria.getLanguage() != null && !criteria.getLanguage().equals("_all")) {
				qs.append(" and povd.language.code=:lang");
			}

		}
		
		/**/
		if(criteria.getOrigin().equals(ProductCriteria.ORIGIN_SHOP) 
				&& CollectionUtils.isNotEmpty(criteria.getOptionValueIds())) {
			qs.append(" and pov.id in (:povid)");
		}
		
		qs.append(" order by p.sortOrder asc");

		String hql = qs.toString();
		Query q = this.em.createQuery(hql);

		if (criteria.getLanguage() != null && !criteria.getLanguage().equals("_all")) {
			q.setParameter("lang", language.getCode());
		}
		q.setParameter("mId", store.getId());

		if (!CollectionUtils.isEmpty(criteria.getCategoryIds())) {
			q.setParameter("cid", criteria.getCategoryIds());
		}
		
		/**/
		if (criteria.getOrigin().equals(ProductCriteria.ORIGIN_SHOP) 
				&& CollectionUtils.isNotEmpty(criteria.getOptionValueIds())) {
			q.setParameter("povid", criteria.getOptionValueIds());
		}

		if (!CollectionUtils.isEmpty(criteria.getProductIds())) {
			q.setParameter("pId", criteria.getProductIds());
		}

		if (criteria.getAvailable() != null) {
			q.setParameter("dt", new Date());
		}

		if (criteria.getManufacturerId() != null) {
			q.setParameter("manufid", criteria.getManufacturerId());
		}

		if (!StringUtils.isBlank(criteria.getCode())) {
			q.setParameter("sku",
					new StringBuilder().append("%").append(criteria.getCode().toLowerCase()).append("%").toString());
		}

		/**/
		if (criteria.getOrigin().equals(ProductCriteria.ORIGIN_SHOP) 
				&& !CollectionUtils.isEmpty(criteria.getAttributeCriteria())) {
			int cnt = 0;
			for (AttributeCriteria attributeCriteria : criteria.getAttributeCriteria()) {
				q.setParameter(attributeCriteria.getAttributeCode(), attributeCriteria.getAttributeCode());
				q.setParameter("val" + cnt + attributeCriteria.getAttributeCode(),
						"%" + attributeCriteria.getAttributeValue() + "%");
				cnt++;
			}
		}

		// RENTAL
		/**
		if (!StringUtils.isBlank(criteria.getStatus())) {
			q.setParameter("status", criteria.getStatus());
		}
		**/

		/**
		if (criteria.getOwnerId() != null) {
			q.setParameter("ownerid", criteria.getOwnerId());
		}
	    **/

		if (!StringUtils.isBlank(criteria.getProductName())) {
			q.setParameter("nm", new StringBuilder().append("%").append(criteria.getProductName().toLowerCase())
					.append("%").toString());
		}

	    @SuppressWarnings("rawtypes")
	    GenericEntityList entityList = new GenericEntityList();
	    entityList.setTotalCount(count.intValue());

		q = RepositoryHelper.paginateQuery(q, count, entityList, criteria);


		@SuppressWarnings("unchecked")
		List<Product> products = q.getResultList();
		productList.setProducts(products);

		return productList;

	}

	@Override
	public List<Product> listByStore(MerchantStore store) {

		/**
		 * Testing in debug mode takes a long time with this query running in
		 * normal mode is fine
		 */

		StringBuilder qs = new StringBuilder();
		qs.append("select p from Product as p ");
		qs.append("join fetch p.merchantStore merch ");
		qs.append("join fetch p.availabilities pa ");
		qs.append("left join fetch pa.prices pap ");

		qs.append("join fetch p.descriptions pd ");
		qs.append("left join fetch p.categories categs ");

		qs.append("left join fetch pap.descriptions papd ");

		// images
		qs.append("left join fetch p.images images ");

		// options (do not need attributes for listings)
		qs.append("left join fetch p.attributes pattr ");
		qs.append("left join fetch pattr.productOption po ");
		qs.append("left join fetch po.descriptions pod ");
		qs.append("left join fetch pattr.productOptionValue pov ");
		qs.append("left join fetch pov.descriptions povd ");

		// other lefts
		qs.append("left join fetch p.manufacturer manuf ");
		qs.append("left join fetch manuf.descriptions manufd ");
		qs.append("left join fetch p.type type ");
		qs.append("left join fetch p.taxClass tx ");

		// RENTAL
		//qs.append("left join fetch p.owner owner ");

		// qs.append("where pa.region in (:lid) ");
		qs.append("where merch.id=:mid");

		String hql = qs.toString();
		Query q = this.em.createQuery(hql);

		q.setParameter("mid", store.getId());

		@SuppressWarnings("unchecked")
		List<Product> products = q.getResultList();

		return products;

	}

	@Override
	public List<Product> listByTaxClass(TaxClass taxClass) {

		/**
		 * Testing in debug mode takes a long time with this query running in
		 * normal mode is fine
		 */

		StringBuilder qs = new StringBuilder();
		qs.append("select p from Product as p ");
		qs.append("join fetch p.merchantStore merch ");
		qs.append("join fetch p.availabilities pa ");
		qs.append("left join fetch pa.prices pap ");

		qs.append("join fetch p.descriptions pd ");
		qs.append("join fetch p.categories categs ");

		qs.append("left join fetch pap.descriptions papd ");

		// images
		qs.append("left join fetch p.images images ");

		// options (do not need attributes for listings)
		qs.append("left join fetch p.attributes pattr ");
		qs.append("left join fetch pattr.productOption po ");
		qs.append("left join fetch po.descriptions pod ");
		qs.append("left join fetch pattr.productOptionValue pov ");
		qs.append("left join fetch pov.descriptions povd ");

		// other lefts
		qs.append("left join fetch p.manufacturer manuf ");
		qs.append("left join fetch manuf.descriptions manufd ");
		qs.append("left join fetch p.type type ");
		qs.append("left join fetch p.taxClass tx ");

		// RENTAL
		//qs.append("left join fetch p.owner owner ");

		// qs.append("where pa.region in (:lid) ");
		qs.append("where tx.id=:tid");

		String hql = qs.toString();
		Query q = this.em.createQuery(hql);

		q.setParameter("tid", taxClass.getId());

		@SuppressWarnings("unchecked")
		List<Product> products = q.getResultList();

		return products;

	}

	@Deprecated
	private String productQueryV1() {

		StringBuilder qs = new StringBuilder();
		qs.append("select distinct p from Product as p ");
		qs.append("join fetch p.availabilities pa ");
		qs.append("join fetch p.merchantStore merch ");
		qs.append("join fetch p.descriptions pd ");

		qs.append("left join fetch p.categories categs ");
		qs.append("left join fetch categs.descriptions categsd ");

		qs.append("left join fetch pa.prices pap ");
		qs.append("left join fetch pap.descriptions papd ");

		// images
		qs.append("left join fetch p.images images ");
		// options
		qs.append("left join fetch p.attributes pattr ");
		qs.append("left join fetch pattr.productOption po ");
		qs.append("left join fetch po.descriptions pod ");
		qs.append("left join fetch pattr.productOptionValue pov ");
		qs.append("left join fetch pov.descriptions povd ");

		//relations
		qs.append("left join fetch p.relationships pr ");

		// variants
		//qs.append("left join fetch pa.variants pav ");
		//qs.append("left join fetch pav.attribute pavattr ");

		// other lefts
		qs.append("left join fetch p.manufacturer manuf ");
		qs.append("left join fetch manuf.descriptions manufd ");
		qs.append("left join fetch p.type type ");
		qs.append("left join fetch p.taxClass tx ");

		// RENTAL REMOVED
		//qs.append("left join fetch p.owner owner ");
		return qs.toString();

	}
	
	private String productQueryV2() {
		StringBuilder qs = new StringBuilder();
		qs.append("select distinct p from Product as p ");
		qs.append("join fetch p.descriptions pd ");
		qs.append("join fetch p.merchantStore merch ");
		qs.append("left join fetch p.availabilities pavail ");
		qs.append("left join fetch p.type type ");
		// images
		qs.append("left join fetch p.images images ");
		qs.append("left join fetch pavail.prices pavailpr ");
		qs.append("left join fetch pavailpr.descriptions pavailprdesc ");

		qs.append("left join fetch p.categories categs ");
		qs.append("left join fetch categs.descriptions categsd ");

		// options
		qs.append("left join fetch p.attributes pattr ");
		qs.append("left join fetch pattr.productOption po ");
		qs.append("left join fetch po.descriptions pod ");
		qs.append("left join fetch pattr.productOptionValue pov ");
		qs.append("left join fetch pov.descriptions povd ");
		qs.append("left join fetch p.relationships pr ");
		// other lefts
		qs.append("left join fetch p.manufacturer manuf ");
		qs.append("left join fetch manuf.descriptions manufd ");
		qs.append("left join fetch p.type type ");
		
		//variants
		qs.append("left join fetch p.variants pinst ");
		qs.append("left join fetch pinst.variation pv ");
		qs.append("left join fetch pv.productOption pvpo ");
		qs.append("left join fetch pv.productOptionValue pvpov ");
		qs.append("left join fetch pvpo.descriptions pvpod ");
		qs.append("left join fetch pvpov.descriptions pvpovd ");
		
		qs.append("left join fetch pinst.variationValue pvv ");
		qs.append("left join fetch pvv.productOption pvvpo ");
		qs.append("left join fetch pvv.productOptionValue pvvpov ");
		qs.append("left join fetch pvvpo.descriptions povvpod ");
		qs.append("left join fetch pvpov.descriptions povvpovd ");	
		
		//variant availability and price
		qs.append("left join fetch pinst.availabilities pinsta ");
		qs.append("left join fetch pinsta.prices pinstap ");
		qs.append("left join fetch pinstap.descriptions pinstapdesc ");
		qs.append("left join fetch pinst.productVariantGroup pinstg ");
		qs.append("left join fetch pinstg.images pinstgimg ");
		qs.append("left join fetch pinstgimg.descriptions ");
		//end variants
		
		return qs.toString();
	}
	


	@Override
	public Product getById(Long id, MerchantStore store, Language language) {

		try {

			StringBuilder qs = new StringBuilder();
			qs.append("select distinct p from Product as p ");
			qs.append("join fetch p.descriptions pd ");
			qs.append("join fetch p.merchantStore pm ");
			qs.append("left join fetch p.availabilities pavail ");
			qs.append("left join fetch p.type type ");
			qs.append("left join fetch pavail.prices pavailpr ");
			qs.append("left join fetch pavailpr.descriptions pavailprdesc ");

			qs.append("left join fetch p.categories categs ");
			qs.append("left join fetch categs.descriptions categsd ");

			// options
			qs.append("left join fetch p.attributes pattr ");
			qs.append("left join fetch pattr.productOption po ");
			qs.append("left join fetch po.descriptions pod ");
			qs.append("left join fetch pattr.productOptionValue pov ");
			qs.append("left join fetch pov.descriptions povd ");
			qs.append("left join fetch p.relationships pr ");
			// other lefts
			qs.append("left join fetch p.manufacturer manuf ");
			qs.append("left join fetch manuf.descriptions manufd ");
			qs.append("left join fetch p.type type ");
			
			//variants
			qs.append("left join fetch p.variants pinst ");
			qs.append("left join fetch pinst.variation pv ");
			qs.append("left join fetch pv.productOption pvpo ");
			qs.append("left join fetch pv.productOptionValue pvpov ");
			qs.append("left join fetch pvpo.descriptions pvpod ");
			qs.append("left join fetch pvpov.descriptions pvpovd ");
			
			qs.append("left join fetch pinst.variationValue pvv ");
			qs.append("left join fetch pvv.productOption pvvpo ");
			qs.append("left join fetch pvv.productOptionValue pvvpov ");
			qs.append("left join fetch pvvpo.descriptions povvpod ");
			qs.append("left join fetch pvpov.descriptions povvpovd ");	
			
			//variant availability and price
			qs.append("left join fetch pinst.availabilities pinsta ");
			qs.append("left join fetch pinsta.prices pinstap ");
			qs.append("left join fetch pinstap.descriptions pinstapdesc ");
			qs.append("left join fetch pinst.productVariantGroup pinstg ");
			qs.append("left join fetch pinstg.images pinstgimg ");
			qs.append("left join fetch pinstgimg.descriptions ");
			//end variants

			qs.append("where p.id=:productId and pm.id=:id");

			String hql = qs.toString();
			Query q = this.em.createQuery(hql);

			q.setParameter("productId", id);
			q.setParameter("id", store.getId());

			return (Product) q.getSingleResult();

		} catch (javax.persistence.NoResultException ers) {
			return null;
		}
		
	}

}
