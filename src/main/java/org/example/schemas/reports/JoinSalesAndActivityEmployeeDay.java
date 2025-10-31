package org.example.schemas.reports;

import org.example.Annotations.KdbColumn;
import org.example.Annotations.KdbPrimaryKey;
import org.example.Annotations.KdbTable;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@KdbTable(
        description = "Joined sales and activity data per employee per day",
        name = "join_sales_and_activity_employee_day",
        tags = {"sales", "activity", "employee", "aggregation"},
        type = ""
)
@Component
public class JoinSalesAndActivityEmployeeDay {

    @KdbPrimaryKey
    @KdbColumn(name = "my_row_id")
    private Long myRowId;

    @KdbColumn(name = "employee_id",isNullable = false)
    private String employeeId;

    @KdbColumn(name = "start_date")
    private LocalDateTime startDate;

    @KdbColumn(name = "order_date")
    private LocalDate orderDate;

    @KdbColumn(name = "day_of_week",isNullable = false)
    private Long dayOfWeek;

    @KdbColumn(name = "week_of_year",isNullable = false)
    private Long weekOfYear;

    @KdbColumn(name = "month_of_year",isNullable = false)
    private Long monthOfYear;

    @KdbColumn(name = "seller_name",isNullable = false)
    private String sellerName;

    @KdbColumn(name = "cancelled",isNullable = false)
    private BigDecimal cancelled;

    @KdbColumn(name = "pending",isNullable = false)
    private BigDecimal pending;

    @KdbColumn(name = "installed",isNullable = false)
    private BigDecimal installed;

    @KdbColumn(name = "unit_net",isNullable = false)
    private BigDecimal unitNet;

    @KdbColumn(name = "unit_gross",isNullable = false)
    private BigDecimal unitGross;

    @KdbColumn(name = "unit_weighted_net",isNullable = false)
    private Double unitWeightedNet;

    @KdbColumn(name = "unit_weighted_gross",isNullable = false)
    private Double unitWeightedGross;

    @KdbColumn(name = "attachment_net",isNullable = false)
    private BigDecimal attachmentNet;

    @KdbColumn(name = "attachment_gross",isNullable = false)
    private BigDecimal attachmentGross;

    @KdbColumn(name = "internet_net",isNullable = false)
    private BigDecimal internetNet;

    @KdbColumn(name = "internet_gross",isNullable = false)
    private BigDecimal internetGross;

    @KdbColumn(name = "weighted_internet_net",isNullable = false)
    private Double weightedInternetNet;

    @KdbColumn(name = "weighted_internet_gross",isNullable = false)
    private Double weightedInternetGross;

    @KdbColumn(name = "new_internet_net",isNullable = false)
    private BigDecimal newInternetNet;

    @KdbColumn(name = "new_internet_gross",isNullable = false)
    private BigDecimal newInternetGross;

    @KdbColumn(name = "upgrade_net",isNullable = false)
    private BigDecimal upgradeNet;

    @KdbColumn(name = "tv_net",isNullable = false)
    private BigDecimal tvNet;

    @KdbColumn(name = "landline_net",isNullable = false)
    private BigDecimal landlineNet;

    @KdbColumn(name = "mobility_net",isNullable = false)
    private BigDecimal mobilityNet;

    @KdbColumn(name = "triple_play",isNullable = false)
    private Long triplePlay;

    @KdbColumn(name = "gig_count",isNullable = false)
    private BigDecimal gigCount;

    @KdbColumn(name = "knocks",isNullable = false)
    private Long knocks;

    @KdbColumn(name = "knocks_unique",isNullable = false)
    private Long knocksUnique;

    @KdbColumn(name = "go_back_knocks",isNullable = false)
    private BigDecimal goBackKnocks;

    @KdbColumn(name = "not_home_knocks",isNullable = false)
    private BigDecimal notHomeKnocks;

    @KdbColumn(name = "not_interested_knocks",isNullable = false)
    private BigDecimal notInterestedKnocks;

    @KdbColumn(name = "interactions",isNullable = false)
    private Long interactions;

    @KdbColumn(name = "verified",isNullable = false)
    private BigDecimal verified;

    @KdbColumn(name = "unverified",isNullable = false)
    private BigDecimal unverified;

    @KdbColumn(name = "earliest_activity_date")
    private LocalDateTime earliestActivityDate;

    @KdbColumn(name = "latest_activity_date")
    private LocalDateTime latestActivityDate;

    @KdbColumn(name = "hours",isNullable = false)
    private BigDecimal hours;

    @KdbColumn(name = "business_unit")
    private String businessUnit;

    @KdbColumn(name = "comp_id")
    private String compId;

    @KdbColumn(name = "role_id")
    private String roleId;

    @KdbColumn(name = "original_team_id")
    private String originalTeamId;

    @KdbColumn(name = "original_team_id_owner")
    private String originalTeamIdOwner;

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
