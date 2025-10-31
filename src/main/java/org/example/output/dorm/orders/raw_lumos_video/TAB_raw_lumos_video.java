package org.example.output.dorm.orders.raw_lumos_video;


import org.example.gma.templates.TableTemplate;
import org.example.gma.templates.QueryTemplate;
import org.example.gma.templates.ColumnTemplate;


import org.example.output.dorm.orders.raw_lumos_video.columns.COL_db_id;
import org.example.output.dorm.orders.raw_lumos_video.columns.COL_wtn;
import org.example.output.dorm.orders.raw_lumos_video.columns.COL_account_number;
import org.example.output.dorm.orders.raw_lumos_video.columns.COL_appointment_date;
import org.example.output.dorm.orders.raw_lumos_video.columns.COL_city;
import org.example.output.dorm.orders.raw_lumos_video.columns.COL_columns;
import org.example.output.dorm.orders.raw_lumos_video.columns.COL_create_date;
import org.example.output.dorm.orders.raw_lumos_video.columns.COL_create_time;
import org.example.output.dorm.orders.raw_lumos_video.columns.COL_customer_name;
import org.example.output.dorm.orders.raw_lumos_video.columns.COL_order_number;
import org.example.output.dorm.orders.raw_lumos_video.columns.COL_order_status;
import org.example.output.dorm.orders.raw_lumos_video.columns.COL_order_type;
import org.example.output.dorm.orders.raw_lumos_video.columns.COL_partner_id;
import org.example.output.dorm.orders.raw_lumos_video.columns.COL_partner_name;
import org.example.output.dorm.orders.raw_lumos_video.columns.COL_partner_reference_id;
import org.example.output.dorm.orders.raw_lumos_video.columns.COL_pops_order_id;
import org.example.output.dorm.orders.raw_lumos_video.columns.COL_port;
import org.example.output.dorm.orders.raw_lumos_video.columns.COL_sales_code;
import org.example.output.dorm.orders.raw_lumos_video.columns.COL_sales_person_id;
import org.example.output.dorm.orders.raw_lumos_video.columns.COL_sales_person_name;
import org.example.output.dorm.orders.raw_lumos_video.columns.COL_sales_person_username;
import org.example.output.dorm.orders.raw_lumos_video.columns.COL_state;
import org.example.output.dorm.orders.raw_lumos_video.columns.COL_status_change_date;
import org.example.output.dorm.orders.raw_lumos_video.columns.COL_street_address;
import org.example.output.dorm.orders.raw_lumos_video.columns.COL_subcategory_id;
import org.example.output.dorm.orders.raw_lumos_video.columns.COL_subcategory_name;
import org.example.output.dorm.orders.raw_lumos_video.columns.COL_subcategory_username;
import org.example.output.dorm.orders.raw_lumos_video.columns.COL_unit;
import org.example.output.dorm.orders.raw_lumos_video.columns.COL_video_product_family;
import org.example.output.dorm.orders.raw_lumos_video.columns.COL_video_product_name;
import org.example.output.dorm.orders.raw_lumos_video.columns.COL_video_quantity;
import org.example.output.dorm.orders.raw_lumos_video.columns.COL_zip_code;
import org.example.output.dorm.orders.raw_lumos_video.columns.COL_latest_insert_date;
import org.example.output.dorm.orders.raw_lumos_video.columns.COL_employee_id;
import org.example.output.dorm.orders.raw_lumos_video.columns.COL_manual_employee_id;

import java.util.List;

import org.example.ContextInstance.KDBContext;

public class TAB_raw_lumos_video extends TableTemplate {

    public TAB_raw_lumos_video() {
        super(
            "raw_lumos_video"
,
            "Table storing raw Lumos video order records"
,
            new String[]{"orders", "lumos", "video", "raw"},
            "tabf4419eee-3074-41a0-8925-6aa49923ff5d"
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





    private final ColumnTemplate COL_columns = new COL_columns();

    public ColumnTemplate getCOL_columns() {
        return COL_columns;
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





    private final ColumnTemplate COL_order_type = new COL_order_type();

    public ColumnTemplate getCOL_order_type() {
        return COL_order_type;
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





    private final ColumnTemplate COL_port = new COL_port();

    public ColumnTemplate getCOL_port() {
        return COL_port;
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





    private final ColumnTemplate COL_video_product_family = new COL_video_product_family();

    public ColumnTemplate getCOL_video_product_family() {
        return COL_video_product_family;
    }





    private final ColumnTemplate COL_video_product_name = new COL_video_product_name();

    public ColumnTemplate getCOL_video_product_name() {
        return COL_video_product_name;
    }





    private final ColumnTemplate COL_video_quantity = new COL_video_quantity();

    public ColumnTemplate getCOL_video_quantity() {
        return COL_video_quantity;
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
    return String.format("('%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s')\n",
                      getCOL_db_id().getEntityValue().getValue().toString(),
          getCOL_wtn().getEntityValue().getValue().toString(),
          getCOL_account_number().getEntityValue().getValue().toString(),
          getCOL_appointment_date().getEntityValue().getValue().toString(),
          getCOL_city().getEntityValue().getValue().toString(),
          getCOL_columns().getEntityValue().getValue().toString(),
          getCOL_create_date().getEntityValue().getValue().toString(),
          getCOL_create_time().getEntityValue().getValue().toString(),
          getCOL_customer_name().getEntityValue().getValue().toString(),
          getCOL_order_number().getEntityValue().getValue().toString(),
          getCOL_order_status().getEntityValue().getValue().toString(),
          getCOL_order_type().getEntityValue().getValue().toString(),
          getCOL_partner_id().getEntityValue().getValue().toString(),
          getCOL_partner_name().getEntityValue().getValue().toString(),
          getCOL_partner_reference_id().getEntityValue().getValue().toString(),
          getCOL_pops_order_id().getEntityValue().getValue().toString(),
          getCOL_port().getEntityValue().getValue().toString(),
          getCOL_sales_code().getEntityValue().getValue().toString(),
          getCOL_sales_person_id().getEntityValue().getValue().toString(),
          getCOL_sales_person_name().getEntityValue().getValue().toString(),
          getCOL_sales_person_username().getEntityValue().getValue().toString(),
          getCOL_state().getEntityValue().getValue().toString(),
          getCOL_status_change_date().getEntityValue().getValue().toString(),
          getCOL_street_address().getEntityValue().getValue().toString(),
          getCOL_subcategory_id().getEntityValue().getValue().toString(),
          getCOL_subcategory_name().getEntityValue().getValue().toString(),
          getCOL_subcategory_username().getEntityValue().getValue().toString(),
          getCOL_unit().getEntityValue().getValue().toString(),
          getCOL_video_product_family().getEntityValue().getValue().toString(),
          getCOL_video_product_name().getEntityValue().getValue().toString(),
          getCOL_video_quantity().getEntityValue().getValue().toString(),
          getCOL_zip_code().getEntityValue().getValue().toString(),
          getCOL_latest_insert_date().getEntityValue().getValue().toString(),
          getCOL_employee_id().getEntityValue().getValue().toString(),
          getCOL_manual_employee_id().getEntityValue().getValue().toString()
    );
}
            @Override
            public String getValues(String arg) {
                return String.format("('%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s')\n",
          getCOL_db_id().getEntityValue().getValue().toString(),
          getCOL_wtn().getEntityValue().getValue().toString(),
          getCOL_account_number().getEntityValue().getValue().toString(),
          getCOL_appointment_date().getEntityValue().getValue().toString(),
          getCOL_city().getEntityValue().getValue().toString(),
          getCOL_columns().getEntityValue().getValue().toString(),
          getCOL_create_date().getEntityValue().getValue().toString(),
          getCOL_create_time().getEntityValue().getValue().toString(),
          getCOL_customer_name().getEntityValue().getValue().toString(),
          getCOL_order_number().getEntityValue().getValue().toString(),
          getCOL_order_status().getEntityValue().getValue().toString(),
          getCOL_order_type().getEntityValue().getValue().toString(),
          getCOL_partner_id().getEntityValue().getValue().toString(),
          getCOL_partner_name().getEntityValue().getValue().toString(),
          getCOL_partner_reference_id().getEntityValue().getValue().toString(),
          getCOL_pops_order_id().getEntityValue().getValue().toString(),
          getCOL_port().getEntityValue().getValue().toString(),
          getCOL_sales_code().getEntityValue().getValue().toString(),
          getCOL_sales_person_id().getEntityValue().getValue().toString(),
          getCOL_sales_person_name().getEntityValue().getValue().toString(),
          getCOL_sales_person_username().getEntityValue().getValue().toString(),
          getCOL_state().getEntityValue().getValue().toString(),
          getCOL_status_change_date().getEntityValue().getValue().toString(),
          getCOL_street_address().getEntityValue().getValue().toString(),
          getCOL_subcategory_id().getEntityValue().getValue().toString(),
          getCOL_subcategory_name().getEntityValue().getValue().toString(),
          getCOL_subcategory_username().getEntityValue().getValue().toString(),
          getCOL_unit().getEntityValue().getValue().toString(),
          getCOL_video_product_family().getEntityValue().getValue().toString(),
          getCOL_video_product_name().getEntityValue().getValue().toString(),
          getCOL_video_quantity().getEntityValue().getValue().toString(),
          getCOL_zip_code().getEntityValue().getValue().toString(),
          getCOL_latest_insert_date().getEntityValue().getValue().toString(),
          getCOL_employee_id().getEntityValue().getValue().toString(),
          getCOL_manual_employee_id().getEntityValue().getValue().toString()
                );
            }

}