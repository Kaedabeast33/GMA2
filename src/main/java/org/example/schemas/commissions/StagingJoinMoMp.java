package org.example.schemas.commissions;

import org.example.Annotations.KdbColumn;
import org.example.Annotations.KdbIndex;
import org.example.Annotations.KdbPrimaryKey;
import org.example.Annotations.KdbTable;
import org.springframework.stereotype.Component;

@KdbTable(
        description = "staging join table for MO and MP records",
        name = "staging_join_mo_mp",
        tags = {"staging", "mo", "mp", "join"},
        type = ""
)
@Component
public class StagingJoinMoMp {

    @KdbColumn(name = "my_row_id")
    @KdbPrimaryKey
    private Long myRowId;

    @KdbIndex(indexGroups = {"mp_db_id_idx"})
    @KdbColumn(name = "mp_db_id")
    private String mpDbId;

    @KdbIndex(indexGroups = {"mo_db_id_idx"})
    @KdbColumn(name = "mo_db_id")
    private String moDbId;
}
