package com.salesmanager.core.business.configuration;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
public class HibernateConfigurations {

    @Value("${hibernate.hbm2ddl.auto}")
    private String hbm2ddl;

    @Value("${hibernate.dialect}")
    private String dialect; 
    
    @Value("${db.show.sql}")
    private String showSql;

    @Value("${db.schema}")
    private String schema;

    final Properties getProperties() {
        final Properties hibernateProperties = new Properties();

        hibernateProperties.setProperty("hibernate.hbm2ddl.auto", hbm2ddl);
        hibernateProperties.setProperty("hibernate.default_schema", schema);
        hibernateProperties.setProperty("hibernate.dialect", dialect);
        hibernateProperties.setProperty("hibernate.show_sql", showSql);
        hibernateProperties.setProperty("hibernate.cache.use_second_level_cache", "true");
        hibernateProperties.setProperty("hibernate.cache.use_query_cache", "true");
        hibernateProperties.setProperty("hibernate.cache.region.factory_class",
                "org.hibernate.cache.ehcache.EhCacheRegionFactory");
        hibernateProperties.setProperty("hibernate.connection.CharSet", "utf8");
        hibernateProperties.setProperty("hibernate.connection.characterEncoding", "utf8");
        hibernateProperties.setProperty("hibernate.connection.useUnicode", "true");
        hibernateProperties.setProperty("hibernate.id.new_generator_mappings", "false"); // unless you run on a new
                                                                                         // schema
        hibernateProperties.setProperty("hibernate.generate_statistics", "false");
        // hibernateProperties.setProperty("hibernate.globally_quoted_identifiers",
        // "true");
        return hibernateProperties;
    }
}
