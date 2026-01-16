package org.example.bank.datasources;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Map;
import java.util.Objects;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        basePackages = "com.chipr.DORM.locations.Ai",
        entityManagerFactoryRef = "entityManagerFactoryAi",
        transactionManagerRef = "transactionManagerAi"
)
public class AiEntityManager {
    @Bean (name="entityManagerFactoryAi")
    LocalContainerEntityManagerFactoryBean entityManagerFactoryBeanAi(EntityManagerFactoryBuilder builder, @Qualifier("dataSourceAi") DataSource dataSource){
        Map<String,Object> properties = new java.util.HashMap<>();
        properties.put("hibernate.hbm2ddl.auto","update");
        properties.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        return (LocalContainerEntityManagerFactoryBean) builder
                .dataSource(dataSource)
                .packages("com.chipr.DORM.Ai")
                .properties(properties)
                .build();
    }

    @Bean
    PlatformTransactionManager transactionManagerAi(@Qualifier("entityManagerFactoryAi") LocalContainerEntityManagerFactoryBean emfb){
        return new org.springframework.orm.jpa.JpaTransactionManager(Objects.requireNonNull(emfb.getObject()));
    }

}
