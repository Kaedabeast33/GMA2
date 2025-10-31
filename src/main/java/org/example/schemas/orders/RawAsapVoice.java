package org.example.schemas.orders;

import org.example.Annotations.KdbColumn;
import org.example.Annotations.KdbPrimaryKey;
import org.example.Annotations.KdbTable;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@KdbTable(
        description = "Table storing raw ASAP voice order records",
        name = "raw_asap_voice",
        tags = {"orders", "asap", "voice", "raw"},
        type = ""
)
@Component
public class RawAsapVoice {

    @KdbColumn(name = "account_number")
    private String accountNumber;

    @KdbColumn(name = "asap_order_id")
    private String asapOrderId;

    @KdbColumn(name = "billing_number")
    private String billingNumber;

    @KdbColumn(name = "business_type")
    private String businessType;

    @KdbColumn(name = "city")
    private String city;

    @KdbColumn(name = "create_time")
    private LocalDateTime createTime;

    @KdbColumn(name = "customer_name")
    private String customerName;

    @KdbColumn(name = "customer_type")
    private String customerType;

    @KdbColumn(name = "due_date")
    private LocalDateTime dueDate;

    @KdbColumn(name = "local_product_family")
    private String localProductFamily;

    @KdbColumn(name = "local_product_name")
    private String localProductName;

    @KdbColumn(name = "local_product_status")
    private String localProductStatus;

    @KdbColumn(name = "local_quantity")
    private Double localQuantity;

    @KdbColumn(name = "master_agent_id")
    private String masterAgentId;

    @KdbColumn(name = "master_agent_name")
    private String masterAgentName;

    @KdbColumn(name = "order_number")
    private String orderNumber;

    @KdbColumn(name = "order_reference_id")
    private String orderReferenceId;

    @KdbColumn(name = "order_status")
    private String orderStatus;

    @KdbColumn(name = "order_type")
    private String orderType;

    @KdbColumn(name = "outside_sales_rep")
    private String outsideSalesRep;

    @KdbColumn(name = "partner_reference_id")
    private String partnerReferenceId;

    @KdbColumn(name = "regional_sales_manager")
    private String regionalSalesManager;

    @KdbColumn(name = "sales_code")
    private String salesCode;

    @KdbColumn(name = "sales_person_id")
    private String salesPersonId;

    @KdbColumn(name = "sales_person_name")
    private String salesPersonName;

    @KdbColumn(name = "sales_person_username")
    private String salesPersonUsername;

    @KdbColumn(name = "state")
    private String state;

    @KdbColumn(name = "status_change_date")
    private LocalDateTime statusChangeDate;

    @KdbColumn(name = "street_address")
    private String streetAddress;

    @KdbColumn(name = "sub_agent_name")
    private String subAgentName;

    @KdbColumn(name = "sub_agent_username")
    private String subAgentUsername;

    @KdbColumn(name = "subagent_id")
    private String subAgentId;

    @KdbColumn(name = "type_of_sale")
    private String typeOfSale;

    @KdbColumn(name = "unit")
    private String unit;

    @KdbColumn(name = "wtn")
    private String wtn;

    @KdbColumn(name = "zip_code")
    private String zipCode;

    @KdbColumn(name = "latest_insert_date")
    private LocalDateTime latestInsertDate;

    @KdbPrimaryKey
    @KdbColumn(name = "db_id")
    private String dbId;

    @KdbColumn(name = "employee_id")
    private String employeeId;

    @KdbColumn(name = "manual_employee_id")
    private String manualEmployeeId;
}
