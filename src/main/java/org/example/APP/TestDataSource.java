package org.example.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;


import javax.sql.DataSource;

@Configuration
public class TestDataSource {

    // Create a DataSourceProperties bean for Test
    @Bean
    @ConfigurationProperties("spring.datasource.test")
    public DataSourceProperties dataSourcePropertiesTest(){
        return new DataSourceProperties();
    }

    @Bean
    @Qualifier("dataSourceTest")
    public DataSource dataSourceTest(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUsername(dataSourcePropertiesTest().getUsername());
        dataSource.setPassword(dataSourcePropertiesTest().getPassword());
        dataSource.setUrl(dataSourcePropertiesTest().getUrl());
        return dataSource;
    }
}
