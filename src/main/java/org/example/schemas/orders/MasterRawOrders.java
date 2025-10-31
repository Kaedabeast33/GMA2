package org.example.schemas.orders;

import org.example.Annotations.*;
import org.example.CommonValues.TriggerType;
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

    @KdbIndex(indexGroups = {"idx_employee_id"})
    @KdbColumn(name = "employee_id")
    private String employeeId;

    @KdbColumn(name = "ref_login_username_id")
    private String refLoginUsernameId;

    @KdbIndex(indexGroups = {"idx_order_date"})
    @KdbColumn(name = "order_date")
    private LocalDateTime orderDate;

    @KdbIndex(indexGroups = {"idx_order_id"})
    @KdbColumn(name = "order_id")
    private String orderId;

    @KdbIndex(indexGroups = {"idx_composite"},order = {1})
    @KdbColumn(name = "order_number")
    private String orderNumber;

    @KdbIndex(indexGroups = {"idx_composite"},order = {2})
    @KdbColumn(name = "account_number")
    private String accountNumber;

    @KdbIndex(indexGroups = {"idx_composite"},order = {3})
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

//
//    @KdbColumn(name = "empalign_ref_id")
//    private String empalignRefId;
//
//    @KdbColumn(name = "markalign_ref_id")
//    private String markalignRefId;
//
//    @KdbColumn(name = "regalign_ref_id")
//    private String regalignRefId;
//
//    @KdbColumn(name = "divalign_ref_id")
//    private String divalignRefId;

    @KdbReference(referenceColumns = {}, cascadeRule = "")
    @KdbColumn(name = "mo_reconciliation_ref_id")
    private String moReconciliationRefId;

    @KdbReference(referenceColumns = {}, cascadeRule = "")
    @KdbColumn(name = "mo_reconciliation_ref_id_2")
    private String moReconciliationRefId2;

    @KdbReference(referenceColumns = {}, cascadeRule = "")
    @KdbColumn(name = "mo_reconciliation_ref_id_3")
    private String moReconciliationRefId3;

    @KdbReference(referenceColumns = {}, cascadeRule = "")
    @KdbColumn(name = "mo_reconciliation_ref_id_4")
    private String moReconciliationRefId4;

    @KdbReference(referenceColumns = {}, cascadeRule = "")
    @KdbColumn(name = "mo_general_ref_id")
    private String moGeneralRefId;

    @KdbIndex(indexGroups = {"idx_carrier_system"})
    @KdbColumn(name = "carrier_system")
    private String carrierSystem;

    @KdbColumn(name = "status")
    private String status;

    @KdbColumn(name = "date_db_insert")
    private LocalDateTime dateDbInsert;


    @KdbReference(referenceColumns = {}, cascadeRule = "")
    @KdbIndex(indexGroups = {"idx_fun_id"})
    @KdbColumn(name = "hub_fun_id")
    private String hubFunId;

    @KdbReference(referenceColumns = {}, cascadeRule = "")
    @KdbColumn(name = "seller_ref_id")
    private String sellerRefId;

    @KdbReference(referenceColumns = {}, cascadeRule = "")
    @KdbColumn(name = "upline_ref_id")
    private String uplineRefId;

    @KdbReference(referenceColumns = {}, cascadeRule = "")
    @KdbColumn(name = "seller_ref_id_level_1")
    private String sellerRefIdLevel1;

    @KdbReference(referenceColumns = {}, cascadeRule = "")
    @KdbColumn(name = "upline_ref_id_level_1")
    private String uplineRefIdLevel1;

    @KdbReference(referenceColumns = {}, cascadeRule = "")
    @KdbColumn(name = "seller_ref_id_level_2")
    private String sellerRefIdLevel2;

    @KdbReference(referenceColumns = {}, cascadeRule = "")
    @KdbColumn(name = "upline_ref_id_level_2")
    private String uplineRefIdLevel2;

    @KdbReference(referenceColumns = {}, cascadeRule = "")
    @KdbColumn(name = "seller_ref_id_level_3")
    private String sellerRefIdLevel3;

    @KdbReference(referenceColumns = {}, cascadeRule = "")
    @KdbColumn(name = "upline_ref_id_level_3")
    private String uplineRefIdLevel3;


    @KdbTrigger(name = "before_update_raw_orders", description = "", triggerType = TriggerType.BEFORE_UPDATE, triggerSet = {})
    public static String before_update_raw_orders(){
        return """
                BEGIN
                    -- SECOND BLOCK
                    -- Additional nulling if product class or package changes
                    IF OLD.package_name != NEW.package_name THEN
                        SET NEW.mo_reconciliation_ref_id = NULL;
                    END IF;
                
                    -- FOURTH BLOCK
                    IF
                        OLD.order_number != NEW.order_number OR
                        OLD.account_number != NEW.account_number OR
                        OLD.customer_name != NEW.customer_name OR
                        OLD.mobile_number != NEW.mobile_number OR
                        OLD.carrier_system != NEW.carrier_system OR
                        OLD.ref_login_username_id != NEW.ref_login_username_id
                    THEN
                        SET NEW.mo_reconciliation_ref_id = NULL;
                        SET NEW.mo_reconciliation_ref_id_2 = NULL;
                        SET NEW.mo_reconciliation_ref_id_3 = NULL;
                        SET NEW.mo_reconciliation_ref_id_4 = NULL;
                        SET NEW.mo_general_ref_id = NULL;
                    END IF;
                   \s
                    IF NOT (OLD.employee_id <=> NEW.employee_id)
                       OR NOT (OLD.ref_login_username_id <=> NEW.ref_login_username_id)
                       OR NOT (OLD.order_date <=> NEW.order_date)
                       OR (NEW.seller_ref_id IS NULL AND OLD.seller_ref_id IS NOT NULL)
                       OR (NEW.upline_ref_id IS NULL AND OLD.upline_ref_id IS NOT NULL)
                       OR (NEW.seller_ref_id_level_1 IS NULL AND OLD.seller_ref_id_level_1 IS NOT NULL)
                       OR (NEW.upline_ref_id_level_1 IS NULL AND OLD.upline_ref_id_level_1 IS NOT NULL)
                       OR (NEW.seller_ref_id_level_2 IS NULL AND OLD.seller_ref_id_level_2 IS NOT NULL)
                       OR (NEW.upline_ref_id_level_2 IS NULL AND OLD.upline_ref_id_level_2 IS NOT NULL)
                       OR (NEW.seller_ref_id_level_3 IS NULL AND OLD.seller_ref_id_level_3 IS NOT NULL)
                       OR (NEW.upline_ref_id_level_3 IS NULL AND OLD.upline_ref_id_level_3 IS NOT NULL)\s
                    THEN
                        -- Clear all alignment fields
                        SET NEW.seller_ref_id = NULL;
                        SET NEW.upline_ref_id = NULL;
                        SET NEW.seller_ref_id_level_1 = NULL;
                        SET NEW.upline_ref_id_level_1 = NULL;
                        SET NEW.seller_ref_id_level_2 = NULL;
                        SET NEW.upline_ref_id_level_2 = NULL;
                        SET NEW.seller_ref_id_level_3 = NULL;
                        SET NEW.upline_ref_id_level_3 = NULL;
                    END IF;
                END""";
    }

}
