package org.example.schemas.reports;

import org.example.Annotations.KdbColumn;
import org.example.Annotations.KdbIndex;
import org.example.Annotations.KdbPrimaryKey;
import org.example.Annotations.KdbTable;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

@KdbTable(
        description = "Aggregated MDU activity data per employee per day",
        name = "ag_mdu_activity_employee_day",
        tags = {"mdu", "activity", "employee", "aggregation"},
        type = ""
)
@Component
public class AgMduActivityEmployeeDay {

    @KdbPrimaryKey
    @KdbColumn(name = "id")
    private Long id;

    @KdbIndex(indexGroups = {"idx_employee_date"},order = {1})
    @KdbColumn(name = "employee_id",isNullable = false)
    private String employeeId;

    @KdbIndex(indexGroups = {"date_idx","idx_team","idx_employee_date"},order = {1,2,2})
    @KdbColumn(name = "activity_date",isNullable = false)
    private LocalDate activityDate;

    @KdbColumn(name = "calls")
    private Integer calls;

    @KdbColumn(name = "activity_unique_calls")
    private Integer activityUniqueCalls;

    @KdbColumn(name = "result_call_connected")
    private Integer resultCallConnected;

    @KdbColumn(name = "meetings")
    private Integer meetings;

    @KdbColumn(name = "activity_unique_meetings")
    private Integer activityUniqueMeetings;

    @KdbColumn(name = "result_meeting_complete")
    private Integer resultMeetingComplete;

    @KdbColumn(name = "other_engagement")
    private Integer otherEngagement;

    @KdbColumn(name = "activity_blitz")
    private Integer activityBlitz;

    @KdbColumn(name = "activity_lunch_learn")
    private Integer activityLunchLearn;

    @KdbColumn(name = "activity_marketing")
    private Integer activityMarketing;

    @KdbColumn(name = "activity_property_visit")
    private Integer activityPropertyVisit;

    @KdbColumn(name = "activity_event")
    private Integer activityEvent;

    @KdbColumn(name = "activity_discovery_meeting")
    private Integer activityDiscoveryMeeting;

    @KdbColumn(name = "role_id")
    private String roleId;

    @KdbColumn(name = "comp_id")
    private String compId;

    @KdbIndex(indexGroups = {"idx_team"},order = {1})
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

    @KdbColumn(name = "created_at")
    private Timestamp createdAt;

    @KdbColumn(name = "updated_at")
    private Timestamp updatedAt;

    @KdbColumn(name = "carrier")
    private String carrier;
}
