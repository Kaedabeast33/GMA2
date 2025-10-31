package org.example.schemas.reports;

import org.example.Annotations.KdbColumn;
import org.example.Annotations.KdbPrimaryKey;
import org.example.Annotations.KdbTable;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@KdbTable(
        description = "Aggregated activity data per employee per day",
        name = "ag_activity_employee_day",
        tags = {"activity", "employee", "daily"},
        type = ""
)
@Component
public class AgActivityEmployeeDay {

    @KdbPrimaryKey
    @KdbColumn(name = "my_row_id")
    private Long myRowId;

    @KdbColumn(name = "employee_id")
    private String employeeId;

    @KdbColumn(name = "activity_date")
    private LocalDate activityDate;

    @KdbColumn(name = "seller")
    private String seller;

    @KdbColumn(name = "seller_email")
    private String sellerEmail;

    @KdbColumn(name = "role_id")
    private String roleId;

    @KdbColumn(name = "comp_id")
    private String compId;

    @KdbColumn(name = "day_of_week")
    private Integer dayOfWeek;

    @KdbColumn(name = "month_of_year")
    private Integer monthOfYear;

    @KdbColumn(name = "week_of_year")
    private Integer weekOfYear;

    @KdbColumn(name = "latest_activity_date")
    private LocalDateTime latestActivityDate;

    @KdbColumn(name = "earliest_activity_date")
    private LocalDateTime earliestActivityDate;

    @KdbColumn(name = "knocks")
    private Long knocks;

    @KdbColumn(name = "knocks_unique")
    private Long knocksUnique;

    @KdbColumn(name = "go_back_knocks")
    private BigDecimal goBackKnocks;

    @KdbColumn(name = "not_home_knocks")
    private BigDecimal notHomeKnocks;

    @KdbColumn(name = "not_interested_knocks")
    private BigDecimal notInterestedKnocks;

    @KdbColumn(name = "interactions")
    private Long interactions;

    @KdbColumn(name = "interactions_unique")
    private Long interactionsUnique;

    @KdbColumn(name = "knocks_sales_visits")
    private BigDecimal knocksSalesVisits;

    @KdbColumn(name = "knocks_non_workable")
    private BigDecimal knocksNonWorkable;

    @KdbColumn(name = "verified")
    private BigDecimal verified;

    @KdbColumn(name = "unverified")
    private BigDecimal unverified;

    @KdbColumn(name = "original_team_id")
    private String originalTeamId;

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
