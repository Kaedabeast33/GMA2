package org.example.schemas.orders;

import org.example.Annotations.KdbColumn;
import org.example.Annotations.KdbPrimaryKey;
import org.example.Annotations.KdbTable;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@KdbTable(
        description = "Table storing raw Frontier addon records",
        name = "raw_addon_frontier",
        tags = {"orders", "addon", "frontier"},
        type = ""
)
@Component
public class RawAddonFrontier {

    @KdbPrimaryKey
    @KdbColumn(name = "db_id")
    private String dbId;

    @KdbColumn(name = "account_number")
    private String accountNumber;

    @KdbColumn(name = "addon")
    private String addon;

    @KdbColumn(name = "date_db_changed")
    private LocalDateTime dateDbChanged;

    @KdbColumn(name = "mobile_number")
    private String mobileNumber;

    @KdbColumn(name = "order_id")
    private String orderId;

    @KdbColumn(name = "order_number")
    private String orderNumber;

    @KdbColumn(name = "carrier_system")
    private String carrierSystem;
}
