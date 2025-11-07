package org.example.inputs.schemas.leads;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import org.example.bank.Annotations.*;
@KdbTable(
        description = "table storing MDU meetings",
        name = "mdu_meeting",
        tags = {"mdu", "meeting", "telecom"},
        type = ""
)
public class MduMeeting {

    @KdbColumn(name = "db_id")
    @KdbPrimaryKey
    private String dbId;

    @KdbColumn(name = "eng_hub_id", isNullable = false, unique = true)
    private String engHubId;

    @KdbIndex(indexGroups = {"engagement_id"})
    @KdbColumn(name = "eng_db_id")
    private String engDbId;

    @KdbColumn(name = "start_time")
    private LocalDateTime startTime;

    @KdbColumn(name = "duration")
    private Integer duration;

    @KdbColumn(name = "type")
    private String type;

    @KdbColumn(name = "meeting_type")
    private String meetingType;
}
