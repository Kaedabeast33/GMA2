package org.example.schemas.productalignment;

import org.example.Annotations.KdbColumn;
import org.example.Annotations.KdbIndex;
import org.example.Annotations.KdbPrimaryKey;
import org.example.Annotations.KdbTable;
import org.springframework.stereotype.Component;

@KdbTable(
        description = "tracks shell order details including customer info, package, and addons",
        name = "shell_orders",
        tags = {"orders", "shell", "customer", "package", "addon"},
        type = ""
)
@Component
public class ShellOrders {

    @KdbColumn(name = "db_id")
    @KdbPrimaryKey
    private String dbId;

    @KdbIndex(indexGroups = {"idx_acc"})
    @KdbColumn(name = "account_number")
    private String accountNumber;

    @KdbColumn(name = "carrier_system")
    @KdbIndex(indexGroups = {"idx_car"})
    private String carrierSystem;

    @KdbColumn(name = "city")
    private String city;

    @KdbColumn(name = "contractor_id")
    private String contractorId;

    @KdbColumn(name = "customer_address")
    private String customerAddress;

    @KdbColumn(name = "customer_name")
    private String customerName;

    @KdbIndex(indexGroups = {"idx_mob"})
    @KdbColumn(name = "mobile_account_number")
    private String mobileAccountNumber;

    @KdbColumn(name = "order_id")
    private String orderId;

    @KdbIndex(indexGroups = {"idx_order"})
    @KdbColumn(name = "order_number")
    private String orderNumber;

    @KdbColumn(name = "package_name")
    private String packageName;

    @KdbColumn(name = "state")
    private String state;

    @KdbColumn(name = "due_date")
    private String dueDate;

    @KdbColumn(name = "zip")
    private String zip;

    @KdbColumn(name = "record_id")
    private String recordId;

    @KdbColumn(name = "order_date")
    private String orderDate;

    @KdbColumn(name = "addon_included")
    private String addonIncluded;

    @KdbColumn(name = "new_upgrade")
    private String newUpgrade;
}
