package org.example.output.dorm.orders.raw_sp_mobility;


import org.example.gma.templates.TableTemplate;
import org.example.gma.templates.QueryTemplate;
import org.example.gma.templates.ColumnTemplate;


import org.example.output.dorm.orders.raw_sp_mobility.columns.COL_db_id;
import org.example.output.dorm.orders.raw_sp_mobility.columns.COL_account_number;
import org.example.output.dorm.orders.raw_sp_mobility.columns.COL_active_date;
import org.example.output.dorm.orders.raw_sp_mobility.columns.COL_apt_number;
import org.example.output.dorm.orders.raw_sp_mobility.columns.COL_city;
import org.example.output.dorm.orders.raw_sp_mobility.columns.COL_columns;
import org.example.output.dorm.orders.raw_sp_mobility.columns.COL_customer_name;
import org.example.output.dorm.orders.raw_sp_mobility.columns.COL_dealer_code;
import org.example.output.dorm.orders.raw_sp_mobility.columns.COL_email;
import org.example.output.dorm.orders.raw_sp_mobility.columns.COL_employee_id;
import org.example.output.dorm.orders.raw_sp_mobility.columns.COL_line;
import org.example.output.dorm.orders.raw_sp_mobility.columns.COL_line_number;
import org.example.output.dorm.orders.raw_sp_mobility.columns.COL_line_provider;
import org.example.output.dorm.orders.raw_sp_mobility.columns.COL_line_status;
import org.example.output.dorm.orders.raw_sp_mobility.columns.COL_line_type;
import org.example.output.dorm.orders.raw_sp_mobility.columns.COL_order_date;
import org.example.output.dorm.orders.raw_sp_mobility.columns.COL_order_number;
import org.example.output.dorm.orders.raw_sp_mobility.columns.COL_order_time;
import org.example.output.dorm.orders.raw_sp_mobility.columns.COL_phone;
import org.example.output.dorm.orders.raw_sp_mobility.columns.COL_plan;
import org.example.output.dorm.orders.raw_sp_mobility.columns.COL_sales_person;
import org.example.output.dorm.orders.raw_sp_mobility.columns.COL_state;
import org.example.output.dorm.orders.raw_sp_mobility.columns.COL_street_address;
import org.example.output.dorm.orders.raw_sp_mobility.columns.COL_wireless_order_number;
import org.example.output.dorm.orders.raw_sp_mobility.columns.COL_wireless_status;
import org.example.output.dorm.orders.raw_sp_mobility.columns.COL_zip;
import org.example.output.dorm.orders.raw_sp_mobility.columns.COL_latest_insert_date;
import org.example.output.dorm.orders.raw_sp_mobility.columns.COL_manual_employee_id;

import java.util.List;

import org.example.ContextInstance.KDBContext;

public class TAB_raw_sp_mobility extends TableTemplate {

    public TAB_raw_sp_mobility() {
        super(
            "raw_sp_mobility"
,
            "Table storing raw SP Mobility order records"
,
            new String[]{"orders", "sp_mobility", "raw"},
            "tab1475e28e-ad7c-42b9-9826-773777520eed"
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





    private final ColumnTemplate COL_active_date = new COL_active_date();

    public ColumnTemplate getCOL_active_date() {
        return COL_active_date;
    }





    private final ColumnTemplate COL_apt_number = new COL_apt_number();

    public ColumnTemplate getCOL_apt_number() {
        return COL_apt_number;
    }





    private final ColumnTemplate COL_city = new COL_city();

    public ColumnTemplate getCOL_city() {
        return COL_city;
    }





    private final ColumnTemplate COL_columns = new COL_columns();

    public ColumnTemplate getCOL_columns() {
        return COL_columns;
    }





    private final ColumnTemplate COL_customer_name = new COL_customer_name();

    public ColumnTemplate getCOL_customer_name() {
        return COL_customer_name;
    }





    private final ColumnTemplate COL_dealer_code = new COL_dealer_code();

    public ColumnTemplate getCOL_dealer_code() {
        return COL_dealer_code;
    }





    private final ColumnTemplate COL_email = new COL_email();

    public ColumnTemplate getCOL_email() {
        return COL_email;
    }





    private final ColumnTemplate COL_employee_id = new COL_employee_id();

    public ColumnTemplate getCOL_employee_id() {
        return COL_employee_id;
    }





    private final ColumnTemplate COL_line = new COL_line();

    public ColumnTemplate getCOL_line() {
        return COL_line;
    }





    private final ColumnTemplate COL_line_number = new COL_line_number();

    public ColumnTemplate getCOL_line_number() {
        return COL_line_number;
    }





    private final ColumnTemplate COL_line_provider = new COL_line_provider();

    public ColumnTemplate getCOL_line_provider() {
        return COL_line_provider;
    }





    private final ColumnTemplate COL_line_status = new COL_line_status();

    public ColumnTemplate getCOL_line_status() {
        return COL_line_status;
    }





    private final ColumnTemplate COL_line_type = new COL_line_type();

    public ColumnTemplate getCOL_line_type() {
        return COL_line_type;
    }





    private final ColumnTemplate COL_order_date = new COL_order_date();

    public ColumnTemplate getCOL_order_date() {
        return COL_order_date;
    }





    private final ColumnTemplate COL_order_number = new COL_order_number();

    public ColumnTemplate getCOL_order_number() {
        return COL_order_number;
    }





    private final ColumnTemplate COL_order_time = new COL_order_time();

    public ColumnTemplate getCOL_order_time() {
        return COL_order_time;
    }





    private final ColumnTemplate COL_phone = new COL_phone();

    public ColumnTemplate getCOL_phone() {
        return COL_phone;
    }





    private final ColumnTemplate COL_plan = new COL_plan();

    public ColumnTemplate getCOL_plan() {
        return COL_plan;
    }





    private final ColumnTemplate COL_sales_person = new COL_sales_person();

    public ColumnTemplate getCOL_sales_person() {
        return COL_sales_person;
    }





    private final ColumnTemplate COL_state = new COL_state();

    public ColumnTemplate getCOL_state() {
        return COL_state;
    }





    private final ColumnTemplate COL_street_address = new COL_street_address();

    public ColumnTemplate getCOL_street_address() {
        return COL_street_address;
    }





    private final ColumnTemplate COL_wireless_order_number = new COL_wireless_order_number();

    public ColumnTemplate getCOL_wireless_order_number() {
        return COL_wireless_order_number;
    }





    private final ColumnTemplate COL_wireless_status = new COL_wireless_status();

    public ColumnTemplate getCOL_wireless_status() {
        return COL_wireless_status;
    }





    private final ColumnTemplate COL_zip = new COL_zip();

    public ColumnTemplate getCOL_zip() {
        return COL_zip;
    }





    private final ColumnTemplate COL_latest_insert_date = new COL_latest_insert_date();

    public ColumnTemplate getCOL_latest_insert_date() {
        return COL_latest_insert_date;
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
    return String.format("('%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s')\n",
                      getCOL_db_id().getEntityValue().getValue().toString(),
          getCOL_account_number().getEntityValue().getValue().toString(),
          getCOL_active_date().getEntityValue().getValue().toString(),
          getCOL_apt_number().getEntityValue().getValue().toString(),
          getCOL_city().getEntityValue().getValue().toString(),
          getCOL_columns().getEntityValue().getValue().toString(),
          getCOL_customer_name().getEntityValue().getValue().toString(),
          getCOL_dealer_code().getEntityValue().getValue().toString(),
          getCOL_email().getEntityValue().getValue().toString(),
          getCOL_employee_id().getEntityValue().getValue().toString(),
          getCOL_line().getEntityValue().getValue().toString(),
          getCOL_line_number().getEntityValue().getValue().toString(),
          getCOL_line_provider().getEntityValue().getValue().toString(),
          getCOL_line_status().getEntityValue().getValue().toString(),
          getCOL_line_type().getEntityValue().getValue().toString(),
          getCOL_order_date().getEntityValue().getValue().toString(),
          getCOL_order_number().getEntityValue().getValue().toString(),
          getCOL_order_time().getEntityValue().getValue().toString(),
          getCOL_phone().getEntityValue().getValue().toString(),
          getCOL_plan().getEntityValue().getValue().toString(),
          getCOL_sales_person().getEntityValue().getValue().toString(),
          getCOL_state().getEntityValue().getValue().toString(),
          getCOL_street_address().getEntityValue().getValue().toString(),
          getCOL_wireless_order_number().getEntityValue().getValue().toString(),
          getCOL_wireless_status().getEntityValue().getValue().toString(),
          getCOL_zip().getEntityValue().getValue().toString(),
          getCOL_latest_insert_date().getEntityValue().getValue().toString(),
          getCOL_manual_employee_id().getEntityValue().getValue().toString()
    );
}
            @Override
            public String getValues(String arg) {
                return String.format("('%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s')\n",
          getCOL_db_id().getEntityValue().getValue().toString(),
          getCOL_account_number().getEntityValue().getValue().toString(),
          getCOL_active_date().getEntityValue().getValue().toString(),
          getCOL_apt_number().getEntityValue().getValue().toString(),
          getCOL_city().getEntityValue().getValue().toString(),
          getCOL_columns().getEntityValue().getValue().toString(),
          getCOL_customer_name().getEntityValue().getValue().toString(),
          getCOL_dealer_code().getEntityValue().getValue().toString(),
          getCOL_email().getEntityValue().getValue().toString(),
          getCOL_employee_id().getEntityValue().getValue().toString(),
          getCOL_line().getEntityValue().getValue().toString(),
          getCOL_line_number().getEntityValue().getValue().toString(),
          getCOL_line_provider().getEntityValue().getValue().toString(),
          getCOL_line_status().getEntityValue().getValue().toString(),
          getCOL_line_type().getEntityValue().getValue().toString(),
          getCOL_order_date().getEntityValue().getValue().toString(),
          getCOL_order_number().getEntityValue().getValue().toString(),
          getCOL_order_time().getEntityValue().getValue().toString(),
          getCOL_phone().getEntityValue().getValue().toString(),
          getCOL_plan().getEntityValue().getValue().toString(),
          getCOL_sales_person().getEntityValue().getValue().toString(),
          getCOL_state().getEntityValue().getValue().toString(),
          getCOL_street_address().getEntityValue().getValue().toString(),
          getCOL_wireless_order_number().getEntityValue().getValue().toString(),
          getCOL_wireless_status().getEntityValue().getValue().toString(),
          getCOL_zip().getEntityValue().getValue().toString(),
          getCOL_latest_insert_date().getEntityValue().getValue().toString(),
          getCOL_manual_employee_id().getEntityValue().getValue().toString()
                );
            }

}