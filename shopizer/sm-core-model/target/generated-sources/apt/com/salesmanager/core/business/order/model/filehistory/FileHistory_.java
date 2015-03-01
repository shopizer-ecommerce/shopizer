package com.salesmanager.core.business.order.model.filehistory;

import com.salesmanager.core.business.merchant.model.MerchantStore;
import java.util.Date;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(FileHistory.class)
public abstract class FileHistory_ {

	public static volatile SingularAttribute<FileHistory, Date> accountedDate;
	public static volatile SingularAttribute<FileHistory, Long> fileId;
	public static volatile SingularAttribute<FileHistory, Long> id;
	public static volatile SingularAttribute<FileHistory, Integer> filesize;
	public static volatile SingularAttribute<FileHistory, MerchantStore> store;
	public static volatile SingularAttribute<FileHistory, Integer> downloadCount;
	public static volatile SingularAttribute<FileHistory, Date> dateDeleted;
	public static volatile SingularAttribute<FileHistory, Date> dateAdded;

}

