package org.example.config;

import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

// enable the transaction management and Jpa repositories
@EnableTransactionManagement
@Configuration
// set the entity manager factory bean and the transaction manager bean and the schema
public class TestEntityManager {
    // return a local conainter entity manager factory bean and inject the datasource and properties.
    //set the properties
    // build the entity manager factory bean

    @Bean(name = "entityManagerFactoryTest")
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryTest(
            EntityManagerFactoryBuilder builder,
            @Qualifier("dataSourceTest") DataSource dataSource) {

        Map<String, Object> properties = new HashMap<>();
        properties.put("hibernate.hbm2ddl.auto", "none");
        properties.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");

        return builder
                .dataSource(dataSource)
                .packages("java.lang") // prevents entity scanning
                .persistenceUnit("entityManagerFactoryTest")
                .properties(properties)
                .build();
    }


    //crewate transaction manager bean by returning the transaction manager with a entity manager factory object


    @Bean(name = "transactionManagerTest")
    public PlatformTransactionManager transactionManagerTest(@Qualifier("entityManagerFactoryTest") LocalContainerEntityManagerFactoryBean entityManagerFactory) {
        return new org.springframework.orm.jpa.JpaTransactionManager(Objects.requireNonNull(entityManagerFactory.getObject()));
    }
//    @Bean
//    public EntityManagerFactoryBuilder entityManagerFactoryBuilder(JpaProperties jpaProperties) {
//        return new EntityManagerFactoryBuilder(new HibernateJpaVendorAdapter(), jpaProperties.getProperties(), null);
//    }
}



