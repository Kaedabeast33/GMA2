package org.example.schemas.orders;

import org.example.Annotations.KdbColumn;
import org.example.Annotations.KdbPrimaryKey;
import org.example.Annotations.KdbTable;
import org.example.Annotations.KdbTrigger;
import org.example.CommonValues.TriggerType;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@KdbTable(
        description = "Table storing master raw addons",
        name = "master_raw_addons",
        tags = {"addons", "master", "tracking"},
        type = ""
)
@Component
public class MasterRawAddons {

    @KdbPrimaryKey
    @KdbColumn(name = "db_id")
    private String dbId;

    @KdbColumn(name = "account_number")
    private String accountNumber;

    @KdbColumn(name = "order_number")
    private String orderNumber;

    @KdbColumn(name = "mobile_number")
    private String mobileNumber;

    @KdbColumn(name = "addon")
    private String addon;

    @KdbColumn(name = "order_id")
    private String orderId;

    @KdbColumn(name = "carrier_system")
    private String carrierSystem;

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

    @KdbColumn(name = "date_db_changed")
    private LocalDateTime dateDbChanged;

    @KdbColumn(name = "order_method")
    private String orderMethod;

    @KdbTrigger(name = "before_update_trg", description = "", triggerType = TriggerType.BEFORE_UPDATE, triggerSet = {})
    public static String before_update_trg(){
        return """
                BEGIN
                    IF (OLD.order_number != NEW.order_number\s
                        OR OLD.account_number != NEW.account_number\s
                        OR OLD.mobile_number != NEW.mobile_number) THEN
                       \s
                        -- Set these columns to NULL if any of the conditions are true
                        SET NEW.mo_reconciliation_ref_id = NULL;
                        SET NEW.mo_reconciliation_ref_id_2 = NULL;
                        SET NEW.mo_reconciliation_ref_id_3 = NULL;
                        SET NEW.mo_reconciliation_ref_id_4 = NULL;
                        SET NEW.mo_general_ref_id = NULL;
                    END IF;
                END""";
    }

    @KdbTrigger(name = "before_update_raw_addon", description = "", triggerType = TriggerType.BEFORE_UPDATE, triggerSet = {})
    public static String before_update_raw_addon(){
        return """
    BEGIN
    -- 1st BLOCK
    
    
        
	
    -- 2nd block
    if
        OLD.order_number != NEW.order_number OR
        OLD.account_number != NEW.account_number OR
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


END
                """;
    }
}
