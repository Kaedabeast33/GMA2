package org.example.inputs.schemas.productalignment;

import org.example.bank.Annotations.*;

@KdbTable(
        description = "tracks knocking status results",
        name = "align_knocking_status",
        tags = {"knocking", "status", "results"},
        type = ""
)
public class KnockingStatus {

    @KdbColumn(name = "id")
    @KdbPrimaryKey
    private Long id;

    @KdbColumn(name = "created_time")
    private String createdTime;

    @KdbColumn(name = "owner")
    private String owner;

    @KdbColumn(name = "record_id")
    private String recordId;

    @KdbColumn(name = "result")
    private String result;

    @KdbColumn(name = "result_group")
    private String resultGroup;

    @KdbColumn(name = "result_translated")
    private String resultTranslated;

    @KdbColumn(name = "system_column")
    private String systemColumn;


}
