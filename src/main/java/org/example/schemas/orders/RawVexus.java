package org.example.schemas.orders;

import org.example.Annotations.KdbColumn;
import org.example.Annotations.KdbIndex;
import org.example.Annotations.KdbPrimaryKey;
import org.example.Annotations.KdbTable;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@KdbTable(
        description = "Table storing raw Vexus order records",
        name = "raw_vexus",
        tags = {"orders", "vexus", "raw"},
        type = ""
)
@Component
public class RawVexus {

    @KdbPrimaryKey
    @KdbColumn(name = "db_id")
    private String dbId;

    @KdbIndex(indexGroups = {"acct_order_num_idx"},order = {1})
    @KdbColumn(name = "account_number")
    private String accountNumber;

    @KdbColumn(name = "address1")
    private String address1;

    @KdbColumn(name = "address2")
    private String address2;

    @KdbColumn(name = "address3")
    private String address3;

    @KdbColumn(name = "campaigns")
    private String campaigns;

    @KdbColumn(name = "cancel_date")
    private LocalDateTime cancelDate;

    @KdbColumn(name = "cancel_reason")
    private String cancelReason;

    @KdbColumn(name = "co")
    private Integer co;

    @KdbColumn(name = "comp_date")
    private LocalDateTime compDate;

    @KdbColumn(name = "contractor")
    private String contractor;

    @KdbColumn(name = "cred_score")
    private String credScore;

    @KdbColumn(name = "credit_score")
    private String creditScore;

    @KdbColumn(name = "customer_name")
    private String customerName;

    @KdbColumn(name = "customer_status")
    private String customerStatus;

    @KdbIndex(indexGroups = {"date_entered_idx"})
    @KdbColumn(name = "date_entered")
    private LocalDateTime dateEntered;

    @KdbColumn(name = "dv")
    private Integer dv;

    @KdbColumn(name = "email_address")
    private String emailAddress;

    @KdbColumn(name = "frn")
    private Integer frn;

    @KdbColumn(name = "home_phone")
    private String homePhone;

    @KdbIndex(indexGroups = {"house_idx"})
    @KdbColumn(name = "house_record_number")
    private String houseRecordNumber;

    @KdbColumn(name = "internet_speed")
    private String internetSpeed;

    @KdbColumn(name = "market_ready_date")
    private LocalDateTime marketReadyDate;

    @KdbIndex(indexGroups = {"acct_order_num_idx"},order = {2})
    @KdbColumn(name = "order_number")
    private String orderNumber;

    @KdbColumn(name = "order_sales_type")
    private String orderSalesType;

    @KdbIndex(indexGroups = {"order_status_idx"})
    @KdbColumn(name = "order_status")
    private String orderStatus;

    @KdbColumn(name = "other_phone")
    private String otherPhone;

    @KdbColumn(name = "sale_reas")
    private String saleReas;

    @KdbColumn(name = "sales_group")
    private String salesGroup;

    @KdbIndex(indexGroups = {"sales_number_idx"})
    @KdbColumn(name = "sales_number")
    private Integer salesNumber;

    @KdbColumn(name = "sales_reason")
    private String salesReason;

    @KdbColumn(name = "sales_type")
    private String salesType;

    @KdbColumn(name = "salesman_name")
    private String salesmanName;

    @KdbColumn(name = "salesman_type")
    private String salesmanType;

    @KdbColumn(name = "sched_date")
    private LocalDateTime schedDate;

    @KdbColumn(name = "serving_area")
    private String servingArea;

    @KdbColumn(name = "status_date")
    private LocalDateTime statusDate;

    @KdbColumn(name = "work_order_comments")
    private String workOrderComments;

    @KdbColumn(name = "wo_type")
    private String woType;

    @KdbColumn(name = "work_phone")
    private String workPhone;

    @KdbColumn(name = "entity_product")
    private String entityProduct;

    @KdbColumn(name = "order_sales_type_full")
    private String orderSalesTypeFull;

    @KdbColumn(name = "employee_id")
    private String employeeId;

    @KdbColumn(name = "manual_employee_id")
    private String manualEmployeeId;

    @KdbColumn(name = "date_db_changed")
    private LocalDateTime dateDbChanged;
}
