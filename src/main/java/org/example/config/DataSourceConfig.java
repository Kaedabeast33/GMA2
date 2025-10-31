package org.example.config;

import org.example.gma.templates.KdbDataBaseProperties;

import org.example.gma.templates.KdbGma;
import org.example.gma.templates.MAConfigTemplate;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Configuration
public class DataSourceConfig {


    @Bean
    @ConfigurationProperties("kdb.datasource.productalignment")
    public KdbDataBaseProperties propertiesAirtable(){
        return new KdbDataBaseProperties();
    }

    @Bean
    @ConfigurationProperties("kdb.datasource.employeealignment")
    public KdbDataBaseProperties propertiesEmployeealignment(){
        return new KdbDataBaseProperties();
    }

    @Bean
    @ConfigurationProperties("kdb.datasource.commissions")
    public KdbDataBaseProperties propertiesCommissions(){
        return new KdbDataBaseProperties();
    }

    @Bean
    @ConfigurationProperties("kdb.datasource.leads")
    public KdbDataBaseProperties propertiesLeads(){
        return new KdbDataBaseProperties();
    }

    @Bean
    @ConfigurationProperties("kdb.datasource.orders")
    public KdbDataBaseProperties propertiesOrders(){
        return new KdbDataBaseProperties();
    }

    @Bean
    @ConfigurationProperties("kdb.datasource.reports")
    public KdbDataBaseProperties propertiesReports(){
        return new KdbDataBaseProperties();
    }


    @Bean(name = "kdbGma")
    public KdbGma kdbDatabaseOrder() throws SQLException {
//        System.out.println(propertiesAirtable().getUrl());
//        System.out.println(propertiesEmployeealignment().getUrl());
        KdbGma  gma = new KdbGma() ;
        Map<String,String> gmaSettings =new ConcurrentHashMap<>();
        gmaSettings.put("dialect","my5sql");
        gmaSettings.put("type","mysql");





        gma.setName("dorm");
        gma.setTags(new String[]{"", ""});
        gma.setDescription("main data lake");

        gma.setGmaSettings(gmaSettings);

        gma.setMa(getSchemas());
//        kdbDatabase.initKdbManager();

        return gma;
    }






    public List<MAConfigTemplate> getSchemas() throws SQLException {
        Map<String,String> maSettings = new ConcurrentHashMap<>();
        maSettings.put("encoding","utf-8-ai-zs");




        MAConfigTemplate schema = new MAConfigTemplate();
        schema.setName(propertiesEmployeealignment().getSchema());
        schema.setDescription("this i the schema where orders are kept ");
        schema.setTags(new String[]{"orders", "sellers"});
        schema.setMaSettings(maSettings);
        schema.setName("employee_alignment");
        schema.setJdbcConnectionUrl(propertiesEmployeealignment().getUrl());
        schema.setUsername(propertiesEmployeealignment().getUsername());
        schema.setPassword(propertiesEmployeealignment().getPassword());

        schema.setJavaFolderPath(propertiesEmployeealignment().getJavaFolderPath());

//        schema.initKdbManager();

        MAConfigTemplate schema2 = new MAConfigTemplate();
        schema2.setName(propertiesAirtable().getSchema());
        schema2.setDescription("this is the connector to airtable and most of our aligment and reference data");
        schema2.setTags(new String[]{"airtable", "alignment"});
        schema2.setMaSettings(maSettings);
        schema2.setName("airtable");
        schema2.setJdbcConnectionUrl(propertiesAirtable().getUrl());
        schema2.setUsername(propertiesAirtable().getUsername());
        schema2.setPassword(propertiesAirtable().getPassword());
        schema2.setJavaFolderPath(propertiesAirtable().getJavaFolderPath());

        MAConfigTemplate schema3 = new MAConfigTemplate();
        schema3.setName(propertiesCommissions().getSchema());
        schema3.setDescription("this is the db that holds data related to the process of getting sellers paid for their  sales");
        schema3.setTags(new String[]{"payroll", "carriers","chipr","internal"});
        schema3.setMaSettings(maSettings);
        schema3.setName("commissions");
        schema3.setJdbcConnectionUrl(propertiesCommissions().getUrl());
        schema3.setUsername(propertiesCommissions().getUsername());
        schema3.setPassword(propertiesCommissions().getPassword());
        schema3.setJavaFolderPath(propertiesCommissions().getJavaFolderPath());

        MAConfigTemplate schema4 = new MAConfigTemplate();
        schema4.setName(propertiesLeads().getSchema());
        schema4.setDescription("manages leads and activities related to lead generation and follow-up");
        schema4.setTags(new String[]{"leads", "activity","chipr","spotio"});
        schema4.setMaSettings(maSettings);
        schema4.setName("leads");
        schema4.setJdbcConnectionUrl(propertiesLeads().getUrl());
        schema4.setUsername(propertiesLeads().getUsername());
        schema4.setPassword(propertiesLeads().getPassword());
        schema4.setJavaFolderPath(propertiesLeads().getJavaFolderPath());

        MAConfigTemplate schema5 = new MAConfigTemplate();
        schema5.setName(propertiesOrders().getSchema());
        schema5.setDescription("this is the db that holds data related to sales and orders placed by sellers from different carriers");
        schema5.setTags(new String[]{"sales","carriers"});
        schema5.setMaSettings(maSettings);
        schema5.setName("orders");
        schema5.setJdbcConnectionUrl(propertiesOrders().getUrl());
        schema5.setUsername(propertiesOrders().getUsername());
        schema5.setPassword(propertiesOrders().getPassword());
        schema5.setJavaFolderPath(propertiesOrders().getJavaFolderPath());

        MAConfigTemplate schema6 = new MAConfigTemplate();
        schema6.setName(propertiesReports().getSchema());
        schema6.setDescription("this is the db that holds all of our reports and aggregated data for analysis and business intelligence");
        schema6.setTags(new String[]{"sales","carriers","reports","aggregation"});
        schema6.setMaSettings(maSettings);
        schema6.setName("reports");
        schema6.setJdbcConnectionUrl(propertiesReports().getUrl());
        schema6.setUsername(propertiesReports().getUsername());
        schema6.setPassword(propertiesReports().getPassword());
        schema6.setJavaFolderPath(propertiesReports().getJavaFolderPath());


//        schema2.initKdbManager();

        return List.of(
                schema,
                schema2,
                schema3,
                schema4,
                schema5,
                schema6
                );
    }












}
