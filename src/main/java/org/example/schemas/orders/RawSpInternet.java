package org.example.schemas.orders;

import org.example.Annotations.KdbColumn;
import org.example.Annotations.KdbPrimaryKey;
import org.example.Annotations.KdbTable;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.LocalTime;

@KdbTable(
        description = "Table storing raw SP Internet order records",
        name = "raw_sp_internet",
        tags = {"orders", "sp_internet", "raw"},
        type = ""
)
@Component
public class RawSpInternet {

    @KdbPrimaryKey
    @KdbColumn(name = "db_id")
    private String dbId;

    @KdbColumn(name = "apt_number")
    private String aptNumber;

    @KdbColumn(name = "campaign")
    private String campaign;

    @KdbColumn(name = "city")
    private String city;

    @KdbColumn(name = "columns")
    private String columns;

    @KdbColumn(name = "customer_name")
    private String customerName;

    @KdbColumn(name = "dealer_code_internet")
    private String dealerCodeInternet;

    @KdbColumn(name = "email")
    private String email;

    @KdbColumn(name = "employee_id")
    private String employeeId;

    @KdbColumn(name = "internet_account_number")
    private String internetAccountNumber;

    @KdbColumn(name = "internet_cancel_date")
    private LocalDateTime internetCancelDate;

    @KdbColumn(name = "internet_cancel_reason")
    private String internetCancelReason;

    @KdbColumn(name = "internet_highest_package_available")
    private String internetHighestPackageAvailable;

    @KdbColumn(name = "internet_install_date")
    private LocalDateTime internetInstallDate;

    @KdbColumn(name = "internet_order_number")
    private String internetOrderNumber;

    @KdbColumn(name = "internet_order_type")
    private String internetOrderType;

    @KdbColumn(name = "internet_package")
    private String internetPackage;

    @KdbColumn(name = "internet_provider")
    private String internetProvider;

    @KdbColumn(name = "internet_status")
    private String internetStatus;

    @KdbColumn(name = "order_date")
    private LocalDateTime orderDate;

    @KdbColumn(name = "order_id")
    private String orderId;

    @KdbColumn(name = "order_time")
    private LocalTime orderTime;

    @KdbColumn(name = "phone")
    private String phone;

    @KdbColumn(name = "sales_person")
    private String salesPerson;

    @KdbColumn(name = "speed")
    private String speed;

    @KdbColumn(name = "state")
    private String state;

    @KdbColumn(name = "street_address")
    private String streetAddress;

    @KdbColumn(name = "zip")
    private String zip;

    @KdbColumn(name = "latest_insert_date")
    private LocalDateTime latestInsertDate;

    @KdbColumn(name = "manual_employee_id")
    private String manualEmployeeId;
}
