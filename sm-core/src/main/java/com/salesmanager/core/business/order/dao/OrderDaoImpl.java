package com.salesmanager.core.business.order.dao;

import javax.persistence.Query;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.mysema.query.BooleanBuilder;
import com.mysema.query.jpa.JPQLQuery;
import com.mysema.query.jpa.impl.JPAQuery;
import com.salesmanager.core.business.common.model.CriteriaOrderBy;
import com.salesmanager.core.business.generic.dao.SalesManagerEntityDaoImpl;
import com.salesmanager.core.business.merchant.model.MerchantStore;
import com.salesmanager.core.business.order.model.Order;
import com.salesmanager.core.business.order.model.OrderCriteria;
import com.salesmanager.core.business.order.model.OrderList;
import com.salesmanager.core.business.order.model.QOrder;
import com.salesmanager.core.business.order.model.QOrderTotal;
import com.salesmanager.core.business.order.model.orderproduct.QOrderProduct;
import com.salesmanager.core.business.order.model.orderproduct.QOrderProductAttribute;
import com.salesmanager.core.business.order.model.orderstatus.QOrderStatusHistory;

@Repository("orderDao")
public class OrderDaoImpl  extends SalesManagerEntityDaoImpl<Long, Order> implements OrderDao {

	public OrderDaoImpl() {
		super();
	}
	
	@Override
	public Order getById(Long id) {
		
		
		QOrder qOrder = QOrder.order;
		QOrderProduct qOrderProduct = QOrderProduct.orderProduct;
		QOrderTotal qOrderTotal = QOrderTotal.orderTotal;
		QOrderStatusHistory qOrderStatusHistory = QOrderStatusHistory.orderStatusHistory;
		QOrderProductAttribute qOrderProductAttribute = QOrderProductAttribute.orderProductAttribute;
		//OrderAccount not loaded for now
		
		JPQLQuery query = new JPAQuery (getEntityManager());
		
		query.from(qOrder)
			.join(qOrder.orderProducts, qOrderProduct).fetch()
			.join(qOrder.orderTotal, qOrderTotal).fetch()
			.leftJoin(qOrder.orderHistory, qOrderStatusHistory).fetch()
			.leftJoin(qOrderProduct.downloads).fetch()
			.leftJoin(qOrderProduct.orderAttributes,qOrderProductAttribute).fetch()
			.leftJoin(qOrderProduct.prices).fetch()
			.where(qOrder.id.eq(id));

		
		return query.uniqueResult(qOrder);
		
	}



	@Override
	public OrderList listByStore(MerchantStore store, OrderCriteria criteria) {

		OrderList orderList = new OrderList();
		StringBuilder countBuilderSelect = new StringBuilder();
		countBuilderSelect.append("select count(o) from Order as o");
		
		StringBuilder countBuilderWhere = new StringBuilder();
		countBuilderWhere.append(" where o.merchant.id=:mId");

		if(!StringUtils.isBlank(criteria.getCustomerName())) {
			countBuilderWhere.append(" and o.billing.firstName like:nm");
			countBuilderWhere.append(" or o.billing.lastName like:nm");
		}
		
		if(!StringUtils.isBlank(criteria.getPaymentMethod())) {
			countBuilderWhere.append(" and o.paymentModuleCode like:pm");
		}
		
		if(criteria.getCustomerId()!=null) {
			countBuilderWhere.append(" and o.customerId =:cid");
		}

		Query countQ = super.getEntityManager().createQuery(
				countBuilderSelect.toString() + countBuilderWhere.toString());

		countQ.setParameter("mId", store.getId());
		
		if(!StringUtils.isBlank(criteria.getCustomerName())) {
			countQ.setParameter("nm",new StringBuilder().append("%").append(criteria.getCustomerName()).append("%").toString());
		}
		
		if(!StringUtils.isBlank(criteria.getPaymentMethod())) {
			countQ.setParameter("pm",new StringBuilder().append("%").append(criteria.getPaymentMethod()).append("%").toString());
		}
		
		if(criteria.getCustomerId()!=null) {
			countQ.setParameter("cid", criteria.getCustomerId());
		}
		


		Number count = (Number) countQ.getSingleResult ();

		orderList.setTotalCount(count.intValue());
		
        if(count.intValue()==0)
        	return orderList;
		
		
		
		QOrder qOrder = QOrder.order;
		QOrderProduct qOrderProduct = QOrderProduct.orderProduct;
		QOrderTotal qOrderTotal = QOrderTotal.orderTotal;
		QOrderStatusHistory qOrderStatusHistory = QOrderStatusHistory.orderStatusHistory;
		QOrderProductAttribute qOrderProductAttribute = QOrderProductAttribute.orderProductAttribute;
		//OrderAccount not loaded for now
		
		JPQLQuery query = new JPAQuery (getEntityManager());
		
		query.from(qOrder)
			.join(qOrder.orderProducts, qOrderProduct).fetch()
			.join(qOrder.orderTotal, qOrderTotal).fetch()
			.leftJoin(qOrder.orderHistory, qOrderStatusHistory).fetch()
			.leftJoin(qOrderProduct.downloads).fetch()
			.leftJoin(qOrderProduct.orderAttributes,qOrderProductAttribute).fetch()
			.leftJoin(qOrderProduct.prices).fetch();
			
			query.where(qOrder.merchant.id.eq(store.getId()))
			.orderBy(qOrder.id.desc());
			BooleanBuilder pBuilder = null;

		if(!StringUtils.isBlank(criteria.getCustomerName())) {
			if(pBuilder==null) {
				pBuilder = new BooleanBuilder();
			}			pBuilder.and(qOrder.billing.firstName.like(new StringBuilder().append("%").append(criteria.getCustomerName()).append("%").toString())
					.or(qOrder.billing.lastName.like(new StringBuilder().append("%").append(criteria.getCustomerName()).append("%").toString())));
		}
		
		if(!StringUtils.isBlank(criteria.getPaymentMethod())) {
			if(pBuilder==null) {
				pBuilder = new BooleanBuilder();
			}
			pBuilder.and(qOrder.paymentModuleCode.stringValue().like(new StringBuilder().append("%").append(criteria.getPaymentMethod()).append("%").toString()));
		}
		
		if(criteria.getCustomerId()!=null) {
			if(pBuilder==null) {
				pBuilder = new BooleanBuilder();
			}
			pBuilder.and(qOrder.customerId.eq(criteria.getCustomerId()));
		}
		
		if(pBuilder!=null) {
			query.where(pBuilder);
		}
		
		if(criteria.getOrderBy().name().equals(CriteriaOrderBy.ASC)) {
			query.orderBy(qOrder.datePurchased.asc());
		} else {
			query.orderBy(qOrder.datePurchased.desc());
		}
		
		if(criteria.getMaxCount()>0) {
			query.limit(criteria.getMaxCount());
			query.offset(criteria.getStartIndex());
		}
		
		
		orderList.setOrders(query.list(qOrder));

		return orderList;
		
	}
}
