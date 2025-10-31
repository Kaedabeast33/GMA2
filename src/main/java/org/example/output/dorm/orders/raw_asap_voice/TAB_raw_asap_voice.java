package org.example.output.dorm.orders.raw_asap_voice;


import org.example.gma.templates.TableTemplate;
import org.example.gma.templates.QueryTemplate;
import org.example.gma.templates.ColumnTemplate;


import org.example.output.dorm.orders.raw_asap_voice.columns.COL_account_number;
import org.example.output.dorm.orders.raw_asap_voice.columns.COL_asap_order_id;
import org.example.output.dorm.orders.raw_asap_voice.columns.COL_billing_number;
import org.example.output.dorm.orders.raw_asap_voice.columns.COL_business_type;
import org.example.output.dorm.orders.raw_asap_voice.columns.COL_city;
import org.example.output.dorm.orders.raw_asap_voice.columns.COL_create_time;
import org.example.output.dorm.orders.raw_asap_voice.columns.COL_customer_name;
import org.example.output.dorm.orders.raw_asap_voice.columns.COL_customer_type;
import org.example.output.dorm.orders.raw_asap_voice.columns.COL_due_date;
import org.example.output.dorm.orders.raw_asap_voice.columns.COL_local_product_family;
import org.example.output.dorm.orders.raw_asap_voice.columns.COL_local_product_name;
import org.example.output.dorm.orders.raw_asap_voice.columns.COL_local_product_status;
import org.example.output.dorm.orders.raw_asap_voice.columns.COL_local_quantity;
import org.example.output.dorm.orders.raw_asap_voice.columns.COL_master_agent_id;
import org.example.output.dorm.orders.raw_asap_voice.columns.COL_master_agent_name;
import org.example.output.dorm.orders.raw_asap_voice.columns.COL_order_number;
import org.example.output.dorm.orders.raw_asap_voice.columns.COL_order_reference_id;
import org.example.output.dorm.orders.raw_asap_voice.columns.COL_order_status;
import org.example.output.dorm.orders.raw_asap_voice.columns.COL_order_type;
import org.example.output.dorm.orders.raw_asap_voice.columns.COL_outside_sales_rep;
import org.example.output.dorm.orders.raw_asap_voice.columns.COL_partner_reference_id;
import org.example.output.dorm.orders.raw_asap_voice.columns.COL_regional_sales_manager;
import org.example.output.dorm.orders.raw_asap_voice.columns.COL_sales_code;
import org.example.output.dorm.orders.raw_asap_voice.columns.COL_sales_person_id;
import org.example.output.dorm.orders.raw_asap_voice.columns.COL_sales_person_name;
import org.example.output.dorm.orders.raw_asap_voice.columns.COL_sales_person_username;
import org.example.output.dorm.orders.raw_asap_voice.columns.COL_state;
import org.example.output.dorm.orders.raw_asap_voice.columns.COL_status_change_date;
import org.example.output.dorm.orders.raw_asap_voice.columns.COL_street_address;
import org.example.output.dorm.orders.raw_asap_voice.columns.COL_sub_agent_name;
import org.example.output.dorm.orders.raw_asap_voice.columns.COL_sub_agent_username;
import org.example.output.dorm.orders.raw_asap_voice.columns.COL_subagent_id;
import org.example.output.dorm.orders.raw_asap_voice.columns.COL_type_of_sale;
import org.example.output.dorm.orders.raw_asap_voice.columns.COL_unit;
import org.example.output.dorm.orders.raw_asap_voice.columns.COL_wtn;
import org.example.output.dorm.orders.raw_asap_voice.columns.COL_zip_code;
import org.example.output.dorm.orders.raw_asap_voice.columns.COL_latest_insert_date;
import org.example.output.dorm.orders.raw_asap_voice.columns.COL_db_id;
import org.example.output.dorm.orders.raw_asap_voice.columns.COL_employee_id;
import org.example.output.dorm.orders.raw_asap_voice.columns.COL_manual_employee_id;

import java.util.List;

import org.example.ContextInstance.KDBContext;

public class TAB_raw_asap_voice extends TableTemplate {

    public TAB_raw_asap_voice() {
        super(
            "raw_asap_voice"
,
            "Table storing raw ASAP voice order records"
,
            new String[]{"orders", "asap", "voice", "raw"},
            "tab0b1b4822-dabd-4446-9ac0-476b9e888546"
,
            "dorm"
,
            "orders"

        );
    }




 KDBContext context = KDBContext.KDB_CONTEXT;
    private final ColumnTemplate COL_account_number = new COL_account_number();

    public ColumnTemplate getCOL_account_number() {
        return COL_account_number;
    }





    private final ColumnTemplate COL_asap_order_id = new COL_asap_order_id();

    public ColumnTemplate getCOL_asap_order_id() {
        return COL_asap_order_id;
    }





    private final ColumnTemplate COL_billing_number = new COL_billing_number();

    public ColumnTemplate getCOL_billing_number() {
        return COL_billing_number;
    }





    private final ColumnTemplate COL_business_type = new COL_business_type();

    public ColumnTemplate getCOL_business_type() {
        return COL_business_type;
    }





    private final ColumnTemplate COL_city = new COL_city();

    public ColumnTemplate getCOL_city() {
        return COL_city;
    }





    private final ColumnTemplate COL_create_time = new COL_create_time();

    public ColumnTemplate getCOL_create_time() {
        return COL_create_time;
    }





    private final ColumnTemplate COL_customer_name = new COL_customer_name();

    public ColumnTemplate getCOL_customer_name() {
        return COL_customer_name;
    }





    private final ColumnTemplate COL_customer_type = new COL_customer_type();

    public ColumnTemplate getCOL_customer_type() {
        return COL_customer_type;
    }





    private final ColumnTemplate COL_due_date = new COL_due_date();

    public ColumnTemplate getCOL_due_date() {
        return COL_due_date;
    }





    private final ColumnTemplate COL_local_product_family = new COL_local_product_family();

    public ColumnTemplate getCOL_local_product_family() {
        return COL_local_product_family;
    }





    private final ColumnTemplate COL_local_product_name = new COL_local_product_name();

    public ColumnTemplate getCOL_local_product_name() {
        return COL_local_product_name;
    }





    private final ColumnTemplate COL_local_product_status = new COL_local_product_status();

    public ColumnTemplate getCOL_local_product_status() {
        return COL_local_product_status;
    }





    private final ColumnTemplate COL_local_quantity = new COL_local_quantity();

    public ColumnTemplate getCOL_local_quantity() {
        return COL_local_quantity;
    }





    private final ColumnTemplate COL_master_agent_id = new COL_master_agent_id();

    public ColumnTemplate getCOL_master_agent_id() {
        return COL_master_agent_id;
    }





    private final ColumnTemplate COL_master_agent_name = new COL_master_agent_name();

    public ColumnTemplate getCOL_master_agent_name() {
        return COL_master_agent_name;
    }





    private final ColumnTemplate COL_order_number = new COL_order_number();

    public ColumnTemplate getCOL_order_number() {
        return COL_order_number;
    }





    private final ColumnTemplate COL_order_reference_id = new COL_order_reference_id();

    public ColumnTemplate getCOL_order_reference_id() {
        return COL_order_reference_id;
    }





    private final ColumnTemplate COL_order_status = new COL_order_status();

    public ColumnTemplate getCOL_order_status() {
        return COL_order_status;
    }





    private final ColumnTemplate COL_order_type = new COL_order_type();

    public ColumnTemplate getCOL_order_type() {
        return COL_order_type;
    }





    private final ColumnTemplate COL_outside_sales_rep = new COL_outside_sales_rep();

    public ColumnTemplate getCOL_outside_sales_rep() {
        return COL_outside_sales_rep;
    }





    private final ColumnTemplate COL_partner_reference_id = new COL_partner_reference_id();

    public ColumnTemplate getCOL_partner_reference_id() {
        return COL_partner_reference_id;
    }





    private final ColumnTemplate COL_regional_sales_manager = new COL_regional_sales_manager();

    public ColumnTemplate getCOL_regional_sales_manager() {
        return COL_regional_sales_manager;
    }





    private final ColumnTemplate COL_sales_code = new COL_sales_code();

    public ColumnTemplate getCOL_sales_code() {
        return COL_sales_code;
    }





    private final ColumnTemplate COL_sales_person_id = new COL_sales_person_id();

    public ColumnTemplate getCOL_sales_person_id() {
        return COL_sales_person_id;
    }





    private final ColumnTemplate COL_sales_person_name = new COL_sales_person_name();

    public ColumnTemplate getCOL_sales_person_name() {
        return COL_sales_person_name;
    }





    private final ColumnTemplate COL_sales_person_username = new COL_sales_person_username();

    public ColumnTemplate getCOL_sales_person_username() {
        return COL_sales_person_username;
    }





    private final ColumnTemplate COL_state = new COL_state();

    public ColumnTemplate getCOL_state() {
        return COL_state;
    }





    private final ColumnTemplate COL_status_change_date = new COL_status_change_date();

    public ColumnTemplate getCOL_status_change_date() {
        return COL_status_change_date;
    }





    private final ColumnTemplate COL_street_address = new COL_street_address();

    public ColumnTemplate getCOL_street_address() {
        return COL_street_address;
    }





    private final ColumnTemplate COL_sub_agent_name = new COL_sub_agent_name();

    public ColumnTemplate getCOL_sub_agent_name() {
        return COL_sub_agent_name;
    }





    private final ColumnTemplate COL_sub_agent_username = new COL_sub_agent_username();

    public ColumnTemplate getCOL_sub_agent_username() {
        return COL_sub_agent_username;
    }





    private final ColumnTemplate COL_subagent_id = new COL_subagent_id();

    public ColumnTemplate getCOL_subagent_id() {
        return COL_subagent_id;
    }





    private final ColumnTemplate COL_type_of_sale = new COL_type_of_sale();

    public ColumnTemplate getCOL_type_of_sale() {
        return COL_type_of_sale;
    }





    private final ColumnTemplate COL_unit = new COL_unit();

    public ColumnTemplate getCOL_unit() {
        return COL_unit;
    }





    private final ColumnTemplate COL_wtn = new COL_wtn();

    public ColumnTemplate getCOL_wtn() {
        return COL_wtn;
    }





    private final ColumnTemplate COL_zip_code = new COL_zip_code();

    public ColumnTemplate getCOL_zip_code() {
        return COL_zip_code;
    }





    private final ColumnTemplate COL_latest_insert_date = new COL_latest_insert_date();

    public ColumnTemplate getCOL_latest_insert_date() {
        return COL_latest_insert_date;
    }





    private final ColumnTemplate COL_db_id = new COL_db_id();

    public ColumnTemplate getCOL_db_id() {
        return COL_db_id;
    }





    private final ColumnTemplate COL_employee_id = new COL_employee_id();

    public ColumnTemplate getCOL_employee_id() {
        return COL_employee_id;
    }





    private final ColumnTemplate COL_manual_employee_id = new COL_manual_employee_id();

    public ColumnTemplate getCOL_manual_employee_id() {
        return COL_manual_employee_id;
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
    return String.format("('%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s')\n",
                      getCOL_account_number().getEntityValue().getValue().toString(),
          getCOL_asap_order_id().getEntityValue().getValue().toString(),
          getCOL_billing_number().getEntityValue().getValue().toString(),
          getCOL_business_type().getEntityValue().getValue().toString(),
          getCOL_city().getEntityValue().getValue().toString(),
          getCOL_create_time().getEntityValue().getValue().toString(),
          getCOL_customer_name().getEntityValue().getValue().toString(),
          getCOL_customer_type().getEntityValue().getValue().toString(),
          getCOL_due_date().getEntityValue().getValue().toString(),
          getCOL_local_product_family().getEntityValue().getValue().toString(),
          getCOL_local_product_name().getEntityValue().getValue().toString(),
          getCOL_local_product_status().getEntityValue().getValue().toString(),
          getCOL_local_quantity().getEntityValue().getValue().toString(),
          getCOL_master_agent_id().getEntityValue().getValue().toString(),
          getCOL_master_agent_name().getEntityValue().getValue().toString(),
          getCOL_order_number().getEntityValue().getValue().toString(),
          getCOL_order_reference_id().getEntityValue().getValue().toString(),
          getCOL_order_status().getEntityValue().getValue().toString(),
          getCOL_order_type().getEntityValue().getValue().toString(),
          getCOL_outside_sales_rep().getEntityValue().getValue().toString(),
          getCOL_partner_reference_id().getEntityValue().getValue().toString(),
          getCOL_regional_sales_manager().getEntityValue().getValue().toString(),
          getCOL_sales_code().getEntityValue().getValue().toString(),
          getCOL_sales_person_id().getEntityValue().getValue().toString(),
          getCOL_sales_person_name().getEntityValue().getValue().toString(),
          getCOL_sales_person_username().getEntityValue().getValue().toString(),
          getCOL_state().getEntityValue().getValue().toString(),
          getCOL_status_change_date().getEntityValue().getValue().toString(),
          getCOL_street_address().getEntityValue().getValue().toString(),
          getCOL_sub_agent_name().getEntityValue().getValue().toString(),
          getCOL_sub_agent_username().getEntityValue().getValue().toString(),
          getCOL_subagent_id().getEntityValue().getValue().toString(),
          getCOL_type_of_sale().getEntityValue().getValue().toString(),
          getCOL_unit().getEntityValue().getValue().toString(),
          getCOL_wtn().getEntityValue().getValue().toString(),
          getCOL_zip_code().getEntityValue().getValue().toString(),
          getCOL_latest_insert_date().getEntityValue().getValue().toString(),
          getCOL_db_id().getEntityValue().getValue().toString(),
          getCOL_employee_id().getEntityValue().getValue().toString(),
          getCOL_manual_employee_id().getEntityValue().getValue().toString()
    );
}
            @Override
            public String getValues(String arg) {
                return String.format("('%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s')\n",
          getCOL_account_number().getEntityValue().getValue().toString(),
          getCOL_asap_order_id().getEntityValue().getValue().toString(),
          getCOL_billing_number().getEntityValue().getValue().toString(),
          getCOL_business_type().getEntityValue().getValue().toString(),
          getCOL_city().getEntityValue().getValue().toString(),
          getCOL_create_time().getEntityValue().getValue().toString(),
          getCOL_customer_name().getEntityValue().getValue().toString(),
          getCOL_customer_type().getEntityValue().getValue().toString(),
          getCOL_due_date().getEntityValue().getValue().toString(),
          getCOL_local_product_family().getEntityValue().getValue().toString(),
          getCOL_local_product_name().getEntityValue().getValue().toString(),
          getCOL_local_product_status().getEntityValue().getValue().toString(),
          getCOL_local_quantity().getEntityValue().getValue().toString(),
          getCOL_master_agent_id().getEntityValue().getValue().toString(),
          getCOL_master_agent_name().getEntityValue().getValue().toString(),
          getCOL_order_number().getEntityValue().getValue().toString(),
          getCOL_order_reference_id().getEntityValue().getValue().toString(),
          getCOL_order_status().getEntityValue().getValue().toString(),
          getCOL_order_type().getEntityValue().getValue().toString(),
          getCOL_outside_sales_rep().getEntityValue().getValue().toString(),
          getCOL_partner_reference_id().getEntityValue().getValue().toString(),
          getCOL_regional_sales_manager().getEntityValue().getValue().toString(),
          getCOL_sales_code().getEntityValue().getValue().toString(),
          getCOL_sales_person_id().getEntityValue().getValue().toString(),
          getCOL_sales_person_name().getEntityValue().getValue().toString(),
          getCOL_sales_person_username().getEntityValue().getValue().toString(),
          getCOL_state().getEntityValue().getValue().toString(),
          getCOL_status_change_date().getEntityValue().getValue().toString(),
          getCOL_street_address().getEntityValue().getValue().toString(),
          getCOL_sub_agent_name().getEntityValue().getValue().toString(),
          getCOL_sub_agent_username().getEntityValue().getValue().toString(),
          getCOL_subagent_id().getEntityValue().getValue().toString(),
          getCOL_type_of_sale().getEntityValue().getValue().toString(),
          getCOL_unit().getEntityValue().getValue().toString(),
          getCOL_wtn().getEntityValue().getValue().toString(),
          getCOL_zip_code().getEntityValue().getValue().toString(),
          getCOL_latest_insert_date().getEntityValue().getValue().toString(),
          getCOL_db_id().getEntityValue().getValue().toString(),
          getCOL_employee_id().getEntityValue().getValue().toString(),
          getCOL_manual_employee_id().getEntityValue().getValue().toString()
                );
            }

}