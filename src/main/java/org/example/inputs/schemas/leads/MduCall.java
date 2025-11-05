package org.example.inputs.schemas.leads;

import org.example.bank.Annotations.*;

import java.time.LocalDateTime;

@KdbTable(
        description = "table storing MDU call records",
        name = "mdu_call",
        tags = {"call", "mdu", "telecom"},
        type = ""
)
public class MduCall {

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

    @KdbColumn(name = "direction")
    private String direction;

    @KdbColumn(name = "type")
    private String type;

    @KdbColumn(name = "call_type")
    private String callType;
}
