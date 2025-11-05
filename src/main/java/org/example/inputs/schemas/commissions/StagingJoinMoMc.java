package org.example.inputs.schemas.commissions;

import org.example.bank.Annotations.*;
import org.example.bank.commonValues.ValueTypes;


@KdbTable(
        description = "staging join table for MO and MC records",
        name = "staging_join_mo_mc",
        tags = {"staging", "mo", "mc", "join"},
        type = ""
)
public class StagingJoinMoMc {

    @KdbColumn(name = "my_row_id")
    @KdbPrimaryKey
    private Long myRowId;

    @KdbColumn(name = "mc_db_id", type = ValueTypes.VARCHAR50)
    @KdbIndex(indexGroups = {"idx_mo_mc_db_id"}, order = {1})
    private String mcDbId;


    @KdbColumn(name = "mo_db_id", type = ValueTypes.VARCHAR50)
    @KdbIndex(indexGroups = {"idx_mo_mc_db_id"}, order = {2})
    private String moDbId;
}
