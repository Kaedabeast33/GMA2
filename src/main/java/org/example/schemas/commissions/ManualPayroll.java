package org.example.schemas.commissions;

import com.google.gson.Gson;
import org.example.Annotations.*;
import org.example.CommonValues.TriggerType;
import org.example.CommonValues.ValueTypes;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@KdbTable(
        description = "tracks manual payroll records including employee, order, and payment details",
        name = "manual_payroll",
        tags = {"manual", "payroll", "employee", "payment"},
        type = ""
)
@Component
public class ManualPayroll {

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

    @KdbColumn(name = "db_insert_date",type= ValueTypes.TIMESTAMP)
    private LocalDateTime dbInsertDate;

    @KdbColumn(name = "employee_id")
    private String employeeId;

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
    private LocalDateTime payDate;

    @KdbColumn(name = "product_type")
    private String productType;

    @KdbColumn(name = "salesperson")
    private String salesperson;

    @KdbColumn(name = "speed_bucket")
    private String speedBucket;

    @KdbColumn(name = "description", type = ValueTypes.TEXT)
    private String description;

    @KdbColumn(name = "mc_account_number")
    private String mcAccountNumber;

    @KdbColumn(name = "mc_order_number")
    private String mcOrderNumber;

    @KdbColumn(name = "mc_mobile_number")
    private String mcMobileNumber;

    @KdbColumn(name = "mc_product_type")
    private String mcProductType;

    @KdbColumn(name = "mc_customer_name")
    private String mcCustomerName;

    @KdbColumn(name = "report")
    private String report;

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

    @KdbTrigger(name = "before_update_manual_payroll", description = "", triggerType = TriggerType.BEFORE_UPDATE, triggerSet = {})
    public static String before_update_manual_payroll(){
        return """
                BEGIN
                    -- 1st block: product type change
                    IF NOT (OLD.product_type <=> NEW.product_type) THEN
                        SET NEW.mo_reconciliation_ref_id = NULL;
                    END IF;
                
                    -- 2nd block: order or account info change
                    IF NOT (OLD.order_number <=> NEW.order_number)
                        OR NOT (OLD.account_number <=> NEW.account_number)
                        OR NOT (OLD.customer_name <=> NEW.customer_name)
                        OR NOT (OLD.mobile_number <=> NEW.mobile_number)
                        OR NOT (OLD.carrier_system <=> NEW.carrier_system)
                    THEN
                        SET NEW.mo_reconciliation_ref_id = NULL;
                        SET NEW.mo_reconciliation_ref_id_2 = NULL;
                        SET NEW.mo_reconciliation_ref_id_3 = NULL;
                        SET NEW.mo_reconciliation_ref_id_4 = NULL;
                        SET NEW.mo_general_ref_id = NULL;
                    END IF;
                
                    -- 3rd block: employee or alignment changes
                    IF NOT (OLD.employee_id <=> NEW.employee_id)
                        OR NOT (OLD.pay_date <=> NEW.pay_date)
                        OR NOT (OLD.seller_ref_id <=> NEW.seller_ref_id)
                        OR NOT (OLD.upline_ref_id <=> NEW.upline_ref_id)
                        OR NOT (OLD.seller_ref_id_level_1 <=> NEW.seller_ref_id_level_1)
                        OR NOT (OLD.upline_ref_id_level_1 <=> NEW.upline_ref_id_level_1)
                        OR NOT (OLD.seller_ref_id_level_2 <=> NEW.seller_ref_id_level_2)
                        OR NOT (OLD.upline_ref_id_level_2 <=> NEW.upline_ref_id_level_2)
                        OR NOT (OLD.seller_ref_id_level_3 <=> NEW.seller_ref_id_level_3)
                        OR NOT (OLD.upline_ref_id_level_3 <=> NEW.upline_ref_id_level_3)
                    THEN
                        SET NEW.seller_ref_id = NULL;
                        SET NEW.upline_ref_id = NULL;
                        SET NEW.seller_ref_id_level_1 = NULL;
                        SET NEW.upline_ref_id_level_1 = NULL;
                        SET NEW.seller_ref_id_level_2 = NULL;
                        SET NEW.upline_ref_id_level_2 = NULL;
                        SET NEW.seller_ref_id_level_3 = NULL;
                        SET NEW.upline_ref_id_level_3 = NULL;
                    END IF;
                
                END
                """;
    }
}
