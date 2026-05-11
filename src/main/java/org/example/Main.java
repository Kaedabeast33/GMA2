package org.example;


//import org.example.dorm.employee_alignment.align_emp_upline_history.TAB_align_emp_upline_history;


//import org.example.output.dorm.GMA_dorm;
//import org.example.output.dorm.orders.master_raw_orders.TAB_master_raw_orders;


//import org.example.output.dorm.GMA_dorm;
//import org.example.output.dorm.orders.master_raw_orders.TAB_master_raw_orders;


import org.example.ClassOutputCreator.templates.ai.AiColumnTemplate;
import org.example.JsonBuilder.json.GMAJson;
import org.example.JsonBuilder.json.ma.AiMAJson;
import org.example.JsonBuilder.json.ma.MAJson;
import org.example.JsonBuilder.json.ma.tables.TableJson;
import org.example.JsonBuilder.json.ma.tables.columns.AiColumnJson;

import org.example.ai.AiRagSchemaJson;
import org.example.bank.OutputClassBank.KDBContext;
import org.example.bank.OutputClassBank.KdbContextAi;
import org.example.bank.commonValues.TableTypes;


//y


import org.example.service.Actions;


//import org.example.service.TestService;
//import org.example.service.TestService;

import org.example.service.TestService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.example.bank.AppConfig.getGmaName;
import static org.example.bank.OutputClassBank.KdbColumnWrapper.safeGetValue;

@EnableConfigurationProperties
@SpringBootApplication//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    public static void main(String[] args) throws Exception {
        ApplicationContext context = SpringApplication.run(Main.class, args);
        Actions actions = context.getBean(Actions.class);

        actions.buildGmaContext();
//        actions.mainRun();

        TestService service= context.getBean(TestService.class);
        service.test();






















    }
}