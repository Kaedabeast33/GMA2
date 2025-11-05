package org.example.inputs.schemas.orders;

import org.example.bank.Annotations.*;

import java.time.LocalDateTime;

@KdbTable(
        description = "Table storing raw Metronet addon records",
        name = "raw_addon_metronet",
        tags = {"orders", "addon", "metronet"},
        type = ""
)
public class RawAddonMetronet {

    @KdbPrimaryKey
    @KdbColumn(name = "db_id")
    private String dbId;

    @KdbColumn(name = "account_number")
    private String accountNumber;

    @KdbColumn(name = "addon")
    private String addon;

    @KdbColumn(name = "carrier_system")
    private String carrierSystem;

    @KdbColumn(name = "date_db_changed")
    private LocalDateTime dateDbChanged;

    @KdbColumn(name = "mobile_number")
    private String mobileNumber;

    @KdbColumn(name = "order_id")
    private String orderId;

    @KdbColumn(name = "order_number")
    private String orderNumber;
}
