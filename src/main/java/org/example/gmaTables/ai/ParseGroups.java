package org.example.gmaTables.ai;

import org.example.bank.Annotations.KdbColumn;
import org.example.bank.Annotations.KdbPrimaryKey;
import org.example.bank.Annotations.KdbReference;
import org.example.bank.Annotations.KdbTable;
import org.example.bank.commonValues.DefaultTypes;
import org.example.bank.commonValues.ValueTypes;
import org.springframework.context.annotation.Primary;

@KdbTable(description = "", name = "parse_groups", type = "")
public class ParseGroups {
    @KdbColumn(name = "upload_group")
    private String uploadGroup;



    @KdbColumn(name = "description", type =ValueTypes.TEXT)
    private String description;

    @KdbColumn(name = "is_active")
    private Boolean isActive;

    @KdbPrimaryKey
    @KdbColumn(name = "db_id", defaultValue = DefaultTypes.UUID)
    private String dbId;
}
