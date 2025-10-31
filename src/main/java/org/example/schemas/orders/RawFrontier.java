package org.example.schemas.orders;

import org.example.Annotations.KdbColumn;
import org.example.Annotations.KdbPrimaryKey;
import org.example.Annotations.KdbTable;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@KdbTable(
        description = "Table storing raw Frontier order records",
        name = "raw_frontier",
        tags = {"orders", "frontier", "raw"},
        type = ""
)
@Component
public class RawFrontier {

    @KdbPrimaryKey
    @KdbColumn(name = "db_id")
    private String dbId;

    @KdbColumn(name = "active_service")
    private String activeService;

    @KdbColumn(name = "agent_first_name")
    private String agentFirstName;

    @KdbColumn(name = "agent_last_name")
    private String agentLastName;

    @KdbColumn(name = "agent_user_id")
    private String agentUserId;

    @KdbColumn(name = "auto_pay_attempted")
    private String autoPayAttempted;

    @KdbColumn(name = "auto_pay_successful")
    private String autoPaySuccessful;

    @KdbColumn(name = "autopay_method")
    private String autopayMethod;

    @KdbColumn(name = "back_balance_value")
    private String backBalanceValue;

    @KdbColumn(name = "btn")
    private String btn;

    @KdbColumn(name = "business_type")
    private String businessType;

    @KdbColumn(name = "campaign_code")
    private String campaignCode;

    @KdbColumn(name = "company_name")
    private String companyName;

    @KdbColumn(name = "customer_cbr")
    private String customerCbr;

    @KdbColumn(name = "customer_first_name")
    private String customerFirstName;

    @KdbColumn(name = "customer_last_name")
    private String customerLastName;

    @KdbColumn(name = "data")
    private String data;

    @KdbColumn(name = "deposit_needed")
    private String depositNeeded;

    @KdbColumn(name = "deposit_value")
    private String depositValue;

    @KdbColumn(name = "email_address")
    private String emailAddress;

    @KdbColumn(name = "env")
    private String env;

    @KdbColumn(name = "equipment")
    private String equipment;

    @KdbColumn(name = "frontier_order_number")
    private String frontierOrderNumber;

    @KdbColumn(name = "ftr_id_auto_pay_response")
    private String ftrIdAutoPayResponse;

    @KdbColumn(name = "last_modified_date")
    private LocalDateTime lastModifiedDate;

    @KdbColumn(name = "line_of_business")
    private String lineOfBusiness;

    @KdbColumn(name = "order_age_days")
    private String orderAgeDays;

    @KdbColumn(name = "order_due_date")
    private LocalDateTime orderDueDate;

    @KdbColumn(name = "order_received_date")
    private LocalDateTime orderReceivedDate;

    @KdbColumn(name = "order_stage")
    private String orderStage;

    @KdbColumn(name = "order_stage_desc")
    private String orderStageDesc;

    @KdbColumn(name = "order_status")
    private String orderStatus;

    @KdbColumn(name = "order_type")
    private String orderType;

    @KdbColumn(name = "partner_id")
    private String partnerId;

    @KdbColumn(name = "partner_id_name")
    private String partnerIdName;

    @KdbColumn(name = "past_due_amt")
    private String pastDueAmt;

    @KdbColumn(name = "past_due_needed")
    private String pastDueNeeded;

    @KdbColumn(name = "payment_amount")
    private String paymentAmount;

    @KdbColumn(name = "payment_successful")
    private String paymentSuccessful;

    @KdbColumn(name = "pending_disconnect")
    private String pendingDisconnect;

    @KdbColumn(name = "pending_disconnect_due_date")
    private LocalDateTime pendingDisconnectDueDate;

    @KdbColumn(name = "port_request")
    private String portRequest;

    @KdbColumn(name = "pos_id")
    private String posId;

    @KdbColumn(name = "quote_number")
    private String quoteNumber;

    @KdbColumn(name = "refuse_credit_check")
    private String refuseCreditCheck;

    @KdbColumn(name = "self_install_capable")
    private String selfInstallCapable;

    @KdbColumn(name = "self_install_selected")
    private String selfInstallSelected;

    @KdbColumn(name = "service_address")
    private String serviceAddress;

    @KdbColumn(name = "service_city")
    private String serviceCity;

    @KdbColumn(name = "service_state")
    private String serviceState;

    @KdbColumn(name = "service_zip")
    private String serviceZip;

    @KdbColumn(name = "ssn_itin_provided")
    private String ssnItinProvided;

    @KdbColumn(name = "subscriptions")
    private String subscriptions;

    @KdbColumn(name = "value_added_services")
    private String valueAddedServices;

    @KdbColumn(name = "vendor_name")
    private String vendorName;

    @KdbColumn(name = "video")
    private String video;

    @KdbColumn(name = "voice")
    private String voice;

    @KdbColumn(name = "voice_add_on")
    private String voiceAddOn;

    @KdbColumn(name = "date_db_changed")
    private LocalDateTime dateDbChanged;

    @KdbColumn(name = "employee_id")
    private String employeeId;

    @KdbColumn(name = "manual_employee_id")
    private String manualEmployeeId;
}
