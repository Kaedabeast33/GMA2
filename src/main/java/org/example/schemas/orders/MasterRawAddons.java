package org.example.schemas.orders;

import org.example.Annotations.KdbColumn;
import org.example.Annotations.KdbPrimaryKey;
import org.example.Annotations.KdbTable;
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
}
