package com.salesmanager.core.business.generic.service;

/**
 * Indique que le service doit être rendu transactionnel via un aspect.
 * 
 * Cela permet de simplifier la configuration Spring de la partie transactionnelle car
 * il suffit alors de déclarer le pointcut de l'aspect sur
 * this(com.salesmanager.core.business.generic.service.ITransactionalAspectAwareService)
 */
public interface TransactionalAspectAwareService {

}
