package org.example.schemas.orders;

import org.example.Annotations.KdbColumn;
import org.example.Annotations.KdbPrimaryKey;
import org.example.Annotations.KdbTable;
import org.example.Annotations.KdbTrigger;
import org.example.CommonValues.TriggerType;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@KdbTable(
        description = "Table storing fun hub queue data",
        name = "fun_hub_queue",
        tags = {"fun", "hub", "queue"},
        type = ""
)
@Component
public class FunHubQueue {

    @KdbPrimaryKey
    @KdbColumn(name = "id")
    private Long id;

    @KdbColumn(name = "fun_id",isNullable = false,unique = true)
    private String funId;


    @KdbColumn(name = "product_type",isNullable = false)
    private String productType;


    @KdbColumn(name = "mo_ref_id",isNullable = false)
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
    public static String on_insert_fun_hub_queue(){
        return """
                BEGIN
                    SET NEW.db_insert_date = NOW();
                END""";
    }

    @KdbTrigger(name = "on_update_fun_hub_queue", description = "", triggerType = TriggerType.BEFORE_UPDATE, triggerSet = {})
    public static String on_update_fun_hub_queue(){
        return """
                BEGIN
                    SET NEW.db_update_date = NOW();
                END""";
    }
}
