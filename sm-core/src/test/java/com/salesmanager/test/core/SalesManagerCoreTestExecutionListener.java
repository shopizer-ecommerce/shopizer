package com.salesmanager.test.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.TestContext;
import org.springframework.test.context.support.AbstractTestExecutionListener;

import com.salesmanager.core.business.generic.util.EntityManagerUtils;

public class SalesManagerCoreTestExecutionListener extends AbstractTestExecutionListener {
	
	@Autowired
	private EntityManagerUtils entityManagerUtils;
	
	@Override
	public void beforeTestClass(TestContext testContext) throws Exception {
		testContext.getApplicationContext().getAutowireCapableBeanFactory().autowireBean(this);
	}

	@Override
	public void beforeTestMethod(TestContext testContext) throws Exception {
		entityManagerUtils.openEntityManager();
	}

	@Override
	public void afterTestMethod(TestContext testContext) throws Exception {
		entityManagerUtils.closeEntityManager();
	}

}
