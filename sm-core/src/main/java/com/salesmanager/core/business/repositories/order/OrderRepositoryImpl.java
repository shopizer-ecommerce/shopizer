package com.salesmanager.core.business.repositories.order;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.lang3.StringUtils;

import com.salesmanager.core.business.utils.RepositoryHelper;
import com.salesmanager.core.model.common.CriteriaOrderBy;
import com.salesmanager.core.model.common.GenericEntityList;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.order.OrderCriteria;
import com.salesmanager.core.model.order.OrderList;
import com.salesmanager.core.model.order.orderstatus.OrderStatus;


public class OrderRepositoryImpl implements OrderRepositoryCustom {

	
    @PersistenceContext
    private EntityManager em;
    
    /**
     * @deprecated
     */
	@SuppressWarnings("unchecked")
	@Override
	public OrderList listByStore(MerchantStore store, OrderCriteria criteria) {
		

		OrderList orderList = new OrderList();
		StringBuilder countBuilderSelect = new StringBuilder();
		StringBuilder objectBuilderSelect = new StringBuilder();
		
		String orderByCriteria = " order by o.id desc";
		
		if(criteria.getOrderBy()!=null) {
			if(CriteriaOrderBy.ASC.name().equals(criteria.getOrderBy().name())) {
				orderByCriteria = " order by o.id asc";
			}
		}
		
		String countBaseQuery = "select count(o) from Order as o";
		String baseQuery = "select o from Order as o left join fetch o.orderTotal ot left join fetch o.orderProducts op left join fetch o.orderAttributes oa left join fetch op.orderAttributes opo left join fetch op.prices opp";
		countBuilderSelect.append(countBaseQuery);
		objectBuilderSelect.append(baseQuery);

		
		
		StringBuilder countBuilderWhere = new StringBuilder();
		StringBuilder objectBuilderWhere = new StringBuilder();
		String whereQuery = " where o.merchant.id=:mId";
		countBuilderWhere.append(whereQuery);
		objectBuilderWhere.append(whereQuery);
		

		if(!StringUtils.isBlank(criteria.getCustomerName())) {
			String nameQuery =" and o.billing.firstName like:nm or o.billing.lastName like:nm";
			countBuilderWhere.append(nameQuery);
			objectBuilderWhere.append(nameQuery);
		}
		
		if(!StringUtils.isBlank(criteria.getPaymentMethod())) {
			String paymentQuery =" and o.paymentModuleCode like:pm";
			countBuilderWhere.append(paymentQuery);
			objectBuilderWhere.append(paymentQuery);
		}
		
		if(criteria.getCustomerId()!=null) {
			String customerQuery =" and o.customerId =:cid";
			countBuilderWhere.append(customerQuery);
			objectBuilderWhere.append(customerQuery);
		}
		
		objectBuilderWhere.append(orderByCriteria);
		

		//count query
		Query countQ = em.createQuery(
				countBuilderSelect.toString() + countBuilderWhere.toString());
		
		//object query
		Query objectQ = em.createQuery(
				objectBuilderSelect.toString() + objectBuilderWhere.toString());

		countQ.setParameter("mId", store.getId());
		objectQ.setParameter("mId", store.getId());
		

		if(!StringUtils.isBlank(criteria.getCustomerName())) {
			String nameParam = new StringBuilder().append("%").append(criteria.getCustomerName()).append("%").toString();
			countQ.setParameter("nm",nameParam);
			objectQ.setParameter("nm",nameParam);
		}
		
		if(!StringUtils.isBlank(criteria.getPaymentMethod())) {
			String payementParam = new StringBuilder().append("%").append(criteria.getPaymentMethod()).append("%").toString();
			countQ.setParameter("pm",payementParam);
			objectQ.setParameter("pm",payementParam);
		}
		
		if(criteria.getCustomerId()!=null) {
			countQ.setParameter("cid", criteria.getCustomerId());
			objectQ.setParameter("cid",criteria.getCustomerId());
		}
		

		Number count = (Number) countQ.getSingleResult();

		orderList.setTotalCount(count.intValue());
		
        if(count.intValue()==0)
        	return orderList;
        
		//TO BE USED
        int max = criteria.getMaxCount();
        int first = criteria.getStartIndex();
        
        objectQ.setFirstResult(first);
        
        
        
    	if(max>0) {
    			int maxCount = first + max;

			objectQ.setMaxResults(Math.min(maxCount, count.intValue()));
    	}
		
    	orderList.setOrders(objectQ.getResultList());

		return orderList;
		
		
	}

	@Override
	public OrderList listOrders(MerchantStore store, OrderCriteria criteria) {
		OrderList orderList = new OrderList();
		StringBuilder countBuilderSelect = new StringBuilder();
		StringBuilder objectBuilderSelect = new StringBuilder();

		String orderByCriteria = " order by o.id desc";

		if(criteria.getOrderBy()!=null) {
			if(CriteriaOrderBy.ASC.name().equals(criteria.getOrderBy().name())) {
				orderByCriteria = " order by o.id asc";
			}
		}

		
		String baseQuery = "select o from Order as o left join fetch o.delivery.country left join fetch o.delivery.zone left join fetch o.billing.country left join fetch o.billing.zone left join fetch o.orderTotal ot left join fetch o.orderProducts op left join fetch o.orderAttributes oa left join fetch op.orderAttributes opo left join fetch op.prices opp";
		String countBaseQuery = "select count(o) from Order as o";
		
		countBuilderSelect.append(countBaseQuery);
		objectBuilderSelect.append(baseQuery);

		StringBuilder objectBuilderWhere = new StringBuilder();

		String storeQuery =" where o.merchant.code=:mCode";
		objectBuilderWhere.append(storeQuery);
		countBuilderSelect.append(storeQuery);
		
		if(!StringUtils.isEmpty(criteria.getCustomerName())) {
			String nameQuery =  " and o.billing.firstName like:name or o.billing.lastName like:name";
			objectBuilderWhere.append(nameQuery);
			countBuilderSelect.append(nameQuery);
		}
		
		if(!StringUtils.isEmpty(criteria.getEmail())) {
			String nameQuery =  " and o.customerEmailAddress like:email";
			objectBuilderWhere.append(nameQuery);
			countBuilderSelect.append(nameQuery);
		}
		
		//id
		if(criteria.getId() != null) {
			String nameQuery =  " and str(o.id) like:id";
			objectBuilderWhere.append(nameQuery);
			countBuilderSelect.append(nameQuery);
		}
		
		//phone
		if(!StringUtils.isEmpty(criteria.getCustomerPhone())) {
			String nameQuery =  " and o.billing.telephone like:phone or o.delivery.telephone like:phone";
			objectBuilderWhere.append(nameQuery);
			countBuilderSelect.append(nameQuery);
		}
		
		//status
		if(!StringUtils.isEmpty(criteria.getStatus())) {
			String nameQuery =  " and o.status =:status";
			objectBuilderWhere.append(nameQuery);
			countBuilderSelect.append(nameQuery);
		}
	
		objectBuilderWhere.append(orderByCriteria);

		//count query
		Query countQ = em.createQuery(
				countBuilderSelect.toString());

		//object query
		Query objectQ = em.createQuery(
				objectBuilderSelect.toString() + objectBuilderWhere.toString());
		
		//customer name
		if(!StringUtils.isEmpty(criteria.getCustomerName())) {
			countQ.setParameter("name", like(criteria.getCustomerName()));
			objectQ.setParameter("name", like(criteria.getCustomerName()));
		}
		
		//email
		if(!StringUtils.isEmpty(criteria.getEmail())) {
			countQ.setParameter("email", like(criteria.getEmail()));
			objectQ.setParameter("email", like(criteria.getEmail()));			
		}
		
		//id
		if(criteria.getId() != null) {
			countQ.setParameter("id", like(String.valueOf(criteria.getId())));
			objectQ.setParameter("id", like(String.valueOf(criteria.getId())));
		}
		
		//phone
		if(!StringUtils.isEmpty(criteria.getCustomerPhone())) {
			countQ.setParameter("phone", like(criteria.getCustomerPhone()));
			objectQ.setParameter("phone", like(criteria.getCustomerPhone()));
		}
		
		//status
		if(!StringUtils.isEmpty(criteria.getStatus())) {
			countQ.setParameter("status", OrderStatus.valueOf(criteria.getStatus().toUpperCase()));
			objectQ.setParameter("status", OrderStatus.valueOf(criteria.getStatus().toUpperCase()));
		}
		

		countQ.setParameter("mCode", store.getCode());
		objectQ.setParameter("mCode", store.getCode());


		Number count = (Number) countQ.getSingleResult();

		if(count.intValue()==0)
			return orderList;

	    @SuppressWarnings("rawtypes")
		GenericEntityList entityList = new GenericEntityList();
	    entityList.setTotalCount(count.intValue());
		
		objectQ = RepositoryHelper.paginateQuery(objectQ, count, entityList, criteria);
		
		//TODO use GenericEntityList

		orderList.setTotalCount(entityList.getTotalCount());
		orderList.setTotalPages(entityList.getTotalPages());

		orderList.setOrders(objectQ.getResultList());

		return orderList;
	}
	
	private String like(String q) {
		return '%' + q + '%';
	}


}
