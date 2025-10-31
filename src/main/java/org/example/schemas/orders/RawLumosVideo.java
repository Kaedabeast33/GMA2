package org.example.schemas.orders;

import org.example.Annotations.KdbColumn;
import org.example.Annotations.KdbIndex;
import org.example.Annotations.KdbPrimaryKey;
import org.example.Annotations.KdbTable;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@KdbTable(
        description = "Table storing raw Lumos video order records",
        name = "raw_lumos_video",
        tags = {"orders", "lumos", "video", "raw"},
        type = ""
)
@Component
public class RawLumosVideo {

    @KdbPrimaryKey
    @KdbColumn(name = "db_id")
    private String dbId;

    @KdbColumn(name = "wtn")
    private String wtn;

    @KdbColumn(name = "account_number")
    private String accountNumber;

    @KdbColumn(name = "appointment_date")
    private LocalDateTime appointmentDate;

    @KdbColumn(name = "city")
    private String city;

    @KdbColumn(name = "columns")
    private String columns;

    @KdbColumn(name = "create_date")
    private LocalDateTime createDate;

    @KdbColumn(name = "create_time")
    private LocalDateTime createTime;

    @KdbColumn(name = "customer_name")
    private String customerName;

    @KdbColumn(name = "order_number")
    private String orderNumber;

    @KdbColumn(name = "order_status")
    private String orderStatus;

    @KdbColumn(name = "order_type")
    private String orderType;

    @KdbColumn(name = "partner_id")
    private String partnerId;

    @KdbColumn(name = "partner_name")
    private String partnerName;

    @KdbColumn(name = "partner_reference_id")
    private String partnerReferenceId;

    @KdbIndex(indexGroups = {"order_id_idx"})
    @KdbColumn(name = "pops_order_id")
    private String popsOrderId;

    @KdbColumn(name = "port")
    private String port;

    @KdbColumn(name = "sales_code")
    private String salesCode;

    @KdbColumn(name = "sales_person_id")
    private String salesPersonId;

    @KdbColumn(name = "sales_person_name")
    private String salesPersonName;

    @KdbColumn(name = "sales_person_username")
    private String salesPersonUsername;

    @KdbColumn(name = "state")
    private String state;

    @KdbColumn(name = "status_change_date")
    private LocalDateTime statusChangeDate;

    @KdbColumn(name = "street_address")
    private String streetAddress;

    @KdbColumn(name = "subcategory_id")
    private String subcategoryId;

    @KdbColumn(name = "subcategory_name")
    private String subcategoryName;

    @KdbColumn(name = "subcategory_username")
    private String subcategoryUsername;

    @KdbColumn(name = "unit")
    private String unit;

    @KdbColumn(name = "video_product_family")
    private String videoProductFamily;

    @KdbColumn(name = "video_product_name")
    private String videoProductName;

    @KdbColumn(name = "video_quantity")
    private Integer videoQuantity;

    @KdbColumn(name = "zip_code")
    private String zipCode;

    @KdbColumn(name = "latest_insert_date")
    private LocalDateTime latestInsertDate;

    @KdbColumn(name = "employee_id")
    private String employeeId;

    @KdbColumn(name = "manual_employee_id")
    private String manualEmployeeId;
}
