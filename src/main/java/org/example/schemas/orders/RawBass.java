package org.example.schemas.orders;

import org.example.Annotations.KdbColumn;
import org.example.Annotations.KdbIndex;
import org.example.Annotations.KdbPrimaryKey;
import org.example.Annotations.KdbTable;
import org.example.CommonValues.ValueTypes;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@KdbTable(
        description = "Table storing raw BASS order records",
        name = "raw_bass",
        tags = {"orders", "bass", "raw"},
        type = ""
)
@Component
public class RawBass {

    @KdbColumn(name = "account_number")
    private String accountNumber;

    @KdbIndex(indexGroups = {"order_id_idx"})
    @KdbColumn(name = "bass_order_id")
    private String bassOrderId;

    @KdbColumn(name = "billing_number")
    private String billingNumber;

    @KdbColumn(name = "city")
    private String city;

    @KdbIndex(indexGroups = {"create_time_idx"})
    @KdbColumn(name = "create_time")
    private LocalDateTime createTime;

    @KdbColumn(name = "customer_name")
    private String customerName;

    @KdbColumn(name = "customer_type",type = ValueTypes.TEXT)
    private String customerType;

    @KdbColumn(name = "due_date")
    private LocalDateTime dueDate;

    @KdbColumn(name = "internet_action_code")
    private String internetActionCode;

    @KdbColumn(name = "internet_product_family")
    private String internetProductFamily;

    @KdbColumn(name = "internet_product_name")
    private String internetProductName;

    @KdbColumn(name = "internet_product_status")
    private String internetProductStatus;

    @KdbColumn(name = "internet_quantity")
    private String internetQuantity;

    @KdbColumn(name = "internet_usoc",type = ValueTypes.TEXT)
    private String internetUsoc;

    @KdbColumn(name = "master_agent_id",type = ValueTypes.TEXT)
    private String masterAgentId;

    @KdbColumn(name = "master_agent_name",type = ValueTypes.TEXT)
    private String masterAgentName;

    @KdbColumn(name = "order_number")
    private String orderNumber;

    @KdbIndex(indexGroups = {"order_status_idx"})
    @KdbColumn(name = "order_status")
    private String orderStatus;

    @KdbColumn(name = "order_type")
    private String orderType;

    @KdbColumn(name = "outside_sales_rep")
    private String outsideSalesRep;

    @KdbColumn(name = "partner_reference_id",type = ValueTypes.TEXT)
    private String partnerReferenceId;

    @KdbColumn(name = "regional_sales_manager")
    private String regionalSalesManager;

    @KdbColumn(name = "sales_code")
    private String salesCode;

    @KdbColumn(name = "sales_person_id")
    private String salesPersonId;

    @KdbColumn(name = "sales_person_name")
    private String salesPersonName;

    @KdbIndex(indexGroups = {"username_idx"})
    @KdbColumn(name = "sales_persons_username")
    private String salesPersonsUsername;

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

    @KdbColumn(name = "subagent_id",type = ValueTypes.TEXT)
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

    @KdbColumn(name = "dsl_migrator")
    private String dslMigrator;

    @KdbColumn(name = "employee_id")
    private String employeeId;

    @KdbColumn(name = "manual_employee_id")
    private String manualEmployeeId;
}
