package org.example.inputs.schemas.orders;

import org.example.bank.Annotations.*;
import org.example.bank.commonValues.TriggerType;

import java.time.LocalDateTime;

@KdbTable(
        description = "Table storing fun hub queue data",
        name = "fun_hub_queue",
        tags = {"funnel", "hub", "queue"},
        type = ""
)
public class FunHubQueue {

    @KdbPrimaryKey
    @KdbColumn(name = "id")
    private Long id;

    @KdbColumn(name = "fun_id", isNullable = false, unique = true)
    private String funId;


    @KdbColumn(name = "product_type", isNullable = false)
    private String productType;


    @KdbColumn(name = "mo_ref_id", isNullable = false)
    private String moRefId;

    @KdbColumn(name = "carrier_system")
    private String carrierSystem;

    @KdbColumn(name = "employee_id")
    private Integer employeeId;

    @KdbColumn(name = "manual_order")
    private Boolean manualOrder;

    @KdbColumn(name = "sync_status")
    private String syncStatus;

    @KdbColumn(name = "mobile_number")
    private String mobileNumber;

    @KdbColumn(name = "db_insert_date")
    private LocalDateTime dbInsertDate;

    @KdbColumn(name = "db_update_date")
    private LocalDateTime dbUpdateDate;

    @KdbIndex(indexGroups = {"recon_id"})
    @KdbColumn(name = "mo_reconciliation_ref_id")
    private String moReconciliationRefId;

    @KdbIndex(indexGroups = {"recon_id_2"})
    @KdbColumn(name = "mo_reconciliation_ref_id_2")
    private String moReconciliationRefId2;



    @KdbTrigger(name = "on_insert_fun_hub_queue", description = "", triggerType = TriggerType.BEFORE_INSERT, triggerSet = {})
    public static String on_insert_fun_hub_queue() {
        return """
                BEGIN
                
                
                       -- Set mo_reconciliation_ref_id
                       SET
                       NEW.db_insert_date = now(),
                      
                       NEW.mo_reconciliation_ref_id =
                           CASE
                               WHEN NEW.carrier_system = 'sara plus'
                                    AND NEW.product_type IN ('internet', 'mobility')
                               THEN
                                   CONCAT(
                                       NULLIF(NEW.mo_ref_id, ''),
                                       IF(NEW.mobile_number IS NULL OR NEW.mobile_number = '', '0000000000', NEW.mobile_number),
                                       NULLIF(NEW.product_type, '')
                                   )
                               ELSE
                                   CONCAT(
                                       NULLIF(NEW.mo_ref_id, ''),      -- You CANNOT reference fhq.*
                                       NULLIF(NEW.product_type, '')    -- Using NEW.* instead
                                   )
                           END;
                
                       -- Set mo_reconciliation_ref_id_2
                       SET NEW.mo_reconciliation_ref_id_2 =
                           CASE
                               WHEN NEW.carrier_system = 'sara plus'
                                    AND NEW.product_type IN ('internet', 'mobility')
                               THEN
                                   CONCAT(
                                       NULLIF(NEW.mo_ref_id, ''),
                                       IF(NEW.mobile_number IS NULL OR NEW.mobile_number = '', '0000000000', NEW.mobile_number)
                                   )
                               ELSE
                                   NULLIF(NEW.mo_ref_id, '')    -- fhq.* removed
                           END;
                
                   END""";
    }

    @KdbTrigger(name = "on_update_fun_hub_queue", description = "", triggerType = TriggerType.BEFORE_UPDATE, triggerSet = {})
    public static String on_update_fun_hub_queue() {
        return """
                BEGIN
                    SET NEW.db_update_date = NOW();
                END""";
    }
}
