package org.example.output.dorm.orders.raw_sp_internet;


import org.example.gma.templates.TableTemplate;
import org.example.gma.templates.QueryTemplate;
import org.example.gma.templates.ColumnTemplate;


import org.example.output.dorm.orders.raw_sp_internet.columns.COL_db_id;
import org.example.output.dorm.orders.raw_sp_internet.columns.COL_apt_number;
import org.example.output.dorm.orders.raw_sp_internet.columns.COL_campaign;
import org.example.output.dorm.orders.raw_sp_internet.columns.COL_city;
import org.example.output.dorm.orders.raw_sp_internet.columns.COL_columns;
import org.example.output.dorm.orders.raw_sp_internet.columns.COL_customer_name;
import org.example.output.dorm.orders.raw_sp_internet.columns.COL_dealer_code_internet;
import org.example.output.dorm.orders.raw_sp_internet.columns.COL_email;
import org.example.output.dorm.orders.raw_sp_internet.columns.COL_employee_id;
import org.example.output.dorm.orders.raw_sp_internet.columns.COL_internet_account_number;
import org.example.output.dorm.orders.raw_sp_internet.columns.COL_internet_cancel_date;
import org.example.output.dorm.orders.raw_sp_internet.columns.COL_internet_cancel_reason;
import org.example.output.dorm.orders.raw_sp_internet.columns.COL_internet_highest_package_available;
import org.example.output.dorm.orders.raw_sp_internet.columns.COL_internet_install_date;
import org.example.output.dorm.orders.raw_sp_internet.columns.COL_internet_order_number;
import org.example.output.dorm.orders.raw_sp_internet.columns.COL_internet_order_type;
import org.example.output.dorm.orders.raw_sp_internet.columns.COL_internet_package;
import org.example.output.dorm.orders.raw_sp_internet.columns.COL_internet_provider;
import org.example.output.dorm.orders.raw_sp_internet.columns.COL_internet_status;
import org.example.output.dorm.orders.raw_sp_internet.columns.COL_order_date;
import org.example.output.dorm.orders.raw_sp_internet.columns.COL_order_id;
import org.example.output.dorm.orders.raw_sp_internet.columns.COL_order_time;
import org.example.output.dorm.orders.raw_sp_internet.columns.COL_phone;
import org.example.output.dorm.orders.raw_sp_internet.columns.COL_sales_person;
import org.example.output.dorm.orders.raw_sp_internet.columns.COL_speed;
import org.example.output.dorm.orders.raw_sp_internet.columns.COL_state;
import org.example.output.dorm.orders.raw_sp_internet.columns.COL_street_address;
import org.example.output.dorm.orders.raw_sp_internet.columns.COL_zip;
import org.example.output.dorm.orders.raw_sp_internet.columns.COL_latest_insert_date;
import org.example.output.dorm.orders.raw_sp_internet.columns.COL_manual_employee_id;

import java.util.List;

import org.example.ContextInstance.KDBContext;

public class TAB_raw_sp_internet extends TableTemplate {

    public TAB_raw_sp_internet() {
        super(
            "raw_sp_internet"
,
            "Table storing raw SP Internet order records"
,
            new String[]{"orders", "sp_internet", "raw"},
            "taba8cc112e-c796-4810-88e0-5211e5910f3c"
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





    private final ColumnTemplate COL_apt_number = new COL_apt_number();

    public ColumnTemplate getCOL_apt_number() {
        return COL_apt_number;
    }





    private final ColumnTemplate COL_campaign = new COL_campaign();

    public ColumnTemplate getCOL_campaign() {
        return COL_campaign;
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





    private final ColumnTemplate COL_dealer_code_internet = new COL_dealer_code_internet();

    public ColumnTemplate getCOL_dealer_code_internet() {
        return COL_dealer_code_internet;
    }





    private final ColumnTemplate COL_email = new COL_email();

    public ColumnTemplate getCOL_email() {
        return COL_email;
    }





    private final ColumnTemplate COL_employee_id = new COL_employee_id();

    public ColumnTemplate getCOL_employee_id() {
        return COL_employee_id;
    }





    private final ColumnTemplate COL_internet_account_number = new COL_internet_account_number();

    public ColumnTemplate getCOL_internet_account_number() {
        return COL_internet_account_number;
    }





    private final ColumnTemplate COL_internet_cancel_date = new COL_internet_cancel_date();

    public ColumnTemplate getCOL_internet_cancel_date() {
        return COL_internet_cancel_date;
    }





    private final ColumnTemplate COL_internet_cancel_reason = new COL_internet_cancel_reason();

    public ColumnTemplate getCOL_internet_cancel_reason() {
        return COL_internet_cancel_reason;
    }





    private final ColumnTemplate COL_internet_highest_package_available = new COL_internet_highest_package_available();

    public ColumnTemplate getCOL_internet_highest_package_available() {
        return COL_internet_highest_package_available;
    }





    private final ColumnTemplate COL_internet_install_date = new COL_internet_install_date();

    public ColumnTemplate getCOL_internet_install_date() {
        return COL_internet_install_date;
    }





    private final ColumnTemplate COL_internet_order_number = new COL_internet_order_number();

    public ColumnTemplate getCOL_internet_order_number() {
        return COL_internet_order_number;
    }





    private final ColumnTemplate COL_internet_order_type = new COL_internet_order_type();

    public ColumnTemplate getCOL_internet_order_type() {
        return COL_internet_order_type;
    }





    private final ColumnTemplate COL_internet_package = new COL_internet_package();

    public ColumnTemplate getCOL_internet_package() {
        return COL_internet_package;
    }





    private final ColumnTemplate COL_internet_provider = new COL_internet_provider();

    public ColumnTemplate getCOL_internet_provider() {
        return COL_internet_provider;
    }





    private final ColumnTemplate COL_internet_status = new COL_internet_status();

    public ColumnTemplate getCOL_internet_status() {
        return COL_internet_status;
    }





    private final ColumnTemplate COL_order_date = new COL_order_date();

    public ColumnTemplate getCOL_order_date() {
        return COL_order_date;
    }





    private final ColumnTemplate COL_order_id = new COL_order_id();

    public ColumnTemplate getCOL_order_id() {
        return COL_order_id;
    }





    private final ColumnTemplate COL_order_time = new COL_order_time();

    public ColumnTemplate getCOL_order_time() {
        return COL_order_time;
    }





    private final ColumnTemplate COL_phone = new COL_phone();

    public ColumnTemplate getCOL_phone() {
        return COL_phone;
    }





    private final ColumnTemplate COL_sales_person = new COL_sales_person();

    public ColumnTemplate getCOL_sales_person() {
        return COL_sales_person;
    }





    private final ColumnTemplate COL_speed = new COL_speed();

    public ColumnTemplate getCOL_speed() {
        return COL_speed;
    }





    private final ColumnTemplate COL_state = new COL_state();

    public ColumnTemplate getCOL_state() {
        return COL_state;
    }





    private final ColumnTemplate COL_street_address = new COL_street_address();

    public ColumnTemplate getCOL_street_address() {
        return COL_street_address;
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
    return String.format("('%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s')\n",
                      getCOL_db_id().getEntityValue().getValue().toString(),
          getCOL_apt_number().getEntityValue().getValue().toString(),
          getCOL_campaign().getEntityValue().getValue().toString(),
          getCOL_city().getEntityValue().getValue().toString(),
          getCOL_columns().getEntityValue().getValue().toString(),
          getCOL_customer_name().getEntityValue().getValue().toString(),
          getCOL_dealer_code_internet().getEntityValue().getValue().toString(),
          getCOL_email().getEntityValue().getValue().toString(),
          getCOL_employee_id().getEntityValue().getValue().toString(),
          getCOL_internet_account_number().getEntityValue().getValue().toString(),
          getCOL_internet_cancel_date().getEntityValue().getValue().toString(),
          getCOL_internet_cancel_reason().getEntityValue().getValue().toString(),
          getCOL_internet_highest_package_available().getEntityValue().getValue().toString(),
          getCOL_internet_install_date().getEntityValue().getValue().toString(),
          getCOL_internet_order_number().getEntityValue().getValue().toString(),
          getCOL_internet_order_type().getEntityValue().getValue().toString(),
          getCOL_internet_package().getEntityValue().getValue().toString(),
          getCOL_internet_provider().getEntityValue().getValue().toString(),
          getCOL_internet_status().getEntityValue().getValue().toString(),
          getCOL_order_date().getEntityValue().getValue().toString(),
          getCOL_order_id().getEntityValue().getValue().toString(),
          getCOL_order_time().getEntityValue().getValue().toString(),
          getCOL_phone().getEntityValue().getValue().toString(),
          getCOL_sales_person().getEntityValue().getValue().toString(),
          getCOL_speed().getEntityValue().getValue().toString(),
          getCOL_state().getEntityValue().getValue().toString(),
          getCOL_street_address().getEntityValue().getValue().toString(),
          getCOL_zip().getEntityValue().getValue().toString(),
          getCOL_latest_insert_date().getEntityValue().getValue().toString(),
          getCOL_manual_employee_id().getEntityValue().getValue().toString()
    );
}
            @Override
            public String getValues(String arg) {
                return String.format("('%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s')\n",
          getCOL_db_id().getEntityValue().getValue().toString(),
          getCOL_apt_number().getEntityValue().getValue().toString(),
          getCOL_campaign().getEntityValue().getValue().toString(),
          getCOL_city().getEntityValue().getValue().toString(),
          getCOL_columns().getEntityValue().getValue().toString(),
          getCOL_customer_name().getEntityValue().getValue().toString(),
          getCOL_dealer_code_internet().getEntityValue().getValue().toString(),
          getCOL_email().getEntityValue().getValue().toString(),
          getCOL_employee_id().getEntityValue().getValue().toString(),
          getCOL_internet_account_number().getEntityValue().getValue().toString(),
          getCOL_internet_cancel_date().getEntityValue().getValue().toString(),
          getCOL_internet_cancel_reason().getEntityValue().getValue().toString(),
          getCOL_internet_highest_package_available().getEntityValue().getValue().toString(),
          getCOL_internet_install_date().getEntityValue().getValue().toString(),
          getCOL_internet_order_number().getEntityValue().getValue().toString(),
          getCOL_internet_order_type().getEntityValue().getValue().toString(),
          getCOL_internet_package().getEntityValue().getValue().toString(),
          getCOL_internet_provider().getEntityValue().getValue().toString(),
          getCOL_internet_status().getEntityValue().getValue().toString(),
          getCOL_order_date().getEntityValue().getValue().toString(),
          getCOL_order_id().getEntityValue().getValue().toString(),
          getCOL_order_time().getEntityValue().getValue().toString(),
          getCOL_phone().getEntityValue().getValue().toString(),
          getCOL_sales_person().getEntityValue().getValue().toString(),
          getCOL_speed().getEntityValue().getValue().toString(),
          getCOL_state().getEntityValue().getValue().toString(),
          getCOL_street_address().getEntityValue().getValue().toString(),
          getCOL_zip().getEntityValue().getValue().toString(),
          getCOL_latest_insert_date().getEntityValue().getValue().toString(),
          getCOL_manual_employee_id().getEntityValue().getValue().toString()
                );
            }

}