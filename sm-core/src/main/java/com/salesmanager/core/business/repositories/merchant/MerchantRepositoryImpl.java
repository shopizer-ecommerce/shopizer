package com.salesmanager.core.business.repositories.merchant;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.salesmanager.core.business.exception.ServiceException;
import com.salesmanager.core.business.utils.RepositoryHelper;
import com.salesmanager.core.model.common.GenericEntityList;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.merchant.MerchantStoreCriteria;

public class MerchantRepositoryImpl implements MerchantRepositoryCustom {

  @PersistenceContext
  private EntityManager em;

  private static final Logger LOGGER = LoggerFactory.getLogger(MerchantRepositoryImpl.class);
  

  @SuppressWarnings({"rawtypes", "unchecked"})
  @Override
  public GenericEntityList listByCriteria(MerchantStoreCriteria criteria) throws ServiceException {
    try {
      StringBuilder req = new StringBuilder();
      req.append(
          "select distinct m from MerchantStore m left join fetch m.country mc left join fetch m.parent cp left join fetch m.currency mc left join fetch m.zone mz left join fetch m.defaultLanguage md left join fetch m.languages mls");
      StringBuilder countBuilder = new StringBuilder();
      countBuilder.append("select count(distinct m) from MerchantStore m");
      if (criteria.getCode() != null) {
        req.append("  where lower(m.code) like:code");
        countBuilder.append(" where lower(m.code) like:code");
      }
      if (criteria.getName() != null) {
        if (criteria.getCode() == null) {
          req.append(" where");
          countBuilder.append(" where ");
        } else {
          req.append(" or");
          countBuilder.append(" or ");
        }
        req.append(" lower(m.storename) like:name");
        countBuilder.append(" lower(m.storename) like:name");
      }

      if (!StringUtils.isBlank(criteria.getCriteriaOrderByField())) {
        req.append(" order by m.").append(criteria.getCriteriaOrderByField()).append(" ")
                .append(criteria.getOrderBy().name().toLowerCase());
      }

      Query countQ = this.em.createQuery(countBuilder.toString());

      String hql = req.toString();
      Query q = this.em.createQuery(hql);

      if (criteria.getCode() != null) {
        countQ.setParameter("code", "%" + criteria.getCode().toLowerCase() + "%");
        q.setParameter("code", "%" + criteria.getCode().toLowerCase() + "%");
      }
      if (criteria.getName() != null) {
        countQ.setParameter("name", "%" + criteria.getCode().toLowerCase() + "%");
        q.setParameter("name", "%" + criteria.getCode().toLowerCase() + "%");
      }


      Number count = (Number) countQ.getSingleResult();

      GenericEntityList entityList = new GenericEntityList();
      entityList.setTotalCount(count.intValue());
      
      q = RepositoryHelper.paginateQuery(q, count, entityList, criteria);


      List<MerchantStore> stores = q.getResultList();
      entityList.setList(stores);


      return entityList;



    } catch (javax.persistence.NoResultException ers) {
    } catch (Exception e) {
      LOGGER.error(e.getMessage());
      throw new ServiceException(e);
    }
    return null;
  }

}
