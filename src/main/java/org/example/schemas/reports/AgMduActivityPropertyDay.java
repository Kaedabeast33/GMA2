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
        description = "Aggregated MDU activity data per property per day",
        name = "ag_mdu_activity_property_day",
        tags = {"mdu", "activity", "property", "aggregation"},
        type = ""
)
@Component
public class AgMduActivityPropertyDay {

    @KdbPrimaryKey
    @KdbIndex(indexGroups = {"idx_ag_activity_company"},order = {1})
    @KdbColumn(name = "company_id")
    private String companyId;

    @KdbPrimaryKey
    @KdbIndex(indexGroups = {"idx_ag_activity_company","idx_ag_act_prop_deal_count","idx_team","idx_employee_date"},order = {2,2,2,2})
    @KdbColumn(name = "activity_date")
    private LocalDate activityDate;

    @KdbPrimaryKey
    @KdbIndex(indexGroups = {"idx_employee_date"},order = {1})
    @KdbColumn(name = "owner_id")
    private String ownerId;

    @KdbColumn(name = "calls")
    private Integer calls;

    @KdbColumn(name = "meetings")
    private Integer meetings;

    @KdbColumn(name = "other_engagement")
    private Integer otherEngagement;

    @KdbColumn(name = "result_meeting_complete")
    private Integer resultMeetingComplete;

    @KdbColumn(name = "result_call_connected")
    private Integer resultCallConnected;

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

    @KdbColumn(name = "property_name")
    private String propertyName;

    @KdbColumn(name = "property_tier")
    private String propertyTier;

    @KdbColumn(name = "property_origin")
    private String propertyOrigin;

    @KdbColumn(name = "property_status")
    private String propertyStatus;

    @KdbColumn(name = "translated_status")
    private String translatedStatus;

    @KdbColumn(name = "deal_count")
    @KdbIndex(indexGroups = {"idx_ag_activity_company","idx_ag_act_prop_deal_count"},order = {3,1})
    private Integer dealCount;

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
