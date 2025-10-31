package org.example.schemas.commissions;

import com.google.gson.Gson;
import org.example.Annotations.KdbColumn;
import org.example.Annotations.KdbIndex;
import org.example.Annotations.KdbPrimaryKey;
import org.example.Annotations.KdbTable;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@KdbTable(
        description = "staging table for MO/MC aggregated payroll and commission data",
        name = "staging_ag_mo_mc",
        tags = {"staging", "mo", "mc", "payroll", "commission"},
        type = ""
)
@Component
public class StagingAgMoMc {

    @KdbColumn(name = "my_row_id")
    @KdbPrimaryKey
    private Long myRowId;

    @KdbIndex(indexGroups = {"idx_mo_db_id"})
    @KdbColumn(name = "mo_db_id")
    private String moDbId;

    @KdbColumn(name = "mo_order_number")
    private String moOrderNumber;

    @KdbColumn(name = "mo_account_number")
    private String moAccountNumber;

    @KdbColumn(name = "mc_db_id")
    private String mcDbId;

    @KdbColumn(name = "max_mc_db_id")
    private String maxMcDbId;

    @KdbColumn(name = "mc_cp_type")
    private String mcCpType;

    @KdbColumn(name = "mc_paid_amount")
    private BigDecimal mcPaidAmount;

    @KdbColumn(name = "mc_c_employee_name")
    private String mcCEmployeeName;

    @KdbColumn(name = "mc_c_employee_id")
    private String mcCEmployeeId;

    @KdbColumn(name = "mc_1p_total")
    private BigDecimal mc1pTotal;

    @KdbColumn(name = "mc_1p_pay_date")
    private LocalDateTime mc1pPayDate;

    @KdbColumn(name = "mc_1c_total")
    private BigDecimal mc1cTotal;

    @KdbColumn(name = "mc_1c_pay_date")
    private LocalDateTime mc1cPayDate;

    @KdbColumn(name = "mc_8p_total")
    private BigDecimal mc8pTotal;

    @KdbColumn(name = "mc_8c_total")
    private BigDecimal mc8cTotal;

    @KdbColumn(name = "mc_2_name")
    private String mc2Name;

    @KdbColumn(name = "mc_2_employee_id")
    private String mc2EmployeeId;

    @KdbColumn(name = "mc_2p_total")
    private BigDecimal mc2pTotal;

    @KdbColumn(name = "mc_2p_pay_date")
    private LocalDateTime mc2pPayDate;

    @KdbColumn(name = "mc_2c_total")
    private BigDecimal mc2cTotal;

    @KdbColumn(name = "mc_2c_pay_date")
    private LocalDateTime mc2cPayDate;

    @KdbColumn(name = "mc_3_employee_name")
    private String mc3EmployeeName;

    @KdbColumn(name = "mc_3_employee_id")
    private String mc3EmployeeId;

    @KdbColumn(name = "mc_3p_total")
    private BigDecimal mc3pTotal;

    @KdbColumn(name = "mc_3p_pay_date")
    private LocalDateTime mc3pPayDate;

    @KdbColumn(name = "mc_3c_total")
    private BigDecimal mc3cTotal;

    @KdbColumn(name = "mc_3c_pay_date")
    private LocalDateTime mc3cPayDate;

    @KdbColumn(name = "mc_4_employee_name")
    private String mc4EmployeeName;

    @KdbColumn(name = "mc_4_employee_id")
    private String mc4EmployeeId;

    @KdbColumn(name = "mc_4p_total")
    private BigDecimal mc4pTotal;

    @KdbColumn(name = "mc_4p_pay_date")
    private LocalDateTime mc4pPayDate;

    @KdbColumn(name = "mc_4c_total")
    private BigDecimal mc4cTotal;

    @KdbColumn(name = "mc_4c_pay_date")
    private LocalDateTime mc4cPayDate;

    @KdbColumn(name = "mc_5_employee_id")
    private String mc5EmployeeId;

    @KdbColumn(name = "mc_5_employee_name")
    private String mc5EmployeeName;

    @KdbColumn(name = "mc_5p_total")
    private BigDecimal mc5pTotal;

    @KdbColumn(name = "mc_5p_pay_date")
    private LocalDateTime mc5pPayDate;

    @KdbColumn(name = "mc_5c_total")
    private BigDecimal mc5cTotal;

    @KdbColumn(name = "mc_5c_pay_date")
    private LocalDateTime mc5cPayDate;

    @KdbColumn(name = "mc_6_employee_name")
    private String mc6EmployeeName;

    @KdbColumn(name = "mc_6_employee_id")
    private String mc6EmployeeId;

    @KdbColumn(name = "mc_6p_total")
    private BigDecimal mc6pTotal;

    @KdbColumn(name = "mc_6p_pay_date")
    private LocalDateTime mc6pPayDate;

    @KdbColumn(name = "mc_6c_total")
    private BigDecimal mc6cTotal;

    @KdbColumn(name = "mc_6c_pay_date")
    private LocalDateTime mc6cPayDate;

    @KdbColumn(name = "mc_7_employee_name")
    private String mc7EmployeeName;

    @KdbColumn(name = "mc_7_employee_id")
    private String mc7EmployeeId;

    @KdbColumn(name = "mc_7p_total")
    private BigDecimal mc7pTotal;

    @KdbColumn(name = "mc_7p_pay_date")
    private LocalDateTime mc7pPayDate;

    @KdbColumn(name = "mc_7c_total")
    private BigDecimal mc7cTotal;

    @KdbColumn(name = "mc_7c_pay_date")
    private LocalDateTime mc7cPayDate;

    @KdbColumn(name = "mc_9_employee_name")
    private String mc9EmployeeName;

    @KdbColumn(name = "mc_9_employee_id")
    private String mc9EmployeeId;

    @KdbColumn(name = "mc_9p_total")
    private BigDecimal mc9pTotal;

    @KdbColumn(name = "mc_9p_pay_date")
    private LocalDateTime mc9pPayDate;

    @KdbColumn(name = "mc_9c_total")
    private BigDecimal mc9cTotal;

    @KdbColumn(name = "mc_9c_pay_date")
    private LocalDateTime mc9cPayDate;

    @KdbColumn(name = "mc_comp_id")
    private String mcCompId;

    @KdbColumn(name = "mc_role_id")
    private String mcRoleId;

    @KdbColumn(name = "mc_team_id")
    private String mcTeamId;

    @KdbColumn(name = "mc_status",isNullable = false)
    private String mcStatus;
}
