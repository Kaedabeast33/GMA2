package org.example.output.dorm.orders.raw_sp_video;


import org.example.gma.templates.TableTemplate;
import org.example.gma.templates.QueryTemplate;
import org.example.gma.templates.ColumnTemplate;


import org.example.output.dorm.orders.raw_sp_video.columns.COL_db_id;
import org.example.output.dorm.orders.raw_sp_video.columns.COL_abp;
import org.example.output.dorm.orders.raw_sp_video.columns.COL_apt_number;
import org.example.output.dorm.orders.raw_sp_video.columns.COL_atv_device_count;
import org.example.output.dorm.orders.raw_sp_video.columns.COL_city;
import org.example.output.dorm.orders.raw_sp_video.columns.COL_columns;
import org.example.output.dorm.orders.raw_sp_video.columns.COL_customer_name;
import org.example.output.dorm.orders.raw_sp_video.columns.COL_dealer_code_video;
import org.example.output.dorm.orders.raw_sp_video.columns.COL_email;
import org.example.output.dorm.orders.raw_sp_video.columns.COL_employee_id;
import org.example.output.dorm.orders.raw_sp_video.columns.COL_order_date;
import org.example.output.dorm.orders.raw_sp_video.columns.COL_order_id;
import org.example.output.dorm.orders.raw_sp_video.columns.COL_order_time;
import org.example.output.dorm.orders.raw_sp_video.columns.COL_order_type;
import org.example.output.dorm.orders.raw_sp_video.columns.COL_phone;
import org.example.output.dorm.orders.raw_sp_video.columns.COL_sales_person;
import org.example.output.dorm.orders.raw_sp_video.columns.COL_state;
import org.example.output.dorm.orders.raw_sp_video.columns.COL_street_address;
import org.example.output.dorm.orders.raw_sp_video.columns.COL_video_account_number;
import org.example.output.dorm.orders.raw_sp_video.columns.COL_video_cancel_date;
import org.example.output.dorm.orders.raw_sp_video.columns.COL_video_cancel_reason;
import org.example.output.dorm.orders.raw_sp_video.columns.COL_video_install_date;
import org.example.output.dorm.orders.raw_sp_video.columns.COL_video_order_number;
import org.example.output.dorm.orders.raw_sp_video.columns.COL_video_package;
import org.example.output.dorm.orders.raw_sp_video.columns.COL_video_provider;
import org.example.output.dorm.orders.raw_sp_video.columns.COL_video_status;
import org.example.output.dorm.orders.raw_sp_video.columns.COL_zip;
import org.example.output.dorm.orders.raw_sp_video.columns.COL_latest_insert_date;
import org.example.output.dorm.orders.raw_sp_video.columns.COL_manual_employee_id;

import java.util.List;

import org.example.ContextInstance.KDBContext;

public class TAB_raw_sp_video extends TableTemplate {

    public TAB_raw_sp_video() {
        super(
            "raw_sp_video"
,
            "Table storing raw SP Video order records"
,
            new String[]{"orders", "sp_video", "raw"},
            "tabc9a7339d-f97f-41bb-8fb6-36a62a1db14f"
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





    private final ColumnTemplate COL_abp = new COL_abp();

    public ColumnTemplate getCOL_abp() {
        return COL_abp;
    }





    private final ColumnTemplate COL_apt_number = new COL_apt_number();

    public ColumnTemplate getCOL_apt_number() {
        return COL_apt_number;
    }





    private final ColumnTemplate COL_atv_device_count = new COL_atv_device_count();

    public ColumnTemplate getCOL_atv_device_count() {
        return COL_atv_device_count;
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





    private final ColumnTemplate COL_dealer_code_video = new COL_dealer_code_video();

    public ColumnTemplate getCOL_dealer_code_video() {
        return COL_dealer_code_video;
    }





    private final ColumnTemplate COL_email = new COL_email();

    public ColumnTemplate getCOL_email() {
        return COL_email;
    }





    private final ColumnTemplate COL_employee_id = new COL_employee_id();

    public ColumnTemplate getCOL_employee_id() {
        return COL_employee_id;
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





    private final ColumnTemplate COL_order_type = new COL_order_type();

    public ColumnTemplate getCOL_order_type() {
        return COL_order_type;
    }





    private final ColumnTemplate COL_phone = new COL_phone();

    public ColumnTemplate getCOL_phone() {
        return COL_phone;
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





    private final ColumnTemplate COL_video_account_number = new COL_video_account_number();

    public ColumnTemplate getCOL_video_account_number() {
        return COL_video_account_number;
    }





    private final ColumnTemplate COL_video_cancel_date = new COL_video_cancel_date();

    public ColumnTemplate getCOL_video_cancel_date() {
        return COL_video_cancel_date;
    }





    private final ColumnTemplate COL_video_cancel_reason = new COL_video_cancel_reason();

    public ColumnTemplate getCOL_video_cancel_reason() {
        return COL_video_cancel_reason;
    }





    private final ColumnTemplate COL_video_install_date = new COL_video_install_date();

    public ColumnTemplate getCOL_video_install_date() {
        return COL_video_install_date;
    }





    private final ColumnTemplate COL_video_order_number = new COL_video_order_number();

    public ColumnTemplate getCOL_video_order_number() {
        return COL_video_order_number;
    }





    private final ColumnTemplate COL_video_package = new COL_video_package();

    public ColumnTemplate getCOL_video_package() {
        return COL_video_package;
    }





    private final ColumnTemplate COL_video_provider = new COL_video_provider();

    public ColumnTemplate getCOL_video_provider() {
        return COL_video_provider;
    }





    private final ColumnTemplate COL_video_status = new COL_video_status();

    public ColumnTemplate getCOL_video_status() {
        return COL_video_status;
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
    return String.format("('%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s')\n",
                      getCOL_db_id().getEntityValue().getValue().toString(),
          getCOL_abp().getEntityValue().getValue().toString(),
          getCOL_apt_number().getEntityValue().getValue().toString(),
          getCOL_atv_device_count().getEntityValue().getValue().toString(),
          getCOL_city().getEntityValue().getValue().toString(),
          getCOL_columns().getEntityValue().getValue().toString(),
          getCOL_customer_name().getEntityValue().getValue().toString(),
          getCOL_dealer_code_video().getEntityValue().getValue().toString(),
          getCOL_email().getEntityValue().getValue().toString(),
          getCOL_employee_id().getEntityValue().getValue().toString(),
          getCOL_order_date().getEntityValue().getValue().toString(),
          getCOL_order_id().getEntityValue().getValue().toString(),
          getCOL_order_time().getEntityValue().getValue().toString(),
          getCOL_order_type().getEntityValue().getValue().toString(),
          getCOL_phone().getEntityValue().getValue().toString(),
          getCOL_sales_person().getEntityValue().getValue().toString(),
          getCOL_state().getEntityValue().getValue().toString(),
          getCOL_street_address().getEntityValue().getValue().toString(),
          getCOL_video_account_number().getEntityValue().getValue().toString(),
          getCOL_video_cancel_date().getEntityValue().getValue().toString(),
          getCOL_video_cancel_reason().getEntityValue().getValue().toString(),
          getCOL_video_install_date().getEntityValue().getValue().toString(),
          getCOL_video_order_number().getEntityValue().getValue().toString(),
          getCOL_video_package().getEntityValue().getValue().toString(),
          getCOL_video_provider().getEntityValue().getValue().toString(),
          getCOL_video_status().getEntityValue().getValue().toString(),
          getCOL_zip().getEntityValue().getValue().toString(),
          getCOL_latest_insert_date().getEntityValue().getValue().toString(),
          getCOL_manual_employee_id().getEntityValue().getValue().toString()
    );
}
            @Override
            public String getValues(String arg) {
                return String.format("('%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s')\n",
          getCOL_db_id().getEntityValue().getValue().toString(),
          getCOL_abp().getEntityValue().getValue().toString(),
          getCOL_apt_number().getEntityValue().getValue().toString(),
          getCOL_atv_device_count().getEntityValue().getValue().toString(),
          getCOL_city().getEntityValue().getValue().toString(),
          getCOL_columns().getEntityValue().getValue().toString(),
          getCOL_customer_name().getEntityValue().getValue().toString(),
          getCOL_dealer_code_video().getEntityValue().getValue().toString(),
          getCOL_email().getEntityValue().getValue().toString(),
          getCOL_employee_id().getEntityValue().getValue().toString(),
          getCOL_order_date().getEntityValue().getValue().toString(),
          getCOL_order_id().getEntityValue().getValue().toString(),
          getCOL_order_time().getEntityValue().getValue().toString(),
          getCOL_order_type().getEntityValue().getValue().toString(),
          getCOL_phone().getEntityValue().getValue().toString(),
          getCOL_sales_person().getEntityValue().getValue().toString(),
          getCOL_state().getEntityValue().getValue().toString(),
          getCOL_street_address().getEntityValue().getValue().toString(),
          getCOL_video_account_number().getEntityValue().getValue().toString(),
          getCOL_video_cancel_date().getEntityValue().getValue().toString(),
          getCOL_video_cancel_reason().getEntityValue().getValue().toString(),
          getCOL_video_install_date().getEntityValue().getValue().toString(),
          getCOL_video_order_number().getEntityValue().getValue().toString(),
          getCOL_video_package().getEntityValue().getValue().toString(),
          getCOL_video_provider().getEntityValue().getValue().toString(),
          getCOL_video_status().getEntityValue().getValue().toString(),
          getCOL_zip().getEntityValue().getValue().toString(),
          getCOL_latest_insert_date().getEntityValue().getValue().toString(),
          getCOL_manual_employee_id().getEntityValue().getValue().toString()
                );
            }

}