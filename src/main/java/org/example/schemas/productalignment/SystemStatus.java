package org.example.schemas.productalignment;

import org.example.Annotations.KdbColumn;
import org.example.Annotations.KdbPrimaryKey;
import org.example.Annotations.KdbTable;
import org.springframework.stereotype.Component;

@KdbTable(
        description = "tracks system status records and their translations",
        name = "align_system_status",
        tags = {"system", "status", "translation"},
        type = ""
)
@Component
public class SystemStatus {

    @KdbColumn(name = "id")
    @KdbPrimaryKey
    private Long id;

    @KdbColumn(name = "created_time")
    private String createdTime;

    @KdbColumn(name = "record_id")
    private String recordId;

    @KdbColumn(name = "system_status")
    private String systemStatus;

    @KdbColumn(name = "translated_status")
    private String translatedStatus;
}
