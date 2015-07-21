package com.salesmanager.test.utils;

import java.util.Map;

import javax.persistence.spi.PersistenceUnitInfo;

import org.hibernate.ejb.Ejb3Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import com.salesmanager.test.core.AbstractSalesManagerCoreTestCase;




public class ExportSchema extends AbstractSalesManagerCoreTestCase {

	@Autowired
	private LocalContainerEntityManagerFactoryBean entityManagerFactory;
	
	/**
	 * Create a sql script with create tables, indexes and fk
	 * from annotated POJOs
	 * You must copy resources/META-INF/sm-persistence.xml to persistence.xml
	 * before you run this class
	 * Once generated, remove the persistence.xml copy from META-INF
	 * @throws Exception
	 */
	@Test
	@Ignore
	public void createSchema() throws Exception {
		

	    PersistenceUnitInfo persistenceUnitInfo = entityManagerFactory.getPersistenceUnitInfo();
	    Map jpaPropertyMap = entityManagerFactory.getJpaPropertyMap();

	    @SuppressWarnings("deprecation")
		org.hibernate.cfg.Configuration configuration = new Ejb3Configuration().configure( persistenceUnitInfo, jpaPropertyMap ).getHibernateConfiguration();

	    SchemaExport schema = new SchemaExport(configuration);
	    schema.setOutputFile("c:/schema.sql");
	    schema.create(true, false);

		
	}

}
