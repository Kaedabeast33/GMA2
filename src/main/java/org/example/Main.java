package org.example;


//import org.example.dorm.employee_alignment.align_emp_upline_history.TAB_align_emp_upline_history;


import org.example.ClassOutputCreator.templates.ColumnTemplate;
import org.example.ClassOutputCreator.templates.TableTemplate;


//import org.example.output.dorm.GMA_dorm;
//import org.example.output.dorm.orders.master_raw_orders.TAB_master_raw_orders;


//import org.example.output.dorm.GMA_dorm;
//import org.example.output.dorm.orders.master_raw_orders.TAB_master_raw_orders;
import org.example.service.Actions;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;

import java.util.List;
import java.util.Scanner;

@EnableConfigurationProperties
@SpringBootApplication//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    public static void main(String[] args) throws Exception {
        ApplicationContext context = SpringApplication.run(Main.class, args);
        Actions actions = context.getBean(Actions.class);
        actions.mainRun();

//        GMA_dorm dorm = new GMA_dorm();
//        TAB_master_raw_orders mro = dorm.getMA_orders().getTAB_master_raw_orders();
//        System.out.println(mro.getUploadDelete(List.of(new ColumnTemplate[]{mro.getCOL_carrier_system(), mro.getCOL_employee_id()}),false));
//        System.out.println(mro.getUploadInsert(List.of(new ColumnTemplate[]{mro.getCOL_carrier_system(), mro.getCOL_employee_id()}),false));
//        System.out.println(mro.getUploadInsert());
//
//        System.out.println(mro.getUploadInsert(List.of(new ColumnTemplate[]{mro.getCOL_carrier_system(), mro.getCOL_employee_id()}),false,mro.getColumns(),true));
//        System.out.println(mro.getUploadUpdate(List.of(new ColumnTemplate[]{mro.getCOL_carrier_system(), mro.getCOL_employee_id()}),false,mro.getColumns()));



// Build GMA context and classes




    }
}