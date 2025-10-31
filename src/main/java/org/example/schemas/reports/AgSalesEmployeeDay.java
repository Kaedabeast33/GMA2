package org.example.schemas.reports;

import org.example.Annotations.KdbColumn;
import org.example.Annotations.KdbIndex;
import org.example.Annotations.KdbPrimaryKey;
import org.example.Annotations.KdbTable;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;

@KdbTable(
        description = "Aggregated sales data per employee per day",
        name = "ag_sales_employee_day",
        tags = {"sales", "employee", "aggregation"},
        type = ""
)
@Component
public class AgSalesEmployeeDay {

    @KdbPrimaryKey
    @KdbColumn(name = "my_row_id")
    private Long myRowId;

    @KdbColumn(name = "employee_id")
    private String employeeId;

    @KdbIndex(indexGroups = {"idx_date"})
    @KdbColumn(name = "order_date")
    private LocalDate orderDate;

    @KdbColumn(name = "seller")
    private String seller;

    @KdbColumn(name = "day_of_week")
    private Long dayOfWeek;

    @KdbColumn(name = "month_of_year")
    private Long monthOfYear;

    @KdbColumn(name = "week_of_year")
    private Long weekOfYear;

    @KdbColumn(name = "cancelled")
    private BigDecimal cancelled;

    @KdbColumn(name = "pending")
    private BigDecimal pending;

    @KdbColumn(name = "installed")
    private BigDecimal installed;

    @KdbColumn(name = "unit_net")
    private BigDecimal unitNet;

    @KdbColumn(name = "unit_gross")
    private BigDecimal unitGross;

    @KdbColumn(name = "unit_weighted_net")
    private Double unitWeightedNet;

    @KdbColumn(name = "unit_weighted_gross")
    private Double unitWeightedGross;

    @KdbColumn(name = "attachment_net")
    private BigDecimal attachmentNet;

    @KdbColumn(name = "attachment_gross")
    private BigDecimal attachmentGross;

    @KdbColumn(name = "internet_net")
    private BigDecimal internetNet;

    @KdbColumn(name = "internet_gross")
    private BigDecimal internetGross;

    @KdbColumn(name = "new_internet_net")
    private BigDecimal newInternetNet;

    @KdbColumn(name = "new_internet_gross")
    private BigDecimal newInternetGross;

    @KdbColumn(name = "weighted_internet_net")
    private Double weightedInternetNet;

    @KdbColumn(name = "weighted_internet_gross")
    private Double weightedInternetGross;

    @KdbColumn(name = "upgrade_net")
    private BigDecimal upgradeNet;

    @KdbColumn(name = "tv_net")
    private BigDecimal tvNet;

    @KdbColumn(name = "landline_net")
    private BigDecimal landlineNet;

    @KdbColumn(name = "mobility_net")
    private BigDecimal mobilityNet;

    @KdbColumn(name = "unit_gross_no_landline")
    private BigDecimal unitGrossNoLandline;

    @KdbColumn(name = "unit_net_no_landline")
    private BigDecimal unitNetNoLandline;

    @KdbColumn(name = "gig_count")
    private BigDecimal gigCount;

    @KdbColumn(name = "triple_play")
    private Long triplePlay;

    @KdbColumn(name = "original_team_id")
    private String originalTeamId;

    @KdbColumn(name = "role_id")
    private String roleId;

    @KdbColumn(name = "comp_id")
    private String compId;

    @KdbColumn(name = "original_team_id_owner")
    private String originalTeamIdOwner;

    @KdbColumn(name = "business_unit")
    private String businessUnit;

    @KdbColumn(name = "team_id_level_1")
    private String teamIdLevel1;

    @KdbColumn(name = "team_id_owner_level_1")
    private String teamIdOwnerLevel1;

    @KdbColumn(name = "role_id_level_1")
    private String roleIdLevel1;

    @KdbColumn(name = "comp_id_level_1")
    private String compIdLevel1;

    @KdbColumn(name = "team_id_level_2")
    private String teamIdLevel2;

    @KdbColumn(name = "team_id_owner_level_2")
    private String teamIdOwnerLevel2;

    @KdbColumn(name = "role_id_level_2")
    private String roleIdLevel2;

    @KdbColumn(name = "comp_id_level_2")
    private String compIdLevel2;

    @KdbColumn(name = "team_id_level_3")
    private String teamIdLevel3;

    @KdbColumn(name = "team_id_owner_level_3")
    private String teamIdOwnerLevel3;

    @KdbColumn(name = "role_id_level_3")
    private String roleIdLevel3;

    @KdbColumn(name = "comp_id_level_3")
    private String compIdLevel3;
}
