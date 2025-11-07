package org.example.inputs.schemas.commissions;

import org.example.bank.Annotations.*;

@KdbTable(
        description = "staging join table for MO and MP records",
        name = "staging_join_mo_mp",
        tags = {"staging", "mo", "mp", "join"},
        type = ""
)
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
