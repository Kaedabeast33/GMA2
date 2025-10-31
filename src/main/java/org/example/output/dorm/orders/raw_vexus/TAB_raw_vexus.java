package org.example.output.dorm.orders.raw_vexus;


import org.example.gma.templates.TableTemplate;
import org.example.gma.templates.QueryTemplate;
import org.example.gma.templates.ColumnTemplate;


import org.example.output.dorm.orders.raw_vexus.columns.COL_db_id;
import org.example.output.dorm.orders.raw_vexus.columns.COL_account_number;
import org.example.output.dorm.orders.raw_vexus.columns.COL_address1;
import org.example.output.dorm.orders.raw_vexus.columns.COL_address2;
import org.example.output.dorm.orders.raw_vexus.columns.COL_address3;
import org.example.output.dorm.orders.raw_vexus.columns.COL_campaigns;
import org.example.output.dorm.orders.raw_vexus.columns.COL_cancel_date;
import org.example.output.dorm.orders.raw_vexus.columns.COL_cancel_reason;
import org.example.output.dorm.orders.raw_vexus.columns.COL_co;
import org.example.output.dorm.orders.raw_vexus.columns.COL_comp_date;
import org.example.output.dorm.orders.raw_vexus.columns.COL_contractor;
import org.example.output.dorm.orders.raw_vexus.columns.COL_cred_score;
import org.example.output.dorm.orders.raw_vexus.columns.COL_credit_score;
import org.example.output.dorm.orders.raw_vexus.columns.COL_customer_name;
import org.example.output.dorm.orders.raw_vexus.columns.COL_customer_status;
import org.example.output.dorm.orders.raw_vexus.columns.COL_date_entered;
import org.example.output.dorm.orders.raw_vexus.columns.COL_dv;
import org.example.output.dorm.orders.raw_vexus.columns.COL_email_address;
import org.example.output.dorm.orders.raw_vexus.columns.COL_frn;
import org.example.output.dorm.orders.raw_vexus.columns.COL_home_phone;
import org.example.output.dorm.orders.raw_vexus.columns.COL_house_record_number;
import org.example.output.dorm.orders.raw_vexus.columns.COL_internet_speed;
import org.example.output.dorm.orders.raw_vexus.columns.COL_market_ready_date;
import org.example.output.dorm.orders.raw_vexus.columns.COL_order_number;
import org.example.output.dorm.orders.raw_vexus.columns.COL_order_sales_type;
import org.example.output.dorm.orders.raw_vexus.columns.COL_order_status;
import org.example.output.dorm.orders.raw_vexus.columns.COL_other_phone;
import org.example.output.dorm.orders.raw_vexus.columns.COL_sale_reas;
import org.example.output.dorm.orders.raw_vexus.columns.COL_sales_group;
import org.example.output.dorm.orders.raw_vexus.columns.COL_sales_number;
import org.example.output.dorm.orders.raw_vexus.columns.COL_sales_reason;
import org.example.output.dorm.orders.raw_vexus.columns.COL_sales_type;
import org.example.output.dorm.orders.raw_vexus.columns.COL_salesman_name;
import org.example.output.dorm.orders.raw_vexus.columns.COL_salesman_type;
import org.example.output.dorm.orders.raw_vexus.columns.COL_sched_date;
import org.example.output.dorm.orders.raw_vexus.columns.COL_serving_area;
import org.example.output.dorm.orders.raw_vexus.columns.COL_status_date;
import org.example.output.dorm.orders.raw_vexus.columns.COL_work_order_comments;
import org.example.output.dorm.orders.raw_vexus.columns.COL_wo_type;
import org.example.output.dorm.orders.raw_vexus.columns.COL_work_phone;
import org.example.output.dorm.orders.raw_vexus.columns.COL_entity_product;
import org.example.output.dorm.orders.raw_vexus.columns.COL_order_sales_type_full;
import org.example.output.dorm.orders.raw_vexus.columns.COL_employee_id;
import org.example.output.dorm.orders.raw_vexus.columns.COL_manual_employee_id;
import org.example.output.dorm.orders.raw_vexus.columns.COL_date_db_changed;

import java.util.List;

import org.example.ContextInstance.KDBContext;

public class TAB_raw_vexus extends TableTemplate {

    public TAB_raw_vexus() {
        super(
            "raw_vexus"
,
            "Table storing raw Vexus order records"
,
            new String[]{"orders", "vexus", "raw"},
            "tabca6119b1-cf4f-4ab8-9a38-95a0201f693d"
,
            "dorm"
,
            "orders"

        );
    }




 KDBContext context = KDBContext.KDB_CONTEXT;
    private final ColumnTemplate COL_db_id = new COL_db_id();

    public ColumnTemplate getCOL_db_id() {
        return COL_db_id;
    }





    private final ColumnTemplate COL_account_number = new COL_account_number();

    public ColumnTemplate getCOL_account_number() {
        return COL_account_number;
    }





    private final ColumnTemplate COL_address1 = new COL_address1();

    public ColumnTemplate getCOL_address1() {
        return COL_address1;
    }





    private final ColumnTemplate COL_address2 = new COL_address2();

    public ColumnTemplate getCOL_address2() {
        return COL_address2;
    }





    private final ColumnTemplate COL_address3 = new COL_address3();

    public ColumnTemplate getCOL_address3() {
        return COL_address3;
    }





    private final ColumnTemplate COL_campaigns = new COL_campaigns();

    public ColumnTemplate getCOL_campaigns() {
        return COL_campaigns;
    }





    private final ColumnTemplate COL_cancel_date = new COL_cancel_date();

    public ColumnTemplate getCOL_cancel_date() {
        return COL_cancel_date;
    }





    private final ColumnTemplate COL_cancel_reason = new COL_cancel_reason();

    public ColumnTemplate getCOL_cancel_reason() {
        return COL_cancel_reason;
    }





    private final ColumnTemplate COL_co = new COL_co();

    public ColumnTemplate getCOL_co() {
        return COL_co;
    }





    private final ColumnTemplate COL_comp_date = new COL_comp_date();

    public ColumnTemplate getCOL_comp_date() {
        return COL_comp_date;
    }





    private final ColumnTemplate COL_contractor = new COL_contractor();

    public ColumnTemplate getCOL_contractor() {
        return COL_contractor;
    }





    private final ColumnTemplate COL_cred_score = new COL_cred_score();

    public ColumnTemplate getCOL_cred_score() {
        return COL_cred_score;
    }





    private final ColumnTemplate COL_credit_score = new COL_credit_score();

    public ColumnTemplate getCOL_credit_score() {
        return COL_credit_score;
    }





    private final ColumnTemplate COL_customer_name = new COL_customer_name();

    public ColumnTemplate getCOL_customer_name() {
        return COL_customer_name;
    }





    private final ColumnTemplate COL_customer_status = new COL_customer_status();

    public ColumnTemplate getCOL_customer_status() {
        return COL_customer_status;
    }





    private final ColumnTemplate COL_date_entered = new COL_date_entered();

    public ColumnTemplate getCOL_date_entered() {
        return COL_date_entered;
    }





    private final ColumnTemplate COL_dv = new COL_dv();

    public ColumnTemplate getCOL_dv() {
        return COL_dv;
    }





    private final ColumnTemplate COL_email_address = new COL_email_address();

    public ColumnTemplate getCOL_email_address() {
        return COL_email_address;
    }





    private final ColumnTemplate COL_frn = new COL_frn();

    public ColumnTemplate getCOL_frn() {
        return COL_frn;
    }





    private final ColumnTemplate COL_home_phone = new COL_home_phone();

    public ColumnTemplate getCOL_home_phone() {
        return COL_home_phone;
    }





    private final ColumnTemplate COL_house_record_number = new COL_house_record_number();

    public ColumnTemplate getCOL_house_record_number() {
        return COL_house_record_number;
    }





    private final ColumnTemplate COL_internet_speed = new COL_internet_speed();

    public ColumnTemplate getCOL_internet_speed() {
        return COL_internet_speed;
    }





    private final ColumnTemplate COL_market_ready_date = new COL_market_ready_date();

    public ColumnTemplate getCOL_market_ready_date() {
        return COL_market_ready_date;
    }





    private final ColumnTemplate COL_order_number = new COL_order_number();

    public ColumnTemplate getCOL_order_number() {
        return COL_order_number;
    }





    private final ColumnTemplate COL_order_sales_type = new COL_order_sales_type();

    public ColumnTemplate getCOL_order_sales_type() {
        return COL_order_sales_type;
    }





    private final ColumnTemplate COL_order_status = new COL_order_status();

    public ColumnTemplate getCOL_order_status() {
        return COL_order_status;
    }





    private final ColumnTemplate COL_other_phone = new COL_other_phone();

    public ColumnTemplate getCOL_other_phone() {
        return COL_other_phone;
    }





    private final ColumnTemplate COL_sale_reas = new COL_sale_reas();

    public ColumnTemplate getCOL_sale_reas() {
        return COL_sale_reas;
    }





    private final ColumnTemplate COL_sales_group = new COL_sales_group();

    public ColumnTemplate getCOL_sales_group() {
        return COL_sales_group;
    }





    private final ColumnTemplate COL_sales_number = new COL_sales_number();

    public ColumnTemplate getCOL_sales_number() {
        return COL_sales_number;
    }





    private final ColumnTemplate COL_sales_reason = new COL_sales_reason();

    public ColumnTemplate getCOL_sales_reason() {
        return COL_sales_reason;
    }





    private final ColumnTemplate COL_sales_type = new COL_sales_type();

    public ColumnTemplate getCOL_sales_type() {
        return COL_sales_type;
    }





    private final ColumnTemplate COL_salesman_name = new COL_salesman_name();

    public ColumnTemplate getCOL_salesman_name() {
        return COL_salesman_name;
    }





    private final ColumnTemplate COL_salesman_type = new COL_salesman_type();

    public ColumnTemplate getCOL_salesman_type() {
        return COL_salesman_type;
    }





    private final ColumnTemplate COL_sched_date = new COL_sched_date();

    public ColumnTemplate getCOL_sched_date() {
        return COL_sched_date;
    }





    private final ColumnTemplate COL_serving_area = new COL_serving_area();

    public ColumnTemplate getCOL_serving_area() {
        return COL_serving_area;
    }





    private final ColumnTemplate COL_status_date = new COL_status_date();

    public ColumnTemplate getCOL_status_date() {
        return COL_status_date;
    }





    private final ColumnTemplate COL_work_order_comments = new COL_work_order_comments();

    public ColumnTemplate getCOL_work_order_comments() {
        return COL_work_order_comments;
    }





    private final ColumnTemplate COL_wo_type = new COL_wo_type();

    public ColumnTemplate getCOL_wo_type() {
        return COL_wo_type;
    }





    private final ColumnTemplate COL_work_phone = new COL_work_phone();

    public ColumnTemplate getCOL_work_phone() {
        return COL_work_phone;
    }





    private final ColumnTemplate COL_entity_product = new COL_entity_product();

    public ColumnTemplate getCOL_entity_product() {
        return COL_entity_product;
    }





    private final ColumnTemplate COL_order_sales_type_full = new COL_order_sales_type_full();

    public ColumnTemplate getCOL_order_sales_type_full() {
        return COL_order_sales_type_full;
    }





    private final ColumnTemplate COL_employee_id = new COL_employee_id();

    public ColumnTemplate getCOL_employee_id() {
        return COL_employee_id;
    }





    private final ColumnTemplate COL_manual_employee_id = new COL_manual_employee_id();

    public ColumnTemplate getCOL_manual_employee_id() {
        return COL_manual_employee_id;
    }





    private final ColumnTemplate COL_date_db_changed = new COL_date_db_changed();

    public ColumnTemplate getCOL_date_db_changed() {
        return COL_date_db_changed;
    }





    @Override
    public List<String> getColumns(){
       List<String> list = context.getColumns(this.getGmaName(),this.getMaName(),this.getName());
       System.out.println(list);
       return list;
    }
     
    @Override
    public List<String> getColumnsByGroupName(String groupName){
        List<String> list = context.getColumnsByGroupName(this.getGmaName(),this.getMaName(),this.getName(),groupName);
        System.out.println(list);
        return list;
    }
     
    @Override
    public List<String> getUniqueIdentifierColumns(){
        List<String> list = context.getUniqueIdentifierColumns(this.getGmaName(),this.getMaName(),this.getName());
        System.out.println(list);
        return list;
    }
     
    @Override
    public List<String> getUniqueIdentifierColumnsByGroupName(String groupName){
        List<String> list = context.getUniqueIdentifierColumnsByGroupName(this.getGmaName(),this.getMaName(),this.getName(),groupName);
        System.out.println(list);
        return list;
        }
     
    @Override
    public String replaceCharacters(String value){
       if(value==null){;
           return null;
       }
         return value.replace("'","''");
    }
    @Override
    public String getTableName(){
        return this.getName();
}
@Override
public String getValues()  {
    return String.format("('%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s')\n",
                      getCOL_db_id().getEntityValue().getValue().toString(),
          getCOL_account_number().getEntityValue().getValue().toString(),
          getCOL_address1().getEntityValue().getValue().toString(),
          getCOL_address2().getEntityValue().getValue().toString(),
          getCOL_address3().getEntityValue().getValue().toString(),
          getCOL_campaigns().getEntityValue().getValue().toString(),
          getCOL_cancel_date().getEntityValue().getValue().toString(),
          getCOL_cancel_reason().getEntityValue().getValue().toString(),
          getCOL_co().getEntityValue().getValue().toString(),
          getCOL_comp_date().getEntityValue().getValue().toString(),
          getCOL_contractor().getEntityValue().getValue().toString(),
          getCOL_cred_score().getEntityValue().getValue().toString(),
          getCOL_credit_score().getEntityValue().getValue().toString(),
          getCOL_customer_name().getEntityValue().getValue().toString(),
          getCOL_customer_status().getEntityValue().getValue().toString(),
          getCOL_date_entered().getEntityValue().getValue().toString(),
          getCOL_dv().getEntityValue().getValue().toString(),
          getCOL_email_address().getEntityValue().getValue().toString(),
          getCOL_frn().getEntityValue().getValue().toString(),
          getCOL_home_phone().getEntityValue().getValue().toString(),
          getCOL_house_record_number().getEntityValue().getValue().toString(),
          getCOL_internet_speed().getEntityValue().getValue().toString(),
          getCOL_market_ready_date().getEntityValue().getValue().toString(),
          getCOL_order_number().getEntityValue().getValue().toString(),
          getCOL_order_sales_type().getEntityValue().getValue().toString(),
          getCOL_order_status().getEntityValue().getValue().toString(),
          getCOL_other_phone().getEntityValue().getValue().toString(),
          getCOL_sale_reas().getEntityValue().getValue().toString(),
          getCOL_sales_group().getEntityValue().getValue().toString(),
          getCOL_sales_number().getEntityValue().getValue().toString(),
          getCOL_sales_reason().getEntityValue().getValue().toString(),
          getCOL_sales_type().getEntityValue().getValue().toString(),
          getCOL_salesman_name().getEntityValue().getValue().toString(),
          getCOL_salesman_type().getEntityValue().getValue().toString(),
          getCOL_sched_date().getEntityValue().getValue().toString(),
          getCOL_serving_area().getEntityValue().getValue().toString(),
          getCOL_status_date().getEntityValue().getValue().toString(),
          getCOL_work_order_comments().getEntityValue().getValue().toString(),
          getCOL_wo_type().getEntityValue().getValue().toString(),
          getCOL_work_phone().getEntityValue().getValue().toString(),
          getCOL_entity_product().getEntityValue().getValue().toString(),
          getCOL_order_sales_type_full().getEntityValue().getValue().toString(),
          getCOL_employee_id().getEntityValue().getValue().toString(),
          getCOL_manual_employee_id().getEntityValue().getValue().toString(),
          getCOL_date_db_changed().getEntityValue().getValue().toString()
    );
}
            @Override
            public String getValues(String arg) {
                return String.format("('%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s')\n",
          getCOL_db_id().getEntityValue().getValue().toString(),
          getCOL_account_number().getEntityValue().getValue().toString(),
          getCOL_address1().getEntityValue().getValue().toString(),
          getCOL_address2().getEntityValue().getValue().toString(),
          getCOL_address3().getEntityValue().getValue().toString(),
          getCOL_campaigns().getEntityValue().getValue().toString(),
          getCOL_cancel_date().getEntityValue().getValue().toString(),
          getCOL_cancel_reason().getEntityValue().getValue().toString(),
          getCOL_co().getEntityValue().getValue().toString(),
          getCOL_comp_date().getEntityValue().getValue().toString(),
          getCOL_contractor().getEntityValue().getValue().toString(),
          getCOL_cred_score().getEntityValue().getValue().toString(),
          getCOL_credit_score().getEntityValue().getValue().toString(),
          getCOL_customer_name().getEntityValue().getValue().toString(),
          getCOL_customer_status().getEntityValue().getValue().toString(),
          getCOL_date_entered().getEntityValue().getValue().toString(),
          getCOL_dv().getEntityValue().getValue().toString(),
          getCOL_email_address().getEntityValue().getValue().toString(),
          getCOL_frn().getEntityValue().getValue().toString(),
          getCOL_home_phone().getEntityValue().getValue().toString(),
          getCOL_house_record_number().getEntityValue().getValue().toString(),
          getCOL_internet_speed().getEntityValue().getValue().toString(),
          getCOL_market_ready_date().getEntityValue().getValue().toString(),
          getCOL_order_number().getEntityValue().getValue().toString(),
          getCOL_order_sales_type().getEntityValue().getValue().toString(),
          getCOL_order_status().getEntityValue().getValue().toString(),
          getCOL_other_phone().getEntityValue().getValue().toString(),
          getCOL_sale_reas().getEntityValue().getValue().toString(),
          getCOL_sales_group().getEntityValue().getValue().toString(),
          getCOL_sales_number().getEntityValue().getValue().toString(),
          getCOL_sales_reason().getEntityValue().getValue().toString(),
          getCOL_sales_type().getEntityValue().getValue().toString(),
          getCOL_salesman_name().getEntityValue().getValue().toString(),
          getCOL_salesman_type().getEntityValue().getValue().toString(),
          getCOL_sched_date().getEntityValue().getValue().toString(),
          getCOL_serving_area().getEntityValue().getValue().toString(),
          getCOL_status_date().getEntityValue().getValue().toString(),
          getCOL_work_order_comments().getEntityValue().getValue().toString(),
          getCOL_wo_type().getEntityValue().getValue().toString(),
          getCOL_work_phone().getEntityValue().getValue().toString(),
          getCOL_entity_product().getEntityValue().getValue().toString(),
          getCOL_order_sales_type_full().getEntityValue().getValue().toString(),
          getCOL_employee_id().getEntityValue().getValue().toString(),
          getCOL_manual_employee_id().getEntityValue().getValue().toString(),
          getCOL_date_db_changed().getEntityValue().getValue().toString()
                );
            }

}