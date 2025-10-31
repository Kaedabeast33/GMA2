package org.example.schemas.orders;

import org.example.Annotations.KdbColumn;
import org.example.Annotations.KdbIndex;
import org.example.Annotations.KdbPrimaryKey;
import org.example.Annotations.KdbTable;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.LocalTime;

@KdbTable(
        description = "Table storing raw SP Mobility order records",
        name = "raw_sp_mobility",
        tags = {"orders", "sp_mobility", "raw"},
        type = ""
)
@Component
public class RawSpMobility {

    @KdbPrimaryKey
    @KdbColumn(name = "db_id")
    private String dbId;

    @KdbColumn(name = "account_number")
    private String accountNumber;

    @KdbColumn(name = "active_date")
    private LocalDateTime activeDate;

    @KdbColumn(name = "apt_number")
    private String aptNumber;

    @KdbColumn(name = "city")
    private String city;

    @KdbColumn(name = "columns")
    private String columns;

    @KdbColumn(name = "customer_name")
    private String customerName;

    @KdbColumn(name = "dealer_code")
    private String dealerCode;

    @KdbColumn(name = "email")
    private String email;

    @KdbColumn(name = "employee_id")
    private String employeeId;

    @KdbIndex(indexGroups = {"order_id_idx"},order = {3})
    @KdbColumn(name = "line")
    private String line;

    @KdbColumn(name = "line_number")
    private String lineNumber;

    @KdbColumn(name = "line_provider")
    private String lineProvider;

    @KdbIndex(indexGroups = {"order_status_idx"})
    @KdbColumn(name = "line_status")
    private String lineStatus;

    @KdbColumn(name = "line_type")
    private String lineType;

    @KdbIndex(indexGroups = {"create_time_idx"})
    @KdbColumn(name = "order_date")
    private LocalDateTime orderDate;

    @KdbIndex(indexGroups = {"order_id_idx"},order = {1})
    @KdbColumn(name = "order_number")
    private String orderNumber;

    @KdbColumn(name = "order_time")
    private LocalTime orderTime;

    @KdbColumn(name = "phone")
    private String phone;

    @KdbColumn(name = "plan")
    private String plan;

    @KdbColumn(name = "sales_person")
    private String salesPerson;

    @KdbColumn(name = "state")
    private String state;

    @KdbColumn(name = "street_address")
    private String streetAddress;

    @KdbIndex(indexGroups = {"order_id_idx"},order = {2})
    @KdbColumn(name = "wireless_order_number")
    private String wirelessOrderNumber;

    @KdbColumn(name = "wireless_status")
    private String wirelessStatus;

    @KdbColumn(name = "zip")
    private String zip;

    @KdbColumn(name = "latest_insert_date")
    private LocalDateTime latestInsertDate;

    @KdbColumn(name = "manual_employee_id")
    private String manualEmployeeId;
}
