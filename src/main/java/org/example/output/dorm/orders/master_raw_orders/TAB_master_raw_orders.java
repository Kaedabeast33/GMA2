package org.example.output.dorm.orders.master_raw_orders;


import org.example.gma.templates.TableTemplate;
import org.example.gma.templates.QueryTemplate;
import org.example.gma.templates.ColumnTemplate;


import org.example.output.dorm.orders.master_raw_orders.columns.COL_db_id;
import org.example.output.dorm.orders.master_raw_orders.columns.COL_employee_id;
import org.example.output.dorm.orders.master_raw_orders.columns.COL_ref_login_username_id;
import org.example.output.dorm.orders.master_raw_orders.columns.COL_order_date;
import org.example.output.dorm.orders.master_raw_orders.columns.COL_order_id;
import org.example.output.dorm.orders.master_raw_orders.columns.COL_order_number;
import org.example.output.dorm.orders.master_raw_orders.columns.COL_account_number;
import org.example.output.dorm.orders.master_raw_orders.columns.COL_mobile_number;
import org.example.output.dorm.orders.master_raw_orders.columns.COL_customer_name;
import org.example.output.dorm.orders.master_raw_orders.columns.COL_customer_contact_number;
import org.example.output.dorm.orders.master_raw_orders.columns.COL_customer_address_1;
import org.example.output.dorm.orders.master_raw_orders.columns.COL_customer_address_2;
import org.example.output.dorm.orders.master_raw_orders.columns.COL_city;
import org.example.output.dorm.orders.master_raw_orders.columns.COL_state;
import org.example.output.dorm.orders.master_raw_orders.columns.COL_zip_5;
import org.example.output.dorm.orders.master_raw_orders.columns.COL_status_change_date;
import org.example.output.dorm.orders.master_raw_orders.columns.COL_due_date;
import org.example.output.dorm.orders.master_raw_orders.columns.COL_purchase_type;
import org.example.output.dorm.orders.master_raw_orders.columns.COL_package_name;
import org.example.output.dorm.orders.master_raw_orders.columns.COL_order_method;
import org.example.output.dorm.orders.master_raw_orders.columns.COL_empalign_ref_id;
import org.example.output.dorm.orders.master_raw_orders.columns.COL_markalign_ref_id;
import org.example.output.dorm.orders.master_raw_orders.columns.COL_regalign_ref_id;
import org.example.output.dorm.orders.master_raw_orders.columns.COL_divalign_ref_id;
import org.example.output.dorm.orders.master_raw_orders.columns.COL_mo_reconciliation_ref_id;
import org.example.output.dorm.orders.master_raw_orders.columns.COL_mo_reconciliation_ref_id_2;
import org.example.output.dorm.orders.master_raw_orders.columns.COL_mo_reconciliation_ref_id_3;
import org.example.output.dorm.orders.master_raw_orders.columns.COL_mo_reconciliation_ref_id_4;
import org.example.output.dorm.orders.master_raw_orders.columns.COL_mo_general_ref_id;
import org.example.output.dorm.orders.master_raw_orders.columns.COL_carrier_system;
import org.example.output.dorm.orders.master_raw_orders.columns.COL_status;
import org.example.output.dorm.orders.master_raw_orders.columns.COL_date_db_insert;
import org.example.output.dorm.orders.master_raw_orders.columns.COL_hub_fun_id;
import org.example.output.dorm.orders.master_raw_orders.columns.COL_seller_ref_id;
import org.example.output.dorm.orders.master_raw_orders.columns.COL_upline_ref_id;
import org.example.output.dorm.orders.master_raw_orders.columns.COL_seller_ref_id_level_1;
import org.example.output.dorm.orders.master_raw_orders.columns.COL_upline_ref_id_level_1;
import org.example.output.dorm.orders.master_raw_orders.columns.COL_seller_ref_id_level_2;
import org.example.output.dorm.orders.master_raw_orders.columns.COL_upline_ref_id_level_2;
import org.example.output.dorm.orders.master_raw_orders.columns.COL_seller_ref_id_level_3;
import org.example.output.dorm.orders.master_raw_orders.columns.COL_upline_ref_id_level_3;

import java.util.List;

import org.example.ContextInstance.KDBContext;

public class TAB_master_raw_orders extends TableTemplate {

    public TAB_master_raw_orders() {
        super(
            "master_raw_orders"
,
            "Table storing master raw orders"
,
            new String[]{"orders", "master", "tracking"},
            "tab2a7e7326-b3c6-4866-b3ad-846b7eafd862"
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





    private final ColumnTemplate COL_employee_id = new COL_employee_id();

    public ColumnTemplate getCOL_employee_id() {
        return COL_employee_id;
    }





    private final ColumnTemplate COL_ref_login_username_id = new COL_ref_login_username_id();

    public ColumnTemplate getCOL_ref_login_username_id() {
        return COL_ref_login_username_id;
    }





    private final ColumnTemplate COL_order_date = new COL_order_date();

    public ColumnTemplate getCOL_order_date() {
        return COL_order_date;
    }





    private final ColumnTemplate COL_order_id = new COL_order_id();

    public ColumnTemplate getCOL_order_id() {
        return COL_order_id;
    }





    private final ColumnTemplate COL_order_number = new COL_order_number();

    public ColumnTemplate getCOL_order_number() {
        return COL_order_number;
    }





    private final ColumnTemplate COL_account_number = new COL_account_number();

    public ColumnTemplate getCOL_account_number() {
        return COL_account_number;
    }





    private final ColumnTemplate COL_mobile_number = new COL_mobile_number();

    public ColumnTemplate getCOL_mobile_number() {
        return COL_mobile_number;
    }





    private final ColumnTemplate COL_customer_name = new COL_customer_name();

    public ColumnTemplate getCOL_customer_name() {
        return COL_customer_name;
    }





    private final ColumnTemplate COL_customer_contact_number = new COL_customer_contact_number();

    public ColumnTemplate getCOL_customer_contact_number() {
        return COL_customer_contact_number;
    }





    private final ColumnTemplate COL_customer_address_1 = new COL_customer_address_1();

    public ColumnTemplate getCOL_customer_address_1() {
        return COL_customer_address_1;
    }





    private final ColumnTemplate COL_customer_address_2 = new COL_customer_address_2();

    public ColumnTemplate getCOL_customer_address_2() {
        return COL_customer_address_2;
    }





    private final ColumnTemplate COL_city = new COL_city();

    public ColumnTemplate getCOL_city() {
        return COL_city;
    }





    private final ColumnTemplate COL_state = new COL_state();

    public ColumnTemplate getCOL_state() {
        return COL_state;
    }





    private final ColumnTemplate COL_zip_5 = new COL_zip_5();

    public ColumnTemplate getCOL_zip_5() {
        return COL_zip_5;
    }





    private final ColumnTemplate COL_status_change_date = new COL_status_change_date();

    public ColumnTemplate getCOL_status_change_date() {
        return COL_status_change_date;
    }





    private final ColumnTemplate COL_due_date = new COL_due_date();

    public ColumnTemplate getCOL_due_date() {
        return COL_due_date;
    }





    private final ColumnTemplate COL_purchase_type = new COL_purchase_type();

    public ColumnTemplate getCOL_purchase_type() {
        return COL_purchase_type;
    }





    private final ColumnTemplate COL_package_name = new COL_package_name();

    public ColumnTemplate getCOL_package_name() {
        return COL_package_name;
    }





    private final ColumnTemplate COL_order_method = new COL_order_method();

    public ColumnTemplate getCOL_order_method() {
        return COL_order_method;
    }





    private final ColumnTemplate COL_empalign_ref_id = new COL_empalign_ref_id();

    public ColumnTemplate getCOL_empalign_ref_id() {
        return COL_empalign_ref_id;
    }





    private final ColumnTemplate COL_markalign_ref_id = new COL_markalign_ref_id();

    public ColumnTemplate getCOL_markalign_ref_id() {
        return COL_markalign_ref_id;
    }





    private final ColumnTemplate COL_regalign_ref_id = new COL_regalign_ref_id();

    public ColumnTemplate getCOL_regalign_ref_id() {
        return COL_regalign_ref_id;
    }





    private final ColumnTemplate COL_divalign_ref_id = new COL_divalign_ref_id();

    public ColumnTemplate getCOL_divalign_ref_id() {
        return COL_divalign_ref_id;
    }





    private final ColumnTemplate COL_mo_reconciliation_ref_id = new COL_mo_reconciliation_ref_id();

    public ColumnTemplate getCOL_mo_reconciliation_ref_id() {
        return COL_mo_reconciliation_ref_id;
    }





    private final ColumnTemplate COL_mo_reconciliation_ref_id_2 = new COL_mo_reconciliation_ref_id_2();

    public ColumnTemplate getCOL_mo_reconciliation_ref_id_2() {
        return COL_mo_reconciliation_ref_id_2;
    }





    private final ColumnTemplate COL_mo_reconciliation_ref_id_3 = new COL_mo_reconciliation_ref_id_3();

    public ColumnTemplate getCOL_mo_reconciliation_ref_id_3() {
        return COL_mo_reconciliation_ref_id_3;
    }





    private final ColumnTemplate COL_mo_reconciliation_ref_id_4 = new COL_mo_reconciliation_ref_id_4();

    public ColumnTemplate getCOL_mo_reconciliation_ref_id_4() {
        return COL_mo_reconciliation_ref_id_4;
    }





    private final ColumnTemplate COL_mo_general_ref_id = new COL_mo_general_ref_id();

    public ColumnTemplate getCOL_mo_general_ref_id() {
        return COL_mo_general_ref_id;
    }





    private final ColumnTemplate COL_carrier_system = new COL_carrier_system();

    public ColumnTemplate getCOL_carrier_system() {
        return COL_carrier_system;
    }





    private final ColumnTemplate COL_status = new COL_status();

    public ColumnTemplate getCOL_status() {
        return COL_status;
    }





    private final ColumnTemplate COL_date_db_insert = new COL_date_db_insert();

    public ColumnTemplate getCOL_date_db_insert() {
        return COL_date_db_insert;
    }





    private final ColumnTemplate COL_hub_fun_id = new COL_hub_fun_id();

    public ColumnTemplate getCOL_hub_fun_id() {
        return COL_hub_fun_id;
    }





    private final ColumnTemplate COL_seller_ref_id = new COL_seller_ref_id();

    public ColumnTemplate getCOL_seller_ref_id() {
        return COL_seller_ref_id;
    }





    private final ColumnTemplate COL_upline_ref_id = new COL_upline_ref_id();

    public ColumnTemplate getCOL_upline_ref_id() {
        return COL_upline_ref_id;
    }





    private final ColumnTemplate COL_seller_ref_id_level_1 = new COL_seller_ref_id_level_1();

    public ColumnTemplate getCOL_seller_ref_id_level_1() {
        return COL_seller_ref_id_level_1;
    }





    private final ColumnTemplate COL_upline_ref_id_level_1 = new COL_upline_ref_id_level_1();

    public ColumnTemplate getCOL_upline_ref_id_level_1() {
        return COL_upline_ref_id_level_1;
    }





    private final ColumnTemplate COL_seller_ref_id_level_2 = new COL_seller_ref_id_level_2();

    public ColumnTemplate getCOL_seller_ref_id_level_2() {
        return COL_seller_ref_id_level_2;
    }





    private final ColumnTemplate COL_upline_ref_id_level_2 = new COL_upline_ref_id_level_2();

    public ColumnTemplate getCOL_upline_ref_id_level_2() {
        return COL_upline_ref_id_level_2;
    }





    private final ColumnTemplate COL_seller_ref_id_level_3 = new COL_seller_ref_id_level_3();

    public ColumnTemplate getCOL_seller_ref_id_level_3() {
        return COL_seller_ref_id_level_3;
    }





    private final ColumnTemplate COL_upline_ref_id_level_3 = new COL_upline_ref_id_level_3();

    public ColumnTemplate getCOL_upline_ref_id_level_3() {
        return COL_upline_ref_id_level_3;
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
    return String.format("('%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s')\n",
                      getCOL_db_id().getEntityValue().getValue().toString(),
          getCOL_employee_id().getEntityValue().getValue().toString(),
          getCOL_ref_login_username_id().getEntityValue().getValue().toString(),
          getCOL_order_date().getEntityValue().getValue().toString(),
          getCOL_order_id().getEntityValue().getValue().toString(),
          getCOL_order_number().getEntityValue().getValue().toString(),
          getCOL_account_number().getEntityValue().getValue().toString(),
          getCOL_mobile_number().getEntityValue().getValue().toString(),
          getCOL_customer_name().getEntityValue().getValue().toString(),
          getCOL_customer_contact_number().getEntityValue().getValue().toString(),
          getCOL_customer_address_1().getEntityValue().getValue().toString(),
          getCOL_customer_address_2().getEntityValue().getValue().toString(),
          getCOL_city().getEntityValue().getValue().toString(),
          getCOL_state().getEntityValue().getValue().toString(),
          getCOL_zip_5().getEntityValue().getValue().toString(),
          getCOL_status_change_date().getEntityValue().getValue().toString(),
          getCOL_due_date().getEntityValue().getValue().toString(),
          getCOL_purchase_type().getEntityValue().getValue().toString(),
          getCOL_package_name().getEntityValue().getValue().toString(),
          getCOL_order_method().getEntityValue().getValue().toString(),
          getCOL_empalign_ref_id().getEntityValue().getValue().toString(),
          getCOL_markalign_ref_id().getEntityValue().getValue().toString(),
          getCOL_regalign_ref_id().getEntityValue().getValue().toString(),
          getCOL_divalign_ref_id().getEntityValue().getValue().toString(),
          getCOL_mo_reconciliation_ref_id().getEntityValue().getValue().toString(),
          getCOL_mo_reconciliation_ref_id_2().getEntityValue().getValue().toString(),
          getCOL_mo_reconciliation_ref_id_3().getEntityValue().getValue().toString(),
          getCOL_mo_reconciliation_ref_id_4().getEntityValue().getValue().toString(),
          getCOL_mo_general_ref_id().getEntityValue().getValue().toString(),
          getCOL_carrier_system().getEntityValue().getValue().toString(),
          getCOL_status().getEntityValue().getValue().toString(),
          getCOL_date_db_insert().getEntityValue().getValue().toString(),
          getCOL_hub_fun_id().getEntityValue().getValue().toString(),
          getCOL_seller_ref_id().getEntityValue().getValue().toString(),
          getCOL_upline_ref_id().getEntityValue().getValue().toString(),
          getCOL_seller_ref_id_level_1().getEntityValue().getValue().toString(),
          getCOL_upline_ref_id_level_1().getEntityValue().getValue().toString(),
          getCOL_seller_ref_id_level_2().getEntityValue().getValue().toString(),
          getCOL_upline_ref_id_level_2().getEntityValue().getValue().toString(),
          getCOL_seller_ref_id_level_3().getEntityValue().getValue().toString(),
          getCOL_upline_ref_id_level_3().getEntityValue().getValue().toString()
    );
}
            @Override
            public String getValues(String arg) {
                return String.format("('%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s')\n",
          getCOL_db_id().getEntityValue().getValue().toString(),
          getCOL_employee_id().getEntityValue().getValue().toString(),
          getCOL_ref_login_username_id().getEntityValue().getValue().toString(),
          getCOL_order_date().getEntityValue().getValue().toString(),
          getCOL_order_id().getEntityValue().getValue().toString(),
          getCOL_order_number().getEntityValue().getValue().toString(),
          getCOL_account_number().getEntityValue().getValue().toString(),
          getCOL_mobile_number().getEntityValue().getValue().toString(),
          getCOL_customer_name().getEntityValue().getValue().toString(),
          getCOL_customer_contact_number().getEntityValue().getValue().toString(),
          getCOL_customer_address_1().getEntityValue().getValue().toString(),
          getCOL_customer_address_2().getEntityValue().getValue().toString(),
          getCOL_city().getEntityValue().getValue().toString(),
          getCOL_state().getEntityValue().getValue().toString(),
          getCOL_zip_5().getEntityValue().getValue().toString(),
          getCOL_status_change_date().getEntityValue().getValue().toString(),
          getCOL_due_date().getEntityValue().getValue().toString(),
          getCOL_purchase_type().getEntityValue().getValue().toString(),
          getCOL_package_name().getEntityValue().getValue().toString(),
          getCOL_order_method().getEntityValue().getValue().toString(),
          getCOL_empalign_ref_id().getEntityValue().getValue().toString(),
          getCOL_markalign_ref_id().getEntityValue().getValue().toString(),
          getCOL_regalign_ref_id().getEntityValue().getValue().toString(),
          getCOL_divalign_ref_id().getEntityValue().getValue().toString(),
          getCOL_mo_reconciliation_ref_id().getEntityValue().getValue().toString(),
          getCOL_mo_reconciliation_ref_id_2().getEntityValue().getValue().toString(),
          getCOL_mo_reconciliation_ref_id_3().getEntityValue().getValue().toString(),
          getCOL_mo_reconciliation_ref_id_4().getEntityValue().getValue().toString(),
          getCOL_mo_general_ref_id().getEntityValue().getValue().toString(),
          getCOL_carrier_system().getEntityValue().getValue().toString(),
          getCOL_status().getEntityValue().getValue().toString(),
          getCOL_date_db_insert().getEntityValue().getValue().toString(),
          getCOL_hub_fun_id().getEntityValue().getValue().toString(),
          getCOL_seller_ref_id().getEntityValue().getValue().toString(),
          getCOL_upline_ref_id().getEntityValue().getValue().toString(),
          getCOL_seller_ref_id_level_1().getEntityValue().getValue().toString(),
          getCOL_upline_ref_id_level_1().getEntityValue().getValue().toString(),
          getCOL_seller_ref_id_level_2().getEntityValue().getValue().toString(),
          getCOL_upline_ref_id_level_2().getEntityValue().getValue().toString(),
          getCOL_seller_ref_id_level_3().getEntityValue().getValue().toString(),
          getCOL_upline_ref_id_level_3().getEntityValue().getValue().toString()
                );
            }

}