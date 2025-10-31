package org.example;


//import org.example.dorm.employee_alignment.align_emp_upline_history.TAB_align_emp_upline_history;



import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import org.springframework.context.ApplicationContext;

@EnableConfigurationProperties
@SpringBootApplication//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    public static void main(String[] args) throws Exception {
        ApplicationContext context = SpringApplication.run(Main.class, args);
        Actions actions = context.getBean(Actions.class);
        actions.buildGmaContext();
        actions.buildClasses();
        actions.getDbJson();
        actions.analyzeJson();

    }
}