package org.example;


//import org.example.dorm.employee_alignment.align_emp_upline_history.TAB_align_emp_upline_history;


//import org.example.output.dorm.GMA_dorm;
//import org.example.output.dorm.orders.master_raw_orders.TAB_master_raw_orders;


//import org.example.output.dorm.GMA_dorm;
//import org.example.output.dorm.orders.master_raw_orders.TAB_master_raw_orders;


import org.example.service.Actions;
import org.example.service.TestService;
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
//        TestService service = context.getBean(TestService.class);


        TestService testService = context.getBean(TestService.class);



        actions.buildGmaContext();
                    actions.mainRun();
//        AIRMA_friend_airtable ma = new AIRMA_friend_airtable();
//        AIRTAB_kaeden test = new AIRTAB_kaeden();
//         test.getAIRCOL_name().setEntityValue("kaeden");
//         test.getAIRCOL_status().setEntityValue(true);
//
//
//        ma.saveAllAirtable(test, List.of(test), testService.getBearer(),toPersonaAirTemplate(List.of(test.getAIRCOL_name(),test.getAIRCOL_status())), toPersonaAirTemplate(List.of(test.getAIRCOL_name())));


//        service.upload();



        // Only run mainRun when NOT running in production profiles
//        Environment env = context.getEnvironment();
//        boolean isProd;
//        try {
//            isProd = env.acceptsProfiles(Profiles.of("production")) || env.acceptsProfiles(Profiles.of("prod"));
//        } catch (NoSuchMethodError ignored) {
//            String[] active = env.getActiveProfiles();
//            isProd = Arrays.asList(active).contains("production") || Arrays.asList(active).contains("prod") || "production".equalsIgnoreCase(env.getProperty("spring.profiles.active"));
//        }
//
//        if (!isProd) {
//            actions.mainRun();
//        } else {
//            System.out.println("Skipping Actions.mainRun() because running in production profile.");
//        }



//        actions.writeDbGmaStructure();





    }
}