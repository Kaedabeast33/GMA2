package org.example.schemas.orders;

import org.example.Annotations.KdbColumn;
import org.example.Annotations.KdbPrimaryKey;
import org.example.Annotations.KdbTable;
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

    @KdbColumn(name = "fun_id")
    private String funId;

    @KdbColumn(name = "product_type")
    private String productType;

    @KdbColumn(name = "mo_ref_id")
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
}
