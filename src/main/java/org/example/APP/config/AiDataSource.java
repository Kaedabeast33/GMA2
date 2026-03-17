package org.example.APP.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;


import javax.sql.DataSource;

@Configuration
public class AiDataSource {

    // Create a DataSourceProperties bean for Ai
    @Bean
    @ConfigurationProperties("spring.datasource.ai")
    public DataSourceProperties dataSourcePropertiesAi(){
        return new DataSourceProperties();
    }

    @Bean
    @Qualifier("dataSourceAi")
    public DataSource dataSourceAi(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUsername(dataSourcePropertiesAi().getUsername());
        dataSource.setPassword(dataSourcePropertiesAi().getPassword());
        dataSource.setUrl(dataSourcePropertiesAi().getUrl());
        return dataSource;
    }
}
