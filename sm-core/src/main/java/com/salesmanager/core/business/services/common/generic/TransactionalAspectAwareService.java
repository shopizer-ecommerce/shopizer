package com.salesmanager.core.business.services.common.generic;

/**
 * Indique que le service doit être rendu transactionnel via un aspect.
 * 
 * Cela permet de simplifier la configuration Spring de la partie transactionnelle car
 * il suffit alors de déclarer le pointcut de l'aspect sur
 * this(com.salesmanager.core.business.services.common.generic.ITransactionalAspectAwareService)
 */
public interface TransactionalAspectAwareService {

}
