package org.example.inputs.schemas.productalignment;

import org.example.bank.Annotations.*;

@KdbTable(
        description = "tracks system status records and their translations",
        name = "align_system_status",
        tags = {"system", "status", "translation"},
        type = ""
)
public class SystemStatus {

    @KdbColumn(name = "id")
    @KdbPrimaryKey
    private Long id;

    @KdbColumn(name = "created_time")
    private String createdTime;

    @KdbColumn(name = "record_id")
    private String recordId;

    @KdbColumn(name = "system_status",unique = true)
    private String systemStatus;

    @KdbColumn(name = "translated_status")
    private String translatedStatus;
}
