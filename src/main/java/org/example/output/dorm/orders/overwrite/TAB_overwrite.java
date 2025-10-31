package org.example.output.dorm.orders.overwrite;


import org.example.gma.templates.TableTemplate;
import org.example.gma.templates.QueryTemplate;
import org.example.gma.templates.ColumnTemplate;


import org.example.output.dorm.orders.overwrite.columns.COL_my_row_id;
import org.example.output.dorm.orders.overwrite.columns.COL_id;
import org.example.output.dorm.orders.overwrite.columns.COL_account_number;
import org.example.output.dorm.orders.overwrite.columns.COL_carrier_system;
import org.example.output.dorm.orders.overwrite.columns.COL_area_name;
import org.example.output.dorm.orders.overwrite.columns.COL_carrier;
import org.example.output.dorm.orders.overwrite.columns.COL_carrier_speed;
import org.example.output.dorm.orders.overwrite.columns.COL_city;
import org.example.output.dorm.orders.overwrite.columns.COL_commission;
import org.example.output.dorm.orders.overwrite.columns.COL_customer_address_1;
import org.example.output.dorm.orders.overwrite.columns.COL_customer_address_2;
import org.example.output.dorm.orders.overwrite.columns.COL_customer_contact_number;
import org.example.output.dorm.orders.overwrite.columns.COL_customer_name;
import org.example.output.dorm.orders.overwrite.columns.COL_dept_code;
import org.example.output.dorm.orders.overwrite.columns.COL_div_code;
import org.example.output.dorm.orders.overwrite.columns.COL_division_name;
import org.example.output.dorm.orders.overwrite.columns.COL_employee_id;
import org.example.output.dorm.orders.overwrite.columns.COL_install_date;
import org.example.output.dorm.orders.overwrite.columns.COL_manager_id;
import org.example.output.dorm.orders.overwrite.columns.COL_manager_name;
import org.example.output.dorm.orders.overwrite.columns.COL_mdu_d2d;
import org.example.output.dorm.orders.overwrite.columns.COL_mobile_number;
import org.example.output.dorm.orders.overwrite.columns.COL_order_date;
import org.example.output.dorm.orders.overwrite.columns.COL_order_id;
import org.example.output.dorm.orders.overwrite.columns.COL_order_number;
import org.example.output.dorm.orders.overwrite.columns.COL_purchase_type;
import org.example.output.dorm.orders.overwrite.columns.COL_package_name;
import org.example.output.dorm.orders.overwrite.columns.COL_product;
import org.example.output.dorm.orders.overwrite.columns.COL_product_type;
import org.example.output.dorm.orders.overwrite.columns.COL_rd_id;
import org.example.output.dorm.orders.overwrite.columns.COL_region_code;
import org.example.output.dorm.orders.overwrite.columns.COL_region_name;
import org.example.output.dorm.orders.overwrite.columns.COL_regional_director;
import org.example.output.dorm.orders.overwrite.columns.COL_rgn_code;
import org.example.output.dorm.orders.overwrite.columns.COL_salesperson;
import org.example.output.dorm.orders.overwrite.columns.COL_speed_bucket;
import org.example.output.dorm.orders.overwrite.columns.COL_state;
import org.example.output.dorm.orders.overwrite.columns.COL_status;
import org.example.output.dorm.orders.overwrite.columns.COL_unit_gross;
import org.example.output.dorm.orders.overwrite.columns.COL_ref_login_username_id;
import org.example.output.dorm.orders.overwrite.columns.COL_vp;
import org.example.output.dorm.orders.overwrite.columns.COL_vp_id;
import org.example.output.dorm.orders.overwrite.columns.COL_zip_5;
import org.example.output.dorm.orders.overwrite.columns.COL_latest_insert_date;
import org.example.output.dorm.orders.overwrite.columns.COL_unit_weighted_gross;
import org.example.output.dorm.orders.overwrite.columns.COL_order_method;
import org.example.output.dorm.orders.overwrite.columns.COL_staged_at;
import org.example.output.dorm.orders.overwrite.columns.COL_referrer_eid;
import org.example.output.dorm.orders.overwrite.columns.COL_unit_net;
import org.example.output.dorm.orders.overwrite.columns.COL_unit_weighted_net;
import org.example.output.dorm.orders.overwrite.columns.COL_db_id;
import org.example.output.dorm.orders.overwrite.columns.COL_employee_start_date;
import org.example.output.dorm.orders.overwrite.columns.COL_name;
import org.example.output.dorm.orders.overwrite.columns.COL_addon;
import org.example.output.dorm.orders.overwrite.columns.COL_addon_count;
import org.example.output.dorm.orders.overwrite.columns.COL_mo_general_ref_id;
import org.example.output.dorm.orders.overwrite.columns.COL_mo_reconciliation_ref_id;
import org.example.output.dorm.orders.overwrite.columns.COL_mo_reconciliation_ref_id_2;
import org.example.output.dorm.orders.overwrite.columns.COL_mo_reconciliation_ref_id_3;
import org.example.output.dorm.orders.overwrite.columns.COL_mo_reconciliation_ref_id_4;
import org.example.output.dorm.orders.overwrite.columns.COL_afrom;
import org.example.output.dorm.orders.overwrite.columns.COL_customer_email;
import org.example.output.dorm.orders.overwrite.columns.COL_order_type_new_upgrade;
import org.example.output.dorm.orders.overwrite.columns.COL_username;
import org.example.output.dorm.orders.overwrite.columns.COL_business_unit;

import java.util.List;

import org.example.ContextInstance.KDBContext;

public class TAB_overwrite extends TableTemplate {

    public TAB_overwrite() {
        super(
            "overwrite"
,
            "Table storing overwrite order records"
,
            new String[]{"orders", "overwrite", "tracking"},
            "tab7bcb9f3b-36d2-40ce-b652-1b73c6cb9521"
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





    private final ColumnTemplate COL_id = new COL_id();

    public ColumnTemplate getCOL_id() {
        return COL_id;
    }





    private final ColumnTemplate COL_account_number = new COL_account_number();

    public ColumnTemplate getCOL_account_number() {
        return COL_account_number;
    }





    private final ColumnTemplate COL_carrier_system = new COL_carrier_system();

    public ColumnTemplate getCOL_carrier_system() {
        return COL_carrier_system;
    }





    private final ColumnTemplate COL_area_name = new COL_area_name();

    public ColumnTemplate getCOL_area_name() {
        return COL_area_name;
    }





    private final ColumnTemplate COL_carrier = new COL_carrier();

    public ColumnTemplate getCOL_carrier() {
        return COL_carrier;
    }





    private final ColumnTemplate COL_carrier_speed = new COL_carrier_speed();

    public ColumnTemplate getCOL_carrier_speed() {
        return COL_carrier_speed;
    }





    private final ColumnTemplate COL_city = new COL_city();

    public ColumnTemplate getCOL_city() {
        return COL_city;
    }





    private final ColumnTemplate COL_commission = new COL_commission();

    public ColumnTemplate getCOL_commission() {
        return COL_commission;
    }





    private final ColumnTemplate COL_customer_address_1 = new COL_customer_address_1();

    public ColumnTemplate getCOL_customer_address_1() {
        return COL_customer_address_1;
    }





    private final ColumnTemplate COL_customer_address_2 = new COL_customer_address_2();

    public ColumnTemplate getCOL_customer_address_2() {
        return COL_customer_address_2;
    }





    private final ColumnTemplate COL_customer_contact_number = new COL_customer_contact_number();

    public ColumnTemplate getCOL_customer_contact_number() {
        return COL_customer_contact_number;
    }





    private final ColumnTemplate COL_customer_name = new COL_customer_name();

    public ColumnTemplate getCOL_customer_name() {
        return COL_customer_name;
    }





    private final ColumnTemplate COL_dept_code = new COL_dept_code();

    public ColumnTemplate getCOL_dept_code() {
        return COL_dept_code;
    }





    private final ColumnTemplate COL_div_code = new COL_div_code();

    public ColumnTemplate getCOL_div_code() {
        return COL_div_code;
    }





    private final ColumnTemplate COL_division_name = new COL_division_name();

    public ColumnTemplate getCOL_division_name() {
        return COL_division_name;
    }





    private final ColumnTemplate COL_employee_id = new COL_employee_id();

    public ColumnTemplate getCOL_employee_id() {
        return COL_employee_id;
    }





    private final ColumnTemplate COL_install_date = new COL_install_date();

    public ColumnTemplate getCOL_install_date() {
        return COL_install_date;
    }





    private final ColumnTemplate COL_manager_id = new COL_manager_id();

    public ColumnTemplate getCOL_manager_id() {
        return COL_manager_id;
    }





    private final ColumnTemplate COL_manager_name = new COL_manager_name();

    public ColumnTemplate getCOL_manager_name() {
        return COL_manager_name;
    }





    private final ColumnTemplate COL_mdu_d2d = new COL_mdu_d2d();

    public ColumnTemplate getCOL_mdu_d2d() {
        return COL_mdu_d2d;
    }





    private final ColumnTemplate COL_mobile_number = new COL_mobile_number();

    public ColumnTemplate getCOL_mobile_number() {
        return COL_mobile_number;
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





    private final ColumnTemplate COL_rd_id = new COL_rd_id();

    public ColumnTemplate getCOL_rd_id() {
        return COL_rd_id;
    }





    private final ColumnTemplate COL_region_code = new COL_region_code();

    public ColumnTemplate getCOL_region_code() {
        return COL_region_code;
    }





    private final ColumnTemplate COL_region_name = new COL_region_name();

    public ColumnTemplate getCOL_region_name() {
        return COL_region_name;
    }





    private final ColumnTemplate COL_regional_director = new COL_regional_director();

    public ColumnTemplate getCOL_regional_director() {
        return COL_regional_director;
    }





    private final ColumnTemplate COL_rgn_code = new COL_rgn_code();

    public ColumnTemplate getCOL_rgn_code() {
        return COL_rgn_code;
    }





    private final ColumnTemplate COL_salesperson = new COL_salesperson();

    public ColumnTemplate getCOL_salesperson() {
        return COL_salesperson;
    }





    private final ColumnTemplate COL_speed_bucket = new COL_speed_bucket();

    public ColumnTemplate getCOL_speed_bucket() {
        return COL_speed_bucket;
    }





    private final ColumnTemplate COL_state = new COL_state();

    public ColumnTemplate getCOL_state() {
        return COL_state;
    }





    private final ColumnTemplate COL_status = new COL_status();

    public ColumnTemplate getCOL_status() {
        return COL_status;
    }





    private final ColumnTemplate COL_unit_gross = new COL_unit_gross();

    public ColumnTemplate getCOL_unit_gross() {
        return COL_unit_gross;
    }





    private final ColumnTemplate COL_ref_login_username_id = new COL_ref_login_username_id();

    public ColumnTemplate getCOL_ref_login_username_id() {
        return COL_ref_login_username_id;
    }





    private final ColumnTemplate COL_vp = new COL_vp();

    public ColumnTemplate getCOL_vp() {
        return COL_vp;
    }





    private final ColumnTemplate COL_vp_id = new COL_vp_id();

    public ColumnTemplate getCOL_vp_id() {
        return COL_vp_id;
    }





    private final ColumnTemplate COL_zip_5 = new COL_zip_5();

    public ColumnTemplate getCOL_zip_5() {
        return COL_zip_5;
    }





    private final ColumnTemplate COL_latest_insert_date = new COL_latest_insert_date();

    public ColumnTemplate getCOL_latest_insert_date() {
        return COL_latest_insert_date;
    }





    private final ColumnTemplate COL_unit_weighted_gross = new COL_unit_weighted_gross();

    public ColumnTemplate getCOL_unit_weighted_gross() {
        return COL_unit_weighted_gross;
    }





    private final ColumnTemplate COL_order_method = new COL_order_method();

    public ColumnTemplate getCOL_order_method() {
        return COL_order_method;
    }





    private final ColumnTemplate COL_staged_at = new COL_staged_at();

    public ColumnTemplate getCOL_staged_at() {
        return COL_staged_at;
    }





    private final ColumnTemplate COL_referrer_eid = new COL_referrer_eid();

    public ColumnTemplate getCOL_referrer_eid() {
        return COL_referrer_eid;
    }





    private final ColumnTemplate COL_unit_net = new COL_unit_net();

    public ColumnTemplate getCOL_unit_net() {
        return COL_unit_net;
    }





    private final ColumnTemplate COL_unit_weighted_net = new COL_unit_weighted_net();

    public ColumnTemplate getCOL_unit_weighted_net() {
        return COL_unit_weighted_net;
    }





    private final ColumnTemplate COL_db_id = new COL_db_id();

    public ColumnTemplate getCOL_db_id() {
        return COL_db_id;
    }





    private final ColumnTemplate COL_employee_start_date = new COL_employee_start_date();

    public ColumnTemplate getCOL_employee_start_date() {
        return COL_employee_start_date;
    }





    private final ColumnTemplate COL_name = new COL_name();

    public ColumnTemplate getCOL_name() {
        return COL_name;
    }





    private final ColumnTemplate COL_addon = new COL_addon();

    public ColumnTemplate getCOL_addon() {
        return COL_addon;
    }





    private final ColumnTemplate COL_addon_count = new COL_addon_count();

    public ColumnTemplate getCOL_addon_count() {
        return COL_addon_count;
    }





    private final ColumnTemplate COL_mo_general_ref_id = new COL_mo_general_ref_id();

    public ColumnTemplate getCOL_mo_general_ref_id() {
        return COL_mo_general_ref_id;
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





    private final ColumnTemplate COL_afrom = new COL_afrom();

    public ColumnTemplate getCOL_afrom() {
        return COL_afrom;
    }





    private final ColumnTemplate COL_customer_email = new COL_customer_email();

    public ColumnTemplate getCOL_customer_email() {
        return COL_customer_email;
    }





    private final ColumnTemplate COL_order_type_new_upgrade = new COL_order_type_new_upgrade();

    public ColumnTemplate getCOL_order_type_new_upgrade() {
        return COL_order_type_new_upgrade;
    }





    private final ColumnTemplate COL_username = new COL_username();

    public ColumnTemplate getCOL_username() {
        return COL_username;
    }





    private final ColumnTemplate COL_business_unit = new COL_business_unit();

    public ColumnTemplate getCOL_business_unit() {
        return COL_business_unit;
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
    return String.format("('%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s')\n",
                      getCOL_my_row_id().getEntityValue().getValue().toString(),
          getCOL_id().getEntityValue().getValue().toString(),
          getCOL_account_number().getEntityValue().getValue().toString(),
          getCOL_carrier_system().getEntityValue().getValue().toString(),
          getCOL_area_name().getEntityValue().getValue().toString(),
          getCOL_carrier().getEntityValue().getValue().toString(),
          getCOL_carrier_speed().getEntityValue().getValue().toString(),
          getCOL_city().getEntityValue().getValue().toString(),
          getCOL_commission().getEntityValue().getValue().toString(),
          getCOL_customer_address_1().getEntityValue().getValue().toString(),
          getCOL_customer_address_2().getEntityValue().getValue().toString(),
          getCOL_customer_contact_number().getEntityValue().getValue().toString(),
          getCOL_customer_name().getEntityValue().getValue().toString(),
          getCOL_dept_code().getEntityValue().getValue().toString(),
          getCOL_div_code().getEntityValue().getValue().toString(),
          getCOL_division_name().getEntityValue().getValue().toString(),
          getCOL_employee_id().getEntityValue().getValue().toString(),
          getCOL_install_date().getEntityValue().getValue().toString(),
          getCOL_manager_id().getEntityValue().getValue().toString(),
          getCOL_manager_name().getEntityValue().getValue().toString(),
          getCOL_mdu_d2d().getEntityValue().getValue().toString(),
          getCOL_mobile_number().getEntityValue().getValue().toString(),
          getCOL_order_date().getEntityValue().getValue().toString(),
          getCOL_order_id().getEntityValue().getValue().toString(),
          getCOL_order_number().getEntityValue().getValue().toString(),
          getCOL_purchase_type().getEntityValue().getValue().toString(),
          getCOL_package_name().getEntityValue().getValue().toString(),
          getCOL_product().getEntityValue().getValue().toString(),
          getCOL_product_type().getEntityValue().getValue().toString(),
          getCOL_rd_id().getEntityValue().getValue().toString(),
          getCOL_region_code().getEntityValue().getValue().toString(),
          getCOL_region_name().getEntityValue().getValue().toString(),
          getCOL_regional_director().getEntityValue().getValue().toString(),
          getCOL_rgn_code().getEntityValue().getValue().toString(),
          getCOL_salesperson().getEntityValue().getValue().toString(),
          getCOL_speed_bucket().getEntityValue().getValue().toString(),
          getCOL_state().getEntityValue().getValue().toString(),
          getCOL_status().getEntityValue().getValue().toString(),
          getCOL_unit_gross().getEntityValue().getValue().toString(),
          getCOL_ref_login_username_id().getEntityValue().getValue().toString(),
          getCOL_vp().getEntityValue().getValue().toString(),
          getCOL_vp_id().getEntityValue().getValue().toString(),
          getCOL_zip_5().getEntityValue().getValue().toString(),
          getCOL_latest_insert_date().getEntityValue().getValue().toString(),
          getCOL_unit_weighted_gross().getEntityValue().getValue().toString(),
          getCOL_order_method().getEntityValue().getValue().toString(),
          getCOL_staged_at().getEntityValue().getValue().toString(),
          getCOL_referrer_eid().getEntityValue().getValue().toString(),
          getCOL_unit_net().getEntityValue().getValue().toString(),
          getCOL_unit_weighted_net().getEntityValue().getValue().toString(),
          getCOL_db_id().getEntityValue().getValue().toString(),
          getCOL_employee_start_date().getEntityValue().getValue().toString(),
          getCOL_name().getEntityValue().getValue().toString(),
          getCOL_addon().getEntityValue().getValue().toString(),
          getCOL_addon_count().getEntityValue().getValue().toString(),
          getCOL_mo_general_ref_id().getEntityValue().getValue().toString(),
          getCOL_mo_reconciliation_ref_id().getEntityValue().getValue().toString(),
          getCOL_mo_reconciliation_ref_id_2().getEntityValue().getValue().toString(),
          getCOL_mo_reconciliation_ref_id_3().getEntityValue().getValue().toString(),
          getCOL_mo_reconciliation_ref_id_4().getEntityValue().getValue().toString(),
          getCOL_afrom().getEntityValue().getValue().toString(),
          getCOL_customer_email().getEntityValue().getValue().toString(),
          getCOL_order_type_new_upgrade().getEntityValue().getValue().toString(),
          getCOL_username().getEntityValue().getValue().toString(),
          getCOL_business_unit().getEntityValue().getValue().toString()
    );
}
            @Override
            public String getValues(String arg) {
                return String.format("('%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s')\n",
          getCOL_my_row_id().getEntityValue().getValue().toString(),
          getCOL_id().getEntityValue().getValue().toString(),
          getCOL_account_number().getEntityValue().getValue().toString(),
          getCOL_carrier_system().getEntityValue().getValue().toString(),
          getCOL_area_name().getEntityValue().getValue().toString(),
          getCOL_carrier().getEntityValue().getValue().toString(),
          getCOL_carrier_speed().getEntityValue().getValue().toString(),
          getCOL_city().getEntityValue().getValue().toString(),
          getCOL_commission().getEntityValue().getValue().toString(),
          getCOL_customer_address_1().getEntityValue().getValue().toString(),
          getCOL_customer_address_2().getEntityValue().getValue().toString(),
          getCOL_customer_contact_number().getEntityValue().getValue().toString(),
          getCOL_customer_name().getEntityValue().getValue().toString(),
          getCOL_dept_code().getEntityValue().getValue().toString(),
          getCOL_div_code().getEntityValue().getValue().toString(),
          getCOL_division_name().getEntityValue().getValue().toString(),
          getCOL_employee_id().getEntityValue().getValue().toString(),
          getCOL_install_date().getEntityValue().getValue().toString(),
          getCOL_manager_id().getEntityValue().getValue().toString(),
          getCOL_manager_name().getEntityValue().getValue().toString(),
          getCOL_mdu_d2d().getEntityValue().getValue().toString(),
          getCOL_mobile_number().getEntityValue().getValue().toString(),
          getCOL_order_date().getEntityValue().getValue().toString(),
          getCOL_order_id().getEntityValue().getValue().toString(),
          getCOL_order_number().getEntityValue().getValue().toString(),
          getCOL_purchase_type().getEntityValue().getValue().toString(),
          getCOL_package_name().getEntityValue().getValue().toString(),
          getCOL_product().getEntityValue().getValue().toString(),
          getCOL_product_type().getEntityValue().getValue().toString(),
          getCOL_rd_id().getEntityValue().getValue().toString(),
          getCOL_region_code().getEntityValue().getValue().toString(),
          getCOL_region_name().getEntityValue().getValue().toString(),
          getCOL_regional_director().getEntityValue().getValue().toString(),
          getCOL_rgn_code().getEntityValue().getValue().toString(),
          getCOL_salesperson().getEntityValue().getValue().toString(),
          getCOL_speed_bucket().getEntityValue().getValue().toString(),
          getCOL_state().getEntityValue().getValue().toString(),
          getCOL_status().getEntityValue().getValue().toString(),
          getCOL_unit_gross().getEntityValue().getValue().toString(),
          getCOL_ref_login_username_id().getEntityValue().getValue().toString(),
          getCOL_vp().getEntityValue().getValue().toString(),
          getCOL_vp_id().getEntityValue().getValue().toString(),
          getCOL_zip_5().getEntityValue().getValue().toString(),
          getCOL_latest_insert_date().getEntityValue().getValue().toString(),
          getCOL_unit_weighted_gross().getEntityValue().getValue().toString(),
          getCOL_order_method().getEntityValue().getValue().toString(),
          getCOL_staged_at().getEntityValue().getValue().toString(),
          getCOL_referrer_eid().getEntityValue().getValue().toString(),
          getCOL_unit_net().getEntityValue().getValue().toString(),
          getCOL_unit_weighted_net().getEntityValue().getValue().toString(),
          getCOL_db_id().getEntityValue().getValue().toString(),
          getCOL_employee_start_date().getEntityValue().getValue().toString(),
          getCOL_name().getEntityValue().getValue().toString(),
          getCOL_addon().getEntityValue().getValue().toString(),
          getCOL_addon_count().getEntityValue().getValue().toString(),
          getCOL_mo_general_ref_id().getEntityValue().getValue().toString(),
          getCOL_mo_reconciliation_ref_id().getEntityValue().getValue().toString(),
          getCOL_mo_reconciliation_ref_id_2().getEntityValue().getValue().toString(),
          getCOL_mo_reconciliation_ref_id_3().getEntityValue().getValue().toString(),
          getCOL_mo_reconciliation_ref_id_4().getEntityValue().getValue().toString(),
          getCOL_afrom().getEntityValue().getValue().toString(),
          getCOL_customer_email().getEntityValue().getValue().toString(),
          getCOL_order_type_new_upgrade().getEntityValue().getValue().toString(),
          getCOL_username().getEntityValue().getValue().toString(),
          getCOL_business_unit().getEntityValue().getValue().toString()
                );
            }

}