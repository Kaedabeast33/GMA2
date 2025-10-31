package org.example.schemas.commissions;

import com.google.gson.Gson;
import org.example.Annotations.*;
import org.example.CommonValues.TriggerType;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@KdbTable(
        description = "stores raw master pay file records including payment, order, and product details",
        name = "raw_master_pay_file",
        tags = {"raw", "master pay file", "payment", "order"},
        type = ""
)
@Component
public class RawMasterPayFile {

    @KdbColumn(name = "db_id")
    @KdbPrimaryKey
    private String dbId;

    @KdbIndex(indexGroups = {"idx_upsert"},order=3)
    @KdbColumn(name = "account_number")
    private String accountNumber;

    @KdbColumn(name = "amount")
    private BigDecimal amount;

    @KdbColumn(name = "carrier")
    private String carrier;


    @KdbIndex(indexGroups = {"idx_upsert"},order=6)
    @KdbColumn(name = "carrier_system")
    private String carrierSystem;

    @KdbColumn(name = "churn_date")
    private LocalDateTime churnDate;

    @KdbColumn(name = "component")
    private String component;

    @KdbColumn(name = "customer_name")
    private String customerName;

    @KdbColumn(name = "date_db_insert")
    private LocalDateTime dateDbInsert;

    @KdbColumn(name = "dealer_code")
    private String dealerCode;

    @KdbColumn(name = "dealer_name")
    private String dealerName;

    @KdbColumn(name = "mo_general_ref_id")
    private String moGeneralRefId;

    @KdbIndex(indexGroups = {"reconciliation_id"})
    @KdbColumn(name = "mo_reconciliation_ref_id")
    private String moReconciliationRefId;

    @KdbIndex(indexGroups = {"reconciliation_id_2"})
    @KdbColumn(name = "mo_reconciliation_ref_id_2")
    private String moReconciliationRefId2;

    @KdbIndex(indexGroups = {"ref_id_3_idx"})
    @KdbColumn(name = "mo_reconciliation_ref_id_3")
    private String moReconciliationRefId3;

    @KdbIndex(indexGroups = {"ref_id_4_idx"})
    @KdbColumn(name = "mo_reconciliation_ref_id_4")
    private String moReconciliationRefId4;

    @KdbIndex(indexGroups = {"idx_upsert"},order=4)
    @KdbColumn(name = "mobile_number")
    private String mobileNumber;

    @KdbColumn(name = "order_date")
    private LocalDateTime orderDate;

    @KdbIndex(indexGroups = {"idx_upsert"},order=1)
    @KdbColumn(name = "order_id")
    private String orderId;
    @KdbIndex(indexGroups = {"idx_upsert"},order=2)
    @KdbColumn(name = "order_number")
    private String orderNumber;

    @KdbColumn(name = "posted_date")
    private LocalDateTime postedDate;

    @KdbColumn(name = "product")
    private String product;

    @KdbIndex(indexGroups = {"idx_upsert"},order=5)
    @KdbColumn(name = "product_type")
    private String productType;

    @KdbColumn(name = "quantity")
    private Long quantity;

    @KdbColumn(name = "statement_date")
    private LocalDateTime statementDate;

    @KdbColumn(name = "file_name")
    private String fileName;

    @KdbColumn(name = "pay_type")
    private String payType;

    @KdbTrigger(name = "before_update_raw_pay", description = "", triggerType = TriggerType.BEFORE_UPDATE, triggerSet = {})
    public static String before_update_raw_pay(){
        return """
                BEGIN
                    -- 1st BLOCK
                   
                    if
                		OLD.product_type != NEW.product_type
                   
                	then
                		set new.mo_reconciliation_ref_id = null;
                	end if;
                    -- 2nd block
                    if
                        OLD.order_number != NEW.order_number OR
                        OLD.account_number != NEW.account_number OR
                        OLD.customer_name != NEW.customer_name OR
                        old.mobile_number != new.mobile_number or
                        OLD.carrier_system != NEW.carrier_system
                    then
                       
                
                        SET NEW.mo_reconciliation_ref_id = null;
                        -- CASE
                --             WHEN NEW.carrier_system IN ('dsi', 'sp internet', 'sp mobility') THEN CONCAT(COALESCE(v_new_account_number, NEW.account_number), NEW.mobile_number, v_product_type)
                --             WHEN NEW.carrier_system IN ('dtv', 'sp video') THEN CONCAT(COALESCE(v_new_account_number, NEW.account_number), v_product_type)
                --             ELSE CONCAT(COALESCE(v_new_order_number, NEW.order_number), v_product_type)
                --         END;
                
                        SET NEW.mo_reconciliation_ref_id_2 = null;
                        -- CASE
                --             WHEN NEW.carrier_system IN ('dsi', 'sp internet', 'sp mobility') THEN CONCAT(COALESCE(v_new_account_number, NEW.account_number), NEW.mobile_number)
                --             WHEN NEW.carrier_system IN ('dtv', 'sp video') THEN COALESCE(v_new_account_number, NEW.account_number)
                --             ELSE COALESCE(v_new_order_number, NEW.order_number)
                --         END;
                
                        SET NEW.mo_reconciliation_ref_id_3 = null;
                        -- CASE
                --             WHEN NEW.carrier_system IN ('dsi', 'sp internet', 'sp mobility', 'dtv', 'sp_video') THEN COALESCE(v_new_order_number, NEW.order_number)
                --             ELSE COALESCE(v_new_account_number, NEW.account_number)
                --         END;
                
                        SET NEW.mo_reconciliation_ref_id_4 = null;
                        -- NEW.customer_name;
                
                        SET NEW.mo_general_ref_id = null;
                        -- CASE
                --             WHEN NEW.carrier_system IN ('dsi', 'sp internet', 'sp mobility', 'dtv', 'sp_video') THEN COALESCE(v_new_account_number, NEW.account_number)
                --             ELSE COALESCE(v_new_order_number, NEW.order_number)
                --         END;
                
                 
                    END IF;
                
                
                END""";
    }
}
