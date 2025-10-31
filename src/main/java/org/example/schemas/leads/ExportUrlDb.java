package org.example.schemas.leads;

import org.example.Annotations.KdbColumn;
import org.example.Annotations.KdbPrimaryKey;
import org.example.Annotations.KdbTable;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@KdbTable(
        description = "table storing export URLs for various objects",
        name = "export_url_db",
        tags = {"export", "url", "db"},
        type = ""
)
@Component
public class ExportUrlDb {

    @KdbColumn(name = "id")
    @KdbPrimaryKey
    private Long id;

    @KdbColumn(name = "timestamp")
    private LocalDateTime timestamp;

    @KdbColumn(name = "object_id")
    private String objectId;

    @KdbColumn(name = "type")
    private String type;

    @KdbColumn(name = "url")
    private String url;
}
