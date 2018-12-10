package com.salesmanager.core.business.repositories.merchant;

import com.salesmanager.core.model.merchant.MerchantStore;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

public class MerchantRepositoryImpl implements MerchantRepositoryCustom {


  @PersistenceContext
  private EntityManager em;

  @Override
  public MerchantStore getById(Integer MerchantStoreId) {

    try {

      StringBuilder qs = new StringBuilder();

      qs.append("select m from MerchantStore m ");
      qs.append("left join fetch m.country mc ");
      qs.append("left join fetch m.currency mc ");
      qs.append("left join fetch m.zone mz ");
      qs.append("left join fetch m.defaultLanguage md ");
      qs.append("left join fetch m.languages mls ");
      qs.append("where m.id = :id");

      String hql = qs.toString();
      Query q = this.em.createQuery(hql);

      q.setParameter("id", MerchantStoreId);

      MerchantStore p = (MerchantStore) q.getSingleResult();

      return p;
    } catch (javax.persistence.NoResultException ers) {
      return null;
    }


  }

  @Override
  public MerchantStore findByCode(String code) {

    try {

      StringBuilder qs = new StringBuilder();

      qs.append("select m from MerchantStore m ");
      qs.append("left join fetch m.country mc ");
      qs.append("left join fetch m.currency mc ");
      qs.append("left join fetch m.zone mz ");
      qs.append("left join fetch m.defaultLanguage md ");
      qs.append("left join fetch m.languages mls ");
      qs.append("where m.code = :code ");

      String hql = qs.toString();
      Query q = this.em.createQuery(hql);

      q.setParameter("code", code);

      MerchantStore p = (MerchantStore) q.getSingleResult();

      return p;
    } catch (javax.persistence.NoResultException ers) {
      return null;
    }

  }
}
