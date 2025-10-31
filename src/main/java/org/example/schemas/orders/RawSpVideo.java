package org.example.schemas.orders;

import org.example.Annotations.KdbColumn;
import org.example.Annotations.KdbPrimaryKey;
import org.example.Annotations.KdbTable;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.LocalTime;

@KdbTable(
        description = "Table storing raw SP Video order records",
        name = "raw_sp_video",
        tags = {"orders", "sp_video", "raw"},
        type = ""
)
@Component
public class RawSpVideo {

    @KdbPrimaryKey
    @KdbColumn(name = "db_id")
    private String dbId;

    @KdbColumn(name = "abp")
    private String abp;

    @KdbColumn(name = "apt_number")
    private String aptNumber;

    @KdbColumn(name = "atv_device_count")
    private String atvDeviceCount;

    @KdbColumn(name = "city")
    private String city;

    @KdbColumn(name = "columns")
    private String columns;

    @KdbColumn(name = "customer_name")
    private String customerName;

    @KdbColumn(name = "dealer_code_video")
    private String dealerCodeVideo;

    @KdbColumn(name = "email")
    private String email;

    @KdbColumn(name = "employee_id")
    private String employeeId;

    @KdbColumn(name = "order_date")
    private LocalDateTime orderDate;

    @KdbColumn(name = "order_id")
    private String orderId;

    @KdbColumn(name = "order_time")
    private LocalTime orderTime;

    @KdbColumn(name = "order_type")
    private String orderType;

    @KdbColumn(name = "phone")
    private String phone;

    @KdbColumn(name = "sales_person")
    private String salesPerson;

    @KdbColumn(name = "state")
    private String state;

    @KdbColumn(name = "street_address")
    private String streetAddress;

    @KdbColumn(name = "video_account_number")
    private String videoAccountNumber;

    @KdbColumn(name = "video_cancel_date")
    private LocalDateTime videoCancelDate;

    @KdbColumn(name = "video_cancel_reason")
    private String videoCancelReason;

    @KdbColumn(name = "video_install_date")
    private LocalDateTime videoInstallDate;

    @KdbColumn(name = "video_order_number")
    private String videoOrderNumber;

    @KdbColumn(name = "video_package")
    private String videoPackage;

    @KdbColumn(name = "video_provider")
    private String videoProvider;

    @KdbColumn(name = "video_status")
    private String videoStatus;

    @KdbColumn(name = "zip")
    private String zip;

    @KdbColumn(name = "latest_insert_date")
    private LocalDateTime latestInsertDate;

    @KdbColumn(name = "manual_employee_id")
    private String manualEmployeeId;
}
