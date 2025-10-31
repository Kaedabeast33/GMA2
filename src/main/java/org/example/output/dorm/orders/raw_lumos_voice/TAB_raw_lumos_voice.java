package org.example.output.dorm.orders.raw_lumos_voice;


import org.example.gma.templates.TableTemplate;
import org.example.gma.templates.QueryTemplate;
import org.example.gma.templates.ColumnTemplate;


import org.example.output.dorm.orders.raw_lumos_voice.columns.COL_db_id;
import org.example.output.dorm.orders.raw_lumos_voice.columns.COL_wtn;
import org.example.output.dorm.orders.raw_lumos_voice.columns.COL_account_number;
import org.example.output.dorm.orders.raw_lumos_voice.columns.COL_appointment_date;
import org.example.output.dorm.orders.raw_lumos_voice.columns.COL_city;
import org.example.output.dorm.orders.raw_lumos_voice.columns.COL_create_date;
import org.example.output.dorm.orders.raw_lumos_voice.columns.COL_create_time;
import org.example.output.dorm.orders.raw_lumos_voice.columns.COL_customer_name;
import org.example.output.dorm.orders.raw_lumos_voice.columns.COL_order_number;
import org.example.output.dorm.orders.raw_lumos_voice.columns.COL_order_status;
import org.example.output.dorm.orders.raw_lumos_voice.columns.COL_partner_id;
import org.example.output.dorm.orders.raw_lumos_voice.columns.COL_partner_name;
import org.example.output.dorm.orders.raw_lumos_voice.columns.COL_partner_reference_id;
import org.example.output.dorm.orders.raw_lumos_voice.columns.COL_pops_order_id;
import org.example.output.dorm.orders.raw_lumos_voice.columns.COL_sales_person_id;
import org.example.output.dorm.orders.raw_lumos_voice.columns.COL_sales_person_name;
import org.example.output.dorm.orders.raw_lumos_voice.columns.COL_sales_person_username;
import org.example.output.dorm.orders.raw_lumos_voice.columns.COL_state;
import org.example.output.dorm.orders.raw_lumos_voice.columns.COL_street_address;
import org.example.output.dorm.orders.raw_lumos_voice.columns.COL_subcategory_id;
import org.example.output.dorm.orders.raw_lumos_voice.columns.COL_subcategory_name;
import org.example.output.dorm.orders.raw_lumos_voice.columns.COL_subcategory_username;
import org.example.output.dorm.orders.raw_lumos_voice.columns.COL_unit;
import org.example.output.dorm.orders.raw_lumos_voice.columns.COL_voice_product_family;
import org.example.output.dorm.orders.raw_lumos_voice.columns.COL_voice_product_name;
import org.example.output.dorm.orders.raw_lumos_voice.columns.COL_voice_quantity;
import org.example.output.dorm.orders.raw_lumos_voice.columns.COL_zip_code;
import org.example.output.dorm.orders.raw_lumos_voice.columns.COL_latest_insert_date;
import org.example.output.dorm.orders.raw_lumos_voice.columns.COL_employee_id;
import org.example.output.dorm.orders.raw_lumos_voice.columns.COL_manual_employee_id;

import java.util.List;

import org.example.ContextInstance.KDBContext;

public class TAB_raw_lumos_voice extends TableTemplate {

    public TAB_raw_lumos_voice() {
        super(
            "raw_lumos_voice"
,
            "Table storing raw Lumos voice order records"
,
            new String[]{"orders", "lumos", "voice", "raw"},
            "tab2e8aa17d-01eb-4ce6-91d7-64e87a99c490"
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





    private final ColumnTemplate COL_wtn = new COL_wtn();

    public ColumnTemplate getCOL_wtn() {
        return COL_wtn;
    }





    private final ColumnTemplate COL_account_number = new COL_account_number();

    public ColumnTemplate getCOL_account_number() {
        return COL_account_number;
    }





    private final ColumnTemplate COL_appointment_date = new COL_appointment_date();

    public ColumnTemplate getCOL_appointment_date() {
        return COL_appointment_date;
    }





    private final ColumnTemplate COL_city = new COL_city();

    public ColumnTemplate getCOL_city() {
        return COL_city;
    }





    private final ColumnTemplate COL_create_date = new COL_create_date();

    public ColumnTemplate getCOL_create_date() {
        return COL_create_date;
    }





    private final ColumnTemplate COL_create_time = new COL_create_time();

    public ColumnTemplate getCOL_create_time() {
        return COL_create_time;
    }





    private final ColumnTemplate COL_customer_name = new COL_customer_name();

    public ColumnTemplate getCOL_customer_name() {
        return COL_customer_name;
    }





    private final ColumnTemplate COL_order_number = new COL_order_number();

    public ColumnTemplate getCOL_order_number() {
        return COL_order_number;
    }





    private final ColumnTemplate COL_order_status = new COL_order_status();

    public ColumnTemplate getCOL_order_status() {
        return COL_order_status;
    }





    private final ColumnTemplate COL_partner_id = new COL_partner_id();

    public ColumnTemplate getCOL_partner_id() {
        return COL_partner_id;
    }





    private final ColumnTemplate COL_partner_name = new COL_partner_name();

    public ColumnTemplate getCOL_partner_name() {
        return COL_partner_name;
    }





    private final ColumnTemplate COL_partner_reference_id = new COL_partner_reference_id();

    public ColumnTemplate getCOL_partner_reference_id() {
        return COL_partner_reference_id;
    }





    private final ColumnTemplate COL_pops_order_id = new COL_pops_order_id();

    public ColumnTemplate getCOL_pops_order_id() {
        return COL_pops_order_id;
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





    private final ColumnTemplate COL_street_address = new COL_street_address();

    public ColumnTemplate getCOL_street_address() {
        return COL_street_address;
    }





    private final ColumnTemplate COL_subcategory_id = new COL_subcategory_id();

    public ColumnTemplate getCOL_subcategory_id() {
        return COL_subcategory_id;
    }





    private final ColumnTemplate COL_subcategory_name = new COL_subcategory_name();

    public ColumnTemplate getCOL_subcategory_name() {
        return COL_subcategory_name;
    }





    private final ColumnTemplate COL_subcategory_username = new COL_subcategory_username();

    public ColumnTemplate getCOL_subcategory_username() {
        return COL_subcategory_username;
    }





    private final ColumnTemplate COL_unit = new COL_unit();

    public ColumnTemplate getCOL_unit() {
        return COL_unit;
    }





    private final ColumnTemplate COL_voice_product_family = new COL_voice_product_family();

    public ColumnTemplate getCOL_voice_product_family() {
        return COL_voice_product_family;
    }





    private final ColumnTemplate COL_voice_product_name = new COL_voice_product_name();

    public ColumnTemplate getCOL_voice_product_name() {
        return COL_voice_product_name;
    }





    private final ColumnTemplate COL_voice_quantity = new COL_voice_quantity();

    public ColumnTemplate getCOL_voice_quantity() {
        return COL_voice_quantity;
    }





    private final ColumnTemplate COL_zip_code = new COL_zip_code();

    public ColumnTemplate getCOL_zip_code() {
        return COL_zip_code;
    }





    private final ColumnTemplate COL_latest_insert_date = new COL_latest_insert_date();

    public ColumnTemplate getCOL_latest_insert_date() {
        return COL_latest_insert_date;
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
    return String.format("('%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s')\n",
                      getCOL_db_id().getEntityValue().getValue().toString(),
          getCOL_wtn().getEntityValue().getValue().toString(),
          getCOL_account_number().getEntityValue().getValue().toString(),
          getCOL_appointment_date().getEntityValue().getValue().toString(),
          getCOL_city().getEntityValue().getValue().toString(),
          getCOL_create_date().getEntityValue().getValue().toString(),
          getCOL_create_time().getEntityValue().getValue().toString(),
          getCOL_customer_name().getEntityValue().getValue().toString(),
          getCOL_order_number().getEntityValue().getValue().toString(),
          getCOL_order_status().getEntityValue().getValue().toString(),
          getCOL_partner_id().getEntityValue().getValue().toString(),
          getCOL_partner_name().getEntityValue().getValue().toString(),
          getCOL_partner_reference_id().getEntityValue().getValue().toString(),
          getCOL_pops_order_id().getEntityValue().getValue().toString(),
          getCOL_sales_person_id().getEntityValue().getValue().toString(),
          getCOL_sales_person_name().getEntityValue().getValue().toString(),
          getCOL_sales_person_username().getEntityValue().getValue().toString(),
          getCOL_state().getEntityValue().getValue().toString(),
          getCOL_street_address().getEntityValue().getValue().toString(),
          getCOL_subcategory_id().getEntityValue().getValue().toString(),
          getCOL_subcategory_name().getEntityValue().getValue().toString(),
          getCOL_subcategory_username().getEntityValue().getValue().toString(),
          getCOL_unit().getEntityValue().getValue().toString(),
          getCOL_voice_product_family().getEntityValue().getValue().toString(),
          getCOL_voice_product_name().getEntityValue().getValue().toString(),
          getCOL_voice_quantity().getEntityValue().getValue().toString(),
          getCOL_zip_code().getEntityValue().getValue().toString(),
          getCOL_latest_insert_date().getEntityValue().getValue().toString(),
          getCOL_employee_id().getEntityValue().getValue().toString(),
          getCOL_manual_employee_id().getEntityValue().getValue().toString()
    );
}
            @Override
            public String getValues(String arg) {
                return String.format("('%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s')\n",
          getCOL_db_id().getEntityValue().getValue().toString(),
          getCOL_wtn().getEntityValue().getValue().toString(),
          getCOL_account_number().getEntityValue().getValue().toString(),
          getCOL_appointment_date().getEntityValue().getValue().toString(),
          getCOL_city().getEntityValue().getValue().toString(),
          getCOL_create_date().getEntityValue().getValue().toString(),
          getCOL_create_time().getEntityValue().getValue().toString(),
          getCOL_customer_name().getEntityValue().getValue().toString(),
          getCOL_order_number().getEntityValue().getValue().toString(),
          getCOL_order_status().getEntityValue().getValue().toString(),
          getCOL_partner_id().getEntityValue().getValue().toString(),
          getCOL_partner_name().getEntityValue().getValue().toString(),
          getCOL_partner_reference_id().getEntityValue().getValue().toString(),
          getCOL_pops_order_id().getEntityValue().getValue().toString(),
          getCOL_sales_person_id().getEntityValue().getValue().toString(),
          getCOL_sales_person_name().getEntityValue().getValue().toString(),
          getCOL_sales_person_username().getEntityValue().getValue().toString(),
          getCOL_state().getEntityValue().getValue().toString(),
          getCOL_street_address().getEntityValue().getValue().toString(),
          getCOL_subcategory_id().getEntityValue().getValue().toString(),
          getCOL_subcategory_name().getEntityValue().getValue().toString(),
          getCOL_subcategory_username().getEntityValue().getValue().toString(),
          getCOL_unit().getEntityValue().getValue().toString(),
          getCOL_voice_product_family().getEntityValue().getValue().toString(),
          getCOL_voice_product_name().getEntityValue().getValue().toString(),
          getCOL_voice_quantity().getEntityValue().getValue().toString(),
          getCOL_zip_code().getEntityValue().getValue().toString(),
          getCOL_latest_insert_date().getEntityValue().getValue().toString(),
          getCOL_employee_id().getEntityValue().getValue().toString(),
          getCOL_manual_employee_id().getEntityValue().getValue().toString()
                );
            }

}