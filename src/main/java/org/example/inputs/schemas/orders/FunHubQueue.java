package org.example.inputs.schemas.orders;

import org.example.bank.commonValues.TriggerType;
import org.example.bank.Annotations.*;

import java.time.LocalDateTime;

@KdbTable(
        description = "Table storing fun hub queue data",
        name = "fun_hub_queue",
        tags = {"fun", "hub", "queue"},
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

    @KdbTrigger(name = "on_insert_fun_hub_queue", description = "", triggerType = TriggerType.BEFORE_INSERT, triggerSet = {})
    public static String on_insert_fun_hub_queue() {
        return """
                BEGIN
                    SET NEW.db_insert_date = NOW();
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
