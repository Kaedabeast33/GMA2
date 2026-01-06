package org.example;


//import org.example.dorm.employee_alignment.align_emp_upline_history.TAB_align_emp_upline_history;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import jakarta.persistence.Column;
import jakarta.persistence.Query;
import org.example.ClassOutputCreator.templates.ColumnTemplate;
import org.example.ClassOutputCreator.templates.TableTemplate;


//import org.example.output.dorm.GMA_dorm;
//import org.example.output.dorm.orders.master_raw_orders.TAB_master_raw_orders;


//import org.example.output.dorm.GMA_dorm;
//import org.example.output.dorm.orders.master_raw_orders.TAB_master_raw_orders;

import org.example.JsonBuilder.json.GMAJson;
import org.example.JsonBuilder.json.QueryGroupJson;
import org.example.JsonBuilder.json.ma.MAJson;
import org.example.JsonBuilder.json.ma.PipelineJson;
import org.example.JsonBuilder.json.ma.tables.*;
import org.example.JsonBuilder.json.ma.tables.columns.*;
import org.example.JsonBuilder.json.ma.tables.dependencies.DependencyJson;
import org.example.JsonBuilder.json.ref.RefColumnJson;
import org.example.JsonBuilder.json.ref.RefTableJson;
import org.example.JsonBuilder.json.ref.ReferenceColumnJson;
import org.example.bank.JsonBuilderRef.BlankJsonGenerator;
import org.example.bank.OutputClassBank.KDBContext;
import org.example.bank.OutputClassBank.KdbColumnPersona;


import org.example.service.Actions;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;

import java.nio.channels.Pipe;
import java.sql.Connection;
import java.util.List;
import java.util.Scanner;

import static org.example.JsonBuilder.json.GMAJson.createBlankInstance;
import static org.example.bank.OutputClassBank.KDBContext.getConnection;

@EnableConfigurationProperties
@SpringBootApplication//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    public static void main(String[] args) throws Exception {
        ApplicationContext context = SpringApplication.run(Main.class, args);
        Actions actions = context.getBean(Actions.class);
        actions.mainRun();

        actions.writeDbGmaStructure();





    }
}