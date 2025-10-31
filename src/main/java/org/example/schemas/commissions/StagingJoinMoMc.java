package org.example.schemas.commissions;

import org.example.Annotations.KdbColumn;
import org.example.Annotations.KdbIndex;
import org.example.Annotations.KdbPrimaryKey;
import org.example.Annotations.KdbTable;
import org.example.CommonValues.ValueTypes;
import org.springframework.stereotype.Component;

@KdbTable(
        description = "staging join table for MO and MC records",
        name = "staging_join_mo_mc",
        tags = {"staging", "mo", "mc", "join"},
        type = ""
)
@Component
public class StagingJoinMoMc {

    @KdbColumn(name = "my_row_id")
    @KdbPrimaryKey
    private Long myRowId;

    @KdbColumn(name = "mc_db_id",type = ValueTypes.VARCHAR50)
    @KdbIndex(indexGroups = {"idx_mo_mc_db_id"},order = {1})
    private String mcDbId;


    @KdbColumn(name = "mo_db_id",type = ValueTypes.VARCHAR50)
    @KdbIndex(indexGroups = {"idx_mo_mc_db_id"},order = {2})
    private String moDbId;
}
