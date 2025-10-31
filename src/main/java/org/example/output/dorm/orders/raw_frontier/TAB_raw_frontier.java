package org.example.output.dorm.orders.raw_frontier;


import org.example.gma.templates.TableTemplate;
import org.example.gma.templates.QueryTemplate;
import org.example.gma.templates.ColumnTemplate;


import org.example.output.dorm.orders.raw_frontier.columns.COL_db_id;
import org.example.output.dorm.orders.raw_frontier.columns.COL_active_service;
import org.example.output.dorm.orders.raw_frontier.columns.COL_agent_first_name;
import org.example.output.dorm.orders.raw_frontier.columns.COL_agent_last_name;
import org.example.output.dorm.orders.raw_frontier.columns.COL_agent_user_id;
import org.example.output.dorm.orders.raw_frontier.columns.COL_auto_pay_attempted;
import org.example.output.dorm.orders.raw_frontier.columns.COL_auto_pay_successful;
import org.example.output.dorm.orders.raw_frontier.columns.COL_autopay_method;
import org.example.output.dorm.orders.raw_frontier.columns.COL_back_balance_value;
import org.example.output.dorm.orders.raw_frontier.columns.COL_btn;
import org.example.output.dorm.orders.raw_frontier.columns.COL_business_type;
import org.example.output.dorm.orders.raw_frontier.columns.COL_campaign_code;
import org.example.output.dorm.orders.raw_frontier.columns.COL_company_name;
import org.example.output.dorm.orders.raw_frontier.columns.COL_customer_cbr;
import org.example.output.dorm.orders.raw_frontier.columns.COL_customer_first_name;
import org.example.output.dorm.orders.raw_frontier.columns.COL_customer_last_name;
import org.example.output.dorm.orders.raw_frontier.columns.COL_data;
import org.example.output.dorm.orders.raw_frontier.columns.COL_deposit_needed;
import org.example.output.dorm.orders.raw_frontier.columns.COL_deposit_value;
import org.example.output.dorm.orders.raw_frontier.columns.COL_email_address;
import org.example.output.dorm.orders.raw_frontier.columns.COL_env;
import org.example.output.dorm.orders.raw_frontier.columns.COL_equipment;
import org.example.output.dorm.orders.raw_frontier.columns.COL_frontier_order_number;
import org.example.output.dorm.orders.raw_frontier.columns.COL_ftr_id_auto_pay_response;
import org.example.output.dorm.orders.raw_frontier.columns.COL_last_modified_date;
import org.example.output.dorm.orders.raw_frontier.columns.COL_line_of_business;
import org.example.output.dorm.orders.raw_frontier.columns.COL_order_age_days;
import org.example.output.dorm.orders.raw_frontier.columns.COL_order_due_date;
import org.example.output.dorm.orders.raw_frontier.columns.COL_order_received_date;
import org.example.output.dorm.orders.raw_frontier.columns.COL_order_stage;
import org.example.output.dorm.orders.raw_frontier.columns.COL_order_stage_desc;
import org.example.output.dorm.orders.raw_frontier.columns.COL_order_status;
import org.example.output.dorm.orders.raw_frontier.columns.COL_order_type;
import org.example.output.dorm.orders.raw_frontier.columns.COL_partner_id;
import org.example.output.dorm.orders.raw_frontier.columns.COL_partner_id_name;
import org.example.output.dorm.orders.raw_frontier.columns.COL_past_due_amt;
import org.example.output.dorm.orders.raw_frontier.columns.COL_past_due_needed;
import org.example.output.dorm.orders.raw_frontier.columns.COL_payment_amount;
import org.example.output.dorm.orders.raw_frontier.columns.COL_payment_successful;
import org.example.output.dorm.orders.raw_frontier.columns.COL_pending_disconnect;
import org.example.output.dorm.orders.raw_frontier.columns.COL_pending_disconnect_due_date;
import org.example.output.dorm.orders.raw_frontier.columns.COL_port_request;
import org.example.output.dorm.orders.raw_frontier.columns.COL_pos_id;
import org.example.output.dorm.orders.raw_frontier.columns.COL_quote_number;
import org.example.output.dorm.orders.raw_frontier.columns.COL_refuse_credit_check;
import org.example.output.dorm.orders.raw_frontier.columns.COL_self_install_capable;
import org.example.output.dorm.orders.raw_frontier.columns.COL_self_install_selected;
import org.example.output.dorm.orders.raw_frontier.columns.COL_service_address;
import org.example.output.dorm.orders.raw_frontier.columns.COL_service_city;
import org.example.output.dorm.orders.raw_frontier.columns.COL_service_state;
import org.example.output.dorm.orders.raw_frontier.columns.COL_service_zip;
import org.example.output.dorm.orders.raw_frontier.columns.COL_ssn_itin_provided;
import org.example.output.dorm.orders.raw_frontier.columns.COL_subscriptions;
import org.example.output.dorm.orders.raw_frontier.columns.COL_value_added_services;
import org.example.output.dorm.orders.raw_frontier.columns.COL_vendor_name;
import org.example.output.dorm.orders.raw_frontier.columns.COL_video;
import org.example.output.dorm.orders.raw_frontier.columns.COL_voice;
import org.example.output.dorm.orders.raw_frontier.columns.COL_voice_add_on;
import org.example.output.dorm.orders.raw_frontier.columns.COL_date_db_changed;
import org.example.output.dorm.orders.raw_frontier.columns.COL_employee_id;
import org.example.output.dorm.orders.raw_frontier.columns.COL_manual_employee_id;

import java.util.List;

import org.example.ContextInstance.KDBContext;

public class TAB_raw_frontier extends TableTemplate {

    public TAB_raw_frontier() {
        super(
            "raw_frontier"
,
            "Table storing raw Frontier order records"
,
            new String[]{"orders", "frontier", "raw"},
            "tab0a55000d-f674-4d69-9bab-479f28b4c1f8"
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





    private final ColumnTemplate COL_active_service = new COL_active_service();

    public ColumnTemplate getCOL_active_service() {
        return COL_active_service;
    }





    private final ColumnTemplate COL_agent_first_name = new COL_agent_first_name();

    public ColumnTemplate getCOL_agent_first_name() {
        return COL_agent_first_name;
    }





    private final ColumnTemplate COL_agent_last_name = new COL_agent_last_name();

    public ColumnTemplate getCOL_agent_last_name() {
        return COL_agent_last_name;
    }





    private final ColumnTemplate COL_agent_user_id = new COL_agent_user_id();

    public ColumnTemplate getCOL_agent_user_id() {
        return COL_agent_user_id;
    }





    private final ColumnTemplate COL_auto_pay_attempted = new COL_auto_pay_attempted();

    public ColumnTemplate getCOL_auto_pay_attempted() {
        return COL_auto_pay_attempted;
    }





    private final ColumnTemplate COL_auto_pay_successful = new COL_auto_pay_successful();

    public ColumnTemplate getCOL_auto_pay_successful() {
        return COL_auto_pay_successful;
    }





    private final ColumnTemplate COL_autopay_method = new COL_autopay_method();

    public ColumnTemplate getCOL_autopay_method() {
        return COL_autopay_method;
    }





    private final ColumnTemplate COL_back_balance_value = new COL_back_balance_value();

    public ColumnTemplate getCOL_back_balance_value() {
        return COL_back_balance_value;
    }





    private final ColumnTemplate COL_btn = new COL_btn();

    public ColumnTemplate getCOL_btn() {
        return COL_btn;
    }





    private final ColumnTemplate COL_business_type = new COL_business_type();

    public ColumnTemplate getCOL_business_type() {
        return COL_business_type;
    }





    private final ColumnTemplate COL_campaign_code = new COL_campaign_code();

    public ColumnTemplate getCOL_campaign_code() {
        return COL_campaign_code;
    }





    private final ColumnTemplate COL_company_name = new COL_company_name();

    public ColumnTemplate getCOL_company_name() {
        return COL_company_name;
    }





    private final ColumnTemplate COL_customer_cbr = new COL_customer_cbr();

    public ColumnTemplate getCOL_customer_cbr() {
        return COL_customer_cbr;
    }





    private final ColumnTemplate COL_customer_first_name = new COL_customer_first_name();

    public ColumnTemplate getCOL_customer_first_name() {
        return COL_customer_first_name;
    }





    private final ColumnTemplate COL_customer_last_name = new COL_customer_last_name();

    public ColumnTemplate getCOL_customer_last_name() {
        return COL_customer_last_name;
    }





    private final ColumnTemplate COL_data = new COL_data();

    public ColumnTemplate getCOL_data() {
        return COL_data;
    }





    private final ColumnTemplate COL_deposit_needed = new COL_deposit_needed();

    public ColumnTemplate getCOL_deposit_needed() {
        return COL_deposit_needed;
    }





    private final ColumnTemplate COL_deposit_value = new COL_deposit_value();

    public ColumnTemplate getCOL_deposit_value() {
        return COL_deposit_value;
    }





    private final ColumnTemplate COL_email_address = new COL_email_address();

    public ColumnTemplate getCOL_email_address() {
        return COL_email_address;
    }





    private final ColumnTemplate COL_env = new COL_env();

    public ColumnTemplate getCOL_env() {
        return COL_env;
    }





    private final ColumnTemplate COL_equipment = new COL_equipment();

    public ColumnTemplate getCOL_equipment() {
        return COL_equipment;
    }





    private final ColumnTemplate COL_frontier_order_number = new COL_frontier_order_number();

    public ColumnTemplate getCOL_frontier_order_number() {
        return COL_frontier_order_number;
    }





    private final ColumnTemplate COL_ftr_id_auto_pay_response = new COL_ftr_id_auto_pay_response();

    public ColumnTemplate getCOL_ftr_id_auto_pay_response() {
        return COL_ftr_id_auto_pay_response;
    }





    private final ColumnTemplate COL_last_modified_date = new COL_last_modified_date();

    public ColumnTemplate getCOL_last_modified_date() {
        return COL_last_modified_date;
    }





    private final ColumnTemplate COL_line_of_business = new COL_line_of_business();

    public ColumnTemplate getCOL_line_of_business() {
        return COL_line_of_business;
    }





    private final ColumnTemplate COL_order_age_days = new COL_order_age_days();

    public ColumnTemplate getCOL_order_age_days() {
        return COL_order_age_days;
    }





    private final ColumnTemplate COL_order_due_date = new COL_order_due_date();

    public ColumnTemplate getCOL_order_due_date() {
        return COL_order_due_date;
    }





    private final ColumnTemplate COL_order_received_date = new COL_order_received_date();

    public ColumnTemplate getCOL_order_received_date() {
        return COL_order_received_date;
    }





    private final ColumnTemplate COL_order_stage = new COL_order_stage();

    public ColumnTemplate getCOL_order_stage() {
        return COL_order_stage;
    }





    private final ColumnTemplate COL_order_stage_desc = new COL_order_stage_desc();

    public ColumnTemplate getCOL_order_stage_desc() {
        return COL_order_stage_desc;
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





    private final ColumnTemplate COL_partner_id_name = new COL_partner_id_name();

    public ColumnTemplate getCOL_partner_id_name() {
        return COL_partner_id_name;
    }





    private final ColumnTemplate COL_past_due_amt = new COL_past_due_amt();

    public ColumnTemplate getCOL_past_due_amt() {
        return COL_past_due_amt;
    }





    private final ColumnTemplate COL_past_due_needed = new COL_past_due_needed();

    public ColumnTemplate getCOL_past_due_needed() {
        return COL_past_due_needed;
    }





    private final ColumnTemplate COL_payment_amount = new COL_payment_amount();

    public ColumnTemplate getCOL_payment_amount() {
        return COL_payment_amount;
    }





    private final ColumnTemplate COL_payment_successful = new COL_payment_successful();

    public ColumnTemplate getCOL_payment_successful() {
        return COL_payment_successful;
    }





    private final ColumnTemplate COL_pending_disconnect = new COL_pending_disconnect();

    public ColumnTemplate getCOL_pending_disconnect() {
        return COL_pending_disconnect;
    }





    private final ColumnTemplate COL_pending_disconnect_due_date = new COL_pending_disconnect_due_date();

    public ColumnTemplate getCOL_pending_disconnect_due_date() {
        return COL_pending_disconnect_due_date;
    }





    private final ColumnTemplate COL_port_request = new COL_port_request();

    public ColumnTemplate getCOL_port_request() {
        return COL_port_request;
    }





    private final ColumnTemplate COL_pos_id = new COL_pos_id();

    public ColumnTemplate getCOL_pos_id() {
        return COL_pos_id;
    }





    private final ColumnTemplate COL_quote_number = new COL_quote_number();

    public ColumnTemplate getCOL_quote_number() {
        return COL_quote_number;
    }





    private final ColumnTemplate COL_refuse_credit_check = new COL_refuse_credit_check();

    public ColumnTemplate getCOL_refuse_credit_check() {
        return COL_refuse_credit_check;
    }





    private final ColumnTemplate COL_self_install_capable = new COL_self_install_capable();

    public ColumnTemplate getCOL_self_install_capable() {
        return COL_self_install_capable;
    }





    private final ColumnTemplate COL_self_install_selected = new COL_self_install_selected();

    public ColumnTemplate getCOL_self_install_selected() {
        return COL_self_install_selected;
    }





    private final ColumnTemplate COL_service_address = new COL_service_address();

    public ColumnTemplate getCOL_service_address() {
        return COL_service_address;
    }





    private final ColumnTemplate COL_service_city = new COL_service_city();

    public ColumnTemplate getCOL_service_city() {
        return COL_service_city;
    }





    private final ColumnTemplate COL_service_state = new COL_service_state();

    public ColumnTemplate getCOL_service_state() {
        return COL_service_state;
    }





    private final ColumnTemplate COL_service_zip = new COL_service_zip();

    public ColumnTemplate getCOL_service_zip() {
        return COL_service_zip;
    }





    private final ColumnTemplate COL_ssn_itin_provided = new COL_ssn_itin_provided();

    public ColumnTemplate getCOL_ssn_itin_provided() {
        return COL_ssn_itin_provided;
    }





    private final ColumnTemplate COL_subscriptions = new COL_subscriptions();

    public ColumnTemplate getCOL_subscriptions() {
        return COL_subscriptions;
    }





    private final ColumnTemplate COL_value_added_services = new COL_value_added_services();

    public ColumnTemplate getCOL_value_added_services() {
        return COL_value_added_services;
    }





    private final ColumnTemplate COL_vendor_name = new COL_vendor_name();

    public ColumnTemplate getCOL_vendor_name() {
        return COL_vendor_name;
    }





    private final ColumnTemplate COL_video = new COL_video();

    public ColumnTemplate getCOL_video() {
        return COL_video;
    }





    private final ColumnTemplate COL_voice = new COL_voice();

    public ColumnTemplate getCOL_voice() {
        return COL_voice;
    }





    private final ColumnTemplate COL_voice_add_on = new COL_voice_add_on();

    public ColumnTemplate getCOL_voice_add_on() {
        return COL_voice_add_on;
    }





    private final ColumnTemplate COL_date_db_changed = new COL_date_db_changed();

    public ColumnTemplate getCOL_date_db_changed() {
        return COL_date_db_changed;
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
    return String.format("('%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s')\n",
                      getCOL_db_id().getEntityValue().getValue().toString(),
          getCOL_active_service().getEntityValue().getValue().toString(),
          getCOL_agent_first_name().getEntityValue().getValue().toString(),
          getCOL_agent_last_name().getEntityValue().getValue().toString(),
          getCOL_agent_user_id().getEntityValue().getValue().toString(),
          getCOL_auto_pay_attempted().getEntityValue().getValue().toString(),
          getCOL_auto_pay_successful().getEntityValue().getValue().toString(),
          getCOL_autopay_method().getEntityValue().getValue().toString(),
          getCOL_back_balance_value().getEntityValue().getValue().toString(),
          getCOL_btn().getEntityValue().getValue().toString(),
          getCOL_business_type().getEntityValue().getValue().toString(),
          getCOL_campaign_code().getEntityValue().getValue().toString(),
          getCOL_company_name().getEntityValue().getValue().toString(),
          getCOL_customer_cbr().getEntityValue().getValue().toString(),
          getCOL_customer_first_name().getEntityValue().getValue().toString(),
          getCOL_customer_last_name().getEntityValue().getValue().toString(),
          getCOL_data().getEntityValue().getValue().toString(),
          getCOL_deposit_needed().getEntityValue().getValue().toString(),
          getCOL_deposit_value().getEntityValue().getValue().toString(),
          getCOL_email_address().getEntityValue().getValue().toString(),
          getCOL_env().getEntityValue().getValue().toString(),
          getCOL_equipment().getEntityValue().getValue().toString(),
          getCOL_frontier_order_number().getEntityValue().getValue().toString(),
          getCOL_ftr_id_auto_pay_response().getEntityValue().getValue().toString(),
          getCOL_last_modified_date().getEntityValue().getValue().toString(),
          getCOL_line_of_business().getEntityValue().getValue().toString(),
          getCOL_order_age_days().getEntityValue().getValue().toString(),
          getCOL_order_due_date().getEntityValue().getValue().toString(),
          getCOL_order_received_date().getEntityValue().getValue().toString(),
          getCOL_order_stage().getEntityValue().getValue().toString(),
          getCOL_order_stage_desc().getEntityValue().getValue().toString(),
          getCOL_order_status().getEntityValue().getValue().toString(),
          getCOL_order_type().getEntityValue().getValue().toString(),
          getCOL_partner_id().getEntityValue().getValue().toString(),
          getCOL_partner_id_name().getEntityValue().getValue().toString(),
          getCOL_past_due_amt().getEntityValue().getValue().toString(),
          getCOL_past_due_needed().getEntityValue().getValue().toString(),
          getCOL_payment_amount().getEntityValue().getValue().toString(),
          getCOL_payment_successful().getEntityValue().getValue().toString(),
          getCOL_pending_disconnect().getEntityValue().getValue().toString(),
          getCOL_pending_disconnect_due_date().getEntityValue().getValue().toString(),
          getCOL_port_request().getEntityValue().getValue().toString(),
          getCOL_pos_id().getEntityValue().getValue().toString(),
          getCOL_quote_number().getEntityValue().getValue().toString(),
          getCOL_refuse_credit_check().getEntityValue().getValue().toString(),
          getCOL_self_install_capable().getEntityValue().getValue().toString(),
          getCOL_self_install_selected().getEntityValue().getValue().toString(),
          getCOL_service_address().getEntityValue().getValue().toString(),
          getCOL_service_city().getEntityValue().getValue().toString(),
          getCOL_service_state().getEntityValue().getValue().toString(),
          getCOL_service_zip().getEntityValue().getValue().toString(),
          getCOL_ssn_itin_provided().getEntityValue().getValue().toString(),
          getCOL_subscriptions().getEntityValue().getValue().toString(),
          getCOL_value_added_services().getEntityValue().getValue().toString(),
          getCOL_vendor_name().getEntityValue().getValue().toString(),
          getCOL_video().getEntityValue().getValue().toString(),
          getCOL_voice().getEntityValue().getValue().toString(),
          getCOL_voice_add_on().getEntityValue().getValue().toString(),
          getCOL_date_db_changed().getEntityValue().getValue().toString(),
          getCOL_employee_id().getEntityValue().getValue().toString(),
          getCOL_manual_employee_id().getEntityValue().getValue().toString()
    );
}
            @Override
            public String getValues(String arg) {
                return String.format("('%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s')\n",
          getCOL_db_id().getEntityValue().getValue().toString(),
          getCOL_active_service().getEntityValue().getValue().toString(),
          getCOL_agent_first_name().getEntityValue().getValue().toString(),
          getCOL_agent_last_name().getEntityValue().getValue().toString(),
          getCOL_agent_user_id().getEntityValue().getValue().toString(),
          getCOL_auto_pay_attempted().getEntityValue().getValue().toString(),
          getCOL_auto_pay_successful().getEntityValue().getValue().toString(),
          getCOL_autopay_method().getEntityValue().getValue().toString(),
          getCOL_back_balance_value().getEntityValue().getValue().toString(),
          getCOL_btn().getEntityValue().getValue().toString(),
          getCOL_business_type().getEntityValue().getValue().toString(),
          getCOL_campaign_code().getEntityValue().getValue().toString(),
          getCOL_company_name().getEntityValue().getValue().toString(),
          getCOL_customer_cbr().getEntityValue().getValue().toString(),
          getCOL_customer_first_name().getEntityValue().getValue().toString(),
          getCOL_customer_last_name().getEntityValue().getValue().toString(),
          getCOL_data().getEntityValue().getValue().toString(),
          getCOL_deposit_needed().getEntityValue().getValue().toString(),
          getCOL_deposit_value().getEntityValue().getValue().toString(),
          getCOL_email_address().getEntityValue().getValue().toString(),
          getCOL_env().getEntityValue().getValue().toString(),
          getCOL_equipment().getEntityValue().getValue().toString(),
          getCOL_frontier_order_number().getEntityValue().getValue().toString(),
          getCOL_ftr_id_auto_pay_response().getEntityValue().getValue().toString(),
          getCOL_last_modified_date().getEntityValue().getValue().toString(),
          getCOL_line_of_business().getEntityValue().getValue().toString(),
          getCOL_order_age_days().getEntityValue().getValue().toString(),
          getCOL_order_due_date().getEntityValue().getValue().toString(),
          getCOL_order_received_date().getEntityValue().getValue().toString(),
          getCOL_order_stage().getEntityValue().getValue().toString(),
          getCOL_order_stage_desc().getEntityValue().getValue().toString(),
          getCOL_order_status().getEntityValue().getValue().toString(),
          getCOL_order_type().getEntityValue().getValue().toString(),
          getCOL_partner_id().getEntityValue().getValue().toString(),
          getCOL_partner_id_name().getEntityValue().getValue().toString(),
          getCOL_past_due_amt().getEntityValue().getValue().toString(),
          getCOL_past_due_needed().getEntityValue().getValue().toString(),
          getCOL_payment_amount().getEntityValue().getValue().toString(),
          getCOL_payment_successful().getEntityValue().getValue().toString(),
          getCOL_pending_disconnect().getEntityValue().getValue().toString(),
          getCOL_pending_disconnect_due_date().getEntityValue().getValue().toString(),
          getCOL_port_request().getEntityValue().getValue().toString(),
          getCOL_pos_id().getEntityValue().getValue().toString(),
          getCOL_quote_number().getEntityValue().getValue().toString(),
          getCOL_refuse_credit_check().getEntityValue().getValue().toString(),
          getCOL_self_install_capable().getEntityValue().getValue().toString(),
          getCOL_self_install_selected().getEntityValue().getValue().toString(),
          getCOL_service_address().getEntityValue().getValue().toString(),
          getCOL_service_city().getEntityValue().getValue().toString(),
          getCOL_service_state().getEntityValue().getValue().toString(),
          getCOL_service_zip().getEntityValue().getValue().toString(),
          getCOL_ssn_itin_provided().getEntityValue().getValue().toString(),
          getCOL_subscriptions().getEntityValue().getValue().toString(),
          getCOL_value_added_services().getEntityValue().getValue().toString(),
          getCOL_vendor_name().getEntityValue().getValue().toString(),
          getCOL_video().getEntityValue().getValue().toString(),
          getCOL_voice().getEntityValue().getValue().toString(),
          getCOL_voice_add_on().getEntityValue().getValue().toString(),
          getCOL_date_db_changed().getEntityValue().getValue().toString(),
          getCOL_employee_id().getEntityValue().getValue().toString(),
          getCOL_manual_employee_id().getEntityValue().getValue().toString()
                );
            }

}