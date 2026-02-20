package org.example.APP;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        basePackages = "org.example.inputs.client",
        entityManagerFactoryRef = "entityManagerFactoryClient",
        transactionManagerRef = "transactionManagerClient"
)
public class ClientEntityManager {

    @Bean(name = "entityManagerFactoryClient")
    @Primary
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryClient(
            EntityManagerFactoryBuilder builder,
            @Qualifier("dataSourceClient") DataSource dataSource) {

        Map<String, Object> properties = new HashMap<>();
        properties.put("hibernate.hbm2ddl.auto", "update");
        properties.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");

        LocalContainerEntityManagerFactoryBean emf = builder
                .dataSource(dataSource)
                // 🔽 put your ENTITY packages here
                .packages("org.example.inputs.client")
                .properties(properties)
                .build();

        // 🔥 prevents persistence.xml lookup
        emf.setPersistenceUnitName("entityManagerFactoryClient");

        return emf;
    }

    @Bean(name = "transactionManagerClient")
    @Primary
    public PlatformTransactionManager transactionManagerClient(
            @Qualifier("entityManagerFactoryClient") LocalContainerEntityManagerFactoryBean emf) {

        return new JpaTransactionManager(Objects.requireNonNull(emf.getObject()));
    }
}
