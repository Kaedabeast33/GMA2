package org.example.output.dorm.orders.master_orders;


import org.example.gma.templates.TableTemplate;
import org.example.gma.templates.QueryTemplate;
import org.example.gma.templates.ColumnTemplate;


import org.example.output.dorm.orders.master_orders.columns.COL_my_row_id;
import org.example.output.dorm.orders.master_orders.columns.COL_db_id;
import org.example.output.dorm.orders.master_orders.columns.COL_order_date;
import org.example.output.dorm.orders.master_orders.columns.COL_employee_id;
import org.example.output.dorm.orders.master_orders.columns.COL_start_date;
import org.example.output.dorm.orders.master_orders.columns.COL_seller_name;
import org.example.output.dorm.orders.master_orders.columns.COL_business_unit;
import org.example.output.dorm.orders.master_orders.columns.COL_referrer_eid;
import org.example.output.dorm.orders.master_orders.columns.COL_referrer_name;
import org.example.output.dorm.orders.master_orders.columns.COL_comp_id;
import org.example.output.dorm.orders.master_orders.columns.COL_comp_name;
import org.example.output.dorm.orders.master_orders.columns.COL_role_id;
import org.example.output.dorm.orders.master_orders.columns.COL_universal_role_name;
import org.example.output.dorm.orders.master_orders.columns.COL_ref_login_username_id;
import org.example.output.dorm.orders.master_orders.columns.COL_order_id;
import org.example.output.dorm.orders.master_orders.columns.COL_order_number;
import org.example.output.dorm.orders.master_orders.columns.COL_account_number;
import org.example.output.dorm.orders.master_orders.columns.COL_mobile_number;
import org.example.output.dorm.orders.master_orders.columns.COL_customer_name;
import org.example.output.dorm.orders.master_orders.columns.COL_customer_contact_number;
import org.example.output.dorm.orders.master_orders.columns.COL_customer_address_1;
import org.example.output.dorm.orders.master_orders.columns.COL_customer_address_2;
import org.example.output.dorm.orders.master_orders.columns.COL_city;
import org.example.output.dorm.orders.master_orders.columns.COL_state;
import org.example.output.dorm.orders.master_orders.columns.COL_zip_5;
import org.example.output.dorm.orders.master_orders.columns.COL_status;
import org.example.output.dorm.orders.master_orders.columns.COL_install_date;
import org.example.output.dorm.orders.master_orders.columns.COL_carrier;
import org.example.output.dorm.orders.master_orders.columns.COL_purchase_type;
import org.example.output.dorm.orders.master_orders.columns.COL_package_name;
import org.example.output.dorm.orders.master_orders.columns.COL_product;
import org.example.output.dorm.orders.master_orders.columns.COL_product_type;
import org.example.output.dorm.orders.master_orders.columns.COL_carrier_speed;
import org.example.output.dorm.orders.master_orders.columns.COL_speed_bucket;
import org.example.output.dorm.orders.master_orders.columns.COL_commission;
import org.example.output.dorm.orders.master_orders.columns.COL_unit_gross;
import org.example.output.dorm.orders.master_orders.columns.COL_unit_net;
import org.example.output.dorm.orders.master_orders.columns.COL_unit_weighted_gross;
import org.example.output.dorm.orders.master_orders.columns.COL_unit_weighted_net;
import org.example.output.dorm.orders.master_orders.columns.COL_addon_count;
import org.example.output.dorm.orders.master_orders.columns.COL_carrier_system;
import org.example.output.dorm.orders.master_orders.columns.COL_date_db_changed;
import org.example.output.dorm.orders.master_orders.columns.COL_order_method;
import org.example.output.dorm.orders.master_orders.columns.COL_mo_reconciliation_ref_id;
import org.example.output.dorm.orders.master_orders.columns.COL_mo_reconciliation_ref_id_2;
import org.example.output.dorm.orders.master_orders.columns.COL_mo_reconciliation_ref_id_3;
import org.example.output.dorm.orders.master_orders.columns.COL_mo_reconciliation_ref_id_4;
import org.example.output.dorm.orders.master_orders.columns.COL_mo_general_ref_id;
import org.example.output.dorm.orders.master_orders.columns.COL_hub_fun_id;
import org.example.output.dorm.orders.master_orders.columns.COL_original_team_id;
import org.example.output.dorm.orders.master_orders.columns.COL_original_team_name;
import org.example.output.dorm.orders.master_orders.columns.COL_original_team_id_owner;
import org.example.output.dorm.orders.master_orders.columns.COL_original_team_owner_name;
import org.example.output.dorm.orders.master_orders.columns.COL_team_id_level_1;
import org.example.output.dorm.orders.master_orders.columns.COL_team_name_level_1;
import org.example.output.dorm.orders.master_orders.columns.COL_team_id_owner_level_1;
import org.example.output.dorm.orders.master_orders.columns.COL_owner_name_level_1;
import org.example.output.dorm.orders.master_orders.columns.COL_role_name_level_1;
import org.example.output.dorm.orders.master_orders.columns.COL_comp_name_level_1;
import org.example.output.dorm.orders.master_orders.columns.COL_team_id_level_2;
import org.example.output.dorm.orders.master_orders.columns.COL_team_name_level_2;
import org.example.output.dorm.orders.master_orders.columns.COL_team_id_owner_level_2;
import org.example.output.dorm.orders.master_orders.columns.COL_owner_name_level_2;
import org.example.output.dorm.orders.master_orders.columns.COL_role_name_level_2;
import org.example.output.dorm.orders.master_orders.columns.COL_comp_name_level_2;
import org.example.output.dorm.orders.master_orders.columns.COL_team_id_level_3;
import org.example.output.dorm.orders.master_orders.columns.COL_team_name_level_3;
import org.example.output.dorm.orders.master_orders.columns.COL_team_id_owner_level_3;
import org.example.output.dorm.orders.master_orders.columns.COL_owner_name_level_3;
import org.example.output.dorm.orders.master_orders.columns.COL_role_name_level_3;
import org.example.output.dorm.orders.master_orders.columns.COL_comp_name_level_3;

import java.util.List;

import org.example.ContextInstance.KDBContext;

public class TAB_master_orders extends TableTemplate {

    public TAB_master_orders() {
        super(
            "master_orders"
,
            "Table storing master orders"
,
            new String[]{"orders", "master", "tracking"},
            "tab4ab990dc-65aa-472d-95d1-2936077a4a14"
,
            "dorm"
,
            "orders"

        );
    }




 KDBContext context = KDBContext.KDB_CONTEXT;
    private final ColumnTemplate COL_my_row_id = new COL_my_row_id();

    public ColumnTemplate getCOL_my_row_id() {
        return COL_my_row_id;
    }





    private final ColumnTemplate COL_db_id = new COL_db_id();

    public ColumnTemplate getCOL_db_id() {
        return COL_db_id;
    }





    private final ColumnTemplate COL_order_date = new COL_order_date();

    public ColumnTemplate getCOL_order_date() {
        return COL_order_date;
    }





    private final ColumnTemplate COL_employee_id = new COL_employee_id();

    public ColumnTemplate getCOL_employee_id() {
        return COL_employee_id;
    }





    private final ColumnTemplate COL_start_date = new COL_start_date();

    public ColumnTemplate getCOL_start_date() {
        return COL_start_date;
    }





    private final ColumnTemplate COL_seller_name = new COL_seller_name();

    public ColumnTemplate getCOL_seller_name() {
        return COL_seller_name;
    }





    private final ColumnTemplate COL_business_unit = new COL_business_unit();

    public ColumnTemplate getCOL_business_unit() {
        return COL_business_unit;
    }





    private final ColumnTemplate COL_referrer_eid = new COL_referrer_eid();

    public ColumnTemplate getCOL_referrer_eid() {
        return COL_referrer_eid;
    }





    private final ColumnTemplate COL_referrer_name = new COL_referrer_name();

    public ColumnTemplate getCOL_referrer_name() {
        return COL_referrer_name;
    }





    private final ColumnTemplate COL_comp_id = new COL_comp_id();

    public ColumnTemplate getCOL_comp_id() {
        return COL_comp_id;
    }





    private final ColumnTemplate COL_comp_name = new COL_comp_name();

    public ColumnTemplate getCOL_comp_name() {
        return COL_comp_name;
    }





    private final ColumnTemplate COL_role_id = new COL_role_id();

    public ColumnTemplate getCOL_role_id() {
        return COL_role_id;
    }





    private final ColumnTemplate COL_universal_role_name = new COL_universal_role_name();

    public ColumnTemplate getCOL_universal_role_name() {
        return COL_universal_role_name;
    }





    private final ColumnTemplate COL_ref_login_username_id = new COL_ref_login_username_id();

    public ColumnTemplate getCOL_ref_login_username_id() {
        return COL_ref_login_username_id;
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





    private final ColumnTemplate COL_status = new COL_status();

    public ColumnTemplate getCOL_status() {
        return COL_status;
    }





    private final ColumnTemplate COL_install_date = new COL_install_date();

    public ColumnTemplate getCOL_install_date() {
        return COL_install_date;
    }





    private final ColumnTemplate COL_carrier = new COL_carrier();

    public ColumnTemplate getCOL_carrier() {
        return COL_carrier;
    }





    private final ColumnTemplate COL_purchase_type = new COL_purchase_type();

    public ColumnTemplate getCOL_purchase_type() {
        return COL_purchase_type;
    }





    private final ColumnTemplate COL_package_name = new COL_package_name();

    public ColumnTemplate getCOL_package_name() {
        return COL_package_name;
    }





    private final ColumnTemplate COL_product = new COL_product();

    public ColumnTemplate getCOL_product() {
        return COL_product;
    }





    private final ColumnTemplate COL_product_type = new COL_product_type();

    public ColumnTemplate getCOL_product_type() {
        return COL_product_type;
    }





    private final ColumnTemplate COL_carrier_speed = new COL_carrier_speed();

    public ColumnTemplate getCOL_carrier_speed() {
        return COL_carrier_speed;
    }





    private final ColumnTemplate COL_speed_bucket = new COL_speed_bucket();

    public ColumnTemplate getCOL_speed_bucket() {
        return COL_speed_bucket;
    }





    private final ColumnTemplate COL_commission = new COL_commission();

    public ColumnTemplate getCOL_commission() {
        return COL_commission;
    }





    private final ColumnTemplate COL_unit_gross = new COL_unit_gross();

    public ColumnTemplate getCOL_unit_gross() {
        return COL_unit_gross;
    }





    private final ColumnTemplate COL_unit_net = new COL_unit_net();

    public ColumnTemplate getCOL_unit_net() {
        return COL_unit_net;
    }





    private final ColumnTemplate COL_unit_weighted_gross = new COL_unit_weighted_gross();

    public ColumnTemplate getCOL_unit_weighted_gross() {
        return COL_unit_weighted_gross;
    }





    private final ColumnTemplate COL_unit_weighted_net = new COL_unit_weighted_net();

    public ColumnTemplate getCOL_unit_weighted_net() {
        return COL_unit_weighted_net;
    }





    private final ColumnTemplate COL_addon_count = new COL_addon_count();

    public ColumnTemplate getCOL_addon_count() {
        return COL_addon_count;
    }





    private final ColumnTemplate COL_carrier_system = new COL_carrier_system();

    public ColumnTemplate getCOL_carrier_system() {
        return COL_carrier_system;
    }





    private final ColumnTemplate COL_date_db_changed = new COL_date_db_changed();

    public ColumnTemplate getCOL_date_db_changed() {
        return COL_date_db_changed;
    }





    private final ColumnTemplate COL_order_method = new COL_order_method();

    public ColumnTemplate getCOL_order_method() {
        return COL_order_method;
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





    private final ColumnTemplate COL_hub_fun_id = new COL_hub_fun_id();

    public ColumnTemplate getCOL_hub_fun_id() {
        return COL_hub_fun_id;
    }





    private final ColumnTemplate COL_original_team_id = new COL_original_team_id();

    public ColumnTemplate getCOL_original_team_id() {
        return COL_original_team_id;
    }





    private final ColumnTemplate COL_original_team_name = new COL_original_team_name();

    public ColumnTemplate getCOL_original_team_name() {
        return COL_original_team_name;
    }





    private final ColumnTemplate COL_original_team_id_owner = new COL_original_team_id_owner();

    public ColumnTemplate getCOL_original_team_id_owner() {
        return COL_original_team_id_owner;
    }





    private final ColumnTemplate COL_original_team_owner_name = new COL_original_team_owner_name();

    public ColumnTemplate getCOL_original_team_owner_name() {
        return COL_original_team_owner_name;
    }





    private final ColumnTemplate COL_team_id_level_1 = new COL_team_id_level_1();

    public ColumnTemplate getCOL_team_id_level_1() {
        return COL_team_id_level_1;
    }





    private final ColumnTemplate COL_team_name_level_1 = new COL_team_name_level_1();

    public ColumnTemplate getCOL_team_name_level_1() {
        return COL_team_name_level_1;
    }





    private final ColumnTemplate COL_team_id_owner_level_1 = new COL_team_id_owner_level_1();

    public ColumnTemplate getCOL_team_id_owner_level_1() {
        return COL_team_id_owner_level_1;
    }





    private final ColumnTemplate COL_owner_name_level_1 = new COL_owner_name_level_1();

    public ColumnTemplate getCOL_owner_name_level_1() {
        return COL_owner_name_level_1;
    }





    private final ColumnTemplate COL_role_name_level_1 = new COL_role_name_level_1();

    public ColumnTemplate getCOL_role_name_level_1() {
        return COL_role_name_level_1;
    }





    private final ColumnTemplate COL_comp_name_level_1 = new COL_comp_name_level_1();

    public ColumnTemplate getCOL_comp_name_level_1() {
        return COL_comp_name_level_1;
    }





    private final ColumnTemplate COL_team_id_level_2 = new COL_team_id_level_2();

    public ColumnTemplate getCOL_team_id_level_2() {
        return COL_team_id_level_2;
    }





    private final ColumnTemplate COL_team_name_level_2 = new COL_team_name_level_2();

    public ColumnTemplate getCOL_team_name_level_2() {
        return COL_team_name_level_2;
    }





    private final ColumnTemplate COL_team_id_owner_level_2 = new COL_team_id_owner_level_2();

    public ColumnTemplate getCOL_team_id_owner_level_2() {
        return COL_team_id_owner_level_2;
    }





    private final ColumnTemplate COL_owner_name_level_2 = new COL_owner_name_level_2();

    public ColumnTemplate getCOL_owner_name_level_2() {
        return COL_owner_name_level_2;
    }





    private final ColumnTemplate COL_role_name_level_2 = new COL_role_name_level_2();

    public ColumnTemplate getCOL_role_name_level_2() {
        return COL_role_name_level_2;
    }





    private final ColumnTemplate COL_comp_name_level_2 = new COL_comp_name_level_2();

    public ColumnTemplate getCOL_comp_name_level_2() {
        return COL_comp_name_level_2;
    }





    private final ColumnTemplate COL_team_id_level_3 = new COL_team_id_level_3();

    public ColumnTemplate getCOL_team_id_level_3() {
        return COL_team_id_level_3;
    }





    private final ColumnTemplate COL_team_name_level_3 = new COL_team_name_level_3();

    public ColumnTemplate getCOL_team_name_level_3() {
        return COL_team_name_level_3;
    }





    private final ColumnTemplate COL_team_id_owner_level_3 = new COL_team_id_owner_level_3();

    public ColumnTemplate getCOL_team_id_owner_level_3() {
        return COL_team_id_owner_level_3;
    }





    private final ColumnTemplate COL_owner_name_level_3 = new COL_owner_name_level_3();

    public ColumnTemplate getCOL_owner_name_level_3() {
        return COL_owner_name_level_3;
    }





    private final ColumnTemplate COL_role_name_level_3 = new COL_role_name_level_3();

    public ColumnTemplate getCOL_role_name_level_3() {
        return COL_role_name_level_3;
    }





    private final ColumnTemplate COL_comp_name_level_3 = new COL_comp_name_level_3();

    public ColumnTemplate getCOL_comp_name_level_3() {
        return COL_comp_name_level_3;
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
    return String.format("('%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s')\n",
                      getCOL_my_row_id().getEntityValue().getValue().toString(),
          getCOL_db_id().getEntityValue().getValue().toString(),
          getCOL_order_date().getEntityValue().getValue().toString(),
          getCOL_employee_id().getEntityValue().getValue().toString(),
          getCOL_start_date().getEntityValue().getValue().toString(),
          getCOL_seller_name().getEntityValue().getValue().toString(),
          getCOL_business_unit().getEntityValue().getValue().toString(),
          getCOL_referrer_eid().getEntityValue().getValue().toString(),
          getCOL_referrer_name().getEntityValue().getValue().toString(),
          getCOL_comp_id().getEntityValue().getValue().toString(),
          getCOL_comp_name().getEntityValue().getValue().toString(),
          getCOL_role_id().getEntityValue().getValue().toString(),
          getCOL_universal_role_name().getEntityValue().getValue().toString(),
          getCOL_ref_login_username_id().getEntityValue().getValue().toString(),
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
          getCOL_status().getEntityValue().getValue().toString(),
          getCOL_install_date().getEntityValue().getValue().toString(),
          getCOL_carrier().getEntityValue().getValue().toString(),
          getCOL_purchase_type().getEntityValue().getValue().toString(),
          getCOL_package_name().getEntityValue().getValue().toString(),
          getCOL_product().getEntityValue().getValue().toString(),
          getCOL_product_type().getEntityValue().getValue().toString(),
          getCOL_carrier_speed().getEntityValue().getValue().toString(),
          getCOL_speed_bucket().getEntityValue().getValue().toString(),
          getCOL_commission().getEntityValue().getValue().toString(),
          getCOL_unit_gross().getEntityValue().getValue().toString(),
          getCOL_unit_net().getEntityValue().getValue().toString(),
          getCOL_unit_weighted_gross().getEntityValue().getValue().toString(),
          getCOL_unit_weighted_net().getEntityValue().getValue().toString(),
          getCOL_addon_count().getEntityValue().getValue().toString(),
          getCOL_carrier_system().getEntityValue().getValue().toString(),
          getCOL_date_db_changed().getEntityValue().getValue().toString(),
          getCOL_order_method().getEntityValue().getValue().toString(),
          getCOL_mo_reconciliation_ref_id().getEntityValue().getValue().toString(),
          getCOL_mo_reconciliation_ref_id_2().getEntityValue().getValue().toString(),
          getCOL_mo_reconciliation_ref_id_3().getEntityValue().getValue().toString(),
          getCOL_mo_reconciliation_ref_id_4().getEntityValue().getValue().toString(),
          getCOL_mo_general_ref_id().getEntityValue().getValue().toString(),
          getCOL_hub_fun_id().getEntityValue().getValue().toString(),
          getCOL_original_team_id().getEntityValue().getValue().toString(),
          getCOL_original_team_name().getEntityValue().getValue().toString(),
          getCOL_original_team_id_owner().getEntityValue().getValue().toString(),
          getCOL_original_team_owner_name().getEntityValue().getValue().toString(),
          getCOL_team_id_level_1().getEntityValue().getValue().toString(),
          getCOL_team_name_level_1().getEntityValue().getValue().toString(),
          getCOL_team_id_owner_level_1().getEntityValue().getValue().toString(),
          getCOL_owner_name_level_1().getEntityValue().getValue().toString(),
          getCOL_role_name_level_1().getEntityValue().getValue().toString(),
          getCOL_comp_name_level_1().getEntityValue().getValue().toString(),
          getCOL_team_id_level_2().getEntityValue().getValue().toString(),
          getCOL_team_name_level_2().getEntityValue().getValue().toString(),
          getCOL_team_id_owner_level_2().getEntityValue().getValue().toString(),
          getCOL_owner_name_level_2().getEntityValue().getValue().toString(),
          getCOL_role_name_level_2().getEntityValue().getValue().toString(),
          getCOL_comp_name_level_2().getEntityValue().getValue().toString(),
          getCOL_team_id_level_3().getEntityValue().getValue().toString(),
          getCOL_team_name_level_3().getEntityValue().getValue().toString(),
          getCOL_team_id_owner_level_3().getEntityValue().getValue().toString(),
          getCOL_owner_name_level_3().getEntityValue().getValue().toString(),
          getCOL_role_name_level_3().getEntityValue().getValue().toString(),
          getCOL_comp_name_level_3().getEntityValue().getValue().toString()
    );
}
            @Override
            public String getValues(String arg) {
                return String.format("('%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s')\n",
          getCOL_my_row_id().getEntityValue().getValue().toString(),
          getCOL_db_id().getEntityValue().getValue().toString(),
          getCOL_order_date().getEntityValue().getValue().toString(),
          getCOL_employee_id().getEntityValue().getValue().toString(),
          getCOL_start_date().getEntityValue().getValue().toString(),
          getCOL_seller_name().getEntityValue().getValue().toString(),
          getCOL_business_unit().getEntityValue().getValue().toString(),
          getCOL_referrer_eid().getEntityValue().getValue().toString(),
          getCOL_referrer_name().getEntityValue().getValue().toString(),
          getCOL_comp_id().getEntityValue().getValue().toString(),
          getCOL_comp_name().getEntityValue().getValue().toString(),
          getCOL_role_id().getEntityValue().getValue().toString(),
          getCOL_universal_role_name().getEntityValue().getValue().toString(),
          getCOL_ref_login_username_id().getEntityValue().getValue().toString(),
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
          getCOL_status().getEntityValue().getValue().toString(),
          getCOL_install_date().getEntityValue().getValue().toString(),
          getCOL_carrier().getEntityValue().getValue().toString(),
          getCOL_purchase_type().getEntityValue().getValue().toString(),
          getCOL_package_name().getEntityValue().getValue().toString(),
          getCOL_product().getEntityValue().getValue().toString(),
          getCOL_product_type().getEntityValue().getValue().toString(),
          getCOL_carrier_speed().getEntityValue().getValue().toString(),
          getCOL_speed_bucket().getEntityValue().getValue().toString(),
          getCOL_commission().getEntityValue().getValue().toString(),
          getCOL_unit_gross().getEntityValue().getValue().toString(),
          getCOL_unit_net().getEntityValue().getValue().toString(),
          getCOL_unit_weighted_gross().getEntityValue().getValue().toString(),
          getCOL_unit_weighted_net().getEntityValue().getValue().toString(),
          getCOL_addon_count().getEntityValue().getValue().toString(),
          getCOL_carrier_system().getEntityValue().getValue().toString(),
          getCOL_date_db_changed().getEntityValue().getValue().toString(),
          getCOL_order_method().getEntityValue().getValue().toString(),
          getCOL_mo_reconciliation_ref_id().getEntityValue().getValue().toString(),
          getCOL_mo_reconciliation_ref_id_2().getEntityValue().getValue().toString(),
          getCOL_mo_reconciliation_ref_id_3().getEntityValue().getValue().toString(),
          getCOL_mo_reconciliation_ref_id_4().getEntityValue().getValue().toString(),
          getCOL_mo_general_ref_id().getEntityValue().getValue().toString(),
          getCOL_hub_fun_id().getEntityValue().getValue().toString(),
          getCOL_original_team_id().getEntityValue().getValue().toString(),
          getCOL_original_team_name().getEntityValue().getValue().toString(),
          getCOL_original_team_id_owner().getEntityValue().getValue().toString(),
          getCOL_original_team_owner_name().getEntityValue().getValue().toString(),
          getCOL_team_id_level_1().getEntityValue().getValue().toString(),
          getCOL_team_name_level_1().getEntityValue().getValue().toString(),
          getCOL_team_id_owner_level_1().getEntityValue().getValue().toString(),
          getCOL_owner_name_level_1().getEntityValue().getValue().toString(),
          getCOL_role_name_level_1().getEntityValue().getValue().toString(),
          getCOL_comp_name_level_1().getEntityValue().getValue().toString(),
          getCOL_team_id_level_2().getEntityValue().getValue().toString(),
          getCOL_team_name_level_2().getEntityValue().getValue().toString(),
          getCOL_team_id_owner_level_2().getEntityValue().getValue().toString(),
          getCOL_owner_name_level_2().getEntityValue().getValue().toString(),
          getCOL_role_name_level_2().getEntityValue().getValue().toString(),
          getCOL_comp_name_level_2().getEntityValue().getValue().toString(),
          getCOL_team_id_level_3().getEntityValue().getValue().toString(),
          getCOL_team_name_level_3().getEntityValue().getValue().toString(),
          getCOL_team_id_owner_level_3().getEntityValue().getValue().toString(),
          getCOL_owner_name_level_3().getEntityValue().getValue().toString(),
          getCOL_role_name_level_3().getEntityValue().getValue().toString(),
          getCOL_comp_name_level_3().getEntityValue().getValue().toString()
                );
            }

}