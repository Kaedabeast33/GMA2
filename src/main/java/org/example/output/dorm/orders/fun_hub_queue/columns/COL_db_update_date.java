package org.example.output.dorm.orders.fun_hub_queue.columns;


import org.example.gma.templates.ColumnTemplate;


public class COL_db_update_date extends ColumnTemplate {

    public COL_db_update_date() {
        super(
            "db_update_date"
,  // name
            "cold08a40a8-b9b3-412d-a72e-58ce22de594a"
,  // columnId
            "",  // description
            new String[]{""},  // tags
            true,  // isNullable
            true,  // isEditable
            false,  // isUnique
            false,  // isRequired
            "DATETIME"
,  // type
            "",  // defaultValue
            new String[]{"default"},  // columnGroups
            false,  // isUniqueIdentifier
            new String[]{""},  // uniqueIdentifierGroups
            false,  // isIndex
            new String[]{""},  // indexGroups
            null,  // referenceColumns
            java.time.LocalDateTime.class,  // fieldType
            org.example.CommonValues.DefaultKdbConverter.class  // kdbConverter


        );
    }

}
