package org.example.gmaTables.ai;

import org.example.bank.Annotations.KdbColumn;
import org.example.bank.Annotations.KdbPrimaryKey;
import org.example.bank.Annotations.KdbReference;
import org.example.bank.Annotations.KdbTable;
import org.example.bank.commonValues.DefaultTypes;
import org.example.bank.commonValues.ValueTypes;

@KdbTable(description = "", name = "parse_input_types", type = "")
public class ParseInputTypes {

    @KdbColumn(name = "input_type")
    private String groupName;

    @KdbColumn(name = "description", type = ValueTypes.TEXT)
    private String description;

    @KdbColumn(name = "is_active")
    private Boolean isActive;

    @KdbReference(referenceColumns = {}, cascadeRule = "")
    @KdbColumn(name = "input_group_id")
    private String inputGroupId;



    @KdbPrimaryKey
    @KdbColumn(name = "db_id", defaultValue = DefaultTypes.UUID)
    private String dbId;
}
