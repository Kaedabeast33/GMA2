package org.example.APP.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;


import javax.sql.DataSource;

@Configuration
public class ClientDataSource {

    // Create a DataSourceProperties bean for Client
    @Bean
    @ConfigurationProperties("spring.datasource.clientmed")
    public DataSourceProperties dataSourcePropertiesClient(){
        return new DataSourceProperties();
    }

    @Bean
    @Qualifier("dataSourceClientMed")
    public DataSource dataSourceClient(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUsername(dataSourcePropertiesClient().getUsername());
        dataSource.setPassword(dataSourcePropertiesClient().getPassword());
        dataSource.setUrl(dataSourcePropertiesClient().getUrl());
        return dataSource;
    }
}
