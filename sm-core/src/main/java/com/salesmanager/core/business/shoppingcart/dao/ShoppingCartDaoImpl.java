package com.salesmanager.core.business.shoppingcart.dao;

import java.util.List;

import javax.persistence.NonUniqueResultException;

import org.springframework.stereotype.Repository;

import com.mysema.query.jpa.JPQLQuery;
import com.mysema.query.jpa.impl.JPAQuery;
import com.salesmanager.core.business.customer.model.Customer;
import com.salesmanager.core.business.generic.dao.SalesManagerEntityDaoImpl;
import com.salesmanager.core.business.merchant.model.MerchantStore;
import com.salesmanager.core.business.shoppingcart.model.QShoppingCart;
import com.salesmanager.core.business.shoppingcart.model.QShoppingCartItem;
import com.salesmanager.core.business.shoppingcart.model.ShoppingCart;

@Repository("shoppingCartDao")
public class ShoppingCartDaoImpl extends SalesManagerEntityDaoImpl<Long, ShoppingCart>
		implements ShoppingCartDao {


	@Override
	public ShoppingCart getById(final Long id) {

		try {
			QShoppingCart qShoppingCart = QShoppingCart.shoppingCart;
			QShoppingCartItem qShoppingCartItem = QShoppingCartItem.shoppingCartItem;

			JPQLQuery query = new JPAQuery (getEntityManager());

			query.from(qShoppingCart)
				.leftJoin(qShoppingCart.lineItems, qShoppingCartItem).fetch()
				.leftJoin(qShoppingCartItem.attributes).fetch()
				.leftJoin(qShoppingCart.merchantStore).fetch()
				.where(qShoppingCart.id.eq(id));

			return query.uniqueResult(qShoppingCart);
		} catch(javax.persistence.NoResultException ers) {
			return null;
		}

	}

	@Override
	public ShoppingCart getShoppingCartById(final Long id) {

			QShoppingCart qShoppingCart = QShoppingCart.shoppingCart;

			JPQLQuery query = new JPAQuery (getEntityManager());

			query.from(qShoppingCart)
				.leftJoin(qShoppingCart.merchantStore).fetch()
				.where(qShoppingCart.id.eq(id));

			return query.uniqueResult(qShoppingCart);

	}

	@Override
	public ShoppingCart getShoppingCartByCode(final String code) {

			QShoppingCart qShoppingCart = QShoppingCart.shoppingCart;

			JPQLQuery query = new JPAQuery (getEntityManager());

			query.from(qShoppingCart)
				.leftJoin(qShoppingCart.merchantStore).fetch()
				.where(qShoppingCart.shoppingCartCode.eq(code));

			List<ShoppingCart> results = query.list(qShoppingCart);
	        if (results.isEmpty()) return null;
	        
	        else if (results.size() >= 1) return results.get(0);
	        return null;


	}

	@Override
	public ShoppingCart getById(final Long id, final MerchantStore store) {

		try {

			QShoppingCart qShoppingCart = QShoppingCart.shoppingCart;
			QShoppingCartItem qShoppingCartItem = QShoppingCartItem.shoppingCartItem;

			JPQLQuery query = new JPAQuery (getEntityManager());

			query.from(qShoppingCart)
				.leftJoin(qShoppingCart.lineItems, qShoppingCartItem).fetch()
				.leftJoin(qShoppingCartItem.attributes).fetch()
				.leftJoin(qShoppingCart.merchantStore).fetch()
				.where(qShoppingCart.id.eq(id)
						.and(qShoppingCart.merchantStore.id.eq(store.getId())));

			return query.uniqueResult(qShoppingCart);

		} catch(javax.persistence.NoResultException ers) {
			return null;
		}

	}

	@Override
	public ShoppingCart getByCode(final String code, final MerchantStore store) {


			QShoppingCart qShoppingCart = QShoppingCart.shoppingCart;
			QShoppingCartItem qShoppingCartItem = QShoppingCartItem.shoppingCartItem;

			JPQLQuery query = new JPAQuery (getEntityManager());

			query.from(qShoppingCart)
				.leftJoin(qShoppingCart.lineItems, qShoppingCartItem).fetch()
				.leftJoin(qShoppingCartItem.attributes).fetch()
				.leftJoin(qShoppingCart.merchantStore).fetch()
				.where(qShoppingCart.shoppingCartCode.eq(code)
						.and(qShoppingCart.merchantStore.id.eq(store.getId())));

			List<ShoppingCart> results = query.list(qShoppingCart);
	        if (results.isEmpty()) return null;
	        
	        else if (results.size() >= 1) return results.get(0);
	        return null;

	}

	@Override
	public ShoppingCart getByCustomer(final Customer customer) {

			QShoppingCart qShoppingCart = QShoppingCart.shoppingCart;
			QShoppingCartItem qShoppingCartItem = QShoppingCartItem.shoppingCartItem;

			JPQLQuery query = new JPAQuery (getEntityManager());

			query.from(qShoppingCart)
				.leftJoin(qShoppingCart.lineItems, qShoppingCartItem).fetch()
				.leftJoin(qShoppingCartItem.attributes).fetch()
				.leftJoin(qShoppingCart.merchantStore).fetch()
				.where(qShoppingCart.customerId.eq(customer.getId()));
			
			List<ShoppingCart> results = query.list(qShoppingCart);
	        if (results.isEmpty()) return null;
	        
	        else if (results.size() >= 1) return results.get(0);
	        return null;

	}

    @Override
    public void removeShoppingCart( final ShoppingCart cart )
    {
            ShoppingCart shoppingCart=getEntityManager().merge( cart );
            getEntityManager().remove( shoppingCart );
   }



}
