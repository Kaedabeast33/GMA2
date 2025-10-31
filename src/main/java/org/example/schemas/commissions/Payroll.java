package org.example.schemas.commissions;

import com.google.gson.Gson;
import org.example.Annotations.*;
import org.example.CommonValues.ValueTypes;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@KdbTable(
        description = "tracks payroll records including employee, order, and payment details",
        name = "payroll",
        tags = {"payroll", "employee", "payment", "order"},
        type = ""
)
@Component
public class Payroll {

    @KdbColumn(name = "db_id")
    @KdbPrimaryKey
    private String dbId;

    @KdbColumn(name = "account_number")
    private String accountNumber;

    @KdbColumn(name = "carrier_system")
    private String carrierSystem;

    @KdbColumn(name = "commission_type")
    private String commissionType;

    @KdbColumn(name = "customer_name")
    private String customerName;

    @KdbColumn(name = "db_insert_date",type = ValueTypes.TIMESTAMP)
    private Timestamp dbInsertDate;

    @KdbColumn(name = "employee_id")
    private String employeeId;

    @KdbIndex(indexGroups = {"mo_db_id_idx"})
    @KdbColumn(name = "mo_db_id")
    private String moDbId;

    @KdbColumn(name = "mo_general_ref_id")
    private String moGeneralRefId;

    @KdbColumn(name = "mo_reconciliation_ref_id")
    private String moReconciliationRefId;

    @KdbColumn(name = "mo_reconciliation_ref_id_2")
    private String moReconciliationRefId2;

    @KdbColumn(name = "mo_reconciliation_ref_id_3")
    private String moReconciliationRefId3;

    @KdbColumn(name = "mo_reconciliation_ref_id_4")
    private String moReconciliationRefId4;

    @KdbColumn(name = "mobile_number")
    private String mobileNumber;

    @KdbColumn(name = "purchase_type")
    private String purchaseType;

    @KdbColumn(name = "order_number")
    private String orderNumber;

    @KdbColumn(name = "paid_amount")
    private BigDecimal paidAmount;

    @KdbColumn(name = "pay_date",type = ValueTypes.TIMESTAMP)
    private Timestamp payDate;

    @KdbColumn(name = "product_type")
    private String productType;

    @KdbColumn(name = "salesperson")
    private String salesperson;

    @KdbColumn(name = "speed_bucket")
    private String speedBucket;

    @KdbColumn(name = "description")
    private String description;

    @KdbColumn(name = "carrier")
    private String carrier;

    @KdbColumn(name = "mc_account_number",type= ValueTypes.VARCHAR50)
    private String mcAccountNumber;

    @KdbColumn(name = "mc_mobile_number")
    private String mcMobileNumber;

    @KdbColumn(name = "mc_order_number")
    private String mcOrderNumber;

    @KdbColumn(name = "mc_customer_name")
    private String mcCustomerName;

    @KdbColumn(name = "mc_product_type")
    private String mcProductType;


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

    @KdbReference(referenceColumns = {}, cascadeRule = "")
    @KdbColumn(name = "report")
    private String report;


    @KdbTrigger(name = "before_update_raw_com", description = "", triggerType = "BEFORE UPDATE", triggerSet = {})
    public static String before_update_raw_com(){
        return "BEGIN\n    -- 1st block: product type change\n    IF NOT (OLD.product_type \u003c\u003d\u003e NEW.product_type) THEN\n        SET NEW.mo_reconciliation_ref_id \u003d NULL;\n    END IF;\n\n    -- 2nd block: order or account info change\n    IF NOT (OLD.order_number \u003c\u003d\u003e NEW.order_number)\n        OR NOT (OLD.account_number \u003c\u003d\u003e NEW.account_number)\n        OR NOT (OLD.customer_name \u003c\u003d\u003e NEW.customer_name)\n        OR NOT (OLD.mobile_number \u003c\u003d\u003e NEW.mobile_number)\n        OR NOT (OLD.carrier_system \u003c\u003d\u003e NEW.carrier_system)\n    THEN\n        SET NEW.mo_reconciliation_ref_id \u003d NULL;\n        SET NEW.mo_reconciliation_ref_id_2 \u003d NULL;\n        SET NEW.mo_reconciliation_ref_id_3 \u003d NULL;\n        SET NEW.mo_reconciliation_ref_id_4 \u003d NULL;\n        SET NEW.mo_general_ref_id \u003d NULL;\n    END IF;\n\n    -- 3rd block: employee or alignment changes\n    IF NOT (OLD.employee_id \u003c\u003d\u003e NEW.employee_id)\n        OR NOT (OLD.pay_date \u003c\u003d\u003e NEW.pay_date)\n        OR (NEW.seller_ref_id is null and old.seller_ref_id is not null)\n        OR (NEW.upline_ref_id is null and old.upline_ref_id is not null)\n        OR (NEW.seller_ref_id_level_1 is null and old.seller_ref_id_level_1 is not null)\n        OR (NEW.upline_ref_id_level_1 is null and old.upline_ref_id_level_1 is not null)\n        OR (NEW.seller_ref_id_level_2 is null and old.seller_ref_id_level_2 is not null)\n        OR (NEW.upline_ref_id_level_2 is null and old.upline_ref_id_level_2 is not null)\n        OR (NEW.seller_ref_id_level_3 is null and old.seller_ref_id_level_3 is not null)\n        OR (NEW.upline_ref_id_level_3 is null and old.upline_ref_id_level_3 is not null)\n    THEN\n        SET NEW.seller_ref_id \u003d NULL;\n        SET NEW.upline_ref_id \u003d NULL;\n        SET NEW.seller_ref_id_level_1 \u003d NULL;\n        SET NEW.upline_ref_id_level_1 \u003d NULL;\n        SET NEW.seller_ref_id_level_2 \u003d NULL;\n        SET NEW.upline_ref_id_level_2 \u003d NULL;\n        SET NEW.seller_ref_id_level_3 \u003d NULL;\n        SET NEW.upline_ref_id_level_3 \u003d NULL;\n    END IF;\n\nEND";
    };
}
