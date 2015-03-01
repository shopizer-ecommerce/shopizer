package com.salesmanager.core.business.order.model.payment;

import com.salesmanager.core.business.payments.model.CreditCardType;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(CreditCard.class)
public abstract class CreditCard_ {

	public static volatile SingularAttribute<CreditCard, String> ccCvv;
	public static volatile SingularAttribute<CreditCard, String> ccNumber;
	public static volatile SingularAttribute<CreditCard, String> ccExpires;
	public static volatile SingularAttribute<CreditCard, String> ccOwner;
	public static volatile SingularAttribute<CreditCard, CreditCardType> cardType;

}

