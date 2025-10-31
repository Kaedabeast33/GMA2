package org.example.schemas.commissions;

import com.google.gson.Gson;
import org.example.Annotations.KdbColumn;
import org.example.Annotations.KdbIndex;
import org.example.Annotations.KdbPrimaryKey;
import org.example.Annotations.KdbTable;
import org.example.CommonValues.ValueTypes;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@KdbTable(
        description = "tracks matches between MO and MC records including payroll, team, role, and commission info",
        name = "mo_mc_match",
        tags = {"mo", "mc", "match", "payroll", "team", "role"},
        type = ""
)
@Component
public class MoMcMatch {

    @KdbColumn(name = "my_row_id")
    @KdbPrimaryKey
    private Long myRowId;

    @KdbIndex(indexGroups = {"idx_mo_mc_db_id"})
    @KdbColumn(name = "mo_db_id")
    private String moDbId;

    @KdbColumn(name = "mc_db_id")
    private String mcDbId;

    @KdbColumn(name = "mc_commission_type")
    private String mcCommissionType;

    @KdbColumn(name = "mc_payroll_group",isNullable = true)
    private String mcPayrollGroup;

    @KdbColumn(name = "mc_payroll_action")
    private String mcPayrollAction;

    @KdbColumn(name = "mc_payroll_group_reference")
    private Integer mcPayrollGroupReference;

    @KdbColumn(name = "mc_payroll_group_sequence")
    private Integer mcPayrollGroupSequence;

    @KdbColumn(name = "mc_paid_amount")
    private BigDecimal mcPaidAmount;

    @KdbColumn(name = "mc_pay_date", type = ValueTypes.TIMESTAMP)
    private LocalDateTime mcPayDate;

    @KdbColumn(name = "mc_name")
    private String mcName;

    @KdbColumn(name = "mc_order_number")
    private String mcOrderNumber;

    @KdbColumn(name = "mc_account_number")
    private String mcAccountNumber;

    @KdbColumn(name = "mc_carrier_system")
    private String mcCarrierSystem;

    @KdbColumn(name = "mc_mobile_number")
    private String mcMobileNumber;

    @KdbColumn(name = "mc_description",type =ValueTypes.MEDIUMTEXT)
    private String mcDescription;

    @KdbColumn(name = "mc_employee_id")
    private String mcEmployeeId;

    @KdbColumn(name = "report")
    private String report;

    @KdbColumn(name = "mo_mobile_number")
    private String moMobileNumber;

    @KdbColumn(name = "mo_employee_id")
    private String moEmployeeId;

    @KdbColumn(name = "mo_salesperson")
    private String moSalesperson;

    @KdbColumn(name = "original_team_id")
    private String originalTeamId;

    @KdbColumn(name = "original_team_name")
    private String originalTeamName;

    @KdbColumn(name = "original_team_id_owner")
    private String originalTeamIdOwner;

    @KdbColumn(name = "original_team_owner_name")
    private String originalTeamOwnerName;

    @KdbColumn(name = "team_id_level_1")
    private String teamIdLevel1;

    @KdbColumn(name = "team_name_level_1")
    private String teamNameLevel1;

    @KdbColumn(name = "team_id_owner_level_1")
    private String teamIdOwnerLevel1;

    @KdbColumn(name = "owner_name_level_1")
    private String ownerNameLevel1;

    @KdbColumn(name = "role_name_level_1")
    private String roleNameLevel1;

    @KdbColumn(name = "comp_name_level_1")
    private String compNameLevel1;

    @KdbColumn(name = "team_id_level_2")
    private String teamIdLevel2;

    @KdbColumn(name = "team_name_level_2")
    private String teamNameLevel2;

    @KdbColumn(name = "team_id_owner_level_2")
    private String teamIdOwnerLevel2;

    @KdbColumn(name = "owner_name_level_2")
    private String ownerNameLevel2;

    @KdbColumn(name = "role_name_level_2")
    private String roleNameLevel2;

    @KdbColumn(name = "comp_name_level_2")
    private String compNameLevel2;

    @KdbColumn(name = "team_id_level_3")
    private String teamIdLevel3;

    @KdbColumn(name = "team_name_level_3")
    private String teamNameLevel3;

    @KdbColumn(name = "team_id_owner_level_3")
    private String teamIdOwnerLevel3;

    @KdbColumn(name = "owner_name_level_3")
    private String ownerNameLevel3;

    @KdbColumn(name = "role_name_level_3")
    private String roleNameLevel3;

    @KdbColumn(name = "comp_name_level_3")
    private String compNameLevel3;

    @KdbColumn(name = "mo_customer_name")
    private String moCustomerName;

    @KdbColumn(name = "mo_product_type")
    private String moProductType;

    @KdbColumn(name = "mo_speed_bucket")
    private String moSpeedBucket;

    @KdbColumn(name = "mo_carrier")
    private String moCarrier;

    @KdbColumn(name = "mo_order_date")
    private LocalDateTime moOrderDate;

    @KdbColumn(name = "mo_order_number")
    private String moOrderNumber;

    @KdbColumn(name = "mo_account_number")
    private String moAccountNumber;

    @KdbColumn(name = "mo_status")
    private String moStatus;

    @KdbColumn(name = "max_date")
    private Long maxDate;

    @KdbColumn(name = "min_date")
    private Long minDate;

    @KdbColumn(name = "mc_team_id")
    private String mcTeamId;

    @KdbColumn(name = "mc_role_id")
    private String mcRoleId;

    @KdbColumn(name = "mc_comp_id")
    private String mcCompId;
}
