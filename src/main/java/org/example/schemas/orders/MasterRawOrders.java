package org.example.schemas.orders;

import org.example.Annotations.KdbColumn;
import org.example.Annotations.KdbPrimaryKey;
import org.example.Annotations.KdbTable;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@KdbTable(
        description = "Table storing master raw orders",
        name = "master_raw_orders",
        tags = {"orders", "master", "tracking"},
        type = ""
)
@Component
public class MasterRawOrders {

    @KdbPrimaryKey
    @KdbColumn(name = "db_id")
    private String dbId;

    @KdbColumn(name = "employee_id")
    private String employeeId;

    @KdbColumn(name = "ref_login_username_id")
    private String refLoginUsernameId;

    @KdbColumn(name = "order_date")
    private LocalDateTime orderDate;

    @KdbColumn(name = "order_id")
    private String orderId;

    @KdbColumn(name = "order_number")
    private String orderNumber;

    @KdbColumn(name = "account_number")
    private String accountNumber;

    @KdbColumn(name = "mobile_number")
    private String mobileNumber;

    @KdbColumn(name = "customer_name")
    private String customerName;

    @KdbColumn(name = "customer_contact_number")
    private String customerContactNumber;

    @KdbColumn(name = "customer_address_1")
    private String customerAddress1;

    @KdbColumn(name = "customer_address_2")
    private String customerAddress2;

    @KdbColumn(name = "city")
    private String city;

    @KdbColumn(name = "state")
    private String state;

    @KdbColumn(name = "zip_5")
    private String zip5;

    @KdbColumn(name = "status_change_date")
    private LocalDateTime statusChangeDate;

    @KdbColumn(name = "due_date")
    private LocalDateTime dueDate;

    @KdbColumn(name = "purchase_type")
    private String purchaseType;

    @KdbColumn(name = "package_name")
    private String packageName;

    @KdbColumn(name = "order_method")
    private String orderMethod;

    @KdbColumn(name = "empalign_ref_id")
    private String empalignRefId;

    @KdbColumn(name = "markalign_ref_id")
    private String markalignRefId;

    @KdbColumn(name = "regalign_ref_id")
    private String regalignRefId;

    @KdbColumn(name = "divalign_ref_id")
    private String divalignRefId;

    @KdbColumn(name = "mo_reconciliation_ref_id")
    private String moReconciliationRefId;

    @KdbColumn(name = "mo_reconciliation_ref_id_2")
    private String moReconciliationRefId2;

    @KdbColumn(name = "mo_reconciliation_ref_id_3")
    private String moReconciliationRefId3;

    @KdbColumn(name = "mo_reconciliation_ref_id_4")
    private String moReconciliationRefId4;

    @KdbColumn(name = "mo_general_ref_id")
    private String moGeneralRefId;

    @KdbColumn(name = "carrier_system")
    private String carrierSystem;

    @KdbColumn(name = "status")
    private String status;

    @KdbColumn(name = "date_db_insert")
    private LocalDateTime dateDbInsert;

    @KdbColumn(name = "hub_fun_id")
    private String hubFunId;

    @KdbColumn(name = "seller_ref_id")
    private String sellerRefId;

    @KdbColumn(name = "upline_ref_id")
    private String uplineRefId;

    @KdbColumn(name = "seller_ref_id_level_1")
    private String sellerRefIdLevel1;

    @KdbColumn(name = "upline_ref_id_level_1")
    private String uplineRefIdLevel1;

    @KdbColumn(name = "seller_ref_id_level_2")
    private String sellerRefIdLevel2;

    @KdbColumn(name = "upline_ref_id_level_2")
    private String uplineRefIdLevel2;

    @KdbColumn(name = "seller_ref_id_level_3")
    private String sellerRefIdLevel3;

    @KdbColumn(name = "upline_ref_id_level_3")
    private String uplineRefIdLevel3;
}
