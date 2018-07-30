package com.salesmanager.core.business.services.payments;

import com.salesmanager.core.business.exception.ServiceException;
import com.salesmanager.core.business.services.common.generic.SalesManagerEntityService;
import com.salesmanager.core.model.order.Order;
import com.salesmanager.core.model.payments.Transaction;

import java.util.Date;
import java.util.List;


public interface TransactionService extends SalesManagerEntityService<Long, Transaction> {

    /**
     * Obtain a previous transaction that has type authorize for a give order
     *
     */
    Transaction getCapturableTransaction(Order order) throws ServiceException;

    Transaction getRefundableTransaction(Order order) throws ServiceException;

    List<Transaction> listTransactions(Order order) throws ServiceException;

    List<Transaction> listTransactions(Date startDate, Date endDate) throws ServiceException;


}