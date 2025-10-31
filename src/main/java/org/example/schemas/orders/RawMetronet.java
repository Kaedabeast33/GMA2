package org.example.schemas.orders;

import org.example.Annotations.KdbColumn;
import org.example.Annotations.KdbIndex;
import org.example.Annotations.KdbPrimaryKey;
import org.example.Annotations.KdbTable;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@KdbTable(
        description = "Table storing raw Metronet order records",
        name = "raw_metronet",
        tags = {"orders", "metronet", "raw"},
        type = ""
)
@Component
public class RawMetronet {

    @KdbPrimaryKey
    @KdbColumn(name = "db_id")
    private String dbId;

    @KdbIndex(indexGroups = {"username_idx"})
    @KdbColumn(name = "agent")
    private String agent;

    @KdbColumn(name = "company")
    private String company;

    @KdbColumn(name = "install_date_internet")
    private LocalDateTime installDateInternet;

    @KdbColumn(name = "internet_plan")
    private String internetPlan;

    @KdbColumn(name = "lcp")
    private String lcp;

    @KdbIndex(indexGroups = {"sub_id_order_date_idx","create_time_idx"},order = {2,1})
    @KdbColumn(name = "order_date_internet")
    private LocalDateTime orderDateInternet;

    @KdbColumn(name = "order_date_phone")
    private LocalDateTime orderDatePhone;

    @KdbColumn(name = "report_month")
    private String reportMonth;

    @KdbColumn(name = "state")
    private String state;

    @KdbIndex(indexGroups = {"sub_id_order_date_idx"},order = {1})
    @KdbColumn(name = "sub_id")
    private String subId;

    @KdbColumn(name = "sub_name")
    private String subName;

    @KdbColumn(name = "date_db_changed")
    private LocalDateTime dateDbChanged;

    @KdbIndex(indexGroups = {"order_status_idx"})
    @KdbColumn(name = "metronet_status")
    private String metronetStatus;

    @KdbColumn(name = "employee_id")
    private String employeeId;

    @KdbColumn(name = "manual_employee_id")
    private String manualEmployeeId;
}
